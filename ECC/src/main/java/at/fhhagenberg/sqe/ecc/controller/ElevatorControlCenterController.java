package at.fhhagenberg.sqe.ecc.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import at.fhhagenberg.sqe.ecc.Elevator;
import at.fhhagenberg.sqe.ecc.Floor;
import at.fhhagenberg.sqe.ecc.cells.ElevatorsListViewCell;
import at.fhhagenberg.sqe.ecc.sqelevator.IElevator;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ElevatorControlCenterController {
	
    @FXML private ListView<Floor> floors;
    @FXML private FloorsController floorsController;
    
	@FXML private ListView<Elevator> lvElevators;
	
	protected List<Elevator> elevators = new ArrayList<>();
	protected ListProperty<Elevator> listPropertyElevators = new SimpleListProperty<>();
	
	private IElevator elevatorSystem;
	
	public void init(IElevator elevatorSystem) {
		this.elevatorSystem = elevatorSystem;
		
		floorsController.init(elevatorSystem);
		
		if(elevatorSystem != null) {
			try {
				
				for(int i = 0; i < elevatorSystem.getElevatorNum(); i++) {
					elevators.add(new Elevator(elevatorSystem));
				}
			
				lvElevators.itemsProperty().bind(listPropertyElevators);
				lvElevators.setCellFactory(new Callback<ListView<Elevator>, ListCell<Elevator>>() {
					
					@Override
					public ListCell<Elevator> call(ListView<Elevator> param) {
						return new ElevatorsListViewCell(elevatorSystem);
					}
				});
		        
				listPropertyElevators.set(FXCollections.observableArrayList(elevators));
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		lvElevators.refresh();
	}
}
