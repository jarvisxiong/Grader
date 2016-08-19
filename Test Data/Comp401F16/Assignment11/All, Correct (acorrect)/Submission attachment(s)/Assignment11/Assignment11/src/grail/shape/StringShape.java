package grail.shape;

public interface StringShape extends Point{
	//use interface inheritance to avoid 
//	public int getX();
//	public void setX(int newX);
//	public int getY();
//	public void setY(int newY);
	public String getText();
	public void setText(String newText);
}
