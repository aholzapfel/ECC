package at.fhhagenberg.sqe.ecc;

import java.util.concurrent.TimeoutException;

import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.matcher.control.LabeledMatchers;

public class TestFXUIAutomaticTest {
	FxRobot robot;

	@Before
	public void setup() throws TimeoutException {
		FxToolkit.registerPrimaryStage();
		FxToolkit.setupApplication(() -> new UiTestApplication(4, 1));

		robot = new FxRobot();
	}

	@Test
	public void testAutomaticMode() {

		robot.clickOn("#tbModeAutomatic");
		FxAssert.verifyThat("#tbModeAutomatic", LabeledMatchers.hasText("Automatic"));
		robot.clickOn("#tbModeManual");
	}

	@Test
	public void testLabels() {
		FxAssert.verifyThat("#lbPayload", LabeledMatchers.hasText("100"));
		FxAssert.verifyThat("#lbSpeed", LabeledMatchers.hasText("2"));
		FxAssert.verifyThat("#lbDoor", LabeledMatchers.hasText("Open"));
	}
}
