package sqecontroller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.rmi.Naming;
import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import sqelevator.IElevator;

/**
 * 
 * @author uebleis Elevator Szenario#1 : System with 10 floors and 1 Elevator
 *         This test were tested on the interface simulation of elevator-0.1.2
 *         purpose were some inconsistency during fast setTarget changing.
 */
public class TestElevatorFloorsController {

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
		assertTrue(elevatorSystem.getFloorNum() == 10);
	}

	@Test
	public void testSetTarget() throws RemoteException, InterruptedException {
		assertEquals(0, elevatorSystem.getTarget(0));
		elevatorSystem.setTarget(0, 3);
		assertEquals(3, elevatorSystem.getTarget(0));

		elevatorSystem.setTarget(0, 5);
		assertEquals(5, elevatorSystem.getTarget(0));

		elevatorSystem.setTarget(0, 9);
		assertEquals(9, elevatorSystem.getTarget(0));

		elevatorSystem.setTarget(0, 12);
		assertEquals(9, elevatorSystem.getTarget(0));
		elevatorSystem.setTarget(0, 0);
	}

	@Test
	public void testFloorButtons() throws RemoteException, InterruptedException {
		elevatorSystem.setTarget(0, 8);
		Thread.sleep(1000);
		elevatorSystem.setTarget(0, 0);
		Thread.sleep(1000);
		assertFalse(elevatorSystem.getFloorButtonDown(0));
	}

}
