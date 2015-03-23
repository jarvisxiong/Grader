package graphics.types;

import util.annotations.Tags;

@Tags({ "Resiable Shape" })
public class ResizableShape extends BoundedShape implements IResizableShape {

	public ResizableShape(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void setHeight(int newHeight) {
		height = newHeight;
	}

	@Override
	public void setWidth(int newWidth) {
		width = newWidth;
	}

}
