package gradingTools.comp110f15.assignment1.testcases;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class GameResult extends BasicTestCase {

	public GameResult() {
		super("Correct Game Result");
		// TODO Auto-generated constructor stub
	}
	
	//unc wins p1
	//dook wins p2
	//tie p3
	

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		boolean W=false; //9
		boolean L =false; //8
		boolean T = false; //9
		boolean math1=false; //5
		boolean math2=false; //5
		boolean math3=false; //5
		boolean printscore=false; //5
		RunningProject p0=RunningProjectUtils.runProject(project, 10,"");
		String out0=p0.await();
		int base=out0.length()-1;
		RunningProject p1=RunningProjectUtils.runProject(project,10,"1000\n100\n10\n1\n5\n2\n9\n4");
		String out1=p1.await().substring(base);
		if(out1.toLowerCase().contains("unc won the game")) W=true;
		String[]lines=out1.toLowerCase().split("\n");
		int totalcounter=0;
		for (String line : lines) {
			if(line.contains("total"))totalcounter++;
			if(line.contains("1111"))math1=true;
			if(line.contains("20"))math2=true;
		}
		if(totalcounter==2)printscore=true;
		if(out1.contains("1091"))math3=true;
		
		RunningProject p2=RunningProjectUtils.runProject(project,10,"1\n0\n0\n1\n5\n2\n9\n4");
		String out2=p2.await().substring(base);
		if(out2.toLowerCase().contains("duke won the game")||out2.toLowerCase().contains("dook won the game")) L=true;
		
		RunningProject p3=RunningProjectUtils.runProject(project,10,"1\n0\n0\n1\n1\n0\n0\n1");
		String out3=p3.await().substring(base);
		if(out3.toLowerCase().contains("a tie")) T=true;
		
	}

}
