package gradingTools.comp410s16.assignment5;

import framework.grading.FrameworkProjectRequirements;
//import gradingTools.assignment6.testCases.ManualTestCase;
import gradingTools.comp410s16.assignment5.testcases.FunctionalityTestCase;
import gradingTools.sharedTestCase.checkstyle.CheckStyleIllegalImportOrCallTestCase;

/**
 * Created by andrewwg94 on 4/30/16.
 */
public class Assignment5Requirements extends FrameworkProjectRequirements {
    public Assignment5Requirements() {
        addDueDate("03/21/2016 23:59:00", 1.0);
        addDueDate("03/23/2016 23:59:00", .9);
        addDueDate("03/25/2016 23:59:00", .8);
        addDueDate("03/28/2016 23:59:00", .7);
        addFeature("Functionality", 50, new FunctionalityTestCase());
        addManualFeature("No Illegal Imports", 25);
        addManualFeature("Program Structure", 25);


    }
}
