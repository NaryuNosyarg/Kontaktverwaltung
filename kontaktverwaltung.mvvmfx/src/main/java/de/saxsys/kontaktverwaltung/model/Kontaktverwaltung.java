package de.saxsys.kontaktverwaltung.model;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Singleton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;



//Mock
@Singleton
public class Kontaktverwaltung implements Verwaltung {
	
	private ObservableList<Contact> contactList = FXCollections.observableArrayList();

	@Override
	public void showContactDetail(Contact contact) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addContact(Contact contact) {
		if (!contactList.contains(contact)) {
			contactList.add(contact);
		}
	}

	@Override
	public void editContact(Contact contact) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeContact(Contact contact) {
		contactList.remove(contact);

	}

	@Override
	public void exportConact(Contact contact) {
		// TODO Auto-generated method stub

	}

	public void showContacts() {
		System.out.println(contactList);
	}

	public ObservableList<Contact> getContactList() {
		return contactList;
	}

	public Kontaktverwaltung() {

		Contact bruce = null;
		try {
			bruce = new Contact("1", "Bruce", "Wayne", LocalDate.of(1975,
					01, 10));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Address bwaddress = new Address("1", "Wayne Manor", "Gotham City",
				"666", "Rhode Island", "Wayne Enterprise", "007");
		bruce.setLivingPlace(bwaddress);
		
		//Set<String> s = new TreeSet<String>();
		//s.add("017466466");
		//ObservableSet<String> observableSet = javafx.collections.FXCollections.observableSet(s);
		//tel.setTelephones(observableSet);
		
		CommunicationInfo ci = new CommunicationInfo();
		ci.setTelephones("017466466");
		ci.setEmails("bruce.wayne@wayneenterprises.com");
		bruce.setCommunicationInfo(ci);
		contactList.add(bruce);

		try {
			contactList.add(new Contact("2", "Dick", "Grayson", LocalDate.of(1991,
					03, 20)));
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}
