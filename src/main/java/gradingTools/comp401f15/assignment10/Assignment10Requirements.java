package gradingTools.comp401f15.assignment10;

import framework.grading.FrameworkProjectRequirements;

import gradingTools.assignment6.testCases.ManualTestCase;
import gradingTools.assignment6.testCases.QuestionTestCase;
import gradingTools.comp401f15.assignment10.testCases.*;
import gradingTools.comp401f15.assignment10.testCases.preConditions.FailedMethodPreconditionTestCase;
import gradingTools.comp401f15.assignment10.testCases.preConditions.PassedMethodPreconditionTestCase;
import gradingTools.comp401f15.assignment10.testCases.preConditions.SayMethodPreconditionTestCase;
import gradingTools.comp401f15.assignment11.testcases.SynchronizedAnimationTestCase;
import gradingTools.comp401f15.assignment7.testCases.commandInterpreter.MoveCommandInterpretedTestCase;
import gradingTools.comp401f15.assignment7.testCases.commandInterpreter.SayCommandInterpretedTestCase;
import gradingTools.sharedTestCase.CheckstyleConstructorDefinedTestCase;
import gradingTools.sharedTestCase.CheckstyleMethodCalledTestCase;
import gradingTools.sharedTestCase.CheckstyleMethodDefinedTestCase;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 11/11/13
 * Time: 9:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class Assignment10Requirements extends FrameworkProjectRequirements {

    public Assignment10Requirements() {

        // Add due date/times with a 30 minute grace period
        addDueDate("11/04/2015 12:59:00", 1.05);
        addDueDate("11/06/2015 12:59:00", 1);
        addDueDate("11/11/2015 12:59:00", 0.9);
        addDueDate("11/13/2015 12:59:00", 0.75);

        // Precondition Methods (24 pts)
        addFeature("Precondition signatures", 8,
                new PreconditionTestCase("approach"),
                new PreconditionTestCase("say"),
                new PreconditionTestCase("passed"),
                new PreconditionTestCase("failed"));
        addFeature("Precondition execution", 22 ,
                new PreconditionExecutionTestCase()   

                );
        
        // Command Objects (21 pts)
        addFeature("A10 Classes", 20, 
        		new CommandObjectExistsTestCase("SayCommand"),
        		new CommandObjectExistsTestCase("MoveCommand"),
        		new CommandObjectExistsTestCase("AnimatingCommand"),
        		new InterfaceImplementedTestCase("Animator", Object.class)

        		);
        
//        addFeature("Say & move cmd objects", 5,
//                new CommandImplementsRunnableTestCase("MoveCommand"),
//                new CommandImplementsRunnableTestCase("SayCommand"));
//        addFeature("Move cmd constructor", 3, new MoveCommandConstructorTestCase());
//        addFeature("Say cmd constructor", 3, new SayCommandConstructorTestCase());
        
        addFeature("A10 Constructors", 6,        		
        		new CheckstyleConstructorDefinedTestCase("SayCommand", ":@BridgeScene; String"),
        		new CheckstyleConstructorDefinedTestCase("MoveCommand", ":@Avatar; int; int")

        );
        
        addFeature("A10 Signatures", 15, 
//    	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseNumber:\\*->\\*"),
    	   		new CheckstyleMethodDefinedTestCase("CommandInterpreter", 	"@parseSay:\\*->Runnable"),
    	   		new CheckstyleMethodDefinedTestCase("CommandInterpreter", 	"@parseMove:\\*->Runnable"),
    	   		new CheckstyleMethodDefinedTestCase("Animator", "@animateAvatar:\\*->void")  	   		

        		);
        addFeature("A10 Calls", 15, 
//    	   		new CheckstyleMethodDefinedTestCase("Parser", 	"@parseNumber:\\*->\\*"),
    	   		new CheckstyleMethodCalledTestCase("CommandInterpreter", 	"@parseSay:\\*->Runnable"),
    	   		new CheckstyleMethodCalledTestCase("CommandInterpreter", 	"@parseMove:\\*->Runnable"),
        		new CheckstyleMethodDefinedTestCase("Animator", "@animateAvatar:\\*->void")
        		);
        addFeature("Command interpretation", 10,
                new SayCommandPartiallyInterpretedTestCase(),
                new MoveCommandPartiallyInterpretedTestCase()
                );


       
//        addFeature("Say and move parsers", 5,
//                new ParserMethodTestCase("parseMove"),
//                new ParserMethodTestCase("parseSay"));
//        addFeature("Command object invoked", 5, new SayMoveCommandInvokedTestCase());

        // Asynchronous Animations (55 pts)
        addFeature("Async Method Execution", 28, 
           		new AsynchronousAnimationTestCase("asynchronousArthur"),
           		new AsynchronousAnimationTestCase("asynchronousLancelot"),
           		new AsynchronousAnimationTestCase("asynchronousRobin"),
           		new AsynchronousAnimationTestCase("asynchronousGalahad")
//           		new SynchronizedAnimationTestCase("asynchronousGalahad"),
//           		new SynchronizedAnimationTestCase("asynchronousLancelot"),
//           		new SynchronizedAnimationTestCase("asynchronousRobin")
           		);
      addFeature("Guard method exists", 2, true,
      new AnimatingMethodTestCase("asynchronousGuard"));
      addManualFeature("Guard claps", 4, true);
      addManualFeature("Command Interpreter Buttons ", 5, true);
      addManualFeature("Dynamically  Enabled Buttons", 5, true);
        
//        addFeature("Animating methods", 5,
//                new AnimatingMethodTestCase("asynchronousArthur"),
//                new AnimatingMethodTestCase("asynchronousGalahad"),
//                new AnimatingMethodTestCase("asynchronousLancelot"),
//                new AnimatingMethodTestCase("asynchronousRobin"));
//        addFeature("Methods start new threads", 10,
//                new AnimatingMethodNewThreadTestCase("asynchronousArthur"),
//                new AnimatingMethodNewThreadTestCase("asynchronousGalahad"),
//                new AnimatingMethodNewThreadTestCase("asynchronousLancelot"),
//                new AnimatingMethodNewThreadTestCase("asynchronousRobin"));
//        addManualFeature("Does each async animation method use an animating command class (Runnable)?", 17);
//        addManualFeature("Are there animator classes with an animating method?", 17);

        // Extra Credit
//        addManualFeature("Console view buttons change as preconditions change", 12, true);
//        addFeature("Guard animation", 5, true,
//                new AnimatingMethodTestCase("asynchronousGuard"),
//                new AnimatingMethodNewThreadTestCase("asynchronousGuard"));
        
        // Restrictions
//        addManualRestriction("Missing demos", 100);
    }
}
