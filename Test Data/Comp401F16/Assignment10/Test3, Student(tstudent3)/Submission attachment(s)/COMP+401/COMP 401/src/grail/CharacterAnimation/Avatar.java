package grail.CharacterAnimation;

import java.beans.PropertyChangeListener;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.Tags;
import util.models.PropertyListenerRegisterer;

@StructurePattern("Bean Pattern")
@Tags({"Avatar"})
@PropertyNames({"arms","legs","head","body","speech"})
@EditablePropertyNames({"speech"})
public class Avatar implements AvatarInterface{
	
	VshapeInterface arms;
	VshapeInterface legs;
	HeadInterface head;
	LineInterface body;
	PointInterface location;
	StringShapeInterface speech;
	
	final int xMod = 20;
	final int yMod = 33;
	final int headHeightMod = 33;
	final int headWidthMod = 64;
	final int bodyMod = 100;
	final int speechXMod = 40;
	final int speechYMod = -5;
		
	public Avatar(String name, PointInterface initLocation){
		location = initLocation;
		int initX = initLocation.getX();
		int initY = initLocation.getY();
		location.setX(initX + xMod);
		location.setY(initY + yMod);
		head = new CharacterHead(name, initLocation.getX() - xMod, initLocation.getY() - yMod, headHeightMod, headWidthMod);
		body = new Line(location,0,bodyMod);
		arms = new CharArms(location);
		legs = new CharLegs(location);
		speech = new StringShape("", initX + speechXMod, initY + speechYMod);
	}
	
	public void setSpeech(String newText) {
		speech.setText(newText);
		
	}

	public StringShapeInterface getSpeech() {
		return speech;
	}
	
	public HeadInterface getHead() {
		return head;
	}

	public VshapeInterface getArms() {
		return arms;
	}

	public VshapeInterface getLegs() {
		return legs;
	}

	public LineInterface getBody() {
		return body;
	}

	public void move(int x, int y){
		@Tags({"move"})
		PointInterface tempPoint = body.getLocation();
		int newX = x + tempPoint.getX();
		int newY = y + tempPoint.getY();
		tempPoint.setX(newX);
		tempPoint.setY(newY);
		
		
		
		body.setLocation(tempPoint);
		arms.movePoint( x , y);
		legs.movePoint( x , y);
		head.setX(head.getX() + x);
		head.setY(head.getY() + y);
		speech.setX(speech.getX() + x);
		speech.setY(speech.getY() + y);
	}
	
	public void scale(int scale){
		body.setHeight(body.getHeight() * scale);
		arms.getLeftLine().setHeight(arms.getLeftLine().getHeight() * scale);
		arms.getRightLine().setHeight(arms.getRightLine().getHeight() * scale);
		legs.getLeftLine().setHeight(legs.getLeftLine().getHeight() * scale);
		legs.getRightLine().setHeight(legs.getRightLine().getHeight() * scale);
		body.setHeight(body.getHeight() * scale);
	}

}
