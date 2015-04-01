package de.saxsys.kontaktverwaltung.model;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

import java.text.ParseException;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;


public class addContactTest {

	private Kontaktverwaltung kv;

	@Before
	public void createKv() throws ParseException {

		kv = new Kontaktverwaltung();
		kv.getContactList().clear();
	
	}

	@Test
	public void testContactListIsNotEmpty() throws ParseException {

		Contact bruceWayne = new Contact("1", "Bruce", "Wayne", LocalDate.of(1975,01,10));
		kv.addContact(bruceWayne);
		assertFalse("List is empty", kv.getContactList().isEmpty());

	}

	@Test
	public void testContactListIsNotNull() throws ParseException {

		Contact bruceWayne = new Contact("1", "Bruce", "Wayne", LocalDate.of(1975,01,10));
		kv.addContact(bruceWayne);
		assertNotNull("List is null", kv.getContactList());
	}


	@Test
	public void testContactListSize() throws ParseException {

		assertEquals("size of list is not correct", 0, kv.getContactList().size());
		Contact bruceWayne = new Contact("1", "Bruce", "Wayne", LocalDate.of(1975,01,10));
		Contact richardGrayson = new Contact("2", "Dick", "Grayson", LocalDate.of(1991,03,20));
		kv.addContact(bruceWayne);
		kv.addContact(richardGrayson);
		assertEquals("Size of list is not correct", 2, kv.getContactList().size());
		kv.removeContact(bruceWayne);
		assertEquals("Size of list is not correct", 1, kv.getContactList().size());

	}
	
	@Test
	public void testContactListDoesntHaveDuplicates() throws ParseException {

		assertEquals("size of list is not correct", 0, kv.getContactList().size());
		Contact bruceWayne = new Contact("1", "Bruce", "Wayne", LocalDate.of(1975,01,10));
		Contact richardGrayson = new Contact("2", "Dick", "Grayson", LocalDate.of(1991,03,20));
		kv.addContact(bruceWayne);
		kv.addContact(richardGrayson);
		kv.addContact(richardGrayson);
		kv.addContact(bruceWayne);
		assertEquals("Size of list is not correct", 2, kv.getContactList().size());

	}

	@Test
	public void testContactListGetElement() throws ParseException {

		Contact bruceWayne = new Contact("1", "Bruce", "Wayne", LocalDate.of(1975,01,10));
		Contact richardGrayson = new Contact("2", "Dick", "Grayson", LocalDate.of(1991,03,20));
		kv.addContact(bruceWayne);
		kv.addContact(richardGrayson);
		assertEquals("Contact is not Bruce Wayne", bruceWayne, kv.getContactList().get(0));
		assertEquals("Contact is not Richard Grayson", richardGrayson, kv.getContactList().get(1));
	}
	
	@Test
	public void testContactListContainsElement() throws ParseException{
		
		Contact bruceWayne = new Contact("1", "Bruce", "Wayne", LocalDate.of(1975,01,10));
		Contact richardGrayson = new Contact("2", "Dick", "Grayson", LocalDate.of(1991,03,20));
		kv.addContact(richardGrayson);
		assertTrue("List does not contain contact Richard Grayson", kv.getContactList().contains(richardGrayson));
		assertFalse("List contains contact Bruce Wayne", kv.getContactList().contains(bruceWayne));
	}
	
	@Test
	public void testContactListWithAssertJ() throws ParseException{

		Contact bruceWayne = new Contact("1", "Bruce", "Wayne", LocalDate.of(1975,01,10));
		Contact richardGrayson = new Contact("2", "Dick", "Grayson", LocalDate.of(1991,03,20));
		kv.addContact(bruceWayne);
		
		assertThat(kv.getContactList().isEmpty()).isFalse();
		assertThat(kv.getContactList()).isNotNull();
		assertThat(kv.getContactList()).hasSize(1).contains(bruceWayne)
				.doesNotContain(richardGrayson);
		assertThat(kv.getContactList().get(0)).isEqualTo(bruceWayne);
		assertThat(kv.getContactList().contains(bruceWayne)).isTrue();

	}

}
