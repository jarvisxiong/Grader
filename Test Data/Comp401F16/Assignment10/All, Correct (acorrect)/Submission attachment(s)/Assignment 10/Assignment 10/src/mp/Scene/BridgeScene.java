package mp.Scene;

import java.beans.PropertyChangeListener;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.models.PropertyListenerRegisterer;

@StructurePattern(StructurePatternNames.BEAN_PATTERN) 
public interface BridgeScene extends PropertyListenerRegisterer{
	public Avatar getArthur();
	public Avatar getGalahad();
	public Avatar getGuard();
	public Avatar getLancelot();
	public Avatar getRobin();
	public void sayScene(String saying);
	public boolean getKnightTurn();
	public void passedScene();
	public void failedScene();
	public void approach(Avatar goo);
	public boolean getOccupied();
	public BoundedShape getKnightArea();
	public BoundedShape getGuardArea();
	public void pass1();
	public void fail1();
	public Gorge getGorge();
	public void scroll(int deltaX, int deltaY);
	public void addPropertyChangeListener(PropertyChangeListener arg0);
}
