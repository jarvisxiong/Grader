package grail.shape;
import util.annotations.Explanation;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.models.PropertyListenerRegisterer;
@StructurePattern(StructurePatternNames.POINT_PATTERN)
@Explanation("Location in Java coordinate System.")
@Tags({"Locatable"})
public interface Point extends PropertyListenerRegisterer{
	public int getX(); 
	public int getY(); 
	public void setX(int newX);
	public void setY(int newY);
	public int listenerCount();

}
