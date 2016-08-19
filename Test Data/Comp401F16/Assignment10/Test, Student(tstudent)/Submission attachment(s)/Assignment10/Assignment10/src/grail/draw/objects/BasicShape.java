package grail.draw.objects;

import grail.draw.interfaces.Shape;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;

@PropertyNames({"X", "Y", "Height", "Width"})
@EditablePropertyNames({"X", "Y"})
@Tags({"BoundedShape"})
public class BasicShape extends BasicLocator implements Shape{
	protected int height, width;
	
	public BasicShape() {
		super();
	}
	
	public BasicShape(int x, int y) {
		super(x,y);
	}


	
	public int getHeight() {
		return height;
	}

	
	public int getWidth() {
		return width;
	}

}
