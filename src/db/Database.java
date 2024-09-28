package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	private final String USERNAME = "root";
	private final String PASSWORD = "";
	private final String DATABASE = "project_bad";
	private final String HOST = "localhost:3306";
	private final String CONNECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);

	private Connection con;
	public Statement state;
	private static Database db;

	public static Database getDB() {
		if (db == null) {
			db = new Database();
		}
		return db;
	}

	private Database() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
			state = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ResultSet executeQuery(String query) {
		ResultSet rs = null;
		try {
			rs = state.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public void executeUpdate(String query) {
		try {
			state.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addMahasiswa(String name, String gender) {
		String query = String.format("INSERT INTO mahasiswa (studentId, name, gender) VALUES ('%s', '%s', '%s')", name,
				gender);
		executeUpdate(query);
	}

	public ResultSet getMahasiswaData() {
		String query = "SELECT * FROM mahasiswa";
		ResultSet rs = null;
		rs = executeQuery(query);
		return rs;
	}

	public PreparedStatement prepareStatement(String query) {
		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ps;
	}

}
