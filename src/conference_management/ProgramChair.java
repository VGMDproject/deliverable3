/**
 * <pre> 
 * Class: <b>ProgramChair</b> 
 * File: ProgramChair.java 
 * Course: TCSS 360 – Spring 2016
 * Copyright 2016 Moe Abdipour, Daniel Bayless, Gabriela Orozco, Vu Hoang
 * </pre>
 */
package conference_management;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <pre>
 * This class is responsible for creating and maintaining a Program
 * 		Chair. A program Chair has the highest access level in a 
 * 		Conference. Program Chair can accept or reject Papers. A
 * 		Program Chair is responsible to assign Papers to Subprogram
 * 		Chair. A Program Chair can manage a Conference. A Program Chair
 * 		is identified by its own unique user name.
 * </pre>
 * 
 * @author Moe Abdipour
 * @author Daniel Bayless
 * @author Gabriela Orozco
 * @author Vu Hoang
 * @version 05/31/2016
 * @since May 10, 2016
 */
public class ProgramChair extends User implements java.io.Serializable {
	/**
	 * The Java auto generated serialization version number
	 */
	private static final long serialVersionUID = -2838033436148976263L;
	
	/**
	 * The current logged in user.
	 */
	private User myUser;

	/**
	 * The current selected Conference
	 */
	private Conference myConference;

	/**
	 * The current selected Conference Index
	 */
	private int conferenceIndex;

	/**
	 * The prompt menu
	 */
	private final String[] myConferenceOptions1 = { "Create a New Conference" };

	/**
	 * The prompt menu
	 */
	private final String[] myConferenceOptions2 = { "View All Papers", "Modify Conference Name",
			"Modify Conference Deadline", "Modify Conference Status", "Create a New Conference" };

	/**
	 * The prompt menu
	 */
	private final String[] myConferenceOptions3 = { "Assign Paper to Subprogram Chair", "View Review",
			"View Recommendation", "Accept/Decline Paper" };

	/**
	 * Default constructor for testing purposes
	 */
	public ProgramChair() {

	}

	/**
	 * This constructor will be used if a new Program Chair object was needed.
	 * 
	 * @param theUser
	 *            The current logged in user
	 * @param theConferenceID
	 *            The current selected Conference
	 */
	public ProgramChair(User theUser, int theConferenceID) {
		super(theUser.getUserName());
		myUser = theUser;
		if (myUser.myConferenceArrayList.size() > 0) {
			conferenceIndex = 0;
			for (Conference localConference : myUser.myConferenceArrayList) {
				if (localConference.getIDConference() == theConferenceID) {
					myConference = localConference;
					break;
				}
				conferenceIndex++;
			}
			promptPC();
		} else {
			promptNewConference();
			promptPC();
		}
	}

