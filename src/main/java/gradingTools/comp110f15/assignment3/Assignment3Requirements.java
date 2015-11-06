package gradingTools.comp110f15.assignment3;

import framework.grading.FrameworkProjectRequirements;
import framework.project.Project;
import gradingTools.comp110.assignment1.testcases.ProperHeaderTestCase;
import gradingTools.comp110f15.assignment3.testcases.RollResultTest;
import gradingTools.comp110f15.assignment3.testcases.RollTest;
import gradingTools.comp110f15.assignment3.testcases.SnakeEyesTest;
import gradingTools.comp110f15.assignment3.testcases.rolledOneOnlyOnceTest;
import gradingTools.comp110f15.assignment3.testcases.winningNameTest;
public class Assignment3Requirements extends FrameworkProjectRequirements {
	public Assignment3Requirements() {
		addDueDate("11/06/2015 23:55:59", 1.0);
		addDueDate("11/07/2015 23:55:59", 0.5);
		// Check for a main method
		addFeature("Correct RollResult Method",20,new RollResultTest());
		long methodsTimeout=3000L;
		addFeature("Correct winningName Method",10,new winningNameTest());
		addFeature("Correct Roll Method",15,new RollTest());
		addFeature("Correct rolled one once method",15,new rolledOneOnlyOnceTest());
		addFeature("Correct snakeEyes method",15,new SnakeEyesTest());
	}
}
