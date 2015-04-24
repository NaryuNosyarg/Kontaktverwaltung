package de.saxsys.jfx.mvvmfx.helloworld;

import javafx.scene.Parent;
import javafx.scene.control.Button;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.loadui.testfx.GuiTest;
import org.loadui.testfx.categories.TestFX;

import static org.junit.Assert.assertFalse;
import static org.testfx.matcher.base.NodeMatchers.hasText;
import static org.testfx.api.FxAssert.verifyThat;

@Category(TestFX.class)
public class SimpleButtonTest extends GuiTest {

	@Override
	protected Parent getRootNode() {
		final Button btn = new Button();
		btn.setId("btn");
		btn.setText("Hello World");
		btn.setOnAction((actionEvent) -> btn.setText("was clicked"));
		return btn;
	}

	@Test
	public void shouldClickButton() {
		final Button button = find("#btn");
		//Button button = find("Hello World");
		click(button);
		verifyThat("#btn", hasText("was clicked"));
		// verifyThat(button, hasText("was clicked"));
		assertFalse(button.isDisabled());
	}
}