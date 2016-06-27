package grader.junit;

import java.util.ArrayList;
import java.util.List;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Visible;
@StructurePattern(StructurePatternNames.LIST_PATTERN)
public class AGradableJUnitTopLevelSuite extends AGradableJUnitSuite {
	public AGradableJUnitTopLevelSuite(Class aJUnitClass) {
		super(aJUnitClass);
	}

	@Visible(false)
	public String getName() {
		return super.getName();
	}
	
//	public String getText() {
//		String retVal = getName() + "\n";
//		for (GradableJUnitTest aTest:children) {
//			retVal += aTest + "\n";
//		}
//		return retVal;
//	}
	
}
