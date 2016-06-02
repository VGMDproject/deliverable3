/**
 * <pre> 
 * Class: <b>Paper</b> 
 * File: Paper.java 
 * Course: TCSS 360 – Spring 2016
 * Copyright 2016 Moe Abdipour, Daniel Bayless, Gabriela Orozco, Vu Hoang
 * </pre>
 */
package conference_management;

/**
 * <pre>
 * This class is responsible for creating and maintaining a Paper.
 * 		Papers are made by Users. Papers belong to Conferences. A
 * 		Paper consists of a name and a file. The file will be stored
 * 		in a specific path. The path to the file is stored in User 
 * 		class. The Paper's file is uploaded by the Author. The file 
 * 		will be stored locally. Papers will remain until a third 
 * 		party removes the files on local storage. This program will
 * 		not remove any file. A Paper has a state which defines if 
 * 		the paper is accepted or rejected. Changing the state of a
 * 		Paper to rejected will not affect the corresponding Paper's
 * 		file on local storage. A Paper is identified by its own unique
 * 		ID.
 * </pre>
 * 
 * @author Moe Abdipour
 * @author Daniel Bayless
 * @author Gabriela Orozco
 * @author Vu Hoang
 * @version 05/31/2016
 * @since May 10, 2016
 */
public class Paper implements java.io.Serializable {
	/**
	 * The Java auto generated serialization version number
	 */
	private static final long serialVersionUID = 3853699737209603400L;

	/**
	 * The unique paper ID
	 */
	private int myPaperID;

	/**
	 * The Program Chair assigned to this paper. The Program Chair is assigned
	 * to paper instead of the Conference to have more flexibility in program.
	 */
	private ProgramChair myProgramChair;

	/**
	 * The Subprogram Chair assigned to this paper.
	 */
	private SubprogramChair mySubprogramChair;

	/**
	 * The Reviewer assigned to this paper.
	 */
	private Reviewer myReviewer;

	/**
	 * The Author of this paper.
	 */
	private Author myAuthor;

	/**
	 * The name of this paper. Same as the title of the Paper.
	 */
	private String myName;

	/**
	 * The ID for the Conference that this paper belongs to.
	 */
	private int myConferenceID;

	/**
	 * The status which may be assigned to any integer number. The default value
	 * is 1 meaning the Paper is active. A Paper with myStatus equals to 0 is
	 * not active. Other states may be assigned as needed.
	 */
	private int myStatus;

	/**
	 * The name of the review file. The file is located in local storage.
	 */
	private String myReview;

	/**
	 * The name of the recommendation file. The file is located in local
	 * storage.
	 */
	private String myRecommendation;

	/**
	 * The name of the Paper file. The file is located in local storage.
	 */
	private String myFile;

	/**
	 * The current logged in user.
	 */
	private User myUser;

	/**
	 * The current logged in user name. This will be the same user name as the
	 * Author's user name.
	 */
	private String myUsername;

	/**
	 * The default constructor just in case if an instance of the class was
	 * needed.
	 */
	public Paper() {
	}

