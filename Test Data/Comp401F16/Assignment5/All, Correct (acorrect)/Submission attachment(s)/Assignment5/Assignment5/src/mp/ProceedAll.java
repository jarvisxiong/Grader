package mp;


import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"ProceedAll"})

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"input","value"})
@EditablePropertyNames({"input"})

public class ProceedAll extends Word {

	public ProceedAll(String input) {
		super(input);
		// TODO Auto-generated constructor stub
	}

}
