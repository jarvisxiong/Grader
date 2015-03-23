package im;

import util.misc.Common;

public enum OperationName {
	ADD,
	DELETE,
	REPLACE;
	
	public static OperationName fromString(String aString) {
		return (OperationName) Common.fromString(OperationName.class, aString);
		
	}
	

}
