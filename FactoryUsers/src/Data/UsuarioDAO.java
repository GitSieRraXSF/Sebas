package Data;

import java.sql.CallableStatement;
import oracle.jdbc.OracleTypes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Model.Usuario;
import application.Main;
import javafx.scene.control.Alert;

public class UsuarioDAO {

	private Connection connection;

	public UsuarioDAO(Connection connection) {
		this.connection = connection;
	}

	public void Save(Usuario usuario) {
		String sql = "{call InsertUsuario(?, ?, ?, ?)}";
		try (CallableStatement stmt = connection.prepareCall(sql)) {
			stmt.setInt(2, usuario.getId());
			stmt.setString(2, usuario.getNombre());
			stmt.setString(2, usuario.getApellido());
			stmt.setString(2, usuario.getEmail());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			Main.showAlert("Error...!", "Proceso invalido!", e.getMessage(), Alert.AlertType.ERROR);
		}
	}

	public ArrayList<Usuario> Fetch() {
		ArrayList<Usuario> usuarios = new ArrayList<>();
		// String sequel = "SELECT * FROM PROGRAMMINGII.Producto";
		String sql = "{? = call FetchUsuario()}";
		try (CallableStatement cs = connection.prepareCall(sql)) {
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.execute();
			try (ResultSet rs = (ResultSet) cs.getObject(1)) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String nombre = rs.getString("nombre");
					String apellido = rs.getString("apellido");
					String email = rs.getString("email");
					Usuario producto = new Usuario(id, nombre, apellido, email);
					usuarios.add(producto);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Main.showAlert("Error...!", "Proceso invalido!", e.getMessage(), Alert.AlertType.ERROR);
		}
		return usuarios;
	}

	public void Update(Usuario usuario) {
		String sql = "{call = UpdateUsuario(?, ?, ?, ?)}";
		try (CallableStatement stmt = connection.prepareCall(sql)) {
			stmt.setInt(1, usuario.getId());
			stmt.setString(2, usuario.getNombre());
			stmt.setString(3, usuario.getApellido());
			stmt.setString(4, usuario.getEmail());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			Main.showAlert("Error...!", "Proceso invalido!", e.getMessage(), Alert.AlertType.ERROR);
		}
	}

	public void Delete(int ID) {
		String sql = "{call DeleteUsuario(?)}";
		try (CallableStatement stmt = connection.prepareCall(sql)) {
			stmt.setInt(1, ID);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			Main.showAlert("Error...!", "Proceso invalido!", e.getMessage(), Alert.AlertType.ERROR);
		}
	}
	
	public boolean authenticate(int ID) {
		String sql = "{? = call AuthenticateUsuario(?)}";
		try (CallableStatement stmt = connection.prepareCall(sql)) {
			stmt.registerOutParameter(1, java.sql.Types.INTEGER);
			stmt.setInt(2, ID);
			stmt.execute();
			int result = stmt.getInt(1);
			return result == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			Main.showAlert("Error...!", "Proceso invalido!", e.getMessage(), Alert.AlertType.ERROR);
		}
		return false;
	}
}