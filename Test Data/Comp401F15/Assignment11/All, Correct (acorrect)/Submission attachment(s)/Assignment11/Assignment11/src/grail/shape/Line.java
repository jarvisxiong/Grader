package grail.shape;

import util.models.PropertyListenerRegisterer;

//import util.models.PropertyListenerRegisterer;

public interface Line extends Point{
    //public Point getLocation();
    //public void setLocation(Point newLocation);
    public int getWidth();
    public void setWidth(int newVal);
    public int getHeight() ;
    public void setHeight(int newVal);
    public void rotate(int units);
}