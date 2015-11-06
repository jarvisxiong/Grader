package gradingTools.comp110f15lab.lab2.testcases;

import java.util.Random;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class LoopChecker extends BasicTestCase {

	public LoopChecker() {
		super("checks if loop is used");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException,
			NoSuchFieldException {
		String smallinput="50\n";
		Random R= new Random();
		int smallcount=0;
		while(smallcount<50){
			int p=R.nextInt();
			if(p%3==0){
				smallcount++;
				smallinput+=""+p+"\n";
			}
		}
		RunningProject empty=RunningProjectUtils.runProject(project, 0,smallinput);
		String em=empty.await();
		String inputStream="100\n";
		int multcounter=0;
		while(multcounter<100){
			int possible=R.nextInt();
			if(possible%3==0){
				multcounter++;
				inputStream+=""+possible+"\n";
			}
		}
		RunningProject goo = RunningProjectUtils.runProject(project, 10,inputStream);
		String af= goo.await();
		if(af.length()>em.length()-2)
		return pass();
		else
		return fail("Most likely did not use loop as a long input stream could not be handled");
	}

}
