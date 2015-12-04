package mp;

import util.annotations.Tags;
import util.models.PropertyListenerRegisterer;
@Tags({"BridgeScene"})

public interface BridgeScene extends PropertyListenerRegisterer{
	public Avatar getArthur();
	public Avatar getGalahad();
	public Avatar getGuard();
	public Avatar getRobin();
	public Avatar getLancelot();
	public GorgeScene getGorge();
	public OvalShape getGuardArea();
	public OvalShape getKnightArea();
	public void passed();
	public void failed();
	public void sayString(String newString);
	public Avatar whoOccupies();
	public void setKnightTurn();
	public void setGuardTurn();
	public boolean getOccupied();
	public boolean getKnightTurn();
	public void approach (Avatar specifiedAvatar);
}
