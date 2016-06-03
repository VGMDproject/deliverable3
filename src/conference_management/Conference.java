/**
 * <pre> 
 * Class: <b>Conference</b> 
 * File: Conference.java 
 * Course: TCSS 360 – Spring 2016
 * Copyright 2016 Moe Abdipour, Daniel Bayless, Gabriela Orozco, Vu Hoang
 * </pre>
 */
package conference_management;

import java.util.Date;

/**
 * <pre>
 * This class is responsible for creating and maintaining a Conference. 
 * 		A Conference is identified by its own unique ID. The Conference
 * 		also has a deadline by which an Author can submit a Paper to the
 * 		Conference.
 * </pre>
 * 
 * @author Moe Abdipour
 * @author Daniel Bayless
 * @author Gabriela Orozco
 * @author Vu Hoang
 * @version 05/31/2016
 * @since May 10, 2016
 */
public class Conference implements java.io.Serializable {
	/**
	 * The Java auto generated serialization version number
	 */
	private static final long serialVersionUID = -2406070688156445622L;

	/**
	 * Used to identify the Conference
	 */
	private int myConferenceID;

	/**
	 * Name is the same as the title of the Conference
	 */
	private String myName;
	
	/**
	 * The status which may be assigned to any integer number. The default value
	 * is 1 meaning the Conference is active. A Conference with myStatus equals
	 * to 0 is not active. Other states may be assigned as needed.
	 */
	private int myStatus;

	/**
	 * The deadline by which the papers should be submitted by the Author.
	 */
	private Date myDeadline;

	/**
	 * The Program Chair assigned to this Conference. This is the same as the
	 * creator of the Conference.
	 */
	private ProgramChair myPC;

	/**
	 * The default constructor just in case if an instance of the class was
	 * needed.
	 */
	public Conference() {
	}

	/**
	 * This constructor will be used if a new Conference object was needed.
	 * 
	 * @param theUser
	 *            The current logged in user
	 * @param theConferenceID
	 *            The current selected Conference
	 */
	public Conference(int theConferenceID) {
		myConferenceID = theConferenceID;
	}

	/**
	 * This method will be used to set the selected Conference
	 * 
	 * @param theConferenceID
	 *            The ID for the current selected Conference
	 */
	public void setConferenceID(int theConferenceID) {
		myConferenceID = theConferenceID;
	}

	/**
	 * This method will be used to set the name of the Conference
	 * 
	 * @param theName
	 *            The name of the Conference
	 */
	public void setName(String theName) {
		myName = theName;
	}

	/**
	 * This method will be used to set the state of the Conference Different
	 * possible states are explained in myStatus
	 * 
	 * @param theStatus
	 *            The state of the Conference
	 */
	public void setStatusConference(int theStatus) {
		myStatus = theStatus;
	}

	/**
	 * This method will be used to set the deadline of the Conference No paper
	 * can be submitted after the deadline
	 * 
	 * @param theDeadline
	 *            The due date
	 */
	public void setDeadline(Date theDeadline) {
		myDeadline = theDeadline;
	}

	/**
	 * This method will be used to get the name of the Conference
	 * 
	 * @return myName The name
	 */
	public String getName() {
		return myName;
	}

	/**
	 * This method will be used to get the deadline of the Conference No paper
	 * can be submitted after the deadline
	 * 
	 * @return myDeadline The due date
	 */
	public Date getDeadline() {
		return myDeadline;
	}

	/**
	 * This method will be used to get the state of the Conference Different
	 * possible states are explained in myStatus
	 * 
	 * @return myStatus The state of the Conference
	 */
	public int getStatusConference() {
		return myStatus;
	}

	/**
	 * This method will be used to get the ID of the Conference The Conference
	 * will be identified by this ID
	 * 
	 * @return myConferenceID The unique ID of the Conference
	 */
	public int getIDConference() {
		return myConferenceID;
	}

	/**
	 * This method will be used to get the Program Chair assigned to this
	 * Conference. The Program Chair of a Conference is the User who creates the
	 * Conference.
	 * 
	 * @return ProgramChair The Program Chair
	 */
	public ProgramChair getProgramChair() {
		return myPC;
	}

	/**
	 * This method will be used to set the Program Chair assigned to this
	 * Conference. The Program Chair of a Conference is the User who creates the
	 * Conference.
	 */
	public void setProgramChair(ProgramChair thePC) {
		myPC = thePC;
	}
}