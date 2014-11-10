package gradingTools.comp110f14lab.lab6;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.MainMethodTestCase;
import gradingTools.comp110f14lab.lab6.testcases.HasprintCheck;
import gradingTools.comp110f14lab.lab6.testcases.HasreplaceTypo;


public class Lab6Requirements extends FrameworkProjectRequirements{
	public Lab6Requirements(){
		addDueDate("11/11/2014 23:55:59", 1.0);
		addDueDate("11/12/2014 23:55:59", 0.5);
		
		//check for main method
		addFeature("Contains a main method", 5, new MainMethodTestCase());
		
		//check for existence of replaceTypo method
		addFeature("Contains replaceTypo method",1.6,new HasreplaceTypo());
		
		//check for existence of printCheck method
		addFeature("Contains printCheck method",1.6,new HasprintCheck());
	}
}
