package grail.scene;

import grail.AngleInterface;
import grail.Line;
import grail.BoundedShape;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.annotations.PropertyNames;

@Tags({ "Angle" })
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({ "leftLine", "rightLine" })
public class AVShape implements AngleInterface {
	final int angleRadius = 60;
	final int leftAngle = 45;
	final int rightAngle = 30;
	Line leftLine = new ALine(angleRadius, leftAngle);
	Line rightLine = new ALine(angleRadius, rightAngle);

	public Line getLeftLine() {
		return leftLine;
	}

	public Line getRightLine() {
		return rightLine;
	}

	@Tags({"move"})
	public void moveAngle(int xOffset, int yOffset) {
		int newX = leftLine.getX()+xOffset;
		int newY = leftLine.getY()+yOffset;
		int newXRight = rightLine.getX()+xOffset;
		int newYRight = rightLine.getY()+yOffset;
		leftLine.setX(newX);
		leftLine.setY(newY);
		rightLine.setY(newYRight);
		rightLine.setX(newXRight);
	}

	public AVShape() {

	}

	public AVShape(int x, int y, double aRadius, double anAngle) {

		leftLine.setX(x);
		leftLine.setY(y);
		rightLine.setX(x);
		rightLine.setY(y);
		leftLine.setAngle(-anAngle);
		leftLine.setRadius(aRadius);
		rightLine.setAngle(anAngle);
		rightLine.setRadius(aRadius);

	}

}
