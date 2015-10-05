package grail.graphics;

import util.annotations.Tags;

@Tags({"BridgeScene"})
public interface BridgeScene {
	public Avatar getArthur();
	public Avatar getLancelot();
	public Avatar getGalahad();
	public Avatar getGuard();
	public Avatar getRobin();
	public ImageShape getGorge();
	public void approach(Avatar currentKnight);
	public Oval getGuardArea();
	public Oval getKnightArea();
	public boolean getOccupied();
	public boolean getKnightTurn();
	public void say(String newSpeech);
	public void passed();
	public void failed();
}
