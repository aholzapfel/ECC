package at.fhhagenberg.sqe.ecc;

import java.rmi.RemoteException;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Elevator {

	private static int elevatorsCounter = 1;
	

	private int targetFloor;
	private int currentFloor;
	private IntegerProperty number;
	
	public Elevator() {
		this.number = new SimpleIntegerProperty(elevatorsCounter++);
		this.setCurrentFloor(0);
	}
	
	public StringProperty numProperty() throws RemoteException {
	    return new SimpleStringProperty("" + number.get());
	}

	public int getTargetFloor() {
		return targetFloor;
	}

	public void setTargetFloor(int targetFloor) {
		this.targetFloor = targetFloor;		
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}	
	
	
	
}
