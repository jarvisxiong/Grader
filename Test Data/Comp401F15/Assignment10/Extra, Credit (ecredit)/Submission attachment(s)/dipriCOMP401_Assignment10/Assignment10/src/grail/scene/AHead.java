package grail.scene;
import grail.Head;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.annotations.Visible;

@Tags({"Head"})
@PropertyNames({ "x", "y","width","height","imageFileName"})
@EditablePropertyNames({ "x", "y","imageFileName" })
@StructurePattern(StructurePatternNames.IMAGE_PATTERN)
public class AHead extends ABoundedShape implements Head {
String imageFileName;
public AHead(String aString, int intX, int intY, int aWidth, int aHeight){
	imageFileName = aString;
	width = aWidth;
	height = aHeight;
	x = intX;
	y = intY;
}
public AHead(){
	
}
public String getImageFileName(){
	return imageFileName;
}
public void setImageFileName(String newString){
	imageFileName = newString;
}
}
