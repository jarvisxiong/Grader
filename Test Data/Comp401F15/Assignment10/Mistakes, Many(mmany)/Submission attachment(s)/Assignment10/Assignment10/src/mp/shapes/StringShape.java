package mp.shapes;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.STRING_PATTERN)
public interface StringShape extends BoundedShape{
	public String getText();
	public void setText(String newText);
}
