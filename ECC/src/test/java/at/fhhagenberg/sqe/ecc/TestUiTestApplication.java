package at.fhhagenberg.sqe.ecc;

import static org.junit.Assert.assertEquals;

import java.rmi.RemoteException;

import org.junit.Before;
import org.junit.Test;

import sqelevator.ElevatorSystemMock;

public class TestUiTestApplication {
	private static UiTestApplication TestApplication = new UiTestApplication();

	private static String[] args;

	@Before
	public void setUp() throws Exception {
		args = null;
	}

	@Test
	public void test() throws RemoteException {
		System.out.println("hie");
		TestApplication.main(args);
		System.out.println("hi");
		ElevatorSystemMock testMock = new ElevatorSystemMock(4, 1);
		assertEquals(4, TestApplication.getElevatorSystemMock().getElevatorNum());

		System.exit(0);
	}

}
