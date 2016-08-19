package grail.draw.interfaces;

import util.annotations.Tags;

@Tags({"BoundedShape"})
public interface Shape extends Locator{
	public int getHeight();
	public int getWidth();
}
