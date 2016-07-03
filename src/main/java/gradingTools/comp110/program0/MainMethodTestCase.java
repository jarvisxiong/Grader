package gradingTools.comp110.program0;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import framework.grading.testing.BasicTestCase;
import grader.basics.junit.NotAutomatableException;
import grader.basics.junit.TestCaseResult;
import grader.basics.project.ClassDescription;
import grader.basics.project.ClassesManager;
import grader.basics.project.NotGradableException;
import grader.basics.project.Project;

public class MainMethodTestCase extends BasicTestCase {

	public MainMethodTestCase() {
		super("HelloWorld class test case");
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {

		// You can get the classes and information about them from the project
		// using the ClassesManager
		if (project.getClassesManager().isEmpty())
			throw new NotGradableException();
		ClassesManager manager = project.getClassesManager().get();

		// Find the class class with the name HelloWorld
		for (ClassDescription description : manager.getClassDescriptions()) {
			Class javaClass = description.getJavaClass();
			if (javaClass.getName().equalsIgnoreCase("HelloWorld")
					|| description.getJavaClass().getName().matches(".+[.]HelloWorld")) {
				for (Method method : javaClass.getDeclaredMethods()) {

					// Check that the signature is public static void main
					// (String[] ...)

					boolean correctName = method.getName().equals("main");
					boolean correctVisibility = Modifier.isPublic(method.getModifiers());
					boolean correctStatic = Modifier.isStatic(method.getModifiers());
					boolean correctReturnType = method.getReturnType().equals(Void.TYPE);
					boolean correctParameterTypes = false;
					Class[] paramClasses = method.getParameterTypes();
					if (paramClasses.length == 1) {
						if (paramClasses[0].equals((new String[0]).getClass())) {
							correctParameterTypes = true;
						}
					}

					if (correctName && correctVisibility && correctStatic && correctReturnType
							&& correctParameterTypes) {
						return pass();
					} else if (correctName) {
						int incorrectCount = 0;
						String message = "";
						if (!correctVisibility) {
							incorrectCount++;
							message += "main method not public, ";
						}
						if (!correctStatic) {
							incorrectCount++;
							message += "main method not static, ";
						}
						if (!correctReturnType) {
							incorrectCount++;
							message += "main method does not return void, ";
						}
						if (!correctParameterTypes) {
							incorrectCount++;
							message += "main does not take String[] as its only parameter, ";
						}

						message = message.substring(0, message.length() - 2);
						return partialPass((5.0 - incorrectCount) / 5.0, message);
					}
				}
			}
		}

		return fail("No method main found in class with name HelloWorld");
	}
}