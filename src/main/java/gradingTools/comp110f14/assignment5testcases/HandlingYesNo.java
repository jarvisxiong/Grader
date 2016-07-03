package gradingTools.comp110f14.assignment5testcases;

import java.util.regex.Pattern;

import framework.grading.testing.BasicTestCase;
import grader.basics.execution.RunningProject;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class HandlingYesNo extends BasicTestCase {

	public HandlingYesNo() {
		super("Handling yes and no test Case");
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {

		Pattern promptstuff = Pattern.compile(".*enter|message.*");
		int numwrong=0;
		String partialMessage = "";
		
		//-------------yes stuff---------------//
		RunningProject yes = RunningProjectUtils.runProject(project, 10, "ThatIsTheQuestion\nyes\nToBeorNotToBE\n");
		String outputyes = yes.await();
		//break up output
		String[] outputyesfoo = outputyes.split("\n");
		int numencounters=0;
		//loop through output given by specified input
		for (int i = 0; i < outputyesfoo.length; i++) { 
			if(promptstuff.matcher(outputyesfoo[i]).find()){
				numencounters++;
			}
		}
		//if didn't find prompt twice then didn't handle yes correctly
		if(numencounters<3){
			numwrong++;
			partialMessage+="Did not ask again for input upon user confirming wish to enter another message.";
		}
		//---------------end yes stuff---------------//
		
		//---------------no stuff--------------------//
		RunningProject no = RunningProjectUtils.runProject(project, 10, "ToBeIsTheQuestion\nyes\nfooBear\nno\n");
		String outputno= no.await();
		//break up output
		String[] outputnofoo = outputno.split("\n");
		
		//test attempt two
		if(outputnofoo.length > outputyesfoo.length){
			numwrong++;
			partialMessage+="Asked for another message to encode despite user entering no\n";
		}
		
		//handle test attempt one
		/*int noencounters=0;
		//loop through output given by specified input
		for (int i = 0; i < outputnofoo.length; i++) {
			if(promptstuff.matcher(outputnofoo[i]).find()){
				noencounters++;
			}
		}
		//check for prompt again if more than once - didn't handle no correctly
		if(noencounters>2){
			numwrong++;
			partialMessage+="Asked for another message to encode despite user entering no\n";
		}*/
		
		//---------------end no stuff---------------//
		
		//if handled yes and no then pass
		if(numwrong==0)return pass();
		//if didn't handle either right fail
		if(numwrong==2)return fail(partialMessage);
		//if handled yes or no but not both
		return partialPass(0.5,partialMessage);
	}

}