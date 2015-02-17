package gradingTools.comp110s15.assignment3;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.MainMethodTestCase;
import gradingTools.comp110.assignment1.testcases.ProperHeaderTestCase;
import gradingTools.comp110s15.assignment3.testcases.FlowerTypePromptTest;
import gradingTools.comp110s15.assignment3.testcases.NameLengthTest;
import gradingTools.comp110s15.assignment3.testcases.NotCarry;
import gradingTools.comp110s15.assignment3.testcases.RoseOutputPromptTest;
import gradingTools.comp110s15.assignment3.testcases.TotalRoses;
import gradingTools.comp110s15.assignment3.testcases.VarsTest;
import gradingTools.comp110s15.assignment3.testcases.WelcomePromptTest;

public class Assignment3Requirements extends FrameworkProjectRequirements {
	public Assignment3Requirements() {
		addDueDate("02/13/2015 23:55:59", 1.0);
		addDueDate("02/14/2015 23:55:59", 0.5);
		
		// Check for Header
		addFeature("Has a proper header", 5, new ProperHeaderTestCase("COMP110-001, Spring 2015"));
		// Check for a main method
		addFeature("Contains a main method", 20, new MainMethodTestCase());
		//Check for welcome message
		addFeature("Contains welcome message",10,new WelcomePromptTest());
		//check flower type
		addFeature("Flower type prompt",10,new FlowerTypePromptTest());
		//rose output
		addFeature("Number of Roses prompt",10,new RoseOutputPromptTest());
		//name length
		addFeature("Name length",20,new NameLengthTest());
		//rose calculation
		addFeature("Rose Calculation",10,new TotalRoses());
		//handle not carry
		addFeature("handle not carry",10,new NotCarry());
		//has var
		addFeature("uses correct variables",5,new VarsTest());
	}
}
