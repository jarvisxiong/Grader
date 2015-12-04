package main;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mp.helpers.APropertyListenerSupport;
import mp.helpers.PropertyListenerSupport;
import mp.shapes.AGorge;
import mp.shapes.ALineShape;
import mp.shapes.AStringShape;
import mp.shapes.AnAngleShape;
import mp.shapes.AnAreaShape;
import mp.shapes.AnAvatar;
import mp.shapes.AnImageShape;
import mp.shapes.AngleShape;
import mp.shapes.AreaShape;
import mp.shapes.Avatar;
import mp.shapes.Gorge;
import mp.shapes.ImageShape;
import mp.shapes.LineShape;
import mp.shapes.StringShape;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.Tags;
import util.annotations.Visible;

@Tags({"BridgeScene"})
@StructurePattern("Bean Pattern")
@PropertyNames({"knightTurn", "occupied", "arthur", "lancelot", "robin", "galahad", "guard", "knightArea", "gorge", "guardArea"})

public class ABridgeScene implements BridgeScene{
	private Avatar arthur;
	private Avatar lancelot;
	private Avatar robin;
	private Avatar galahad;
	private Avatar guard;
	
	private AreaShape knightArea;
	private AreaShape guardArea;
	private Gorge gorge;
	
	private boolean knightTurn = false;
	private boolean occupied = false;
	
	private Avatar knightInStandingArea = null;
	
	private PropertyListenerSupport properties;
	
	//Constants
	private final int lineHeight = 30;
	private final int four = 4;
	private final double piOver4 = Math.PI/four;
	private final int rotateEquiv = 7;
	private final int startingY = 40;
	private final int spacer = 200;
	private final int guardX = 350;
	private final int space = 100;
	
	private final String approach = "approach";
	private final String say = "say";
	private final String passed = "passed";
	private final String failed = "failed";
	
	public ABridgeScene(){
		properties = new APropertyListenerSupport();
		
		StringShape text = new AStringShape("Arthur", 0, 0);
		LineShape torso = new ALineShape(lineHeight, 0);
		LineShape leftArm = new ALineShape(lineHeight, piOver4);
		leftArm.setColor(Color.red);
		LineShape rightArm = new ALineShape(lineHeight, rotateEquiv*piOver4);
		rightArm.setColor(Color.blue);
		AngleShape arms = new AnAngleShape(leftArm, rightArm);
		LineShape leftLeg = new ALineShape(lineHeight, piOver4);
		LineShape rightLeg = new ALineShape(lineHeight, rotateEquiv*piOver4);
		AngleShape legs = new AnAngleShape(leftLeg, rightLeg);
		ImageShape head = new AnImageShape("arthur.jpg");
		head.setY(startingY);
		arthur = new AnAvatar(text, head, arms, legs, torso);
		
		text = new AStringShape("Lancelot", 0, 0);
		torso = new ALineShape(lineHeight, 0);
		leftArm = new ALineShape(lineHeight, piOver4);
		rightArm = new ALineShape(lineHeight, rotateEquiv*piOver4);
		arms = new AnAngleShape(leftArm, rightArm);
		leftLeg = new ALineShape(lineHeight, piOver4);
		rightLeg = new ALineShape(lineHeight, rotateEquiv*piOver4);
		legs = new AnAngleShape(leftLeg, rightLeg);
		head = new AnImageShape("lancelot.jpg");
		head.setX(spacer/2);
		head.setY(startingY);
		lancelot = new AnAvatar(text, head, arms, legs, torso);
		
		text = new AStringShape("Robin", 0, 0);
		torso = new ALineShape(lineHeight, 0);
		leftArm = new ALineShape(lineHeight, piOver4);
		rightArm = new ALineShape(lineHeight, rotateEquiv*piOver4);
		arms = new AnAngleShape(leftArm, rightArm);
		leftLeg = new ALineShape(lineHeight, piOver4);
		rightLeg = new ALineShape(lineHeight, rotateEquiv*piOver4);
		legs = new AnAngleShape(leftLeg, rightLeg);
		head = new AnImageShape("robin.jpg");
		head.setY(spacer);
		robin = new AnAvatar(text, head, arms, legs, torso);
		
		text = new AStringShape("Galahad", 0, 0);
		torso = new ALineShape(lineHeight, 0);
		leftArm = new ALineShape(lineHeight, piOver4);
		rightArm = new ALineShape(lineHeight, rotateEquiv*piOver4);
		arms = new AnAngleShape(leftArm, rightArm);
		leftLeg = new ALineShape(lineHeight, piOver4);
		rightLeg = new ALineShape(lineHeight, rotateEquiv*piOver4);
		legs = new AnAngleShape(leftLeg, rightLeg);
		head = new AnImageShape("galahad.jpg");
		head.setY(spacer);
		head.setX(spacer/2);
		galahad = new AnAvatar(text, head, arms, legs, torso);
		
		text = new AStringShape("Guard", 0, 0);
		torso = new ALineShape(lineHeight, 0);
		leftArm = new ALineShape(lineHeight, piOver4);
		rightArm = new ALineShape(lineHeight, rotateEquiv*piOver4);
		arms = new AnAngleShape(leftArm, rightArm);
		leftLeg = new ALineShape(lineHeight, piOver4);
		rightLeg = new ALineShape(lineHeight, rotateEquiv*piOver4);
		legs = new AnAngleShape(leftLeg, rightLeg);
		head = new AnImageShape("guard.jpg");
		head.setY(spacer);
		head.setX(guardX);
		guard = new AnAvatar(text, head, arms, legs, torso);
		
		knightArea = new AnAreaShape(spacer, spacer+space, space, space/2);
		
		guardArea = new AnAreaShape(spacer+space+startingY/2, spacer+space, space, space/2);
		
		gorge = new AGorge(guardX+space, guardX-startingY);
	}
	
