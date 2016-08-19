package main;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)


public interface ScannerBeanInterface {
	
	void setScannedString(String input);
	public String getScannedString();
	public String[] getTokens();
	public String[] getParse();
}
