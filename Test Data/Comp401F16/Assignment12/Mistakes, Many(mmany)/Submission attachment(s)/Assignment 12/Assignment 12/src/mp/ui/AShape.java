package mp.ui;

import java.beans.PropertyChangeEvent;

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

public abstract class AShape extends AnAdvLocatable implements Shape {
	
	private int height, width;
	protected AdvLocatable location, adjLocation, oldAdjLocation;
	
	public AShape() {
		location = new AnAdvLocatable();
		adjLocation = new AnAdvLocatable();
	}
	public AShape(int theHeight, int theWidth) {
		location = new AnAdvLocatable();
		adjLocation = new AnAdvLocatable();
		oldAdjLocation = new AnAdvLocatable();
		height = theHeight;
		width = theWidth;
		adjLocation.setX(width);
		adjLocation.setY(height);
	}
	@Override
	@Visible(false)
	public AdvLocatable getLocation() {
		location.setX(this.getX());
		location.setY(this.getY());
		return location;
	}
	@Visible(false)
	public int getHeight() {
		return height;
	}
	public void setHeight(int newHeight) {
		int oldVal = getHeight();
		height = newHeight;
		adjLocation.setLocation(location.getRelativeLocation(width, height));
		propertyListenerSupport.notifyAllListeners(
				new PropertyChangeEvent(this, "Height", oldVal, newHeight));		
	}
	@Visible(false)
	public int getWidth() {
		return width;
	}
	public void setWidth(int newWidth) {
		int oldVal = getWidth();
		width = newWidth;
		adjLocation.setLocation(location.getRelativeLocation(width, height));
		propertyListenerSupport.notifyAllListeners(
				new PropertyChangeEvent(this, "Width", oldVal, newWidth));
	}
	@Visible(false)
	public AdvLocatable getAdjLocation() {
		return adjLocation;
	}
	@Visible(false)
	public void setAdjLocation(AdvLocatable newAdjLocation) {
		oldAdjLocation = adjLocation;
		int oldWidth = getWidth();
		int oldHeight = getHeight();
		adjLocation = newAdjLocation;
//		setLocation(adjLocation.getRelativeLocation(-width, -height));
		setWidth(oldWidth+(adjLocation.getX()-oldAdjLocation.getX()));
		setHeight(oldHeight+(adjLocation.getY()-oldAdjLocation.getY()));
	}

}
