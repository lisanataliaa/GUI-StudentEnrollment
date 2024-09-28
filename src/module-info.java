module ProjectBAD {
	opens main;
	opens db;

	requires java.sql;
	requires javafx.graphics;
	requires javafx.controls;
}