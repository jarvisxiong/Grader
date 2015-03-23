package graphics.types;

import util.annotations.Tags;

@Tags({ "Bounded Shape" })
public interface IBoundedShape extends ILocatable {
	public int getWidth();

	public int getHeight();
}
