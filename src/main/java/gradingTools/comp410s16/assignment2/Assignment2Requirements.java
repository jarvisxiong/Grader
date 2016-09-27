package gradingTools.comp410s16.assignment2;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp410s16.assignment2.testcases.FunctionalityTestCase;
import gradingTools.sharedTestCase.checkstyle.CheckStyleClassDefinedTestCase;
import gradingTools.sharedTestCase.checkstyle.CheckStyleIllegalImportOrCallTestCase;

/**
 * Created by andrewwg94 on 2/11/16.
 */
public class Assignment2Requirements extends FrameworkProjectRequirements{
    public Assignment2Requirements() {
        addDueDate("02/03/2016 23:59:00", 1.0);
        addFeature("Functionality", 27, new FunctionalityTestCase());
        addFeature("No illegal import or call", 47, new CheckStyleIllegalImportOrCallTestCase());

        addFeature("Classes Defined",15, new CheckStyleClassDefinedTestCase("@ListArray"),new CheckStyleClassDefinedTestCase("@ListLinked"),
                new CheckStyleClassDefinedTestCase("@ListMain"),new CheckStyleClassDefinedTestCase("@QueueAbstraction"));
        addManualFeature("Interface Defined", 3, false);
        addManualFeature("One of each impl in main method", 8, false);

    }
}
