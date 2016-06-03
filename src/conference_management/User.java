/**
 * <pre> 
 * Class: <b>User</b> 
 * File: User.java 
 * Course: TCSS 360 – Spring 2016
 * Copyright 2016 Moe Abdipour, Daniel Bayless, Gabriela Orozco, Vu Hoang
 * </pre>
 */
package conference_management;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;

/**
 * <pre>
 * This class is responsible for creating and maintaining a User. All
 * 		of the actions in this program are performed by Users. All other
 * 		users in this program with all types of access levels inherit 
 * 		this User class. The User class delivers all data that each other 
 * 		users need to perform their jobs. The most important data that 
 * 		User class retains is User, Paper, and Conference ArrayLists which
 * 		hold all the information required to run the program. The most
 * 		important method that the User class call is makeSerialize and 
 * 		deserialize which are calls to serialize data into external .ser
 * 		files and retrieve data from external .ser files. The .ser files
 * 		are located in root. Each User is identified by its own	unique 
 * 		user name.
 * </pre>
 * 
 * @author Moe Abdipour
 * @author Daniel Bayless
 * @author Gabriela Orozco
 * @author Vu Hoang
 * @version 05/31/2016
 * @since May 10, 2016
 */
public class User implements java.io.Serializable {
	/**
	 * The Java auto generated serialization version number
	 */
	private static final long serialVersionUID = 518192311986499400L;

	/**
	 * The path to the directory that holds the Paper files
	 */
	public final String PAPER_FILE_PATH = "paper\\";

	/**
	 * The path to the directory that holds the Review files
	 */
	public final String REVIEW_FILE_PATH = "review\\";
	
	/**
	 * The list of accepted file extensions. For various reasons, the
	 * 		program will not display the contents of any file with other
	 * 		extensions. In those cases, the file name will be displayed
	 * 		to the user.
	 */
	public final String[] myAcceptableFileExtensions = { "txt" };

	/**
	 * The path to the directory that holds the Recommendation files
	 */
	public final String RECOMMENDATION_FILE_PATH = "recommendation\\";

	/**
	 * The User role
	 */
	public final String USER = "User";

	/**
	 * The Author role
	 */
	public final String AUTHOR = "Author";

	/**
	 * The Reviewer role
	 */
	public final String REVIEWER = "Reviewer";

	/**
	 * The Subprogram Chair role
	 */
	public final String SPC = "Subprogram Chair";

	/**
	 * The Program Chair role
	 */
	public final String PC = "Program Chair";

	/**
	 * The default role assigned to the user as the user logs in. The default
	 * value is USER.
	 */
	public final String DEFAULT_ROLE = USER;

	/**
	 * The prompt menu
	 */
	public final String[] mySubmitBackOutOption = { "Submit a Paper", "Back", "Log Out" };

	/**
	 * The prompt menu
	 */
	public final String[] myOutOption = { "Log Out" };

	/**
	 * The prompt menu
	 */
	public final String[] myBackOutOption = { "Back", "Log Out" };

	/**
	 * The generic prompt when the user needs to select from different options
	 */
	public final String mySelectPrompt = "\nSelect from the following options:";

	/**
	 * The maximum number of Papers a Reviewer can take
	 */
	public final int MAX_REVIEW = 4;

	/**
	 * The maximum number of Papers a Subprogram Chair can take
	 */
	public final int MAX_SPC = 4;

	/**
	 * The ArrayList of all roles that the current logged user has.
	 */
	private ArrayList<String> roleArrayList;

	/**
	 * The ArrayList of all Conference objects
	 */
	public ArrayList<Conference> myConferenceArrayList;

	/**
	 * The ArrayList of all User objects
	 */
	public ArrayList<User> myUserArrayList;

	/**
	 * The ArrayList of all Paper objects
	 */
	public ArrayList<Paper> myPaperArrayList;

	/**
	 * An instance of UpdateSerFile responsible for updating User objects data
	 */
	public UpdateSerFile myUpdateSerFileUser;

