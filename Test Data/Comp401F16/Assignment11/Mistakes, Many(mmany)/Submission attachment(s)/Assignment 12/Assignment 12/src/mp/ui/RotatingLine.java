package mp.ui;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@StructurePattern(StructurePatternNames.LINE_PATTERN)
@Tags("RotatingLine")
@PropertyNames({"X", "Y", "Location", "Height", "Width", "Radius", "Angle", "AdjLocation"}) 
@EditablePropertyNames({"X", "Y", "Location", "Height", "Width", "Radius", "Angle", "AdjLocation"})

public interface RotatingLine extends Shape {

	public double getRadius();
	public void setRadius(double newRadius);
	public double getAngle();
	public void setAngle(double newAngle);
	public void rotate(int units);

	
}
