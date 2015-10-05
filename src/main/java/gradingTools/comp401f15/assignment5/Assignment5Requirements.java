package gradingTools.comp401f15.assignment5;

import framework.grading.FrameworkProjectRequirements;
import framework.grading.testing.Restriction;
import gradingTools.assignment6.testCases.EncapsulationTestCase;
import gradingTools.assignment6.testCases.InterfaceTypeTestCase;
import gradingTools.assignment6.testCases.MainClassTestCase;
import gradingTools.assignment6.testCases.SystemExitTestCase;
import gradingTools.assignment6.testCases.ThreePackageTestCase;
import gradingTools.comp401f15.assignment1.testcases.MainClassDefinedTestCase;
import gradingTools.comp401f15.assignment3.testcases.HasInterfaceTestCase;
import gradingTools.comp401f15.assignment3.testcases.VariableHasClassTypeTestCase;
import gradingTools.comp401f15.assignment4.testcases.CommonTokenInterfaceNotTTestCase;
import gradingTools.comp401f15.assignment4.testcases.ScannerBeanReturnsTokenInterfaceArrayTestCase;
import gradingTools.comp401f15.assignment4.testcases.ScannerHasErrorPropertyTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.CallCommandCreatedTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.DefineCommandCreatedTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.MoveCommandCreatedTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.ProceedAllCommandCreatedTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.RedoCommandCreatedTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.RepeatCommandCreatedTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.RotateLeftArmCommandCreatedTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.RotateRightArmCommandCreatedTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.SayCommandCreatedTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.SleepCommandCreatedTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.ThreadCommandCreatedTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.UndoCommandCreatedTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.WaitCommandCreatedTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.function.CallCommandBeanTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.function.DefineCommandBeanTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.function.MoveCommandBeanTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.function.ProceedAllCommandBeanTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.function.RedoCommandBeanTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.function.RepeatCommandBeanTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.function.RotateLeftArmCommandBeanTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.function.RotateRightArmCommandBeanTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.function.SayCommandBeanTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.function.SleepCommandBeanTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.function.ThreadCommandBeanTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.function.UndoCommandBeanTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.function.WaitCommandBeanTestCase;
import gradingTools.comp401f15.assignment4.testcases.rotateLine.RotatingLineDefinedTestCase;
import gradingTools.comp401f15.assignment4.testcases.rotateLine.RotatingFixedLineMethodsTestCase;
import gradingTools.comp401f15.assignment4.testcases.rotateLine.RotatingMovingLineMethodsTestCase;
import gradingTools.sharedTestCase.ClassDefinedTestCase;
import gradingTools.sharedTestCase.IllegalImportOrCallTestCase;
import gradingTools.sharedTestCase.NoWarningOrErrorTestCase;


public class Assignment5Requirements extends FrameworkProjectRequirements {

    public Assignment5Requirements() {
    	addDueDate("09/23/2015 23:59:00", 1.05);
    	addDueDate("09/25/2015 23:59:00", 1);
    	addDueDate("09/30/2015 23:59:00", 0.9);
    	addDueDate("10/02/2015 23:59:00", 0.75);
        
        
        
        
    	addFeature("Class has interface check", 5, new HasInterfaceTestCase("Has interface"));
    	addFeature("Variable has class type", 5, new VariableHasClassTypeTestCase("Variable has class type"));

        
        addRestriction("Classes Tagged ", 9, 
        		new ClassDefinedTestCase("@Angle"),
        		new ClassDefinedTestCase("@Avatar"),
        		new ClassDefinedTestCase("@BridgeScene")
        	);
        addRestriction(new Restriction("No public variables.", -5, new EncapsulationTestCase("Encapsulation test case")));
        addRestriction(new Restriction("At least three packages.", -5, new ThreePackageTestCase("Three package test case")));
        // TODO: Method/interface check
        addRestriction(new Restriction("No System.exit()", -5, new SystemExitTestCase("System.exit test case")));
//        addManualRestriction(INTERACTIVE_RUN, 10, new NoWarningOrErrorTestCase("OE Warnings", ".*efresh.* | .*not in range.*", 0.3));
        addManualRestriction(INTERACTIVE_RUN, 5, new NoWarningOrErrorTestCase("OE Warnings", ".*(efresh|not in range).*", null, 0.3));

    }
}
