package de.saxsys.kontaktverwaltung.viewmodel;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import de.saxsys.kontaktverwaltung.model.Kontaktverwaltung;
import de.saxsys.kontaktverwaltung.model.Verwaltung;
import eu.lestard.easydi.EasyDI;

public class ContactListOverviewModelTest {

	private ContactListOverviewModel viewModel;
	private EasyDI easyDI;

	@Before
	public void setup() {

		easyDI = new EasyDI();
		easyDI.bindInterface(Verwaltung.class, Kontaktverwaltung.class);
		viewModel = easyDI.getInstance(ContactListOverviewModel.class);

	}

	@Test
	public void testAdd() throws ParseException {
		assertThat(viewModel.listProperty()).hasSize(2);
		viewModel.add(t -> true);
		assertThat(viewModel.listProperty()).hasSize(3);
		viewModel.add(t -> false);
		assertThat(viewModel.listProperty()).hasSize(3);
	}

	@Test
	public void testListProperty() {
		assertThat(viewModel.listProperty()).isNotNull();
		assertThat(viewModel.listProperty().isEmpty()).isFalse();
		assertThat(viewModel.listProperty()).hasSize(2);
		assertThat(viewModel.listProperty().get(0).getName())
				.isEqualTo("Bruce");
	}

	@Test
	public void testSelectedContactProperty() {
		assertThat(viewModel.selectedContactProperty()).isNotNull();
	}
}
