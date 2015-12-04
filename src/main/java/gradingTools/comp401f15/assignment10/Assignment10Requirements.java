package gradingTools.comp401f15.assignment10;

import framework.grading.FrameworkProjectRequirements;

import gradingTools.assignment6.testCases.ManualTestCase;
import gradingTools.assignment6.testCases.QuestionTestCase;
import gradingTools.comp401f15.assignment10.testCases.*;
import gradingTools.comp401f15.assignment10.testCases.preConditions.ApproachMethodPreconditionTestCase;
import gradingTools.comp401f15.assignment10.testCases.preConditions.FailedMethodPreconditionTestCase;
import gradingTools.comp401f15.assignment10.testCases.preConditions.PassedMethodPreconditionTestCase;
import gradingTools.comp401f15.assignment10.testCases.preConditions.SayMethodPreconditionTestCase;

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
        addFeature("Precondition methods", 12,
                new PreconditionTestCase("approach"),
                new PreconditionTestCase("say"),
                new PreconditionTestCase("passed"),
                new PreconditionTestCase("failed"));
        addFeature("Precondition methods", 14,
                new ApproachMethodPreconditionTestCase(),   // not done yet
                new SayMethodPreconditionTestCase(),        // not done yet
                new PassedMethodPreconditionTestCase(),     // not done yet
                new FailedMethodPreconditionTestCase(),     // not done yet
        		new PreconditionChangedAndPropertyAnnouncedTestCase("Preconditions changed and announced"));
        addFeature("Console view shows precondition events", 6, new PreconditionEventsTestCase()); // reimplement
        
        // Command Objects (21 pts)
        addFeature("Say & move cmd objects", 5,
                new CommandImplementsRunnableTestCase("MoveCommand"),
                new CommandImplementsRunnableTestCase("SayCommand"));
        addFeature("Move cmd constructor", 3, new MoveCommandConstructorTestCase());
        addFeature("Say cmd constructor", 3, new SayCommandConstructorTestCase());
        
        addFeature("Say and move parsers", 5,
                new ParserMethodTestCase("parseMove"),
                new ParserMethodTestCase("parseSay"));
        addFeature("Command object invoked", 5, new SayMoveCommandInvokedTestCase());

        // Asynchronous Animations (55 pts)
        addFeature("Animating methods", 5,
                new AnimatingMethodTestCase("asynchronousArthur"),
                new AnimatingMethodTestCase("asynchronousGalahad"),
                new AnimatingMethodTestCase("asynchronousLancelot"),
                new AnimatingMethodTestCase("asynchronousRobin"));
        addFeature("Methods start new threads", 10,
                new AnimatingMethodNewThreadTestCase("asynchronousArthur"),
                new AnimatingMethodNewThreadTestCase("asynchronousGalahad"),
                new AnimatingMethodNewThreadTestCase("asynchronousLancelot"),
                new AnimatingMethodNewThreadTestCase("asynchronousRobin"));
        addManualFeature("Does each async animation method use an animating command class (Runnable)?", 17);
        addManualFeature("Are there animator classes with an animating method?", 17);

        // Extra Credit
        addManualFeature("Console view buttons change as preconditions change", 12, true);
        addFeature("Guard animation", 5, true,
                new AnimatingMethodTestCase("asynchronousGuard"),
                new AnimatingMethodNewThreadTestCase("asynchronousGuard"));
        
        // Restrictions
        addManualRestriction("Missing demos", 100);
    }
}
