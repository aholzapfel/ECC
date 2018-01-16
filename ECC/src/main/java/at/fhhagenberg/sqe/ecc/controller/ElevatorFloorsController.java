package at.fhhagenberg.sqe.ecc.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import at.fhhagenberg.sqe.ecc.cells.ElevatorFloorsListViewCell;
import at.fhhagenberg.sqe.ecc.datastructure.Floor;
import at.fhhagenberg.sqe.ecc.sqelevator.IElevator;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ElevatorFloorsController {
	
	@FXML private ListView<Floor> lvFloors;
	
	private IElevator elevatorSystem;
	private int elevatorNumber;
	
	protected List<Floor> floors = new ArrayList<>();
	protected ListProperty<Floor> listPropertyFloors = new SimpleListProperty<>();
	
	public void init(IElevator elevatorSystem, int elevatorNumber) {
		try {
			this.elevatorSystem = elevatorSystem;
			this.elevatorNumber = elevatorNumber;
		
			for(int i = 1; i <= elevatorSystem.getFloorNum(); i++) {
				floors.add(new Floor(i));
			}
	
			lvFloors.itemsProperty().bind(listPropertyFloors);
			lvFloors.setCellFactory(new Callback<ListView<Floor>, ListCell<Floor>>() {
				
				@Override
				public ListCell<Floor> call(ListView<Floor> param) {
					return new ElevatorFloorsListViewCell(elevatorSystem, elevatorNumber);
				}
			});
	        
			listPropertyFloors.set(FXCollections.observableArrayList(floors));
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void refreshFloors() {
		lvFloors.refresh();
	}
}
