package grail.shape;

import util.annotations.Tags;

@Tags({"BoundedShape"})
public interface BoundedShape extends Point{
//	public void setX(int x);
//    public void setY(int y);
//    public int getX();
//    public int getY();
    public int getWidth();
    public int getHeight();
    public void setWidth(int w);
    public void setHeight(int h);
}
