package grail.scene;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import grail.scene.APropertyListenerSupport;
import grail.Line;
import grail.PropertyListenerSupport;
import grail.BodyInterface;
import grail.BridgeScene;
import grail.BoundedShape;
import grail.StandingArea;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.annotations.PropertyNames;
import util.misc.ThreadSupport;

import java.beans.PropertyChangeEvent;

@Tags({"BridgeScene"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"arthur", "galahad","robin","lancelot","guard", "leftEdge", "rightEdge", "topBridge", "botBridge", "guardArea", "knightArea", "Occupied", "knightTurn"})
public class ABridgeScene implements BridgeScene{
BodyInterface arthur;
BodyInterface galahad;
BodyInterface robin;
BodyInterface lancelot;
BodyInterface guard;
Line leftEdge;
Line rightEdge;
Line topBridge;
Line botBridge;
StandingArea guardArea;
StandingArea knightArea;
Boolean occupied = false;
Boolean knightTurn = false;
BodyInterface knightInArea;
PropertyListenerSupport propertySupport = new APropertyListenerSupport();
final static int ARTHUR_X = 40;
final static int ARTHUR_Y = 64;
final static int ROBIN_IMAGE_X = 43;
final static int ROBIN_IMAGE_Y = 64;
final static int ROBIN_X = 50;
final static int GALAHAD_X = 100;
final static int GALAHAD_IMAGE_X = 38;
final static int GALAHAD_IMAGE_Y = 65;
final static int LANCELOT_X = 150;
final static int LANCELOT_IMAGE_X = 37;
final static int LANCELOT_IMAGE_Y = 64;
final static int GUARD_X = 200;
final static int GUARD_IMAGE_X = 33;
final static int GUARD_IMAGE_Y = 65;
final static int EDGE_RADIUS = 700;
final static int LEFT_EDGE_X = 700;
final static int RIGHT_EDGE_X = 900;
final static double VERTICAL_LINE = Math.PI;
final static double HORIZONTAL_LINE = Math.PI/2;
final static int EDGE_Y = 800;
final static int BRIDGE_RADIUS = 200;
final static int GUARD_AREA_X = 500;
final static int GUARD_AREA_Y = 450;
final static int AREA_WIDTH_HEIGHT = 150;
final static int KNIGHT_AREA_X = 330;
final static int KNIGHT_AREA_Y = 450;
final static int TOP_BRIDGE_Y = 450;
final static int BOT_BRIDGE_Y = 550;
final static int GUARD_MOVE_X = 260;
final static int GUARD_MOVE_Y = 400;
final static int KNIGHT_MOVE_X = 415;
final static int KNIGHT_MOVE_Y = 505;
final static int PASSED_X = 600;
final static int FAILED_X = 350;
final static int FAILED_Y = -300;
final static int GUARD_FAILED_X = 200;
final static int INCREMENTS = 35;
final static int ACTION_DELAY = 200;
final static int ROTATE_ONE = 16;
final static int ROTATE_TWO = 32;

public ABridgeScene(){
	
	arthur = new BodyConstructor("arthur.jpg", 0, 0, ARTHUR_X, ARTHUR_Y);
	robin = new BodyConstructor("robin.jpg", ROBIN_X, 0, ROBIN_IMAGE_X, ROBIN_IMAGE_Y);
	galahad = new BodyConstructor("galahad.jpg", GALAHAD_X, 0, GALAHAD_IMAGE_X, GALAHAD_IMAGE_Y);
	lancelot = new BodyConstructor("lancelot.jpg", LANCELOT_X, 0, LANCELOT_IMAGE_X, LANCELOT_IMAGE_Y);
	guard = new BodyConstructor("guard.jpg", GUARD_X, 0, GUARD_IMAGE_X, GUARD_IMAGE_Y);
	leftEdge = new ALine(EDGE_RADIUS, Math.PI);
	rightEdge = new ALine(EDGE_RADIUS, VERTICAL_LINE);
	leftEdge.setX(LEFT_EDGE_X);
	rightEdge.setX(RIGHT_EDGE_X);
	leftEdge.setY(EDGE_Y);
	rightEdge.setY(EDGE_Y);
	topBridge = new ALine(BRIDGE_RADIUS, HORIZONTAL_LINE);
	topBridge.setX(LEFT_EDGE_X);
	topBridge.setY(TOP_BRIDGE_Y);
	botBridge = new ALine (BRIDGE_RADIUS, HORIZONTAL_LINE);
	botBridge.setX(LEFT_EDGE_X);
	botBridge.setY(BOT_BRIDGE_Y);
	guardArea = new AStandingArea(GUARD_AREA_X, GUARD_AREA_Y, AREA_WIDTH_HEIGHT, AREA_WIDTH_HEIGHT);
	knightArea = new AStandingArea(KNIGHT_AREA_X, KNIGHT_AREA_Y, AREA_WIDTH_HEIGHT, AREA_WIDTH_HEIGHT);
	guard.moveBody(GUARD_MOVE_X, GUARD_MOVE_Y);
	
}

public boolean getKnightTurn(){
	return knightTurn;
}

@Tags({"say"})
public void sayScene(String aString){
	assert preSayScene();
		if (!knightTurn){
			guard.getText().setText(aString);
			knightTurn = true;
		}
		else{
			knightInArea.getText().setText(aString);
			knightTurn = false;
		}
}

public boolean preSayScene(){
	return occupied;
}

@Tags({"approach"})
public void approachArea(BodyInterface avatar){
	assert preApproachArea();
			knightInArea = avatar;
			int xDistance = (int) (KNIGHT_MOVE_X - avatar.getBody().getX())/INCREMENTS;
			int yDistance = (int) (KNIGHT_MOVE_Y-avatar.getBody().getY())/INCREMENTS;
			for (int i = 0; i <= INCREMENTS; i++){
			avatar.moveBody(xDistance, yDistance);
			if (i%2 ==0){
				avatar.getLegs().getLeftLine().rotate(-ROTATE_ONE);
				avatar.getLegs().getRightLine().rotate(ROTATE_TWO);
				}
				else if (i%2 != 0){
					avatar.getLegs().getRightLine().rotate(ROTATE_ONE);
					avatar.getLegs().getLeftLine().rotate(-ROTATE_TWO);
				}
			ThreadSupport.sleep(ACTION_DELAY);
		}
	occupied = true;
	propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "approach", true));
}

