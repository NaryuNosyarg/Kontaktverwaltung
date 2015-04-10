package de.saxsys.kontaktverwaltung.view;

import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasText;
import javafx.scene.Parent;

import org.junit.Test;
import org.loadui.testfx.GuiTest;

import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.MvvmFX;
import eu.lestard.easydi.EasyDI;

public class ContactDetailViewTest extends GuiTest{

	@Test
	public void test() {
		verifyThat("#btnEdit", hasText("Bearbeiten"));
	}

	@Override
	protected Parent getRootNode() {
		EasyDI context = new EasyDI();
		MvvmFX.setCustomDependencyInjector(context::getInstance);
		return FluentViewLoader.fxmlView(ContactDetailView.class).load()
				.getView();
	}

}
