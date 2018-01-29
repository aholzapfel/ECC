package sqelevator;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ElevatorSystemMock implements IElevator {

	static List<ElevatorMock> elevators;

	private int numberOfFloors;
	private int numberOfElevators;
	private boolean DoorUp = true;

	public ElevatorSystemMock(int numberOfFloors, int numberOfElevators) {
		this.numberOfFloors = numberOfFloors;
		this.numberOfElevators = numberOfElevators;

		initElevatorMock();
	}

	private void initElevatorMock() {
		elevators = new ArrayList<ElevatorMock>();

		for (int i = 0; i < numberOfElevators; i++) {
			elevators.add(new ElevatorMock(1, 1, 100, 2, 1));
		}
	}

	@Override
	public int getCommittedDirection(int elevatorNumber) throws RemoteException {
		return 0;
	}

	@Override
	public int getElevatorAccel(int elevatorNumber) throws RemoteException {
		return 5;
	}

	@Override
	public boolean getElevatorButton(int elevatorNumber, int floor) throws RemoteException {
		return true;
	}

	@Override
	public int getElevatorDoorStatus(int elevatorNumber) throws RemoteException {
		return elevators.get(elevatorNumber).getDoorStatus();
	}

	@Override
	public int getElevatorFloor(int elevatorNumber) throws RemoteException {
		return elevators.get(elevatorNumber).getCurrentFloor();
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
		return elevators.get(elevatorNumber).getSpeed();
	}

	@Override
	public int getElevatorWeight(int elevatorNumber) throws RemoteException {
		return elevators.get(elevatorNumber).getWeight();
	}

	@Override
	public int getElevatorCapacity(int elevatorNumber) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getFloorButtonDown(int floor) throws RemoteException {
		if (getElevatorFloor(0) == floor)
			return false;
		return true;
	}

	@Override
	public boolean getFloorButtonUp(int floor) throws RemoteException {
		if (getElevatorFloor(0) == floor)
			DoorUp = false;
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
		return elevators.get(elevatorNumber).getTargetFloor();
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
		elevators.get(elevatorNumber).setTargetFloor(target);
		elevators.get(elevatorNumber).setCurrentFloor(target);
	}

	@Override
	public long getClockTick() throws RemoteException {
		return (long) (Math.random() * 10000);
	}
}
