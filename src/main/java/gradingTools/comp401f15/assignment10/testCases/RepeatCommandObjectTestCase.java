package gradingTools.comp401f15.assignment10.testCases;

import java.io.IOException;
import java.util.Set;

import tools.CompilationNavigation;
import wrappers.framework.project.ProjectWrapper;

import com.github.antlrjavaparser.api.body.ClassOrInterfaceDeclaration;
import com.github.antlrjavaparser.api.body.MethodDeclaration;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.Project;
import grader.project.flexible.FlexibleClassDescription;
import grader.sakai.project.SakaiProject;

public class RepeatCommandObjectTestCase extends BasicTestCase {
	
	public RepeatCommandObjectTestCase () {
		super ("Repeat Command Test Case");
		
	}
	@Override
	public TestCaseResult test(Project project, boolean autoGrade)
			throws NotAutomatableException, NotGradableException {
		ProjectWrapper aProjectWrapper = (ProjectWrapper) project;
		SakaiProject aSakaiProject = aProjectWrapper.getProject();
		Set<FlexibleClassDescription> descriptions = aSakaiProject.getClassesManager().tagToClassDescriptions("RepeatCommand");
		if (descriptions == null || descriptions.isEmpty()) {
			descriptions = aSakaiProject.getClassesManager().tagToClassDescriptions("Repeat");
		}
		if (descriptions == null || descriptions.isEmpty()) {
			return fail ("Repeat command object not found");
		}
		boolean foundRunnable = false;
		FlexibleClassDescription description = null;
		for (FlexibleClassDescription aClassDescription:descriptions) {
			foundRunnable = Runnable.class.isAssignableFrom(aClassDescription.getJavaClass());
			if (foundRunnable) {
				description = aClassDescription;
				break;
			}
		}
		if (!foundRunnable) {
			return fail ("Repeat command not a runnable");
		}
		// Check that it loops and runs the command within the loop
        try {
            ClassOrInterfaceDeclaration classDef = CompilationNavigation.getClassDef(description.getCompilationUnit());
            MethodDeclaration method = CompilationNavigation.getMethod(classDef, "run");
            String code = method.getBody().toString();
            StringBuilder result = new StringBuilder();
            // Look for a loop
            double passes = 0;
            if (code.contains("for") || code.contains("while"))
                passes++;
            else
                result.append("Couldn't find a loop.");

            // Look for run
            if (code.contains(".run();"))
                passes++;
            else
                result.append("Couldn't find a run invocation.");
//            result.setScore(this.getCheckable().get * (passes / 3.0));
            if (passes == 2)
            	return pass();
            return partialPass(passes/2.0, result.toString());
        } catch (IOException e) {
            return null;
        }
    
		
//		Class<?> clazz = IntrospectionUtil.getOrFindClass(project, this, "RepeatCommand");
//		if (clazz != null) {
//			boolean isRunnable = Runnable.class.isAssignableFrom(clazz);
//			if (!isRunnable) {
//				return fail ("Repeat command not a runnable");
//			}
//		} else {
//			List<Class> aClasses = IntrospectionUtil.findClasses(project, "Repeat", "Repeat", "Repeat", "Repeat");
//			if (aClasses.isEmpty()) {
//				return fail ("Repeat command object not found");
//			}
//			boolean foundRunnable = false;
//			for (Class aClass:aClasses) {
//				clazz = aClass;
//				foundRunnable = Runnable.class.isAssignableFrom(clazz);
//				if (foundRunnable)
//					break;
//			}
//			if (!foundRunnable) {
//				return fail ("Repeat command not a runnable");
//			}			
//		}
		
		
	}
}
