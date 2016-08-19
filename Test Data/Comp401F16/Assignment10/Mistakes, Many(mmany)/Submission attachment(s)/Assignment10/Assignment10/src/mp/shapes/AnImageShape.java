package mp.shapes;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.IMAGE_PATTERN)
public class AnImageShape extends ABoundedShape implements ImageShape{
	private String imageFileName;
	
	public AnImageShape(String imageFileName){
		setImageFileName(imageFileName);
		Icon icon = new ImageIcon(imageFileName);
		this.width = icon.getIconWidth();
		this.height = icon.getIconHeight();
	}

	@Override
	public String getImageFileName() {
		return imageFileName;
	}

	@Override
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	
}
