package de.saxsys.kontaktverwaltung.view;

import static org.junit.Assert.*;

import javax.inject.Provider;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import org.junit.Test;
import org.loadui.testfx.GuiTest;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasText;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.MvvmFX;
import eu.lestard.easydi.EasyDI;

public class ContactListOverviewTest extends GuiTest {
	

	@Override
	protected Parent getRootNode() {
		EasyDI context = new EasyDI();
		MvvmFX.setCustomDependencyInjector(context::getInstance);
		return FluentViewLoader.fxmlView(ContactListOverview.class).load()
				.getView();
	}
	
	  public void start(Stage stage) {
	        
	        stage.setScene(new Scene(getRootNode()));
			stage.show();
	    }

	@Test
	public void test() {
		// given
		//Button btnExp = find("#btnExp");
		//Button btnAdd = find("#btnAdd");

		verifyThat("#btnExp", hasText("Exportieren"));
		//verifyThat(btnAdd, hasText("Kontakt hinzufügen"));
		// assertThat(btnExp.getText()).isEqualTo("Exportieren");
	}
}
