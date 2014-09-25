package gradingTools.comp110f14.lab2.testcases;

import java.util.regex.Pattern;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class RestTestCase extends BasicTestCase {

	public RestTestCase() {
		super("Rest Test Case");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		// TODO Auto-generated method stub
		RunningProject goo=RunningProjectUtils.runProject(project, 3,"");
		String output=goo.await();
		boolean concat=false;
		boolean len=false;
		boolean low=false;
		int numoff=5;
		concat=Pattern.matches(".*Computer.*Science.*is.*fun.*", output);
		//concat=output.matches(".*Computer.*Science.*is.*fun.*");
		len=Pattern.matches(".*((2[0-9])|(30)).*", output);
		//len=output.matches(".*[20-30].*");
		concat=Pattern.matches(".*computer.*science.*is.*fun.*", output);
		//low=output.matches(".*computer.*science.*is.*fun.*");
		if(concat&&len&&low)return pass();
		if((!concat&&!len&&!low))return fail("no correct concatenated statement, length, or lower case ");
		String partialpassmessage = "";
		if(!concat){
			numoff-=2;
			partialpassmessage+="no correct concatenated message.";
		}
		if(!len){
			numoff--;
			partialpassmessage+=" no correct length.";
		}
		if(!low){
			numoff--;
			partialpassmessage+=" no correct lower case printing";
		}
		double partialpassvalue=(5-numoff)/5;
		return partialPass(partialpassvalue,partialpassmessage);
	}

}
