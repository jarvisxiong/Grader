package mp.shapes;

import util.annotations.StructurePattern;
import util.annotations.Tags;
import util.models.PropertyListenerRegisterer;

@Tags({"Locatable"})
@StructurePattern("Bean Pattern")
public interface Locatable extends PropertyListenerRegisterer{
	public int getX();
	public int getY();
	public void setX(int x);
	public void setY(int y);
}
