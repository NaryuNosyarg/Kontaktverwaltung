package de.saxsys.kontaktverwaltung.model;


import javafx.collections.ObservableList;

public interface Verwaltung {
	
	
	public void showContactDetail(Contact contact);
	
	public void addContact(Contact contact);
	
	public void editContact(Contact contact);
	
	public void removeContact(Contact contact);
	
	public void exportConact(Contact contact);
	public ObservableList<Contact> getContactList();
}
