package gradingTools.comp401f15.assignment5;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.assignment6.testCases.EncapsulationTestCase;
import gradingTools.assignment6.testCases.SystemExitTestCase;
import gradingTools.assignment6.testCases.ThreePackageTestCase;
import gradingTools.comp401f15.assignment1.testcases.MainClassDefinedTestCase;
import gradingTools.comp401f15.assignment3.testcases.HasInterfaceTestCase;
import gradingTools.comp401f15.assignment3.testcases.VariableHasClassTypeTestCase;
import gradingTools.sharedTestCase.ClassDefinedTestCase;
import gradingTools.sharedTestCase.NoWarningOrErrorTestCase;
import gradingTools.sharedTestCase.CheckstylePropertyDefinedTestCase;


public class Assignment5Requirements extends FrameworkProjectRequirements {

    public Assignment5Requirements() {
    	addDueDate("09/23/2015 23:59:00", 1.05);
    	addDueDate("09/25/2015 23:59:00", 1);
    	addDueDate("09/30/2015 23:59:00", 0.9);
    	addDueDate("10/02/2015 23:59:00", 0.75);
    	 addFeature("Properties Defined ", 9, 
         		new CheckstylePropertyDefinedTestCase("LeftLine")
         		
         	);
        
    	addFeature("Class has interface check", 5, new HasInterfaceTestCase("Has interface"));
    	addFeature("Variable has class type", 5, new VariableHasClassTypeTestCase("Variable has class type"));
        addRestriction("Single main.Assignment", 10, new MainClassDefinedTestCase("main.Assignment(.*)"));
        addRestriction("Classes Tagged ", 9, 
        		new ClassDefinedTestCase("@Angle"),
        		new ClassDefinedTestCase("@Avatar"),
        		new ClassDefinedTestCase("@BridgeScene")
        	);
        addRestriction("No public variables.", -5, new EncapsulationTestCase("Encapsulation test case"));
        addRestriction("At least three packages.", -5, new ThreePackageTestCase("Three package test case"));
        // TODO: Method/interface check
        addRestriction("No System.exit()", -5, new SystemExitTestCase("System.exit test case"));
//        addManualRestriction(INTERACTIVE_RUN, 10, new NoWarningOrErrorTestCase("OE Warnings", ".*efresh.* | .*not in range.*", 0.3));
        addManualRestriction(INTERACTIVE_RUN, 5, new NoWarningOrErrorTestCase("OE Warnings", ".*(efresh|not in range).*", null, 0.3));

    }
}
