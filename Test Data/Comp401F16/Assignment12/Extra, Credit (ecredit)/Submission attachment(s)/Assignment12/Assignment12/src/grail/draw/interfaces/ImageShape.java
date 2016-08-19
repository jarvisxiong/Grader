package grail.draw.interfaces;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.IMAGE_PATTERN)
public interface ImageShape extends MutableShape {
	public String getImageFileName();  
    public void setImageFileName(String newName);
}
