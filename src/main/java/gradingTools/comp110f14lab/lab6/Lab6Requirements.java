package gradingTools.comp110f14lab.lab6;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.MainMethodTestCase;
import gradingTools.comp110f14lab.lab6.testcases.HasprintCheck;
import gradingTools.comp110f14lab.lab6.testcases.HasreplaceTypo;
import gradingTools.comp110f14lab.lab6.testcases.correctreplaceTypo;
import gradingTools.sharedTestCase.ASimpleMethodMatcher;
import gradingTools.sharedTestCase.MethodMatcher;


public class Lab6Requirements extends FrameworkProjectRequirements{
	public Lab6Requirements(){
		addDueDate("11/11/2014 23:55:59", 1.0);
		addDueDate("11/12/2014 23:55:59", 0.5);
		
		//check for main method
		addFeature("Contains a main method", 5, new MainMethodTestCase());
		
		//check for existence of printCheck method
				addFeature("Contains printCheck method",1.2,new HasprintCheck());
		//check for existence of replaceTypo method
		addFeature("Contains replaceTypo method",1.2,new HasreplaceTypo());
		
		
		
		MethodMatcher goo = new ASimpleMethodMatcher("replaceTypo");
		Object[][] args = new Object[1][3];
		Object[] ret = new Object[1];
		args[0][0]="oog is my favorite color";
		args[0][1]="oog";
		args[0][2]="goo";
		ret[0]="goo is my favorite color";
		//correct return for a call to replaceTypo
		addFeature("Correct return for a call to replaceTypo",2.6,new correctreplaceTypo(goo, args, ret, 500));
	}
}
