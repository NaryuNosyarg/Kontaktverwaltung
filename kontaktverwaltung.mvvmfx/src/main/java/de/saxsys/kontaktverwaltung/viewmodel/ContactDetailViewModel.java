package de.saxsys.kontaktverwaltung.viewmodel;

import java.time.LocalDate;
import java.util.concurrent.Callable;
import java.util.function.Function;

import javax.inject.Singleton;

import de.saxsys.kontaktverwaltung.model.Contact;
import de.saxsys.kontaktverwaltung.model.Verwaltung;
import de.saxsys.kontaktverwaltung.util.DateUtil;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

@Singleton
public class ContactDetailViewModel implements ViewModel {

	private ObjectProperty<Contact> selectedContact = new SimpleObjectProperty<>();

	private StringProperty contactNameProperty = new SimpleStringProperty();
	private StringProperty streetProperty = new SimpleStringProperty();
	private StringProperty zipCodePlaceProperty = new SimpleStringProperty();
	private StringProperty countryProperty = new SimpleStringProperty();
	private ObjectProperty<LocalDate> birthDateProperty = new SimpleObjectProperty<LocalDate>();
	private StringProperty formattedBirthDateProperty = new SimpleStringProperty();
	private StringProperty emailProperty = new SimpleStringProperty();
	private StringProperty telephoneProperty = new SimpleStringProperty();

	private Function<Void, Void> detailShowFunction;
	private Function<Void, Void> detailCloseFunction;
	private Function<Void, Void> detailShowAndWaitFunction;

	private Verwaltung verwaltung;

	public void setDetailShowFunction(Function<Void, Void> detailShowFunction) {
		this.detailShowFunction = detailShowFunction;
	}

	public void setDetailCloseFunction(Function<Void, Void> detailCloseFunction) {
		this.detailCloseFunction = detailCloseFunction;
	}

	public void setDetailShowAndWaitFunction(
			Function<Void, Void> detailShowAndWaitFunction) {
		this.detailShowAndWaitFunction = detailShowAndWaitFunction;
	}

	public ContactDetailViewModel(Verwaltung verwaltung) {
		this.verwaltung = verwaltung;

		selectedContact.addListener(new ChangeListener<Contact>() {

			@Override
			public void changed(ObservableValue<? extends Contact> arg0,
					Contact oldContact, Contact newContact) {
				if (newContact != null) {
					contactNameProperty.bind(Bindings.concat(selectedContact
							.getValue().nameProperty(), " ", selectedContact
							.getValue().familyNameProperty()));
					streetProperty.bind(selectedContact.getValue()
							.getLivingPlace().streetProperty());
					zipCodePlaceProperty.bind(Bindings.concat(selectedContact
							.getValue().getLivingPlace().zipCodeProperty(),
							" ", selectedContact.getValue().getLivingPlace()
									.placeProperty()));
					countryProperty.bind(selectedContact.getValue()
							.getLivingPlace().countryProperty());
					birthDateProperty.bind(selectedContact.getValue()
							.birthDateProperty());
					emailProperty.bind(selectedContact.getValue()
							.getCommunicationInfo().emailsProperty());
					telephoneProperty.bind(selectedContact.getValue()
							.getCommunicationInfo().telephonesProperty());
				}
			}
		});

		StringBinding formattedDateBinding = Bindings.createStringBinding(
				new Callable<String>() {
					@Override
					public String call() throws Exception {
						return DateUtil.format(birthDateProperty.getValue());
					}
				}, birthDateProperty);

		formattedBirthDateProperty.bind(formattedDateBinding);
	}

	public void show(Contact contact) {
		selectedContactProperty().set(contact);
		detailShowAndWaitFunction.apply(null);
	}

	public StringProperty contactNameProperty() {
		return contactNameProperty;
	}

	public StringProperty streetProperty() {
		return streetProperty;
	}

	public StringProperty zipCodePlaceProperty() {
		return zipCodePlaceProperty;
	}

	public StringProperty countryProperty() {
		return countryProperty;
	}

	public StringProperty birthDateProperty() {
		return formattedBirthDateProperty;
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
	//for Test
	public void setSelectedContact(Contact contact) {
		this.selectedContact.set(contact);
	}
	//for Test
	public ObservableList<Contact> listProperty() {
		return verwaltung.getContactList();
	}

	public void edit(Function<Contact, Boolean> editFunction) {
		Contact selectedPerson = selectedContactProperty().getValue();
		if (selectedPerson != null) {
			boolean okClicked = editFunction.apply(selectedPerson);
			if (okClicked) {
				detailShowFunction.apply(null);
			}

		}
	}

	public void remove() {
		Contact selectedPerson = selectedContactProperty().getValue();
		verwaltung.removeContact(selectedPerson);
		detailCloseFunction.apply(null);
	}

}
