package grader.permissions;

import java.security.Permission;

public interface Permissible<PermissionType> {
	PermissionType[] getPermissions();

}
