package gradingTools.comp999junit.assignment1;

import framework.grading.FrameworkProjectRequirements;
import framework.grading.testing.Restriction;
import gradingTools.comp410s16.assignment2.testcases.FunctionalityTestCase;
import gradingTools.sharedTestCase.ClassDefinedTestCase;
import gradingTools.sharedTestCase.IllegalImportOrCallTestCase;


public class Assignment1Requirements extends FrameworkProjectRequirements{
    public Assignment1Requirements() {
        addDueDate("02/03/2016 23:59:00", 1.0);
        addFeature("Functionality", 27, new FunctionalityTestCase());
        addFeature("No illegal import or call", 47, new IllegalImportOrCallTestCase());

        addFeature("Classes Defined",15, new ClassDefinedTestCase("@ListArray"),new ClassDefinedTestCase("@ListLinked"),
                new ClassDefinedTestCase("@ListMain"),new ClassDefinedTestCase("@QueueAbstraction"));
        addManualFeature("Interface Defined", 3, false);
        addManualFeature("One of each impl in main method", 8, false);

    }
}
