package mp;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.models.PropertyListenerRegisterer;

@StructurePattern(StructurePatternNames.IMAGE_PATTERN)
public interface Image extends LocatableInter, PropertyListenerRegisterer {

	/*public int getX(); 
	public void setX(int newX);
	public int getY(); 
	public void setY(int newY); 
	*/
	public String getImageFileName(); 
	public void setImageFileName(String newVal); 
	public int listenerCount();
	
}
