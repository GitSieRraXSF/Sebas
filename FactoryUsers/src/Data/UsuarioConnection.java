package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UsuarioConnection implements DBConnection {

	private static UsuarioConnection instance; // Singleton
	private Connection connection;
	private final String username = "Usuario1";
	private final String password = "Usuario1X";
	private final String host = "192.168.254.215";
	private final String port = "1521";
	private final String service = "orcl";

	private UsuarioConnection() {
		try {
			connection = DriverManager.getConnection(getConnectionString(), username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error connecting to the database.");
		}
	}

	public static UsuarioConnection getInstance() {
		if (instance == null)
			instance = new UsuarioConnection();
		return instance;
	}

	public Connection getConnection() {
		return connection;
	}

	public String getConnectionString() {
		return String.format("jdbc:oracle:thin:@%s:%s:%s", this.host, this.port, this.service);
	}
}