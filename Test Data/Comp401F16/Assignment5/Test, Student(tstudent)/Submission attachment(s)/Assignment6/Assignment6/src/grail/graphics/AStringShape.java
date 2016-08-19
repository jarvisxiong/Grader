/**
 * @author Ian Hoyt-McCullough
 * @version 9/24/15
 */

package grail.graphics;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.PropertyNames;
import util.annotations.EditablePropertyNames;

@StructurePattern(StructurePatternNames.STRING_PATTERN)
@PropertyNames({"X", "Y", "String", "Text"})
@EditablePropertyNames({"X", "Y", "String"})
public class AStringShape extends ALocatable implements StringShape {
	private String string;
	
	public AStringShape(int newX, int newY, String newText) {
		setX(newX);
		setY(newY);
		string = newText;
	}
	public void setString(String newText) { string = newText; }
	public String getString() { return string; }
	public String getText() { return string; }
}
