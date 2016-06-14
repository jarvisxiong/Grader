package gradingTools.comp110f14.assignment2testcases;

import java.util.Random;
import java.util.Scanner;

import framework.execution.BasicRunningProject;
import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class BalancePrintingTestCase extends BasicTestCase {

	public BalancePrintingTestCase() {
		super("Balance Printing Test Case");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		Random r= new Random();
		int depo=r.nextInt(Integer.MAX_VALUE)+1;
		int withd=r.nextInt(depo);
		while(withd>depo){
			withd=r.nextInt(depo);
		}
		int result=depo-withd;
		BasicRunningProject runningProject = RunningProjectUtils.runProject(project, 10,""+depo);
		String initialbalanceoutput=runningProject.await();
		boolean print1=false;
		if(initialbalanceoutput.contains(""+depo))print1=true;
		BasicRunningProject runningProject2=RunningProjectUtils.runProject(project, 10,""+depo+"\n"+withd);
		String finalbalanceoutput=runningProject2.await();
		boolean print2=false;
		if(finalbalanceoutput.contains(""+(depo-withd)))print2=true;
		boolean p1int=true;
		boolean p2int=true;
		Scanner goo=new Scanner(finalbalanceoutput);
		while(goo.hasNext()){
			String line=goo.nextLine();
			if(line.contains(""+depo+".0"))p1int=false;
			if(line.contains(""+result+".0"))p2int=false;
		}
		if(print1&&print2&&p1int&&p2int)return pass();
		if(print1&&!p1int&&print2&&p2int)return partialPass(0.78,"printed initial balance and final balance, but initial balance was not int");
		if(print1&&p1int&&print2&&!p2int)return partialPass(0.71,"printed initial balance and final balance, but final balance was not int");
		if(print1&&!p1int&&print2&&!p2int)return partialPass(0.5,"printed initial balance and final balance, but initial final balance were not ints");
		if(print1&&!print2)return partialPass(0.43,"did not print correct final balance");
		if(!print1&&print2)return partialPass(0.57,"did not print correct initial balance");
		return fail("did not print any balances correctly");
	}

}
