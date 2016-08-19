package mp.ui;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.IMAGE_PATTERN)
@PropertyNames({"X", "Y", "Location", "Height", "Width", "ImageFileName", "AdjLocation"}) 
@EditablePropertyNames({"X", "Y", "Location", "Height", "Width", "ImageFileName", "AdjLocation"})

public class AHead extends AShape implements Head {

	private String imageFileName;
	private Icon image;
	
	public AHead() {}
	public AHead(AdvLocatable initLocation){
		this.setLocation(initLocation);
	}
	
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String fileName){
		imageFileName = fileName;
		image = new ImageIcon(imageFileName);
	}
	@Override
	public int getHeight(){
		int imageHeight = image.getIconHeight();
		return imageHeight;
	}
	@Override
	public int getWidth(){
		int imageWidth = image.getIconWidth();
		return imageWidth;
	}

}
