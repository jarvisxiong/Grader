package gradingTools.comp401f15.assignment7;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.assignment6.testCases.EncapsulationTestCase;
import gradingTools.assignment6.testCases.QuestionTestCase;
import gradingTools.assignment6.testCases.SystemExitTestCase;
import gradingTools.assignment6.testCases.ThreePackageTestCase;
import gradingTools.assignment7.testCases.BasicPutAndGetTestCase;
import gradingTools.assignment7.testCases.BoundedShapeExtendsLocatableTestCase;
import gradingTools.assignment7.testCases.BoundedShapePropertiesTestCase;
import gradingTools.assignment7.testCases.BoundedShapeTagTestCase;
import gradingTools.assignment7.testCases.CmdIntEditablePropTestCase;
import gradingTools.assignment7.testCases.CmdIntTagTestCase;
import gradingTools.assignment7.testCases.GetInvalidTestCase;
import gradingTools.assignment7.testCases.GetNullTestCase;
import gradingTools.assignment7.testCases.LocatablePropertiesTestCase;
import gradingTools.assignment7.testCases.LocatableTagTestCase;
import gradingTools.assignment7.testCases.PutNullTestCase;
import gradingTools.assignment7.testCases.TableTagTestCase;
import gradingTools.comp401f15.assignment1.testcases.MainClassDefinedTestCase;
import gradingTools.comp401f15.assignment3.testcases.HasInterfaceTestCase;
import gradingTools.comp401f15.assignment3.testcases.VariableHasClassTypeTestCase;
import gradingTools.sharedTestCase.ClassDefinedTestCase;
import gradingTools.sharedTestCase.IllegalImportOrCallTestCase;
import gradingTools.sharedTestCase.NoWarningOrErrorTestCase;


public class Assignment7Requirements extends FrameworkProjectRequirements {

    public Assignment7Requirements() {
    	addDueDate("09/16/2015 23:59:00", 1.05);
    	addDueDate("09/18/2015 23:59:00", 1);
    	addDueDate("09/23/2015 23:59:00", 0.9);
    	addDueDate("09/25/2015 23:59:00", 0.75);
        addFeature("Locatable class and interface", 15,
                new LocatableTagTestCase(),
                new LocatablePropertiesTestCase()//,
//                new LocatableInheritanceTestCase()
                // TODO: More refactoring?
        );

        addFeature("Bounded shape class and interface", 15,
                new BoundedShapeTagTestCase(),
                new BoundedShapePropertiesTestCase(),
//                new BoundedShapeInheritanceTestCase(),
                new BoundedShapeExtendsLocatableTestCase()
                // TODO: More refactoring?
        );

        addFeature("Table class tagged", 5, new TableTagTestCase());
        addFeature("Put and get methods", 30,
                new BasicPutAndGetTestCase(),
                new PutNullTestCase(),
                new GetNullTestCase(),
                new GetInvalidTestCase()

//                new PutNewTestCase(),
//                new PutExistingTestCase(),

//        new QuestionTestCase("When adding an item w/ a new key, is it added?", "Put new test case"),
//                new QuestionTestCase("When adding an item w/ an existing key, is it replaced?", "Put existing test case"),
//                new QuestionTestCase("When adding an item w/ a null key, does nothing happen?", "Put null test case"),
//                new QuestionTestCase("Is the put method demoed?", "Put method demo test case")
                );
//        addFeature("Get method", 15,
//                new QuestionTestCase("When getting an item w/ a key, is it return?", "Get value test case"),
//                new QuestionTestCase("When getting an item w/ a non-existent key, is null returned?", "Get non-existent test case"),
//                new QuestionTestCase("Is the get method demoed?", "Get method demo test case")
//                );

        // Command interpreter

        addFeature("Command interpreter tag", 5, new CmdIntTagTestCase());
        addFeature("Editable property", 5, new CmdIntEditablePropTestCase());
        addManualFeature("Command interpretation", 25,
                new QuestionTestCase("In the command interpreter, Does the command get scanned?", "Command interpreter scanning test case"),
                new QuestionTestCase("Does the command interpreter correctly identify 'move' and 'say' commands?", "Command interpreter move/say test case"),
                new QuestionTestCase("Does the command interpreter invoke the move or say method?", "Command interpreter method invoke test case"),
                new QuestionTestCase("Is the command interpreter demoed?", "Command interpreter demo test case"));

        // Extra credit
        addManualFeature("Move supports signs and is tagged", 5, true);
        addManualFeature("Interpreter detects errors and is tagged", 5, true);
        addManualFeature("Nice Demo", 5, true);

        // Define the restrictions

        addRestriction("No public variables.", -5, new EncapsulationTestCase("Encapsulation test case"));
        addRestriction("At least three packages.", -5, new ThreePackageTestCase("Three package test case"));
        // TODO: Method/interface check
        addRestriction("No System.exit()", -5, new SystemExitTestCase("System.exit test case"));
        
    	addFeature("Class has interface check", 5, new HasInterfaceTestCase("Has interface"));
    	addFeature("Variable has class type", 5, new VariableHasClassTypeTestCase("Variable has class type"));
        
//        addFeature("Screenshots enclosed", 10, new ImageEnclosedTestCase());
        
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
//        addManualRestriction(INTERACTIVE_RUN, 10, new NoWarningOrErrorTestCase("OE Warnings", ".*efresh.* | .*not in range.*", 0.3));
        addManualRestriction(INTERACTIVE_RUN, 5, new NoWarningOrErrorTestCase("OE Warnings", ".*(efresh|not in range).*", ".*Assuming implicit pattern.*", 0.3));

    }
}
