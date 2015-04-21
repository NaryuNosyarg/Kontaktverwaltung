package de.saxsys.kontaktverwaltung.viewmodel;

import java.time.LocalDate;
import java.util.function.Function;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Control;

import javax.inject.Singleton;

import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import de.saxsys.kontaktverwaltung.model.Contact;
import de.saxsys.kontaktverwaltung.util.BirthDateValidator;
import de.saxsys.kontaktverwaltung.util.EmailValidator;
import de.saxsys.kontaktverwaltung.util.TelephoneValidator;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.mapping.ModelWrapper;

@Singleton
public class ContactEditDialogModel implements ViewModel {

	private ModelWrapper<Contact> contactWrapper = new ModelWrapper<>();
	ValidationSupport validationSupport = new ValidationSupport();

	private ReadOnlyBooleanWrapper valid = new ReadOnlyBooleanWrapper();

	private Function<Void, Void> dialogShowAndWaitFunction;
	private Function<Void, Void> dialogCloseFunction;
	private Function<String, Void> messageFunction;
	private String errorMessage;

	private boolean okClicked = false;

	private ObjectProperty<Contact> selectedContact = new SimpleObjectProperty<>();

	
	public void setDialogShowAndWaitFunction(
			Function<Void, Void> dialogShowAndWaitFunction) {
		this.dialogShowAndWaitFunction = dialogShowAndWaitFunction;
	}

	public void setDialogCloseFunction(Function<Void, Void> dialogCloseFunction) {
		this.dialogCloseFunction = dialogCloseFunction;
	}

	public void setMessageFunction(Function<String, Void> messageFunction) {
		this.messageFunction = messageFunction;
	}

	public void show(Contact contact) {
		selectedContactProperty().set(contact);
		this.contactWrapper.set(selectedContact.getValue());
		contactWrapper.reload();

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
		if (isInputValid()) {
			contactWrapper.commit();

			okClicked = true;
			dialogCloseFunction.apply(null);
		}
	}

	public ContactEditDialogModel() {
		valid.bind(validationSupport.invalidProperty().isNull()
				.or(validationSupport.invalidProperty().isEqualTo(false)));
		

		selectedContact.addListener(new ChangeListener<Contact>() {

			@Override
			public void changed(ObservableValue<? extends Contact> arg0,
					Contact oldContact, Contact newContact) {
				if (newContact != null) {
					contactWrapper.reload();
				}
			}
		});
	}

	public void initValidationForName(Control input) {
		validationSupport.registerValidator(input, true, Validator.createEmptyValidator("Geben Sie einen Vornamen ein!"));
	}
	
	public void initValidationForFamilyName(Control input) {
		validationSupport.registerValidator(input, true, Validator.createEmptyValidator("Geben Sie einen Nachnamen ein!"));
	}
	
	public void initValidationForStreet(Control input) {
		validationSupport.registerValidator(input, false, Validator.createEmptyValidator("Geben Sie eine Straße ein!\n"));
	}
	
	public void initValidationForZipCode(Control input) {
		validationSupport.registerValidator(input, false, Validator.createEmptyValidator("Geben Sie eine Postleitzahl ein!\n"));
	}
	
	public void initValidationForPlace(Control input) {
		validationSupport.registerValidator(input, false, Validator.createEmptyValidator("Geben Sie einen Ort ein!\n"));
	}
	
	public void initValidationForCountry(Control input) {
		validationSupport.registerValidator(input, false, Validator.createEmptyValidator("Geben Sie ein Land ein!\n"));
	}
	
	public void initValidationForBirthDate(Control input) {
		validationSupport.registerValidator(input, true, new BirthDateValidator());
	}
	
	public void initValidationForEmail(Control input) {
		validationSupport.registerValidator(input, false, new EmailValidator());
	}
	
	public void initValidationForTelephone(Control input) {
		validationSupport.registerValidator(input, false, new TelephoneValidator());
	}

	public ReadOnlyBooleanProperty validProperty() {
		return valid.getReadOnlyProperty();
	}
	
	private String setErrorMessage(){
		return errorMessage = validationSupport.getValidationResult().getMessages().toString();
	}

	private boolean isInputValid() {

		if (validProperty().get()) {
			return true;
		} else {
			messageFunction.apply(setErrorMessage());
			return false;
		}
	}

	public Property<String> nameProperty() {
		return contactWrapper.field("name", Contact::nameProperty);
	}
	
	public Property<String> familyNameProperty() {
		return contactWrapper.field("familyName", Contact::familyNameProperty);
	}

	public Property<String> streetProperty() {
		return contactWrapper.field("street", Contact::streetProperty);
	}

	public Property<String> zipCodeProperty() {
		return contactWrapper.field("zipCode", Contact::zipCodeProperty);
	}

	public Property<String> placeProperty() {
		return contactWrapper.field("place", Contact::placeProperty);
	}

	public Property<String> countryProperty() {
		return contactWrapper.field("country", Contact::countryProperty);
	}

	public Property<LocalDate> birthDateProperty() {
		return contactWrapper.field("birthDate", Contact::birthDateProperty);
	}

	public Property<String> emailProperty() {
		return contactWrapper.field("email", Contact::emailsProperty);
	}

	public Property<String> telephoneProperty() {
		return contactWrapper.field("telephone", Contact::telephonesProperty);
	}

	public ObjectProperty<Contact> selectedContactProperty() {
		return selectedContact;
	}

	// for Test
	public void setSelectedContact(Contact contact) {
		this.selectedContact.set(contact);
	}

}
