package grader.trace.compilation;

import grader.trace.file.FileInfo;
import grader.trace.file.SerializableFileInfo;

public class ClassFileCompiledInMemory extends FileInfo {
	
	byte[] bytes;

	public ClassFileCompiledInMemory(String aMessage, String aFileName, byte[] aBytes,
			Object aFinder) {
		super(aMessage, aFileName, aFinder);
		bytes = aBytes;
	}
	
	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public static ClassFileCompiledInMemory newCase(String aFileName, byte[] aBytes,
			Object aFinder) {
		String aMessage =  "Class  file compiled in memory: " + aFileName;
		ClassFileCompiledInMemory retVal = new ClassFileCompiledInMemory(aMessage, aFileName, aBytes, aFinder);
		retVal.announce();
		return retVal;
	}

}
