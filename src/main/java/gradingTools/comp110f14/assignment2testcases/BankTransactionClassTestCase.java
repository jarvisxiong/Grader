package gradingTools.comp110f14.assignment2testcases;

import java.util.ArrayList;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import grader.basics.project.ClassDescription;
import grader.basics.project.ClassesManager;
import grader.basics.project.Project;

public class BankTransactionClassTestCase extends BasicTestCase {

	public BankTransactionClassTestCase() {
		super("Contains BankTransaction Class test case");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		// TODO Auto-generated method stub
		if (project.getClassesManager().isEmpty()) {
			throw new NotAutomatableException();
		}
		ClassesManager manager = project.getClassesManager().get();
		boolean containsClass = false;
		ArrayList<String> foundClasses = new ArrayList<String>();
		boolean correctspelling=false;
		for (ClassDescription description : manager.getClassDescriptions()) {
			Class<?> javaClass = description.getJavaClass();
			if (javaClass.getName().equalsIgnoreCase("BankTransaction")) {
				containsClass = true;
			}
			if(javaClass.getName().equals("BankTransaction"))correctspelling=true;
			foundClasses.add(javaClass.getName());
		}
		if (!containsClass) {
			return fail("No BankTransaction class found.  Only found: "+foundClasses);
		}
		if(!correctspelling){
			return partialPass(0.5, "Incorrect Capitalization");
		}
		return pass();

	}
}