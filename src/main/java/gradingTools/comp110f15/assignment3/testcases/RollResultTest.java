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

public class RollResultTest extends BasicTestCase {
	public RollResultTest() {
		super("Has correct RollResult Method");
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
		Class D = null;
		Class G = null;
		for (ClassDescription description : manager.getClassDescriptions() ) {
			Class<?> javaClass = description.getJavaClass();
			if(javaClass.getName().equalsIgnoreCase("die"))D=javaClass;
			if(javaClass.getName().equalsIgnoreCase("game"))G=javaClass;
			
		}
		Method rollResult=null;
		for (Method m : G.getMethods()) {
			if(m.getName().equals("rollResult"))rollResult=m;
		}
		Object d1=getInstanceOf(D);
		Object d2=getInstanceOf(D);
		boolean gotzero=false;
		boolean gotnegone=false;
		boolean gotother=false;
		for(int i=0;i<10000;i++){
			try {
				int r=(int)rollResult.invoke(null,d1, d2);
				if(r==0)gotzero=true;
				if(r==-1)gotnegone=true;
				if(r>0)gotother=true;
				if(gotzero&&gotnegone&&gotother)return partialPass(0.5,"They roll their dice and many runs return all possible answers. Check if they use the boolean methods.");
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return fail("After 10000 runs, we did not get all three possible return values.");
		
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