	/**
	 * An instance of UpdateSerFile responsible for updating Paper objects data
	 */
	public UpdateSerFile myUpdateSerFilePaper;

	/**
	 * An instance of UpdateSerFile responsible for updating Conference objects
	 * data
	 */
	public UpdateSerFile myUpdateSerFileConference;

	/**
	 * An instance of Paper class that is used to send to the UpdateSerFile
	 * class to identify the type of data that is passed in.
	 */
	private Paper myPaper;

	/**
	 * An instance of Conference class that is used to send to the UpdateSerFile
	 * class to identify the type of data that is passed in.
	 */
	private Conference myConference;

	/**
	 * The name of the current logged in user
	 */
	private String myName;

	/**
	 * The role of the current logged in user
	 */
	private String myRole;

	/**
	 * This variable will be set to true if the current logged in user is a
	 * Program Chair
	 */
	private boolean myIsPC;

	/**
	 * This variable will be set to true if the current logged in user is a
	 * Subprogram Chair
	 */
	private boolean myIsSPC;

	/**
	 * This variable will be set to true if the current logged in user is a
	 * Reviewer
	 */
	private boolean myIsReviewer;

	/**
	 * This variable will be set to true if the current logged in user is an
	 * Author
	 */
	private boolean myIsAuthor;

	/**
	 * This variable will be set to true if the current logged in user is a
	 * User. All users are User by default. User has the lowest access level but
	 * it can be changed if it was needed in future. For example if a new access
	 * level lower than User was created, then the default access level could be
	 * set at the new access level and User will be higher than the new access
	 * level.
	 */
	private boolean myIsUser;

	/**
	 * This only Scanner used in this program. Please do not make more Scanner
	 * objects. This program does not close the Scanner, since the program is
	 * always asking for new user inputs. In case of implementing a GUI, make
	 * sure that you close the Scanner.
	 */
	private transient Scanner input;

	/**
	 * The user name of the current logged in user.
	 */
	private String myUserName;

	/**
	 * The current selected Conference ID.
	 */
	private int selectedConferenceID;

	/**
	 * The default constructor that calls the initializer method
	 */
	public User() {
		input = new Scanner(System.in);
	}

	/**
	 * The constructor that makes an instance of a User with the specified user
	 * name. The constructor also calls the initializer method
	 */
	public User(String theUserName) {
		if (theUserName != null && !theUserName.equals("")) {
			myUserName = theUserName;
		}
		input = new Scanner(System.in);
	}

	/**
	 * This method runs the program. The user can interact with the program
	 * after this method has been called.
	 */
	public void start() {
		System.out.println("Welcome to Conference Management program");
		login();
	}

	/**
	 * This method is responsible to retrieve data from external .ser filed and
	 * update the ArrayLists of User objects, Paper objects, and Conference
	 * objects. The method also resets the user role to null.
	 */
	@SuppressWarnings("unchecked")
	public void init() {
		input = new Scanner(System.in);
		myPaper = new Paper();
		myConference = new Conference();

		myUpdateSerFileUser = new UpdateSerFile(this);
		myUpdateSerFilePaper = new UpdateSerFile(myPaper);
		myUpdateSerFileConference = new UpdateSerFile(myConference);

		myUserArrayList = new ArrayList<User>();
		myPaperArrayList = new ArrayList<Paper>();
		myConferenceArrayList = new ArrayList<Conference>();
		roleArrayList = new ArrayList<String>();

		selectedConferenceID = -1;

		if ((ArrayList<User>) myUpdateSerFileUser.deserialize() != null) {
			myUserArrayList = (ArrayList<User>) myUpdateSerFileUser.deserialize();
		}

		if ((ArrayList<Paper>) myUpdateSerFilePaper.deserialize() != null) {
			myPaperArrayList = (ArrayList<Paper>) myUpdateSerFilePaper.deserialize();
		}

		if ((ArrayList<Conference>) myUpdateSerFileConference.deserialize() != null) {
			myConferenceArrayList = (ArrayList<Conference>) myUpdateSerFileConference.deserialize();
		}

		myIsPC = false;
		myIsSPC = false;
		myIsReviewer = false;
		myIsAuthor = false;
		myIsUser = false;
	}

