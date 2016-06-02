package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import conference_management.Paper;
import conference_management.Reviewer;
import conference_management.User;

/**
 * Test Class for Reviewer. Two test case. 1. Constructor testing. 2. get
 * Reviewer User Name.
 * 
 * @author Daniel Bayless
 *
 */
public class ReviewerTest {
	private Reviewer testRev;
	private String userName;
	private User testUser;
	private Paper testPaper;

	/**
	 * Initializing different variables that will use thought out the Testing.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		userName = "TestUser";
		int paperID = 1;
		int ConfID = 1;

		testUser = new User(userName);
		testUser.init();
		testUser.myUserArrayList.add(testUser);
		testPaper = new Paper(testUser, paperID, ConfID);
		testRev = new Reviewer(testUser, testUser.getUserName(), testPaper.getIndexPaper(paperID));
	}

	/**
	 * Testing Constructor.
	 */
	@Test
	public void testReviewerUserStringInt() {
		testRev = new Reviewer(testUser, testUser.getUserName(), testPaper.getIndexPaper(1));
		assertEquals(testUser.getUserName(), testRev.getReviewerUser());
	}

	/**
	 * Testing get Reviewer User Test if this method returns right Reviewer User
	 * name.
	 */
	@Test
	public void testGetReviewerUser() {
		assertEquals("Fail get Reviewer User ", userName, testRev.getReviewerUser());
	}
}