package grail.tokens;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public interface NumberTokenBean extends TokenBean {
	int getValue();
}