	/**
	 * This method helps the user to log in to the program. The method also sets
	 * the role of the user or let the user to register if the user is not
	 * already registered to the program. Here is how the method handle the
	 * situation: the method first look for the typed in user name. If found
	 * then the user is good to use the program. If no user name matches the
	 * typed in user name, then the method needs the user to register for a new
	 * account.
	 */
	public void login() {
		/*
		 * These commented loops are for testing purposes. They print out the
		 * list of all Users, Papers, and Conferences.
		 * 
		 * for (User u : myUserArrayList) { System.out.println(u.getUserName() +
		 * " " + u.getRole()); } System.out.println(); for (Paper u :
		 * myPaperArrayList) { System.out.println(u.getName()); }
		 * System.out.println(); for (Conference u : myConferenceArrayList) {
		 * System.out.println(u.getName()); }
		 */

		User matchedUser = new User(getUserName());
		matchedUser.init();
		boolean matchedUserBoolean = false;
		System.out.print("\nEnter your username to login or choose a username to register: ");
		String selectedUserName = readConsole();

		for (User localUser : myUserArrayList) {
			if (localUser.myUserName != null && localUser.myUserName.equals(selectedUserName)) {
				matchedUser = localUser;
				matchedUserBoolean = true;
				break;
			}
		}

		setUserName(selectedUserName);

		if (matchedUserBoolean) {
			setUserName(matchedUser.getUserName());
			setName(matchedUser.getName());
			setRole(matchedUser.getRole());
			System.out.println("Logged in as " + matchedUser.getUserName() + " [" + DEFAULT_ROLE + "]");
			if (matchedUser.isUser()) {
				setUser(true);
			}

			if (matchedUser.isPC()) {
				setPC(true);
			}

			if (matchedUser.isSPC()) {
				setSPC(true);
			}

			if (matchedUser.isReviewer()) {
				setReviewer(true);
			}

			setAuthor(true);
			selectedConferenceID = selectConference();
			selectRole();
		} else {
			register(DEFAULT_ROLE);
		}
	}

	/**
	 * This method helps the user to log out of the program. The method also
	 * calls for another login process so the user can log in to the program
	 * again.
	 */
	public void logout() {
		myIsPC = false;
		myIsSPC = false;
		myIsReviewer = false;
		myIsAuthor = false;
		myIsUser = false;
		myUserName = null;
		login();
	}

	/**
	 * The prompt menu. If there is no active Conference and the user is not
	 * able to create new Conference, then the user will be kicked out of the
	 * system since the user cannot do anything without selecting a Conference.
	 * The proper message will be displayed to the user in this case.
	 */
	public void prompt() {
		System.out.println("Logged in as " + getUserName() + " [" + getRole() + "]");
		if (selectedConferenceID > 0) {
			if (getRole().equals(USER)) {
				selectedConferenceID = selectConference();
				selectRole();
			} else if (getRole().equals(AUTHOR)) {
				Author author = new Author(this);
				author.promptAuthor();
			} else if (getRole().equals(REVIEWER)) {
				new Reviewer(this, selectedConferenceID);
			} else if (getRole().equals(SPC)) {
				new SubprogramChair(this, selectedConferenceID);
			} else if (getRole().equals(PC)) {
				new ProgramChair(this, selectedConferenceID);
			}
		} else {
			login();
		}
	}

