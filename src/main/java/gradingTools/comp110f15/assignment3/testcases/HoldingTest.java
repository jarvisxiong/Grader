package gradingTools.comp110f15.assignment3.testcases;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.ClassDescription;
import framework.project.ClassesManager;
import framework.project.Project;

public class HoldingTest extends BasicTestCase{

	public HoldingTest() {
		super("Holding Test");
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
		Class G = null;
		for (ClassDescription description : manager.getClassDescriptions() ) {
			Class<?> javaClass = description.getJavaClass();
			if(javaClass.getName().equals("Game")){
				G=javaClass;
				break;
			}
		}
		if(G!=null){
			Method holding=null;
					for (Method m : G.getMethods()) {
						if(m.getName().equals("holding")){
							holding=m;
							break;
						}
					}
					if(holding!=null){
						Scanner k1=new Scanner("1");
						Scanner k2=new Scanner("2");
						Scanner k3=new Scanner("3 4 6 99 1");
						try {
							boolean b1=(boolean)holding.invoke(null, k1);
							boolean b2=(boolean)holding.invoke(null, k2);
							boolean b3=(boolean)holding.invoke(null, k3);
							k1.close();
							k2.close();
							k3.close();
							
							if(b1&&!b2&&b3)return pass();
							
							int numwrong=0;
							String message="";
							if(!b1){
								numwrong++;
								message+="Did not return true after user typed in 1";
							}
							if(b2){
								numwrong++;
								message+="Did not return false after user typed in 2";
							}
							if(!b3){
								numwrong++;
								message+="Did not return true after user typed in multiple unrecognized commands and then 1";
							}
							return partialPass((3-numwrong)/3,message);
						} catch (IllegalAccessException
								| IllegalArgumentException
								| InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}return fail("Could not find Game.holding");
		}return fail("Couldn't find Game.java");
	}

}
