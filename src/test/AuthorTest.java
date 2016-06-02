package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import conference_management.Author;
import conference_management.User;

/**
 * JUnit test for the Author class.
 * 
 * @author Gabriela Orozco
 * @version May 29, 2016.
 */
public class AuthorTest {

	/** Field to be able to access the User class for testing. */
	private User myUser;
	/** Object of the Author class. */
	private Author myAuthor;

	/**
	 * Creates the user and author.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		myUser = new User("Gabriela");
		myAuthor = new Author(myUser);
	}

	/**
	 * Testing the Getters and Setters.
	 */
	@Test
	public void testAuthorSetters() {
		String testName = "Gabriela";
		myAuthor.setAuthor(testName);
		assertEquals("Setter for Author failed!", testName, myAuthor.getUsername());

		testName = "Daniel";
		myAuthor.setAuthor(testName);
		assertEquals("Setter for Author failed!", testName, myAuthor.getUsername());

		testName = "Moe";
		myAuthor.setAuthor(testName);
		assertEquals("Setter for Author failed!", testName, myAuthor.getUsername());

		testName = "Vu";
		myAuthor.setAuthor(testName);
		assertEquals("Setter for Author failed!", testName, myAuthor.getUsername());

		testName = "Josh";
		myAuthor.setAuthor(testName);
		assertEquals("Setter for Author failed!", testName, myAuthor.getUsername());

		testName = "Bob";
		myAuthor.setAuthor(testName);
		assertEquals("Setter for Author failed!", testName, myAuthor.getUsername());

		testName = "Luke";
		myAuthor.setAuthor(testName);
		assertEquals("Setter for Author failed!", testName, myAuthor.getUsername());

		testName = "Dave";
		myAuthor.setAuthor(testName);
		assertEquals("Setter for Author failed!", testName, myAuthor.getUsername());
	}
}
