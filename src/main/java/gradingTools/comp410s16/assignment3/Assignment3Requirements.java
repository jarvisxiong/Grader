package gradingTools.comp410s16.assignment3;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp410s16.assignment3.testcases.FunctionalityTestCase;
import gradingTools.sharedTestCase.checkstyle.CheckStyleClassDefinedTestCase;
import gradingTools.sharedTestCase.checkstyle.CheckStyleIllegalImportOrCallTestCase;

/**
 * Created by andrewwg94 on 3/7/16.
 */
public class Assignment3Requirements extends FrameworkProjectRequirements {
    public Assignment3Requirements() {
        addDueDate("02/17/2016 23:59:00", 1.0);
        addFeature("Functionality", 27, new FunctionalityTestCase());

    }
}
