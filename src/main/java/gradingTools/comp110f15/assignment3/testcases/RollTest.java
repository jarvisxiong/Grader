package gradingTools.comp110f15.assignment3.testcases;

import java.lang.reflect.Constructor;
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

public class RollTest extends BasicTestCase{

	public RollTest() {
		super("Checks proper roll distribution");
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
				boolean correctName = method.getName().toLowerCase().equals("roll");//decided to ignore case here
    			boolean correctVisibility = Modifier.isPublic(method.getModifiers()); //should be public
    			//boolean correctReturnType = method.getReturnType().equals();//should return a string
    			if(correctName&&correctVisibility){
    				//return partialPass(0,"Cannot invoke void instance methods that give me back anything meaningful, please grade by hand.");
    			try {
					Class klass=javaClass;
					Method roll=getMethod(klass,"roll");
					Method getValue=getMethod(klass,"getValue");
					getValue.setAccessible(true);
					Object die =getInstanceOf(klass);
					int rollResults[]=new int[6];
					for(int i=0;i<10000;i++){
						roll.invoke(die);
						rollResults[(int)getValue.invoke(die)-1]+=1;
					}
					boolean passes=true;
					for (int count : rollResults) {
						if(count==0)return fail("We rolled your die 10000 times and not all 6 values were rolled.");
					}return pass();
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			
    			}
    			}
		}return fail("No method Roll found in program, grader please confirm.");
	}
	 static Method getMethod(Class klass, String name) {
		    try {
		      Class[] parameterTypes = {};
		      return klass.getDeclaredMethod(name, parameterTypes);
		    } catch(Exception e) {
		      return null;
		    }
		  }
	 static Object getInstanceOf(Class klass) {
		    try {
		      Class[] parameterTypes = {};
		      Constructor constructor = klass.getConstructor(parameterTypes);
		      Object[] parameters = {};
		      return constructor.newInstance(parameters);
		    } catch(Exception e) {
		      return null;
		    }
		  }

}
