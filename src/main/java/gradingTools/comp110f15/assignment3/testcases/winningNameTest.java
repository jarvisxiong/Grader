package gradingTools.comp110f15.assignment3.testcases;


import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.ClassDescription;
import framework.project.Project;
import framework.project.ClassesManager;

public class winningNameTest extends BasicTestCase{

	public winningNameTest() {
		super("checks winningNameTest Case");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException,
			NoSuchFieldException {
		if (project.getClassesManager().isEmpty()) {
			throw new NotAutomatableException();
		}
		ClassesManager manager = project.getClassesManager().get();
		for (ClassDescription description : manager.getClassDescriptions() ) {
			Class<?> javaClass = description.getJavaClass();
			
			for (Method method : javaClass.getDeclaredMethods()) {
				boolean correctName = method.getName().toLowerCase().equals("winningname");//decided to ignore case here
    			boolean correctVisibility = Modifier.isPublic(method.getModifiers()); //should be public
    			boolean correctReturnType = method.getReturnType().equals(String.class);//should return a string
    			if(correctName&&correctVisibility&&correctReturnType) return partialPass(0,"This method is meant to be handgraded do to other method invokations in here");
    			}
		}return fail("No method winningNameTest found in program, grader please confirm.");
		
	}
}
