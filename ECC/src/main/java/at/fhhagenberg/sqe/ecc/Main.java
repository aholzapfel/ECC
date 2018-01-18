package at.fhhagenberg.sqe.ecc;
	
import java.net.URL;
import java.rmi.Naming;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import at.fhhagenberg.sqe.ecc.controller.ElevatorControlCenterController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import sqelevator.IElevator;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private static final int WINDOW_HEIGHT = 800;
	private static final int WINDOW_WIDTH = 500;
	
	private static IElevator elevatorSystem;
	private static ElevatorControlCenterController controller;
	
	
	public Main() {
		try {
			elevatorSystem = (IElevator) Naming.lookup("rmi://localhost/ElevatorSim");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Main(IElevator elevatorSystem) {
		Main.elevatorSystem = elevatorSystem;
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {		
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
		
			startPolling(elevatorSystem.getClockTick());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void startPolling(long clockTick) {
		Runnable pollingRunnable = new Runnable() {
		    public void run() {
		    	Platform.runLater(controller.updateRunnable);
		    }
		};

		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(pollingRunnable, 0, clockTick, TimeUnit.MILLISECONDS);
	}
}
