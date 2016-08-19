package mp.ui;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.IMAGE_PATTERN)
@PropertyNames({"X", "Y", "Location", "Height", "Width", "ImageFileName", "AdjLocation"}) 
@EditablePropertyNames({"X", "Y", "Location", "Height", "Width", "ImageFileName", "AdjLocation"})

public interface Head extends Shape { 
	public String getImageFileName();
	public void setImageFileName(String fileName);
}
