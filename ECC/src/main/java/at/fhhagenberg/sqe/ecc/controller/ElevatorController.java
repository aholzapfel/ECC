package at.fhhagenberg.sqe.ecc.controller;

import java.rmi.RemoteException;

import at.fhhagenberg.sqe.ecc.Floor;
import at.fhhagenberg.sqe.ecc.sqelevator.IElevator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
	
	private int spGoToSave;

	
	public void init(IElevator elevatorSystem) {
		this.elevatorSystem = elevatorSystem;
		
		try {
			int elevatorNumber = Integer.parseInt(lbHeader.getText());
			
			elevatorFloorsController.init(elevatorSystem, elevatorNumber);
		
			SpinnerValueFactory<Integer> floorsValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, elevatorSystem.getFloorNum());
			spGoTo.setValueFactory(floorsValueFactory);
			
			btGoTo.setOnAction(new EventHandler<ActionEvent>() {
				
			    @Override public void handle(ActionEvent e) {
			    	try {
			    		elevatorSystem.setTarget(elevatorNumber, spGoTo.getValue().intValue());
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
			    }
			});

			spGoTo.valueProperty().addListener(new ChangeListener<Integer>() {

				@Override
				public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
					spGoToSave = newValue;
				}
				
			});
			
			spGoTo.getValueFactory().setValue(spGoToSave);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
