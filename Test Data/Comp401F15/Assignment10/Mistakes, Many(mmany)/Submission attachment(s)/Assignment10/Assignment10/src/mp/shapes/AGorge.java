package mp.shapes;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;

@StructurePattern("Bean Pattern")
@PropertyNames({"x", "y", "width", "height", "leftLine", "rightLine", "bridgeTop", "bridgeBottom"})
@EditablePropertyNames({"x", "y"})
public class AGorge extends ABoundedShape implements Gorge{
	
	private LineShape leftLine;
	private LineShape rightLine;
	private LineShape bridgeTop;
	private LineShape bridgeBottom;
	
	//COnstants
	private final int initialX = 300;
	private final int space = 20;
	
	public AGorge(int x, int y){
		this.x = x;
		this.y = y;
		
		leftLine = new ALineShape(initialX, 0);
		rightLine = new ALineShape(initialX, 0);
		bridgeTop = new ALineShape(initialX/2, Math.PI/2);
		bridgeBottom = new ALineShape(initialX/2, Math.PI/2);
		
		syncShapes();
	}
	
	@Override
	public void syncShapes(){
		leftLine.setX(x); leftLine.setY(y);
		rightLine.setX(x+initialX/2); rightLine.setY(y);
		bridgeTop.setX(x); bridgeTop.setY(y);
		bridgeBottom.setX(x); bridgeBottom.setY(y+space);
	}
	
	@Override
	public void move(int xOffset, int yOffset){
		this.x += xOffset;
		this.y += yOffset;
		syncShapes();
	}

	@Override
	public LineShape getLeftLine() {
		return leftLine;
	}

	@Override
	public LineShape getRightLine() {
		return rightLine;
	}

	@Override
	public LineShape getBridgeTop() {
		return bridgeTop;
	}

	@Override
	public LineShape getBridgeBottom() {
		return bridgeBottom;
	}
	
}
