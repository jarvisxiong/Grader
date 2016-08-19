package grail.draw.objects;

import java.awt.Color;

import grail.draw.interfaces.OvalShape;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;


@StructurePattern(StructurePatternNames.OVAL_PATTERN)
@PropertyNames({"X", "Y", "Width", "Height", "Color", "Filled"})
@EditablePropertyNames({"X", "Y", "Width", "Height", "Color", "Filled"})
public class BasicOvalShape extends BasicColorMutableShape implements OvalShape{
	
	public BasicOvalShape() {
		super();
		final int defaultValue = 100;
		this.x = defaultValue;
		this.y = defaultValue;
		this.height = defaultValue;
		this.width = defaultValue;
		this.color = Color.BLACK;
		this.filled = true;
	}

}
