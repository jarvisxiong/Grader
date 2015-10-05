package grail.tokens;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public interface WordTokenBean extends TokenBean {
	String getValue();
}
