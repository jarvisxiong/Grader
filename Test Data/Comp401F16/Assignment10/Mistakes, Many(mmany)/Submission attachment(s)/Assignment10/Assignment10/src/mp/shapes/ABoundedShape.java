package mp.shapes;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.Tags;


@Tags({"BoundedShape"})
@StructurePattern("Bean Pattern")
@PropertyNames({"x", "y", "width", "height"})
@EditablePropertyNames({"x", "y"})
public class ABoundedShape extends ALocatable implements BoundedShape{
	protected int width;
	protected int height;
	
	@Override
	public int getWidth() {
		return width;
	}
	@Override
	public int getHeight() {
		return height;
	}
	
}
