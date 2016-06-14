package gradingTools.comp110f14.assignment4testcases;

import java.util.regex.Pattern;

import framework.execution.BasicRunningProject;
import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class HandlingAskingAgain extends BasicTestCase {

	public HandlingAskingAgain() {
		super("Handling Asking Again Test Case");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		BasicRunningProject promptcap= RunningProjectUtils.runProject(project, 10, "");
		String outputpromptcap = promptcap.await();
		String[] goo = outputpromptcap.split("\n");
		String prompt="";
		Pattern promptshit = Pattern.compile(".*enter|please|input|type|write.*");
		for (int i = 0; i < goo.length; i++) {
			if(promptshit.matcher(goo[i]).find()){
				prompt=goo[i];
				break;
			}
		}//have prompting
		int numwrong=0;
		String partialMessage = "";
		BasicRunningProject yes = RunningProjectUtils.runProject(project, 10, "BBC\nyes\n");
		String outputyes = yes.await();
		String[] outputyesgoo = outputyes.split("\n");
		int numencounters=0;
		for (int i = 0; i < outputyesgoo.length; i++) { 
			if(prompt.equals(outputyesgoo[i]))
				numencounters++;
		}
		if(numencounters<2){
			numwrong++;
			partialMessage+="Did not ask again for input upon user confirming wish to enter another gene sequence.";
		}
		BasicRunningProject no = RunningProjectUtils.runProject(project, 10, "BBC\nno\n");
		String outputno= no.await();
		String[] outputnogoo = outputno.split("\n");
		int noencounters=0;
		for (int i = 0; i < outputnogoo.length; i++) {
			if(prompt.equals(outputnogoo[i]))noencounters++;
		}
		if(noencounters>1){
			numwrong++;
			partialMessage+="Asked for another gene sequence despite user entering no\n";
		}
		BasicRunningProject other = RunningProjectUtils.runProject(project, 10, "BBC\nother\n");
		String otheroutput = other.await();
		if(!otheroutput.toLowerCase().contains("error")){
			numwrong++;
			partialMessage+="Did not print out error message when user did not print yes or no when asked if they wished to go again";
		}
		if(numwrong==0)return pass();
		if(numwrong==3)return fail(partialMessage);
		return partialPass(0.5,partialMessage);
	}

}
