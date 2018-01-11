package at.fhhagenberg.sqe.ecc;

import java.rmi.RemoteException;

import at.fhhagenberg.sqe.ecc.sqelevator.IElevator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Elevator {

	private static int elevatorsCounter = 1;
	
	private IElevator elevatorSystem;
	
	private int number;
	
	public Elevator(IElevator elevatorSystem) {
		this.elevatorSystem = elevatorSystem;
		
		this.number = elevatorsCounter++;
	}
	
	public StringProperty numProperty() throws RemoteException {
	    return new SimpleStringProperty("" + number);
	}
	
	public StringProperty payloadProperty() throws RemoteException {
		int weight = elevatorSystem.getElevatorWeight(number);
	    return new SimpleStringProperty(weight + " lbs");
	}
	
	public StringProperty speedProperty() throws RemoteException {
		int speed = elevatorSystem.getElevatorSpeed(number);
	    return new SimpleStringProperty(speed + " m/s");
	}
	
	public StringProperty doorProperty() throws RemoteException {
		String doorStatus = "";
		
		switch(elevatorSystem.getElevatorDoorStatus(number)) {
			case 1:
				doorStatus = "Open";
				break;
			case 2:
				doorStatus = "Closed";
		}
	    return new SimpleStringProperty(doorStatus);
	}
}
