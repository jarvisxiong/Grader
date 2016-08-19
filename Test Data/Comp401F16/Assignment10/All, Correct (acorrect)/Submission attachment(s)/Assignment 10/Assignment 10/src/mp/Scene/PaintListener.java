package mp.Scene;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import util.models.PropertyListenerRegisterer;

public interface PaintListener extends PropertyChangeListener{
	public void paint(Graphics2D g);
	public void draw(Graphics2D g, BridgeScene scene);
	public void draw(Graphics2D g,BoundedShape bs);
	public void draw(Graphics2D g,Gorge aGorge);
	public void draw(Graphics2D g,Avatar ava);
	public void draw(Graphics g, StringShape aLabel);
    public  void draw(Graphics2D g, ImageShape anImage);
    public void propertyChange(PropertyChangeEvent arg0);
    public void register(PropertyListenerRegisterer aChange);
    
}
