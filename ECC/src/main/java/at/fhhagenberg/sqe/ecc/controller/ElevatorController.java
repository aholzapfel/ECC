package at.fhhagenberg.sqe.ecc.controller;

import java.net.URL;
import java.rmi.RemoteException;
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

	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
		
		int elevatorNumber = Integer.parseInt(lbHeader.getText());
		
		
		 SpinnerValueFactory<Integer> floorsValueFactory;
		 
		try {
			floorsValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, ElevatorControlCenter.getInstance().getFloorNum());
			spGoTo.setValueFactory(floorsValueFactory);
			
			lbPayload.setText( elevatorNumber*ElevatorControlCenter.getInstance().getElevatorWeight(elevatorNumber) + " lbs");
			lbSpeed.setText( elevatorNumber*ElevatorControlCenter.getInstance().getElevatorSpeed(elevatorNumber) + " m/s");
			lbDoor.setText( ElevatorControlCenter.getInstance().getElevatorDoorStatus(elevatorNumber) + " status");
			elevatorFloorsController.setElevatorNumber(elevatorNumber);
			btGoTo.setOnAction(new EventHandler<ActionEvent>() {
				
			    @Override public void handle(ActionEvent e) {
			    	try {
						ElevatorControlCenter.getInstance().setTarget(elevatorNumber-1, spGoTo.getValue().intValue()-1);
						System.out.println("clicked on " + (elevatorNumber-1));
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    }
			});

			spGoTo.valueProperty().addListener(new ChangeListener<Integer>() {

				@Override
				public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
					System.out.println("new val" + newValue);
					spGoToSave = newValue;
				}
				
			});
			
			spGoTo.getValueFactory().setValue(spGoToSave);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void testController() {
		System.out.println("elevator works");
	}
}
