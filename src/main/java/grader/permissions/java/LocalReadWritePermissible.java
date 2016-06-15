package grader.permissions.java;

import grader.permissions.Permissible;

import java.io.FilePermission;

public class LocalReadWritePermissible implements Permissible {
	protected final String directoryName;
	public static final String WORKING_DIRECTORY_DESCENDENTS = "./-";
	public static final String WORKING_DIRECTORY_ONLY = ".";
	public static final String WORKING_DIRECTORY_CHILDREN = "./*";
	
	public LocalReadWritePermissible(String aDirectoryName) {
		directoryName = aDirectoryName;
		
	}
	public LocalReadWritePermissible() {
		this (WORKING_DIRECTORY_DESCENDENTS );
		
	}

	@Override
	public Object[] getPermissions() {
		return new Object[] {new  FilePermission(".", "read,write")};
	}

}
