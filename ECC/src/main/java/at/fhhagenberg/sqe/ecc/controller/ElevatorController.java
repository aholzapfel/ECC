package at.fhhagenberg.sqe.ecc.controller;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.ResourceBundle;

import at.fhhagenberg.sqe.ecc.Floor;
import at.fhhagenberg.sqe.ecc.sqelevator.ElevatorControlCenter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class ElevatorController implements Initializable {
	
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
	
	private int spGoToSave;

	private static HashMap<Integer, Integer> spGoToSaveValues = new HashMap<Integer, Integer>();
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
		
		int elevatorNumber = Integer.parseInt(lbHeader.getText());
		
		
		 SpinnerValueFactory<Integer> floorsValueFactory;
		 
		try {
			floorsValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, ElevatorControlCenter.getInstance().getFloorNum());
			spGoTo.setValueFactory(floorsValueFactory);
			
			if(spGoToSaveValues.containsKey(elevatorNumber)){
				
				spGoTo.getValueFactory().setValue(spGoToSaveValues.get(elevatorNumber));
			}
			
			elevatorFloorsController.setElevatorNumber(elevatorNumber);
			
			btGoTo.setOnAction(new EventHandler<ActionEvent>() {
				
			    @Override public void handle(ActionEvent e) {
			    	try {
						ElevatorControlCenter.getInstance().setTarget(elevatorNumber, spGoTo.getValue().intValue());
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
			    }
			});

			spGoTo.valueProperty().addListener(new ChangeListener<Integer>() {

				@Override
				public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
					spGoToSaveValues.put(elevatorNumber, newValue);
				}
				
			});

			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
