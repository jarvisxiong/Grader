package grail;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import util.annotations.Tags;

@Tags({"BridgeSceneController"})
public interface BridgeSceneController extends MouseListener, KeyListener {
public int getX();
public int getY();
public void setX(int x);
public void setY(int y);
}
