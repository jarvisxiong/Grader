package mp.ui;

import java.beans.PropertyChangeListener;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;
import util.annotations.Visible;
import util.models.PropertyListenerRegisterer;

@Tags("BridgeScene")
@PropertyNames({"Arthur", "Lancelot", "Robin", "Galahad", "Guard", "Gorge", "KnightArea", "GuardArea", "KnightTurn", "Occupied"})

public interface BridgeScene extends PropertyListenerRegisterer {

	public Avatar getArthur();
	public Avatar getLancelot();
	public Avatar getRobin();
	public Avatar getGalahad();
	public Avatar getGuard();
	public Gorge getGorge();
	public Shape getKnightArea();
	public Shape getGuardArea();
	public void approach(Avatar arthur);
	public boolean getOccupied();
	public boolean getKnightTurn();
	public void scroll(int i, int j);
	public void say(String text);
	public void passed();
	public void failed();
	public void resetLocation();
	public boolean preApproach();
	public boolean preSay();
	public boolean prePassed();
	public boolean preFailed();
}
