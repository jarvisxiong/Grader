package grader.permissions.java;

import grader.permissions.Permissible;

public class DefaultJavaPermissible  implements Permissible{
	static final protected Permissible defaultPermissible = new LocalReadWritePermissible();
	@Override
	public Object[] getPermissions() {
		return defaultPermissible.getPermissions();
	}
}
