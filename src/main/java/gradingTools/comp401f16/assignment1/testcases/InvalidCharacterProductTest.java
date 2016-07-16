package gradingTools.comp401f16.assignment1.testcases;

import org.junit.Assert;
import org.junit.Test;

import util.annotations.Explanation;
import util.annotations.IsExtra;
import grader.basics.execution.NotRunnableException;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.NotesAndScore;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.CurrentProjectHolder;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.shared.testcases.OutputAndErrorCheckingTestCase;

@Explanation("Invalid Character Tokens")
@IsExtra(true)
public class InvalidCharacterProductTest extends AbstractScanningTest {
	protected String[][] tokenLines = {{"0", "ten", "20"}, {"40" , "forty", "50", " !@@"}};

//    String inputWithNoEndingSpace = "10 ten 20\n40 forty 50!\n.";

//    String inputWithEndingSpace = "10 ten 20\n40 forty 50 ! \n.";

//    String[] expectedOutputs = {"(.*)30(.*)", "(.*)200(.*)", "(.*)90(.*)", "(.*)2000(.*)"};

    
    
    @Override
    protected String[] expectedOutputs() {
    	return expectedTokenOutputs();
    }
    
	
}

