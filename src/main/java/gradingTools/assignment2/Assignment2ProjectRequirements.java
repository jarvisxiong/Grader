package gradingTools.assignment2;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.assignment2.testCases.BeanClassTestCase;
import gradingTools.assignment2.testCases.IsLetterTestCase;
import gradingTools.assignment2.testCases.NumberTokensTestCase;
import gradingTools.assignment2.testCases.QuotedStringTestCase;
import gradingTools.assignment2.testCases.WordTokensTestCase;

/**
 * Created with IntelliJ IDEA.
 * User: josh
 * Date: 11/7/13
 * Time: 9:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class Assignment2ProjectRequirements extends FrameworkProjectRequirements {
    public Assignment2ProjectRequirements() {

        // Functionality
        addFeature("Bean class", 20, new BeanClassTestCase());
        addFeature("Numbers and words", 20,
                new NumberTokensTestCase(),
                new WordTokensTestCase());
        addFeature("Quoted string", 30, new QuotedStringTestCase());

        // Testing
        addManualFeature("Debugging screenshots", 20, false);
        addManualFeature("Output screenshots", 10, false);

        // Extra credit
//        addManualFeature("Variable spaces", 3, true);
        addManualFeature("Error message w/ missing quote", 3, true);
        addFeature("Custom isLetter method", 3, true, new IsLetterTestCase());
        addManualFeature("Plus and minus tokens", 4, true);


    }
}
