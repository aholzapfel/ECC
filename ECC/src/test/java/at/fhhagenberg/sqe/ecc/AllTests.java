package at.fhhagenberg.sqe.ecc;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ElevatorSystemIfaceTest.class, TestFXUIAutomaticTest.class, TestFXUITest.class })
public class AllTests {

}
