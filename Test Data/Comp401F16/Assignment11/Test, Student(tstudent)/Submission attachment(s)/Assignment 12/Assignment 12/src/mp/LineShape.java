package mp;

import util.annotations.PropertyNames;
import util.annotations.EditablePropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@PropertyNames({"X","Y","Height","Width"})
@EditablePropertyNames({"X","Y","Width","Height"})
@StructurePattern(StructurePatternNames.LINE_PATTERN)

public class LineShape extends ABoundedShape implements Line{
	public LineShape () {};
	public LineShape (int initX, int initY, int initWidth, int initHeight) {
		x = initX; 
		y = initY;
		width = initWidth;
		height = initHeight;	
	}
}
