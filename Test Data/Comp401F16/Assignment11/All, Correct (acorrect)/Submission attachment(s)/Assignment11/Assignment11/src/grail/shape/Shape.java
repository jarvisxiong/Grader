package grail.shape;

public interface Shape extends BoundedShape{
    public void setRadius(double r);
    public void setAngle(double a);
//    public void setX(int x);
//    public void setY(int y);
//    public int getX();
//    public int getY();
//    public int getWidth();
//    public int getHeight();
    public void changeRotateAngle (int units);
    public void move(int dx,int dy);


}
