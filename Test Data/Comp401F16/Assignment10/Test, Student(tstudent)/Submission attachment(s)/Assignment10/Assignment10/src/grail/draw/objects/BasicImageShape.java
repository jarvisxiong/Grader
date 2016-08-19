package grail.draw.objects;

import java.beans.PropertyChangeEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import grail.draw.interfaces.ImageShape;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.IMAGE_PATTERN)
@PropertyNames({"Height", "Width", "X", "Y","ImageFileName"})
@EditablePropertyNames({"Height", "Width", "X", "Y", "ImageFileName"})
public class BasicImageShape extends BasicMutableShape implements ImageShape {
	private String imageFileName;
	private Icon image;
	
	public BasicImageShape(String imageFileName) {
		super();
		this.imageFileName = imageFileName;
		try {
			image = new ImageIcon(imageFileName);
			this.height = image.getIconHeight();
			this.width = image.getIconWidth();
		} catch (Exception e) {
			System.out.println("The image was not found!!");
		}

	}


	
	public String getImageFileName() {
		return imageFileName;
	}

	
	public void setImageFileName(String newName) {
		String oldFileName = this.imageFileName;
		this.imageFileName = newName;
		if (this.propertyListenerSupport != null) {
			this.propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent (this,"ImageFileName", oldFileName, this.imageFileName ));
		}
		
	}

}
