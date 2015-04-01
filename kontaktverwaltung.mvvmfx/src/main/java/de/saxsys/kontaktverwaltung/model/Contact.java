package de.saxsys.kontaktverwaltung.model;


import java.text.ParseException;
import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Contact {

	private final StringProperty id;
	private final StringProperty name;
	private final StringProperty familyName;
	private Address livingPlace;
	private  final ObjectProperty<LocalDate> birthDate;
	private CommunicationInfo communicationInfo;
	

	public Contact(String cId, String cName, String cFamilyName,
			LocalDate cBirthDate) throws ParseException {

		this.id = new SimpleStringProperty(cId);
		this.name = new SimpleStringProperty(cName);
		this.familyName = new SimpleStringProperty(cFamilyName);
		this.livingPlace = new Address(this.getId(), "", "", "", "", "", "");
		this.birthDate = new SimpleObjectProperty<LocalDate>(cBirthDate);
		this.communicationInfo = new CommunicationInfo();
		
	}

	public String getId() {
		return id.get();
	}

	public String getName() {
		return name.get();
	}

	public String getFamilyName() {
		return familyName.get();
	}

	public Address getLivingPlace() {
		return livingPlace;
	}

	public LocalDate getBirthDate() {
		return birthDate.get();
	}

	public CommunicationInfo getCommunicationInfo() {
		return communicationInfo;
	}

	public void setId(String newId) {
		this.id.set(newId);
    }

	public void setName(String newName) {
		this.name.set(newName);
	}

	public void setFamilyName(String newFamilyName) {
		this.familyName.set(newFamilyName);
	}

	public void setLivingPlace(Address newLivingPlace) {
		livingPlace = newLivingPlace;
	}

	public void setBirthDate(LocalDate newBirthDate) {
		this.birthDate.set(newBirthDate);
	}

	public void setCommunicationInfo(CommunicationInfo newCommunicationInfo) {
		communicationInfo = newCommunicationInfo;
	}

	public StringProperty idProperty() {
		return id;
	}

	public StringProperty nameProperty() {
		return name;
	}

	public StringProperty familyNameProperty() {
		return familyName;
	}

	public ObjectProperty<LocalDate> birthDateProperty() {
		return birthDate;
	}

	public String toString() {
		return getName() + " " + getFamilyName();
	}

}
