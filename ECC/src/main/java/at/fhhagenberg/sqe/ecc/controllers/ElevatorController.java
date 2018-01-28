package at.fhhagenberg.sqe.ecc.controllers;

import java.rmi.RemoteException;

import at.fhhagenberg.sqe.ecc.viewmodels.Elevator;
import at.fhhagenberg.sqe.ecc.viewmodels.Floor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.util.converter.NumberStringConverter;
import sqelevator.IElevator;

public class ElevatorController {

	@FXML
	private ListView<Floor> elevatorFloors;
	@FXML
	private ElevatorFloorsController elevatorFloorsController;

	@FXML
	private Label lbHeader;

	@FXML
	private ToggleGroup tgMode;
	@FXML
	private ToggleButton tbModeAutomatic;
	@FXML
	private ToggleButton tbModeManual;

	@FXML
	private Spinner<Integer> spGoTo;
	@FXML
	private Button btGoTo;

	@FXML
	private Label lbPayload;
	@FXML
	private Label lbSpeed;
	@FXML
	private Label lbDoor;
	@FXML
	private Label lbCurrentFloor;

	private IElevator elevatorSystem;
	private Elevator elevator;

	public void init(IElevator elevatorSystem, Elevator elevator) {
		try {
			this.elevatorSystem = elevatorSystem;
			this.elevator = elevator;

			elevatorFloorsController.init(elevatorSystem, elevator.getNumber());

			// bind elevator properties to GUI
			lbHeader.textProperty().bindBidirectional(elevator.numberProperty, new NumberStringConverter());
			lbPayload.textProperty().bindBidirectional(elevator.payloadProperty, new NumberStringConverter());
			lbSpeed.textProperty().bindBidirectional(elevator.speedProperty, new NumberStringConverter());
			lbCurrentFloor.textProperty().bindBidirectional(elevator.currentFloorProperty, new NumberStringConverter());
			lbDoor.textProperty().bind(elevator.doorStatusProperty);
			tbModeAutomatic.selectedProperty().bindBidirectional(elevator.automaticProperty);

			// Go to is only enabled if manual mode is selected
			spGoTo.disableProperty().bind(tbModeManual.selectedProperty().not());
			btGoTo.disableProperty().bind(tbModeManual.selectedProperty().not());

			SpinnerValueFactory<Integer> floorsValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,
					elevatorSystem.getFloorNum());
			spGoTo.setValueFactory(floorsValueFactory);

			btGoTo.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {
					try {
						elevatorSystem.setTarget(elevator.getNumber(), spGoTo.getValue().intValue() - 1);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
			});

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void refreshFloors() {
		elevatorFloorsController.refreshFloors();
	}
}
