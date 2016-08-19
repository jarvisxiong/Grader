package grail.CharacterAnimation;

public interface AvatarInterface {
	
	public void setSpeech(String newText);
	public StringShapeInterface getSpeech();
	public HeadInterface getHead();
	public VshapeInterface getArms();
	public VshapeInterface getLegs();
	public LineInterface getBody();
	public void scale(int scale);
	public void move(int x, int y);
	
	
}