	/**
	 * This method takes care of prompting user and perform any action that a
	 * Program Chair is permitted to do. These actions are listed in the option
	 * array instance variables.
	 */
	public void promptPC() {
		System.out.println("\nYou are in Conference: \"" + myConference.getName() + "\"");
		DateFormat deadlineFormat = new SimpleDateFormat("MM/dd/yyyy 'at' hh:mm:ss");
		System.out.println("Conference deadline: " + deadlineFormat.format(myConference.getDeadline()));
		System.out.println("Conference status: " + (myConference.getStatusConference() == 1 ? "Active" : "Not Active"));
		System.out.println(myUser.mySelectPrompt);
		int optionIndex = 0;
		for (String tempString : myConferenceOptions2) {
			System.out.println(++optionIndex + ") " + tempString);
		}

		for (String tempString : myUser.myBackOutOption) {
			System.out.println(++optionIndex + ") " + tempString);
		}

		int selectedOption = Integer.valueOf(myUser.readConsole());

		switch (selectedOption) {
		case 1: // View All Papers
			int paperCounter = 0;
			boolean paperFound = false;
			if (myUser.myPaperArrayList.size() > 0) {
				for (Paper localPaper : myUser.myPaperArrayList) {
					if (localPaper.getConferenceID() == myConference.getIDConference()) {
						paperFound = true;
						break;
					}
				}
			}

			if (paperFound) {
				System.out.println("\nList of all papers in conference \"" + myConference.getName()
				+ "\".\nSelect a paper to go to Paper Management: ");
				for (Paper localPaper : myUser.myPaperArrayList) {
					if (localPaper.getConferenceID() == myConference.getIDConference()) {
						System.out.println(
								++paperCounter + ") " + localPaper.getName() + " by " + localPaper.getUsername());
					}
				}

				int optionSelected = paperCounter;
				for (String tempString : myUser.myBackOutOption) {
					System.out.println(++optionSelected + ") " + tempString);
				}

				int selectedPaperIndex = Integer.valueOf(myUser.readConsole());
				int selectedPaperID = -1;

				if (selectedPaperIndex <= paperCounter) {
					for (Paper localPaper : myUser.myPaperArrayList) {
						if (localPaper.getConferenceID() == myConference.getIDConference()) {
							selectedPaperIndex--;
							if (selectedPaperIndex == 0) {
								selectedPaperID = localPaper.getID();
							}
						}
					}

					Paper selectedPaper = myUser.myPaperArrayList.get(myUser.getPaperIndex(selectedPaperID));
					if (!selectedPaper.getAuthor().getUserName().equals(myUser.getUserName())) {
						promptPaperManagement(selectedPaperID);
					} else {
						System.out.println("As a Program Chair, you cannot modify your own paper. "
								+ "Please log in as an Author.");
						promptPC();
					}
				} else {
					switch (selectedPaperIndex - paperCounter) {
					case 1:
						promptPC();
						break;
					case 2:
						myUser.logout();
						break;
					}
				}
			} else {
				System.out.println("No paper has been submitted yet.");
			}

			promptPC();
			break;
		case 2: // Modify Conference Name
			System.out.println("Choose a name for the conference: ");
			String newName = myUser.readConsole();
			myUser.myConferenceArrayList.get(conferenceIndex).setName(newName);
			myUser.myUpdateSerFileConference.makeSerialize(myUser.myConferenceArrayList);
			promptPC();
			break;
		case 3: // Modify Conference Deadline
			System.out.println("Choose a deadline (MM/DD/YYYY) for the conference: ");
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			Date selectedDate = new Date();
			String selectedDateString = myUser.readConsole();
			try {
				selectedDate = formatter.parse(selectedDateString);
			} catch (ParseException e) {
				System.out.println("Invalid date format.");
			}
			myUser.myConferenceArrayList.get(conferenceIndex).setDeadline(selectedDate);
			myUser.myUpdateSerFileConference.makeSerialize(myUser.myConferenceArrayList);
			promptPC();
			break;
		case 4: // Modify Conference Status
			System.out.println("Choose the status (0:Deactive, 1: Active) of the conference:");
			int newStatus = Integer.valueOf(myUser.readConsole());
			myUser.myConferenceArrayList.get(conferenceIndex).setStatusConference(newStatus);
			myUser.myUpdateSerFileConference.makeSerialize(myUser.myConferenceArrayList);
			promptPC();
			break;
		case 5: // Create New Conference
			createNewConference();
			promptPC();
			break;
		case 6: // back
			myUser.selectRole();
			myUser.prompt();
			break;
		case 7: // log out
			myUser.logout();
			break;
		}
	}

