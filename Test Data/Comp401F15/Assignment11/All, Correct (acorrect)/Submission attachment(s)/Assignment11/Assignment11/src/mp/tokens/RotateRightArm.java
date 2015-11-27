package mp.tokens;


import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"RotateRightArm"})

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"input","value"})
@EditablePropertyNames({"input"})

public class RotateRightArm extends Word{
	public RotateRightArm(String str)
	{
		super(str);
	}
}
