package de.saxsys.kontaktverwaltung.viewmodel;

import static org.assertj.core.api.Assertions.assertThat;


import java.text.ParseException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import de.saxsys.kontaktverwaltung.model.Contact;
import de.saxsys.kontaktverwaltung.model.Kontaktverwaltung;
import eu.lestard.easydi.EasyDI;

public class ContactListOverviewModelTest {

	//private ContactListOverviewModel viewModel;
	Kontaktverwaltung kv;
	private ObservableList<Contact> contactData;
	ContactListOverviewModel viewModel;
	private EasyDI easyDI;


	@Before
	public void setup() {
		//viewModel = new ContactListOverviewModel();
		kv = mock(Kontaktverwaltung.class);
		contactData = FXCollections.observableArrayList();
		
		easyDI = new EasyDI();

       viewModel = easyDI.getInstance(ContactListOverviewModel.class);
	}

	@Test
	public void testAdd() throws ParseException {
		viewModel.add(t -> true);
		assertThat(viewModel.listProperty()).hasSize(3);
	}

	@Test
	public void testListProperty() {
		assertThat(viewModel.listProperty()).isNotNull();
		assertThat(viewModel.listProperty().isEmpty()).isFalse();
		assertThat(viewModel.listProperty()).hasSize(2);
		assertThat(kv.getContactList()).hasSize(2);
	}

}
