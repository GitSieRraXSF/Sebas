module FactoryUsers {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires com.oracle.database.jdbc;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
}
