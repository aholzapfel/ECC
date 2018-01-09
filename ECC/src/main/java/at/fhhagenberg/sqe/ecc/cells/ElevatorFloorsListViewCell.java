package at.fhhagenberg.sqe.ecc.cells;

import java.rmi.RemoteException;

import at.fhhagenberg.sqe.ecc.Floor;
import at.fhhagenberg.sqe.ecc.Main;
import at.fhhagenberg.sqe.ecc.sqelevator.ElevatorControlCenter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ElevatorFloorsListViewCell extends ListCell<Floor> {

	private int elevatorNumber;
	
	public ElevatorFloorsListViewCell(int elevatorNumber) {
		this.elevatorNumber = elevatorNumber;
	}
	
	@Override
	public void updateItem(Floor floor, boolean empty) {
		super.updateItem(floor, empty);
		
		if (empty) {
			setText(null);
			setGraphic(null);
		} else {		
			setText(null);
			 
			
            // DO NOT CREATE INSTANCES IN THIS METHOD, THIS IS BAD!
            HBox root = new HBox();
            root.setPadding(new Insets(0, 10, 0, 10));
            root.setAlignment(Pos.CENTER);
 
            // DO NOT CREATE INSTANCES IN THIS METHOD, THIS IS BAD!
            Image img;
            if(false) { // TODO
            	img = new Image(Main.class.getClassLoader().getResource("images\\lamp_on.png").toString());
            } else {
            	img = new Image(Main.class.getClassLoader().getResource("images\\lamp_off.png").toString());
            }
            
            Image floorStatus;
            try {
				if(ElevatorControlCenter.getInstance().getElevatorFloor(elevatorNumber) == getIndex()) {
					floorStatus = new Image(Main.class.getClassLoader().getResource("images\\elevator_arrived.png").toString());
				} else {
				    if(floor.getDown()) {
				    	floorStatus = new Image(Main.class.getClassLoader().getResource("images\\elevator_moving_down.png").toString());
				    } else if(floor.getUp()) {
				    	floorStatus = new Image(Main.class.getClassLoader().getResource("images\\elevator_moving_up.png").toString());
				    } else {
				    	floorStatus = new Image(Main.class.getClassLoader().getResource("images\\elevator_not_moving.png").toString());
				    }
				   
				}
				
	            ImageView elevatorFloorStatus = new ImageView(floorStatus);
	            elevatorFloorStatus.setFitHeight(25);
	            elevatorFloorStatus.setFitWidth(25);
	            root.getChildren().addAll(elevatorFloorStatus);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            


            
            ImageView lamp = new ImageView(img);
            lamp.setFitHeight(25);
            lamp.setFitWidth(25);
            root.getChildren().addAll(lamp);
 
            
            setGraphic(root);
		}
	}

	public int getElevatorNumber() {
		return elevatorNumber;
	}

	public void setElevatorNumber(int elevatorNumber) {
		this.elevatorNumber = elevatorNumber;
	}
}
