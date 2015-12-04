package mp;

import util.annotations.Tags;
@Tags({"Avatar"})

public interface Avatar {
	public Head getHead();
	public AngleShape getLegs();
	public AngleShape getArms();
	public Torso getTorso();
	public Sword getSword();
	public StringShape getText();
	public void setText(String newString);
	public void setText(StringShape newStringShape);
	public void move (int addX, int addY);
	public void approach();
	public void pass();
	public void fail();
	public boolean getOccupied();
	public void setOccupied();
	public void setUnoccupied();
	public void reset();
	public void relocate(int newX, int newY);
}
