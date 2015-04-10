package de.saxsys.kontaktverwaltung.viewmodel;

import java.time.LocalDate;
import java.util.function.Function;

import javax.inject.Singleton;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import de.saxsys.kontaktverwaltung.model.Contact;
import de.saxsys.mvvmfx.ViewModel;

@Singleton
public class ContactEditDialogModel implements ViewModel {

	private Function<Void, Void> dialogShowAndWaitFunction;
	private Function<Void, Void> dialogCloseFunction;
	private Function<String, Void> messageFunction;
	private boolean okClicked = false;

	private ObjectProperty<Contact> selectedContact = new SimpleObjectProperty<>();

	private StringProperty nameProperty = new SimpleStringProperty();
	private StringProperty familyNameProperty = new SimpleStringProperty();
	private StringProperty streetProperty = new SimpleStringProperty();
	private StringProperty zipCodeProperty = new SimpleStringProperty();
	private StringProperty placeProperty = new SimpleStringProperty();
	private StringProperty countryProperty = new SimpleStringProperty();
	private ObjectProperty<LocalDate> birthDateProperty = new SimpleObjectProperty<LocalDate>();
	private StringProperty emailProperty = new SimpleStringProperty();
	private StringProperty telephoneProperty = new SimpleStringProperty();
	

	public void setDialogShowAndWaitFunction(
			Function<Void, Void> dialogShowAndWaitFunction) {
		this.dialogShowAndWaitFunction = dialogShowAndWaitFunction;
	}

	public void setDialogCloseFunction(Function<Void, Void> dialogCloseFunction) {
		this.dialogCloseFunction = dialogCloseFunction;
	}

	public void show(Contact contact) {
		selectedContactProperty().set(contact);

		dialogShowAndWaitFunction.apply(null);
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	public void cancel() {
		okClicked = false;
		dialogCloseFunction.apply(null);
	}

	public void ok() {

		Contact contact = selectedContact.getValue();
		if (isInputValid()) {
			contact.setName(nameProperty.get());
			contact.setFamilyName(familyNameProperty.get());
			contact.getLivingPlace().setStreet(streetProperty.get());
			contact.getLivingPlace().setZipCode(zipCodeProperty.get());
			contact.getLivingPlace().setPlace(placeProperty.get());
			contact.getLivingPlace().setCountry(countryProperty.get());
			contact.setBirthDate(birthDateProperty.get());
			contact.getCommunicationInfo().setTelephones(
					telephoneProperty.get());
			contact.getCommunicationInfo().setEmails(emailProperty.get());

			okClicked = true;
			dialogCloseFunction.apply(null);
		}
	}

	public ContactEditDialogModel() {

		selectedContact.addListener(new ChangeListener<Contact>() {

			@Override
			public void changed(ObservableValue<? extends Contact> arg0,
					Contact oldContact, Contact newContact) {
				if (newContact != null) {
					Contact contact = selectedContact.getValue();

					nameProperty.set(contact.getName());
					familyNameProperty.set(contact.getFamilyName());
					streetProperty.set(contact.getLivingPlace().getStreet());
					zipCodeProperty.set(contact.getLivingPlace().getZipCode());
					placeProperty.set(contact.getLivingPlace().getPlace());
					countryProperty.set(contact.getLivingPlace().getCountry());
					birthDateProperty.set(contact.getBirthDate());
					emailProperty.set(contact.getCommunicationInfo()
							.getEmails());
					telephoneProperty.set(contact.getCommunicationInfo()
							.getTelephones());

					okClicked = false;
				}
			}
		});

	}

	private boolean isInputValid() {
		String errorMessage = "";

		if (nameProperty.get() == null || nameProperty.get().length() == 0) {
			errorMessage += "Geben Sie einen Vornamen ein!\n";
		}
		if (familyNameProperty.get() == null
				|| familyNameProperty.get().length() == 0) {
			errorMessage += "Geben Sie einen Nachnamen ein!\n";
		}
		if (streetProperty.get() == null || streetProperty.get().length() == 0) {
			errorMessage += "Geben Sie eine Straße ein!\n";
		}

		if (zipCodeProperty.get() == null
				|| zipCodeProperty.get().length() == 0) {
			errorMessage += "Geben Sie eine Postleitzahl ein!\n";
		}

		if (placeProperty.get() == null || placeProperty.get().length() == 0) {
			errorMessage += "Geben Sie einen Ort ein!\n";
		}
		if (countryProperty.get() == null
				|| countryProperty.get().length() == 0) {
			errorMessage += "Geben Sie ein Land ein!\n";
		}

		if (birthDateProperty.get() == null) {
			errorMessage += "Geben Sie ein Geburtsdatum ein!\n";
		}
		if (emailProperty.get() == null || emailProperty.get().length() == 0) {
			errorMessage += "Geben Sie eine E-Mail-Adresse ein!\n";
		}
		if (telephoneProperty.get() == null
				|| telephoneProperty.get().length() == 0) {
			errorMessage += "Geben Sie eine Telefonnummer ein!\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			messageFunction.apply(errorMessage);
			return false;
		}
	}
	
	public void setMessageFunction(Function<String, Void> messageFunction) {
		this.messageFunction = messageFunction;
	}

	public StringProperty nameProperty() {
		return nameProperty;
	}

	public StringProperty familyNameProperty() {
		return familyNameProperty;
	}

	public StringProperty streetProperty() {
		return streetProperty;
	}

	public StringProperty zipCodeProperty() {
		return zipCodeProperty;
	}

	public StringProperty placeProperty() {
		return placeProperty;
	}

	public StringProperty countryProperty() {
		return countryProperty;
	}

	public ObjectProperty<LocalDate> birthDateProperty() {
		return birthDateProperty;
	}

	public StringProperty emailProperty() {
		return emailProperty;
	}

	public StringProperty telephoneProperty() {
		return telephoneProperty;
	}

	public ObjectProperty<Contact> selectedContactProperty() {
		return selectedContact;
	}

	// for Test
	public void setSelectedContact(Contact contact) {
		this.selectedContact.set(contact);
	}

}
