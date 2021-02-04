package aed.accesoficheros;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
	
	private static Stage primaryStage;
	
	private MainController controller;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		App.primaryStage = primaryStage;
		
		controller = new MainController();
		
		Scene escena = new Scene(controller.getView());
		
		primaryStage.setScene(escena);
		primaryStage.setTitle("Acceso a ficheros - Sergio Rodríguez Rodríguez");
		primaryStage.getIcons().add(new Image("/images/open-16x16.png"));
		primaryStage.show();
	}
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

}