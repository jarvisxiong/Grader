package gradingTools.comp401f15.assignment7;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.assignment6.testCases.EncapsulationTestCase;
import gradingTools.assignment6.testCases.SystemExitTestCase;
import gradingTools.assignment6.testCases.ThreePackageTestCase;
import gradingTools.assignment7.testCases.BasicPutAndGetTestCase;
import gradingTools.assignment7.testCases.BoundedShapeExtendsLocatableTestCase;
import gradingTools.assignment7.testCases.BoundedShapeInheritanceTestCase;
import gradingTools.assignment7.testCases.BoundedShapeTagTestCase;
import gradingTools.assignment7.testCases.GetInvalidTestCase;
import gradingTools.assignment7.testCases.GetNullTestCase;
import gradingTools.assignment7.testCases.LocatableInheritanceTestCase;
import gradingTools.assignment7.testCases.LocatableTagTestCase;
import gradingTools.assignment7.testCases.PutExistingTestCase;
import gradingTools.assignment7.testCases.PutNullTestCase;
import gradingTools.comp401f15.assignment1.testcases.MainClassDefinedTestCase;
import gradingTools.comp401f15.assignment3.testcases.HasInterfaceTestCase;
import gradingTools.comp401f15.assignment7.testCases.commandInterpreter.ErrorResilientCommandInterpreterDefinedTestCase;
import gradingTools.comp401f15.assignment7.testCases.commandInterpreter.ErrorResilientCommandInterpreterFunctionTestCase;
import gradingTools.comp401f15.assignment7.testCases.commandInterpreter.MoveCommandInterpretedTestCase;
import gradingTools.comp401f15.assignment7.testCases.commandInterpreter.SayCommandInterpretedTestCase;
import gradingTools.comp401f15.assignment7.testCases.commandInterpreter.SignedMoveCommandInterpretedTestCase;
import gradingTools.sharedTestCase.MethodDefinedTestCase;
import gradingTools.sharedTestCase.NoWarningOrErrorTestCase;
import gradingTools.sharedTestCase.PropertyDefinedTestCase;
import gradingTools.sharedTestCase.ReflectiveClassDefinedTestCase;
import gradingTools.sharedTestCase.ReflectiveInterfaceDefinedTestCase;
import gradingTools.sharedTestCase.checkstyle.CheckStyleClassDefinedTestCase;
import gradingTools.sharedTestCase.checkstyle.CheckStyleIllegalImportOrCallTestCase;
import gradingTools.sharedTestCase.checkstyle.CheckStyleVariableHasClassTypeTestCase;


public class Assignment7Requirements extends FrameworkProjectRequirements {

