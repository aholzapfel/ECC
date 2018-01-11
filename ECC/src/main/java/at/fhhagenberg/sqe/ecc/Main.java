package at.fhhagenberg.sqe.ecc;
	
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import at.fhhagenberg.sqe.ecc.controller.ElevatorControlCenterController;
import at.fhhagenberg.sqe.ecc.sqelevator.ElevatorSystem;
import at.fhhagenberg.sqe.ecc.sqelevator.IElevator;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private static final int WINDOW_HEIGHT = 500;
	private static final int WINDOW_WIDTH = 800;
	
	private static final int NUMBER_OF_FLOORS = 4;
	private static final int NUMBER_OF_ELEVATORS = 4;
	
	ElevatorControlCenterController controller;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			IElevator elevatorSystem = new ElevatorSystem(NUMBER_OF_FLOORS, NUMBER_OF_ELEVATORS);
			
			URL location = Main.class.getClassLoader().getResource("layouts\\elevator_control_center.fxml");
			
			FXMLLoader loader = new FXMLLoader(location);
			Pane root = loader.load();

			controller =  loader.<ElevatorControlCenterController>getController();
			controller.init(elevatorSystem);
			
			Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("application.css").toExternalForm());
			
			primaryStage.setTitle("Elevator Control Center");
			primaryStage.setMinWidth(WINDOW_WIDTH);
			primaryStage.setMinHeight(WINDOW_HEIGHT);
			primaryStage.setScene(scene);
			primaryStage.show();
		
			Runnable updateRunnable = new Runnable() {
			    public void run() {
					controller.update();
			    }
			};

			ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
			executor.scheduleAtFixedRate(updateRunnable, 0, elevatorSystem.getClockTick(), TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
