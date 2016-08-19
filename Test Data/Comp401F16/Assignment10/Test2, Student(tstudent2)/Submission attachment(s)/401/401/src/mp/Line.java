package mp;

import java.beans.PropertyChangeListener;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.LINE_PATTERN)

public interface Line extends BoundedShapeInter{
	
	public double getRadius();
	public double getAngle();
	void setAngle(double add);
	void addPropertyChangeListener(PropertyChangeListener listener);
}
