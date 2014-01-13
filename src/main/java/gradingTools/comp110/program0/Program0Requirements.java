package gradingTools.comp110.program0;

import framework.grading.FrameworkProjectRequirements;

/**
 * This is an example on how to define requirements and test cases for an assignment. You just extend the
 * {@link FrameworkProjectRequirements} class, define a nullary constructor (no arguments), and within it add due dates,
 * features, and restrictions by calling the respective methods:
 * <ul>
 *     <li>addDueDate</li>
 *     <li>addFeature</li>
 *     <li>addRestriction</li>
 * </ul>
 *
 * These project requirements are to be used with the "Test Data/Example Bulk" folder.
 */
public class Program0Requirements extends FrameworkProjectRequirements {

    public Program0Requirements() {

        // This defines the due dates. What this is saying is, if a project is turned in on or before the specified time
        // then its score is multiplied by the defined percentage.
        addDueDate("01/16/2014 23:59:59", 1.0);
        addDueDate("01/17/2014 23:59:59", 0.5);
        
        // Check for a HelloWorld class
        addFeature("Contains the HelloWorld class", 30, new HelloWorldClassTestCase());
        
        // Check for a main method
        addFeature("Contains a main method", 30, new MainMethodTestCase());

        // Check that the output is correct
        addFeature("Prints Hello World", 40, new HelloWorldPrinterTestCase());
    }
}
