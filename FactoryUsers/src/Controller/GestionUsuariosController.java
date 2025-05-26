package Controller;

import java.sql.Connection;
import Data.DBConnectionFactory;
import Data.UsuarioDAO;
import Model.UserSession;
import Model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class GestionUsuariosController {

	@FXML
    private TableColumn<Usuario, String> colapellido;

    @FXML
    private TableColumn<Usuario, String> colcorreo;

    @FXML
    private TableColumn<Usuario, Integer> colid;

    @FXML
    private TableColumn<Usuario, String> colnombre;

    @FXML
    private TableView<Usuario> tableUser;
    
    private Connection connection = DBConnectionFactory.getConnectionByRole(UserSession.getInstance().getRole()).getConnection();
	private UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
	
	@FXML
	public void initialize() {
		
		ObservableList<Usuario> availableUsers = FXCollections.observableArrayList();
		// Filter available products and add them to the availableProducts list
		for (Usuario user : usuarioDAO.Fetch()) {
			availableUsers.add(user);
		}
		// Bind only the columns you want to show
	}
    
    @FXML
    void btoActualizar(ActionEvent event) {

    }

    @FXML
    void btoAgregar(ActionEvent event) {

    }

    @FXML
    void btoEliminar(ActionEvent event) {

    }
}
