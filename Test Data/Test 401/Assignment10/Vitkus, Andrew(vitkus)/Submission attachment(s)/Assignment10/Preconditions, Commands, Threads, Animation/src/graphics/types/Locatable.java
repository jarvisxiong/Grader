package graphics.types;

import util.annotations.Tags;

@Tags({ "Locatable" })
public class Locatable implements ILocatable {
	int x, y;

	public Locatable(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

}
