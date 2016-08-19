package mp.ui;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.annotations.Visible;

@Tags("BoundedShape")
@StructurePattern(StructurePatternNames.AWT_SHAPE_PATTERN)
@PropertyNames({"X", "Y", "Location", "Height", "Width", "AdjLocation"}) 
@EditablePropertyNames({"X", "Y", "Location", "Height", "Width", "AdjLocation"})

public interface Shape extends AdvLocatable {
	public int getHeight();
	public void setHeight(int newHeight);
	public int getWidth();
	public void setWidth(int newWidth);
	public AdvLocatable getAdjLocation();
	public void setAdjLocation(AdvLocatable newAdjLocation);
}
