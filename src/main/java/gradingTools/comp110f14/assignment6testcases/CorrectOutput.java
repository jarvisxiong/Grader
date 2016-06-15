package gradingTools.comp110f14.assignment6testcases;

import framework.execution.BasicRunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class CorrectOutput extends BasicTestCase {

	public CorrectOutput() {
		super("Correct Output, only used for graders");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		BasicRunningProject Project = RunningProjectUtils.runProject(project, 10,"order\n5\norder\n10\nprint\n");
		//grabs user prompt String and sets it to lower case so ignores case
		String output=Project.await().toLowerCase();
		String[] lines = output.split("\n");
		boolean cocoa=false,wafers=false,sugar=false,price=false;
		for(int i=0;i<lines.length;i++){
			if (lines[i].contains("cocoa")&&lines[i].contains("30"))cocoa=true;
			if(lines[i].contains("60")&&lines[i].contains("wafers"))wafers=true;
			if(lines[i].contains("30")&&lines[i].contains("sugar"))sugar=true;
			if(lines[i].contains("price")&&lines[i].contains("13.50"))price=true;
		}
		if(cocoa&&wafers&&sugar&&price)return pass();
		String partialoutput="";
		if(!cocoa)partialoutput+="incorrect output for cocoa\n";
		if(!wafers)partialoutput+="incorrect output for wafers\n";
		if(!sugar)partialoutput+="incorrect output for sugar\n";
		if(!price)partialoutput+="incorrect total price";
		return fail(partialoutput);
	}

}
