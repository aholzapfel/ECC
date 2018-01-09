package at.fhhagenberg.sqe.ecc;
	
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import at.fhhagenberg.sqe.ecc.controller.ElevatorControlCenterController;
import at.fhhagenberg.sqe.ecc.controller.ElevatorController;
import at.fhhagenberg.sqe.ecc.sqelevator.ElevatorControlCenter;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private static final int WINDOW_HEIGHT = 500;
	private static final int WINDOW_WIDTH = 800;
	
	private final int numberOfElevators = 4;
	private final int numberOfFloors = 4;
	
	ElevatorControlCenterController controller;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			ElevatorControlCenter ecc = new ElevatorControlCenter(numberOfFloors, numberOfElevators);
			
			URL location = Main.class.getClassLoader().getResource("layouts\\elevator_control_center.fxml");
			//Pane root = FXMLLoader.load(location);
			
			FXMLLoader loader = new FXMLLoader(location);

			Pane root = (Pane) loader.load();

			controller =  loader.<ElevatorControlCenterController>getController();
			
			//controller.testGetController();
			    

			
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
		

		
		Runnable helloRunnable = new Runnable() {
		    public void run() {
				controller.update();
		    }
		};

		
		try {
			ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
			executor.scheduleAtFixedRate(helloRunnable, 0, ElevatorControlCenter.getInstance().getClockTick(), TimeUnit.MILLISECONDS);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
