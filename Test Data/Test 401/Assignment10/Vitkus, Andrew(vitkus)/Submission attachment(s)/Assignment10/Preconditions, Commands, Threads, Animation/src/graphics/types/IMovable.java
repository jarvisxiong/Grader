package graphics.types;

import util.annotations.Tags;

@Tags({ "Moveable" })
public interface IMovable extends ILocatable {
	public void setX(int newX);

	public void setY(int newY);
}
