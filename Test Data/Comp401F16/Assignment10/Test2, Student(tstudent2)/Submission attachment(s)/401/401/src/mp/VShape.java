package mp;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Visible;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public interface VShape extends LocatableInter {
	
	Line getLeftLine();
	Line getRightLine();
}
