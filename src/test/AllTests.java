package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AuthorTest.class, ConferenceTest.class, PaperTest.class, ReviewerTest.class, 
	SubprogramChairTest.class,
	UpdateSerFileTest.class, UserTest.class })
public class AllTests {

}
