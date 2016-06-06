package gradingTools.comp410f15.assignment3;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp410f15.assignment3.testcases.MaxHeapTestCase;
import gradingTools.comp410f15.assignment3.testcases.MedianContainerTestCase;
import gradingTools.comp410f15.assignment3.testcases.MinHeapTestCase;

public class Assignment3Requirements extends FrameworkProjectRequirements {
	public Assignment3Requirements() {
		addDueDate("11/13/2015 17:00:00", 1.0);

		// Min Heap
		addFeature("Min Heap", 32, new MinHeapTestCase());
		
		// Max Heap
		addFeature("Max Heap", 32, new MaxHeapTestCase());
		
		// Median Container
		addFeature("Median Container", 26, new MedianContainerTestCase());
		
		// Clean code
		addManualFeature("Clean Code", 10, false);
	}
}
