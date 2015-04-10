package de.saxsys.kontaktverwaltung.viewmodel;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.function.Function;

import org.junit.Before;
import org.junit.Test;

import de.saxsys.kontaktverwaltung.model.Address;
import de.saxsys.kontaktverwaltung.model.Contact;
import de.saxsys.kontaktverwaltung.model.Kontaktverwaltung;
import de.saxsys.kontaktverwaltung.model.Verwaltung;
import eu.lestard.easydi.EasyDI;

public class ContactEditDialogModelTest {

	ContactEditDialogModel viewModel;
	private EasyDI easyDI;
	private Contact alfred = null;
	private Contact martha = null;

	@Before
	public void setup() {
		
		easyDI = new EasyDI();
		easyDI.bindInterface(Verwaltung.class, Kontaktverwaltung.class);
		viewModel = easyDI.getInstance(ContactEditDialogModel.class);
		
		try {
			alfred = new Contact("3", "Alfred", "Pennyworth", LocalDate.of(
					1940, 07, 25));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Address address = new Address("1", "Wayne Manor", "Gotham City", "666",
				"Rhode Island", "Wayne Enterprise", "007");
		alfred.setLivingPlace(address);
	
		
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
		//given
		viewModel.setSelectedContact(martha);
		viewModel.nameProperty().set("Martha");
		viewModel.familyNameProperty().set("Wayne");
		viewModel.streetProperty().set("Crime Alley");
		viewModel.zipCodeProperty().set("05871");
		viewModel.placeProperty().set("Gotham City");
		viewModel.countryProperty().set("New Jersey");
		viewModel.birthDateProperty().set(LocalDate.of(1944, 10, 14));
		viewModel.emailProperty().set("marthawayne@wayneindustries.com");
		viewModel.telephoneProperty().set("015289765231");
		//when
		viewModel.ok();
		//then
		assertThat(
				viewModel.selectedContactProperty().getValue().nameProperty())
				.isEqualTo("Martha");
		assertThat(viewModel.isOkClicked()).isTrue();
	}

	@Test
	public void testContactEditDialogModel() {
		//given
		viewModel.setSelectedContact(alfred);
		assertThat(viewModel.nameProperty().get()).isEqualTo(
				"Alfred");
		//when
		viewModel.selectedContactProperty().getValue().setName("Jarvis");
		//then
		assertThat(viewModel.nameProperty().get()).isNotNull();
		assertThat(viewModel.nameProperty().get()).isEqualTo("Jarvis");
		//when
		viewModel.selectedContactProperty().getValue()
		.setBirthDate(LocalDate.of(1941, 8, 21));
		//then
		assertThat(viewModel.birthDateProperty().get()).isEqualTo("21.08.1941");
	}

}
