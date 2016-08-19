package mp.ui;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.POINT_PATTERN)
@PropertyNames({"X", "Y", "Location"}) 
@EditablePropertyNames({"X", "Y", "Location"})

public interface AdvLocatable extends Locatable {
	public void setLocation(AdvLocatable newLocation);
	public void setLocation(int newX, int newY);
	public AdvLocatable getLocation();
	public AdvLocatable getRelativeLocation(int relX, int relY);
	public AdvLocatable getRelativeLocation(double radius, double angle);
}