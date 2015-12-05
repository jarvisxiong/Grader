package mp.painter;

import java.awt.Graphics2D;

import mp.shapes.AreaShape;
import mp.shapes.LineShape;

public class AnAreaPaintListener extends APaintListener implements AreaPaintListener{
	protected AreaShape shape;	
	
	public AnAreaPaintListener (AreaShape shape){
		this.shape = shape;
		shape.addPropertyChangeListener(this);
	}
	public void paint(Graphics2D g){
		g.drawOval(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
	}
}
