package gradingTools.comp110f14lab.lab6.testcases;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import grader.basics.project.ClassDescription;
import grader.basics.project.ClassesManager;
import grader.basics.project.Project;

public class HasreplaceTypo extends BasicTestCase {
	String methodName;
	Class<?> returnType;
	Class<?>[] paramTypes;
	public HasreplaceTypo() {
		super("has a replaceTypo method");
		// TODO Auto-generated constructor stub
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		if (project.getClassesManager().isEmpty()) {
			throw new NotAutomatableException();
		}
		ClassesManager manager = project.getClassesManager().get();
		for (ClassDescription description : manager.getClassDescriptions() ) {
			Class<?> javaClass = description.getJavaClass();
			
			for (Method method : javaClass.getDeclaredMethods()) {
				boolean correctName = method.getName().toLowerCase().equals("replacetypo");//decided to ignore case here
    			boolean correctVisibility = Modifier.isPublic(method.getModifiers()); //should be public
    			boolean correctReturnType = method.getReturnType().equals(String.class);//should return a string
    			boolean correctParameterTypes = false;
    			Class[] paramClasses = method.getParameterTypes();
    			if (paramClasses.length == 3) { //should take in 3 parameters
    				boolean good=true;
    				for (int i = 0; i < paramClasses.length; i++) {
						if(paramClasses[i]!=String.class) good=false;
					}
    				if(good)correctParameterTypes = true;
    					}
    			if (correctName && correctVisibility && correctReturnType && correctParameterTypes) {
    				return pass();
    			} else if (correctName) {
    				int incorrectCount = 0;
    				String message = "";
    				if (!correctVisibility) {
    					incorrectCount++;
    					message += "method not public, ";
    				}
    				if (!correctReturnType) {
    					incorrectCount++;
    					message += "method does not return String, ";
    				}
    				if (!correctParameterTypes) {
    					incorrectCount++;
    					message += "method does not have correct parameters, ";
    				}
    				
    				message = message.substring(0,message.length()-2);
    				return partialPass((4.0-incorrectCount)/4.0, message);
    			}
			}
		}return fail("No method replaceTypo found in program");
	}
		
	
}

