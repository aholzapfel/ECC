package at.fhhagenberg.sqe.ecc.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import at.fhhagenberg.sqe.ecc.Floor;
import at.fhhagenberg.sqe.ecc.cells.ElevatorsListViewCell;
import at.fhhagenberg.sqe.ecc.sqelevator.ElevatorMock;
import at.fhhagenberg.sqe.ecc.sqelevator.IElevator;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ElevatorControlCenterController implements Initializable {
	
    @FXML private ListView<Floor> floors;
    @FXML private FloorsController floorsController;
    
	@FXML private ListView<IElevator> lvElevators;
	
	protected List<IElevator> elevators = new ArrayList<>();
	protected ListProperty<IElevator> listPropertyElevators = new SimpleListProperty<>();
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
		initElevators();
	}
	
	private void initElevators() {
		elevators.add(new ElevatorMock());
		elevators.add(new ElevatorMock());
		elevators.add(new ElevatorMock());
		elevators.add(new ElevatorMock());
		
		lvElevators.itemsProperty().bind(listPropertyElevators);
		lvElevators.setCellFactory(new Callback<ListView<IElevator>, ListCell<IElevator>>() {
			
			@Override
			public ListCell<IElevator> call(ListView<IElevator> param) {
				return new ElevatorsListViewCell();
			}
		});
        
		listPropertyElevators.set(FXCollections.observableArrayList(elevators));
	}
}
