package grader.permissions.java;

import java.io.File;

import wrappers.framework.project.ProjectWrapper;
import framework.grading.testing.TestCase;
import framework.project.Project;
import grader.language.LanguageDependencyManager;

public class JavaProjectToPermissionFile {

	public static File getPermissionFile(Project aProject) {
		Object[] aPermissions = null; 
		if (aProject instanceof ProjectWrapper) {
			TestCase aTestCase = 
    		((ProjectWrapper) aProject).getProject().getCurrentTestCase();
			if (aTestCase != null) {
				aPermissions = aTestCase.getPermissions();
			}
    	}
		if (aPermissions == null) {
			
		    aPermissions	= LanguageDependencyManager.getDefaultPermissible().getPermissions();
		}
		return LanguageDependencyManager.getPermissionGenerator().permissionFile(aProject, aPermissions);
	}

}
