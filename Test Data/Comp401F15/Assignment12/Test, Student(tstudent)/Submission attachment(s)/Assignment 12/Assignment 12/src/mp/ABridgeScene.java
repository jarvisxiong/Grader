package mp;

import util.annotations.Tags;
import util.annotations.Visible;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Arthur","Galahad","Robin","Lancelot","Guard","Gorge","GuardArea","KnightArea"})
@Tags({"BridgeScene"})
public class ABridgeScene implements BridgeScene{
	List<PropertyChangeListener> listeners = new ArrayList<PropertyChangeListener>(); 
	private boolean knightTurn = false;
	private int sayCounter = 0;
	private final int MAX_QUESTIONS = 6;
	Avatar arthur, galahad, robin, guard, lancelot;
	GorgeScene gorge;
	OvalShape guardArea, knightArea;
	
	public ABridgeScene() {}
	public ABridgeScene (Avatar avatar1, Avatar avatar2, Avatar avatar3, Avatar avatar4, Avatar avatar5, GorgeScene newGorge, OvalShape newGuardArea, OvalShape newKnightArea) {
		arthur = avatar1;
		galahad = avatar2;
		guard = avatar3;
		lancelot = avatar4; 
		robin = avatar5;
		gorge = newGorge;
		guardArea = newGuardArea;
		knightArea = newKnightArea;
		
	}
	
	public Avatar getArthur() {return arthur;}
	public Avatar getGalahad() {return galahad;}
	public Avatar getRobin() {return robin;}
	public Avatar getGuard() {return guard;}
	public Avatar getLancelot() {return lancelot;}
	public GorgeScene getGorge() {return gorge;}
	public OvalShape getGuardArea() {return guardArea;}
	public OvalShape getKnightArea() {return knightArea;}
	
	
	
	
	
@Visible(false)
	public boolean getOccupied() {return arthur.getOccupied() || galahad.getOccupied() || robin.getOccupied() || lancelot.getOccupied();}
@Visible(false)
	public boolean getKnightTurn() {return knightTurn;}
@Visible(false)
	public void setKnightTurn() {knightTurn = true;}
@Visible(false)
	public void setGuardTurn() {knightTurn = false;}
	
	public Avatar whoOccupies() {
		if (arthur.getOccupied()) {return arthur;}
		else if (galahad.getOccupied()) {return galahad;}
		else if (robin.getOccupied()) {return robin;}
		else {return lancelot;}
	}
	
	
	
	
@Tags({"say"})
	public void sayString(String newLine) {
		assert (getOccupied());
		if (sayCounter > (MAX_QUESTIONS-1)) {return;}
		else if (!knightTurn) {
			assert (!knightTurn);
			guard.setText(newLine);
			setKnightTurn();
			sayCounter++;
			PropertyChangeEvent newEvent = new PropertyChangeEvent(this, "this", "knightTurn", true);
			alertListeners(newEvent);
		}
		else if (knightTurn) {
			assert(knightTurn);
			whoOccupies().setText(newLine);
			setGuardTurn();
			sayCounter++;
			PropertyChangeEvent newEvent = new PropertyChangeEvent(this, "this", "knightTurn", false);
			alertListeners(newEvent);
		}
	}

@Tags({"passed"})
	public void passed() {
		assert(getOccupied());
		sayCounter = 0;
		whoOccupies().pass();
		PropertyChangeEvent newEvent = new PropertyChangeEvent(this, "this", "passed", false);
		alertListeners(newEvent);
	}

@Tags({"failed"})
	public void failed() {
		assert(getOccupied());
		sayCounter = 0;
		if (!knightTurn) {whoOccupies().fail();}
		if (knightTurn) {guard.fail();}
		PropertyChangeEvent newEvent = new PropertyChangeEvent(this, "this", "failed", false);
		alertListeners(newEvent);
	}

@Tags({"approach"})
	public void approach(Avatar specifiedAvatar) {
		assert(!getOccupied());
		specifiedAvatar.approach();
		PropertyChangeEvent newEvent = new PropertyChangeEvent(this, "this", "approach", false);
		alertListeners(newEvent);
	}






/*
 * PropertyListenerRegisterer Stuff
 */
public void addPropertyChangeListener (PropertyChangeListener aListener) {listeners.add(aListener);}

protected void alertListeners(PropertyChangeEvent newEvent) {
	for (int x = 0; x < listeners.size(); x++) {
		listeners.get(x).propertyChange(newEvent);
		}
	}
}
