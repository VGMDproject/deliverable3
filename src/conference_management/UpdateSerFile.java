/**
 * <pre> 
 * Class: <b>UpdateSerFile</b> 
 * File: UpdateSerFile.java 
 * Course: TCSS 360 – Spring 2016
 * Copyright 2016 Moe Abdipour, Daniel Bayless, Gabriela Orozco, Vu Hoang
 * </pre>
 */
package conference_management;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * <pre>
 * This class is responsible for creating and maintaining external .ser
 * 		files. There are three .ser files: conference.ser, paper.ser, and
 * 		user.ser. Each of these files hold an ArrayList of objects. In 
 * 		case of retrieving data, each of  object in these ArrayLists will
 * 		be casted to their original data.
 * </pre>
 * 
 * @author Moe Abdipour
 * @author Daniel Bayless
 * @author Gabriela Orozco
 * @author Vu Hoang
 * @version 05/31/2016
 * @since May 10, 2016
 */
public class UpdateSerFile implements java.io.Serializable {
	/**
	 * The Java auto generated serialization version number
	 */
	private static final long serialVersionUID = 766729644025146049L;

	/**
	 * The file name to be updated or retrieved
	 */
	private String fileName = null;

	/**
	 * The object that defines which type of data is received
	 */
	private Object myObject = null;

	/**
	 * The return object of retrieved data
	 */
	private Object myReturnObject = null;
	
	/**
	 * This method is responsible to update the file name of the external .ser
	 * files based upon the input object.
	 * 
	 * @param theObject
	 *            The input object to be identified.
	 */
	public UpdateSerFile(Object theObject) {
		myObject = theObject;
		if (myObject instanceof Paper) {
			fileName = "paper.ser";
		} else if (myObject instanceof User) {
			fileName = "user.ser";
		} else if (myObject instanceof Conference) {
			fileName = "conference.ser";
		}
	}

	/**
	 * This method is responsible to update the .ser files. The content of the
	 * external .ser file will be updated with the input object.
	 * 
	 * @param theObject
	 *            The input object to be inserted into the .ser file.
	 */
	public void makeSerialize(Object theObject) {
		ObjectOutputStream out;
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(fileName);
			out = new ObjectOutputStream(fileOut);
			out.writeObject(theObject);
			out.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			// Only for testing purposes
			// System.out.println("Unable to find the file. (" + fileName +
			// ")");
		} catch (IOException e1) {
			// Only for testing purposes
			// System.out.println("No access or no data to read. (" + fileName +
			// ")");
		}
	}

	/**
	 * This method is responsible to extract data from the external .ser files.
	 * 
	 * @return The content of the .ser file as an object. This object needs to
	 *         be casted to the proper data type.
	 */
	public Object deserialize() {
		try {
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			myReturnObject = in.readObject();
			in.close();
			fileIn.close();
		} catch (ClassNotFoundException c) {
			// Only for testing purposes
			// System.out.println("Unable to read the file. (" + fileName +
			// ")");
		} catch (IOException i) {
			// Only for testing purposes
			// System.out.println("No access or no data to read. (" + fileName +
			// ")");
		}

		return myReturnObject;
	}
}