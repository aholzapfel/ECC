package at.fhhagenberg.sqe.ecc.controller;

import java.rmi.RemoteException;

import com.sun.javafx.css.converters.StringConverter;

import at.fhhagenberg.sqe.ecc.datastructure.Elevator;
import at.fhhagenberg.sqe.ecc.datastructure.Floor;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.converter.NumberStringConverter;
import sqelevator.IElevator;

public class ElevatorController {
	
	@FXML private ListView<Floor> elevatorFloors;
	@FXML private ElevatorFloorsController elevatorFloorsController;
	
	@FXML private Label lbHeader;
	
	@FXML private ToggleGroup tgMode;
	@FXML private ToggleButton tbModeAutomatic;
	@FXML private ToggleButton tbModeManual;
	
	@FXML private Spinner<Integer> spGoTo;
	@FXML private Button btGoTo;
	
	@FXML private Label lbPayload;
	@FXML private Label lbSpeed;
	@FXML private Label lbDoor;
	
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
			lbDoor.textProperty().bind(elevator.doorStatusProperty);
			tbModeAutomatic.selectedProperty().bindBidirectional(elevator.automaticProperty);
			
			// Go to is only enabled if manual mode is selected
			spGoTo.disableProperty().bind(tbModeManual.selectedProperty().not());
			btGoTo.disableProperty().bind(tbModeManual.selectedProperty().not());
		
			SpinnerValueFactory<Integer> floorsValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, elevatorSystem.getFloorNum());
			spGoTo.setValueFactory(floorsValueFactory);
			
			btGoTo.setOnAction(new EventHandler<ActionEvent>() {
				
			    @Override public void handle(ActionEvent e) {
			    	try {
			    		elevatorSystem.setTarget(elevator.getNumber(), spGoTo.getValue().intValue()-1);
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
