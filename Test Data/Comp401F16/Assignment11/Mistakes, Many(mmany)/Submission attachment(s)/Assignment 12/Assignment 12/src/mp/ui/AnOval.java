package mp.ui;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.OVAL_PATTERN)
@PropertyNames({"X", "Y", "Location", "Height", "Width", "AdjLocation"}) 
@EditablePropertyNames({"X", "Y", "Location", "Height", "Width", "AdjLocation"})

public class AnOval extends AShape implements Shape {
	
	AdvLocatable location;
	int width, height;
	
	public AnOval(AdvLocatable initLocation, int initWidth, int initHeight){
		super(initWidth, initHeight);
		setLocation(initLocation);
	}

}
