package grail.scene;
import grail.AngleInterface;
import grail.BodyInterface;
import grail.Head;
import grail.BoundedShape;
import grail.Line;
import grail.StringShape;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.annotations.PropertyNames;

@Tags({"Avatar"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"aHead", "body", "arms", "legs", "text"})
public class BodyConstructor implements BodyInterface{
	Head aHead;
	Line body;
	AngleInterface arms;
	AngleInterface legs;
	StringShape text;
	final static int HEAD_DEFAULT_X = 110;
	final static int HEAD_DEFAULT_Y = 28;
	final static int BODY_RADIUS = 60;
	final static double VERTICAL_LINE = Math.PI;
	final static int BODY_X = 130;
	final static int BODY_Y = 150;
	final static int ARMS_Y = 90;
	final static int LIMB_RADIUS = 70;
	final static int RADIAN_SETTER = 4;
	final static double LIMB_ANGLE = Math.PI/RADIAN_SETTER;
	final static int TEXT_Y = 25;
	
	
	
public BodyConstructor(){
	
}
public BodyConstructor(String imagePath, int xChange, int yChange, int imageWidth, int imageHeight){
	aHead = new AHead(imagePath, HEAD_DEFAULT_X+xChange, yChange+HEAD_DEFAULT_Y, imageWidth, imageHeight);
	body = new ALine(BODY_RADIUS, VERTICAL_LINE);
	body.setX(BODY_X+xChange);
	body.setY(BODY_Y+yChange);
	arms = new AVShape(BODY_X+xChange,ARMS_Y+yChange, LIMB_RADIUS, LIMB_ANGLE);
	legs = new AVShape(BODY_X+xChange,BODY_Y+yChange, LIMB_RADIUS, LIMB_ANGLE);
	text = new AStringShape("test",BODY_Y+xChange,TEXT_Y+yChange);
	
}
@Tags({"move"})
public void moveBody(int x, int y){
	aHead.setX(aHead.getX()+x);
	aHead.setY(aHead.getY()+y);
	body.setX(body.getX()+x);
	body.setY(body.getY()+y);
	arms.moveAngle(x, y);
	legs.moveAngle(x, y);
	text.setX(text.getX()+x);
	text.setY(text.getY()+y);
}
public Head getAHead(){
	return aHead;
}
public Line getBody(){
	return body;
}
public AngleInterface getArms(){
	return arms;
}
public AngleInterface getLegs(){
	return legs;
}
public StringShape getText(){
	return text;
}
}
