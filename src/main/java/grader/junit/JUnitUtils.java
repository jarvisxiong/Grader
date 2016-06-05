package grader.junit;

import org.junit.Assert;

public class JUnitUtils {

	public static void assertTrue (Exception e, double aFractionComplete) {
		Assert.assertTrue(e.getClass().getName() + " " + e.getMessage() + ":" + aFractionComplete, false);
	}

}
