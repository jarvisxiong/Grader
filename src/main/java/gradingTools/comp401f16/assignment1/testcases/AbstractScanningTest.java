package gradingTools.comp401f16.assignment1.testcases;

import java.util.ArrayList;
import java.util.List;

import grader.basics.execution.NotRunnableException;
import grader.basics.junit.NotesAndScore;
import grader.basics.project.NotGradableException;
import gradingTools.shared.testcases.OutputAndErrorCheckingTestCase;

import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractScanningTest extends OutputAndErrorCheckingTestCase {
    public static String MATCH_ANY = "(.*)";

    protected String inputWithEndingSpace() {
    	return toSpacedInput(inputWithNoEndingSpace());
    }    
  ;
  
    protected String toRegex(String aString) {
 	   return MATCH_ANY + aString + MATCH_ANY;
    }
	protected String correctOutputButErrorsMessage() {
		return "Correct output but errors";
	}
	protected double correctOutputButErrorsCredit() {
		return 0.5;
	}
	protected String incorrectOutputMessage() {
		return "Incorrect output";
	}
	protected double incorrectOutputCredit() {
		return 0.0;
	}
	@Test
    public void test() {
        try {
            OutputErrorStatus retVal = test(Assignment1Suite.MAIN_CLASS_NAME, inputWithEndingSpace(), expectedOutputs());
            
            if (retVal == OutputErrorStatus.CORRECT_OUTPUT_NO_ERRORS)
            	return;
            if (retVal == OutputErrorStatus.CORRECT_OUTPUT_ERRORS)
            	Assert.assertTrue(correctOutputButErrorsMessage() +
            			+ NotesAndScore.PERECTAGE_CHARACTER 
            			+ correctOutputButErrorsCredit(), false);
             retVal = test(Assignment1Suite.MAIN_CLASS_NAME, inputWithNoEndingSpace(), expectedOutputs());
            if (retVal == OutputErrorStatus.CORRECT_OUTPUT_NO_ERRORS)
            	return;
            if (retVal == OutputErrorStatus.CORRECT_OUTPUT_ERRORS)
            	Assert.assertTrue(correctOutputButErrorsMessage() +
            			+ NotesAndScore.PERECTAGE_CHARACTER 
            			+ correctOutputButErrorsCredit(), false);      	

            Assert.assertTrue(incorrectOutputMessage() +
            		+ NotesAndScore.PERECTAGE_CHARACTER
            		+ incorrectOutputCredit(),
            		
            		false);
            
        } catch (NotRunnableException e) {
            throw new NotGradableException();
        }
    }
	protected static StringBuilder stringBuilder = new StringBuilder();

    public static String toSpacedInput(String anInput) {
		stringBuilder.setLength(0);
		String[] lines = anInput.split("\n");
        for (String line : lines)
            stringBuilder.append(line + " \n");

       return stringBuilder.toString();
	}
protected String[][] tokenLines = {{"22", "44", "66"}, {"11" , "33", "55"}};
	
	protected String[][] tokenLines() {
		return tokenLines;
	}
	
	protected String inputWithNoEndingSpace() {
    	String[][] aTokenLines = tokenLines();
    	stringBuilder.setLength(0);
    	for (int aLineNumber = 0; aLineNumber < aTokenLines.length; aLineNumber++) {
    		String[] aLine = aTokenLines[aLineNumber];
    		for (int aTokenNumber = 0; aTokenNumber < aLine.length; aTokenNumber++) {
    			stringBuilder.append(aLine[aTokenNumber]);
    			if (aTokenNumber < aLine.length - 1) {
    				stringBuilder.append(" ");
    			}
    		}
    		stringBuilder.append("\n");
    	}
    	stringBuilder.append(".\n");
    	return stringBuilder.toString();
	}
    protected static String[] emptyStringArray = {};
	protected List<String> tokens = new ArrayList();
	
	protected String[] expectedSumProductOutputs() {
		String[] aSumOut = expectedSumOutput();
		String[] aProductOut = expectedProductOutput();
		return new String [] {aSumOut[0], aProductOut[0]};
		
	}
	protected String[] expectedProductOutput() {
		int aProduct = 1;
		 String[] aTokens = allTokens();
		 for (String aToken: aTokens) {
			 try {
				 int aNum = Integer.parseInt(aToken);
				 aProduct *= aNum;
				 
				 
			 } catch (Exception e) {
				 
			 }
		 }
		 return  new String[] {toRegex("" + aProduct)};
	}
	protected String[] expectedSumOutput() {
		int aSum = 0;
		 String[] aTokens = allTokens();
		 for (String aToken: aTokens) {
			 try {
				 int aNum = Integer.parseInt(aToken);
				 aSum += aNum;
				 
				 
			 } catch (Exception e) {
				 
			 }
		 }
		 return  new String[] {toRegex("" + aSum)};

	}
	protected abstract String[] expectedOutputs() ;

	protected String[] expectedTokenOutputs() {
		String[] anAllTokens = allTokens();
		tokens.clear();
		for (int aTokenNumber = 0; aTokenNumber < anAllTokens.length; aTokenNumber++) {
			tokens.add(toRegex(anAllTokens[aTokenNumber]));
		}
		return tokens.toArray(emptyStringArray);
	}

	protected String[] allTokens() {
		String[][] aTokenLines = tokenLines();
		tokens.clear();
		for (int aLineNumber = 0; aLineNumber < aTokenLines.length; aLineNumber++) {
    		String[] aLine = aTokenLines[aLineNumber];
    		for (int aTokenNumber = 0; aTokenNumber < aLine.length; aTokenNumber++) {
    			tokens.add(aLine[aTokenNumber]);
    			
    		}
    	}
		return tokens.toArray(emptyStringArray);
		
	} 
}

