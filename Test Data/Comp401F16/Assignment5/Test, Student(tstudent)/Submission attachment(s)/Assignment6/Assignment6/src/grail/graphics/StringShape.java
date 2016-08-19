package grail.graphics;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.STRING_PATTERN)
public interface StringShape extends Locatable {
	public void setString(String newText);
	public String getString();
	public String getText();
}
