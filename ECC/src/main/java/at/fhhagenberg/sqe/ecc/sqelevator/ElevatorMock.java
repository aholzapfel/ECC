package at.fhhagenberg.sqe.ecc.sqelevator;

import java.rmi.RemoteException;

public class ElevatorMock implements IElevator {
	
	private static int elevatorCounter = 1;
	
	private int num;
	
	public ElevatorMock() {
		num = elevatorCounter++;
	}
	
	@Override
	public int getCommittedDirection(int elevatorNumber) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getElevatorAccel(int elevatorNumber) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getElevatorButton(int elevatorNumber, int floor) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getElevatorDoorStatus(int elevatorNumber) throws RemoteException {
		// 1=open and 2=closed
		return elevatorNumber % 2 + 1;
	}

	@Override
	public int getElevatorFloor(int elevatorNumber) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getElevatorNum() throws RemoteException {
		return num;
	}

	@Override
	public int getElevatorPosition(int elevatorNumber) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getElevatorSpeed(int elevatorNumber) throws RemoteException {
		return elevatorNumber * 11;
	}

	@Override
	public int getElevatorWeight(int elevatorNumber) throws RemoteException {
		return elevatorNumber * 10;
	}

	@Override
	public int getElevatorCapacity(int elevatorNumber) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getFloorButtonDown(int floor) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getFloorButtonUp(int floor) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getFloorHeight() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFloorNum() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getServicesFloors(int elevatorNumber, int floor) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getTarget(int elevatorNumber) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCommittedDirection(int elevatorNumber, int direction) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setServicesFloors(int elevatorNumber, int floor, boolean service) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTarget(int elevatorNumber, int target) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getClockTick() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

}
