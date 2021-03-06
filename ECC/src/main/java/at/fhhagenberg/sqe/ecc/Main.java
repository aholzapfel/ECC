package at.fhhagenberg.sqe.ecc;

import java.net.URL;
import java.rmi.Naming;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import at.fhhagenberg.sqe.ecc.controllers.ElevatorControlCenterController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sqelevator.IElevator;

public class Main extends Application {

	private static final int WINDOW_HEIGHT = 800;
	private static final int WINDOW_WIDTH = 500;

	private IElevator elevatorSystem;
	private ElevatorControlCenterController controller;

	public static ScheduledExecutorService pollingExecutor;

	public Main() {
		connect();
	}

	public Main(IElevator elevatorSystem) {
		this.elevatorSystem = elevatorSystem;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			URL location = Main.class.getClassLoader().getResource("layouts\\elevator_control_center.fxml");

			FXMLLoader loader = new FXMLLoader(location);
			Pane root = loader.load();

			controller = loader.<ElevatorControlCenterController>getController();
			controller.init(elevatorSystem);

			Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("application.css").toExternalForm());

			primaryStage.setTitle("Elevator Control Center");
			primaryStage.setMinWidth(WINDOW_WIDTH);
			primaryStage.setMinHeight(WINDOW_HEIGHT);
			primaryStage.setScene(scene);
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					System.exit(0);
				}
			});
			primaryStage.show();

			startPolling();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void connect() {
		try {
			elevatorSystem = (IElevator) Naming.lookup("rmi://localhost/ElevatorSim");
		} catch (Exception e) {
			showConnectingFailedDialog();
		}
	}

	private void showConnectingFailedDialog() {
		Alert alert = new Alert(AlertType.CONFIRMATION);

		alert.setTitle("Connection failed!");
		alert.setHeaderText("The connection to the elevator simulator failed!");
		alert.setContentText("Do you want to retry?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			connect();
		} else {
			System.exit(0);
		}
	}

	public static void showConnectionLostDialog() {
		Alert alert = new Alert(AlertType.INFORMATION);

		alert.setTitle("Connection lost!");
		alert.setHeaderText("The connection to the elevator simulator got lost!");
		alert.setContentText("The application will be closed...");

		alert.showAndWait();

		System.exit(0);
	}

	private void startPolling() {
		Runnable pollingRunnable = new Runnable() {
			public void run() {
				Platform.runLater(controller.updateRunnable);
			}
		};

		pollingExecutor = Executors.newScheduledThreadPool(1);
		pollingExecutor.scheduleAtFixedRate(pollingRunnable, 0, 100, TimeUnit.MILLISECONDS);
	}
}
