package mp.shapes;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.OVAL_PATTERN)
public class AnAreaShape extends ABoundedShape implements AreaShape{
	
	public AnAreaShape(int x, int y, int width, int height){
		setX(x);
		setY(y);
		this.width = width;
		this.height = height;
	}

	@Override
	public void move(int xOffset, int yOffset){
		this.x += xOffset;
		this.y += yOffset;
	}
}
