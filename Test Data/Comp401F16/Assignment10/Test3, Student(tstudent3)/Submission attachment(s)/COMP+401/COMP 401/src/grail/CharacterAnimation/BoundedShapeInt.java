package grail.CharacterAnimation;

public interface BoundedShapeInt extends PropertyListenerSupport , LocatableShapeInt {

	 public PointInterface getLocation();
	    public void setLocation(PointInterface tempPoint);
	    public int getWidth();
	    public void setWidth(int newVal);
	    public int getHeight() ;
	    public void setHeight(int newVal);
	    public void rotate(int rotation);
}
