package mp.ui;

import util.annotations.EditablePropertyNames;
import util.annotations.IsCompositeShape;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.annotations.Visible;

@Tags("Angle")
@IsCompositeShape(true)
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"X", "Y", "Location", "LeftLine", "RightLine", "Angle"}) 
@EditablePropertyNames({"X", "Y", "Location", "Angle"})

public class AVShape extends AnAdvLocatable implements VShape {
	
	RotatingLine leftLine, rightLine;
	double angle, angleR, angleL;
	int length;
	
	public AVShape() {}
	public AVShape(AdvLocatable initLocation, int initLength, double initAngle){
		super.setLocation(initLocation);
		angleR = ((Math.PI)/2)-(initAngle/2);
		angleL = ((Math.PI)/2)+(initAngle/2);
		length = initLength;
		leftLine = new ARotatingLine(getLocation(), length, angleL);
		rightLine = new ARotatingLine(getLocation(), length, angleR);
	}
	@Visible(true)
	public RotatingLine getLeftLine() {
		return leftLine;
	}
	@Visible(true)
	public RotatingLine getRightLine() {
		return rightLine;
	}
	@Override
	@Visible(false)
	public void setLocation(AdvLocatable newLocation) {
		super.setLocation(newLocation);
		leftLine.setLocation(getLocation());
		rightLine.setLocation(getLocation());
	}
	@Visible(false)
	public double getAngle() {
		return angle;
	}
	@Visible(false)
	public void setAngle(double newAngle) {
		angle = newAngle;
		leftLine.setAngle(((Math.PI)/2)+(angle/2));
		rightLine.setAngle(((Math.PI)/2)-(angle/2));
	}
	@Tags("move")
	public void moveLocation(int x, int y) {
		setX(getX()+x);
		setY(getY()+y);
	}

}
