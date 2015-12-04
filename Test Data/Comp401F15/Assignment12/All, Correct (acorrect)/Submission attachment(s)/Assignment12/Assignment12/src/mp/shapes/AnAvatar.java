package mp.shapes;

import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.Tags;

@Tags({"Avatar"})
@StructurePattern("Bean Pattern")
@PropertyNames({"text", "head", "arms", "legs", "torso"})

public class AnAvatar implements Avatar{
	private StringShape text;
	private ImageShape head;
	private AngleShape arms;
	private AngleShape legs;
	private LineShape torso;
	
	private final int textOffset = 20;
	private final int numberOfShapes = 5; 
	
	private BoundedShape[] body = new BoundedShape[numberOfShapes];
	
	public AnAvatar(StringShape text, ImageShape head, AngleShape arms, AngleShape legs, LineShape torso){
		this.text = text;
		this.head = head;
		this.arms = arms;
		this.legs = legs;
		this.torso = torso;
		syncShapes();
		
		final int four = 4; final int three = 3;
		
		body[0] = text; body[1] = head; body[2] = arms; body[three] = legs; body[four] = torso;
	}
	@Override
	public StringShape getText() {
		return text; 
	}

	@Override
	public ImageShape getHead() {
		return head;
	}

	@Override
	public AngleShape getArms() {
		return arms;
	}

	@Override
	public AngleShape getLegs() {
		return legs;
	}

	@Override
	public LineShape getTorso() {
		return torso;
	}
	
	@Tags({"connect"})
	public void syncShapes(){
		int centerX = head.getX() + head.getWidth()/2;
		arms.setX(centerX);
		arms.setY(head.getY()+head.getHeight());
		
		torso.setX(centerX);
		torso.setY(head.getY()+head.getHeight());
		
		legs.setX(centerX);
		legs.setY(head.getY()+head.getHeight()+torso.getHeight());
		
		text.setX(centerX);
		text.setY(head.getY() - textOffset);
	}
	
	@Tags({"move"})
	@Override
	public void move(int xOffset, int yOffset){
		for(int i = 0; i < body.length; i++){
			BoundedShape shape = body[i];
			shape.setX(shape.getX()+xOffset);
			shape.setY(shape.getY()+yOffset);
		}
	}
	
	@Tags({"scale"})
	@Override
	public void scale(double scale){
		torso.setRadius(torso.getRadius()*scale);
		arms.getLeftLine().setRadius(arms.getLeftLine().getRadius()*scale);
		arms.getRightLine().setRadius(arms.getRightLine().getRadius()*scale);
		legs.getLeftLine().setRadius(legs.getLeftLine().getRadius()*scale);
		legs.getRightLine().setRadius(legs.getRightLine().getRadius()*scale);
		syncShapes();
	}
}