	public void promptPaperManagement(int theSelectedPaperID) {
		Paper selectedPaper = myUser.myPaperArrayList.get(myUser.getPaperIndex(theSelectedPaperID));

		if (selectedPaper.getStatusPaper() == 2) { // 2 is the status when the
			// Author unsubmit the Paper
			System.out.println("\nTHIS PAPER HAS BEEN UNSUBMITTED BY ITS AUTHOR.");
		} else {
			System.out.println();
		}

		System.out.println("You are in Paper: " + selectedPaper.getName());
		System.out.println("Paper Author: " + selectedPaper.getUsername());
		if (selectedPaper.getSubprogramChair() != null && !selectedPaper.getSubprogramChair().getSPCUser().equals("")) {
			System.out.println("Paper Subprogram Chair: " + selectedPaper.getSubprogramChair().getSPCUser());
		} else {
			System.out.println("Paper Subprogram Chair has not been assigned yet.");
		}
		System.out.println("Paper Status: " + (selectedPaper.getStatusPaper() == 1 ? "Accepted" : "Declined"));

		System.out.println(myUser.mySelectPrompt);
		int optionIndex = 0;
		for (String tempString : myConferenceOptions3) {
			System.out.println(++optionIndex + ") " + tempString);
		}

		for (String tempString : myUser.myBackOutOption) {
			System.out.println(++optionIndex + ") " + tempString);
		}

		int selectedOption = Integer.valueOf(myUser.readConsole());

		switch (selectedOption) {
		case 1: // Assign Paper to Subprogram Chair
			System.out.println("\nChoose a user as the Subprogram Chair for this paper: ");
			optionIndex = 0;
			for (User localUser : myUser.getUserList()) {
				System.out.println(++optionIndex + ") " + localUser.getUserName());
			}

			for (String tempString : myUser.myBackOutOption) {
				System.out.println(++optionIndex + ") " + tempString);
			}

			int selectedUserIndex = Integer.valueOf(myUser.readConsole()) - 1;

			if (selectedUserIndex < myUser.getUserList().size()) {
				// check to see if author is the same as the SPC
				if (selectedPaper.getAuthor().getUserName()
						.equals(myUser.myUserArrayList.get(selectedUserIndex).getUserName())) {
					System.out.println("Subprogram Chair cannot be the same as the Author.");
					promptPaperManagement(selectedPaper.getID());
				} else {
					int totalSPCForUser = 0;
					for (Paper localPaper : myUser.myPaperArrayList) {
						if (localPaper.getSubprogramChair() != null
								&& localPaper.getSubprogramChair().getSPCUser()
								.equals(myUser.myUserArrayList.get(selectedUserIndex).getUserName())
								&& localPaper.getConferenceID() == myUser.getSelectedConferenceID()) {
							totalSPCForUser++;
						}
					}

					// check to see if the maximum number of SPC has
					// been assigned to the selected user
					if (totalSPCForUser >= myUser.MAX_SPC) {
						System.out.println("This user has already been assigned the maximum (" + myUser.MAX_SPC
								+ ") possible papers.");
					} else {
						SubprogramChair localSPC = new SubprogramChair(myUser,
								myUser.myUserArrayList.get(selectedUserIndex).getUserName(),
								myUser.getPaperIndex(selectedPaper.getID()));
						myUser.myPaperArrayList.get(myUser.getPaperIndex(selectedPaper.getID()))
						.setSubprogramChair(localSPC);
						myUser.myUserArrayList.get(myUser.getUserIndex(localSPC.getSPCUser())).setSPC(true);
						myUser.myUpdateSerFilePaper.makeSerialize(myUser.myPaperArrayList);
						myUser.myUpdateSerFileUser.makeSerialize(myUser.myUserArrayList);
						System.out.println("The user \"" + myUser.myUserArrayList.get(selectedUserIndex).getUserName()
								+ "\" has been assigned to the paper \"" + selectedPaper.getName() + "\" by \""
								+ selectedPaper.getAuthor().getUsername() + "\" as the Subprogram Chair.");
					}
				}
				promptPaperManagement(theSelectedPaperID);
			} else {
				switch (selectedUserIndex + 1 - myUser.getUserList().size()) {
				case 1: // go back
					promptPaperManagement(theSelectedPaperID);
					break;
				case 2: // log out
					myUser.logout();
					break;
				}
			}
			break;
		case 2: // View Review
			if (selectedPaper.getReview() != null && !selectedPaper.getReview().equals("")) {
				System.out.println("\nReview for the paper \"" + selectedPaper.getName() + "\":");
				File localFile = new File(myUser.REVIEW_FILE_PATH + selectedPaper.getReview());
				String fileContent = myUser.getContent(localFile);
				if (fileContent != null && !fileContent.equals("")) {
					System.out.println(fileContent);
				} else {
					System.out.println("File name: " + myUser.REVIEW_FILE_PATH + selectedPaper.getReview());
				}
			} else {
				System.out.println("No review has been submitted yet.");
			}
			promptPaperManagement(theSelectedPaperID);
			break;
		case 3: // View Recommendation
			if (selectedPaper.getRecommendation() != null && !selectedPaper.getRecommendation().equals("")) {
				System.out.println("\nRecommendation for the paper \"" + selectedPaper.getName() + "\":");
				File localFile = new File(myUser.RECOMMENDATION_FILE_PATH + selectedPaper.getRecommendation());
				String fileContent = myUser.getContent(localFile);
				if (fileContent != null && !fileContent.equals("")) {
					System.out.println(fileContent);
				} else {
					System.out.println(
							"File name: " + myUser.RECOMMENDATION_FILE_PATH + selectedPaper.getRecommendation());
				}
			} else {
				System.out.println("No recommendation has been submitted yet.");
			}
			promptPaperManagement(theSelectedPaperID);
			break;
		case 4: // Accept/Decline Paper
			if (selectedPaper.getAuthor().getUserName().equals(myUser.getUserName())) {
				System.out.println("You cannot accept/decline your own paper.");
			} else {
				System.out.println("\nChoose the status (0:Decline, 1: Accept) of the paper:");
				int selectedStatus = Integer.valueOf(myUser.readConsole());
				myUser.myPaperArrayList.get(myUser.getPaperIndex(selectedPaper.getID())).setStatusPaper(selectedStatus);
				myUser.myUpdateSerFilePaper.makeSerialize(myUser.myPaperArrayList);
				System.out.println("Paper " + selectedPaper.getName() + " has been "
						+ (selectedStatus == 0 ? "declined" : "accepted") + ".");
			}
			promptPaperManagement(theSelectedPaperID);
			break;
		case 5: // go back
			promptPC();
			break;
		case 6: // log out
			myUser.logout();
			break;
		}
	}

