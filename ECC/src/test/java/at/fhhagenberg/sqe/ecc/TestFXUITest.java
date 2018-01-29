package at.fhhagenberg.sqe.ecc;

import java.util.concurrent.TimeoutException;

import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.matcher.control.LabeledMatchers;

import javafx.scene.input.MouseButton;

public class TestFXUITest {
	FxRobot robot;

	@Before
	public void setup() throws TimeoutException {
		FxToolkit.registerPrimaryStage();
		FxToolkit.setupApplication(() -> new UiTestApplication(4, 1));

		robot = new FxRobot();
	}

	// @Test
	// public void testConnectAlert() throws TimeoutException {
	// FxToolkit.setupApplication(() -> new UiTestApplicationConnect(3, 1));
	// robot.clickOn(".button");
	// robot.point("OK");
	// System.out.println("hi");
	// robot.clickOn("OK");
	// robot.moveBy(50, 50);
	// Main.showConnectionLostDialog();
	// }

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
		robot.clickOn("#tbModeManual");
	}

	@Test
	public void testLabels() {
		FxAssert.verifyThat("#lbPayload", LabeledMatchers.hasText("100"));
		FxAssert.verifyThat("#lbSpeed", LabeledMatchers.hasText("2"));
		FxAssert.verifyThat("#lbDoor", LabeledMatchers.hasText("Open"));
	}
}
