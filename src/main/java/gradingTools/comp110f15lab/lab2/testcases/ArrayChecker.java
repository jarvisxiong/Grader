package gradingTools.comp110f15lab.lab2.testcases;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import framework.execution.RunningProject;
import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.ClassDescription;
import framework.project.Project;
import framework.project.ClassesManager;
import gradingTools.utils.RunningProjectUtils;

public class ArrayChecker extends BasicTestCase {

	public ArrayChecker() {
		super("Correct population of array");
	}

	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		// TODO Auto-generated method stub
		if (project.getClassesManager().isEmpty())
			throw new NotGradableException();
		ClassesManager manager = project.getClassesManager().get();
		Field target = null; // this is the desired array

		// Check for a class with the name HelloWorld
		for (ClassDescription description : manager.getClassDescriptions()) {
			if (description.getJavaClass().getName().equalsIgnoreCase("lab2")
					|| description.getJavaClass().getName().toLowerCase()
							.matches(".+[.]lab2")) {
				Object theClass = description.getJavaClass();
				try {
					Field[] fields = theClass.getClass().getFields();
					theClass.getClass().getDeclaredField("sum");
					for (Field field : fields) {
						if (field.getName().contains("mult")){		
						target = field;
						break;
						}
					}
					RunningProject goo = RunningProjectUtils.runProject(project, 10,
							"3\n3\n6\n7\n9\n");
					goo.await();
						for (int i = 0; i < 3; i++) {
							if (Array.getInt((Object) target, i) % 3 != 0
									|| Array.getInt((Object) target, i) == 0)
								
							return fail("Your array contained a non-multiple of three!");
						}
						return pass("Your array is correctly populated");

					}
				 catch (Exception f) {
					return fail("Looks like student did not have array as a public field, therefore it is not visible by reflection...please check by hand");
				}

			}
		}
		return fail("Looks like student did not have array as a public field, therefore it is not visible by reflection...please check by hand");

	}

}
