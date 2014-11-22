package gradingTools.comp110f14.assignment5;
import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp110.assignment1.testcases.ProperHeaderTestCase;
import gradingTools.comp110f14.assignment4testcases.CapitalizationStyle;
import gradingTools.comp110f14.assignment5testcases.CorrectStringDecoder;
import gradingTools.comp110f14.assignment5testcases.CorrectStringEncoder;
import gradingTools.comp110f14.assignment5testcases.hasOperateEncoder;
import gradingTools.comp110f14.assignment5testcases.hasStringDecoder;
import gradingTools.comp110f14.assignment5testcases.hasStringEncoder;
import gradingTools.sharedTestCase.ASimpleMethodMatcher;
import gradingTools.sharedTestCase.MethodMatcher;

public class Assignment5Requirements extends FrameworkProjectRequirements {
	boolean stringfirsten=false;
	boolean stringfirstde=false;
	char[] cipher={ 'Z', 'Y', 'X', 'W', 'V', 'U', 'T', 'S', 'R', 'Q',
			'P', 'O', 'N', 'M', 'L', 'K', 'J', 'I', 'H', 'G', 'F', 'E',
			'D', 'C', 'B', 'A' };
	String toencode="goobear";
	String todecode="YLOWYVZI";
	
	public Assignment5Requirements(){
		addDueDate("11/21/2014 23:55:59", 1.0);
		addDueDate("11/22/2014 23:55:59", 0.5);
		
		// Check for Header
		addFeature("Has a proper header", 5, new ProperHeaderTestCase("COMP110-001, Fall 2014"));
		//Check for Capitalization
		addFeature("Naming Style",5,new CapitalizationStyle());
		//Initial Prompt
		
		//has OperateEncoder Method
		addFeature("has OperateEncoder method",4,new hasOperateEncoder());
		//has StringDecoder method
		addFeature("has StringDecoder method",3,new hasStringDecoder());
		this.stringfirstde= new hasStringDecoder().stringfirstde;
		//has StringEncoder method
		addFeature("has StringEncoder method",3,new hasStringEncoder());
		this.stringfirsten=new hasStringEncoder().stringfirsten;
		
		//working StringEncoder
		MethodMatcher encodetest = new ASimpleMethodMatcher("stringEncoder");
		Object[][] argsen = new Object[1][2];
		if(stringfirsten){
			argsen[0][0]=toencode;
			argsen[0][1]=cipher;
		}
		else{
			argsen[0][0]=cipher;
			argsen[0][1]=toencode;
		}
		Object[] reten = new Object[1];
		reten[0]="TLLYVZI";
		addFeature("Correct return for a call to StringEncoder",30,new CorrectStringEncoder(encodetest, argsen, reten, 500));
		
		//Working StringDecoder
		MethodMatcher decodetest = new ASimpleMethodMatcher("stringDecoder");
		Object[][] argsde = new Object[1][2];
		if(stringfirstde){
			argsde[0][0]=todecode;
			argsde[0][1]=cipher;
		}
		else{
			argsde[0][0]=cipher;
			argsde[0][1]=todecode;
		}
		Object[] retde = new Object[1];
		retde[0]="BOLDBEAR";
		addFeature("Correct return for a call to StringDecoder",20,new CorrectStringDecoder(decodetest, argsde, retde, 500));
		
	}
}
