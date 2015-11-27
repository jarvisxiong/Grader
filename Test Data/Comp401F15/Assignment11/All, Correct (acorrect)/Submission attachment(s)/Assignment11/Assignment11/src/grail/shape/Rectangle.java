package grail.shape;

import util.annotations.Explanation;


@Explanation("Creates a colorable rectangle from a point, width, and height")
public interface Rectangle extends BoundedShape{
    public boolean getFilled();
    public void setFilled(boolean newVal);
}
