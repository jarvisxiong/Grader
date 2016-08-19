package mp.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mp.ui.mvc.APropertyListenerSupport;
import mp.ui.mvc.PropertyListenerSupport;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.annotations.Visible;

@Tags("BridgeScene")
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Arthur", "Lancelot", "Robin", "Galahad", "Guard", "Gorge", "KnightArea", "GuardArea", "KnightTurn", "Occupied"})

public class ABridgeScene implements BridgeScene {
	
	Avatar avatarArthur, avatarLancelot, avatarRobin, avatarGalahad, avatarGuard, currAvatar;
	Bridge bridge;
	AdvLocatable bridgeLocation, knightAreaStandingLocation, guardAreaStandingLocation, passedLocation, aLoc1, aLoc2, aLoc3, aLoc4;
	final int[] bridgeLocXY = {350,450}, aPos1 = {155,225}, aPos2 = {270,225}, aPos3 = {80,300}, aPos4 = {215,300},
			knightAreaHW = {100,100}, guardAreaHW = {75,75}, knightAreaOffset = {-200,-100}, guardAreaOffset = {-85,-75}, 
			knightAreaStandOffset = {0,-15}, guardAreaStandOffset = {15,-15}, passedLocOffset = {0,0};
	final double dimAngle = (-Math.PI/3), bridgeLength = (double) 260, cliffLength = (double) 800;
	Gorge gorge;
	Shape knightArea, guardArea;
	boolean occupied, guardSpoken;
	int personalSpace = 0;
	final int personalSpaceIncrement = 40, failedYOffset = 1000;
	PropertyListenerSupport listenerSupport;
	
	public ABridgeScene(){
		bridgeLocation = new AnAdvLocatable(bridgeLocXY[0], bridgeLocXY[1]);
		knightArea = new AnOval(bridgeLocation.getRelativeLocation(knightAreaOffset[0],knightAreaOffset[1]), knightAreaHW[0], knightAreaHW[1]);
		guardArea = new AnOval(bridgeLocation.getRelativeLocation(guardAreaOffset[0],guardAreaOffset[1]), guardAreaHW[0], guardAreaHW[1]);
		gorge = new AGorge(bridgeLocation, cliffLength, dimAngle, bridgeLength);
		knightAreaStandingLocation = new AnAdvLocatable(knightArea.getX()+knightArea.getWidth()+knightAreaStandOffset[0], 
				knightArea.getY()+knightArea.getHeight()+knightAreaStandOffset[1]);
		guardAreaStandingLocation = new AnAdvLocatable(guardArea.getX()+guardArea.getWidth()+guardAreaStandOffset[0], 
				guardArea.getY()+guardArea.getHeight()+guardAreaStandOffset[1]);
		passedLocation = bridgeLocation.getRelativeLocation(bridgeLength+passedLocOffset[0], passedLocOffset[1]);
		
		aLoc1 = new AnAdvLocatable(aPos1[0], aPos1[1]);
		aLoc2 = new AnAdvLocatable(aPos2[0], aPos2[1]);
		aLoc3 = new AnAdvLocatable(aPos3[0], aPos3[1]);
		aLoc4 = new AnAdvLocatable(aPos4[0], aPos4[1]);
		avatarArthur = new AnAvatar("arthur.jpg", aLoc1);
		avatarRobin = new AnAvatar("robin.jpg", aLoc2);
		avatarLancelot = new AnAvatar("lancelot.jpg", aLoc3);
		avatarGalahad = new AnAvatar("galahad.jpg", aLoc4);
		avatarGuard = new AnAvatar("guard.jpg", guardAreaStandingLocation);
		
		listenerSupport = new APropertyListenerSupport();
	}

