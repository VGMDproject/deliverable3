/**
 * <pre> 
 * Class: <b>Reviewer</b> 
 * File: Reviewer.java 
 * Course: TCSS 360 – Spring 2016
 * Copyright 2016 Moe Abdipour, Daniel Bayless, Gabriela Orozco, Vu Hoang
 * </pre>
 */
package conference_management;

/**
 * <pre>
 * This class is responsible for creating and maintaining a Reviewer.
 * 		A Reviewer is responsible to review the Papers that is assigned
 * 		to it. The review will be in form of a file uploaded into the
 * 		specific path. The path to the file is stored in User class.
 *		A Reviewer is identified by its own unique user name.
 * </pre>
 * 
 * @author Moe Abdipour
 * @author Daniel Bayless
 * @author Gabriela Orozco
 * @author Vu Hoang
 * @version 05/31/2016
 * @since May 10, 2016
 */
public class Reviewer extends User implements java.io.Serializable {
	/**
	 * The Java auto generated serialization version number
	 */
	private static final long serialVersionUID = 8269419358241983344L;
	
	/**
	 * The current logged in user.
	 */
	private User myUser;

	/**
	 * An instance of a Reviewer user.
	 */
	private User myReviewerUser;

	/**
	 * The prompt menu
	 */
	private final String[] myReviewerOptions1 = { "Submit Review" };

	/**
	 * Default constructor for testing purposes
	 */
	public Reviewer() {

	}

	/**
	 * This constructor will be used if a new Reviewer object was needed.
	 * 
	 * @param theUser
	 *            The current logged in user
	 * @param theConferenceID
	 *            The current selected Conference
	 */
	public Reviewer(User theUser, int theConferenceID) {
		super(theUser.getUserName());
		myUser = theUser;

		if (myUser.myConferenceArrayList.size() > 0) {
			promptReviewer();
		} else {
			System.out.println("No conference has beet created yet.");
			myUser.prompt();
		}
	}

	/**
	 * This constructor will be used if a new Subprogram Chair object was
	 * needed.
	 * 
	 * @param theUser
	 *            The current logged in user
	 * @param theUserName
	 *            The user name of the current logged in user
	 * @param thePaperIndex
	 *            The current selected Paper to be assigned
	 */
	public Reviewer(User theUser, String theUserName, int thePaperIndex) {
		myUser = theUser;
		for (User localUser : myUser.myUserArrayList) {
			if (localUser.getUserName().equals(theUserName)) {
				myReviewerUser = localUser;
				break;
			}
		}
	}

	/**
	 * This method returns the current logged in user name
	 * 
	 * @return the logged in user name
	 */
	public String getReviewerUser() {
		return myReviewerUser.getUserName();
	}

	/**
	 * This method takes care of prompting user and perform any action that a
	 * Reviewer is permitted to do. These actions are listed in the option array
	 * instance variables.
	 */
	public void promptReviewer() {
		int paperCounter = 0;
		System.out.println("\nSelect a paper to add review: ");
		for (Paper localPaper : myUser.myPaperArrayList) {
			if (localPaper.getReviewer() != null
					&& localPaper.getReviewer().getReviewerUser().equals(myUser.getUserName())
					&& localPaper.getConferenceID() == myUser.getSelectedConferenceID()) {
				System.out.println(
						++paperCounter + ") " + localPaper.getName() + " by " + localPaper.getAuthor()
						.getUserName());
			}
		}

		int optionIndex = paperCounter;
		for (String tempString : myUser.myBackOutOption) {
			System.out.println(++optionIndex + ") " + tempString);
		}

		if (paperCounter > 0) {
			int selectedPaperIndex = Integer.valueOf(myUser.readConsole());
			if (selectedPaperIndex <= paperCounter) {
				int selectedPaperID = -1;
				for (Paper localPaper : myUser.myPaperArrayList) {
					if (localPaper.getReviewer() != null
							&& localPaper.getReviewer().getReviewerUser().equals(myUser.getUserName())) {
						selectedPaperIndex--;
						if (selectedPaperIndex == 0) {
							selectedPaperID = localPaper.getID();
						}
					}
				}

				System.out.println(myUser.mySelectPrompt);
				paperCounter = 0;
				for (String tempString : myReviewerOptions1) {
					System.out.println(++paperCounter + ") " + tempString);
				}

				for (String tempString : myUser.myBackOutOption) {
					System.out.println(++paperCounter + ") " + tempString);
				}

				int selectedOption = Integer.valueOf(myUser.readConsole());
				switch (selectedOption) {
				case 1: // Add a review
					System.out.println("Add review for the paper "
							+ myUser.myPaperArrayList.get(myUser.getPaperIndex(selectedPaperID))
							.getName() + ": ");

					System.out.println("Select the file for the new review for the paper "
							+ myUser.myPaperArrayList.get(myUser.getPaperIndex(selectedPaperID))
							.getName() + ": ");
					myUser.myPaperArrayList.get(myUser.getPaperIndex(selectedPaperID)).submitReview(
							myUser.chooseFile(myUser.getUserName() + "_" + selectedPaperID, myUser
									.REVIEW_FILE_PATH));

					myUser.myUpdateSerFilePaper.makeSerialize(myUser.myPaperArrayList);
					System.out.println("The review was submitted.");
					promptReviewer();
					break;
				case 2: // back
					promptReviewer();
					break;
				case 3: // log out
					myUser.logout();
					break;
				}
			} else {
				switch (selectedPaperIndex - paperCounter) {
				case 1:
					myUser.selectRole();
					break;
				case 2:
					myUser.logout();
					break;
				}
			}
		} else {
			System.out.println("No paper has been assigned to you in this conference.");
			myUser.selectRole();
			myUser.prompt();
		}
	}
}