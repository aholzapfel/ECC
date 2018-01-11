package at.fhhagenberg.sqe.ecc.cells;

import java.io.IOException;
import java.net.URL;

import at.fhhagenberg.sqe.ecc.Elevator;
import at.fhhagenberg.sqe.ecc.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class ElevatorsListViewCell extends ListCell<Elevator> {

	@Override
	public void updateItem(Elevator elevator, boolean empty) {
		super.updateItem(elevator, empty);

        if (elevator == null || empty) {
            setGraphic(null);
        } else {
            
        	try {
            	URL location = Main.class.getClassLoader().getResource("layouts\\elevator.fxml");
                FXMLLoader loader = new FXMLLoader(location); 
                loader.getNamespace().put("elevator", elevator);
                setGraphic(loader.load());
            } catch (IOException e) {
                e.printStackTrace();
                setGraphic(null);
            } 
        }
	}
}
