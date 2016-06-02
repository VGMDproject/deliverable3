//Author Vu
package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import conference_management.Conference;
import conference_management.User;

public class ConferenceTest {
	int confID = 1;
	String testName = "name";
	int testStatus = 0;
	User testUser;
	Conference conf;
	Date testDeadline;
	ArrayList<Conference> confList;

	@Before
	public void setup() {
		User testUser = new User();
		testUser.init();
		conf = new Conference(confID);
		testUser.myConferenceArrayList.add(conf);
		testDeadline = new Date();
		confList = new ArrayList<Conference>();
	}

	@Test
	public void testConstructor_withNonull() {
		assertNotNull(conf);
	}

	@Test
	public void testtestConstructor_withconfID() {
		assertEquals(conf.getIDConference(), 1);
	}

	@Test
	public void testConferenceUserInt_withEmptyConference() {
	}

	@Test
	public void testConferenceUserInt_withAddedConference() {
		User testUser2 = new User();
		testUser2.init();
		User testUser3 = new User();
		testUser3.init();
		Conference conf2 = new Conference(5);
		Conference conf3 = new Conference(6);
		testUser2.myConferenceArrayList.add(conf2);
		testUser2.myConferenceArrayList.add(conf3);
	}

	@Test
	public void testsetConferenceID() {
		conf.setConferenceID(confID);
		assertEquals(confID, conf.getIDConference());
	}

	@Test
	public void testsetConferenceID_notNUll() {
		conf.setConferenceID(confID);
		assertNotNull(conf.getIDConference());
	}

	@Test
	public void testsetName() {
		conf.setName(testName);
		assertEquals(testName, conf.getName());
	}

	@Test
	public void testSetName_withNull() {
		conf.setName(null);
		assertNull(conf.getName());
	}

	@Test
	public void testSetStatusConference_withNotNUll() {
		conf.setStatusConference(testStatus);
		assertNotNull(conf.getStatusConference());
	}

	@Test
	public void testSetStatusConference() {
		conf.setStatusConference(testStatus);
		assertEquals(testStatus, conf.getStatusConference());
	}

	@Test
	public void testSetDeadline_withNUll() {
		conf.setDeadline(null);
		assertNull(conf.getDeadline());
	}

	@Test
	public void testSetDeadline() {
		conf.setDeadline(testDeadline);
		assertEquals(testDeadline, conf.getDeadline());
	}

	@Test
	public void testGetName() {
		String Username = "user1";
		conf.setName(Username);
		assertEquals(Username, conf.getName());
	}

	@Test
	public void testGetDeadline() {
		conf.setDeadline(testDeadline);
		assertTrue(conf.getDeadline().equals(testDeadline));
	}

	@Test
	public void testGetStatusConference() {
		int status = 1;
		conf.setStatusConference(status);
		assertEquals(status, conf.getStatusConference());
	}

	@Test
	public void testGetIDConference() {
		int newID = 10;
		conf.setConferenceID(newID);
		assertEquals(newID, conf.getIDConference());
	}
}