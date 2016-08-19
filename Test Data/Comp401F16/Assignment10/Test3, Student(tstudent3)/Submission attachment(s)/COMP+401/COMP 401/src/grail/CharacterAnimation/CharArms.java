package grail.CharacterAnimation;

import util.annotations.StructurePattern;

@StructurePattern("Bean Pattern")
public class CharArms extends ArmLegVshape {

	final int mod25 = 25;
	final int mod50 = 25;
	final int mod20 = 20;
	
	
	public CharArms(PointInterface initPoint){
		pivotLocation = new Point(initPoint.getX(), initPoint.getY() + mod20);
		leftLine = new Line(pivotLocation, -mod25, mod50);
		rightLine = new Line(pivotLocation, mod25, mod50);
	}
}
