package graphics.shapes;

import java.beans.PropertyChangeListener;

import util.APropertyChangeListenerManager;
import util.annotations.EditablePropertyNames;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@PropertyNames({ "ImageFileName", "X", "Y", "Width", "Height" })
@EditablePropertyNames({ "ImageFileName", "X", "Y", "Width", "Height" })
@StructurePattern(StructurePatternNames.IMAGE_PATTERN)
@Tags({ "Image Shape" })
public class AnImageShape implements IImageShape {
	private int x, y, height, width;
	private String fileName;

	private final APropertyChangeListenerManager pclm = new APropertyChangeListenerManager(this);

	public AnImageShape(String fileName, int x, int y, int height, int width) {
		setImageFileName(fileName);
		setX(x);
		setY(y);
		setHeight(height);
		setWidth(width);
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int newX) {
		pclm.firePropertyChange("x", x, newX);
		x = newX;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int newY) {
		pclm.firePropertyChange("y", y, newY);
		y = newY;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public void setHeight(int newHeight) {
		pclm.firePropertyChange("height", height, newHeight);
		height = newHeight;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public void setWidth(int newWidth) {
		pclm.firePropertyChange("width", width, newWidth);
		width = newWidth;
	}

	@Override
	public String getImageFileName() {
		return fileName;
	}

	@Override
	public void setImageFileName(String newFileName) {
		pclm.firePropertyChange("imagefilename", fileName, newFileName);
		fileName = newFileName;
	}

	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	@Override
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		pclm.addPropertyChangeListener(pcl);
	}
}
