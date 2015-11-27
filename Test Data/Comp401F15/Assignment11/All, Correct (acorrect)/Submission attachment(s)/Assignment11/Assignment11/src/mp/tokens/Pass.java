package mp.tokens;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
@Tags({ "pass" })

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"input","value"})
@EditablePropertyNames({"input"})
public class Pass extends Word{

	public Pass(String token) {
		super(token);
	}
}







