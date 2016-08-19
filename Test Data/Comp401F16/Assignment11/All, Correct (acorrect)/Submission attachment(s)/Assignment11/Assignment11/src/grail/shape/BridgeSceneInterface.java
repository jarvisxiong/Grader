package grail.shape;

import java.beans.PropertyChangeListener;

import util.annotations.Tags;
import util.models.PropertyListenerRegisterer;

public interface BridgeSceneInterface extends PropertyChangeListener,PropertyListenerRegisterer{
	public AvatarInterface getArthur();
	public AvatarInterface getLancelot();
	public AvatarInterface getRobin();
	public AvatarInterface getGalahad();
	public AvatarInterface getGuard();
	public void approachScene(AvatarInterface person);
	public void sayScene(String word);
	public void passScene();
	public void failScene();
	public void scrollScene(int dx,int dy);
	public void addPropertyChangeListener(PropertyChangeListener listener);
	public ParallelLinesInterface getGorge();
	public ParallelLinesInterface getBridge();
	public Shape getKnightArea();
	public Shape getGuardArea();
	public boolean preApproach();
	public boolean preSay();
	public boolean prePass();
	public boolean preFail();
}
