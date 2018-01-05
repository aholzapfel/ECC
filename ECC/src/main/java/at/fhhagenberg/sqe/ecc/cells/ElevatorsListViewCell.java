package at.fhhagenberg.sqe.ecc.cells;

import java.io.IOException;
import java.net.URL;

import at.fhhagenberg.sqe.ecc.Main;
import at.fhhagenberg.sqe.ecc.sqelevator.ElevatorPropWrapper;
import at.fhhagenberg.sqe.ecc.sqelevator.IElevator;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class ElevatorsListViewCell extends ListCell<IElevator> {

	@Override
	public void updateItem(IElevator elevator, boolean empty) {
		super.updateItem(elevator, empty);

        if (elevator == null || empty) {
            setGraphic(null);
        } else {
            try {
            	URL location = Main.class.getClassLoader().getResource("layouts\\elevator.fxml");
                FXMLLoader loader = new FXMLLoader(location);
                loader.getNamespace().put("elevator", new ElevatorPropWrapper(elevator));
                setGraphic(loader.load());
            } catch (IOException e) {
                e.printStackTrace();
                setGraphic(null);
            }
        }
	}
}
