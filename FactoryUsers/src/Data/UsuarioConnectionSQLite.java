package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UsuarioConnectionSQLite implements DBConnection {

	private static UsuarioConnectionSQLite instance; // Singleton
	private Connection connection;

	public UsuarioConnectionSQLite() {
		try {
			connection = DriverManager.getConnection(getConnectionString());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error connecting to the database.");
		}
	}

	public static UsuarioConnectionSQLite getInstance() {
		if (instance == null)
			instance = new UsuarioConnectionSQLite();
		return instance;
	}

	public Connection getConnection() {
		return connection;
	}

	public String getConnectionString() {
		return String.format("jdbc:sqlite:usuario.db");
	}
}