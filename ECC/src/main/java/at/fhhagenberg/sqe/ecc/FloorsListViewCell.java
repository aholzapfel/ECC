package at.fhhagenberg.sqe.ecc;

import java.net.URISyntaxException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FloorsListViewCell extends ListCell<Floor> {

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
            Label number = new Label(String.valueOf(floor.getNumber()));
            number.setFont(Font.font("FontAwesome", FontWeight.BOLD, 24));
            number.getStyleClass().add("cache-list-icon");
            root.getChildren().addAll(number);
 
            // DO NOT CREATE INSTANCES IN THIS METHOD, THIS IS BAD!
            Image img;
            if(floor.getUp()) {
            	img = new Image(Main.class.getClassLoader().getResource("Direction_Up_Pressed.png").toString());
            } else {
            	img = new Image(Main.class.getClassLoader().getResource("Direction_Up.png").toString());
            }
            ImageView up = new ImageView(img);
            up.setFitHeight(25);
            up.setFitWidth(25);
            root.getChildren().addAll(up);
            
            // DO NOT CREATE INSTANCES IN THIS METHOD, THIS IS BAD!
            if(floor.getUp()) {
            	img = new Image(Main.class.getClassLoader().getResource("Direction_Down_Pressed.png").toString());
            } else {
            	img = new Image(Main.class.getClassLoader().getResource("Direction_Down.png").toString());
            }
            ImageView down = new ImageView(img);
            down.setFitHeight(25);
            down.setFitWidth(25);
            root.getChildren().addAll(down);
 
            setGraphic(root);
		}
	}
}
