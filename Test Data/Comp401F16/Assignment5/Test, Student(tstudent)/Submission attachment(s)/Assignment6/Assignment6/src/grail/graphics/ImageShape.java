package grail.graphics;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.IMAGE_PATTERN)
public interface ImageShape extends Locatable {
    public String getImageFileName() ;  
}
