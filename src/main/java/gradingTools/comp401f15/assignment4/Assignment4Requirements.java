package gradingTools.comp401f15.assignment4;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.assignment6.testCases.ManualTestCase;
import gradingTools.comp401f15.assignment1.testcases.ImageEnclosedTestCase;
import gradingTools.comp401f15.assignment1.testcases.MainClassDefinedTestCase;
import gradingTools.comp401f15.assignment3.testcases.EndClassTestCase;
import gradingTools.comp401f15.assignment3.testcases.EndEchoTestCase;
import gradingTools.comp401f15.assignment3.testcases.EndTokenBeanTestCase;
import gradingTools.comp401f15.assignment3.testcases.HasInterfaceTestCase;
import gradingTools.comp401f15.assignment3.testcases.MinusClassTestCase;
import gradingTools.comp401f15.assignment3.testcases.MinusEchoTestCase;
import gradingTools.comp401f15.assignment3.testcases.MinusTokenBeanTestCase;
import gradingTools.comp401f15.assignment3.testcases.NumberClassTestCase;
import gradingTools.comp401f15.assignment3.testcases.NumberComputationTestCase;
import gradingTools.comp401f15.assignment3.testcases.PlusClassTestCase;
import gradingTools.comp401f15.assignment3.testcases.PlusEchoTestCase;
import gradingTools.comp401f15.assignment3.testcases.PlusTokenBeanTestCase;
import gradingTools.comp401f15.assignment3.testcases.QuoteClassTestCase;
import gradingTools.comp401f15.assignment3.testcases.QuoteEchoTestCase;
import gradingTools.comp401f15.assignment3.testcases.QuoteTokenBeanTestCase;
import gradingTools.comp401f15.assignment3.testcases.NumberEchoTestCase;
import gradingTools.comp401f15.assignment3.testcases.StartClassTestCase;
import gradingTools.comp401f15.assignment3.testcases.StartEchoTestCase;
import gradingTools.comp401f15.assignment3.testcases.StartTokenBeanTestCase;
import gradingTools.comp401f15.assignment3.testcases.VariableHasClassTypeTestCase;
import gradingTools.comp401f15.assignment3.testcases.WordClassTestCase;
import gradingTools.comp401f15.assignment3.testcases.WordComputationTestCase;
import gradingTools.comp401f15.assignment3.testcases.WordEchoTestCase;
import gradingTools.comp401f15.assignment4.testcases.CommonTokenInterfaceNotTTestCase;
import gradingTools.comp401f15.assignment4.testcases.ScannerBeanReturnsTokenInterfaceArrayTestCase;
import gradingTools.comp401f15.assignment4.testcases.ScannerHasErrorPropertyTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.CommandClassesExtendWordTokenTestCase;
import gradingTools.comp401f15.assignment4.testcases.commands.UniqueCommandClassesTestCase;
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
import gradingTools.comp401f15.assignment4.testcases.rotateLine.RotatingLineStructurePatternTestCase;
import gradingTools.comp401f15.assignment4.testcases.rotateLine.RotatingMovingLineMethodsTestCase;
import gradingTools.sharedTestCase.ClassDefinedTestCase;
import gradingTools.sharedTestCase.IllegalImportOrCallTestCase;
import gradingTools.sharedTestCase.NoWarningOrErrorTestCase;


public class Assignment4Requirements extends FrameworkProjectRequirements {

    public Assignment4Requirements() {
    	addDueDate("09/16/2015 23:59:00", 1.05);
    	addDueDate("09/18/2015 23:59:00", 1);
    	addDueDate("09/23/2015 23:59:00", 0.9);
    	addDueDate("09/25/2015 23:59:00", 0.75);
        
        //Do the command tokens exist and do word stuff
    	addFeature("Command Classes Implemented", 15,
    			new CallCommandBeanTestCase(),
    			new DefineCommandBeanTestCase(),
    			new MoveCommandBeanTestCase(),
    			new ProceedAllCommandBeanTestCase(),
    			new RedoCommandBeanTestCase(),
    			new RepeatCommandBeanTestCase(),
    			new RotateLeftArmCommandBeanTestCase(),
    			new RotateRightArmCommandBeanTestCase(),
    			new SayCommandBeanTestCase(),
    			new SleepCommandBeanTestCase(),
    			new ThreadCommandBeanTestCase(),
    			new UndoCommandBeanTestCase(),
    			new WaitCommandBeanTestCase()
    			);
//        //Does each command has a unique class
//        addFeature("Every Command Has Its Own Class", 10, new UniqueCommandClassesTestCase());
        
//        //Do command tokens extend word
//        addFeature("Command Tokens Extend Word Token", 30, new CommandClassesExtendWordTokenTestCase());

        // uses array (or they make an array to return, but checkstyle should catch that)
        addFeature("Scanner bean returns token array", 10, new ScannerBeanReturnsTokenInterfaceArrayTestCase());
        //Correct output from scanner
        addFeature("Scanner Makes Command Tokens", 20,
    			new CallCommandCreatedTestCase(),
    			new DefineCommandCreatedTestCase(),
    			new MoveCommandCreatedTestCase(),
    			new ProceedAllCommandCreatedTestCase(),
    			new RedoCommandCreatedTestCase(),
    			new RepeatCommandCreatedTestCase(),
    			new RotateLeftArmCommandCreatedTestCase(),
    			new RotateRightArmCommandCreatedTestCase(),
    			new SayCommandCreatedTestCase(),
    			new SleepCommandCreatedTestCase(),
    			new ThreadCommandCreatedTestCase(),
    			new UndoCommandCreatedTestCase(),
    			new WaitCommandCreatedTestCase()
    			);
        
        
    	addFeature("Class has interface check", 5, new HasInterfaceTestCase("Has interface"));
    	addFeature("Variable has class type", 5, new VariableHasClassTypeTestCase("Variable has class type"));

        addManualFeature("Scanner object displays properly in ObjectEditor and tests for every token type", 15);
        
        /*
         * Interface stuff goes here. I think that is checkstyle, but I'm not sure what the case for that is
         */
        
        // scanner error array
    	addFeature("Scanner has error property", 5, true, new ScannerHasErrorPropertyTestCase());
    	addManualFeature("Scanner displays errors correctly", 5, true);

        // will do with warnings
        
//    	addFeature("Rotating line pattern", 2, true,
//    			new RotatingLineStructurePatternTestCase()
//    			);
        addFeature("Rotating fixed line", 6, true,
    				new RotatingLineDefinedTestCase(),

                        new RotatingFixedLineMethodsTestCase() // add execution tests
                        );
        addFeature("Rotating moving line", 5, true,
                        new RotatingMovingLineMethodsTestCase() // add execution tests
                        );
        
        addManualFeature("Object Editor rotating, moving line", 24, true);
        
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
        addRestriction("Common token interface not T", 5, new CommonTokenInterfaceNotTTestCase());
//        addManualRestriction(INTERACTIVE_RUN, 10, new NoWarningOrErrorTestCase("OE Warnings", ".*efresh.* | .*not in range.*", 0.3));
        addManualRestriction(INTERACTIVE_RUN, 5, new NoWarningOrErrorTestCase("OE Warnings", ".*(efresh|not in range).*", ".*Assuming implicit pattern.*", 0.3));

    }
}
