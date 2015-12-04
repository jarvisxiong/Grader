package mp;

import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@PropertyNames({"X","Y","Height","Width"})
@StructurePattern(StructurePatternNames.OVAL_PATTERN)

public class AnOvalShape extends ABoundedShape implements OvalShape{
	public AnOvalShape () {};
	public AnOvalShape (int initX, int initY, int initWidth, int initHeight) {
		x = initX; 
		y = initY;
		width = initWidth;
		height = initHeight;	
	}
}
