package grader.junit;

import java.util.ArrayList;
import java.util.List;

import util.annotations.Visible;

public class AGradableJUnitTopLevelSuite extends AGradableJUnitSuite {
	public AGradableJUnitTopLevelSuite(Class aJUnitClass) {
		super(aJUnitClass);
	}

	@Visible(false)
	public String getName() {
		return getExplanation();
	}
	
}
