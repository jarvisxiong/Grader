package mp.shapes;

import util.annotations.StructurePattern;

@StructurePattern("Bean Pattern")
public interface Gorge extends BoundedShape{
	public LineShape getLeftLine();
	public LineShape getRightLine();
	public LineShape getBridgeTop();
	public LineShape getBridgeBottom();
	
	public void syncShapes();
	public void move(int xOffset, int yOffset);
}
