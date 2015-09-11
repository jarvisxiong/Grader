package gradingTools.comp110f15lab.lab1.testcases;
import java.lang.reflect.Field;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.ClassDescription;
import framework.project.ClassesManager;
import framework.project.Project;
import gradingTools.utils.RunningProjectUtils;

public class CorrectMath extends BasicTestCase {

	public CorrectMath() {
		super("EverythingElse");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException, SecurityException {
		if (project.getClassesManager().isEmpty())
			throw new NotGradableException();
		ClassesManager manager = project.getClassesManager().get();

		// Check for a class with the name HelloWorld
		for (ClassDescription description : manager.getClassDescriptions()) {
			if (description.getJavaClass().getName().equalsIgnoreCase("lab1")
					|| description.getJavaClass().getName().toLowerCase().matches(".+[.]lab1")) {
			Object theClass=description.getJavaClass();
			try{
				Field field = theClass.getClass().getField("num1");
				Field field2 = theClass.getClass().getField("num2");
				int num1=field.getInt(theClass);
				int num2=field2.getInt(theClass);
				double sum =num1+num2;
				double product=num1*num2;
				double quotient=num2/num1;
				double rem=num2%num1;
				RunningProject goo = RunningProjectUtils.runProject(project, 10, "");
				String output=goo.await();
				int numright=0;
				if(output.contains(""+sum)||output.contains(""+(int)sum)){
					numright++;
				}
				return pass(); //this is a great example of using reflection to grab values...stashing away....
				
			}
			catch(NoSuchFieldException f){
				return fail("could not reflect the values you declared. TA, please grade by hand...");
			}
			catch(IllegalAccessException e){
				return fail("could not reflect the values you declared. TA, please grade by hand...");
			}
			
			}
		}
		return null;
	}

}
