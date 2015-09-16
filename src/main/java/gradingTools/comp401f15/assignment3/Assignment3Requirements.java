package gradingTools.comp401f15.assignment3;

import framework.grading.FrameworkProjectRequirements;
import gradingTools.assignment1.testCases.*;
import gradingTools.assignment6.testCases.ManualTestCase;
import gradingTools.assignment6.testCases.QuestionTestCase;
import gradingTools.comp401f15.assignment1.testcases.ImageEnclosedTestCase;
import gradingTools.comp401f15.assignment1.testcases.MinDeclaredMethodsInSameOrDifferentClassTestCase;
import gradingTools.comp401f15.assignment1.testcases.TerminateWithPeriodTestCase;
import gradingTools.comp401f15.assignment1.testcases.VariableSpacesTestCase;
import gradingTools.comp401f15.assignment2.testcases.MissingQuotedStringTokensTestCase;
import gradingTools.comp401f15.assignment2.testcases.ModularScannerBeanTestCase;
import gradingTools.comp401f15.assignment2.testcases.NumberTokensTestCase;
import gradingTools.comp401f15.assignment2.testcases.PlusMinusTokensTestCase;
import gradingTools.comp401f15.assignment2.testcases.QuotedStringTokensTestCase;
import gradingTools.comp401f15.assignment2.testcases.ScannerBeanTestCase;
import gradingTools.comp401f15.assignment2.testcases.VarialbleSpaceTokensTestCase;
import gradingTools.comp401f15.assignment2.testcases.WordTokensTestCase;
import gradingTools.comp401f15.assignment3.testcases.EndTokenBeanTestCase;
import gradingTools.comp401f15.assignment3.testcases.NumberTokenBeanTestCase;
import gradingTools.comp401f15.assignment3.testcases.QuoteTokenBeanTestCase;
import gradingTools.comp401f15.assignment3.testcases.NumberLinesTestCase;
import gradingTools.comp401f15.assignment3.testcases.StartTokenBeanTestCase;
import gradingTools.comp401f15.assignment3.testcases.WordTokenBeanTestCase;
import gradingTools.sharedTestCase.ClassDefinedTestCase;
import gradingTools.sharedTestCase.IllegalImportOrCallTestCase;
import gradingTools.sharedTestCase.MinCalledMethodsTestCase;
import gradingTools.sharedTestCase.MinDeclaredMethodsTestCase;


public class Assignment3Requirements extends FrameworkProjectRequirements {

    public Assignment3Requirements() {
    	addDueDate("09/09/2015 23:59:00", 1.05);
    	addDueDate("09/13/2015 23:59:00", 1);
    	addDueDate("09/16/2015 23:59:00", 0.9);
    	addDueDate("09/18/2015 23:59:00", 0.75);
    	addFeature("Scanner Check", 20, new NumberLinesTestCase());
    	addFeature("Multi Property Token Beans", 8,
    			new NumberTokenBeanTestCase(),
    			new WordTokenBeanTestCase()
    			);
    	addFeature("Single Property Token Beans", 8,
    			new QuoteTokenBeanTestCase(),
    			new StartTokenBeanTestCase(),
    			new EndTokenBeanTestCase()
    			);
//         addFeature("Numbers and words", 20,
//                 new NumberTokensTestCase(),
//                 new WordTokensTestCase());
//         addFeature("Quoted string", 30, new QuotedStringTokensTestCase());
    	
//        // Functionality
//        addFeature("Process & print tokens", 40,
//                new SingleTokenTestCase(),
//                new MultipleTokensTestCase()
//              //  new RemovePrecedingZerosTestCase()
//                );
//        addFeature("Sum and product", 10,
//                new SumTestCase(),
//                new ProductTestCase());
//        addFeature("Terminates with period", 10, new TerminateWithPeriodTestCase());

        // Style
//        addManualFeature("One loop on string", 20, new QuestionTestCase("Is there only one loop over the input string?", "Input string one loop test case"));
//        addFeature("Two declared methods", 3, new MinDeclaredMethodsInSameOrDifferentClassTestCase(2));
//        addFeature("One called method", 7, new MinDeclaredMethodsInSameOrDifferentClassTestCase(1));
//        addFeature("Variable spaces", 5, true, new VariableSpacesTestCase());
//        addFeature("Handle invalid chars", 5, true, new VariableSpacesTestCase());
        addFeature("Screenshots enclosed", 10, new ImageEnclosedTestCase());
//        addManualFeature("Breakpoint step into/over/return screenhots", 20);
        
//        addFeature("Variable spaces", 3, true, new VarialbleSpaceTokensTestCase());
//        addFeature("Plus minus", 4, true, new PlusMinusTokensTestCase());
//      addFeature("Missing quote", 3, true, new MissingQuotedStringTokensTestCase());
//      addManualFeature("Own isLetter", 3, true);





//        addManualFeature("Use an extra class with iterator like interface", 10, true);


//        addManualFeature("Screenshots", 10, new QuestionTestCase("Screenshots included showing test data output?", "Screenshots testcase"));

        // TODO: Extra Credit
//        addManualFeature("Handle invalid chars", 5, true);
//        addManualFeature("No-array parser class", 10, true);
//        addManualFeature("Variable spaces", 5, true);
        addRestriction("Illegal import or call", 25, new IllegalImportOrCallTestCase());
        addRestriction("Single main.Assignment", 10, new ClassDefinedTestCase("main.Assignment(.*)"));
        addRestriction("Classes Tagged ", 18, 
        		new ClassDefinedTestCase("@ScannerBean"),
        		new ClassDefinedTestCase("@Word"),
        		new ClassDefinedTestCase("@Number"),
        		new ClassDefinedTestCase("@Quote"),
        		new ClassDefinedTestCase("@Start"),
        		new ClassDefinedTestCase("@End"));


       
        

//        addManualFeature("Nice code", 10, true);

        // Restrictions
//        addRestriction("No .split allowed", -10, new NoSplitTestCase());

    }
}
