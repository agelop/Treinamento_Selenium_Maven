package modulo12;


import org.junit.Test;
import org.junit.experimental.ParallelComputer;
import org.junit.runner.JUnitCore;

public class ParallelTest {

	@Test
	public void runAllTests() {
		
		Class<?>[] classes = { CheckTabletTest.class, CheckSpeakerTest.class };
		
		JUnitCore.runClasses(new ParallelComputer(true, true), classes);
	}

}
