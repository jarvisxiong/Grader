package gradingTools.comp110f14.assignment4testcases;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class ColorBlindTest extends BasicTestCase {

	public ColorBlindTest() {
		super("Color Blind Test Caser");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		// TODO Auto-generated method stub
		RunningProject noinfo = RunningProjectUtils.runProject(project, 10, "iwjefoiwejfoweijfBB");
		String outputnoInfo=noinfo.await();
		RunningProject blindA = RunningProjectUtils.runProject(project, 10, "bwfeijBbiojefcww");
		String outputblindA=blindA.await();
		RunningProject blindB = RunningProjectUtils.runProject(project, 10, "woejcbbwefjoij");
		String outputblindB=blindB.await();
		RunningProject fineA = RunningProjectUtils.runProject(project, 10, "wefiojbBowjC");
		String outputfineA=fineA.await();
		RunningProject fineB = RunningProjectUtils.runProject(project, 10, "wefoiioCBB");
		String outputfineB=fineB.await();
		int numright=5;
		String partialMessage="";
		if(outputnoInfo.toLowerCase().contains("blindness")){
		}
		if(!outputblindA.toLowerCase().contains("blind")){
			numright--;
			partialMessage+="When given input in which eye color is given BEFORE blindness, incorrect output\n";
		}
		if(!outputblindB.toLowerCase().contains("blind")){
			numright--;
			partialMessage+="When given input in which eye color is given AFTER blindness, incorrect output\n";
		}
		if(!outputfineA.toLowerCase().contains("norm")){
			numright--;
			partialMessage+="When given input in which eye color is given BEFORE normal vision, incorrect output\n";
		}
		if(!outputfineB.toLowerCase().contains("norm")){
			numright--;
			partialMessage+="When given input in which eye color is given BEFORE normal vision, incorrect output\n";
		}
		if(numright==5)return pass();
		if(numright==0)return fail(partialMessage);
		return partialPass(numright/5,partialMessage);
	}

}
