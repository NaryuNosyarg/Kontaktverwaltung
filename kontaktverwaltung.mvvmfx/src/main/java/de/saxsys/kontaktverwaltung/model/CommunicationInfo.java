package de.saxsys.kontaktverwaltung.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CommunicationInfo {

	private final StringProperty id;
	private final StringProperty emails;
	private final StringProperty telephones;

	public CommunicationInfo() {

		this.id = new SimpleStringProperty();
		this.emails = new SimpleStringProperty();
		telephones = new SimpleStringProperty();
	}

	public String getId() {
		return id.get();
	}

	public String getEmails() {
		return emails.get();
	}

	public String getTelephones() {
		return telephones.get();
	}

	public void setId(String newId) {
		this.id.set(newId);
	}

	public void setEmails(String newEmail) {
		this.emails.set(newEmail);
	}

	public void setTelephones(String newTelephone) {
		this.telephones.set(newTelephone);
	}

	public StringProperty idProperty() {
		return id;
	}

	public StringProperty emailsProperty() {
		return emails;
	}

	public StringProperty telephonesProperty() {
		return telephones;
	}
	

	public String toString() {
		return getEmails() + "  " + getTelephones();
	}

}
