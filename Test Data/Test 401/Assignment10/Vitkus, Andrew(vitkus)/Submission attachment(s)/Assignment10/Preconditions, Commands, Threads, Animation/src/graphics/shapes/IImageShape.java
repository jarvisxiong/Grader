package graphics.shapes;

import graphics.types.IMovableResizableShape;
import util.annotations.Tags;

import util.models.PropertyListenerRegisterer;

@Tags({ "Image Shape" })
public interface IImageShape extends IMovableResizableShape, PropertyListenerRegisterer {
	public String getImageFileName();

	public void setImageFileName(String newFileName);
}
