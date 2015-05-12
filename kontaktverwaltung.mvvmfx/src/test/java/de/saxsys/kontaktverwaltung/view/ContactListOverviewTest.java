package de.saxsys.kontaktverwaltung.view;

import javafx.scene.input.KeyCode;

import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasText;
import static org.testfx.matcher.control.ListViewMatchers.hasItems;
import static org.loadui.testfx.Assertions.assertNodeExists;
import de.saxsys.kontaktverwaltung.App;

public class ContactListOverviewTest extends FxRobot {

	@Before
	public void setupApp() throws Exception {
		FxToolkit.registerPrimaryStage();

		// App.class is your application class that extends
		// de.saxsys.mvvmfx.cdi.MvvmfxCdiApplication
		FxToolkit.setupApplication(App.class);
	}

	@Test
	public void testContactListOverview() {

		assertNodeExists("#btnExp");
		assertNodeExists("#btnAdd");
		verifyThat("#btnExp", hasText("Exportieren"));
		verifyThat("#btnAdd", hasText("Kontakt hinzufügen"));
		assertNodeExists("#listView");
		verifyThat("#listView", hasItems(2));
		// verifyThat("#listView", hasItem(hasText("Bruce Wayne")));
	}

	@Test
	public void testAddNewContact() {

		clickOn("#btnAdd");

		assertNodeExists("#btnOk");
		assertNodeExists("#btnCancel");
		verifyThat("#btnOk", hasText("OK"));
		verifyThat("#btnCancel", hasText("Abbrechen"));

		clickOn("#nameField");
		write("Wally");

		clickOn("#familyNameField");
		write("West");

		clickOn("#streetField");
		write("Spisak Street 16");

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

	@Test
	public void testShowDetailView() {

		doubleClickOn("#listView");

		verifyThat("#contactName", hasText("Bruce Wayne"));
		verifyThat("#streetLabel", hasText("Wayne Manor"));
		verifyThat("#zipcodePlaceLabel", hasText("666 Gotham City"));
		verifyThat("#countryLabel", hasText("Rhode Island"));
		verifyThat("#birthDateLabel", hasText("10.01.1975"));
		verifyThat("#telephoneLabel", hasText("017466466"));
		verifyThat("#emailLabel", hasText("brucewayne@wayneenterprises.com"));

		assertNodeExists("#btnEdit");
		assertNodeExists("#btnDelete");
		verifyThat("#btnEdit", hasText("Bearbeiten"));
		verifyThat("#btnDelete", hasText("Löschen"));
	}

	@Test
	public void testEditContact() {

		doubleClickOn("#listView");
		clickOn("#btnEdit");
		clickOn("#nameField");
		push(KeyCode.CONTROL, KeyCode.A);
		write("John");
		clickOn("#btnOk");

		verifyThat("#contactName", hasText("John Wayne"));
		verifyThat("#listView", hasItems(2));
	}

	@Test
	public void testEditContactCancel() {

		doubleClickOn("#listView");
		clickOn("#btnEdit");
		clickOn("#nameField");
		push(KeyCode.CONTROL, KeyCode.A);
		write("John");
		clickOn("#btnCancel");

		verifyThat("#contactName", hasText("Bruce Wayne"));
		verifyThat("#listView", hasItems(2));
	}

	@Test
	public void testDeleteContact() {

		doubleClickOn("#listView");
		clickOn("#btnDelete");
		clickOn();
		verifyThat("#listView", hasItems(1));
	}

}
