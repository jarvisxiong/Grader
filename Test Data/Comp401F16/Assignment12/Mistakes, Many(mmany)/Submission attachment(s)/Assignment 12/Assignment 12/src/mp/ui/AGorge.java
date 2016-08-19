package mp.ui;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Visible;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"X", "Y", "Location", "LeftCliff", "RightCliff", "Bridge"}) 
@EditablePropertyNames({"X", "Y", "Location"})

public class AGorge extends AnAdvLocatable implements Gorge {
	
	RotatingLine leftCliff, rightCliff;
	AdvLocatable location, leftLocation, rightLocation;
	double cliffLength, dimAngle, bridgeLength;
	Bridge bridge;
	final int cliffBridgeOffset = -150;
	
	public AGorge(AdvLocatable initLocation, double initLength, double initAngle, double initBridgeLength){
		location = initLocation;
		super.setLocation(initLocation); //location is the lower left hand corner of the bridge
		cliffLength = initLength;
		dimAngle = initAngle;
		bridgeLength = initBridgeLength;
		leftLocation = getLocation().getRelativeLocation((double) cliffBridgeOffset, dimAngle);
		rightLocation = leftLocation.getRelativeLocation((int) bridgeLength, 0);
		leftCliff = new ARotatingLine(leftLocation, cliffLength, dimAngle);
		rightCliff = new ARotatingLine(rightLocation, cliffLength, dimAngle);
		bridge = new ABridge(getLocation(), bridgeLength, dimAngle);
	}

	public RotatingLine getLeftCliff() {
		return leftCliff;
	}
	public RotatingLine getRightCliff() {
		return rightCliff;
	}
	public Bridge getBridge(){
		return bridge;
	}
	@Override
	@Visible(false)
	public AdvLocatable getLocation() {
		location.setX(this.getX());
		location.setY(this.getY());
		return location;
	}
	@Override
	public void setLocation(AdvLocatable newLocation) {
		super.setLocation(newLocation);
		leftLocation = getLocation().getRelativeLocation((double) cliffBridgeOffset, dimAngle);
		rightLocation = leftLocation.getRelativeLocation((int) bridgeLength, 0);
		bridge.setLocation(getX(), getY());
		leftCliff.setLocation(leftLocation);
		rightCliff.setLocation(rightLocation);
	}

}
