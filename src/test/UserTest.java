/**
 * <pre> 
 * Class: <b>UserTest</b> 
 * File: UserTest.java 
 * Course: TCSS 360 – Spring 2016
 * Copyright 2016 Moe Abdipour
 * </pre>
 */
package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import conference_management.User;

/**
 * <pre>
 * This class is responsible for testing the User class. The User class
 * 		is the parent of all other users in this program. Hence, testing
 * 		User class guarantees almost all actions made by other users with
 * 		different roles.
 * </pre>
 * 
 * @author Moe Abdipour
 * @version 05/31/2016
 * @since May 26, 2016
 */
public class UserTest {

	private User myUser;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() {
		myUser = new User("");
	}

	/**
	 * Test method for {@link conference_management.User#User()}.
	 */
	@Test
	public final void testUser() {
		try {
			new User();
		} catch (Exception e) {
			fail("Unable to make and instance of " + this.getClass().getSimpleName());
		}
	}

	/**
	 * Test method for {@link conference_management.User#User()}.
	 */
	@Test
	public final void testUserPassingNull() {
		try {
			new User(null);
		} catch (Exception e) {
			fail("Unable to make and instance of " + this.getClass().getSimpleName());
		}
	}

	/**
	 * Test method for {@link conference_management.User#User()}.
	 */
	@Test
	public final void testUserPassingEmptyString() {
		try {
			new User("");
		} catch (Exception e) {
			fail("Unable to make and instance of " + this.getClass().getSimpleName());
		}
	}

	/**
	 * Test method for {@link conference_management.User#User()}.
	 */
	@Test
	public final void testUserPassingString() {
		try {
			new User("1");
		} catch (Exception e) {
			fail("Unable to make and instance of " + this.getClass().getSimpleName());
		}
	}

	/**
	 * Test method for {@link conference_management.User#init()}.
	 */
	@Test
	public final void testInit() {
		try {
			myUser.init();
			assertFalse(myUser.isAuthor());
			assertFalse(myUser.isPC());
			assertFalse(myUser.isReviewer());
			assertFalse(myUser.isSPC());
			assertFalse(myUser.isUser());
			assertEquals(myUser.getSelectedConferenceID(), -1);
			assertNotNull(myUser.myConferenceArrayList);
			assertNotNull(myUser.myPaperArrayList);
			assertNotNull(myUser.myUserArrayList);
			assertNotNull(myUser.myUpdateSerFileConference);
			assertNotNull(myUser.myUpdateSerFilePaper);
			assertNotNull(myUser.myUpdateSerFileUser);
		} catch (Exception e) {
			fail("init() failed.");
		}
	}

	/**
	 * Test method for {@link conference_management.User#getPaperIndex(int)}.
	 * This test goes over all elements in Paper ArrayList and sends their ID's
	 * to the getPaperIndex() and compares the result to the actual index.
	 */
	@Test
	public final void testGetPaperIndex() {
		// assertNotNull(myUser.myPaperArrayList);
		if (myUser.myPaperArrayList != null && myUser.myPaperArrayList.size() > 0) {
			for (int i = 0; i < myUser.myPaperArrayList.size(); i++) {
				assertEquals(myUser.getPaperIndex(myUser.myPaperArrayList.get(i).getID()), i);
			}
		}
	}

	/**
	 * Test method for {@link conference_management.User#getUserIndex(String)}.
	 * This test goes over all elements in User ArrayList and sends their user
	 * names to the getPaperIndex() and compares the result to the actual index.
	 */
	@Test
	public final void testGetUserIndex() {
		// assertNotNull(myUser.myUserArrayList);
		if (myUser.myUserArrayList != null && myUser.myUserArrayList.size() > 0) {
			for (int i = 0; i < myUser.myUserArrayList.size(); i++) {
				assertEquals(myUser.getUserIndex(myUser.myUserArrayList.get(i).getUserName()), i);
			}
		}
	}

	/**
	 * Test method for
	 * {@link conference_management.User#getConferenceIndex(int)}. This test
	 * goes over all elements in Conference ArrayList and sends their ID's to
	 * the getConferenceIndex() and compares the result to the actual index.
	 */
	@Test
	public final void testGetConferenceIndex() {
		// assertNotNull(myUser.myConferenceArrayList);
		if (myUser.myConferenceArrayList != null && myUser.myConferenceArrayList.size() > 0) {
			for (int i = 0; i < myUser.myConferenceArrayList.size(); i++) {
				assertEquals(myUser.getConferenceIndex(myUser.myConferenceArrayList.get(i)
						.getIDConference()), i);
			}
		}
	}

	/**
	 * Test method for {@link conference_management.User#getConferenceList()}.
	 */
	@Test
	public final void testGetConferenceList() {
		assertEquals(myUser.myConferenceArrayList, myUser.getConferenceList());
	}

