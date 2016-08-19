package mp.ui;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Visible;

@StructurePattern(StructurePatternNames.POINT_PATTERN)
@PropertyNames({"X", "Y", "Location"}) 
@EditablePropertyNames({"X", "Y", "Location"})

public class AnAdvLocatable extends ALocatable implements AdvLocatable{
	private AdvLocatable advLocation, relativeLocation;
	
	public AnAdvLocatable() {
	}
	public AnAdvLocatable(int theX, int theY) {
		super.setX(theX);
		super.setY(theY);
	}
	@Visible(false)
	public AdvLocatable getRelativeLocation(int relX, int relY){
		relativeLocation = new AnAdvLocatable();
		relativeLocation.setX(getX()+relX);
		relativeLocation.setY(getY()+relY);
		return relativeLocation;
	}
	@Visible(false)
	public AdvLocatable getRelativeLocation(double radius, double angle) {
		relativeLocation = new AnAdvLocatable();
		relativeLocation.setX(getX() + (int) (radius*Math.cos(angle)));
		relativeLocation.setY(getY() + (int) (radius*Math.sin(angle)));
		return relativeLocation;
	}
	@Visible(false)
	public AdvLocatable getLocation() {
		advLocation = new AnAdvLocatable(getX(),getY());
		return advLocation;
	}
	public void setLocation(AdvLocatable newLocation) {
		super.setX(newLocation.getX());
		super.setY(newLocation.getY());
	}
	public void setLocation(int newX, int newY) {
		super.setX(newX);
		super.setY(newY);
	}
}
