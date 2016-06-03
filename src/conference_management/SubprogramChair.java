/**
 * <pre> 
 * Class: <b>SubprogramChair</b> 
 * File: SubprogramChair.java 
 * Course: TCSS 360 – Spring 2016
 * Copyright 2016 Moe Abdipour, Daniel Bayless, Gabriela Orozco, Vu Hoang
 * </pre>
 */
package conference_management;

/**
 * <pre>
 * This class is responsible for creating and maintaining a Subprogram
 * 		Chair. A Subprogram Chair has the highest access level in a 
 * 		Conference after Program Chair. A Subprogram Chair is responsible
 * 		for the assigned Papers. A Program Chair assigns Papers to 
 * 		Subprogram Chair. No Subprogram Chair is responsible to manage
 * 		more than a specific number of Papers in each Conference. This
 * 		specific number is stored in User class. A Subprogram Chair is
 * 		also responsible to assign Papers to Reviewers. A Subprogram
 * 		Chair is able to submit recommendation for the assigned Paper to
 * 		the Program Chair. A Subprogram Chair is identified by its own 
 * 		unique user name.
 * </pre>
 * 
 * @author Moe Abdipour
 * @author Daniel Bayless
 * @author Gabriela Orozco
 * @author Vu Hoang
 * @version 05/31/2016
 * @since May 10, 2016
 */
public class SubprogramChair extends User implements java.io.Serializable {
	/**
	 * The Java auto generated serialization version number
	 */
	private static final long serialVersionUID = 8515150283652510510L;

	/**
	 * The current logged in user.
	 */
	private User myUser;

	/**
	 * An instance of Subprogram Chair
	 */
	private User mySPCUser;

	/**
	 * The prompt menu
	 */
	private final String[] mySPCOptions1 = { "Assign a Reviewer", "Submit Recommendation" };

	/**
	 * Default constructor for testing purposes
	 */
	public SubprogramChair() {

	}

