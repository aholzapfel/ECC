package at.fhhagenberg.sqe.ecc.views;

import java.rmi.RemoteException;

import at.fhhagenberg.sqe.ecc.Main;
import at.fhhagenberg.sqe.ecc.viewmodels.Floor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sqelevator.IElevator;

public class FloorsListViewCell extends ListCell<Floor> {
	
	private IElevator elevatorSystem;
	
	public FloorsListViewCell(IElevator elevatorSystem) {
		this.elevatorSystem = elevatorSystem;
	}
	
	@Override
	public void updateItem(Floor floor, boolean empty) {
		super.updateItem(floor, empty);

		if (empty) {
			setText(null);
			setGraphic(null);
		} 
		else {
			try {
				setText(null);
				 
	            // DO NOT CREATE INSTANCES IN THIS METHOD, THIS IS BAD!
	            HBox root = new HBox();
	            root.setPadding(new Insets(0, 10, 0, 10));
	            root.setAlignment(Pos.CENTER);
	 
	            // DO NOT CREATE INSTANCES IN THIS METHOD, THIS IS BAD!
	            Label number = new Label(String.valueOf(floor.getNumber()+1));
	            number.setFont(Font.font("FontAwesome", FontWeight.BOLD, 16));
	            number.getStyleClass().add("cache-list-icon");
	            root.getChildren().addAll(number);
	 
	            // DO NOT CREATE INSTANCES IN THIS METHOD, THIS IS BAD!
	            Image img;
	            if(elevatorSystem.getFloorButtonUp(floor.getNumber())) {
	            	img = new Image(Main.class.getClassLoader().getResource("images\\direction_up_pressed.png").toString());
	            } else {
	            	img = new Image(Main.class.getClassLoader().getResource("images\\direction_up.png").toString());
	            }
	            ImageView up = new ImageView(img);
	            up.setFitHeight(25);
	            up.setFitWidth(25);
	            root.getChildren().addAll(up);
	            
	            // DO NOT CREATE INSTANCES IN THIS METHOD, THIS IS BAD!
	            if(elevatorSystem.getFloorButtonDown(floor.getNumber())) {
	            	img = new Image(Main.class.getClassLoader().getResource("images\\direction_down_pressed.png").toString());
	            } else {
	            	img = new Image(Main.class.getClassLoader().getResource("images\\direction_down.png").toString());
	            }
	            ImageView down = new ImageView(img);
	            down.setFitHeight(25);
	            down.setFitWidth(25);
	            root.getChildren().addAll(down);
	 
	            setGraphic(root);
			} catch(RemoteException e) {
				e.printStackTrace();
			}
		}
	}
}
