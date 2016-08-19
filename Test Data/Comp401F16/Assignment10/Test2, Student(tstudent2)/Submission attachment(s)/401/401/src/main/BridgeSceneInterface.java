package main;

import java.beans.PropertyChangeListener;

import mp.Avatar;
import mp.Circle;
import mp.Gorge;
import util.models.PropertyListenerRegisterer;


public interface BridgeSceneInterface extends PropertyListenerRegisterer{
	
	public Avatar getArthur();
	public Avatar getGalahad();
	public Avatar getLancelot();
	public Avatar getRobin();
	public Avatar getGuard();
	public Gorge getGorge();
	public Circle getKnightArea();
	public Circle getGuardArea();
	public void approach(Avatar avatar);
	public boolean preApproach();
	public void say(String text);
	public boolean preSay();
	public void passed();
	public boolean prePassed();
	public void failed();
	public boolean preFailed();
	boolean getOccupied();
	boolean getKnightTurn();
	public void addPropertyChangeListener(InheritingBridgeScenePainter inheritingBridgeScenePainter);
	public void addPropertyChangeListener(PropertyChangeListener listener);
	
}
