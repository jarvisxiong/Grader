package mp.tokens;



import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"Sleep"})

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"input","value"})
@EditablePropertyNames({"input"})

public class Sleep extends Word {

	public Sleep(String input) {
		super(input);
		// TODO Auto-generated constructor stub
	}

}
