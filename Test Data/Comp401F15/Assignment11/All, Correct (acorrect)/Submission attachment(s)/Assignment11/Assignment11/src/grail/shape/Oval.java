package grail.shape;

import util.annotations.Explanation;
import util.annotations.Tags;
@Tags("BoundedShape")
@Explanation("Creates a colorable oval from a point, width, and height")
public interface Oval extends BoundedShape {
	public void setFilled(boolean newVal);
    public boolean getFilled();
}
