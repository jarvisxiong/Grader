package mp.ui;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;

@PropertyNames({"X", "Y", "Location", "HorizontalLines", "VerticalLines"}) 
@EditablePropertyNames({"X", "Y", "Location"})

public interface Bridge extends AdvLocatable {
	
	public RotatingLine[] getHorizontalLines();
	public void setHorizontalLines();
	public RotatingLine[] getVerticalLines();
	public void setVerticalLines();

}
