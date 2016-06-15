package gradingTools.comp401f15.assignment4;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp401f15.assignment1.testcases.MainClassDefinedTestCase;
import gradingTools.comp401f15.assignment3.testcases.EndTokenBeanTestCase;
import gradingTools.comp401f15.assignment3.testcases.HasInterfaceTestCase;
import gradingTools.comp401f15.assignment3.testcases.NumberTokenBeanTestCase;
import gradingTools.comp401f15.assignment3.testcases.QuoteTokenBeanTestCase;
import gradingTools.comp401f15.assignment3.testcases.StartTokenBeanTestCase;
import gradingTools.comp401f15.assignment3.testcases.WordTokenBeanTestCase;
import gradingTools.comp401f15.assignment4.testcases.CommonTokenInterfaceNotTTestCase;
import gradingTools.comp401f15.assignment4.testcases.MultiRunScannerBeanTestCase;
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
import gradingTools.comp401f15.assignment4.testcases.rotateLine.RotatingFixedLineMethodsTestCase;
import gradingTools.comp401f15.assignment4.testcases.rotateLine.RotatingLineDefinedTestCase;
import gradingTools.comp401f15.assignment4.testcases.rotateLine.RotatingMovingLineMethodsTestCase;
import gradingTools.sharedTestCase.ClassDefinedTestCase;
import gradingTools.sharedTestCase.IllegalImportOrCallTestCase;
import gradingTools.sharedTestCase.NoWarningOrErrorTestCase;
import gradingTools.sharedTestCase.VariableHasClassTypeTestCase;


public class Assignment4Requirements extends FrameworkProjectRequirements {

    public Assignment4Requirements() {
    	addDueDate("09/16/2015 23:59:00", 1.05);
    	addDueDate("09/18/2015 23:59:00", 1);
    	addDueDate("09/23/2015 23:59:00", 0.9);
    	addDueDate("09/25/2015 23:59:00", 0.75);
    	// 0 points for previous assignments data
    	addFeature("Multi Property Token Beans", 0,
    			new NumberTokenBeanTestCase(),
    			new WordTokenBeanTestCase()
    			);
    	
    	addFeature("Single Property Token Beans", 0,
    			new QuoteTokenBeanTestCase(),
    			new StartTokenBeanTestCase(),
    			new EndTokenBeanTestCase()
    			);
    	
        
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
        addFeature("Scanner bean returns token array", 5, new ScannerBeanReturnsTokenInterfaceArrayTestCase());
        //Correct output from scanner
        addFeature("Scanner Makes Command Tokens", 17,
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
        
    	addFeature("Multiple scanner Sets check", 8, new MultiRunScannerBeanTestCase());

    	addFeature("Class has interface check", 5, new HasInterfaceTestCase());
    	addFeature("Variable has class type", 5, new VariableHasClassTypeTestCase());

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
