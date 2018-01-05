package at.fhhagenberg.sqe.ecc;
	
import java.net.URL;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private static final int WINDOW_HEIGHT = 500;
	private static final int WINDOW_WIDTH = 800;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			URL location = Main.class.getClassLoader().getResource("layouts\\elevator_control_center.fxml");
			Pane root = FXMLLoader.load(location);
			
			Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("application.css").toExternalForm());
			
			primaryStage.setTitle("Elevator Control Center");
			primaryStage.setMinWidth(WINDOW_WIDTH);
			primaryStage.setMinHeight(WINDOW_HEIGHT);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
