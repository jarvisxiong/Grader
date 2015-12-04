package mp.painter;

import java.awt.Graphics2D;

import mp.shapes.Gorge;
import mp.shapes.LineShape;

public class ABackgroundPaintListener extends APaintListener implements BackgroundPaintListener{
	protected Gorge gorge;
	
	public ABackgroundPaintListener(Gorge gorge){
		this.gorge = gorge;
		gorge.getBridgeBottom().addPropertyChangeListener(this);
		gorge.getBridgeTop().addPropertyChangeListener(this);
		gorge.getLeftLine().addPropertyChangeListener(this);
		gorge.getRightLine().addPropertyChangeListener(this);
	}
	public void paint(Graphics2D g){
		LineShape line = gorge.getBridgeBottom();
		g.drawLine(line.getX(), line.getY(), line.getX()+line.getWidth(), line.getY()+line.getHeight());
		line = gorge.getLeftLine();
		g.drawLine(line.getX(), line.getY(), line.getX()+line.getWidth(), line.getY()+line.getHeight());
		line = gorge.getBridgeTop();
		g.drawLine(line.getX(), line.getY(), line.getX()+line.getWidth(), line.getY()+line.getHeight());
		line = gorge.getRightLine();
		g.drawLine(line.getX(), line.getY(), line.getX()+line.getWidth(), line.getY()+line.getHeight());
	}
}
