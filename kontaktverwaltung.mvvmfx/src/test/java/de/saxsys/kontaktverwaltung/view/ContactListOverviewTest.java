package de.saxsys.kontaktverwaltung.view;

import static org.junit.Assert.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasText;
import static org.testfx.matcher.control.ListViewMatchers.hasItems;
import de.saxsys.kontaktverwaltung.App;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.MvvmFX;
import eu.lestard.easydi.EasyDI;

public class ContactListOverviewTest extends FxRobot {
	
/**
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
	    }*/
	
	@Before
    public void setupApp() throws Exception{
        FxToolkit.registerPrimaryStage();

        // App.class is your application class that extends de.saxsys.mvvmfx.cdi.MvvmfxCdiApplication
        FxToolkit.setupApplication(App.class); 
    }

	@Test
	public void test() {
		// given
//		//Button btnExp = find("#btnExp");
		//Button btnAdd = find("#btnAdd");

		verifyThat("#btnExp", hasText("Exportieren"));
		//verifyThat(btnAdd, hasText("Kontakt hinzufügen"));
		// assertThat(btnExp.getText()).isEqualTo("Exportieren");
	}
	
	@Test
	public void testAddNewContact() {
		
		verifyThat("#btnExp", hasText("Exportieren"));
		verifyThat("#btnAdd", hasText("Kontakt hinzufügen"));
		clickOn("#btnAdd");
		
		clickOn("#nameField");
		write("Wally");
		
		clickOn("#familyNameField");
		write("West");
		
		clickOn("#streetField");
		write("SpisakStreet 16");
		
		clickOn("#zipCodeField");
		write("14758");
		
		clickOn("#placeField");
		write("Palo Alto");
		
		clickOn("#countryField");
		write("California");
		
		clickOn("#birthDateField");
		write("11.11.1994");
		
		clickOn("#telephoneField");
		write("07158694231");
		
		clickOn("#emailField");
		write("likeablur@yahoo.com");
		
		clickOn("#btnOk");
		
		
		verifyThat("#listView", hasItems(3));
		
	}
}
