package gradingTools.comp110f14.assignment6;
import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.ProperHeaderTestCase;
import gradingTools.comp110f14.assignment4testcases.CapitalizationStyle;
import gradingTools.comp110f14.assignment6testcases.CorrectOutput;
import gradingTools.comp110f14.assignment6testcases.HandlingPrompts;
import gradingTools.comp110f14.assignment6testcases.KitKatMethods;
import gradingTools.comp110f14.assignment6testcases.MYOMethods;
import gradingTools.comp110f14.assignment6testcases.PromptAgain;
import gradingTools.comp110f14.assignment6testcases.PromptTest;
import gradingTools.comp110f14.assignment6testcases.VariableTest;

public class Assignment6Requirements extends FrameworkProjectRequirements {
	
	public Assignment6Requirements(){
		addDueDate("12/03/2014 23:55:59", 1.0);
		addDueDate("12/04/2014 23:55:59", 0.5);
		
		// Check for Header
		addFeature("Has a proper header", 5, new ProperHeaderTestCase("COMP110-001, Fall 2014"));
		//Check for Capitalization
		addFeature("Naming Style",5,new CapitalizationStyle());
		//Check prompt
		addFeature("Has Prompt",10, new PromptTest());
		//Check prompt again
		addFeature("Prompts again",5,new PromptAgain());
		//Handling Prompt Again
		addFeature("Handling prompt again",15,new HandlingPrompts());
		//Check variables
		addFeature("Contains desired variables in KitKatFactory",10,new VariableTest());
		//manual cases
		//implementation of methods in KitKatfactory
		addFeature("Methods in KitKat factory are implemented correctly",30,new KitKatMethods());
		//implementation of methods in MYOChocolates
		addFeature("Methods in MYOChocolates are implemented correctly",20,new MYOMethods());
		
		addFeature("Correct Output (for grader's use only)",0,new CorrectOutput());
	}
}
