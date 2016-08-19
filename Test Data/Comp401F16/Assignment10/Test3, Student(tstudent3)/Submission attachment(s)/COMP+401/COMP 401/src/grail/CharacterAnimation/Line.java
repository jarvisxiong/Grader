package grail.CharacterAnimation;


import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@PropertyNames({"width","height","x","y"})
@EditablePropertyNames({"width","height","x","y"})
@StructurePattern(StructurePatternNames.LINE_PATTERN) 
public class Line extends BoundedShape implements LineInterface{

    public Line (PointInterface initLocation, int initWidth, int initHeight) {
    	location = initLocation;
        width = initWidth;
        height = initHeight;
        x = initLocation.getX();
        y = initLocation.getY();
    }
    public Line (int newX, int newY, int initWidth, int initHeight){
    	location = new Point(newX,newY);    	
        width = initWidth;
        height = initHeight; 
        x = newX;
        y = newY;
        
    }
    
   

}
