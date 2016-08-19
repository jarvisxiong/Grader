package grail;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
@Tags({"BoundedShape"})
public interface BoundedShape extends Locatable {
public int getHeight();
public int getWidth();
}
