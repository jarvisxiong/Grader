package gradingTools.comp110s15.assignment7.testcases;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class UltimatePrint extends BasicTestCase {

	public UltimatePrint() {
		super("ult print test");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		// TODO Auto-generated method stub
		RunningProject Project0 = RunningProjectUtils.runProject(project, 10,
				"max\ndeposit\nchecking\n75\nwithdraw\nchecking\n20\ntransfer\nchecking\n10\n");
		String out0=Project0.await().toLowerCase();
		RunningProject TestAll = RunningProjectUtils.runProject(project, 10,
				"max\ndeposit\nchecking\n75\nwithdraw\nchecking\n20\ntransfer\nchecking\n10\nprint\n");
		String realOutAll = TestAll.await();
//		String outall=TestAll.await().toLowerCase().substring(out0.length()-1);
		String outall=realOutAll.toLowerCase().substring(out0.length()-1);

		if(outall.contains("45") && outall.contains("10") && outall.contains("75") && outall.contains("20"))return pass();
		else{
			boolean depo=false;
			boolean with=false;
			boolean tran=false;
			boolean print=false;
			RunningProject Pred=RunningProjectUtils.runProject(project, 10, "max\ndeposit\nchecking\n75\ndeposit\nsaving\n60\n");
			String predout=Pred.await().toLowerCase();
			RunningProject Depos= RunningProjectUtils.runProject(project, 10,
					"max\ndeposit\nchecking\n75\ndeposit\nsaving\n60\nprint\n");
			String Deposout=Depos.await().toLowerCase().substring(predout.length()-1);
			if(Deposout.contains("75")&&Deposout.contains("60"))depo=true;
			
			RunningProject Prew=RunningProjectUtils.runProject(project, 10, "max\ndeposit\nchecking\n75\nwithdraw\nchecking\n60\n");
			String prewout=Prew.await().toLowerCase();
			RunningProject Withs=RunningProjectUtils.runProject(project, 10, "max\ndeposit\nchecking\n75\nwithdraw\nchecking\n60\nprint\n");
			String Withsout=Withs.await().toLowerCase().substring(prewout.length()-1);
			if(Withsout.contains("15"))with=true;
			
			RunningProject Pret=RunningProjectUtils.runProject(project, 10, "max\ndeposit\nchecking\n30\ntransfer\nchecking\n25\ndeposit\nsaving\n3\n");
			String pretout=Pret.await().toLowerCase();
			RunningProject Trans=RunningProjectUtils.runProject(project, 10, "max\ndeposit\nchecking\n30\ntransfer\nchecking\n25\ndeposit\nsaving\n3\nprint\n");
			String Transout=Trans.await().toLowerCase().substring(pretout.length()-1);
			if(Transout.contains("5")&&Transout.contains("28"))tran=true;
			
			if(depo||with||tran)print=true;
			if (print=false)return fail("none of the math flushed, or your program cannot print in the way specified in prompt");
			
			int numright=4;
			String partial="The following operations were not done correctly (tested against specific values and the math did not flush): ";
			if(!depo){
				numright--;
				partial+="deposit,";
			}
			if(!with){
				numright--;
				partial+="withdraw,";
			}
			if(!tran){
				numright--;
				partial+="transfer";	
			}
			return partialPass(numright/4,partial);
		}
	}

}