	@Override
	public void reset(){
		arthur.getHead().setY(startingY);
		arthur.getHead().setX(0);
		arthur.syncShapes();
		lancelot.getHead().setX(spacer/2);
		lancelot.getHead().setY(startingY);
		lancelot.syncShapes();
		robin.getHead().setX(0);
		robin.getHead().setY(spacer);
		robin.syncShapes();
		galahad.getHead().setX(spacer/2);
		galahad.getHead().setY(spacer);
		galahad.syncShapes();
	}
	
	@Override
	public Avatar getArthur() {
		return arthur;
	}
	@Override
	public Avatar getLancelot() {
		return lancelot;
	}
	@Override
	public Avatar getRobin() {
		return robin;
	}
	@Override
	public Avatar getGalahad() {
		return galahad;
	}
	@Override
	public Avatar getGuard() {
		return guard;
	}
	@Tags({"KnightArea"})
	@Override
	public AreaShape getKnightArea(){
		return knightArea;
	}
	@Tags({"GuardArea"})
	@Override
	public AreaShape getGuardArea(){
		return guardArea;
	}
	@Override
	public boolean getKnightTurn(){
		return knightTurn;
	}
	@Override
	public boolean getOccupied(){
		return !occupied;
	}
	
	public boolean preApproach(){
		return !occupied;
	}
	
	public boolean preSay(){
		return occupied;
	}
	
	public boolean prePassed(){
		return !knightTurn && occupied;
	}
	
	public boolean preFailed(){
		return occupied;
	}
	
	@Tags({approach})
	@Override
	public void approach(Avatar knight){
		assert preApproach(): "Knight area cannot be occupied when approaching";
		if(!occupied){
			occupied = true;
			
			knightInStandingArea = knight;
			
			knight.getHead().setX(knightArea.getX()+lineHeight);
			knight.getHead().setY(knightArea.getY()-space);
			knight.syncShapes();
			properties.notifyAllListeners(new PropertyChangeEvent(this, "this", approach, false));
			properties.notifyAllListeners(new PropertyChangeEvent(this, "this", failed, true));
			properties.notifyAllListeners(new PropertyChangeEvent(this, "this", say, true));properties.notifyAllListeners(new PropertyChangeEvent(this, "this", say, true));
			properties.notifyAllListeners(new PropertyChangeEvent(this, "this", passed, true));
		}
	}
	
	@Tags({say})
	@Override
	public void say(String text){
		assert preSay(): "Knight area needs to be occupied for speak";
		if(occupied){
			if(knightTurn){
				knightInStandingArea.getText().setText(text);
				guard.getText().setText("");
				knightTurn = false;
				properties.notifyAllListeners(new PropertyChangeEvent(this, "this", passed, false));
			}else{
				guard.getText().setText(text);
				knightInStandingArea.getText().setText("");
				knightTurn = true;
				properties.notifyAllListeners(new PropertyChangeEvent(this, "this", passed, true));
			}
		}
	}
	
	@Tags({passed})
	@Override
	public void passed(){
		assert prePassed(): "Needs to be knights turn and occupied";
		if(!knightTurn && occupied){
			occupied = false;
			
			knightInStandingArea.getHead().setX(knightArea.getX()+guardX+space/2);
			knightInStandingArea.syncShapes();
			
			knightInStandingArea = null;
			properties.notifyAllListeners(new PropertyChangeEvent(this, "this", failed, false));
			properties.notifyAllListeners(new PropertyChangeEvent(this, "this", approach, true));
			properties.notifyAllListeners(new PropertyChangeEvent(this, "this", say, false));
			properties.notifyAllListeners(new PropertyChangeEvent(this, "this", passed, false));
		}
	}
	
	@Tags({"failed"})
	@Override
	public void failed(){
		assert preFailed(): "Needs to be occupied for failed";
		if(occupied && !knightTurn){
			ImageShape head = knightInStandingArea.getHead();
			head.setX(knightArea.getX()+guardX-space/2);
			head.setY(knightArea.getY()+guardX-space/2-space/2);
			knightInStandingArea.syncShapes();
			occupied = false;
			properties.notifyAllListeners(new PropertyChangeEvent(this, "this", failed, false));
			properties.notifyAllListeners(new PropertyChangeEvent(this, "this", approach, true));
			properties.notifyAllListeners(new PropertyChangeEvent(this, "this", say, false));
			properties.notifyAllListeners(new PropertyChangeEvent(this, "this", passed, false));
		}else if(occupied && knightTurn){
			guard.getHead().setX(knightArea.getX()+guardX-space/2);
			guard.getHead().setY(knightArea.getY()+guardX-space/2);
			guard.syncShapes();
		}
	}
	
	@Tags({"scroll"})
	@Override
	public void scroll(int xOffset, int yOffset){
		arthur.move(xOffset, yOffset);
		lancelot.move(xOffset, yOffset);
		galahad.move(xOffset, yOffset);
		guard.move(xOffset, yOffset);
		robin.move(xOffset, yOffset);
		
		gorge.move(xOffset, yOffset);
		knightArea.move(xOffset, yOffset);
		guardArea.move(xOffset, yOffset);
		
	}

	@Override
	public Gorge getGorge() {
		return gorge;
	}

	@Visible(false)
	@Override
	public Avatar getCurrentKnight() {
		return knightInStandingArea;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener e) {
		properties.add(e);
	}
}
