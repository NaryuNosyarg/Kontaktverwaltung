package de.saxsys.kontaktverwaltung.viewmodel;

import java.text.ParseException;
import java.util.function.Function;

import javax.inject.Singleton;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import de.saxsys.kontaktverwaltung.model.Contact;
import de.saxsys.kontaktverwaltung.model.Verwaltung;
import de.saxsys.mvvmfx.ViewModel;

@Singleton
public class ContactListOverviewModel implements ViewModel {

	private ObjectProperty<Contact> selectedContact = new SimpleObjectProperty<>();
	private Verwaltung verwaltung;

	
	public ContactListOverviewModel(Verwaltung verwaltung) {
		this.verwaltung = verwaltung;
	}
	
	
	public void show(Function<Contact, Void> showFunction) {
		Contact selectedPerson = selectedContactProperty().get();
		if (selectedPerson != null) {
			showFunction.apply(selectedPerson);
		}
	}
	
	
	public void add(Function<Contact, Boolean> editFunction) {
		try {
			Contact tempPerson = new Contact(null, null, null, null);

			boolean okClicked = editFunction.apply(tempPerson);

			if (okClicked) {
				verwaltung.addContact(tempPerson);
			}
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
	}

	public ObservableList<Contact> listProperty() {
		return verwaltung.getContactList();
	}

	public ObjectProperty<Contact> selectedContactProperty() {
		return selectedContact;
	}

}