	/**
	 * Test method for {@link conference_management.User#getPaperList()}.
	 */
	@Test
	public final void testGetPaperList() {
		assertEquals(myUser.myPaperArrayList, myUser.getPaperList());
	}

	/**
	 * Test method for {@link conference_management.User#getUserList()}.
	 */
	@Test
	public final void testGetUserList() {
		assertEquals(myUser.myUserArrayList, myUser.getUserList());
	}

	/**
	 * Test method for {@link conference_management.User#getUserName()}.
	 */
	@Test
	public final void testGetUserName() {
		String newUserName = "newUserName";
		myUser.setUserName(newUserName);
		assertNotNull(myUser.getUserName());
	}

	/**
	 * Test method for {@link conference_management.User#getName()}.
	 */
	@Test
	public final void testGetName() {
		String newName = "newName";
		myUser.setName(newName);
		assertNotNull(myUser.getName());
	}

	/**
	 * Test method for {@link conference_management.User#getRole()}.
	 */
	@Test
	public final void testGetRole() {
		String newRole = myUser.USER;
		myUser.setRole(newRole);
		assertNotNull(myUser.getRole());
	}

	/**
	 * Test method for
	 * {@link conference_management.User#setRole(java.lang.String)}.
	 */
	@Test
	public final void testSetRole() {
		String newRole = myUser.USER;
		myUser.setRole(newRole);
		assertEquals(newRole, myUser.getRole());
	}

	/**
	 * Test method for
	 * {@link conference_management.User#setName(java.lang.String)}.
	 */
	@Test
	public final void testSetName() {
		String newName = "newName";
		myUser.setName(newName);
		assertEquals(newName, myUser.getName());
	}

	/**
	 * Test method for
	 * {@link conference_management.User#setUserName(java.lang.String)}.
	 */
	@Test
	public final void testSetUserName() {
		String newUserName = "newUserName";
		myUser.setUserName(newUserName);
		assertEquals(newUserName, myUser.getUserName());
	}

	/**
	 * Test method for {@link conference_management.User#setUser()}.
	 */
	@Test
	public final void testSetUser() {
		myUser.setUser(true);
		assertTrue(myUser.isUser());
	}

	/**
	 * Test method for {@link conference_management.User#setPC()}.
	 */
	@Test
	public final void testSetPC() {
		myUser.setPC(true);
		assertTrue(myUser.isPC());
	}

	/**
	 * Test method for {@link conference_management.User#setSPC()}.
	 */
	@Test
	public final void testSetSPC() {
		myUser.setSPC(true);
		assertTrue(myUser.isSPC());
	}

	/**
	 * Test method for {@link conference_management.User#setReviewer()}.
	 */
	@Test
	public final void testSetReviewer() {
		myUser.setReviewer(true);
		assertTrue(myUser.isReviewer());
	}

	/**
	 * Test method for {@link conference_management.User#setAuthor()}.
	 */
	@Test
	public final void testSetAuthor() {
		myUser.setAuthor(true);
		assertTrue(myUser.isAuthor());
	}

	/**
	 * Test method for {@link conference_management.User#isUser()}.
	 */
	@Test
	public final void testIsUser() {
		myUser.setUser(false);
		assertFalse(myUser.isUser());
	}

	/**
	 * Test method for {@link conference_management.User#isPC()}.
	 */
	@Test
	public final void testIsPC() {
		myUser.setPC(false);
		assertFalse(myUser.isPC());
	}

	/**
	 * Test method for {@link conference_management.User#isSPC()}.
	 */
	@Test
	public final void testIsSPC() {
		myUser.setSPC(false);
		assertFalse(myUser.isSPC());
	}

	/**
	 * Test method for {@link conference_management.User#isReviewer()}.
	 */
	@Test
	public final void testIsReviewer() {
		myUser.setReviewer(false);
		assertFalse(myUser.isReviewer());
	}

	/**
	 * Test method for {@link conference_management.User#isAuthor()}.
	 */
	@Test
	public final void testIsAuthor() {
		myUser.setAuthor(false);
		assertFalse(myUser.isAuthor());
	}

	/**
	 * Test method for
	 * {@link conference_management.User#getSelectedConferenceID()}.
	 */
	@Test
	public final void testGetSelectedConferenceID() {
		myUser.setSelectedConferenceID(1);
		assertEquals(myUser.getSelectedConferenceID(), 1);
	}

	/**
	 * Test method for
	 * {@link conference_management.User#setSelectedConferenceID(int)}.
	 */
	@Test
	public final void testSetSelectedConferenceID() {
		myUser.setSelectedConferenceID(2);
		assertEquals(myUser.getSelectedConferenceID(), 2);
	}
}