package at.fhhagenberg.sqe.ecc.cells;

import at.fhhagenberg.sqe.ecc.Floor;
import at.fhhagenberg.sqe.ecc.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ElevatorFloorsListViewCell extends ListCell<Floor> {

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
            ImageView lamp = new ImageView(img);
            lamp.setFitHeight(25);
            lamp.setFitWidth(25);
            root.getChildren().addAll(lamp);
 
            setGraphic(root);
		}
	}
}
