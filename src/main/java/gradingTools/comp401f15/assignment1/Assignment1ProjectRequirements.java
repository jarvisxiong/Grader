package gradingTools.comp401f15.assignment1;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.assignment1.testCases.*;
import gradingTools.assignment6.testCases.ManualTestCase;
import gradingTools.assignment6.testCases.QuestionTestCase;
import gradingTools.sharedTestCase.MinCalledMethodsTestCase;
import gradingTools.sharedTestCase.MinDeclaredMethodsTestCase;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 11/6/13
 * Time: 9:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class Assignment1ProjectRequirements extends FrameworkProjectRequirements {

    public Assignment1ProjectRequirements() {
    	
        // Functionality
        addFeature("Process & print tokens", 40,
                new SingleTokenTestCase(),
                new MultipleTokensTestCase(),
                new RemovePrecedingZerosTestCase());
        addFeature("Sum and product", 10,
                new SumTestCase(),
                new ProductTestCase());
        addFeature("Terminates with period", 10, new TerminateWithPeriodTestCase());

        // Style
        addManualFeature("One loop on string", 20, new QuestionTestCase("Is there only one loop over the input string?", "Input string one loop test case"));
        addFeature("Two declared methods", 3, new MinDeclaredMethodsTestCase(2));
        addFeature("One called method", 7, new MinCalledMethodsTestCase(1));

        addManualFeature("Screenshots", 10, new QuestionTestCase("Are there screenshots included which show test data output?", "Screenshots testcase"));

        // TODO: Extra Credit
        addManualFeature("Handle invalid chars", 5, true);
        addManualFeature("No-array parser class", 10, true);
        addManualFeature("Variable spaces", 5, true);
        addManualFeature("Nice code", 10, true);

        // Restrictions
        addRestriction("No .split allowed", -10, new NoSplitTestCase());

    }
}
