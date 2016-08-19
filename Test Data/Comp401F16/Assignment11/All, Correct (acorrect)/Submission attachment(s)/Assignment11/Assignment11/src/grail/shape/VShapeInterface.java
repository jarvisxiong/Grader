package grail.shape;

import java.beans.PropertyChangeListener;

public interface VShapeInterface{
	public Line getLeftLine();
	public Line getRightLine();
	public void moveAngle(int x, int y);
	//public void setAngleLocation(int x, int y);
	//public void setAngleLocation(Point p);
	public Point getAngleLocation();
}
