package grader.permissions;

import grader.basics.project.Project;

import java.io.File;

public interface PermissionsGenerator {
	File permissionFile(Project aProject, Object[] aPermissions);

}
