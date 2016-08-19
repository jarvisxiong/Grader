package mp;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public interface Avatar extends LocatableInter{
	public StringShape getText();
	public Image getHead();
	public VShape getArms();
	public Line getBody();
	public VShape getLegs();
	public void move(int stepsX, int stepsY);
	public void preciseMove(int newX,int newY);
	public void animatedMove(int newX, int newY);
	public void relocate(int x, int y);
}
