package at.fhhagenberg.sqe.ecc.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import at.fhhagenberg.sqe.ecc.cells.ElevatorsListViewCell;
import at.fhhagenberg.sqe.ecc.datastructure.Elevator;
import at.fhhagenberg.sqe.ecc.datastructure.Floor;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import sqelevator.IElevator;

public class ElevatorControlCenterController {
	
    @FXML private ListView<Floor> floors;
    @FXML private FloorsController floorsController;
    
	@FXML private ListView<Elevator> lvElevators;
	
	protected List<Elevator> elevators = new ArrayList<>();
	protected ListProperty<Elevator> listPropertyElevators = new SimpleListProperty<>();
	
	private List<ElevatorsListViewCell> cells = new ArrayList<>();
	
	private IElevator elevatorSystem;
	
	public void init(IElevator elevatorSystem) {
		try {
			this.elevatorSystem = elevatorSystem;
			
			floorsController.init(elevatorSystem);
			
			for(int i = 0; i <= elevatorSystem.getElevatorNum()-1; i++) {
				elevators.add(initElevatorFromSystem(new Elevator(i)));
			}
		
			lvElevators.itemsProperty().bind(listPropertyElevators);
			lvElevators.setCellFactory(new Callback<ListView<Elevator>, ListCell<Elevator>>() {
				
				@Override
				public ListCell<Elevator> call(ListView<Elevator> param) {
					ElevatorsListViewCell cell = new ElevatorsListViewCell(elevatorSystem);
					cells.add(cell);
					return cell;
				}
			});
	        
			listPropertyElevators.set(FXCollections.observableArrayList(elevators));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public Runnable updateRunnable = new Runnable() {
		public void run() {
			try {
				// Go throw all elevators and reload their data from elevator system
				// Refreshes GUI automatically through binding
				for(Elevator elevator : elevators) {
					initElevatorFromSystem(elevator);
				}
				
				// Refresh floor list
				floorsController.refreshFloors();
				
				// Refresh elevator floor lists
				for(ElevatorsListViewCell cell : cells) {
					cell.refreshFloors();
				}
				
			} catch (RemoteException e) {
				e.printStackTrace();
			}
	    }
	};
	
	private Elevator initElevatorFromSystem(Elevator elevator) throws RemoteException {
		int number = elevator.getNumber();
		
		int payload = elevatorSystem.getElevatorWeight(number);
		int speed = elevatorSystem.getElevatorSpeed(number);
		int doorStatus = elevatorSystem.getElevatorDoorStatus(number);
		
		elevator.setPayload(payload);
		elevator.setSpeed(speed);
		elevator.setDoorStatus(doorStatus);
		
		return elevator;
	}
}
