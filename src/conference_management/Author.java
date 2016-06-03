/**
 * <pre> 
 * Class: <b>Author</b> 
 * File: Author.java 
 * Course: TCSS 360 – Spring 2016
 * Copyright 2016 Moe Abdipour, Daniel Bayless, Gabriela Orozco, Vu Hoang
 * </pre>
 */
package conference_management;

import java.io.File;
import java.util.Date;

/**
 * <pre>
 * This class is responsible for creating and maintaining an Author. A
 * 		User becomes an Author by submitting a Paper to a Conference.
 * 		A Paper is identified by its own unique ID.
 * </pre>
 * 
 * @author Moe Abdipour
 * @author Daniel Bayless
 * @author Gabriela Orozco
 * @author Vu Hoang
 * @version 05/31/2016
 * @since May 10, 2016
 */
public class Author extends User implements java.io.Serializable {
	/**
	 * The Java auto generated serialization version number
	 */
	private static final long serialVersionUID = -4834438673324102559L;

	/**
	 * The current logged in user.
	 */
	private User myUser;

	/**
	 * The current logged in user name
	 */
	private String myUsername;

	/**
	 * The prompt menu
	 */
	private final String[] mySubmitOption = { "Submit a Paper" };

	/**
	 * The prompt menu
	 */
	private final String[] myModifyOption = { "Modify the Paper", "Unsubmit the Paper",
	"View the Review for the Paper" };

	/**
	 * This constructor will be used if a new Author object was needed. It also
	 * calls the super class with the current logged in user name
	 * 
	 * @param theUser
	 *            The current logged in user
	 */
	public Author(User theUser) {
		super(theUser.getUserName());
		myUser = theUser;
		myUsername = myUser.getUserName();

	}

	/**
	 * This method takes care of prompting user and perform any action that an
	 * Author is permitted to do. These actions are listed in the option array
	 * instance variables.
	 */
	public void promptAuthor() {
		int menuIndex = 0;
		boolean paperFound = false;
		for (Paper tempPaper : myUser.myPaperArrayList) {
			if (tempPaper.getUsername().equals(myUser.getUserName())
					&& tempPaper.getConferenceID() == myUser.getSelectedConferenceID()
					&& tempPaper.getStatusPaper() == 1) {
				paperFound = true;

				System.out.println(myUser.mySelectPrompt);
				for (String menuOption : myModifyOption) {
					System.out.println(++menuIndex + ") " + menuOption);
				}

				for (String menuOption : myUser.myBackOutOption) {
					System.out.println(++menuIndex + ") " + menuOption);
				}

				int selectedOption = Integer.valueOf(myUser.readConsole());

				switch (selectedOption) {
				case 1: // Modify the Paper
					Date now = new Date();
					if (myUser.getConferenceList().get(myUser.getConferenceIndex(myUser
							.getSelectedConferenceID()))
							.getDeadline().compareTo(now) > 0) {
						System.out.println("\nEnter the new name: ");
						myUser.getPaperList().get(tempPaper.getIndexPaper(tempPaper.getID()))
						.setName(myUser.readConsole());
						myUser.myUpdateSerFilePaper.makeSerialize(myUser.myPaperArrayList);
						System.out.println("Paper \"" + tempPaper.getName() + "\" was changed.");
						promptAuthor();
					} else {
						System.out.println("Deadline has passed. You cannot make changes at this moment.");
						System.out.println("For issues regarding your submission please contact conference management.");
						myUser.selectRole();
						myUser.prompt();
					}
					break;
				case 2: // Unsubmit the Paper
					now = new Date();
					if (myUser.getConferenceList().get(myUser.getConferenceIndex(myUser
							.getSelectedConferenceID()))
							.getDeadline().compareTo(now) > 0) {
						myUser.getPaperList().get(tempPaper.getIndexPaper(tempPaper.getID()))
						.setStatusPaper(2);
						myUser.myUpdateSerFilePaper.makeSerialize(myUser.myPaperArrayList);
						System.out.println("Paper \"" + tempPaper.getName() + "\" was unsubmitted.");
						promptAuthor();
					} else {
						System.out.println("Deadline has passed. You cannot make changes at this moment.");
						System.out.println("For issues regarding your submission please contact conference management.");
						myUser.selectRole();
						myUser.prompt();
					}
					break;
				case 3: // View the Review for the Paper
					if (tempPaper.getReview() != null && !tempPaper.getReviewer().equals("")) {
						System.out.println("\nReview for the paper \"" + tempPaper.getName() + "\":");
						File localFile = new File(myUser.REVIEW_FILE_PATH + tempPaper.getReview());
						String fileContent = myUser.getContent(localFile);
						if (fileContent != null && !fileContent.equals("")) {
							System.out.println(fileContent);
						} else {
							System.out.println("Unable to read the content. Please contact the conference management.");
						}
					} else {
						System.out.println("No review has been submitted yet.");
					}
					promptAuthor();
					break;
				case 4: // back
					myUser.selectRole();
					myUser.prompt();
					break;
				case 5: // log out
					myUser.logout();
					break;
				}
				break;
			}
		}

		if (!paperFound) {
			// check to see if deadline has already passed
			Date now = new Date();
			if (myUser.getConferenceList().get(myUser.getConferenceIndex(myUser
					.getSelectedConferenceID()))
					.getDeadline().compareTo(now) > 0) {
				menuIndex = 0;
				System.out.println(myUser.mySelectPrompt);
				for (String menuOption : mySubmitOption) {
					System.out.println(++menuIndex + ") " + menuOption);
				}
				for (String menuOption : myUser.myBackOutOption) {
					System.out.println(++menuIndex + ") " + menuOption);
				}
				int selectedOption = Integer.valueOf(myUser.readConsole());
				switch (selectedOption) {
				case 1:

					Paper newPaper = new Paper(myUser, myUser.getPaperList().size() + 1,
							myUser.getSelectedConferenceID());
					System.out.println("\nEnter the name for the new paper: ");
					newPaper.setAuthor(this);
					newPaper.setName(myUser.readConsole());
					newPaper.setStatusPaper(1);
					System.out.println("\nSelect the file for the new paper: ");
					newPaper.setFile(
							myUser.chooseFile(newPaper.getAuthor().getUserName() + "_" 
									+ newPaper.getConferenceID(),
									myUser.PAPER_FILE_PATH));
					myUser.myPaperArrayList.add(newPaper);
					myUser.myUpdateSerFilePaper.makeSerialize(myUser.myPaperArrayList);
					System.out.println("New paper \"" + newPaper.getName() + "\" was submitted.");
					myUser.selectRole();
					myUser.prompt();
					break;
				case 2:
					myUser.selectRole();
					myUser.prompt();
					break;

				case 3:
					myUser.logout();
					break;
				}
			} else {
				System.out.println("Deadline has passed.");
				myUser.selectRole();
				myUser.prompt();
			}
		}
	}
	
	/**
	 * This method returns the current logged in user name
	 * 
	 * @return the logged in user name
	 */
	public String getUsername() {
		return myUsername;
	}

	/**
	 * Sets the user name of the Author.
	 * 
	 * @param theAuthor
	 *            String for the user name of the author
	 */
	public void setAuthor(final String theAuthorUsername) {
		myUsername = theAuthorUsername;
	}
}