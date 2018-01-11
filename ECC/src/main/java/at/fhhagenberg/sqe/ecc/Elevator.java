package at.fhhagenberg.sqe.ecc;

import java.rmi.RemoteException;

import at.fhhagenberg.sqe.ecc.sqelevator.ElevatorControlCenter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Elevator {

	private static int elevatorsCounter = 1;
	
	private int number;
	
	public Elevator() {
		this.number = elevatorsCounter++;
	}
	
	public StringProperty numProperty() throws RemoteException {
	    return new SimpleStringProperty("" + number);
	}
	
	public StringProperty payloadProperty() throws RemoteException {
		int weight = ElevatorControlCenter.getInstance().getElevatorWeight(number);
	    return new SimpleStringProperty(weight + " lbs");
	}
	
	public StringProperty speedProperty() throws RemoteException {
		int speed = ElevatorControlCenter.getInstance().getElevatorSpeed(number);
	    return new SimpleStringProperty(speed + " m/s");
	}
	
	public StringProperty doorProperty() throws RemoteException {
		String doorStatus = "";
		
		switch(ElevatorControlCenter.getInstance().getElevatorDoorStatus(number)) {
			case 1:
				doorStatus = "Open";
				break;
			case 2:
				doorStatus = "Closed";
		}
	    return new SimpleStringProperty(doorStatus);
	}
}
