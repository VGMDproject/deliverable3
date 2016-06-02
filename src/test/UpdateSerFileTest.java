/**
 * <pre> 
 * Class: <b>UpdateSerFileTest</b> 
 * File: UpdateSerFileTest.java 
 * Course: TCSS 360 – Spring 2016
 * Copyright 2016 Moe Abdipour
 * </pre>
 */
package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;
import conference_management.Conference;
import conference_management.Paper;
import conference_management.UpdateSerFile;
import conference_management.User;

/**
 * <pre>
 * This class is responsible for testing the UpdateSerFile class. The
 * 		UpdateSerFile class works with external files so does the test
 * 		file. The test file also checks for the accuracy of data that
 * 		is deserialized from the external files.
 * </pre>
 * 
 * @author Moe Abdipour
 * @version 05/31/2016
 * @since May 26, 2016
 */
public class UpdateSerFileTest {
	private UpdateSerFile myUpdateSerFile;
	private ArrayList<Conference> conferenceArrayList;
	private ArrayList<Paper> paperArrayList;
	private ArrayList<User> userArrayList;

	/**
	 * Test method for
	 * {@link conference_management.UpdateSerFile#UpdateSerFile(java.lang.Object)}
	 * . Test to see if a new instance of this class is created input value is
	 * an instance of an Object
	 * 
	 */
	@Test
	public final void testUpdateSerFilePassingNull() {
		Object object = null;

		try {
			new UpdateSerFile(object);
		} catch (Exception e) {
			fail("Unable to make and instance of " + this.getClass().getSimpleName()
					+ " with passing in a null object");
		}
	}

	/**
	 * Test method for
	 * {@link conference_management.UpdateSerFile#UpdateSerFile(java.lang.Object)}
	 * . Test to see if a new instance of this class is created input value is
	 * an instance of an Object
	 * 
	 */
	@Test
	public final void testUpdateSerFilePassingObject() {
		Object object = new Object();

		try {
			new UpdateSerFile(object);
		} catch (Exception e) {
			fail("Unable to make and instance of " + this.getClass().getSimpleName()
					+ " with passing in an instance of an object");
		}
	}

	/**
	 * Test method for {@link conference_management.UpdateSerFile#deserialize()}
	 * . Test to deserialize conference.ser to see if the return object is an
	 * ArrayList
	 * 
	 * @throws ClassNotFoundException
	 */
	@Test
	public final void testDeserializeIsReturnArrayList() {
		myUpdateSerFile = new UpdateSerFile(new Conference());
		Object myObject = new Object();
		myObject = myUpdateSerFile.deserialize();

		// assertNotNull(myObject);
		if (myObject != null) {
			assertTrue(myObject instanceof ArrayList);
		}
	}

	/**
	 * Test method for {@link conference_management.UpdateSerFile#deserialize()}
	 * . Test to deserialize conference.ser to see if it is possible to cast the
	 * return object to an ArrayList
	 */
	@SuppressWarnings("unchecked")
	@Test
	public final void testDeserializeConference() {
		myUpdateSerFile = new UpdateSerFile(new Conference());
		conferenceArrayList = null;
		conferenceArrayList = (ArrayList<Conference>) myUpdateSerFile.deserialize();
	}

	/**
	 * Test method for {@link conference_management.UpdateSerFile#deserialize()}
	 * . Test to deserialize paper.ser to see if it is possible to cast the
	 * return object to an ArrayList
	 */
	@SuppressWarnings("unchecked")
	@Test
	public final void testDeserializePaper() {
		myUpdateSerFile = new UpdateSerFile(new Paper());
		paperArrayList = null;
		paperArrayList = (ArrayList<Paper>) myUpdateSerFile.deserialize();

	}

	/**
	 * Test method for {@link conference_management.UpdateSerFile#deserialize()}
	 * . Test to deserialize user.ser to see if it is possible to cast the
	 * return object to an ArrayList
	 */
	@SuppressWarnings("unchecked")
	@Test
	public final void testDeserializeUser() {

		myUpdateSerFile = new UpdateSerFile(new User());
		userArrayList = null;
		userArrayList = (ArrayList<User>) myUpdateSerFile.deserialize();
	}

	/**
	 * Test method for {@link conference_management.UpdateSerFile#deserialize()}
	 * . Test to deserialize user.ser to see if it is possible to cast the
	 * return object to an ArrayList
	 */
	@Test
	public final void testDeserializeInvalid() {
		Object newObject = new Object();
		myUpdateSerFile = new UpdateSerFile(newObject);
		try {
			myUpdateSerFile.deserialize();
			fail("Unable to deserialize.");
		} catch (Exception e) {
		}
	}

