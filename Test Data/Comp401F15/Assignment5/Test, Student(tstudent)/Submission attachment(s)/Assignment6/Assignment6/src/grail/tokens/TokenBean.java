package grail.tokens;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public interface TokenBean {
	public String getInput();
	public void setInput(String newInput);
}
