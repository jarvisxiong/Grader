package grail.scene;

import grail.BoundedShape;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"BoundedShape"})
@StructurePattern(StructurePatternNames.LINE_PATTERN)
@PropertyNames({ "x", "y","width","height"})
@EditablePropertyNames({ "x", "y" })
public class ABoundedShape extends ALocatable implements BoundedShape {
int width, height;

public int getHeight() {
	return height;
}

public int getWidth() {
	return width;
}
}
