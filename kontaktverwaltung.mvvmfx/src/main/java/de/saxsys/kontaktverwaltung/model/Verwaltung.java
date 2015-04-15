package de.saxsys.kontaktverwaltung.model;

import java.util.List;

public interface Verwaltung {

	public void showContactDetail(Contact contact);

	public void addContact(Contact contact);

	public void editContact(Contact contact);

	public void removeContact(Contact contact);

	public void exportConact(Contact contact);

	public List<Contact> getContactList();
}
