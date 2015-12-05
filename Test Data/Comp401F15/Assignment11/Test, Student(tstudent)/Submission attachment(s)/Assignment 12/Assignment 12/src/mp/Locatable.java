package mp;

import util.models.PropertyListenerRegisterer;
import util.annotations.Tags;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
@PropertyNames({"X", "Y"})
@EditablePropertyNames({"X", "Y"})
@Tags({"Locatable"})

public interface Locatable extends PropertyListenerRegisterer{
	public int getX();
	public int getY();
	public void setX(int newX);
	public void setY(int newY);
}
