package graphics.avatar;

import graphics.shapes.IRotatingLine;
import graphics.shapes.ARotatingLine;
import graphics.types.Movable;
import util.annotations.EditablePropertyNames;
import util.annotations.IsCompositeShape;
import util.annotations.PropertyNames;
import util.annotations.Tags;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@Tags({ "Angle" })
@PropertyNames({ "LeftLine", "RightLine", "X", "Y" })
@EditablePropertyNames({ "X", "Y" })
@IsCompositeShape(true)
public class AngleShape extends Movable implements IAngleShape {
	private IRotatingLine rightLine, leftLine;

	public AngleShape(int x, int y, double rightAngle, double rightLength,
			double leftAngle, double leftLength) {
		super(x, y);
		rightLine = new ARotatingLine(x, y, rightAngle, rightLength);
		leftLine = new ARotatingLine(x, y, leftAngle, leftLength);
	}

	@Override
	public IRotatingLine getLeftLine() {
		return leftLine;
	}

	@Override
	public IRotatingLine getRightLine() {
		return rightLine;
	}

	@Override
	public void setX(int newX) {
		super.setX(newX);
		rightLine.setX(newX);
		leftLine.setX(newX);
	}

	@Override
	public void setY(int newY) {
		super.setY(newY);
		rightLine.setY(newY);
		leftLine.setY(newY);
	}

}
