package grail.CharacterAnimation;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import util.annotations.EditablePropertyNames;
import util.annotations.Explanation;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.POINT_PATTERN)
@Explanation("Uses Cartesian representation.")
@PropertyNames({"x","y"})
@EditablePropertyNames({"x","y"})
public class Point extends LocatableShape implements PointInterface {	

	
	public Point(int theX, int theY) {
		x = theX;
		y = theY;
	}

	
}
