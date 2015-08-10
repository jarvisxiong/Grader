package gradingTools.sharedTestCase;

import framework.grading.testing.BasicTestCase;
import framework.grading.testing.NotAutomatableException;
import framework.grading.testing.NotGradableException;
import framework.grading.testing.TestCaseResult;
import framework.project.ClassDescription;
import framework.project.Project;
import grader.sakai.project.SakaiProject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import wrappers.framework.project.ProjectWrapper;


public class PackageDeclarationTestCase extends CheckStyleTestCase {
    public PackageDeclarationTestCase() {
        super("Package declaration test case");
    }
    
 
@Override
public String regexLineFilter() {
	return "(.*)Missing package declaration(.*)";
}

protected  TestCaseResult penalty (SakaiProject aProject, String[] aCheckStyleLines, List<String> aFailedLines, boolean autoGrade) {

	return classFractionPenalty(aProject, aCheckStyleLines, aFailedLines, autoGrade);
	
}


@Override
public String failMessageSpecifier() {
	// TODO Auto-generated method stub
	return "Missing package declaration";
}
}

