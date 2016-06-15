package grader.trace.compilation;

import grader.trace.file.FileInfo;

public class SourceTextCompiledInMemory extends FileInfo {
	
	byte[] bytes;

	public SourceTextCompiledInMemory(String aMessage, String aFileName, byte[] aBytes,
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

	public static SourceTextCompiledInMemory newCase(String aFileName, byte[] aBytes,
			Object aFinder) {
		String aMessage =  "Class  file compiled in memory: " + aFileName;
		SourceTextCompiledInMemory retVal = new SourceTextCompiledInMemory(aMessage, aFileName, aBytes, aFinder);
		retVal.announce();
		return retVal;
	}

}
