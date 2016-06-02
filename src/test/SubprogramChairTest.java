package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import conference_management.SubprogramChair;
import conference_management.User;

/**
 * Tests the SubprogramChair.java
 * 
 * @author Gabriela Orozco
 * @version May 27, 2016
 */
public class SubprogramChairTest {

	/** SubprogramChair object used for testing. */
	private SubprogramChair mySPC;
	/** User object used for testing. */
	private User myUser;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() {
		myUser = new User("Gabriela");
		myUser.init();
		myUser.myUserArrayList.add(myUser);
		mySPC = new SubprogramChair(myUser, myUser.getUserName(), 0);
	}

	/**
	 * Tests the setters and getters of the SubprogramChair class.
	 */
	@Test
	public void testSubprogramChairSetters() {
		String testName = "Gabriela";
		mySPC.setSPCUser(myUser);
		assertEquals("Setter for SubprogramChair failed!", testName, mySPC.getSPCUser());

		testName = "Daniel";
		myUser.setUserName(testName);
		mySPC.setSPCUser(myUser);
		assertEquals("Setter for SubprogramChair failed!", testName, mySPC.getSPCUser());

		testName = "Josh";
		myUser.setUserName(testName);
		mySPC.setSPCUser(myUser);
		assertEquals("Setter for SubprogramChair failed!", testName, mySPC.getSPCUser());

		testName = "Moe";
		myUser.setUserName(testName);
		mySPC.setSPCUser(myUser);
		assertEquals("Setter for SubprogramChair failed!", testName, mySPC.getSPCUser());

		testName = "Vu";
		myUser.setUserName(testName);
		mySPC.setSPCUser(myUser);
		assertEquals("Setter for SubprogramChair failed!", testName, mySPC.getSPCUser());

		testName = "Bob";
		myUser.setUserName(testName);
		mySPC.setSPCUser(myUser);
		assertEquals("Setter for SubprogramChair failed!", testName, mySPC.getSPCUser());

		testName = "Luke";
		myUser.setUserName(testName);
		mySPC.setSPCUser(myUser);
		assertEquals("Setter for SubprogramChair failed!", testName, mySPC.getSPCUser());

		testName = "Rob";
		myUser.setUserName(testName);
		mySPC.setSPCUser(myUser);
		assertEquals("Setter for SubprogramChair failed!", testName, mySPC.getSPCUser());
	}
}