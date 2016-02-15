package gradingTools.comp410s16.assignment2;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.comp410s16.assignment2.testcases.FunctionalityTestCase;

/**
 * Created by andrewwg94 on 2/11/16.
 */
public class Assignment2Requirements extends FrameworkProjectRequirements{
    public Assignment2Requirements() {
        addDueDate("02/03/2016 23:59:00", 1.0);
        addFeature("Functionality", 27, new FunctionalityTestCase());

    }
}
