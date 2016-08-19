package mp.ui;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.annotations.Visible;

@Tags("Angle")
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"X", "Y", "Location", "LeftLine", "RightLine", "Angle"}) 
@EditablePropertyNames({"X", "Y", "Location", "Angle"})

public interface VShape extends AdvLocatable {
	
	public RotatingLine getLeftLine();
	public RotatingLine getRightLine();
	public double getAngle();
	public void setAngle(double newAngle);
	public void moveLocation(int x, int y);

}
