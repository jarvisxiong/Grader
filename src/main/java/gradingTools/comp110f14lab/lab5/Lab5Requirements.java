package gradingTools.comp110f14lab.lab5;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.MainMethodTestCase;
import gradingTools.comp110f14lab.lab5.testcases.AvgPrintout;
import gradingTools.comp110f14lab.lab5.testcases.HasArray;
import gradingTools.comp110f14lab.lab5.testcases.PromptTestCase;

public class Lab5Requirements extends FrameworkProjectRequirements{
	public Lab5Requirements(){
		addDueDate("10/27/2014 23:55:59", 1.0);
		addDueDate("10/28/2014 23:55:59", 0.5);
		
		//check for main method
		addFeature("Contains a main method", 5, new MainMethodTestCase());
		//check for prompt
		addFeature("Array size prompt",1,new PromptTestCase());
		//check for average printout
		addFeature("Average Printout",1,new AvgPrintout());
		//check for existence of myArray
		addFeature("Has Array",3,new HasArray());
	}
}
