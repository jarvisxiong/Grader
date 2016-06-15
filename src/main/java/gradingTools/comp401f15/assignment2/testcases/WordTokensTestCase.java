package gradingTools.comp401f15.assignment2.testcases;



public class WordTokensTestCase extends AbstractTokensTestCase {

    public WordTokensTestCase() {
        super("Word Token Test Case");
    }
    
    protected String simplifiedInputWithNoEndingSpace() { return "hello goodbye\n.\n";}
    protected String simplifiedInputWithEndingSpace() { return "hello goodbye \n.\n";}
    protected String inputWithEndingSpace() {return "hello \"h e l l o\" 10 goodbye  \"go odb ye\" 20\n.\n";}
    protected String inputWithNoEndingSpace() {return "hello \"h e l l o\" 10 goodbye \"go odb ye\" 20 \n.\n";}
    protected String inputWithStartingAndEndingSpace() {return  " hello \"h e l l o\" 10 goodbye  \"go odb ye\" 20\n.\n";}


 
//    String[] expectedOutputs = {"30", "200", "90", "2000"};
    protected String[] expectedOutputs() {return new String[] {".*hello.*", ".*goodbye.*"};}

////    String simplifiedInputWithNoEndingSpace = "10 20 \n40 50 \n.\n";
////    String simplifiedInputWithEndingSpace = "10 20\n40 50 \n.\n";
////    String inputWithEndingSpace = "10 20 \n40 50 \n.the end\n";
////    String inputWithNoEndingSpace = "10 20\n40 50 \n.the end\n";
////    String[] expectedOutputs = {"30", "200", "90", "2000"};
//    
//    String simplifiedInputWithNoEndingSpace = "hello goodbye\n.\n";
//    String simplifiedInputWithEndingSpace = "hello goodbye \n.\n";
//    String inputWithEndingSpace = "hello \"h e l l o\" 10 goodbye  \"go odb ye\" 20\n.\n";
//    String inputWithNoEndingSpace = "hello \"h e l l o\" 10 goodbye \"go odb ye\" 20 \n.\n";
//
// 
////    String[] expectedOutputs = {"30", "200", "90", "2000"};
//    String[] expectedOutputs = {".*hello.*", ".*goodbye.*"};
//    protected double simplifiedCredit = 0.7;
//
//    
//    @Override
//    protected boolean isValidOutput(List<String> anOutput, String[] anExpectedStrings){
// 	   return isValidOutputInDifferentLines(anOutput, anExpectedStrings);
//    }
//
//    @Override
//    public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException, NotGradableException {
//        try {
//            OutputErrorStatus retVal = test(project, inputWithEndingSpace, expectedOutputs, autoGrade);
//            if (retVal == OutputErrorStatus.CORRECT_OUTPUT_NO_ERRORS 
//            		|| retVal == OutputErrorStatus.CORRECT_OUTPUT_ERRORS)
//            	return pass();
//            retVal = test(project, inputWithNoEndingSpace, expectedOutputs, autoGrade);
//            if (retVal == OutputErrorStatus.CORRECT_OUTPUT_NO_ERRORS
//            	|| retVal == OutputErrorStatus.CORRECT_OUTPUT_ERRORS)
//            	return pass();
//            retVal = test(project, simplifiedInputWithNoEndingSpace, expectedOutputs, autoGrade);
//            if (retVal == OutputErrorStatus.CORRECT_OUTPUT_NO_ERRORS
//            		|| retVal == OutputErrorStatus.CORRECT_OUTPUT_ERRORS)
//            	return partialPass(simplifiedCredit, "Cannot scan words in mixed token case");            
//            retVal = test(project, simplifiedInputWithEndingSpace, expectedOutputs, autoGrade);
//            if (retVal == OutputErrorStatus.CORRECT_OUTPUT_NO_ERRORS
//            		|| retVal == OutputErrorStatus.CORRECT_OUTPUT_ERRORS)
//            	return partialPass(simplifiedCredit, "Cannot scan words in mixed token case");
//
//            return fail("Cannot scan numbers", autoGrade);
//            
//        } catch (NotRunnableException e) {
//            throw new NotGradableException();
//        }
//    }
}

