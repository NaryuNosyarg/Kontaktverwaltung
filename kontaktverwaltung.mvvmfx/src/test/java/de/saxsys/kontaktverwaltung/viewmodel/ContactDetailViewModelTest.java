package de.saxsys.kontaktverwaltung.viewmodel;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import eu.lestard.easydi.EasyDI;

public class ContactDetailViewModelTest {

	ContactDetailViewModel viewModel;
	private EasyDI easyDI;

	@Before
	public void setup() {
		easyDI = new EasyDI();
		viewModel = easyDI.getInstance(ContactDetailViewModel.class);
	}

	@Test
	public void testContactDetailViewModel() {
		// given
		assertThat(viewModel.selectedContactProperty()).isNotNull();
		// when
		viewModel.selectedContactProperty().getValue().getLivingPlace()
				.setStreet("Crime Alley");
		// then
		assertThat(viewModel.streetProperty().get()).isEqualTo("Crime Alley");
	}

	@Test
	public void testEdit() {
		assertThat(viewModel.selectedContactProperty()).isNotNull();
		viewModel.edit(t -> true);

	}

	@Test
	public void testRemove() {
		viewModel.remove();
	}

}
