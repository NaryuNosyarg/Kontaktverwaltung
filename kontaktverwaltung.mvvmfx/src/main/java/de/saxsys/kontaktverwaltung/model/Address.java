package de.saxsys.kontaktverwaltung.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Address {

	private final StringProperty id;
	private final StringProperty street;
	private final StringProperty place;
	private final StringProperty zipCode;
	private final StringProperty country;
	private final StringProperty building;
	private final StringProperty room;

	public Address(String aId, String aStreet, String aPlace, String aZipCode, String aCountry, String aBuilding, String aRoom) {

		this.id = new SimpleStringProperty(aId);
		this.street = new SimpleStringProperty(aStreet);
		this.place = new SimpleStringProperty(aPlace);
		this.zipCode = new SimpleStringProperty(aZipCode); 
		this.country = new SimpleStringProperty(aCountry);
		this.building = new SimpleStringProperty(aBuilding);
		this.room = new SimpleStringProperty(aRoom);
	}


	public String getId() {
		return id.get();
	}

	public String getStreet() {
		return street.get();
	}

	public String getPlace() {
		return place.get();
	}

	public String getZipCode() {
		return zipCode.get();
	}

	public String getCountry() {
		return country.get();
	}

	public String getBuilding() {
		return building.get();
	}

	public String getRoom() {
		return room.get();
	}

	public void setId(String newId) {
		this.id.set(newId);
	}

	public void setStreet(String newStreet) {
		this.street.set(newStreet);
	}

	public void setPlace(String newPlace) {
		this.place.set(newPlace);
	}

	public void setZipCode(String newZipCode) {
		this.zipCode.set(newZipCode);
	}

	public void setCountry(String newCountry) {
		this.country.set(newCountry);
	}

	public void setBuilding(String newBuilding) {
		this.building.set(newBuilding);
	}

	public void setRoom(String newRoom) {
		this.room.set(newRoom);
	}

	public StringProperty idProperty() {
		return id;
	}

	public StringProperty streetProperty() {
		return street;
	}

	public StringProperty placeProperty() {
		return place;
	}

	public StringProperty zipCodeProperty() {
		return zipCode;
	}

	public StringProperty countryProperty() {
		return country;
	}

	public StringProperty buildingProperty() {
		return building;
	}

	public StringProperty roomProperty() {
		return room;
	}

	public String toString() {
		return getStreet() + "  " + getPlace() + " " + getZipCode()
				+ "  Land: " + getCountry() + "  Ort: " + getBuilding()
				+ "  Raum: " + getRoom();
	}
}
