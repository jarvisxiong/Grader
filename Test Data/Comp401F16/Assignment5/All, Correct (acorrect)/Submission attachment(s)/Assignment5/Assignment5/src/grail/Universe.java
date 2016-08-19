package grail;

public interface Universe {
	public Line getLine();
	public StringShape getText();	
	public ImageShape getImage();
	public void setX(int newX);
	public void setY(int newY);
	public int getX();	
	public int getY();
	public Point getLineLocation();
	public void move(int newX, int newY);
	public void moveLine(int newX, int newY);
	public void moveText(int newX, int newY);
	public void moveImage(int newX, int newY);
}
