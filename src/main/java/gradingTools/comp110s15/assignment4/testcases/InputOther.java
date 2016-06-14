package gradingTools.comp110s15.assignment4.testcases;

import java.util.regex.Pattern;

import framework.execution.BasicRunningProject;
import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class InputOther extends BasicTestCase{
	/* Program Handles for input other than UNC/Dook
	 * */
	
	public InputOther() {
		super("Test Case for Other input");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		// TODO Auto-generated method stub
		int uncWins = 5; // unc wins
		int otherWin1 = 12; //more than unc
		int otherWin2 = 3;  //less than unc
		int moreThanUNC = otherWin1 - uncWins; //result of more
		int lessThanUNC = uncWins - otherWin2; //result of less
		
		int passCount = 2;
		Boolean prompted = true;
		String partialMessage = "Failed to handle (Other) ";
		//prompt pattern
		Pattern prompt = Pattern.compile(".*championships.*");
		
		//smaller test
		Pattern lessThanUNCPattern = Pattern.compile(".*"+lessThanUNC+".*"+"wins"+".*");
		//larger test
		Pattern moreThanUNCPattern = Pattern.compile(".*okay.*");
		
		//Project for comparison
		BasicRunningProject Project0 = RunningProjectUtils.runProject(project, 10,
				"");
		String output0 = Project0.await().toLowerCase();

		//Project for just school name
		BasicRunningProject Project1 = RunningProjectUtils.runProject(project, 10,
				"Brown");
		String output1 = Project1.await().toLowerCase();
		output1 = output1.substring(output0.length() - 1);
		
		if(!prompt.matcher(output1).find()){
			prompted = false;
		}
		
		//Project for School and Less Wins
		BasicRunningProject Project2 = RunningProjectUtils.runProject(project, 10,
				"Brown"+'\n'+otherWin2);
		String output2 = Project2.await().toLowerCase();
		output2 = output2.substring(output0.length() - 1);
		
		if(!lessThanUNCPattern.matcher(output2).find()){
			passCount--;
			partialMessage += "-- Did not Handle Team with Less Wins Correctly ";
		}
		//Project for School and More wins
		BasicRunningProject Project3 = RunningProjectUtils.runProject(project, 10,
				"Brown"+'\n'+otherWin1);
		String output3 = Project3.await().toLowerCase();
		output3 = output3.substring(output0.length() - 1);
		
		if(!moreThanUNCPattern.matcher(output3).find()){
			passCount--;
			partialMessage += "-- Did not Handle Team with More Wins Correctly ";
		}
		
		if(prompted&&passCount==2){
			return pass();
		}
		else if(prompted&&passCount<2&&passCount>0){
			return partialPass(0.5,partialMessage);
		}
		
		return fail(partialMessage);
	}
}