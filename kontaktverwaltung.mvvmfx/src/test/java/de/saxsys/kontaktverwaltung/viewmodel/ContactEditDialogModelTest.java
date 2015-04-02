package de.saxsys.kontaktverwaltung.viewmodel;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.Function;

import org.junit.Before;
import org.junit.Test;

import de.saxsys.kontaktverwaltung.model.Kontaktverwaltung;
import de.saxsys.kontaktverwaltung.model.Verwaltung;
import eu.lestard.easydi.EasyDI;

public class ContactEditDialogModelTest {

	ContactEditDialogModel viewModel;
	private EasyDI easyDI;

	@Before
	public void setup() {
		
		easyDI = new EasyDI();
		easyDI.bindInterface(Verwaltung.class, Kontaktverwaltung.class);
		viewModel = easyDI.getInstance(ContactEditDialogModel.class);
		
		viewModel.setDialogCloseFunction(new Function<Void, Void>() {

			@Override
			public Void apply(Void t) {
				return null;
			}
		});
	}

	@Test
	public void testIsOkClicked() {
		// given
		assertThat(viewModel.isOkClicked()).isFalse();

		// when
		viewModel.cancel();
		// then
		assertThat(viewModel.isOkClicked()).isFalse();

		// when
		viewModel.ok();

		// then
		assertThat(viewModel.isOkClicked()).isTrue();
	}

	@Test
	public void testCancel() {
		viewModel.cancel();
		assertThat(viewModel.isOkClicked()).isFalse();
	}

	@Test
	public void testOk() {
		viewModel.nameProperty().set("Martha");
		viewModel.familyNameProperty().set("Wayne");
		viewModel.streetProperty().set("Crime Alley");
		viewModel.zipCodeProperty().set("05871");
		viewModel.placeProperty().set("Gotham City");
		viewModel.countryProperty().set("New Jersey");
		//viewModel.birthDateProperty().set("14.10.1944");
		viewModel.emailProperty().set("marthawayne@wayneindustries.com");
		viewModel.telephoneProperty().set("015289765231");
		viewModel.ok();
		assertThat(
				viewModel.selectedContactProperty().getValue().nameProperty())
				.isEqualTo("Matha");
		assertThat(viewModel.isOkClicked()).isTrue();
	}

	@Test
	public void testContactEditDialogModel() {

		viewModel.selectedContactProperty().getValue().setName("John");
		assertThat(viewModel.nameProperty().get()).isNotNull();
		assertThat(viewModel.nameProperty().get()).isEqualTo("John");
	}

}