	/**
	 * Test method for
	 * {@link conference_management.UpdateSerFile#makeSerialize(java.lang.Object)}
	 * . Test to see if it is possible to serialize an ArrayList of Conferences
	 * The test happens only if the previous corresponding deserialize test put
	 * some data into the the ArrayList of Conferences, otherwise, data lost
	 * might happen.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public final void testMakeSerializeConference() {
		conferenceArrayList = null;
		myUpdateSerFile = new UpdateSerFile(new Conference());
		conferenceArrayList = (ArrayList<Conference>) myUpdateSerFile.deserialize();

		// assertNotNull(conferenceArrayList);
		if (conferenceArrayList != null) {
			Conference localConference = new Conference();
			myUpdateSerFile = new UpdateSerFile(localConference);
			myUpdateSerFile.makeSerialize(conferenceArrayList);
		}
	}

	/**
	 * Test method for
	 * {@link conference_management.UpdateSerFile#makeSerialize(java.lang.Object)}
	 * . Test to see if it is possible to serialize an ArrayList of Paper The
	 * test happens only if the previous corresponding deserialize test put some
	 * data into the the ArrayList of Paper, otherwise, data lost might happen.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public final void testMakeSerializePaper() {
		paperArrayList = null;
		myUpdateSerFile = new UpdateSerFile(new Paper());
		paperArrayList = (ArrayList<Paper>) myUpdateSerFile.deserialize();

		// assertNotNull(paperArrayList);
		if (paperArrayList != null) {
			myUpdateSerFile = new UpdateSerFile(new Paper());
			myUpdateSerFile.makeSerialize(paperArrayList);
		}
	}

	/**
	 * Test method for
	 * {@link conference_management.UpdateSerFile#makeSerialize(java.lang.Object)}
	 * . Test to see if it is possible to serialize an ArrayList of User The
	 * test happens only if the previous corresponding deserialize test put some
	 * data into the the ArrayList of User, otherwise, data lost might happen.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public final void testMakeSerializeUser() {
		userArrayList = null;
		myUpdateSerFile = new UpdateSerFile(new User());
		userArrayList = (ArrayList<User>) myUpdateSerFile.deserialize();

		// assertNotNull(userArrayList);
		if (userArrayList != null) {
			myUpdateSerFile = new UpdateSerFile(new User());
			myUpdateSerFile.makeSerialize(userArrayList);
		}
	}

	/**
	 * Test method for {@link conference_management.UpdateSerFile#deserialize()}
	 * . Test to deserialize conference.ser to see if the new written Conference
	 * ArrayList is the same as the Conference ArrayList that we deserialized
	 * before. In fact, this test is to see if writing into conference.ser is
	 * working properly. The test checks for the equal Conference ID, name,
	 * deadline, and status on each of Conferenes in both ArrayLists.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public final void testDeserializeConferenceBeforeAfter() {
		// first fetch
		conferenceArrayList = null;
		myUpdateSerFile = new UpdateSerFile(new Conference());
		conferenceArrayList = (ArrayList<Conference>) myUpdateSerFile.deserialize();

		// serialize
		// assertNotNull(conferenceArrayList);
		if (conferenceArrayList != null) {
			myUpdateSerFile = new UpdateSerFile(new Conference());
			myUpdateSerFile.makeSerialize(conferenceArrayList);
		}

		// second fetch
		ArrayList<Conference> conferenceArrayListNew = null;
		myUpdateSerFile = new UpdateSerFile(new Conference());
		conferenceArrayListNew = (ArrayList<Conference>) myUpdateSerFile.deserialize();

		// assertNotNull(conferenceArrayList);
		// assertNotNull(conferenceArrayListNew);
		if (conferenceArrayListNew != null && conferenceArrayList != null) {
			for (int i = 0; i < conferenceArrayList.size(); i++) {
				assertEquals(conferenceArrayList.get(i).getIDConference(),
						conferenceArrayListNew.get(i).getIDConference());
				assertEquals(conferenceArrayList.get(i).getDeadline(), conferenceArrayListNew
						.get(i).getDeadline());
				assertEquals(conferenceArrayList.get(i).getName(), conferenceArrayListNew
						.get(i).getName());
				assertEquals(conferenceArrayList.get(i).getStatusConference(),
						conferenceArrayListNew.get(i).getStatusConference());
			}
		}
	}

	/**
	 * Test method for {@link conference_management.UpdateSerFile#deserialize()}
	 * . Test to deserialize paper.ser to see if the new written Paper ArrayList
	 * is the same as the Paper ArrayList that we deserialized before. In fact,
	 * this test is to see if writing into paper.ser is working properly. The
	 * test checks for the equal Paper author user name, conference ID, file,
	 * ID, name, status, Program Chair if any, Subprogram Chair if any, Reviewer
	 * if any, review if any, and recommendation if any.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public final void testDeserializePaperBeforeAfter() {
		// first fetch
		paperArrayList = null;
		myUpdateSerFile = new UpdateSerFile(new Paper());
		paperArrayList = (ArrayList<Paper>) myUpdateSerFile.deserialize();

		// serialize
		// assertNotNull(paperArrayList);
		if (paperArrayList != null) {
			myUpdateSerFile = new UpdateSerFile(new Paper());
			myUpdateSerFile.makeSerialize(paperArrayList);
		}

		// second fetch
		ArrayList<Paper> paperArrayListNew = null;
		myUpdateSerFile = new UpdateSerFile(new Paper());
		paperArrayListNew = (ArrayList<Paper>) myUpdateSerFile.deserialize();

		// assertNotNull(paperArrayList);
		// assertNotNull(paperArrayListNew);
		if (paperArrayListNew != null && paperArrayList != null) {
			for (int i = 0; i < paperArrayList.size(); i++) {
				assertEquals(paperArrayList.get(i).getAuthor().getUserName(),
						paperArrayListNew.get(i).getAuthor().getUserName());
				assertEquals(paperArrayList.get(i).getConferenceID(), paperArrayListNew.get(i)
						.getConferenceID());
				assertEquals(paperArrayList.get(i).getFile(), paperArrayListNew.get(i).getFile());
				assertEquals(paperArrayList.get(i).getID(), paperArrayListNew.get(i).getID());
				assertEquals(paperArrayList.get(i).getName(), paperArrayListNew.get(i).getName());
				assertEquals(paperArrayList.get(i).getStatusPaper(), paperArrayListNew.get(i)
						.getStatusPaper());

				// assertNotNull(paperArrayList.get(i).getProgramchair());
				if (paperArrayList.get(i).getProgramchair() != null) {
					assertEquals(paperArrayList.get(i).getProgramchair().getUserName(),
							paperArrayListNew.get(i).getProgramchair().getUserName());
				}

				// assertNotNull(paperArrayList.get(i).getRecommendation());
				if (paperArrayList.get(i).getRecommendation() != null) {
					assertEquals(paperArrayList.get(i).getRecommendation(),
							paperArrayListNew.get(i).getRecommendation());
				}

				// assertNotNull(paperArrayList.get(i).getReview());
				if (paperArrayList.get(i).getReview() != null) {
					assertEquals(paperArrayList.get(i).getReview(), paperArrayListNew.get(i).getReview());
				}

				// assertNotNull(paperArrayList.get(i).getReviewer());
				if (paperArrayList.get(i).getReviewer() != null) {
					assertEquals(paperArrayList.get(i).getReviewer().getUserName(),
							paperArrayListNew.get(i).getReviewer().getUserName());
				}

				// assertNotNull(paperArrayList.get(i).getSubprogramChair());
				if (paperArrayList.get(i).getSubprogramChair() != null) {
					assertEquals(paperArrayList.get(i).getSubprogramChair().getUserName(),
							paperArrayListNew.get(i).getSubprogramChair().getUserName());
				}
			}
		}
	}

	/**
	 * Test method for {@link conference_management.UpdateSerFile#deserialize()}
	 * . Test to deserialize user.ser to see if the new written User ArrayList
	 * is the same as the User ArrayList that we deserialized before. In fact,
	 * this test is to see if writing into user.ser is working properly. The
	 * test checks for the equal User name, user name, and role on each of Users
	 * in both ArrayLists.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public final void testDeserializeUserBeforeAfter() {
		// first fetch
		userArrayList = null;
		myUpdateSerFile = new UpdateSerFile(new User());
		userArrayList = (ArrayList<User>) myUpdateSerFile.deserialize();

		// serialize
		// assertNotNull(userArrayList);
		if (userArrayList != null) {
			myUpdateSerFile = new UpdateSerFile(new User());
			myUpdateSerFile.makeSerialize(userArrayList);
		}

		// second fetch
		ArrayList<User> userArrayListNew = null;
		myUpdateSerFile = new UpdateSerFile(new User());
		userArrayListNew = (ArrayList<User>) myUpdateSerFile.deserialize();

		// assertNotNull(userArrayList);
		// assertNotNull(userArrayListNew);
		if (userArrayListNew != null && userArrayList != null) {
			for (int i = 0; i < userArrayList.size(); i++) {
				assertEquals(userArrayList.get(i).getName(), userArrayListNew.get(i).getName());
				assertEquals(userArrayList.get(i).getUserName(), userArrayListNew.get(i).getUserName());
				assertEquals(userArrayList.get(i).getRole(), userArrayListNew.get(i).getRole());
			}
		}
	}
}