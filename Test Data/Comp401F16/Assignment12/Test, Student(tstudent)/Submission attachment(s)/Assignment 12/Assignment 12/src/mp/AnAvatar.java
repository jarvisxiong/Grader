package mp;

import util.annotations.Tags;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Visible;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Sword","Arms", "Legs","Text", "Torso", "Head","Occupied"})
@EditablePropertyNames({"Text"})
@Tags({"Avatar"})

public class AnAvatar implements Avatar{
	protected boolean occupied = false;
	int x, y, height, width, swordsLength, swordsWidth;
	SpeechBubble text;
	AngleShape arms;
	AngleShape legs;
	Line leftArm;
	Line rightArm;
	Torso torso;
	Line leftLeg;
	Line rightLeg;
	Sword sword;
	Head head;
	
	public AnAvatar (String fileName, String initText, int initX, int initY, int initWidth, int initHeight, int swordLength, int swordWidth) {
		x = initX;
		y = initY;
		height = initHeight;
		width = initWidth;
		swordsLength = swordLength;
		swordsWidth = swordWidth;
		head = new AHead(fileName, (int) (initX-initHeight/2), (int) (initY-initWidth), initHeight, initWidth);
		text = new ASpeechBubble(initText, initX, initY, initHeight);
		leftArm = new LineShape (initX, initY, -initWidth, initHeight);
		rightArm = new LineShape (initX, initY, initWidth, initHeight);
		arms = new AnAngleShape (leftArm, rightArm);
		torso = new ATorso (initX, initY, 0, initHeight);
		leftLeg = new LineShape (initX, initY+initHeight, -initWidth, initHeight);
		rightLeg = new LineShape (initX, initY+initHeight, initWidth, initHeight);
		legs = new AnAngleShape (leftLeg, rightLeg);
		sword = new ASword (initX, initY, initHeight, swordWidth, -swordLength, initWidth);
	}
	
@Tags({"move"})  
	public void move (int addX, int addY) {
		legs.move(addX, addY);
		arms.move(addX, addY);
		head.move(addX, addY);
		torso.move(addX, addY);
		text.move(addX, addY);
		sword.move(addX, addY);
	}
@Visible(false)
	public void approach () {
	if (getOccupied()) {return;}
	torso.approach();
	head.approach();
	legs.legsApproach();
	arms.armsApproach();
	text.approach();
	sword.approach();
	setOccupied();
}
@Visible(false)
	public void pass() {
		if (!getOccupied()) {return;}
		torso.pass();
		head.pass();
		legs.legsPass();
		arms.armsPass();
		text.pass();
		sword.pass();
		setUnoccupied();
	}
@Visible(false)
	public void fail() {
		if (!getOccupied()) {return;}
		torso.fail();
		head.fail();
		legs.legsFail();
		arms.armsFail();
		text.fail();
		sword.fail();
		setUnoccupied();
	}
	
	public Sword getSword() {return sword;}
	public AngleShape getArms() {return arms;}
	public AngleShape getLegs() {return legs;}
	public Torso getTorso() {return torso;}
	public StringShape getText() {return text.getText();}
@Visible(false)
	public void setText(StringShape newStringShape) {text.setString(newStringShape);}
@Visible(false)
	public void setText(String newText) {text.setText(newText);}
	public Head getHead () {return head;}

@Visible(false)
	public boolean getOccupied() {
		if ("guard.jpg".equals(head.getImageFileName())) {occupied = true;}
		return occupied;
		}
@Visible(false)
	public void setOccupied() {occupied = true;}
@Visible(false)
	public void setUnoccupied() {occupied = false;}
	
@Visible(false)
	public void reset () {
		head.reset();
		text.reset();
		arms.reset();
		legs.reset();
		torso.reset();
		sword.reset();
		setUnoccupied();
	}

@Visible(false)
	public void relocate(int newX, int newY) {
		head.relocate((int)newX-height/2, newY-width);
		text.relocate(newX, newY);
		arms.relocate(newX, newY);
		legs.relocate(newX, newY+height);
		torso.relocate(newX, newY);
		sword.relocate(newX+width, newY+height);
	}
}
