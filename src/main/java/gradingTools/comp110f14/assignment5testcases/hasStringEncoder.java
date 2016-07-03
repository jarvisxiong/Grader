package gradingTools.comp110f14.assignment5testcases;

import java.lang.reflect.Method;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import grader.basics.project.ClassDescription;
import grader.basics.project.ClassesManager;
import grader.basics.project.Project;

public class hasStringEncoder extends BasicTestCase {
	public boolean stringfirsten=false;
	String methodName;
	Class<?> returnType;
	Class<?>[] paramTypes;
	public hasStringEncoder() {
		super("Has StringEncoder Method");
		// TODO Auto-generated constructor stub
	}//return String take in String and char[]
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
				boolean correctName = method.getName().toLowerCase().equals("stringencoder");//decided to ignore case here
    			boolean correctReturnType = method.getReturnType().equals(String.class);//should return a string
    			boolean correctParameterTypes = false;
    			Class[] paramClasses = method.getParameterTypes();
    			if (paramClasses.length == 2) { //should take in 3 parameters
    				if(paramClasses[0]==String.class&&paramClasses[1].isArray()){
    					correctParameterTypes=true;
    					stringfirsten=true;
    				}
    				else if(paramClasses[1]==String.class&&paramClasses[0].isArray())correctParameterTypes = true;
    					}
    			if (correctName && correctReturnType && correctParameterTypes) {
    				return pass();
    			} else if (correctName) {
    				int incorrectCount = 0;
    				String message = "";
    				if (!correctReturnType) {
    					incorrectCount++;
    					message += "method does not return String, ";
    				}
    				if (!correctParameterTypes) {
    					incorrectCount++;
    					message += "method does not have correct parameters, ";
    				}
    				
    				message = message.substring(0,message.length()-2);
    				return partialPass((3.0-incorrectCount)/3.0, message);
    			}
			}
		}return fail("No method replaceTypo found in program");
	}

}
