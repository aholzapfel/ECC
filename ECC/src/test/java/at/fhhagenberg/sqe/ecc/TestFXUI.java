package at.fhhagenberg.sqe.ecc;

import java.util.concurrent.TimeoutException;

import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.matcher.control.LabeledMatchers;

import javafx.scene.input.MouseButton;

public class TestFXUI {
	FxRobot robot;

	@Before
	public void setup() throws TimeoutException {
		FxToolkit.registerPrimaryStage();
		FxToolkit.setupApplication(UiTestApplication.class);

		robot = new FxRobot();
	}

	@Test
	public void testManualMode() {
		robot.clickOn("#tbModeManual");
		robot.clickOn("#tbModeManual");
		FxAssert.verifyThat("#tbModeManual", LabeledMatchers.hasText("Manual"));
		robot.clickOn("#btGoTo");
		FxAssert.verifyThat("#lbCurrentFloor", LabeledMatchers.hasText("1"));
	}

	@Test
	public void testManualModeClickUp() {
		robot.moveTo("#spGoTo");
		robot.moveBy(20, -10);
		robot.clickOn(MouseButton.PRIMARY);
		robot.clickOn("#btGoTo");
		FxAssert.verifyThat("#lbCurrentFloor", LabeledMatchers.hasText("2"));

		robot.moveTo("#spGoTo");
		robot.moveBy(20, -10);
		robot.clickOn(MouseButton.PRIMARY);
		robot.clickOn("#btGoTo");
		FxAssert.verifyThat("#lbCurrentFloor", LabeledMatchers.hasText("3"));

		robot.moveTo("#spGoTo");
		robot.moveBy(20, -10);
		robot.clickOn(MouseButton.PRIMARY);
		robot.clickOn("#btGoTo");
		FxAssert.verifyThat("#lbCurrentFloor", LabeledMatchers.hasText("4"));
	}

	@Test
	public void testManualModeClickDown() {
		robot.moveTo("#spGoTo");
		robot.moveBy(20, -10);
		robot.clickOn(MouseButton.PRIMARY);
		robot.clickOn(MouseButton.PRIMARY);
		robot.clickOn("#btGoTo");
		FxAssert.verifyThat("#lbCurrentFloor", LabeledMatchers.hasText("3"));

		robot.moveTo("#spGoTo");
		robot.moveBy(20, 10);
		robot.clickOn(MouseButton.PRIMARY);
		robot.clickOn("#btGoTo");
		FxAssert.verifyThat("#lbCurrentFloor", LabeledMatchers.hasText("2"));
	}

	@Test
	public void testAutomaticMode() {
		robot.clickOn("#tbModeAutomatic");
		FxAssert.verifyThat("#tbModeAutomatic", LabeledMatchers.hasText("Automatic"));

	}
}
