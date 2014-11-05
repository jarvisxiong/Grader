package gradingTools.comp110f14.assignment4;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.ProperHeaderTestCase;
import gradingTools.comp110f14.assignment4testcases.CapitalizationStyle;
import gradingTools.comp110f14.assignment4testcases.ColorBlindTest;
import gradingTools.comp110f14.assignment4testcases.EyeColorTest;
import gradingTools.comp110f14.assignment4testcases.HandlingAskingAgain;
import gradingTools.comp110f14.assignment4testcases.NotEnoughInfoTestCase;
import gradingTools.comp110f14.assignment4testcases.askAgain;
import gradingTools.comp110f14.assignment4testcases.promptCase;

public class Assignment4Requirements extends FrameworkProjectRequirements {
	public Assignment4Requirements(){
		addDueDate("10/31/2014 23:55:59", 1.0);//temp
		addDueDate("11/01/2014 23:55:59", 0.5);//temp
		

		// Check for Header
		addFeature("Has a proper header", 5, new ProperHeaderTestCase("COMP110-001, Fall 2014"));
		//Check Cap Style
		addFeature("Naming Style",5,new CapitalizationStyle());
		//Check for initial prompt
		addFeature("Initial prompt",10, new promptCase());
		//Check for Not Enough Info Message
		addFeature("Handling not enough information given.",10, new NotEnoughInfoTestCase());
		//Check eye color check
		addFeature("Eye Color Check",30,new EyeColorTest());
		//Check for color blindness
		addFeature("Blindness Check",20,new ColorBlindTest());
		//asked again?
		addFeature("Asking for another expression",5,new askAgain());
		//handled asking again response
		addFeature("Handling response to asking again",15,new HandlingAskingAgain());
		
	}
}
