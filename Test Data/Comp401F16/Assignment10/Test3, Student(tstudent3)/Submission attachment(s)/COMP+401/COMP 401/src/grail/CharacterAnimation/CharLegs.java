package grail.CharacterAnimation;

import util.annotations.StructurePattern;

@StructurePattern("Bean Pattern")
public class CharLegs extends ArmLegVshape{
	
	final int mod25 = 25;
	final int mod50 = 25;
	final int mod20 = 20;
	final int mod100 = 100;
	
	public CharLegs(PointInterface initPoint){
		
		pivotLocation = new Point(initPoint.getX(), initPoint.getY() + mod100);
		leftLine = new Line(pivotLocation, -mod25, mod50);
		rightLine = new Line(pivotLocation, mod25, mod50);
	}
	
	
	
}
