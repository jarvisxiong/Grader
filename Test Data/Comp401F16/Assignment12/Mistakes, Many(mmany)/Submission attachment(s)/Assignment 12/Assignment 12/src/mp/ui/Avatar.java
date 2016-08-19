package mp.ui;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;

@Tags("Avatar")
@PropertyNames({"X", "Y", "Location", "Text", "Head", "Arms", "Legs", "Body"}) 
@EditablePropertyNames({"X", "Y", "Location"})

public interface Avatar extends AdvLocatable {
	
	public LocatableString getText();
	public Head getHead();
	public VShape getArms();
	public VShape getLegs();
	public RotatingLine getBody();
	public void move(int moveX, int moveY);

}
