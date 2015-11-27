package grail.shape;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
//@StructurePattern("Label Pattern")
@StructurePattern(StructurePatternNames.LABEL_PATTERN)
@PropertyNames({"x","y","width","height","imageFileName"})
@EditablePropertyNames({"x","y","width","height","imageFileName"})
public class AShapeImage extends ABoundedShape implements ImageShape {
	 String imageFileName;
   //  int x, y, height, width;
     public AShapeImage (String initImageFileName, int initX, int initY, int initHeight, int initweight) {	
    	super(initX,initY,initweight,initHeight);
    	 imageFileName = initImageFileName;
     }          
//    public int getX() {return x;}
// 	public void setX(int newX) {x = newX;}
// 	public int getY() { return y; }
// 	public void setY(int newY) {y = newY;}
// 	public int getHeight() { return height; }
// 	public void setHeight(int newHeight) {height = newHeight;}
// 	public int getWidth() { return width; }
 //	public void setWidth(int newWidth) {width = newWidth;}
    public String getImageFileName() {return imageFileName;}  
    public void setImageFileName(String newVal) {imageFileName = newVal ;}     

}
