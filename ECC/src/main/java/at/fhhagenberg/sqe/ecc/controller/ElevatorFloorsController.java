package at.fhhagenberg.sqe.ecc.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import at.fhhagenberg.sqe.ecc.Floor;
import at.fhhagenberg.sqe.ecc.cells.ElevatorFloorsListViewCell;
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
	
	protected List<Floor> floors = new ArrayList<>();
	protected ListProperty<Floor> listPropertyFloors = new SimpleListProperty<>();
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
		floors.add(new Floor());
		floors.add(new Floor());
		floors.add(new Floor());

		lvFloors.itemsProperty().bind(listPropertyFloors);
		lvFloors.setCellFactory(new Callback<ListView<Floor>, ListCell<Floor>>() {
			
			@Override
			public ListCell<Floor> call(ListView<Floor> param) {
				return new ElevatorFloorsListViewCell();
			}
		});
        
		listPropertyFloors.set(FXCollections.observableArrayList(floors));
	}
}
