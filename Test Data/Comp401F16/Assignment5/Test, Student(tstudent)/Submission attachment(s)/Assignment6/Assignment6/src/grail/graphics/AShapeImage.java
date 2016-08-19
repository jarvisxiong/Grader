package grail.graphics;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.IMAGE_PATTERN)
public class AShapeImage extends ALocatable implements ImageShape {
	String imageFileName;
    
    public AShapeImage (String initImageFileName, int initX, int initY) {	
    	imageFileName = initImageFileName;
    	setX(initX);
    	setY(initY);
    }          

    public String getImageFileName() { return imageFileName; }  
}
