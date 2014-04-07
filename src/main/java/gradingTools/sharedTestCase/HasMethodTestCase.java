package gradingTools.sharedTestCase;

import java.lang.reflect.Method;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.ClassDescription;
import framework.project.ClassesManager;
import framework.project.Project;

public class HasMethodTestCase extends BasicTestCase{
	String requiredClassName;
	String methodName;
	Class<?> returnType;
	Class<?>[] paramTypes;

	public HasMethodTestCase(String name) {
		super(name);
	}

	
	
	@Override
	public TestCaseResult test(Project project, boolean autoGrade) throws NotAutomatableException,
			NotGradableException {
		
		if (project.getClassesManager().isEmpty()) {
			throw new NotAutomatableException();
		}
		ClassesManager manager = project.getClassesManager().get();
		
		boolean found = false;
		for (ClassDescription description : manager.getClassDescriptions() ) {
			Class<?> javaClass = description.getJavaClass();
			if (requiredClassName != null && requiredClassName.equals(javaClass.getName())) {
				continue;
			}
			
			for (Method method : javaClass.getDeclaredMethods()) {
				// Check if public
				// Check if return value is correct type (matches above class)
				// Check if paramTypes match corresponding above classes
			}
		}
		
		// TODO Auto-generated method stub
		return null;
	}

}
