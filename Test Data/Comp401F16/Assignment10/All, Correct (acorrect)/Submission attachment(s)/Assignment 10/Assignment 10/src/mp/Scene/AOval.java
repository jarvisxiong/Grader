package mp.Scene;

import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@PropertyNames({"X","Y","Width","Height"})
@StructurePattern(StructurePatternNames.OVAL_PATTERN)
public class AOval extends ABoundedShape implements BoundedShape{
	
	public AOval(int iniX, int iniY, int iniW, int iniH){
		x=iniX;
		y=iniY;
		width= iniW;
		height=iniH;
	}
}
