package main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import db.Database;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application implements EventHandler<ActionEvent> {

	Database db = Database.getDB();

	BorderPane outperPane;

	GridPane innerPane;

	Label nLabel;
	TextField nField;

	Label gLabel;
	TextField gField;

//	Button sumbitbtn;
	Button deleteBtn;
	Button updateBtn;

	TableView<Mahasiswa> mhsList;

	Scene scene;

	VBox innerBox;
	HBox BtnBox;

	public Main() {

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void init() {
		outperPane = new BorderPane();
		innerPane = new GridPane();
		nLabel = new Label("Name:");
		nField = new TextField();
		gLabel = new Label("Gender: ");
		gField = new TextField();

//		sumbitbtn = new Button("Submit");
		deleteBtn = new Button("Delete");
		updateBtn = new Button("Update");
		mhsList = new TableView<>();
		innerBox = new VBox();
		BtnBox = new HBox();

		scene = new Scene(outperPane, 500, 500);
	}

	public void layout() {
		innerPane.add(nLabel, 0, 0);
		innerPane.add(nField, 1, 0);
		innerPane.add(gLabel, 0, 1);
		innerPane.add(gField, 1, 1);
//		innerPane.add(sumbitbtn, 0, 2);
		innerPane.add(deleteBtn, 1, 2);
		innerPane.add(updateBtn, 2, 2);
		innerPane.setVgap(10);
		innerPane.setHgap(10);
		innerPane.setPadding(new Insets(10));
		innerBox.getChildren().addAll(innerPane, BtnBox);
//		BtnBox.getChildren().addAll(sumbitbtn, updateBtn, deleteBtn);
		BtnBox.getChildren().addAll(updateBtn, deleteBtn);
		outperPane.setTop(innerBox);
		innerBox.setPadding(new Insets(10));
		BtnBox.setSpacing(10);
		outperPane.setCenter(mhsList);
	}

	public void setTable() {
		TableColumn<Mahasiswa, String> nCol = new TableColumn<>("Name");
		TableColumn<Mahasiswa, String> gCol = new TableColumn<>("Gender");
		nCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		gCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
		nCol.setMinWidth(scene.getWidth() / 2);
		gCol.setMinWidth(scene.getWidth() / 2);
		mhsList.getColumns().addAll(nCol, gCol);
		mhsList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		mhsList.setOnMouseClicked(handleTableEvent());

	}

	private EventHandler<MouseEvent> handleTableEvent() {
		return new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Mahasiswa m = mhsList.getSelectionModel().getSelectedItem();
				nField.setText(m.getName());
				gField.setText(m.getGender());
			}
		};
	}

	public void setHandler() {
//		sumbitbtn.setOnAction(this);
		deleteBtn.setOnAction(this);
		updateBtn.setOnAction(this);
	}

	@Override
	public void start(Stage stage) throws Exception {
		init();
		layout();
		setHandler();
		setTable();
		getMahasiswaData();

		stage.setTitle("Enrollment Data Mahasiswa");
		stage.setScene(scene);
		stage.show();

		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent arg0) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setContentText("Are you sure want to exit?");
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.CANCEL) {
					arg0.consume();
				}
			}
		});
	}

	@Override
	public void handle(ActionEvent e) {
//		if (e.getSource() == sumbitbtn) {
//			db.addMahasiswa(nField.getText(), gField.getText());
//			mhsList.getItems().clear();
//			getMahasiswaData();
//		} 
		if (e.getSource() == deleteBtn) {
			Mahasiswa m = mhsList.getSelectionModel().getSelectedItem();
			String id = m.getStudentID();

			String query = String.format("DELETE FROM mahasiswa WHERE studentID = '%s'", id);
			db.executeUpdate(query);

			mhsList.getItems().removeAll(mhsList.getSelectionModel().getSelectedItems());

		} else if (e.getSource() == updateBtn) {
			String newName = nField.getText();
			String newGender = gField.getText();

			Mahasiswa m = mhsList.getSelectionModel().getSelectedItem();
			String id = m.getStudentID();

			String query = String.format("UPDATE mahasiswa SET name = ?, gender = ? WHERE studentID = ?");
			PreparedStatement ps = db.prepareStatement(query);
			try {
				ps.setString(1, newName);
				ps.setString(2, newGender);
				ps.setString(3, id);
				ps.execute();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			mhsList.getItems().clear();
			getMahasiswaData();
		}

	}

	private void getMahasiswaData() {
		ResultSet rs = db.getMahasiswaData();
		try {
			while (rs.next()) {
				String studentID = rs.getString("studentID");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				mhsList.getItems().add(new Mahasiswa(studentID, name, gender));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
