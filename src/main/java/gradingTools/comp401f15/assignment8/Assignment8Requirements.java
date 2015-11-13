package gradingTools.comp401f15.assignment8;

import framework.grading.FrameworkProjectRequirements;
import static framework.grading.ProjectRequirements.INTERACTIVE_RUN;
import gradingTools.assignment6.testCases.*;
import gradingTools.assignment8.testCases.*;
import gradingTools.comp401f15.assignment1.testcases.MainClassDefinedTestCase;
import gradingTools.comp401f15.assignment3.testcases.HasInterfaceTestCase;
import gradingTools.comp401f15.assignment3.testcases.VariableHasClassTypeTestCase;
import gradingTools.comp401f15.assignment8.testCase.ConsoleScenePrintsTestCase;
import gradingTools.sharedTestCase.ClassDefinedTestCase;
import gradingTools.sharedTestCase.IllegalImportOrCallTestCase;
import gradingTools.sharedTestCase.NoWarningOrErrorTestCase;

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
        addFeature("Console View is a listener", 25,
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
    	addRestriction("Variable has class type", 5, new VariableHasClassTypeTestCase("Variable has class type"));
        addRestriction("Illegal import or call", 25, new IllegalImportOrCallTestCase());
        addRestriction("Single main.Assignment", 10, new MainClassDefinedTestCase("main.Assignment(.*)"));
        addRestriction("Classes Tagged ", 39, 
        		new ClassDefinedTestCase("@Call"),
        		new ClassDefinedTestCase("@Define"),
        		new ClassDefinedTestCase("@Move"),
        		new ClassDefinedTestCase("@ProceedAll"),
        		new ClassDefinedTestCase("@Redo"),
        		new ClassDefinedTestCase("@Repeat"),
        		new ClassDefinedTestCase("@RotateLeftArm"),
        		new ClassDefinedTestCase("@RotateRightArm"),
        		new ClassDefinedTestCase("@Say"),
        		new ClassDefinedTestCase("@Sleep"),
        		new ClassDefinedTestCase("@Thread"),
        		new ClassDefinedTestCase("@Undo"),
        		new ClassDefinedTestCase("@Wait"));
        addManualRestriction(INTERACTIVE_RUN, 5, new NoWarningOrErrorTestCase("OE Warnings", ".*(efresh|not in range|Hashtable Pattern).*", ".*Assuming implicit pattern.*", 0.3));

    }
}
