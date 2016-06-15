package gradingTools.comp999junit.assignment1;

import grader.junit.AJUnitProjectRequirements;
import gradingTools.comp999junit.assignment1.testcases.PointProxyFactory;
import gradingTools.comp999junit.assignment1.testcases.autoproxyreference.AnAutoPointProxy;
import gradingTools.comp999junit.assignment1.testcases.autoproxyreference.AutoProxyCartesianPointSuite;


public class Assignment1Requirements extends AJUnitProjectRequirements{
    public Assignment1Requirements() {
        addDueDate("02/03/2016 23:59:00", 1.0);
		PointProxyFactory.setPointProxy(new AnAutoPointProxy());
        this.addJUnitTestSuite(AutoProxyCartesianPointSuite.class);
//        addFeature("Functionality", 27, new FunctionalityTestCase());
//        addFeature("No illegal import or call", 47, new IllegalImportOrCallTestCase());
//
//        addFeature("Classes Defined",15, new ClassDefinedTestCase("@ListArray"),new ClassDefinedTestCase("@ListLinked"),
//                new ClassDefinedTestCase("@ListMain"),new ClassDefinedTestCase("@QueueAbstraction"));
//        addManualFeature("Interface Defined", 3, false);
//        addManualFeature("One of each impl in main method", 8, false);

    }
}
