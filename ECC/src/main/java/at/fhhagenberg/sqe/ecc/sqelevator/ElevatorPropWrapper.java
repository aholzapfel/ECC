package at.fhhagenberg.sqe.ecc.sqelevator;

import java.rmi.RemoteException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ElevatorPropWrapper {

	private IElevator elevator;
	
	public ElevatorPropWrapper(IElevator elevator) {
		this.elevator = elevator;
	}
	/*
	public StringProperty numProperty() throws RemoteException {
	    return new SimpleStringProperty("Elevator " + elevator.getElevatorNum());
	}
	
	public StringProperty payloadProperty() throws RemoteException {
	    return new SimpleStringProperty(elevator.getElevatorWeight(elevator.getElevatorNum()) + " lbs");
	}
	
	public StringProperty speedProperty() throws RemoteException {
	    return new SimpleStringProperty(elevator.getElevatorSpeed(elevator.getElevatorNum()) + " f/s");
	}
	
	public StringProperty doorProperty() throws Exception {
		
		String doorStatus;
		
		switch(elevator.getElevatorDoorStatus(elevator.getElevatorNum())) {
		
			case 1: 
				doorStatus = "Open";
				break;
			case 2:
				doorStatus = "Closed";
				break;
			default:
				// must not happen!
				throw new Exception("Invalid door status!");
		}
		
	    return new SimpleStringProperty(doorStatus);
	}*/
}
