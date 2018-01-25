package sqecontroller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.rmi.Naming;
import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import sqelevator.IElevator;

//Elevator Szenario#2 : System with 15 floors and 2 Elevator
public class TestElevatorFloorsControllerTwoElevator {

	private IElevator elevatorSystem;
	private int elevatorNumber;

	@Before
	public void setUp() throws Exception {
		try {
			elevatorSystem = (IElevator) Naming.lookup("rmi://localhost/ElevatorSim");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testNumberOfFloors() throws RemoteException {
		assertTrue(elevatorSystem.getFloorNum() == 15);
	}

	@Test
	public void testSetTarget1st() throws RemoteException, InterruptedException {
		assertEquals(0, elevatorSystem.getTarget(0));
		elevatorSystem.setTarget(0, 3);
		assertEquals(3, elevatorSystem.getTarget(0));

		elevatorSystem.setTarget(0, 5);
		assertEquals(5, elevatorSystem.getTarget(0));

		elevatorSystem.setTarget(0, 9);
		assertEquals(9, elevatorSystem.getTarget(0));

		elevatorSystem.setTarget(0, 12);
		assertEquals(12, elevatorSystem.getTarget(0));
		elevatorSystem.setTarget(0, 0);
	}

	@Test
	public void testSetTarget2nd() throws RemoteException, InterruptedException {
		assertEquals(0, elevatorSystem.getTarget(1));
		elevatorSystem.setTarget(1, 3);
		assertEquals(3, elevatorSystem.getTarget(1));

		elevatorSystem.setTarget(1, 5);
		assertEquals(5, elevatorSystem.getTarget(1));

		elevatorSystem.setTarget(1, 9);
		assertEquals(9, elevatorSystem.getTarget(1));

		elevatorSystem.setTarget(1, 12);
		assertEquals(12, elevatorSystem.getTarget(1));
		elevatorSystem.setTarget(1, 0);
	}

	@Test
	public void testFloorButtons() throws RemoteException, InterruptedException {
		elevatorSystem.setTarget(1, 8);
		Thread.sleep(5000);
		elevatorSystem.setTarget(1, 0);
		Thread.sleep(5000);
		assertFalse(elevatorSystem.getFloorButtonDown(0));

		elevatorSystem.setTarget(1, 2);

		Thread.sleep(5000);
		assertFalse(elevatorSystem.getFloorButtonDown(2));
		Thread.sleep(1000);
		elevatorSystem.setTarget(1, 0);
	}

}
