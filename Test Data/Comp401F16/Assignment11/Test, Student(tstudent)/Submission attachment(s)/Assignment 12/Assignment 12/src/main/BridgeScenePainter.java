package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeListener;
import mp.BridgeScene;
import mp.ImageShape;
import mp.Line;
import mp.OvalShape;
import mp.StringShape;

public interface BridgeScenePainter extends PropertyChangeListener{
	public void paint(Graphics g);
	public void draw (Graphics2D g, BridgeScene aBridgeScene);
	public void draw (Graphics2D g, Line lineShape);
	public void draw (Graphics2D g, ImageShape anImage);
	public void draw (Graphics g, StringShape text);
	public void draw (Graphics2D g, OvalShape oval);
	
}
