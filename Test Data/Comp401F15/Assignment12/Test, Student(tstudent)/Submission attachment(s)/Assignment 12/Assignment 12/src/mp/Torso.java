package mp;

public interface Torso {
	public Line getTorso();
	public void move(int addX, int addY);
	public void approach();
	public void pass();
	public void fail();
	public void reset();
	public void relocate(int newX, int newY);
}
