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
		Class Player=null;
		Class Game=null;
		ClassesManager manager = project.getClassesManager().get();
		for (ClassDescription description : manager.getClassDescriptions() ) {
			Class<?> javaClass = description.getJavaClass();
			if(javaClass.getName().equals("Game"))Game=javaClass;
			if(javaClass.getName().equals("Player"))Player=javaClass;
		}
		if(Game!=null&&Player!=null){
			Method addPoints=null;
			Method winningName=null;
			for (Method m : Player.getMethods()) {
				if(m.getName().equals("addPoints")){
					addPoints=m;
					break;
				}
			}
			for (Method m: Game.getMethods()){
				if(m.getName().equals("winningName")){
					winningName=m;
					break;
				}
			}
			if(winningName!=null&&addPoints!=null){
				Object P1=getInstanceOf(Player,"Yennifer");
				Object P2=getInstanceOf(Player,"Triss");
				try {
					addPoints.invoke(P1, 54);
					addPoints.invoke(P2, 53);
					String result=(String)winningName.invoke(null, P1,P2);
					if(result.equals("Yennifer"))return pass();
					return fail("Player's name w/ the lower score was returned");
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			return fail("Could not find one/all of Game.winningName and Player.addPoints");
			
		}return fail("Could not find one of the required classes needed to grade this.");
		
	}
	static Object getInstanceOf(Class klass,String name) {
	    try {
	      Class[] parameterTypes = {String.class};
	      Constructor constructor = klass.getConstructor(parameterTypes);
	      Object[] parameters = {name};
	      return constructor.newInstance(parameters);
	    } catch(Exception e) {
	      return null;
	    }
	  }
}
