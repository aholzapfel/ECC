package at.fhhagenberg.sqe.ecc.controller;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import at.fhhagenberg.sqe.ecc.Floor;
import at.fhhagenberg.sqe.ecc.cells.ElevatorFloorsListViewCell;
import at.fhhagenberg.sqe.ecc.sqelevator.ElevatorControlCenter;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ElevatorFloorsController implements Initializable {
	
	@FXML private ListView<Floor> lvFloors;
	
	private int elevatorNumber;
	
	protected List<Floor> floors = new ArrayList<>();
	protected ListProperty<Floor> listPropertyFloors = new SimpleListProperty<>();
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {

		try {
			for(int i = 0; i < ElevatorControlCenter.getInstance().getFloorNum(); i++) {
				floors.add(new Floor());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		lvFloors.itemsProperty().bind(listPropertyFloors);
		lvFloors.setCellFactory(new Callback<ListView<Floor>, ListCell<Floor>>() {
			
			@Override
			public ListCell<Floor> call(ListView<Floor> param) {
				return new ElevatorFloorsListViewCell(elevatorNumber);
			}
		});
        
		listPropertyFloors.set(FXCollections.observableArrayList(floors));
	}
	
	public void setElevatorNumber(int elevatorNumber) {
		this.elevatorNumber = elevatorNumber;
	}
}
