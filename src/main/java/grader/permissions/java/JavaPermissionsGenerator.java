package grader.permissions.java;

import java.io.File;
import java.io.IOException;
import java.security.Permission;

import util.misc.Common;
import util.models.Hashcodetable;
import wrappers.framework.project.ProjectWrapper;
import framework.project.Project;
import grader.file.FileProxy;
import grader.permissions.PermissionsGenerator;
import grader.sakai.project.ASakaiProjectDatabase;
import grader.sakai.project.SakaiProjectDatabase;

public class JavaPermissionsGenerator implements PermissionsGenerator{
	Hashcodetable<Object[], File> permissionsToFile = new Hashcodetable<>();
	

	@Override
	public File permissionFile(Project aProject, Object[] aPermissions) {
//		Object[] aPermissions = aPermissions.getPermissions();
		File aFile = permissionsToFile.get(aPermissions);
		if (aFile == null) {
			aFile = new File(composePermissionFileName(aProject));
			String aPermissionString = composePermissionString(aPermissions);
			try {
				Common.writeText(aFile, aPermissionString);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return aFile;
	}
	
	public String composePermissionString(Object[] permissions) {
		StringBuilder sb = new StringBuilder();
		sb.append("grant codeBase \"").append("file:.").append("\" {\n");
		for (Object o : permissions) {
			if (o instanceof Permission) {
				Permission perm = (Permission) o;
				sb.append("\tpermission ").append(perm.getClass().getName()).append(" \"").append(perm.getName()).append("\", \"").append(perm.getActions()).append("\";\n");
			}
		}
		sb.append("};");

		return sb.toString();

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
