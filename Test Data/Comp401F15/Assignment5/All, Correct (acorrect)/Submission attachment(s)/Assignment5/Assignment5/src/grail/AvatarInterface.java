package grail;

public interface AvatarInterface {
	public void move(int dx,int dy);
	public StringShape getText();
	public void setText(StringShape ss);
	public ImageShape getHead();
	public VShapeInterface getArms();
	public VShapeInterface getLegs();
	public Line getWaist();
	public void scale(int k);
}
