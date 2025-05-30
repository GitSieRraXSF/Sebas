package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UsuarioConnectionOracle implements DBConnection {

	private static UsuarioConnectionOracle instance; // Singleton
	private Connection connection;
	private final String username = "Usuario1";
	private final String password = "Usuario1X";
	private final String host = "192.168.254.215";
	private final String port = "1521";
	private final String service = "orcl";

	public UsuarioConnectionOracle() {
		try {
			connection = DriverManager.getConnection(getConnectionString(), username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error connecting to the database.");
		}
	}

	public static UsuarioConnectionOracle getInstance() {
		if (instance == null)
			instance = new UsuarioConnectionOracle();
		return instance;
	}

	public Connection getConnection() {
		return connection;
	}

	public String getConnectionString() {
		return String.format("jdbc:oracle:thin:@%s:%s:%s", this.host, this.port, this.service);
	}
}