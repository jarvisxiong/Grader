package mp.Scene;

import util.annotations.Tags;

@Tags({"BoundedShape"})
public interface BoundedShape extends Locatable {
	public int getWidth();
	public void setWidth(int newVal);
	public int getHeight();
	public void setHeight(int newVal);
}
