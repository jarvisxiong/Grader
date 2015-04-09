package grader.permissions;

import java.io.File;

import framework.project.Project;

public interface PermissionsGenerator {
	File permissionFile(Project aProject, Object[] aPermissions);

}
