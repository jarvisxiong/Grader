package grader.trace.source;

import grader.trace.file.FileInfo;

public class SourceFileInfo extends FileInfo {
	
	String text;
	
	public SourceFileInfo(String aMessage, String aFileName, String aText, Object aFinder) {
		super(aMessage, aFileName, aFinder);
		text = aText;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	

}
