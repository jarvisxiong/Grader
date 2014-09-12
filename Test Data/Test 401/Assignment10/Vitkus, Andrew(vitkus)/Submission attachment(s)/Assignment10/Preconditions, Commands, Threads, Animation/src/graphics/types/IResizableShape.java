package graphics.types;

import util.annotations.Tags;

@Tags({ "Resizable Shape" })
public interface IResizableShape extends IBoundedShape {
	public void setHeight(int newHeight);

	public void setWidth(int newWidth);
}
