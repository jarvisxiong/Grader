package mp.shapes;

import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.Tags;
@Tags({"Avatar"})
@StructurePattern("Bean Pattern")
public interface Avatar {
	public StringShape getText();
	
	public ImageShape getHead();
	
	public AngleShape getArms();
	
	public AngleShape getLegs();
	
	public LineShape getTorso();
	
	public void move(int xOffset, int yOffset);
	public void scale(double scale);
	public void syncShapes();
}
