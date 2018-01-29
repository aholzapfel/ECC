package at.fhhagenberg.sqe.ecc;

import javafx.application.Application;
import javafx.stage.Stage;
import sqelevator.ElevatorSystemMock;
import sqelevator.IElevator;

public class UiTestApplication extends Application {

	private int NUMBER_OF_FLOORS;
	private int NUMBER_OF_ELEVATORS;

	private IElevator elevatorSystem;

	public UiTestApplication(int floornumber, int elevatornumber) {
		NUMBER_OF_ELEVATORS = elevatornumber;
		NUMBER_OF_FLOORS = floornumber;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		elevatorSystem = new ElevatorSystemMock(NUMBER_OF_FLOORS, NUMBER_OF_ELEVATORS);
		final Main main = new Main(elevatorSystem);
		main.start(primaryStage);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	public IElevator getElevatorSystemMock() {
		return this.elevatorSystem;
	}
}
