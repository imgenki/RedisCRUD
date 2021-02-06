package aed.redisCRUD;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import redis.clients.jedis.*;

public class MainController implements Initializable{
    
    @FXML
    private VBox view;

    @FXML
    private TableView<Usuario> userTable;
    @FXML
    private TableColumn<Usuario, String> userColumn;

    @FXML
    private TableColumn<Usuario, String> nameColumn;

    @FXML
    private TableColumn<Usuario, String> mailColumn;

    @FXML
    private TableColumn<Usuario, String> passColumn;

    @FXML
    private TableColumn<Usuario, String> nacionalidadColumn;

    @FXML
    private ListView<String> usersListShow;

    @FXML
    private Tab createTab;

    @FXML
    private Button crearButton;

    @FXML
    private TextField usuarioText;

    @FXML
    private TextField nombreText;

    @FXML
    private TextField emailText;

    @FXML
    private TextField contrasenaText;

    @FXML
    private TextField nacionalidadText;

    @FXML
    private Tab updateTab;

    @FXML
    private Button actualizarButton;

    @FXML
    private TextField usuarioText1;

    @FXML
    private TextField nombreText1;

    @FXML
    private TextField emailText1;

    @FXML
    private TextField contrasenaText1;

    @FXML
    private TextField nacionalidadText1;

    @FXML
    private Tab deleteTab;

    @FXML
    private Button eliminarButton;
	// Threadsafe pool of network connections
	JedisPool pool;
	
	// Redis connection
	Jedis jedis;
    
    private ListProperty<Usuario> usuariosInfo = new SimpleListProperty<>(FXCollections.observableArrayList());
    private ListProperty<String> usuarios = new SimpleListProperty<>(FXCollections.observableArrayList());

    public MainController() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
    	loader.setController(this);
    	loader.load();
    }
    
    public VBox getView() {
    	return view;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		jedis = new Jedis("localhost");
		userColumn.setCellValueFactory(cellData -> cellData.getValue().nombreUsuarioProperty());
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
		mailColumn.setCellValueFactory(cellData -> cellData.getValue().mailProperty());
		passColumn.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
		nacionalidadColumn.setCellValueFactory(cellData -> cellData.getValue().nacionalidadProperty());
		
		
		//jedis.sadd("users", "pepe", "juan", "antonio");
		
//		jedis.hset("pepe", "email", "pepe@mail.com");
//		jedis.hset("pepe", "nombre", "Pepe");
//		jedis.hset("pepe", "password", "grillos");
//		jedis.hset("pepe", "nacionalidad", "Chipude");
//		Usuario user = new Usuario();
//		user.setMail(jedis.hget("pepe", "email"));
//		user.setNacionalidad(jedis.hget("pepe", "nacionalidad"));
//		user.setNombre(jedis.hget("pepe", "nombre"));
//		user.setPassword(jedis.hget("pepe", "password"));
//		user.setNombreUsuario(null);
		
		usuarios.addAll(jedis.smembers("users"));
		
		for(String usrName : jedis.smembers("users")) {
			Usuario user = new Usuario();
			user.setMail(jedis.hget(usrName, "email"));
			user.setNacionalidad(jedis.hget(usrName, "nacionalidad"));
			user.setNombre(jedis.hget(usrName, "nombre"));
			user.setPassword(jedis.hget(usrName, "password"));
			user.setNombreUsuario(usrName);
			usuariosInfo.add(user);
		}
		usersListShow.itemsProperty().bind(usuarios);
		userTable.itemsProperty().bind(usuariosInfo);
	}

    @FXML
    void onActualizarAction(ActionEvent event) {

    }

    @FXML
    void onCrearAction(ActionEvent event) {
    	jedis.sadd("users", usuarioText.textProperty().get());
		jedis.hset(usuarioText.textProperty().get(), "email", emailText.textProperty().get());
		jedis.hset(usuarioText.textProperty().get(), "nombre", nombreText.textProperty().get());
		jedis.hset(usuarioText.textProperty().get(), "password", contrasenaText.textProperty().get());
		jedis.hset(usuarioText.textProperty().get(), "nacionalidad", nacionalidadText.textProperty().get());
		
		usuarios.clear();
		usuarios.addAll(jedis.smembers("users"));
		usuariosInfo.clear();
		for(String usrName : jedis.smembers("users")) {
			Usuario user = new Usuario();
			user.setMail(jedis.hget(usrName, "email"));
			user.setNacionalidad(jedis.hget(usrName, "nacionalidad"));
			user.setNombre(jedis.hget(usrName, "nombre"));
			user.setPassword(jedis.hget(usrName, "password"));
			user.setNombreUsuario(usrName);
			usuariosInfo.add(user);
		}
		
		usuarioText.clear();
		emailText.clear();
		nombreText.clear();
		contrasenaText.clear();
		nacionalidadText.clear();
		
    }

    @FXML
    void onEliminarAction(ActionEvent event) {

    }
	
	public Jedis getConnection() {
		pool = new JedisPool(new JedisPoolConfig(), "localhost");
		
		jedis = pool.getResource();
		
		return jedis;
		
	}
	public void destroyPool() {

		// Close the connection
		if (jedis != null) {
			jedis.close();
		}
		
		// Destroy the pool
		if (pool != null) {
			pool.destroy();
		}
	}
	
}
