package sqelevator;

public class ElevatorMock {

	private int targetFloor;
	private int currentFloor;
	private int weight;
	private int speed;
	private int doorStatus;

	public ElevatorMock(int targetFloor, int currentFloor, int weight, int speed, int doorStatus) {
		this.setTargetFloor(targetFloor);
		this.setCurrentFloor(currentFloor);
		this.setWeight(weight);
		this.setSpeed(speed);
		this.setDoorStatus(doorStatus);
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

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDoorStatus() {
		return doorStatus;
	}

	public void setDoorStatus(int doorStatus) {
		this.doorStatus = doorStatus;
	}

}
