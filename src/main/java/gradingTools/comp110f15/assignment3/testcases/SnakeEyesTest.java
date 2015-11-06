package gradingTools.comp110f15.assignment3.testcases;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.ClassDescription;
import framework.project.ClassesManager;
import framework.project.Project;

public class SnakeEyesTest extends BasicTestCase {

	public SnakeEyesTest() {
		super("Snake Eyes Test");
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
				boolean correctName = method.getName().toLowerCase().equals("snakeeyes");//decided to ignore case here
    			boolean correctVisibility = Modifier.isPublic(method.getModifiers()); //should be public
    			boolean correctReturnType = method.getReturnType().equals(Boolean.TYPE);//should return a string
    			if(correctName&&correctVisibility&&correctReturnType)
					try {
					Object result1=method.invoke(null,1,1);
					Object result2=method.invoke(null,6,4);
					Object result3=method.invoke(null, 1,3);
					boolean b1=result1.equals(Boolean.TRUE);
					boolean b2=result2.equals(Boolean.FALSE);
					boolean b3=result3.equals(Boolean.FALSE);
					if(b1&&b2&&b3)return pass();
					else{
						String message="";
						int numwrong=0;
						if(!b1){
							numwrong++;
							message+="does not return true when dice values are 1 and 1\n";
						}
						if(!b2){
							numwrong++;
							message+="does not return false when dice values are 6 and 4\n";
						}
						if(!b3){
							numwrong++;
							message+="does not return false when dice values are 1 and 3\n";
						}
						return partialPass((3-numwrong)/3,message);
					}
					} catch (IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
		}return fail("No method snakeEyes found in program, grader please confirm.");
	}

}
