package grail;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import util.annotations.Tags;
import util.models.PropertyListenerRegisterer;

@Tags({"BridgeScene"})
public interface BridgeScene extends PropertyListenerRegisterer {
public BodyInterface getArthur();
public BodyInterface getGalahad();
public BodyInterface getRobin();
public BodyInterface getGuard();
public BodyInterface getLancelot();
public Line getRightEdge();
public Line getLeftEdge();
public Line getTopBridge();
public Line getBotBridge();
public StandingArea getGuardArea();
public StandingArea getKnightArea();
public void approachArea(BodyInterface avatar);
public void sayScene(String aString);
public void pass();
public void fail();
public boolean getKnightTurn();
public boolean prePass();
public boolean preFail();
public boolean preApproachArea();
public boolean preSayScene();
}
