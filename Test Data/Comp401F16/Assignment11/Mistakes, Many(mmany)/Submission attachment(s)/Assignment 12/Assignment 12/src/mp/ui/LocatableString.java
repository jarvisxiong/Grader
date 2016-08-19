package mp.ui;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.STRING_PATTERN)
@PropertyNames({"X", "Y", "Location", "Text"}) 
@EditablePropertyNames({"X", "Y", "Location", "Text"})

public interface LocatableString extends AdvLocatable{
	public String getText();
	public void setText(String newText);
}
