package grail.controllers;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import grail.paint.ObservablePainter;

public interface BridgeController extends MouseListener, KeyListener  {
	public void setModel(ObservablePainter model);
	public ObservablePainter getModel();
}
