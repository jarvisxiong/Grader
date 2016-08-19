package mp;

import java.beans.PropertyChangeListener;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.STRING_PATTERN)
public interface StringShape extends LocatableInter {
	void setText(String newText);
	String getText();
	void addPropertyChangeListener(PropertyChangeListener listener);

}
