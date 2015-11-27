package mp.tokens;


import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"Approach"})

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"input","value"})
@EditablePropertyNames({"input"})

public class Approach extends Word {

	public Approach(String input) {
		super(input);
		// TODO Auto-generated constructor stub
	}

}
