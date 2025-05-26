package Data;

public class DBConnectionFactory {

	public static DBConnection getConnectionByRole(String role) {
		switch (role.toLowerCase()) {
		case "Usuario":
			return UsuarioConnection.getInstance();
		default:
			throw new IllegalArgumentException("Rol no válido: " + role);
		}
	}
}
