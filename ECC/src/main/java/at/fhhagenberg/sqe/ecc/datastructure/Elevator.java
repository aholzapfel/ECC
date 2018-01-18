package at.fhhagenberg.sqe.ecc.datastructure;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Elevator {

	public IntegerProperty numberProperty;
	public IntegerProperty payloadProperty;
	public IntegerProperty speedProperty;
	public StringProperty doorStatusProperty;
	
	public Elevator(int number) {		
		this.numberProperty = new SimpleIntegerProperty(number+1);
		this.payloadProperty = new SimpleIntegerProperty(0);
		this.speedProperty = new SimpleIntegerProperty(0);
		this.doorStatusProperty = new SimpleStringProperty("Closed");
	}
	
	public int getNumber() {
		return numberProperty.get();
	}
	
	public void setPayload(int payload) {
		payloadProperty.set(payload);
	}
	
	public void setSpeed(int speed) {
		speedProperty.set(speed);
	}
	
	public void setDoorStatus(int doorStatus) {
		String doorStatusStr = "";
		
		switch(doorStatus) {
			case 1:
				doorStatusStr = "Open";
				break;
			case 2:
				doorStatusStr = "Closed";
		}
		
		doorStatusProperty.set(doorStatusStr);
	}
}
