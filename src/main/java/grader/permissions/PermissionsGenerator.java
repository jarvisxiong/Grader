package grader.permissions;

import java.io.File;

import grader.basics.project.Project;

public interface PermissionsGenerator {
	File permissionFile(Project aProject, Object[] aPermissions);

}
