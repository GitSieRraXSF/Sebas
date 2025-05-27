package Controller;

import java.sql.Connection;
import Data.DBConnectionFactory;
import Data.UsuarioDAO;
import Model.UserSession;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

public class ConeccionesController {

	@FXML
    private ComboBox<String> cbConnect;
	
	private Connection connection;
	private UsuarioDAO userDAO;
	public UserSession userSession;
	
	@FXML
	void initialize() {
		cbConnect.getItems().addAll("UsuarioOracle", "UsuarioSQLite");
	}
	
    @FXML
    void btoSession(ActionEvent event) {
    	switch (cbConnect.getSelectionModel().getSelectedItem()) {
    	case "UsuarioOracle":
    		connection = DBConnectionFactory.getConnectionByRole("UsuarioOracle").getConnection();
    		userDAO = new UsuarioDAO(connection);
			userSession = UserSession.getInstance("UsuarioOracle");
			Main.loadView("/view/GestionUsuarios.fxml");
			break;
    	case "UsuarioSQLite":
    		connection = DBConnectionFactory.getConnectionByRole("UsuarioSQLite").getConnection();
    		userDAO = new UsuarioDAO(connection);
			userSession = UserSession.getInstance("UsuarioSQLite");
			Main.loadView("/view/GestionUsuarios.fxml");
			break;
		default:
			Main.showAlert("Error!", "Ninguna Seleccion...", "Deberas elegir una eleccion para continuar", Alert.AlertType.ERROR);
    	}
    }
}
