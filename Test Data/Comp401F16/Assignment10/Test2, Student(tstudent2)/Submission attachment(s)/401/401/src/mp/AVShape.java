package mp;

import mp.Locatable;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;

@Tags({"Angle"})
public class AVShape extends Locatable implements VShape {
	Line LeftLine, RightLine;
	
	public AVShape(int originX, int originY){
		LeftLine = new ALine(originX,originY,originY + 25, originX - 25);
		RightLine = new ALine(originX,originY,originY + 25, originX + 25);
		x = originX;
		y = originY;
	}
	
	public Line getLeftLine(){return LeftLine;}
	public Line getRightLine(){return RightLine;}
}