public boolean preApproachArea(){
	return !occupied;
}

@Tags({"passed"})
public void pass(){
	assert prePass();
	if(!knightTurn){
		knightInArea.moveBody(PASSED_X, 0);
		occupied = false;
		propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "passed", false));
	}
	
}

public boolean prePass(){
	return occupied;
}

@Tags({"failed"})
public void fail(){
	assert preFail();
		if(!knightTurn){
			knightInArea.moveBody(FAILED_X, FAILED_Y);
			occupied = false;
			propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "failed", false));
		}
		else if (knightTurn){
			guard.moveBody(GUARD_FAILED_X, FAILED_Y);
			pass();
		}
}

public boolean preFail(){
	return occupied;
}

public Boolean getOccupied(){
	return occupied;
}
@Tags({"KnightArea"})
public StandingArea getKnightArea(){
	return knightArea;
}
@Tags({"GuardArea"})
public StandingArea getGuardArea(){
	return guardArea;
}

public Line getBotBridge(){
	return botBridge;
}

public Line getTopBridge(){
	return topBridge;
}

public Line getLeftEdge(){
	return leftEdge;
}

public Line getRightEdge(){
	return rightEdge;
}

public BodyInterface getArthur(){
	return arthur;
}
public BodyInterface getGalahad(){
	return galahad;
}
public BodyInterface getRobin(){
	return robin;
}
public BodyInterface getLancelot(){
	return lancelot;
}
public BodyInterface getGuard(){
	return guard;
}

public void addPropertyChangeListener(PropertyChangeListener arg0) {
	propertySupport.add(arg0);
	
}

}