	/**
	 * The user will be prompted to select a role. The list of available roles
	 * are based on the roles that the current logged in user has.
	 */
	public void selectRole() {
		roleArrayList = new ArrayList<String>();

		if (isUser()) {
			roleArrayList.add(USER);
		}

		if (isPC()) {
			roleArrayList.add(PC);
		}

		if (isSPC()) {
			roleArrayList.add(SPC);
		}

		if (isReviewer()) {
			roleArrayList.add(REVIEWER);
		}

		// if(isAuthor()) {
		roleArrayList.add(AUTHOR); // deos not mean that the user is an Author
		// }

		System.out.println(mySelectPrompt);
		int roleCounter = 0;
		for (String tempString : roleArrayList) {
			if (!tempString.equals(USER)) {
				System.out.println(++roleCounter + ") Log in as " + tempString);
			}
		}

		for (String tempString : myBackOutOption) {
			System.out.println(++roleCounter + ") " + tempString);
		}

		int selectedOption = Integer.valueOf(readConsole());
		if (selectedOption < roleArrayList.size()) {
			int j = 0;
			for (int i = 0; i < selectedOption; i++) {
				if (roleArrayList.get(j).equals(USER)) {
					i--;
				}
				setRole(roleArrayList.get(j));
				j++;
			}

			prompt();
		} else {
			switch (selectedOption - (roleArrayList.size() - 1)) {
			case 1: // go back
				selectedConferenceID = selectConference();
				selectRole();
				break;
			case 2: // log out
				logout();
				break;
			}
		}
	}

	/**
	 * The user will be asked to select a conference. If no Conference is found,
	 * then the method asks the user to create a new Conference. This latter
	 * options will be provided only to the users who have the authority to
	 * create a new Conference.
	 * 
	 * @return The ID for the selected Conference
	 */
	public int selectConference() {
		int selectedOption = -1;
		if (myConferenceArrayList.size() > 0) {
			int conferenceCounter = 0;
			System.out.println(mySelectPrompt);
			for (Conference tempConference : myConferenceArrayList) {
				System.out.println(++conferenceCounter + ") Go to Conference \"" 
						+ tempConference.getName() + "\"");
			}

			for (String tempString : myOutOption) {
				System.out.println(++conferenceCounter + ") " + tempString);
			}

			selectedOption = Integer.valueOf(readConsole());

			for (int i = 0; i < myOutOption.length; i++) {

				if (selectedOption == myConferenceArrayList.size() + i + 1) {
					logout();
					break;
				}
			}

			selectedOption = myConferenceArrayList.get(selectedOption - 1).getIDConference();
		} else {
			if (getRole().equals(PC)) {
				System.out.println("No conference has been made yet.");
				System.out.println("You have " + PC + " privilege. You can make a new conference.");
				new ProgramChair(this, selectedConferenceID);
			} else {
				System.out.println("No conference has been made yet. Please come back later.");
				logout();
			}
		}

		return selectedOption;
	}

	/**
	 * This method helps the user to register for a new account. The initial
	 * role of the user will be the passed in role. The method also calls for
	 * selecting a conference.
	 * 
	 * @param theRole
	 *            The initial role of the new user
	 */
	public void register(final String theRole) {
		User newUser = new User(getUserName());
		newUser.init();
		System.out.print("Enter your name: ");

		newUser.setName(readConsole());
		newUser.setRole(theRole);

		System.out.println("Logged in as " + getUserName() + " [" + DEFAULT_ROLE + "]");

		newUser.setUser(true);
		if (theRole.equals(PC)) {
			newUser.setPC(true);
		} else if (theRole.equals(SPC)) {
			newUser.setSPC(true);
		} else if (theRole.equals(REVIEWER)) {
			newUser.setReviewer(true);
		} else if (theRole.equals(AUTHOR)) {
			newUser.setAuthor(true);
		}

		myUserArrayList.add(newUser);
		myUpdateSerFileUser = new UpdateSerFile(newUser);
		myUpdateSerFileUser.makeSerialize(myUserArrayList);

		setName(newUser.getName());
		setRole(theRole);
		setUser(true);
		if (theRole.equals(PC)) {
			setPC(true);
		} else if (theRole.equals(SPC)) {
			setSPC(true);
		} else if (theRole.equals(REVIEWER)) {
			setReviewer(true);
		} else if (theRole.equals(AUTHOR)) {
			setAuthor(true);
		}

		selectedConferenceID = selectConference();
		selectRole();
	}

