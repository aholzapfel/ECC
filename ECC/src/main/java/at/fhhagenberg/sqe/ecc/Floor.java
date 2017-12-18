package at.fhhagenberg.sqe.ecc;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Floor {

	private static int floorsCounter = 1;
	
	private IntegerProperty number;
	private BooleanProperty up;
	private BooleanProperty down;
	
	public Floor() {
		this.number = new SimpleIntegerProperty(floorsCounter++);
		this.up = new SimpleBooleanProperty(false);
		this.down = new SimpleBooleanProperty(false);
	}
	
	public int getNumber() {
		return number.get();
	}
	
	public boolean getUp() {
		return up.get();
	}
	
	public void setUp(boolean up) {
		this.up.set(up);
	}
	
	public boolean getDown() {
		return down.get();
	}
	
	public void setDown(boolean down) {
		this.down.set(down);
	}	
}
