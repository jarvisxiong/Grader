package mp.ui;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;

@PropertyNames({"X", "Y", "Location", "LeftCliff", "RightCliff", "Bridge"}) 
@EditablePropertyNames({"X", "Y", "Location"})

public interface Gorge extends AdvLocatable {
	
	public RotatingLine getLeftCliff();
	public RotatingLine getRightCliff();
	public Bridge getBridge();

}
