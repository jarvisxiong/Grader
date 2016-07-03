package gradingTools.comp110f14lab.lab3.testcases;

import java.util.regex.Pattern;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import grader.basics.execution.RunningProject;
import grader.basics.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class DoesRestTestCase extends BasicTestCase {

	public DoesRestTestCase() {
		super("Contains a main method Test Case");
		
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		boolean worksforA=false;
		boolean worksforB=false;
		boolean Cprint=false;
		boolean printincorrect=false;
		boolean lengthprint=false;
		
		String worksforAString1="Abc";
		String worksforBString1="Bcc";
		String worksforCString1="Cba";
		String lengthprint1="owiefjoweifjowfeijewf";
		String lengthprint2="jw";
		
		Pattern o02= Pattern.compile(".*cor.*");
		Pattern o4= Pattern.compile(".*cig.*");
		Pattern o6= Pattern.compile(".*in|wr.*");
		Pattern o6b=Pattern.compile(".*long.*");
		Pattern o7=Pattern.compile(".*short.*");
		
		
		
		RunningProject goo0=RunningProjectUtils.runProject(project, 3,worksforAString1);
		String output0=goo0.await();
		RunningProject goo2=RunningProjectUtils.runProject(project, 3,worksforBString1);
		String output2=goo2.await();
		RunningProject goo4=RunningProjectUtils.runProject(project, 3,worksforCString1);
		String output4=goo4.await();
		RunningProject goo6=RunningProjectUtils.runProject(project, 3,lengthprint1);
		String output6=goo6.await();
		RunningProject goo7=RunningProjectUtils.runProject(project, 3,lengthprint2);
		String output7=goo7.await();
		
		String partialmessage="";
		
		if(o02.matcher(output0.toLowerCase()).find())worksforA=true;
		else partialmessage+="Does not work for A\n";
		if(o02.matcher(output2.toLowerCase()).find())worksforB=true;
		else partialmessage+="Does not work for B\n";
		if(o4.matcher(output4.toLowerCase()).find())Cprint=true;
		else partialmessage+="Does not work for C\n";
		if(o6.matcher(output6.toLowerCase()).find())printincorrect=true;
		else partialmessage+="Does not work for printing incorrect\n";
		if(o6b.matcher(output6.toLowerCase()).find()&&o7.matcher(output7.toLowerCase()).find())lengthprint=true;
		else partialmessage+= "Short/Long print problem";
		boolean[] foo=new boolean[5] ;
			foo[0]=worksforA;
			foo[1]=worksforB;
			foo[2]=Cprint;
			foo[3]=printincorrect;
			foo[4]=lengthprint;
		int grade=5;
		for(int i=0;i<5;i++){
			if(!foo[i])grade--;
		}
		if(grade==5)return pass();
		else if(grade>0) return partialPass(grade/5,partialmessage);
		else return fail(partialmessage);
		
	}
}
