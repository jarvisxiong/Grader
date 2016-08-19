package grail.draw.objects;

import grail.draw.interfaces.Rectangle;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@StructurePattern(StructurePatternNames.RECTANGLE_PATTERN)
@PropertyNames({"X", "Y", "Height", "Width"})
@EditablePropertyNames({"X", "Y", "Height", "Width"})
@Tags({"Locatable"})
public class BasicRectangle extends BasicColorMutableShape implements Rectangle{

	public BasicRectangle() {
		super();
		final int defaultWidthAndHeight = 100;
		this.height = defaultWidthAndHeight;
		this.width = defaultWidthAndHeight;
	}
	
	public BasicRectangle(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		
	}


}