	/**
	 * This method takes care of prompting user if a new Conference was needed.
	 */
	public void promptNewConference() {
		System.out.println(mySelectPrompt);
		if (myUser.myConferenceArrayList.size() == 0) {
			int optionIndex = 0;
			for (String tempString : myConferenceOptions1) {
				System.out.println(++optionIndex + ") " + tempString);
			}

			for (String tempString : myUser.myOutOption) {
				System.out.println(++optionIndex + ") " + tempString);
			}

			int selectedIndex = Integer.valueOf(myUser.readConsole());
			switch (selectedIndex) {
			case 1: // Create a New Conference
				createNewConference();
				break;
			case 2: // log out
				myUser.logout();
				break;
			}
		}
	}

	/**
	 * This method creates a new Conference
	 */
	private void createNewConference() {
		System.out.println("Choose a name for the new conference: ");
		String selectedName = myUser.readConsole();
		System.out.println("Choose a deadline (MM/DD/YYYY) for the new conference: ");
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date selectedDate = new Date();
		String selectedDateString = myUser.readConsole();
		try {
			selectedDate = formatter.parse(selectedDateString);
		} catch (ParseException e) {
			System.out.println("Invalid date format.");
		}

		Conference newConference = new Conference();
		newConference.setConferenceID(myUser.myConferenceArrayList.size() + 1);
		newConference.setName(selectedName);
		newConference.setDeadline(selectedDate);
		newConference.setStatusConference(1);
		newConference.setProgramChair(this);
		myUser.setSelectedConferenceID(newConference.getIDConference());
		myUser.myConferenceArrayList.add(newConference);
		myUser.myUpdateSerFileConference.makeSerialize(myUser.myConferenceArrayList);
		System.out.println("Conference '" + newConference.getName() + "' was added.");
		myConference = newConference;
	}
}