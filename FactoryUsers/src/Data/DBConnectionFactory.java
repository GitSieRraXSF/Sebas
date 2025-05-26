package Data;

public class DBConnectionFactory {

	public static DBConnection getConnectionByRole(String role) {
		switch (role.toLowerCase()) {
		case "UsuarioOracle":
			return UsuarioConnectionOracle.getInstance();
		case "":
			return UsuarioConnectionSQLite.getInstance();
		default:
			throw new IllegalArgumentException("Rol no válido: " + role);
		}
	}
}
