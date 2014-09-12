package graphics.types;

import util.annotations.Tags;

@Tags({ "Moveable" })
public class Movable extends Locatable implements IMovable {

	public Movable(int x, int y) {
		super(x, y);
	}

	@Override
	public void setX(int newX) {
		x = newX;
	}

	@Override
	public void setY(int newY) {
		y = newY;
	}

}
