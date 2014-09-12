package graphics.avatar;

import graphics.shapes.IRotatingLine;
import graphics.types.IMovable;
import util.annotations.Tags;

@Tags({ "Angle" })
public interface IAngleShape extends IMovable {
	public IRotatingLine getLeftLine();

	public IRotatingLine getRightLine();
}
