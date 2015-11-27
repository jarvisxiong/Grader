package grail.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public interface BridgeSceneController extends MouseListener, KeyListener{
	 public void mouseClicked(MouseEvent e);
	 public void keyPressed(KeyEvent e);
}
