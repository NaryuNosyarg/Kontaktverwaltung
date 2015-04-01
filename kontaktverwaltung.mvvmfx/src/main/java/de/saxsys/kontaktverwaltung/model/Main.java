package de.saxsys.kontaktverwaltung.model;

import java.text.ParseException;
import java.time.LocalDate;

//Mock
public class Main {

	public static void main(String[] args) throws ParseException {

		Contact bruceWayne = new Contact("1", "Bruce", "Wayne", LocalDate.of(1975,01,10));

		Address bwaddress = new Address("1", "Wayne Manor", "Gotham City",
				"666", "Rhode Island", "Wayne Enterprise", "007");

		CommunicationInfo bwcomminfo = new CommunicationInfo();

		bruceWayne.setLivingPlace(bwaddress);
		bruceWayne.setCommunicationInfo(bwcomminfo);

		bwcomminfo.setId("1");
		bwcomminfo.setEmails(null);
		bwcomminfo.setTelephones(null);

		Contact richardGrayson = new Contact("2", "Dick", "Grayson", LocalDate.of(1991,03,20));
		
		Address rgaddress = new Address("2", "Island Street 29", "Bludhaven",
				"0145", "New Jersey", "Bludhaven Police Department", "15");
		
		CommunicationInfo rgcomminfo = new CommunicationInfo();

		richardGrayson.setLivingPlace(rgaddress);
		richardGrayson.setCommunicationInfo(rgcomminfo);

		rgcomminfo.setId("2");
		rgcomminfo.setEmails(null);
		rgcomminfo.setTelephones(null);

		Kontaktverwaltung kv = new Kontaktverwaltung();

		kv.addContact(bruceWayne);
		kv.addContact(richardGrayson);
		kv.showContacts();
	}
}
