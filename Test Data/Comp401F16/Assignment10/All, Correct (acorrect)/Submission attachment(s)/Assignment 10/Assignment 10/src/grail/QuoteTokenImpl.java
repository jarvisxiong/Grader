package grail;
import util.annotations.EditablePropertyNames; 
import util.annotations.PropertyNames;
import util.annotations.StructurePattern; 
import util.annotations.StructurePatternNames; 
import util.annotations.Tags;

@Tags({"Quote"}) 
@StructurePattern(StructurePatternNames.BEAN_PATTERN) 
@PropertyNames({"Input"}) 
@EditablePropertyNames({"Input"})

public class QuoteTokenImpl extends AToken implements Token{
		public QuoteTokenImpl(String input){
			super(input);
		}
	}

