package grail.draw.interfaces;

public interface BridgeMoat extends MutableShape {
	public RotatingShape getMoatLeft();
	public RotatingShape getMoatRight();
	public RotatingShape getBridgeBottom();
	public RotatingShape getBridgeTop();
	public RotatingShape getPlankOne();
	public RotatingShape getPlankTwo();
	public RotatingShape getPlankThree();
	public RotatingShape getPlankFour();
	public RotatingShape getPlankFive();
	public RotatingShape getPlankSix();
	public void setSize(int width, int height);
	public void setLocation(int x, int y);
	public void move(int x, int y);
	public int getFallZoneX();
	public int getFallZoneY();
}
