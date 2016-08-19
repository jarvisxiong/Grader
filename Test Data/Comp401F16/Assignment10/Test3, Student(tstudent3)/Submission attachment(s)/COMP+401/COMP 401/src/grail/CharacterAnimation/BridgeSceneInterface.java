package grail.CharacterAnimation;

import java.beans.PropertyChangeListener;

public interface BridgeSceneInterface extends PropertyChangeListener {
	public AvatarInterface getArthur();
	public AvatarInterface getGalahad();
	public AvatarInterface getLancelot();
	public AvatarInterface getRobin();
	public AvatarInterface getGuard();
	public void rotateAvatarArms(int wave);
	//public void move();
	public void scaleAvatar(int scale);
	public void setOccupied();
	public boolean getOccupied();
	public GorgeAndBridgeInterface getSceneDetails();
	public void moveToCircle(AvatarInterface next);
	public void sayScene(String speech);
	public boolean passed();
	public  void failed();
	public void scroll(int xScroll, int yScroll);
	
}
