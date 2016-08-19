package mp.ui;

import util.annotations.EditablePropertyNames;
import util.annotations.IsCompositeShape;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.annotations.Visible;
import util.misc.ThreadSupport;

@Tags("Avatar")
@IsCompositeShape(true)
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"X", "Y", "Location", "Text", "Head", "Arms", "Legs", "Body"}) 
@EditablePropertyNames({"X", "Y", "Location"})

public class AnAvatar extends AnAdvLocatable implements Avatar {
	
	LocatableString theUtterance;
	AdvLocatable baseLoc;
	Head head; 
	VShape arms, legs;	
	RotatingLine body;
	final int[] utteranceOffset = {30, -120}, headOffset = {-20,-164}, armsOffset = {0,-90}, 
			legsOffset = {0,0}, bodyOffset = {0,-100};
	final int armsLength = 50, legsLength = 50, bodyLength = 100;
	final double armsAngle = Math.PI/3, legsAngle = Math.PI/3;
	double legAngle = ((Math.PI)/2)-(legsAngle/2);
	int legWidth = (int) ((legsLength)*Math.cos(legAngle));
	int legHeight = (int) ((legsLength)*Math.sin(legAngle));
	
	public AnAvatar(String initHeadFile){
		head.setImageFileName(initHeadFile);
	}
	public AnAvatar(String initHeadFile, int x, int y){
		super.setLocation(x,y);
		baseLoc = getLocation().getRelativeLocation(-legWidth, -legHeight);
		theUtterance = new ALocatableString(baseLoc.getRelativeLocation(utteranceOffset[0],utteranceOffset[1]));
		head = new AHead(baseLoc.getRelativeLocation(headOffset[0],headOffset[1])); 
		arms = new AVShape(baseLoc.getRelativeLocation(armsOffset[0],armsOffset[1]), armsLength, armsAngle);
		legs = new AVShape(baseLoc.getRelativeLocation(legsOffset[0], legsOffset[1]), legsLength, legsAngle);	
		body = new ARotatingLine(baseLoc.getRelativeLocation(bodyOffset[0],bodyOffset[1]), bodyLength, Math.PI/2);
		head.setImageFileName(initHeadFile);
	}
	
	public AnAvatar(String initHeadFile, AdvLocatable location) {
		super.setLocation(location);
		baseLoc = getLocation().getRelativeLocation(-legWidth, -legHeight);
		theUtterance = new ALocatableString(baseLoc.getRelativeLocation(utteranceOffset[0],utteranceOffset[1]));
		head = new AHead(baseLoc.getRelativeLocation(headOffset[0],headOffset[1])); 
		arms = new AVShape(baseLoc.getRelativeLocation(armsOffset[0],armsOffset[1]), armsLength, armsAngle);
		legs = new AVShape(baseLoc.getRelativeLocation(legsOffset[0], legsOffset[1]), legsLength, legsAngle);	
		body = new ARotatingLine(baseLoc.getRelativeLocation(bodyOffset[0],bodyOffset[1]), bodyLength, Math.PI/2);
		head.setImageFileName(initHeadFile);
	}
	@Visible(true)
	public LocatableString getText() {
		return theUtterance;
	}
	@Visible(true)
	public Head getHead() {
		return head;
	}
	@Visible(true)
	public VShape getArms() {
		return arms;
	}
	@Visible(true)
	public VShape getLegs() {
		return legs;
	}
	@Visible(true)
	public RotatingLine getBody() {
		return body;
	}
	@Override
	public void setLocation(AdvLocatable newLocation) {
		super.setLocation(newLocation);
		reassembleAvatar(newLocation);
	}
	@Override
	public void setLocation(int newX, int newY) {
		super.setLocation(newX, newY);
		reassembleAvatar(this.getLocation());
	}
	void reassembleAvatar(AdvLocatable loc) {
		baseLoc = getLocation().getRelativeLocation(-legWidth, -legHeight);
		legs.setLocation(baseLoc);
		arms.setLocation(baseLoc.getRelativeLocation(armsOffset[0],armsOffset[1]));
		body.setLocation(baseLoc.getRelativeLocation(bodyOffset[0],bodyOffset[1]));
		head.setLocation(baseLoc.getRelativeLocation(headOffset[0],headOffset[1]));
		theUtterance.setLocation(baseLoc.getRelativeLocation(utteranceOffset[0],utteranceOffset[1]));
	}
	public void move(int moveX, int moveY) {
		setX(getX()+moveX);
		setY(getY()+moveY);
		setLocation(getLocation());
	}

}
