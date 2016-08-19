package mp.shapes;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.IMAGE_PATTERN)
public interface ImageShape extends BoundedShape{
	public String getImageFileName();
	public void setImageFileName(String imageFileName);
}
