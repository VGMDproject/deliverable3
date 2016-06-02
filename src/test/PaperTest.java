package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import conference_management.Author;
import conference_management.Conference;
import conference_management.Paper;
import conference_management.ProgramChair;
import conference_management.Reviewer;
import conference_management.SubprogramChair;
import conference_management.User;

/**
 * Test Class that hold all the test case for Paper Class.
 * 
 * @author Daniel Bayless
 *
 */
public class PaperTest {

	private User testUser1;
	private Paper testPaper1;
	private Paper testPaperWithConst;
	private Conference testConf;
	private int testConfID;
	private ArrayList<Paper> paperList;
	private ProgramChair testPC;
	private SubprogramChair testSPC;
	private Reviewer testRev;
	private Author testAut;

	/**
	 * Initialize Object or variable that will use through out the Test.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		testConfID = 1;
		testUser1 = new User("testUser1");
		testUser1.init();
		testConf = new Conference(testConfID);
		testUser1.myConferenceArrayList.add(testConf); // new Conference
		testPaper1 = new Paper();
		paperList = testUser1.getPaperList();
	}

	/**
	 * test setter for PC.
	 */
	@Test
	public void testSetProgramChair() {
		ProgramChair testPC = new ProgramChair();
		testPaper1.setProgramchair(testPC);
		assertTrue(testPaper1.getProgramchair().equals(testPC));
	}

	/**
	 * testing setter for SPC.
	 */
	@Test
	public void testSetSubprogramChair() {
		SubprogramChair testSPC = new SubprogramChair();
		testPaper1.setSubprogramChair(testSPC);
		assertTrue(testPaper1.getSubprogramChair().equals(testSPC));
	}

	/**
	 * testing setter for Reviewer.
	 */
	@Test
	public void testSetReviewer() {
		Reviewer testRev = new Reviewer();
		testPaper1.setReviewer(testRev);
		assertTrue(testPaper1.getReviewer().equals(testRev));
	}

	/**
	 * testing setter for Reviewer.
	 */
	@Test
	public void testSetAuthor() {
		Author aut = new Author(testUser1);
		testPaper1.setAuthor(aut);
		assertTrue(testPaper1.getAuthor().equals(aut));
	}

	/**
	 * testing Paper Constructor.
	 */
	@Test
	public void testPaperConstructor() {
		String name = "User1";
		testUser1 = new User(name);
		testUser1.init();
		int paperID = 1;
		int ConfID = 1;

		testPaperWithConst = new Paper(testUser1, paperID, ConfID);
		assertEquals("constructor fail to set Name ", name, testPaperWithConst.getUsername());
		assertEquals("constructor fail to set Paper ID ", paperID, testPaperWithConst.getID());
		assertEquals("constructor fail to set Conference ID ", ConfID, testPaperWithConst.getConferenceID());
	}

	/**
	 * testing setting paper Name with Null.
	 */
	public void testSetName_NULL() {
		fail("Should throw NullPointerException...");
		testPaper1.setName(null);
		assertNull(testPaper1.getName());
	}

	/**
	 * testing setting paper Name with String.
	 */
	@Test
	public void testSetName() {
		String paperName = "testPaper";
		testPaper1.setName(paperName);
		assertEquals(paperName, testPaper1.getName());
	}

	/**
	 * Testing setting conference for this Paper.
	 */
	@Test
	public void testSetConference() {
		int testInt = 1;
		testPaper1.setConference(testInt);
		int testPaperId = testPaper1.getConferenceID();
		assertEquals("Conference ID didn't match", testPaperId, testInt);
	}

	/**
	 * Testing setting Paper Status (Reject/Accept)
	 */
	@Test
	public void testsetStatusPaper() {
		int testInt = 1;
		testPaper1.setStatusPaper(testInt);
		assertEquals(testInt, testPaper1.getStatusPaper());
	}

	/**
	 * Testing saving name of the file.
	 */
	@Test
	public void testSetFile() {
		String testStr = "paperFile.txt";
		testPaper1.setFile(testStr);
		assertEquals(testStr, testPaper1.getFile());
	}

