package grail.graphics;

import util.annotations.Tags;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@Tags({"Avatar"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public interface Avatar extends Locatable {
	public VShape getLegs();
	public VShape getArms();
	public RotatingLine getBody();
	public ImageShape getHead();
	public String getText();
	public void move(int xAmount, int yAmount);
	public void setText(String newText);
	public StringShape getSpeech();
}
