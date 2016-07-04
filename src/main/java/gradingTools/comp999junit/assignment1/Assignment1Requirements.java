package gradingTools.comp999junit.assignment1;

import grader.junit.AJUnitProjectRequirements;
import gradingTools.comp999junit.assignment1.testcases.CartesianPointSuite;


public class Assignment1Requirements extends AJUnitProjectRequirements{
    public Assignment1Requirements() {
        addDueDate("02/03/2016 23:59:00", 1.0);
//		MultiPointProxyFactory.setPointProxy(new AnAutoPointProxy());
//      this.addJUnitTestSuite(AutoProxyCartesianPointSuite.class);
        this.addJUnitTestSuite(CartesianPointSuite.class);
//        addJUnitTestSuite(CartesianPointSuite.class);

    }
}
