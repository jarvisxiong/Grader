package mp;

public interface SpeechBubble {
	public StringShape getText();
	public void setText(String newText);
	public void setString(StringShape newStringShape);
	public void move(int addX, int addY);
	public void approach();
	public void pass();
	public void fail();
	public void reset();
	public void relocate(int newX, int newY);
}