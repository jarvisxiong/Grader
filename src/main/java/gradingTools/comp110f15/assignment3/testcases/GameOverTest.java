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

public class GameOverTest extends BasicTestCase {

	public GameOverTest() {
		super("GameOver Test Case");
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
				boolean correctName = method.getName().toLowerCase().equals("gameover");//decided to ignore case here
    			boolean correctVisibility = Modifier.isPublic(method.getModifiers()); //should be public
    			boolean correctReturnType = method.getReturnType().equals(Integer.TYPE);//should return a string
    			if(correctName&&correctVisibility&&correctReturnType)
					try {
					int result1=(int)method.invoke(null,50,4);
					int result2=(int)method.invoke(null,5,4);
					int result3=(int)method.invoke(null,1,1);
					int result4=(int)method.invoke(null,5,52);
					int result5=(int)method.invoke(null,57,57);
					boolean b1= result1==1;
					boolean b2= result2==0;
					boolean b3= result3==-1;
					boolean b4=result4==1;
					boolean b5=result5==-1;
					if(b1&&b2&&b3&&b4&&b5)return pass();
					else{
						String message="";
						int numwrong=0;
						if(!b1){
							numwrong++;
							message+="does not return 1 when score is 50 to 4\n";
						}
						if(!b2){
							numwrong++;
							message+="does not return 0 when score is 5 to 4\n";
						}
						if(!b3){
							numwrong++;
							message+="does not return -1 when score is 1 to 1\n";
						}
						if(!b4){
							numwrong++;
							message+="does not return 1 when score is 5 to 52\n";
						}
						if(!b5){
							numwrong++;
							message+="does not return -1 when score is 57 to 57";
						}
						return partialPass((5-numwrong)/5,message);
					}
					} catch (IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
	}return fail("Could not find the requried class and/or method to do this test");
	
}
}