	public Avatar getArthur() {		
		return avatarArthur;
	}
	public Avatar getLancelot() {
		
		return avatarLancelot;
	}
	public Avatar getRobin() {
		
		return avatarRobin;
	}
	public Avatar getGalahad() {
		
		return avatarGalahad;
	}
	public Avatar getGuard() {
		
		return avatarGuard;
	}
	public Gorge getGorge() {
		return gorge;
	}
	@Tags("KnightArea")
	public Shape getKnightArea() {
		return knightArea;
	}
	@Tags("GuardArea")
	public Shape getGuardArea() {
		return guardArea;
	}
	@Visible(true)
	public boolean getOccupied() {
		return occupied;
	}
	@Visible(true)
	public boolean getKnightTurn() {
		if (getOccupied() && guardSpoken) {
			return true;
		}
		else { return false; }
	}
	@Tags("approach")
	public void approach(Avatar newAvatar) {
		assert preApproach();
		currAvatar = newAvatar;
		currAvatar.setLocation(knightAreaStandingLocation);
		occupied = true;
		listenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "approach", false));
		listenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "say", true));
		listenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "failed", true));
		listenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "passed", true));
	}
	@Tags({"say"})
	public void say(String text) {
		assert preSay();
		if (getOccupied() && !getKnightTurn()){
			currAvatar.getText().setText("");
			avatarGuard.getText().setText(text);
			guardSpoken = true;
			if (getOccupied()) {
				listenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "passed", true));
			}
		}
		else if (getOccupied()) { 
			avatarGuard.getText().setText("");
			currAvatar.getText().setText(text);
			guardSpoken = false;
			listenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "passed", false));
		}
	}
	@Tags("passed")
	public void passed() {
		assert prePassed();
		currAvatar.setLocation(passedLocation.getRelativeLocation(personalSpace, 0));
		personalSpace += personalSpaceIncrement;
		occupied = false;
		listenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "approach", true));
		listenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "say", false));
		listenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "failed", false));
		listenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "passed", false));
	}
	@Tags("failed")
	public void failed() {
		assert preFailed();
		if (getOccupied() && !getKnightTurn()) {
			currAvatar.setLocation(currAvatar.getLocation().getRelativeLocation(0, failedYOffset));
			occupied = false;
			listenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "approach", true));
			listenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "say", false));
			listenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "failed", false));
			listenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "passed", false));
		}
		else if (getOccupied() && getKnightTurn()) {
			avatarGuard.setLocation(currAvatar.getLocation().getRelativeLocation(0, failedYOffset));
			listenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "say", false));
			listenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "failed", false));
			listenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "passed", false));
		}
	}
	@Tags("scroll")
	public void scroll(int x, int y) {
		knightArea.setLocation(knightArea.getLocation().getRelativeLocation(x,y));
		guardArea.setLocation(guardArea.getLocation().getRelativeLocation(x,y));
		gorge.setLocation(gorge.getLocation().getRelativeLocation(x, y));
		knightAreaStandingLocation.setLocation(knightAreaStandingLocation.getRelativeLocation(x, y));
		guardAreaStandingLocation.setLocation(guardAreaStandingLocation.getRelativeLocation(x, y));
		passedLocation.setLocation(passedLocation.getRelativeLocation(x, y));
		avatarArthur.setLocation(avatarArthur.getLocation().getRelativeLocation(x, y));
		avatarRobin.setLocation(avatarRobin.getLocation().getRelativeLocation(x, y));
		avatarLancelot.setLocation(avatarLancelot.getLocation().getRelativeLocation(x, y));
		avatarGalahad.setLocation(avatarGalahad.getLocation().getRelativeLocation(x, y));
		avatarGuard.setLocation(avatarGuard.getLocation().getRelativeLocation(x, y));
	}
	public void resetLocation() {
		avatarArthur.setLocation(aLoc1);
		avatarRobin.setLocation(aLoc2);
		avatarLancelot.setLocation(aLoc3);
		avatarGalahad.setLocation(aLoc4);
	}
	public boolean preApproach() {
		return !getOccupied();
	}
	public boolean preSay() {
		return getOccupied();
	}
	public boolean prePassed() {
		return getOccupied() && getKnightTurn();
	}
	public boolean preFailed() {
		return getOccupied();
	}
	public void addPropertyChangeListener(PropertyChangeListener listener)
	{
		listenerSupport.addElement(listener);
	}
}
