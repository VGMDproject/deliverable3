/**
 * <pre> 
 * Class: <b>Main</b> 
 * File: Main.java 
 * Course: TCSS 360 – Spring 2016
 * Copyright 2016 Moe Abdipour, Daniel Bayless, Gabriela Orozco, Vu Hoang
 * </pre>
 */
package conference_management;

import java.awt.EventQueue;

/**
 * <pre>
 * This class is the main class that runs the program. The program
 * 		itself is a Conference Management console based program
 * 		which allows users to login to the program with different
 * 		access levels. Each access level is coded in its own class
 * 		where all access level classes inherit the User class. Also 
 * 		each access level class has its own prompt method which is 
 * 		useful in case of adding GUI features to the program. In 
 * 		fact, the GUI can call the prompt method in each class and
 * 		the prompt methods take care of calling other method and 
 * 		classes as needed. Adding GUI to the program, the console 
 * 		may still be useful for testing purposes.
 * </pre>
 * 
 * @author Moe Abdipour
 * @author Daniel Bayless
 * @author Gabriela Orozco
 * @author Vu Hoang
 * @version 05/31/2016
 * @since May 10, 2016
 */
public final class Main {
	/**
	 * Private constructor, to prevent instantiation of this class.
	 */
	private Main() {
	}
	
	/**
	 * The main class that runs the program.
	 */
	public static void main(final String[] theArgs) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				User startingUser = new User();
				startingUser.init();
				startingUser.start();
			}
		});
	}
}