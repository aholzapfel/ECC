package at.fhhagenberg.sqe.ecc.controller;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import at.fhhagenberg.sqe.ecc.Elevator;
import at.fhhagenberg.sqe.ecc.Floor;
import at.fhhagenberg.sqe.ecc.cells.ElevatorsListViewCell;
import at.fhhagenberg.sqe.ecc.sqelevator.ElevatorControlCenter;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ElevatorControlCenterController implements Initializable{
	
    @FXML private ListView<Floor> floors;
    @FXML private FloorsController floorsController;
    
	@FXML private ListView<Elevator> lvElevators;
	
	protected List<Elevator> elevators = new ArrayList<>();
	protected ListProperty<Elevator> listPropertyElevators = new SimpleListProperty<>();
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
		initElevators();
	}
	
	private void initElevators() {
		
		try {
			for(int i = 0; i < ElevatorControlCenter.getInstance().getElevatorNum(); i++) {
				elevators.add(new Elevator());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		lvElevators.itemsProperty().bind(listPropertyElevators);
		lvElevators.setCellFactory(new Callback<ListView<Elevator>, ListCell<Elevator>>() {
			
			@Override
			public ListCell<Elevator> call(ListView<Elevator> param) {
				return new ElevatorsListViewCell();
			}
		});
        
		listPropertyElevators.set(FXCollections.observableArrayList(elevators));
		
	}
	
	public void update() {
		lvElevators.refresh();
	}
}
