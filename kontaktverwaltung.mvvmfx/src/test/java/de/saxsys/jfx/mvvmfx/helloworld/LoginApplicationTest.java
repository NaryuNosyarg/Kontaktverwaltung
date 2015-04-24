package de.saxsys.jfx.mvvmfx.helloworld;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;

import static org.loadui.testfx.controls.Commons.hasText;
import static org.loadui.testfx.Assertions.assertNodeExists;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;

public class LoginApplicationTest extends FxRobot {

	@BeforeClass
	public static void setupSpec() throws Exception {
		FxToolkit.registerPrimaryStage();
		FxToolkit.setupStage(stage -> stage.show());
	}

	@Before
	public void setup() throws Exception {
		FxToolkit.setupApplication(LoginApplication.class);
	}

	@Test
	public void should_login_with_correct_credentials() {
		// when:
		clickOn("#usernameField").write("scott");
		clickOn("#passwordField").write("tiger");
		clickOn("#loginButton");

		// then:
		verifyThat("#messageLabel", hasText("Not logged in."));
		assertNodeExists(hasText("Username"));
		assertNodeExists("#logoutButton");
		 verifyThat("#logoutButton", isDisabled());
	}
}
