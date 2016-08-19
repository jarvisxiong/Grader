package mp.Scene;

import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;


@PropertyNames({"X","Y","Width","Height","ImageFileName"})
@StructurePattern(StructurePatternNames.LABEL_PATTERN) 
public class AImageShape extends ABoundedShape implements ImageShape {
	String imageFileName;
	public AImageShape(String theName,int intX, int intY, int intW, int intH){
		x=intX;
		y=intY;
		width=intW;
		height=intH;
		imageFileName= theName;
	}
	
    public String getImageFileName() {return imageFileName;}  
    public void setImageFileName(String newVal) {imageFileName = newVal ;} 
}
