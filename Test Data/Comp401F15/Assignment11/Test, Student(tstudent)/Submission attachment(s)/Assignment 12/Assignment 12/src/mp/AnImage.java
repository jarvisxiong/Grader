package mp;

import java.beans.PropertyChangeEvent;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.IMAGE_PATTERN)

public class AnImage extends ABoundedShape implements ImageShape{
	 String imageFileName;
     public AnImage () {};
     public AnImage (String initImageFileName, int initX, int initY, int initHeight, int initWeight) {	
    	imageFileName = initImageFileName;
    	x = initX;
    	y = initY;    
    	height = initHeight;
    	width = initWeight;
     }          
    public String getImageFileName() {return imageFileName;}  
    public void setImageFileName(String newVal) {
    	String oldVal = getImageFileName();
    	imageFileName = newVal ;  
    	PropertyChangeEvent newEvent = new PropertyChangeEvent(this, "ImageFileName", oldVal, newVal);
    	alertListeners(newEvent);
	}
}

