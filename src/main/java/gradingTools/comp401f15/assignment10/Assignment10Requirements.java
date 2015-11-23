package gradingTools.comp401f15.assignment10;

import framework.grading.FrameworkProjectRequirements;

import gradingTools.assignment6.testCases.ManualTestCase;
import gradingTools.assignment6.testCases.QuestionTestCase;
import gradingTools.comp401f15.assignment10.testCases.*;

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
    	   // Add due date/times with a 30 minute grace period
        addDueDate("11/04/2015 12:59:00", 1.05);
        addDueDate("11/06/2015 12:59:00", 1);
        addDueDate("11/11/2015 12:59:00", 0.9);
        addDueDate("11/13/2015 12:59:00", 0.75);

        // Precondition Methods (24 pts)
        addFeature("Precondition methods", 12,
                new PreconditionTestCase("approach"),
                new PreconditionTestCase("say"),
                new PreconditionTestCase("passed"),
                new PreconditionTestCase("failed"));
        addFeature("Precondition methods", 14,
        		new PreconditionChangedAndPropertyAnnouncedTestCase("Preconditions changed and announced"));
//
        addManualFeature("Console view buttons change as preconditions change", 12, true);
        addFeature("Console view shows precond events", 12, new PreconditionEventsTestCase());

        // Command Objects (21 pts)
        addFeature("Say & move cmd objects", 5,
                new CommandImplementsRunnableTestCase("move command"),
                new CommandImplementsRunnableTestCase("say command"));
        addFeature("Move cmd constructor", 3, new MoveCommandConstructorTestCase());
        addFeature("Say cmd constructor", 3, new SayCommandConstructorTestCase());
        addFeature("Say and move parsers", 5,
                new ParserMethodTestCase("say parser"),
                new ParserMethodTestCase("move parser"));
        addFeature("Command object invoked", 5, new SayMoveCommandInvokedTestCase());

        // Asynchronous Animations (55 pts)
        addFeature("Animating methods", 5,
                new AnimatingMethodTestCase("asynchronous Arthur"),
                new AnimatingMethodTestCase("asynchronous Galahad"),
                new AnimatingMethodTestCase("asynchronous Lancelot"),
                new AnimatingMethodTestCase("asynchronous Robin"));
        addFeature("Methods start new threads", 10,
                new AnimatingMethodNewThreadTestCase("asynchronous Arthur"),
                new AnimatingMethodNewThreadTestCase("asynchronous Galahad"),
                new AnimatingMethodNewThreadTestCase("asynchronous Lancelot"),
                new AnimatingMethodNewThreadTestCase("asynchronous Robin"));
        addManualFeature("Animating command classes", 20, new QuestionTestCase("Does each async animation method use an animating command class (Runnable)?", "Animating command class"));
        addManualFeature("Animator classes", 20, new QuestionTestCase("Are there animator classes with an animating method?", "Animator classes with method test case"));

        // Extra Credit
        addFeature("Guard animation", 5, true,
                new AnimatingMethodTestCase("asynchronous guard"),
                new AnimatingMethodNewThreadTestCase("asynchronous guard"));
        addManualFeature("Precondition buttons", 10, true, new QuestionTestCase("Are there buttons that are dynamically enabled/disabled based on the preconditions?", "Precondition buttons test case"));
        addManualFeature("Awesome demo", 5, true);
    }
}
