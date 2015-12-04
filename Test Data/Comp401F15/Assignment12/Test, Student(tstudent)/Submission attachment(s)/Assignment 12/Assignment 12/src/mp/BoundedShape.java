package mp;

import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
@PropertyNames({"X", "Y","Width","Height"})
@EditablePropertyNames({"X", "Y","Width","Height"})
@Tags({"BoundedShape"})

public interface BoundedShape extends Locatable{
	public int getWidth();
	public int getHeight();
	public void setWidth(int newWidth);
	public void setHeight(int newHeight);
}
