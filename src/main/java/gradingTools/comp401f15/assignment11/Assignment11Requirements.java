package gradingTools.comp401f15.assignment11;

import gradingTools.comp401f15.assignment10.testCases.InterfaceImplementationTestCase;
import gradingTools.comp401f15.assignment11.testcases.*;
import gradingTools.comp401f15.assignment4.testcases.ScannerBeanReturnsTokenInterfaceArrayTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.CallCommandCreatedTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.DefineCommandCreatedTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.FailCommandCreatedTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.MoveCommandCreatedTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.created.PassCommandCreatedTestCase;
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
import gradingTools.comp401f15.assignment4.testcases.commands.function.FailCommandBeanTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.function.MoveCommandBeanTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.function.PassCommandBeanTestCase;
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
import wrappers.framework.grading.ExtendedProjectRequirements;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 11/19/13
 * Time: 9:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class Assignment11Requirements extends ExtendedProjectRequirements {

    public Assignment11Requirements() {
        addDueDate("11/16/2013 00:30:00", 1.05);
        addDueDate("11/19/2013 00:30:00", 1);
        addDueDate("11/21/2013 00:30:00", 0.9);
        addDueDate("11/23/2013 00:30:00", 0.75);

        // Abstract classes (3)
        addFeature("Abstract classes", 5,
                new AbstractClassTestCase("Locatable"),
                new AbstractClassTestCase("BoundedShape"));
//                new AbstractAncestorTokenTestCase());
//      //Do the command tokens exist and do word stuff
//    	addFeature("Pass/Fail Commands Implemented", 2,
//    		
//
//    			new PassCommandBeanTestCase(),
//    			new FailCommandBeanTestCase()
//    			);
//        
    	// initialize token array
    	addFeature("Scanner Initialization", 0,
        		new ScannerBeanReturnsTokenInterfaceArrayTestCase()    			
    			);
    	addFeature("Scanner Makes Pass and Fail", 4,
    			new PassCommandCreatedTestCase(),
    			new FailCommandCreatedTestCase()
    			
    			);
        addFeature("Passed failed approach cmd obj", 5, 
        		new InterfaceImplementationTestCase("PassCommand", Runnable.class)
        				);
        		
        		

        // New commands (17)
//        addFeature("Passed & failed", 2);
        addFeature("Passed failed approach cmd obj", 5, new AdditionalCommandObjectsChecker());
        addFeature("Command list cmd obj", 5, new CommandListCommandObjectChecker());
        addFeature("Repeat command", 5, new RepeatCommandObjectChecker());

        //Parsing (70)
        addFeature("Methods for all 8 nonterminals", 10, new ParserMethodChecker());
//        addManualFeature("Recursive cmd list commands", 40,
//                new QuestionTestCase("Does the command list parser recursively parse?", "Recursive command list test case"),
//                new QuestionTestCase("Test the command interpreter. Do command lists work properly?", "Command list functionality test case"));
//        addManualFeature("Recursive repeat commands", 20,
//                new QuestionTestCase("Does the repeat command parser recursively parse?", "Recursive repeat command test case"),
//                new QuestionTestCase("Test the command interpreter. Do repeat commands work properly?", "Command list functionality test case"));

        // Animation (20)
        addManualFeature("Synchronized avatar methods", 10);
//        addManualFeature("Coordinated animation", 10);

        // Generics (10)
        addManualFeature("Generic table", 10);

        // Extra Credit
//        addFeature("Lockstep animation", 3, true);
//        addFeature("Signed number parsing", 2, true);
//        addFeature("Extended grammar parsing", 15, true);
//        addFeature("Exceptions", 10, true);
//        addFeature("Undo/redo", 5, true);

    }
}
