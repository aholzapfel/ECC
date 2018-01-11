package at.fhhagenberg.sqe.ecc;

import java.rmi.RemoteException;

import at.fhhagenberg.sqe.ecc.sqelevator.ElevatorControlCenter;

public class Floor {

	private static int floorsCounter = 1;
	
	private int number;
	
	public Floor() {
		this.number = floorsCounter++;
	}
	
	public int getNumber() {
		return number;
	}
	
	public boolean getUp() throws RemoteException {
		return ElevatorControlCenter.getInstance().getFloorButtonUp(number);
	}
	
	public boolean getDown() throws RemoteException {
		return ElevatorControlCenter.getInstance().getFloorButtonDown(number);
	}
}
