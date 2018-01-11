package at.fhhagenberg.sqe.ecc.sqelevator;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem implements IElevator {
	
	static List<ElevatorMock> elevators;
	
	private static int numberOfFloors;
	private static int numberOfElevators;
	
	
	public ElevatorSystem(int numberOfFloors, int numberOfElevators) {
	   ElevatorSystem.numberOfFloors = numberOfFloors;
	   ElevatorSystem.numberOfElevators = numberOfElevators;
	    
	   initElevatorMock();
	}

	private void initElevatorMock() {
		elevators = new ArrayList<ElevatorMock>();
		
		for(int i = 0; i < numberOfElevators; i++) {
			elevators.add(new ElevatorMock(1,1,100,2,1));
		}
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
		return elevators.get(elevatorNumber-1).getDoorStatus();
	}
	
	@Override
	public int getElevatorFloor(int elevatorNumber) throws RemoteException {
		return elevators.get(elevatorNumber-1).getCurrentFloor();
	}
	
	@Override
	public int getElevatorNum() throws RemoteException {
		return numberOfElevators;
	}
	
	@Override
	public int getElevatorPosition(int elevatorNumber) throws RemoteException {
		return elevators.get(elevatorNumber).getCurrentFloor();
	}
	
	@Override
	public int getElevatorSpeed(int elevatorNumber) throws RemoteException {
		return elevators.get(elevatorNumber-1).getSpeed();
	}
	
	@Override
	public int getElevatorWeight(int elevatorNumber) throws RemoteException {
		return elevators.get(elevatorNumber-1).getWeight();
	}
	
	@Override
	public int getElevatorCapacity(int elevatorNumber) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean getFloorButtonDown(int floor) throws RemoteException {
		return false;
	}
	
	@Override
	public boolean getFloorButtonUp(int floor) throws RemoteException {
		return true;
	}
	
	@Override
	public int getFloorHeight() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int getFloorNum() throws RemoteException {
		// TODO Auto-generated method stub
		return numberOfFloors;
	}
	
	@Override
	public boolean getServicesFloors(int elevatorNumber, int floor) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public int getTarget(int elevatorNumber) throws RemoteException {
		return elevators.get(elevatorNumber-1).getTargetFloor();
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
		elevators.get(elevatorNumber-1).setTargetFloor(target-1);
		elevators.get(elevatorNumber-1).setCurrentFloor(target-1);
	}
	
	@Override
	public long getClockTick() throws RemoteException {
		return 2000;
	}
}