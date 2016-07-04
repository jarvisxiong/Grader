package grader.permissions.java;

import framework.grading.testing.TestCase;
import grader.basics.project.Project;
import grader.language.LanguageDependencyManager;

import java.io.File;

import wrappers.framework.project.ProjectWrapper;

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
