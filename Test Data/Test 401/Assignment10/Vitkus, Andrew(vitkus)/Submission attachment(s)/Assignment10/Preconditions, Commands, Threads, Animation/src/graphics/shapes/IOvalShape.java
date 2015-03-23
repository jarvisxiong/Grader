package graphics.shapes;

import graphics.types.IColorable;
import graphics.types.IMovableResizableShape;
import util.annotations.Tags;
import util.models.PropertyListenerRegisterer;

@Tags({ "Oval Shape" })
public interface IOvalShape extends IMovableResizableShape, IColorable,
		PropertyListenerRegisterer {
}
