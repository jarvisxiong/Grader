package gradingTools.comp110f15.assignment2;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.ProperHeaderTestCase;
import gradingTools.comp110f15.assignment2.testcases.ABCK;
import gradingTools.comp110f15.assignment2.testcases.ABDK;
import gradingTools.comp110f15.assignment2.testcases.AEFK;
import gradingTools.comp110f15.assignment2.testcases.AEGK;
import gradingTools.comp110f15.assignment2.testcases.AHJK;
import gradingTools.comp110f15.assignment2.testcases.AttemptNumberTest;
import gradingTools.comp110f15.assignment2.testcases.GameOverTest;
import gradingTools.comp110f15.assignment2.testcases.IntegerAfterKTest;
import gradingTools.comp110f15.assignment2.testcases.LoopedTest;

public class Assignment2Requirements extends FrameworkProjectRequirements {
	public Assignment2Requirements() {
		addDueDate("10/02/2015 23:55:59", 1.0);
		addDueDate("10/03/2015 23:55:59", 0.5);
		// Check for a main method
		addFeature("Contains proper Header",10,new ProperHeaderTestCase("Fall 2015"));
		addFeature("ABCK Path",10,new ABCK());
		addFeature("ABDK Path",10,new ABDK());
		addFeature("AEFK Path",10,new AEFK());
		addFeature("AEFK Path",10,new AEGK());
		addFeature("AHJK Path",10,new AHJK());
		addFeature("Attempt Number", 5, new AttemptNumberTest());
		addFeature("GameOverTest", 5, new GameOverTest());
		addFeature("LoopedTest", 10, new LoopedTest());
		addFeature("IntegerAfterKTest", 5, new IntegerAfterKTest());
	}
}
