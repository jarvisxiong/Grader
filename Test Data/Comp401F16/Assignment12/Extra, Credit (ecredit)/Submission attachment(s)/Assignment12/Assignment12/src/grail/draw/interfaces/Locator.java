package grail.draw.interfaces;
import util.annotations.Tags;
import util.models.PropertyListenerRegisterer;

@Tags({"Locatable"})
public interface Locator extends PropertyListenerRegisterer {
	public int getX();
	public void setX(int newX);
	public int getY();
	public void setY(int newY);
}
