package de.saxsys.kontaktverwaltung.viewmodel;

import java.text.ParseException;
import java.util.function.Function;

import javax.inject.Singleton;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import de.saxsys.kontaktverwaltung.model.Contact;
import de.saxsys.kontaktverwaltung.model.Verwaltung;
import de.saxsys.mvvmfx.MvvmFX;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.notifications.NotificationObserver;

@Singleton
public class ContactListOverviewModel implements ViewModel {

	private ObjectProperty<Contact> selectedContact = new SimpleObjectProperty<>();
	private ObservableList<Contact> contactList = FXCollections
			.observableArrayList();
	private Verwaltung verwaltung;

	public ContactListOverviewModel(Verwaltung verwaltung) {
		this.verwaltung = verwaltung;

		MvvmFX.getNotificationCenter().subscribe("update",
				new NotificationObserver() {
					@Override
					public void receivedNotification(String key,
							Object... payload) {
						updateContactList();
					}
				});
	}

	private void updateContactList() {
		contactList.clear();
		contactList.addAll(verwaltung.getContactList());
		System.out.println(contactList);
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
				MvvmFX.getNotificationCenter().publish("update");
			}
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
	}

	public ObservableList<Contact> listProperty() {
		MvvmFX.getNotificationCenter().publish("update");
		return contactList;
	}

	public ObjectProperty<Contact> selectedContactProperty() {
		return selectedContact;
	}

}
