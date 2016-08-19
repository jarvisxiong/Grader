package mp.ui;

import java.beans.PropertyChangeEvent;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Visible;

@StructurePattern(StructurePatternNames.STRING_PATTERN)
@PropertyNames({"X", "Y", "Location", "Text"}) 
@EditablePropertyNames({"X", "Y", "Location", "Text"})


public class ALocatableString extends AnAdvLocatable implements LocatableString {
	
	private String text;
	protected AdvLocatable location, adjLocation, relativeLocation;
	
	public ALocatableString() {}
	public ALocatableString(AdvLocatable initLocation){
		setLocation(initLocation);
		text = "";
	}

	@Override
	@Visible(false)
	public AdvLocatable getRelativeLocation(int relX, int relY){
		relativeLocation.setX(location.getX()+relX);
		relativeLocation.setY(location.getY()+relY);
		return relativeLocation;
	}
	@Override
	@Visible(false)
	public AdvLocatable getRelativeLocation(double radius, double angle) {
		relativeLocation.setX(location.getX() + (int) (radius*Math.cos(angle)));
		relativeLocation.setY(location.getY() + (int) (radius*Math.sin(angle)));
		return relativeLocation;
	}
	@Override
	@Visible(false)
	public AdvLocatable getLocation() {
		location.setX(this.getX());
		location.setY(this.getY());
		return location;
	}
	public String getText() {
		return text;
	}
	public void setText(String newText) {
		String oldVal = getText();
		text = newText;
		propertyListenerSupport.notifyAllListeners(
				new PropertyChangeEvent(this, "Text", oldVal, newText));
	}

}