	/**
	 * This constructor will be used if a new Subprogram Chair object was
	 * needed.
	 * 
	 * @param theUser
	 *            The current logged in user
	 * @param theConferenceID
	 *            The current selected Conference
	 */
	public SubprogramChair(User theUser, int theConferenceID) {
		super(theUser.getUserName());
		myUser = theUser;

		if (myUser.myConferenceArrayList.size() > 0) {
			promptSPC();
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
	public SubprogramChair(User theUser, String theUserName, int thePaperIndex) {
		myUser = theUser;
		for (User localUser : myUser.myUserArrayList) {
			if (localUser.getUserName().equals(theUserName)) {
				mySPCUser = localUser;
				break;
			}
		}
	}

	/**
	 * This method returns the current logged in user name
	 * 
	 * @return the logged in user name
	 */
	public String getSPCUser() {
		return mySPCUser.getUserName();
	}

	/**
	 * Sets the current Subprogram Chair
	 * 
	 * @param theSPC
	 *            The current Subprogram Chair to be set
	 */
	public void setSPCUser(final User theSPC) {
		mySPCUser = theSPC;
	}

	/**
	 * This method takes care of prompting user and perform any action that a
	 * Subprogram Chair is permitted to do. These actions are listed in the
	 * option array instance variables.
	 */
	public void promptSPC() {
		int optionIndex = 0;
		System.out.println("\nSelect a paper to go to Paper Management: ");
		for (Paper localPaper : myUser.myPaperArrayList) {
			if (localPaper.getSubprogramChair() != null
					&& localPaper.getSubprogramChair().getSPCUser().equals(myUser.getUserName())
					&& localPaper.getConferenceID() == myUser.getSelectedConferenceID()) {
				System.out.println(++optionIndex + ") " + localPaper.getName() + " by "
						+ localPaper.getAuthor().getUserName());
			}
		}

		int paperCounter = optionIndex;

		if (paperCounter > 0) {
			for (String tempString : myUser.myBackOutOption) {
				System.out.println(++optionIndex + ") " + tempString);
			}

			int selectedPaperIndex = Integer.valueOf(myUser.readConsole());

			if (selectedPaperIndex <= paperCounter) {
				int selectedPaperID = -1;
				for (Paper localPaper : myUser.myPaperArrayList) {
					if (localPaper.getSubprogramChair() != null
							&& localPaper.getSubprogramChair().getSPCUser().equals(myUser.getUserName())
							&& localPaper.getConferenceID() == myUser.getSelectedConferenceID()) {
						selectedPaperIndex--;
						if (selectedPaperIndex == 0) {
							selectedPaperID = localPaper.getID();
						}
					}
				}

				promptPaperManagement(selectedPaperID);
			} else {
				switch (selectedPaperIndex - paperCounter) {
				case 1:
					myUser.selectRole();
					myUser.prompt();
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

	public void promptPaperManagement(int theSelectedPaperID) {
		int selectedPaperID = theSelectedPaperID;
		int selectedPaperIndex = myUser.getPaperIndex(theSelectedPaperID);
		int optionIndex = 0;
		System.out.println(myUser.mySelectPrompt);
		for (String tempString : mySPCOptions1) {
			System.out.println(++optionIndex + ") " + tempString);
		}

		for (String tempString : myUser.myBackOutOption) {
			System.out.println(++optionIndex + ") " + tempString);
		}
		
		int selectedOption = Integer.valueOf(myUser.readConsole());

		switch (selectedOption) {
		case 1: // Assign a Reviewer
			System.out.println("\nChoose a user as the Reviewer for this paper: ");
			optionIndex = 0;
			for (User localUser : myUser.getUserList()) {
				System.out.println(++optionIndex + ") " + localUser.getUserName());
			}

			for (String tempString : myUser.myBackOutOption) {
				System.out.println(++optionIndex + ") " + tempString);
			}

			int selectedUserIndex = Integer.valueOf(myUser.readConsole()) - 1;

			if (selectedUserIndex < myUser.getUserList().size()) {
				// check to see if author is the same as the reviewer
				if (myUser.myPaperArrayList.get(selectedPaperIndex).getAuthor().getUsername()
						.equals(myUser.myUserArrayList.get(selectedUserIndex).getUserName())) {
					System.out.println("Reviewer cannot be the same as the Author.");
					promptPaperManagement(selectedPaperID);
				} else {
					int totalReviewForSelectedUser = 0;
					for (Paper localPaper : myUser.myPaperArrayList) {
						if (localPaper.getReviewer() != null
								&& localPaper.getReviewer().getReviewerUser()
								.equals(myUser.myUserArrayList.get(selectedUserIndex).getUserName())
								&& localPaper.getConferenceID() == myUser.getSelectedConferenceID()) {
							totalReviewForSelectedUser++;
						}
					}

					Paper selectedPaper = myUser.myPaperArrayList.get(myUser.getPaperIndex(selectedPaperID));
					// check to see if the maximum number of review has been
					// assigned to the selected user
					if (totalReviewForSelectedUser >= myUser.MAX_REVIEW) {
						System.out.println("This user has already been assigned the maximum (" + myUser.MAX_REVIEW
								+ ") possible papers.");
					} else {
						Reviewer localReviewer = new Reviewer(myUser,
								myUser.myUserArrayList.get(selectedUserIndex).getUserName(),
								myUser.getPaperIndex(selectedPaper.getID()));
						myUser.myPaperArrayList.get(myUser.getPaperIndex(selectedPaperID)).setReviewer(localReviewer);
						myUser.myUserArrayList.get(myUser.getUserIndex(localReviewer.getReviewerUser()))
						.setReviewer(true);
						myUser.myUpdateSerFilePaper.makeSerialize(myUser.myPaperArrayList);
						myUser.myUpdateSerFileUser.makeSerialize(myUser.myUserArrayList);
						System.out
						.println("The user " + myUser.myUserArrayList.get(selectedUserIndex).getUserName()
								+ " has been assigned to the paper \""
								+ myUser.myPaperArrayList.get(myUser.getPaperIndex(selectedPaperID)).getName()
								+ "\" by \"" + myUser.myPaperArrayList
								.get(myUser.getPaperIndex(selectedPaperID)).getAuthor().getUsername()
								+ "\" as the Reviewer.");
						promptPaperManagement(selectedPaperID);
					}
				}
				break;
			} else {
				System.out.println(selectedUserIndex + 1 - myUser.getUserList().size());
				switch (selectedUserIndex + 1 - myUser.getUserList().size()) {
				case 1: // go back
					promptPaperManagement(selectedPaperID);
					break;
				case 2: // log out
					myUser.logout();
					break;
				}
			}
		case 2: // Submit Recommendation
			System.out.println("Select the file for the new recommendation for the paper \""
					+ myUser.myPaperArrayList.get(myUser.getPaperIndex(selectedPaperID)).getName() + "\": ");
			myUser.myPaperArrayList.get(myUser.getPaperIndex(selectedPaperID)).submitRecommendation(
					myUser.chooseFile(myUser.getUserName() + "_" + selectedPaperID, myUser.RECOMMENDATION_FILE_PATH));
			myUser.myUpdateSerFilePaper.makeSerialize(myUser.myPaperArrayList);
			System.out.println("The recommendation was submitted.");
			promptPaperManagement(selectedPaperID);
			break;
		case 3: // back
			promptSPC();
			break;
		case 4: // log out
			myUser.logout();
			break;
		}
	}
}