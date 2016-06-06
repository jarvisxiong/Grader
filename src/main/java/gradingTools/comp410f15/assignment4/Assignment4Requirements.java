package gradingTools.comp410f15.assignment4;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp410f15.assignment4.testcases.InsertAndRemoveTestCase;
import gradingTools.comp410f15.assignment4.testcases.LargerElementsTestCase;

public class Assignment4Requirements extends FrameworkProjectRequirements {
	public Assignment4Requirements() {
		addDueDate("11/13/2015 17:00:00", 1.0);

		// Min Heap
		addFeature("Insert And Remove", 35, new InsertAndRemoveTestCase());
		
		// Median Container
		addFeature("Larger Elements on The Right", 25, new LargerElementsTestCase());
		
		// Insert
		addManualFeature("Insert Manual", 15, false);
		
		// Remove
		addManualFeature("Remove Manual", 15, false);
		
		// Clean code
		addManualFeature("Clean Code", 10, false);
	}
}
