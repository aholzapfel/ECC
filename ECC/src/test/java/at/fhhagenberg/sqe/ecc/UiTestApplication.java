package at.fhhagenberg.sqe.ecc;

import javafx.application.Application;
import javafx.stage.Stage;
import sqelevator.ElevatorSystemMock;
import sqelevator.IElevator;

public class UiTestApplication extends Application {

	private static int NUMBER_OF_FLOORS = 4;
	private static int NUMBER_OF_ELEVATORS = 1;

	private static IElevator elevatorSystem;

	@Override
	public void start(Stage primaryStage) throws Exception {
		elevatorSystem = new ElevatorSystemMock(NUMBER_OF_FLOORS, NUMBER_OF_ELEVATORS);
		final Main main = new Main(elevatorSystem);
		main.start(primaryStage);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	public static IElevator getElevatorSystemMock() {
		return elevatorSystem;
	}
}
