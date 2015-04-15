package de.saxsys.kontaktverwaltung.viewmodel;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.function.Function;

import org.junit.Before;
import org.junit.Test;

import de.saxsys.kontaktverwaltung.model.Address;
import de.saxsys.kontaktverwaltung.model.CommunicationInfo;
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
		CommunicationInfo ci = new CommunicationInfo();
		ci.setTelephones("01748957642");
		ci.setEmails("alfred.pennyworth@wayneenterprises.com");
		alfred.setCommunicationInfo(ci);
		
		try {
			martha = new Contact(null, null, null, null);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	
		
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
		viewModel.setSelectedContact(alfred);
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
				viewModel.selectedContactProperty().getValue().nameProperty()
						.get()).isEqualTo("Martha");
		assertThat(
				viewModel.selectedContactProperty().getValue()
						.birthDateProperty().get()).isEqualTo(LocalDate.of(1944, 10, 14));
		assertThat(viewModel.isOkClicked()).isTrue();
		assertThat(
				viewModel.selectedContactProperty().getValue()
						.getCommunicationInfo().emailsProperty().get())
				.isEqualTo("marthawayne@wayneindustries.com");
	}

	@Test
	public void testContactEditDialogModel() {
		// given
		viewModel.setSelectedContact(alfred);
		assertThat(viewModel.nameProperty().get()).isEqualTo("Alfred");
		assertThat(viewModel.familyNameProperty().get())
				.isEqualTo("Pennyworth");
		assertThat(viewModel.streetProperty().get()).isEqualTo("Wayne Manor");
		assertThat(viewModel.zipCodeProperty().get()).isEqualTo("666");
		assertThat(viewModel.placeProperty().get()).isEqualTo("Gotham City");
		assertThat(viewModel.countryProperty().get()).isEqualTo("Rhode Island");
		assertThat(viewModel.birthDateProperty().get()).isEqualTo(
				LocalDate.of(1940, 07, 25));
		assertThat(viewModel.emailProperty().get()).isEqualTo(
				"alfred.pennyworth@wayneenterprises.com");
		assertThat(viewModel.telephoneProperty().get())
				.isEqualTo("01748957642");
		// when
		viewModel.nameProperty().set("Jarvis");
		//then
		assertThat(viewModel.nameProperty().get()).isNotNull();
		assertThat(viewModel.nameProperty().get()).isEqualTo("Jarvis");
	}

}
