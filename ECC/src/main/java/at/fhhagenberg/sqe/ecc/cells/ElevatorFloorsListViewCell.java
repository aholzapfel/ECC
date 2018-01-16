package at.fhhagenberg.sqe.ecc.cells;

import java.rmi.RemoteException;

import at.fhhagenberg.sqe.ecc.Main;
import at.fhhagenberg.sqe.ecc.datastructure.Floor;
import at.fhhagenberg.sqe.ecc.sqelevator.ElevatorSystemMock;
import at.fhhagenberg.sqe.ecc.sqelevator.IElevator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ElevatorFloorsListViewCell extends ListCell<Floor> {

	private IElevator elevatorSystem;
	private int elevatorNumber;
	
	public ElevatorFloorsListViewCell(IElevator elevatorSystem, int elevatorNumber) {
		this.elevatorSystem = elevatorSystem;
		this.elevatorNumber = elevatorNumber;
	}
	
	@Override
	public void updateItem(Floor floor, boolean empty) {
		super.updateItem(floor, empty);
		
		if (empty) {
			setText(null);
			setGraphic(null);
		} else {	
			 try {
				setText(null);
				 
	            // DO NOT CREATE INSTANCES IN THIS METHOD, THIS IS BAD!
	            HBox root = new HBox();
	            root.setPadding(new Insets(0, 10, 0, 10));
	            root.setAlignment(Pos.CENTER);
	 
	            // DO NOT CREATE INSTANCES IN THIS METHOD, THIS IS BAD!
	            Image img;
	            if(elevatorSystem.getElevatorButton(elevatorNumber, floor.getNumber())) {
	            	img = new Image(Main.class.getClassLoader().getResource("images\\lamp_on.png").toString());
	            } else {
	            	img = new Image(Main.class.getClassLoader().getResource("images\\lamp_off.png").toString());
	            }
	            
	            Image floorStatus = null;
				if(elevatorSystem.getElevatorFloor(elevatorNumber) == floor.getNumber()) {
					if(elevatorSystem.getTarget(elevatorNumber) == floor.getNumber()) {
						floorStatus = new Image(Main.class.getClassLoader().getResource("images\\elevator_arrived.png").toString());
					}
					else if(elevatorSystem != null && elevatorSystem.getElevatorFloor(elevatorNumber) > floor.getNumber()) {
					    	floorStatus = new Image(Main.class.getClassLoader().getResource("images\\elevator_moving_down.png").toString());
					}
				    else if(elevatorSystem != null && elevatorSystem.getElevatorFloor(elevatorNumber) < floor.getNumber()) {
				    	floorStatus = new Image(Main.class.getClassLoader().getResource("images\\elevator_moving_up.png").toString());
				    }
				} else {
			    	floorStatus = new Image(Main.class.getClassLoader().getResource("images\\elevator_not_moving.png").toString());
			    }
				
	            ImageView elevatorFloorStatus = new ImageView(floorStatus);
	            elevatorFloorStatus.setFitHeight(25);
	            elevatorFloorStatus.setFitWidth(25);
	            root.getChildren().addAll(elevatorFloorStatus);
	            
	            ImageView lamp = new ImageView(img);
	            lamp.setFitHeight(25);
	            lamp.setFitWidth(25);
	            root.getChildren().addAll(lamp);
	 
	            setGraphic(root);
	            
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public int getElevatorNumber() {
		return elevatorNumber;
	}

	public void setElevatorNumber(int elevatorNumber) {
		this.elevatorNumber = elevatorNumber;
	}
}
