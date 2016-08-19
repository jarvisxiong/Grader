package grail.CharacterAnimation;


import util.annotations.Tags;

@Tags({"Angle"})

public class ArmLegVshape implements VshapeInterface {

	PointInterface pivotLocation;
	LineInterface leftLine;
	LineInterface rightLine;
	
	public LineInterface getLeftLine() {
		return leftLine;
	}

	public LineInterface getRightLine() {
		return rightLine;
	}
	
	public void movePoint(int x, int y) {
		
		int tempX = pivotLocation.getX() + x;
		pivotLocation.setX(tempX);
		
		int tempY = pivotLocation.getY() + y;
		pivotLocation.setY(tempY);
		
		leftLine.setXY(tempX, tempY);
		rightLine.setXY(tempX, tempY);
	}
	

	
}
