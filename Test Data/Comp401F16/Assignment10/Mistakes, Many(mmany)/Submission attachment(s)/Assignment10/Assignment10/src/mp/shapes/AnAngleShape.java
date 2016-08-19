package mp.shapes;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.Tags;

@Tags({"Angle"})
@StructurePattern("Bean Pattern")
@PropertyNames({"x", "y", "width", "height", "leftLine", "rightLine"})
@EditablePropertyNames({"x", "y"})

public class AnAngleShape extends ABoundedShape implements AngleShape{
	private LineShape leftLine;
	private LineShape rightLine;

	public AnAngleShape(LineShape leftLine, LineShape rightLine){
		this.leftLine = leftLine;
		this.rightLine = rightLine;
	}

	private void syncShapes(){
		this.leftLine.setX(x);
		this.rightLine.setX(x);
		this.leftLine.setY(y);
		this.rightLine.setY(y);
	}

	@Override
	public void setX(int newVal) {
		super.setX(newVal);
		syncShapes();
	}

	@Override
	public void setY(int newVal) {
		super.setY(newVal);
		syncShapes();
	}

	@Override
	public LineShape getLeftLine() {
		return leftLine;
	}

	@Override
	public LineShape getRightLine() {
		return rightLine;
	}

	@Tags({"move"})
	@Override
	public void move(int xOffset, int yOffset) {
		this.x += xOffset;
		this.y += yOffset;
		syncShapes();
	}
}
