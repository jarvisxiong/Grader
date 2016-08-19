package grail.CharacterAnimation;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.OVAL_PATTERN) 
@PropertyNames({"width","height","location","x","y"})
@EditablePropertyNames({"width","height","location","x","y"})
public class Circle extends BoundedShape implements CircleInterface {

	public Circle (PointInterface initLocation, int initWidth, int initHeight) {
		location = initLocation;
		width = initWidth;
		height = initHeight;   
	}
	public Circle (int newX, int newY, int initWidth, int initHeight){
		location = new Point(newX,newY);  	
		width = initWidth;
		height = initHeight;
		x = newX;
		y = newY;
	}
	
	public void rotate(int rotation) {
		//DOES NOTHING		
	}
}
