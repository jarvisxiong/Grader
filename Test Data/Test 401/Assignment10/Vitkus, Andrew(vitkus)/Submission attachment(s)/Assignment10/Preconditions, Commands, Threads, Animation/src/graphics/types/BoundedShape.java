package graphics.types;

import util.annotations.Tags;

@Tags({ "Bounded Shape" })
public class BoundedShape extends Locatable implements IBoundedShape {
	int width, height;

	public BoundedShape(int x, int y, int width, int height) {
		super(x, y);
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

}
