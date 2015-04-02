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

public class ContactDetailViewModelTest {

	ContactDetailViewModel viewModel;
	private EasyDI easyDI;
	private Contact alfred = null;

	@Before
	public void setup() {
		
		easyDI = new EasyDI();
		easyDI.bindInterface(Verwaltung.class, Kontaktverwaltung.class);
		viewModel = easyDI.getInstance(ContactDetailViewModel.class);

		try {
			alfred = new Contact("3", "Alfred", "Pennyworth", LocalDate.of(
					1940, 07, 25));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Address address = new Address("1", "Wayne Manor", "Gotham City", "666",
				"Rhode Island", "Wayne Enterprise", "007");
		alfred.setLivingPlace(address);
	}

	@Test
	public void testContactDetailViewModel() {
		// given
		assertThat(viewModel.selectedContactProperty()).isNotNull();
		viewModel.setSelectedContact(alfred);
		// when
		viewModel.selectedContactProperty().getValue().getLivingPlace()
				.setStreet("Crime Alley");
		// then
		assertThat(viewModel.streetProperty().get()).isEqualTo("Crime Alley");
	}

	@Test
	public void testEdit() {
		assertThat(viewModel.selectedContactProperty()).isNotNull();

		viewModel.setSelectedContact(alfred);
		assertThat(viewModel.streetProperty().get()).isEqualTo("Wayne Manor");

		viewModel.setDetailShowFunction(new Function<Void, Void>() {

			@Override
			public Void apply(Void t) {
				return null;
			}
		});

		viewModel.edit(t -> false);
		assertThat(viewModel.streetProperty().get()).isEqualTo("Wayne Manor");

		viewModel.selectedContactProperty().getValue().getLivingPlace()
				.setStreet("Crime Alley");
		viewModel.edit(t -> true);
		assertThat(viewModel.streetProperty().get()).isEqualTo("Crime Alley");
	}

	@Test
	public void testRemove() {
		assertThat(viewModel.selectedContactProperty()).isNotNull();
		assertThat(viewModel.listProperty()).hasSize(2);
		viewModel.setSelectedContact(alfred);

		viewModel.setDetailCloseFunction(new Function<Void, Void>() {

			@Override
			public Void apply(Void t) {
				return null;
			}
		});

		viewModel.listProperty().add(alfred);
		assertThat(viewModel.listProperty()).hasSize(3);

		viewModel.remove();
		assertThat(viewModel.listProperty()).hasSize(2);
	}

}
