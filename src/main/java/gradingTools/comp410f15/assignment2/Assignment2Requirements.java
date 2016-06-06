package gradingTools.comp410f15.assignment2;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp410f15.assignment2.testcases.BucketSortTestCase;
import gradingTools.comp410f15.assignment2.testcases.MergeTestCase;
import gradingTools.comp410f15.assignment2.testcases.QuickSortTestCase;

public class Assignment2Requirements extends FrameworkProjectRequirements {
	public Assignment2Requirements() {
		addDueDate("10/30/2015 17:00:00", 1.0);

		// Bucket sort
		addFeature("Bucket Sort", 25, new BucketSortTestCase());
		
		// Quick sort
		addFeature("Quick Sort", 25, new QuickSortTestCase());
		
		// Merge
		addFeature("Merge", 25, new MergeTestCase());
		
		// Clean code
		addManualFeature("Clean Code", 10, false);
		
		// Efficient Implementation
		addManualFeature("Efficiency", 15, false);
	}
}
