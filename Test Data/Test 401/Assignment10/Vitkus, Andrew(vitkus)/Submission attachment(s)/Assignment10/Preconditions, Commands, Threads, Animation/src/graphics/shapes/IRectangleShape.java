package graphics.shapes;

import graphics.types.IColorable;
import graphics.types.IMovableResizableShape;
import util.annotations.Tags;
import util.models.PropertyListenerRegisterer;

@Tags({ "Rectangle Shape" })
public interface IRectangleShape extends IMovableResizableShape, IColorable, PropertyListenerRegisterer {
}
