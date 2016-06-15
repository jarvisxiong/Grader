package gradingTools.assignment1;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.assignment1.testCases.MultipleTokensTestCase;
import gradingTools.assignment1.testCases.NoSplitTestCase;
import gradingTools.assignment1.testCases.ProductTestCase;
import gradingTools.assignment1.testCases.RemovePrecedingZerosTestCase;
import gradingTools.assignment1.testCases.SingleTokenTestCase;
import gradingTools.assignment1.testCases.SumTestCase;
import gradingTools.assignment1.testCases.TwoMethodTestCase;
import gradingTools.assignment6.testCases.QuestionTestCase;
import gradingTools.comp401f15.assignment1.testcases.TerminateWithPeriodTestCase;

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
        addFeature("Two methods", 10, new TwoMethodTestCase());
        addManualFeature("Screenshots", 10, new QuestionTestCase("Are there screenshots included which show test data output?", "Screenshots testcase"));

        // TODO: Extra Credit
        addManualFeature("Handle invalid chars", 2, true);
        addManualFeature("No-array parser class", 10, true);
        addManualFeature("Variable spaces", 5, true);
        addManualFeature("Nice code", 10, true);

        // Restrictions
        addRestriction("No .split allowed", -10, new NoSplitTestCase());

    }
}
