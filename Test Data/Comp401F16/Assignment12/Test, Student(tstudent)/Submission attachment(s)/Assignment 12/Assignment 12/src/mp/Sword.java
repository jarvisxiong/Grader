package mp;

public interface Sword {
	public Line getSword ();
	public void move(int addX, int addY);
	public void approach();
	public void pass();
	public void fail();
	public void reset();
	public void relocate(int newX, int newY);
}
