package grail.view;

import grail.shape.BridgeSceneInterface;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeListener;

import shapes.ImageShape;
import grail.shape.BoundedShape;
import grail.shape.Line;
import grail.shape.StandArea;
import grail.shape.Rectangle;
import grail.shape.StringShape;

//it seems there is no need to have SceneView class
public interface SceneView  extends PropertyChangeListener{
	public void paint(Graphics g);
	public void draw(Graphics2D g, BridgeSceneInterface scene);
	//public void draw(Graphics2D g, ImageShape anImage);
	public void draw(Graphics2D g, Line aLine);
	public void draw(Graphics2D g, StandArea anOval);
	public void draw(Graphics2D g, BoundedShape aRectangle);
	public void draw(Graphics2D g, StringShape aStringShape);
}
