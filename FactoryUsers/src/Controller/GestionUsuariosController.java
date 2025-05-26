package Controller;

import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Data.DBConnectionFactory;
import Data.UsuarioDAO;
import Model.UserSession;
import Model.Usuario;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    
    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtapellido;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtnombre;
    
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
		colid.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		colnombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		colapellido.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		colcorreo.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		// Set data to TableView
		tableUser.setItems(availableUsers);
	}
    
    @FXML
    void btoActualizar(ActionEvent event) {
    	int id = Integer.parseInt(txtid.getText());
    	String nombre = txtnombre.getText();
    	String apellido = txtapellido.getText();
    	String email = colcorreo.getText();
    	if (usuarioDAO.authenticate(id) && UserSession.getInstance().getRole().equals("Usuario")) {
    		Usuario usuario = new Usuario(id, nombre, apellido, email);
    		usuarioDAO.Update(usuario);
    	} else {
    		Main.showAlert("Error!", "El usuario que ingresaste no esta guardado o rol invalido!", "Si deseas actulizar alguno, compruebe los repectivos datos.", Alert.AlertType.ERROR);
    	}
    }

    @FXML
    void btoAgregar(ActionEvent event) {
    	int id = Integer.parseInt(txtid.getText());
    	String nombre = txtnombre.getText();
    	String apellido = txtapellido.getText();
    	String email = colcorreo.getText();
    	if (!usuarioDAO.authenticate(id) && UserSession.getInstance().getRole().equals("Usuario")) {
    		Usuario usuario = new Usuario(id, nombre, apellido, email);
    		if (id > 0 || isValidEmail(email)) {
    			usuarioDAO.Save(usuario);
    		} else {
    			Main.showAlert("Error!", "Formatos invalidos!", "ingrese con un formato valido", Alert.AlertType.ERROR);
    		}
    	} else {
    		Main.showAlert("Error!", "El usuario ya esta registrado o el rol no corresponde!", "Si deseas agregar uno, que tengan datos o valores diferentes.", Alert.AlertType.ERROR);
    	}
    }

    @FXML
    void btoEliminar(ActionEvent event) {
    	if (!tableUser.getSelectionModel().isEmpty() && UserSession.getInstance().getRole().equals("Usuario")) {
			Usuario user = tableUser.getSelectionModel().getSelectedItem();
			usuarioDAO.Delete(user.getId());
			initialize();
		} else {
			Main.showAlert("Ningun producto seleccionado O Acceso denegado", "Referencia repetida O Acceso denegado", "Debe registrar una referencia diferente O debes entrar al rol respectivo.", Alert.AlertType.ERROR);
		}
		initialize();
    }
    
	public static boolean isValidEmail(String email) {
		// Expresión regular para validar el formato del correo electrónico
		String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
