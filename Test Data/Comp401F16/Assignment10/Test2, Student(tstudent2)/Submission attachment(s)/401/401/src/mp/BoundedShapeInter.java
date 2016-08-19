package mp;

import util.annotations.Tags;

@Tags({"BoundedShape"})
public interface BoundedShapeInter extends LocatableInter {
	public int getWidth();
	public int getHeight();

	public void setWidth(int newWidth);
	public void setHeight(int newHeight);
}
