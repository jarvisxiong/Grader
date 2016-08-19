package grail.shape;

public interface ParallelLinesInterface {
	public void setFirstLineLocation(int x, int y);
	public void setSecondLineLocation(int x, int y);
	public Point getFirstLineLocation();
	public Point getSecondLineLocation();
	public Line getFirstLine();
	public Line getSecondLine();
	public void move(int dx,int dy);


}
