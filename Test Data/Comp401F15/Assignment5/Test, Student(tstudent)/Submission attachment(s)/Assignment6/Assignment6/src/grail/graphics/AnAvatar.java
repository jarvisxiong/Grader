package grail.graphics;

import util.annotations.IsAtomicShape;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.annotations.PropertyNames;
import util.annotations.EditablePropertyNames;

@Tags({"Avatar"})
@IsAtomicShape(false)
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Text", "Legs", "Arms", "Head", "Body", "X", "Y", "Speech"})
@EditablePropertyNames({"Text", "X", "Y"})
public class AnAvatar extends ALocatable implements Avatar {
	//Constants to determine relative body part positions based off avatar head position
	private static final int DEFAULT_POSITION = 110;
	private static final int SPEECH_X = 40;
	private static final int BODY_X = 20;
	private static final int BODY_Y = 63;
	private static final int BODY_LENGTH = 80;
	
	private StringShape text;
	private ImageShape head;
	private VShape legs;
	private VShape arms;
	private RotatingLine body;
	
	public AnAvatar(String imageFile) {
		setX(DEFAULT_POSITION);
		setY(DEFAULT_POSITION);
		text = new AStringShape(getX() + SPEECH_X, getY(), "");
		head = new AShapeImage(imageFile, getX(), getY());
		body = new ARotatingLine(getX() + BODY_X, getY() + BODY_Y, Math.PI/2, BODY_LENGTH);
		arms = new AVShape(getX() + BODY_X, getY() + BODY_Y);
		legs = new AVShape(getX() + BODY_X, getY() + BODY_Y + BODY_LENGTH);
	}
	
	public AnAvatar(String imageFile, int initX, int initY) {
		setY(initY);
		setX(initX);
		text = new AStringShape(getX() + SPEECH_X, getY(), "");
		head = new AShapeImage(imageFile, getX(), getY());
		body = new ARotatingLine(getX() + BODY_X, getY() + BODY_Y, Math.PI/2, BODY_LENGTH);
		arms = new AVShape(getX() + BODY_X, getY() + BODY_Y);
		legs = new AVShape(getX() + BODY_X, getY() + BODY_Y + BODY_LENGTH);
	}
	
	public String getText() { return text.getString(); }
	public ImageShape getHead() { return head; }
	public RotatingLine getBody() { return body; }
	public VShape getArms() { return arms; }
	public VShape getLegs() { return legs; }
	public StringShape getSpeech() { return text; }
	
	@Tags({"move"})
	public void move(int xAmount, int yAmount) {
		setX(getX() + xAmount);
		setY(getY() + yAmount);
		arms.move(xAmount, yAmount);
		legs.move(xAmount, yAmount);
		body.move(xAmount, yAmount);
		text.setX(text.getX() + xAmount);
		text.setY(text.getY() + yAmount);
		head.setX(head.getX() + xAmount);
		head.setY(head.getY() + yAmount);
	}
	
	public void setText(String newText) {
		text.setString(newText);
	}
}
