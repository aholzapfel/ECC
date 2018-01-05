package at.fhhagenberg.sqe.ecc.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ElevatorController {
	
	@FXML private Label lbHeader;
	
	@FXML private ToggleGroup tgMode;
	@FXML private ToggleButton tbModeAutomatic;
	@FXML private ToggleButton tbModeManual;
	
	@FXML private Spinner<String> spGoTo;
	@FXML private Button btGoTo;
	
	@FXML private Label lbPayload;
	@FXML private Label lbSpeed;
	@FXML private Label lbDoor;
}