    public Assignment7Requirements() {
    	addDueDate("10/07/2015 23:59:00", 1.05);
    	addDueDate("10/09/2015 23:59:00", 1);
    	addDueDate("10/21/2015 23:59:00", 0.9);
    	addDueDate("10/23/2015 23:59:00", 0.75);
    	
        addFeature("Locatable class and interface", 15,
                new ReflectiveClassDefinedTestCase("Locatable", ".*[lL]ocatable.*", ".*[lL]ocatable.*"),
                new ReflectiveInterfaceDefinedTestCase("Locatable", ".*[lL]ocatable.*", ".*[lL]ocatable.*"),
                new MethodDefinedTestCase("Locatable", "getX", null, "^((?![sS][eE][tT]).)*[xX].*", int.class),
                new MethodDefinedTestCase("Locatable", "getY", null, "^((?![sS][eE][tT]).)*[yY].*", int.class),
                new LocatableTagTestCase(),
                new LocatableInheritanceTestCase()
        );

        addFeature("BoundedShape class and interface", 15,
                new ReflectiveClassDefinedTestCase("BoundedShape", ".*[bB]ounded[sS]hape.*", ".*[bB]ounded[sS]hape.*"),
                new ReflectiveInterfaceDefinedTestCase("BoundedShape", ".*[bB]ounded[sS]hape.*", ".*[bB]ounded[sS]hape.*"),
                new BoundedShapeTagTestCase(),
                new BoundedShapeInheritanceTestCase(),
                new BoundedShapeExtendsLocatableTestCase()
        );

        addFeature("Table class tagged", 5, new ReflectiveClassDefinedTestCase("Table"));
        addFeature("Put and get methods", 24,
                new BasicPutAndGetTestCase(),
                new PutNullTestCase(),
                new GetNullTestCase(),
                new GetInvalidTestCase(),
                new PutExistingTestCase()
                );
        addManualFeature("Table demoed", 6);

        // Command interpreter

        addFeature("Command interpreter tag", 5, new ReflectiveClassDefinedTestCase("CommandInterpreter", ".*[cC]ommand.*[iI]nterpreter.*", ".*[cC]ommand[iI]nterpreter.*"));
        addFeature("Editable property", 5, new PropertyDefinedTestCase("CommandInterpreter", "Command", Object.class));
        addFeature("Command interpretation", 20,
                new SayCommandInterpretedTestCase(),
                new MoveCommandInterpretedTestCase());
        
        addManualFeature("Is the command interpreter demoed?", 5);

        // Extra credit
        addFeature("Signed move", 8, true,
                new CheckStyleClassDefinedTestCase("@SignedMove"),
                new SignedMoveCommandInterpretedTestCase());
        addFeature("Error Resilient Command Interpreter", 7, true,
                new ErrorResilientCommandInterpreterDefinedTestCase(),
                new ErrorResilientCommandInterpreterFunctionTestCase());
        
        // Define the restrictions

        addRestriction("No public variables.", 5, new EncapsulationTestCase("Encapsulation test case"));
        addRestriction("At least three packages.", 5, new ThreePackageTestCase("Three package test case"));
        // TODO: Method/interface check
        addRestriction("No System.exit()", 5, new SystemExitTestCase("System.exit test case"));
        
    	addRestriction("Class has interface check", 5, new HasInterfaceTestCase("Has interface"));
    	addRestriction("Variable has class type", 5, new CheckStyleVariableHasClassTypeTestCase("Variable has class type"));
        
//        addFeature("Screenshots enclosed", 10, new ImageEnclosedTestCase());
        
        addRestriction("Illegal import or call", 25, new CheckStyleIllegalImportOrCallTestCase());
        addRestriction("Single main.Assignment", 10, new MainClassDefinedTestCase("main.Assignment(.*)"));
        addRestriction("Classes Tagged ", 39, 
        		new CheckStyleClassDefinedTestCase("@Call"),
        		new CheckStyleClassDefinedTestCase("@Define"),
        		new CheckStyleClassDefinedTestCase("@Move"),
        		new CheckStyleClassDefinedTestCase("@ProceedAll"),
        		new CheckStyleClassDefinedTestCase("@Redo"),
        		new CheckStyleClassDefinedTestCase("@Repeat"),
        		new CheckStyleClassDefinedTestCase("@RotateLeftArm"),
        		new CheckStyleClassDefinedTestCase("@RotateRightArm"),
        		new CheckStyleClassDefinedTestCase("@Say"),
        		new CheckStyleClassDefinedTestCase("@Sleep"),
        		new CheckStyleClassDefinedTestCase("@Thread"),
        		new CheckStyleClassDefinedTestCase("@Undo"),
        		new CheckStyleClassDefinedTestCase("@Wait"));
//        addManualRestriction(INTERACTIVE_RUN, 10, new NoWarningOrErrorTestCase("OE Warnings", ".*efresh.* | .*not in range.*", 0.3));
        addManualRestriction(INTERACTIVE_RUN, 5, new NoWarningOrErrorTestCase("OE Warnings", ".*(efresh|not in range|Hashtable Pattern).*", ".*Assuming implicit pattern.*", 0.3));

    }
}
