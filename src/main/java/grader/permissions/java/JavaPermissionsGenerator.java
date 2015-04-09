package grader.permissions.java;

import java.io.File;
import java.security.Permission;

import util.misc.Common;
import util.models.Hashcodetable;
import wrappers.framework.project.ProjectWrapper;
import framework.grading.testing.TestCase;
import framework.project.Project;
import grader.file.FileProxy;
import grader.permissions.PermissionsGenerator;
import grader.sakai.project.ASakaiProjectDatabase;
import grader.sakai.project.SakaiProjectDatabase;

public class JavaPermissionsGenerator implements PermissionsGenerator{
	Hashcodetable<Object[], File> permissionsToFile = new Hashcodetable();
	

	@Override
	public File generatePermissions(Project aProject, TestCase aTestCase) {
		Object[] aPermissions = aTestCase.getPermissions();
		File aFile = permissionsToFile.get(aPermissions);
		if (aFile == null) {
			aFile = new File(composePermissionFileName(aProject);
			String aPermissionString = composePermissionString(aPermissions);
			Common.writeText(aFile, aPermissionString);
			
		}
		return null;
	}
	
	public String composePermissionString(Object[] permissions) {
		StringBuilder sb = new StringBuilder();
		sb.append("grant codeBase \"").append(".").append("\" {\n");
		for (Object o : permissions) {
		if (o instanceof Permission) {
		Permission p = (Permission) o;
		sb.append("\tpermission ").apend(perm.getClass().getName()).append(" \"").append(perm.getName()).append("\", \"").append(perm.getActions()).append("\";\n");
		}
		}
		sb.append("};");

		String fileText = sb.toString();
	}
	
	public String composePermissionFileName(Project aProject) {
		return ASakaiProjectDatabase.getCurrentSakaiProjectDatabase().
				getBulkAssignmentFolder().getAssignmentFolder().getMixedCaseAbsoluteName() + 
				"/" + permissionsToFile.size();
	} 
		
		
		
//		if (aProject instanceof ProjectWrapper) {
//			SakaiProjectD
//			SakaiProject aSakaiProject = ((ProjectWrapper) aProject).getProject().
//		}
//		
	}

}
