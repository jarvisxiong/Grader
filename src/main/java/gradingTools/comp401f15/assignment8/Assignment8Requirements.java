package gradingTools.comp401f15.assignment8;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.assignment6.testCases.EncapsulationTestCase;
import gradingTools.assignment6.testCases.SystemExitTestCase;
import gradingTools.assignment6.testCases.ThreePackageTestCase;
import gradingTools.assignment8.testCases.ConsoleViewConstructorAddListenerTestCase;
import gradingTools.assignment8.testCases.ConsoleViewConstructorParameterTestCase;
import gradingTools.assignment8.testCases.ConsoleViewListenerTestCase;
import gradingTools.assignment8.testCases.ConsoleViewTagTestCase;
import gradingTools.assignment8.testCases.ListenerCollectionTestCase;
import gradingTools.assignment8.testCases.SettersNotifyTestCase;
import gradingTools.assignment8.testCases.ShapesRegisterListenerTestCase;
import gradingTools.comp401f15.assignment1.testcases.MainClassDefinedTestCase;
import gradingTools.comp401f15.assignment3.testcases.HasInterfaceTestCase;
import gradingTools.comp401f15.assignment8.testCase.ConsoleScenePrintsTestCase;
import gradingTools.sharedTestCase.NoWarningOrErrorTestCase;
import gradingTools.sharedTestCase.checkstyle.CheckStyleIllegalImportOrCallTestCase;
import gradingTools.sharedTestCase.checkstyle.CheckStyleVariableHasClassTypeTestCase;

/**
 * Project 8 requirements.
 * See the rubric here: http://www.cs.unc.edu/~dewan/comp401/current/Recitations/
 */
public class Assignment8Requirements extends FrameworkProjectRequirements {

    public Assignment8Requirements() {

        // Add due date/times with a 30 minute grace period
        addDueDate("10/21/2015 23:59:00", 1.05);
        addDueDate("10/23/2015 23:59:00", 1);
        addDueDate("10/28/2015 23:59:00", 0.9);
        addDueDate("10/30/2015 23:59:00", 0.75);

        // Part 1
        addFeature("Shapes implement PropertyListenerRegisterer", 20, new ShapesRegisterListenerTestCase());
        addFeature("Collection of observers", 5, new ListenerCollectionTestCase());
        addFeature("Setters notify observers", 10, new SettersNotifyTestCase());

        // Part 2
        addFeature("Console View tag", 5, new ConsoleViewTagTestCase());
        addFeature("Console View is a listener", 5, new ConsoleViewListenerTestCase());
        addFeature("Console View is a listener 2", 25,
                new ConsoleViewConstructorParameterTestCase(),
                new ConsoleViewConstructorAddListenerTestCase());
        
        addFeature("Console View functionality", 25, new ConsoleScenePrintsTestCase());

        // Part 3
        addManualFeature("Does the avatar move?", 5);
        addManualFeature("Are the move events printed to the console?", 5);
        addManualFeature("Does the avatar's text change?", 5);
        addManualFeature("Is the text event printed to the console?", 5);
        
        addManualFeature("Do the avatar parts rotate?", 5, true);
        addManualFeature("Are the rotate events printed to the console?", 10, true);
        
        // Define the restrictions
        addRestriction("No public variables.", 5, new EncapsulationTestCase("Encapsulation test case"));
        addRestriction("At least three packages.", 5, new ThreePackageTestCase("Three package test case"));
        // TODO: Method/interface check
        addRestriction("No System.exit()", 5, new SystemExitTestCase("System.exit test case"));
    	addRestriction("Class has interface check", 5, new HasInterfaceTestCase("Has interface"));
    	addRestriction("Variable has class type", 5, new CheckStyleVariableHasClassTypeTestCase("Variable has class type"));
        addRestriction("Illegal import or call", 25, new CheckStyleIllegalImportOrCallTestCase());
        addRestriction("Single main.Assignment", 10, new MainClassDefinedTestCase("main.Assignment(.*)"));
        addManualRestriction(INTERACTIVE_RUN, 5, new NoWarningOrErrorTestCase("OE Warnings", ".*(efresh|not in range|Hashtable Pattern).*", ".*Assuming implicit pattern.*", 0.3));

    }
}
