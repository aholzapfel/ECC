package at.fhhagenberg.sqe.ecc.cells;

import java.io.IOException;
import java.net.URL;

import at.fhhagenberg.sqe.ecc.Elevator;
import at.fhhagenberg.sqe.ecc.Main;
import at.fhhagenberg.sqe.ecc.controller.ElevatorControlCenterController;
import at.fhhagenberg.sqe.ecc.controller.ElevatorController;
import at.fhhagenberg.sqe.ecc.sqelevator.IElevator;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Pane;

public class ElevatorsListViewCell extends ListCell<Elevator> {

	private IElevator elevatorSystem;
	private ElevatorController controller;
	
	public ElevatorsListViewCell(IElevator elevatorSystem) {
		this.elevatorSystem = elevatorSystem;
	}

	@Override
	public void updateItem(Elevator elevator, boolean empty) {
		super.updateItem(elevator, empty);

        if (elevator == null || empty) {
            setGraphic(null);
        } else {
            
        	try {
            	URL location = Main.class.getClassLoader().getResource("layouts\\elevator.fxml");
                FXMLLoader loader = new FXMLLoader(location); 
                
                //loader.getNamespace().put("elevator", elevator);
                Pane root = loader.load();
                
                controller =  loader.<ElevatorController>getController();
    			controller.init(elevatorSystem, elevator);
                
                setGraphic(root);
            } catch (IOException e) {
                e.printStackTrace();
                setGraphic(null);
            } 
        }
	}
	
	public void refreshFloors() {
		if(controller != null) {
			controller.refreshFloors();
		}
	}
}