	/**
	 * This method reads in a file and writes out another file. The input file
	 * is selected by the user and the output file and the location of the
	 * output file is determined by the passed in parameters. The method is able
	 * to read and write all types of files as it reads and writes bytes.
	 * 
	 * @param theName
	 *            The name of the new file calculated by the caller
	 * @param thePath
	 *            The path of the new file calculated by the caller
	 * @return The name of the new created file
	 */
	public String chooseFile(String theName, String thePath) {
		File file = null;
		JFileChooser fileChooser = new JFileChooser();
		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
		}

		String inFileName = file.getName();
		String outFileName = theName + inFileName.substring(inFileName.lastIndexOf("."), inFileName.length());

		Path pathIn = Paths.get(file.getAbsolutePath());

		Path pathOut = Paths.get(thePath + outFileName);
		byte[] fileByteArray = null;
		try {
			fileByteArray = Files.readAllBytes(pathIn);
		} catch (AccessDeniedException e) {
			System.out.println("No access to the file.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("File error.");
			e.printStackTrace();
		}

		try {
			Files.write(pathOut, fileByteArray);
		} catch (AccessDeniedException e) {
			System.out.println("No access to the file.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("File error.");
			e.printStackTrace();
		}
		return outFileName;
	}

	public String getContent(File theFile) {
		StringBuilder contents = new StringBuilder();
		String extension = theFile.getName().substring(theFile.getName().lastIndexOf(".") + 1,
				theFile.getName().length());
		boolean acceptableFile = false;

		for (String localExtension : myAcceptableFileExtensions) {
			if (extension.toLowerCase().equals(localExtension.toLowerCase())) {
				acceptableFile = true;
				break;
			}
		}

		if (acceptableFile) {
			try {
				BufferedReader input = new BufferedReader(new FileReader(theFile));
				try {
					String line = null;
					while ((line = input.readLine()) != null) {
						contents.append(line);
						contents.append(System.getProperty("line.separator"));
					}
				} finally {
					input.close();
				}
			} catch (IOException ex) {
				System.out.println("Unable to read the external file.");
			}
		}

		return contents.toString();
	}

	/**
	 * This method takes a Paper ID and returns the index of the associated
	 * Paper in ArrayList of Papers
	 * 
	 * @param thePaperID
	 *            The ID for a Paper
	 * @return The index of the associated Paper in ArrayList of Paper objects
	 */
	public int getPaperIndex(int thePaperID) {
		int index = -1;
		for (int i = 0; i < myPaperArrayList.size(); i++) {
			if (myPaperArrayList.get(i).getID() == thePaperID) {
				index = i;
				break;
			}
		}

		return index;
	}

	/**
	 * This method takes a User name and returns the index of the associated
	 * User in ArrayList of Users
	 * 
	 * @param theUserName
	 *            The ID for a User
	 * @return The index of the associated User in ArrayList of User objects
	 */
	public int getUserIndex(String theUserName) {
		int index = -1;
		for (int i = 0; i < myUserArrayList.size(); i++) {
			if (myUserArrayList.get(i).getUserName().equals(theUserName)) {
				index = i;
				break;
			}
		}

		return index;
	}

	/**
	 * This method takes a Conference ID and returns the index of the associated
	 * Conference in ArrayList of Conferences
	 * 
	 * @param theConferenceID
	 *            The ID for a Conference
	 * @return The index of the associated Conference in ArrayList of Conference
	 *         objects
	 */
	public int getConferenceIndex(int theConferenceID) {
		int index = -1;
		for (int i = 0; i < myConferenceArrayList.size(); i++) {
			if (myConferenceArrayList.get(i).getIDConference() == theConferenceID) {
				index = i;
				break;
			}
		}

		return index;
	}

	/**
	 * This method returns the list of all Conference objects
	 * 
	 * @return The list of all Conference objects
	 */
	public ArrayList<Conference> getConferenceList() {
		return myConferenceArrayList;
	}

	/**
	 * This method returns the list of all Paper objects
	 * 
	 * @return The list of all Paper objects
	 */
	public ArrayList<Paper> getPaperList() {
		return myPaperArrayList;
	}

	/**
	 * This method returns the list of all User objects
	 * 
	 * @return The list of all User objects
	 */
	public ArrayList<User> getUserList() {
		return myUserArrayList;
	}

	/**
	 * This method returns the current logged in user name
	 * 
	 * @return The current logged in user name
	 */
	public String getUserName() {
		return myUserName;
	}

	/**
	 * This method returns the name of the current logged in user
	 * 
	 * @return The name of the current logged in user
	 */
	public String getName() {
		return myName;
	}

	/**
	 * This method returns the role of the current logged in user
	 * 
	 * @return The role of the current logged in user
	 */
	public String getRole() {
		return myRole;
	}

	/**
	 * This method sets the role for the current logged in user
	 * 
	 * @param theRole
	 *            The new role for the current logged in user
	 */
	public void setRole(String theRole) {
		myRole = theRole;
	}

	/**
	 * This method sets the user name for the current logged in user
	 * 
	 * @param theUserName
	 *            The new user name for the current logged in user
	 */
	public void setUserName(String theUserName) {
		myUserName = theUserName;
	}

	/**
	 * This method assigns the current logged in user to be a User
	 */
	public void setUser(boolean theStatus) {
		myIsUser = theStatus;
	}

	/**
	 * This method assigns the current logged in user to be a Program Chair
	 */
	public void setPC(boolean theStatus) {
		myIsPC = theStatus;
	}

	/**
	 * This method assigns the current logged in user to be a Subprogram Chair
	 */
	public void setSPC(boolean theStatus) {
		myIsSPC = theStatus;
	}

	/**
	 * This method assigns the current logged in user to be a Reviewer
	 */
	public void setReviewer(boolean theStatus) {
		myIsReviewer = theStatus;
	}

	/**
	 * This method assigns the current logged in user to be an Author
	 */
	public void setAuthor(boolean theStatus) {
		myIsAuthor = theStatus;
	}

	/**
	 * This method sets a name for the current logged in user
	 * 
	 * @param theName
	 *            The new name of the current logged in user
	 */
	public void setName(String theName) {
		myName = theName;
	}

	/**
	 * This method checks to see if the current logged in user is a User
	 * 
	 * @return True if the current logged in user is a User and False otherwise
	 */
	public boolean isUser() {
		return myIsUser;
	}

	/**
	 * This method checks to see if the current logged in user is a Program
	 * Chair
	 * 
	 * @return True if the current logged in user is a Program Chair and False
	 *         otherwise
	 */
	public boolean isPC() {
		return myIsPC;
	}

	/**
	 * This method checks to see if the current logged in user is a Subprogram
	 * Chair
	 * 
	 * @return True if the current logged in user is a Subprogram Chair and
	 *         False otherwise
	 */
	public boolean isSPC() {
		return myIsSPC;
	}

	/**
	 * This method checks to see if the current logged in user is a Reviewer
	 * 
	 * @return True if the current logged in user is a Reviewer and False
	 *         otherwise
	 */
	public boolean isReviewer() {
		return myIsReviewer;
	}

	/**
	 * This method checks to see if the current logged in user is an Author
	 * 
	 * @return True if the current logged in user is an Author and False
	 *         otherwise
	 */
	public boolean isAuthor() {
		return myIsAuthor;
	}

	/**
	 * This method returns the ID for the current selected Conference
	 * 
	 * @return The ID for the current selected Conference
	 */
	public int getSelectedConferenceID() {
		return selectedConferenceID;
	}

	/**
	 * This method sets the current selected Conference
	 * 
	 * @param theConferenceID
	 *            The ID of the Conference
	 */
	public void setSelectedConferenceID(int theConferenceID) {
		selectedConferenceID = theConferenceID;
	}

	/**
	 * This method reads in input from console and return the input to the
	 * caller. This method is set to not read in spaces. This is to have an
	 * easier data validation. However, it can be set to read in lines as well.
	 * 
	 * @return The user's input string
	 */
	public String readConsole() {
		while (!input.hasNext()) {
		}

		String returnDate = input.nextLine();
		return returnDate;
	}
}