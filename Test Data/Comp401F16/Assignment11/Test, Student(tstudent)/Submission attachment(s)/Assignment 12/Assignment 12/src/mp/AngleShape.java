package mp;

import util.annotations.Tags;
@Tags({"Angle"})
public interface AngleShape {
	public Line getLeftLine();
	public Line getRightLine();	
	public void move (int addX, int addY);
	public void legsApproach();
	public void armsApproach();
	public void armsPass();
	public void legsPass();
	public void armsFail();
	public void legsFail();
	public void reset();
	public void relocate(int newX, int newY);
}
