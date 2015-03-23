package gradingTools.comp110.assignment4.testCases;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.ClassDescription;
import framework.project.ClassesManager;
import framework.project.Project;

public class searchForGerbilTestCase extends BasicTestCase  {
	String methodName;
	Class<?> returnType;
	Class<?>[] paramTypes;
	ArrayList<String> badClass;
	
	public searchForGerbilTestCase() {
		super("Class contains a working averageFood test case");
		// TODO Auto-generated constructor stub
	}

	
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		// TODO Auto-generated method stub
		badClass.add("gerbil");
		badClass.add("food");
		if (project.getClassesManager().isEmpty()) {
			throw new NotAutomatableException();
		}
		ClassesManager manager = project.getClassesManager().get();
		Object returner = null;	//will use to make sure I am returning a gerbil object
		for (ClassDescription description : manager.getClassDescriptions() ) {
			Class<?> javaClass = description.getJavaClass();
			if(javaClass.getName().toLowerCase().equals("gerbil")){
				 returner=description.getClass(); //I believe this is how I make returner a gerbil object
			}
			if (!badClass.contains(javaClass.getName().toLowerCase())) {//should get the remaining class in program
				continue;
			}
			
			for (Method method : javaClass.getDeclaredMethods()) {
				boolean correctName = method.getName().toLowerCase().equals("searchforgerbil");//decided to ignore case here
    			boolean correctVisibility = Modifier.isPublic(method.getModifiers()); //should be public
    			boolean correctReturnType = method.getReturnType().equals(returner)&&returner!=null;//should return a string
    			boolean correctParameterTypes = false;
    			Class[] paramClasses = method.getParameterTypes();
    			if (paramClasses.length == 1 && paramClasses[0]==String.class) { 
    					correctParameterTypes = true;
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
    					message += "method does not return Gerbil object, ";
    				}
    				if (!correctParameterTypes) {
    					incorrectCount++;
    					message += "method does not take in a String, ";
    				}
    				
    				message = message.substring(0,message.length()-2);
    				return partialPass((4.0-incorrectCount)/4.0, message);
    			}
			}
		}return fail("No searchForGerbil method found in program");
		
	}


	}
