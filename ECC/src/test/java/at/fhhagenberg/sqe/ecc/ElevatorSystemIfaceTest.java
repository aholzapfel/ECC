package at.fhhagenberg.sqe.ecc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import sqelevator.ElevatorSystemMock;
import sqelevator.IElevator;

public class ElevatorSystemIfaceTest {

	private IElevator elevatorSystem;
	private final int FLOORNUMBER = 4;
	private final int ELEVATORNUMBER = 2;

	@Before
	public void setUp() throws Exception {
		elevatorSystem = new ElevatorSystemMock(FLOORNUMBER, ELEVATORNUMBER);
	}

	@Test
	public void testGetTarget() throws RemoteException {
		assertEquals(1, elevatorSystem.getTarget(0));
	}

	@Test
	public void testSetTarget() throws RemoteException {
		elevatorSystem.setTarget(0, 3);
		assertEquals(3, elevatorSystem.getTarget(0));

		elevatorSystem.setTarget(0, 5);
		assertEquals(5, elevatorSystem.getTarget(0));
	}

	@Test
	public void testCommitedDirection() throws RemoteException {
		assertEquals(0, elevatorSystem.getCommittedDirection(0));
		assertEquals(0, elevatorSystem.getCommittedDirection(1));
	}

	@Test
	public void testAcceleration() throws RemoteException {
		assertEquals(5, elevatorSystem.getElevatorAccel(0));
		assertEquals(5, elevatorSystem.getElevatorAccel(1));
	}

	@Test
	public void testDoorStatus() throws RemoteException {
		assertEquals(1, elevatorSystem.getElevatorDoorStatus(0));
		assertEquals(1, elevatorSystem.getElevatorDoorStatus(1));
	}

	@Test
	public void testFloorNumber() throws RemoteException {
		assertEquals(FLOORNUMBER, elevatorSystem.getFloorNum());
	}

	@Test
	public void testSpeed() throws RemoteException {
		assertEquals(2, elevatorSystem.getElevatorSpeed(0));
		assertEquals(2, elevatorSystem.getElevatorSpeed(1));
	}

	@Test
	public void testWeight() throws RemoteException {
		assertEquals(100, elevatorSystem.getElevatorWeight(0));
		assertEquals(100, elevatorSystem.getElevatorWeight(1));
	}

	@Test
	public void testTime() throws RemoteException {
		assertNotEquals(elevatorSystem.getClockTick(), elevatorSystem.getClockTick());

	}

}