	/*
	 * Testing setting paper ID.
	 */
	@Test
	public void testSetID() {
		int id = 1;
		testPaper1.setID(id);
		assertEquals(id, testPaper1.getID());
	}

	// getters

	/**
	 * Get User name for this paper with constructor.
	 */
	@Test
	public void testGetUserName_withConstructor() {
		String name = "User1";
		testUser1 = new User(name);
		testUser1.init();
		int paperID = 1;
		int ConfID = 1;

		testPaperWithConst = new Paper(testUser1, paperID, ConfID);
		assertEquals(name, testPaperWithConst.getUsername());
	}

	/*
	 * testing getPaperIndex with New Paper Since we add new Paper to List Paper
	 * should in last index.
	 */
	@Test
	public void testGetIndexPaper_addNewElement() {
		testPaper1.setID(99);
		paperList.add(testPaper1);
		int lastIndex = paperList.size() - 1;
		assertEquals(lastIndex, testUser1.getPaperIndex(99));
	}

	/**
	 * testing for getting PC for this Paper.
	 */
	@Test
	public void testGetProgramchair() {
		testPC = new ProgramChair();
		testPaper1.setProgramchair(testPC);
		assertTrue(testPaper1.getProgramchair().equals(testPC));
	}

	/**
	 * testing for getting SPC for this Paper.
	 */
	@Test
	public void testGetSubprogramChair() {
		testSPC = new SubprogramChair();
		testPaper1.setSubprogramChair(testSPC);
		assertTrue(testPaper1.getSubprogramChair().equals(testSPC));
	}

	/**
	 * testing for getting Reviewer for this Paper.
	 */
	@Test
	public void testGetReviewer() {
		testRev = new Reviewer();
		testPaper1.setReviewer(testRev);
		assertTrue(testPaper1.getReviewer().equals(testRev));
	}

	/**
	 * testing for getting Author for this Paper.
	 */
	@Test
	public void testGetAuthor() {
		testAut = new Author(testUser1);
		testPaper1.setAuthor(testAut);
		assertTrue(testPaper1.getAuthor().equals(testAut));
	}

	/**
	 * testing for getting Paper Name for this Paper.
	 */
	@Test
	public void testGetName() {
		testPaper1.setName("paper1");
		assertEquals("paper1", testPaper1.getName());
	}

	/**
	 * testing for getting ConferenceID for this Paper.
	 */
	@Test
	public void testGetConferenceID() {
		testPaper1.setConference(1);
		assertEquals(1, testPaper1.getConferenceID());
	}

	/**
	 * testing for getting Paper status for this Paper.
	 */
	@Test
	public void testGetStatusPaper() {
		testPaper1.setStatusPaper(1);
		assertEquals(1, testPaper1.getStatusPaper());
	}

	/**
	 * testing for getting Submitted Review for this Paper.
	 */
	@Test
	public void testGetReview() {
		testPaper1.submitReview("Good Paper!!");
		assertEquals("Good Paper!!", testPaper1.getReview());
	}

	/**
	 * testing for getting Recommendation for this Paper.
	 */
	@Test
	public void testGetRecommendation() {
		testPaper1.submitRecommendation("As a SPC, Good Paper!!!");
		assertEquals("As a SPC, Good Paper!!!", testPaper1.getRecommendation());
	}

	/**
	 * testing for getting Name of the testFile for this Paper.
	 */
	@Test
	public void testGetFile() {
		testPaper1.setFile("User1Paper.txt"); // Name of the file
		assertEquals("User1Paper.txt", testPaper1.getFile());
	}

	/**
	 * testing for getting User name for this Paper.
	 */
	@Test
	public void testGetUsername() {
		testUser1.setUserName("TestUser");
		testPaper1 = new Paper(testUser1, testPaper1.getID(), testPaper1.getConferenceID());
		assertEquals(testPaper1.getUsername(), "TestUser");
	}
}