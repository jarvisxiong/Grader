package mp.shapes;

import util.annotations.StructurePattern;
import util.annotations.Tags;
@Tags({"Angle"})
@StructurePattern("Bean Pattern")
public interface AngleShape extends BoundedShape{
	public LineShape getLeftLine();
	public LineShape getRightLine();
	@Tags({"move"})
	public void move(int xOffset, int yOffset);
}
