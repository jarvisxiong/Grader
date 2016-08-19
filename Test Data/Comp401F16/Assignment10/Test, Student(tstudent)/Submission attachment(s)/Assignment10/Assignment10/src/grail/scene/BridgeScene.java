package grail.scene;

import grail.avatar.Avatar;
import grail.draw.interfaces.BridgeMoat;
import grail.draw.interfaces.Platform;
import grail.helpers.Table;
import util.models.PropertyListenerRegisterer;

public interface BridgeScene extends PropertyListenerRegisterer{
	public Avatar getArthur();
	public Avatar getLancelot();
	public Avatar getRobin();
	public Avatar getGalahad();
	public Avatar getGuard();
	public BridgeMoat getGorge();
	public Platform getKnightArea();
	public Platform getGuardArea();
	public void scroll(int x , int y);
	public boolean getOccupied();
	public void approach(Avatar avatarToApproach);
	public void say(String lineOfDialogue);
	public void passed();
	public void failed();
	public boolean getKnightTurn();
	public Table getKnightList();
	public void setOriginalPositions();
	public boolean prePassed();
	public boolean preFailed();
	public boolean preSay();
	public boolean preApproach();
}
