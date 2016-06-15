package grader.permissions;


public interface Permissible<PermissionType> {
	PermissionType[] getPermissions();

}