	/**
	 * This constructor will be used if a new Paper object was needed.
	 * 
	 * @param theUser
	 *            The current logged in user
	 * @param thePaperID
	 *            The ID for the new Paper object
	 * @param theConferenceID
	 *            The current selected Conference
	 */
	public Paper(User theUser, int thePaperID, int theConferenceID) {
		myUser = theUser;
		myUsername = myUser.getUserName();
		myPaperID = thePaperID;
		myConferenceID = theConferenceID;
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
	 * This method will be used to get the index of a Paper in the ArrayList of
	 * all Papers. The Paper will be identified by its unique ID.
	 * 
	 * @param theID
	 *            The ID for the Paper
	 * @@return The index of the Paper in the ArrayList of all Papers
	 */
	public int getIndexPaper(int theID) {
		int index = -1;
		for (int i = 0; i < myUser.myPaperArrayList.size(); i++) {
			if (myUser.myPaperArrayList.get(i).getID() == myPaperID) {
				index = i;
				break;
			}
		}
		return index;
	}

	/**
	 * This method returns the Program Chair assigned to this Paper.
	 * 
	 * @return the assigned Program Chair
	 */
	public ProgramChair getProgramchair() {
		return myProgramChair;
	}

	/**
	 * This method returns the Subprogram Chair assigned to this Paper.
	 * 
	 * @return the assigned Subprogram Chair
	 */
	public SubprogramChair getSubprogramChair() {
		return mySubprogramChair;
	}

	/**
	 * This method returns the Reviewer assigned to this Paper
	 * 
	 * @return the assigned Reviewer
	 */
	public Reviewer getReviewer() {
		return myReviewer;
	}

	/**
	 * This method returns the Author assigned to this Paper
	 * 
	 * @return the assigned Author
	 */
	public Author getAuthor() {
		return myAuthor;
	}

	/**
	 * This method returns the name of this Paper. Name is the same as title.
	 * 
	 * @return the name of the Paper
	 */
	public String getName() {
		return myName;
	}

	/**
	 * This method returns the ID for the Conference that this Paper belongs to.
	 * 
	 * @return the ID for the COnference
	 */
	public int getConferenceID() {
		return myConferenceID;
	}

	/**
	 * This method will be used to set the state of the Paper Different possible
	 * states are explained in myStatus
	 * 
	 * @param theStatus
	 *            The state of the Paper
	 */
	public int getStatusPaper() {
		return myStatus;
	}

	/**
	 * This method returns the Reviewer assigned to this Paper
	 * 
	 * @return the assigned Reviewer
	 */
	public String getReview() {
		return myReview;
	}

	/**
	 * This method returns the recommendation submitted for this Paper
	 * 
	 * @return the submitted recommendation
	 */
	public String getRecommendation() {
		return myRecommendation;
	}

	/**
	 * This method returns the name of the file associated to this Paper. The
	 * location of the actual file is on local storage. The path to the file is
	 * in User class.
	 * 
	 * @return the name of the associated file to this Paper
	 */
	public String getFile() {
		return myFile;
	}

	/**
	 * This method assigns a Program Chair to this Paper. A Program Chair can
	 * accept or reject this Paper.
	 * 
	 * @param theProgramChair
	 *            the Program Chair assigned to this Paper
	 */
	public void setProgramchair(ProgramChair theProgramChair) {
		myProgramChair = theProgramChair;
	}

	/**
	 * This method assigns a Subprogram Chair to this Paper. A Program Chair can
	 * assign a Reviewer to this Paper. A Subprogram Chair also can add
	 * recommendation to this Paper.
	 * 
	 * @param theSubprogramChair
	 *            the Subprogram Chair assigned to this Paper
	 */
	public void setSubprogramChair(SubprogramChair theSubprogramChair) {
		mySubprogramChair = theSubprogramChair;
	}

	/**
	 * This method assigns a Reviewer to this Paper. A Reviewer can add Review
	 * to this Paper.
	 * 
	 * @param theReviewer
	 *            the Reviewer assigned to this Paper
	 */
	public void setReviewer(Reviewer theReviewer) {
		myReviewer = theReviewer;
	}

	/**
	 * This method assigns an Author to this Paper. An Author can unsubmit this
	 * Paper.
	 * 
	 * @param theAuthor
	 *            the Author of this Paper
	 */
	public void setAuthor(Author theAuthor) {
		myAuthor = theAuthor;
	}

	/**
	 * This method sets the name of this Paper. Name is the same as title.
	 * 
	 * @param theName
	 *            the name of this Paper
	 */
	public void setName(String theName) {
		myName = theName;
	}

	/**
	 * This method sets the Conference that this Paper belongs to.
	 * 
	 * @param theConferenceID
	 *            the ID for the Conference
	 */
	public void setConference(int theConferenceID) {
		myConferenceID = theConferenceID;
	}

	/**
	 * This method will be used to set the state of the Paper. Different
	 * possible states are explained in myStatus
	 * 
	 * @param theStatus
	 *            The state of the Paper
	 */
	public void setStatusPaper(int theStatus) {
		myStatus = theStatus;
	}

	/**
	 * This method will be used to set the file of the Paper.
	 * 
	 * @param theFile
	 *            The file of the Paper
	 */
	public void setFile(String theFile) {
		myFile = theFile;
	}

	/**
	 * This method will be used to set the ID of the Paper. The ID will be used
	 * to identify the Paper.
	 * 
	 * @param theID
	 *            The ID for the Paper
	 */
	public void setID(int theID) {
		myPaperID = theID;
	}

	/**
	 * This method will be used to set the ID of the Paper. The ID will be used
	 * to identify the Paper.
	 * 
	 * @param theID
	 *            The ID for the Paper
	 */
	public int getID() {
		return myPaperID;
	}

	/**
	 * This method will be used to submit a review for the the Paper. The review
	 * will be in form of a file.
	 * 
	 * @param theReview
	 *            The file of the review
	 */
	public void submitReview(String theReview) {
		myReview = theReview;
	}

	/**
	 * This method will be used to submit a recommendation for the Paper. The
	 * recommendation will be in form of a file.
	 * 
	 * @param theRecommendation
	 *            The file of the recommendation
	 */
	public void submitRecommendation(String theRecommendation) {
		myRecommendation = theRecommendation;
	}

	/**
	 * This method will edit the name of the Paper
	 * 
	 * @param theName
	 *            The new name for the Paper
	 */
	public void editPaper(String theName) {
		myName = theName;
	}
}