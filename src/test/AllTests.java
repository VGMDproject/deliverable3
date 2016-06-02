/**
 * <pre> 
 * Class: <b>AllTests</b> 
 * File: AllTests.java 
 * Course: TCSS 360 – Spring 2016
 * Copyright 2016 Moe Abdipour
 * </pre>
 */
package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AuthorTest.class, 
				ConferenceTest.class, 
				PaperTest.class, 
				ReviewerTest.class, 
				SubprogramChairTest.class,
				UpdateSerFileTest.class, 
				UserTest.class })

/**
 * <pre>
 * This class is a suit class for all test classes in this project. Running this class
 * 		runs all test classes listed above.
 * </pre>
 * 
 * @author Moe Abdipour
 * @version 05/31/2016
 * @since May 26, 2016
 */
public class AllTests {
}
