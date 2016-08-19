package mp.ui.mvc;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;

import mp.ui.AnOval;
import mp.ui.Bridge;
import mp.ui.BridgeScene;
import mp.ui.Gorge;
import mp.ui.RotatingLine;
import mp.ui.Shape;
import util.annotations.Tags;

@Tags("PaintListener")

public class ABackgroundPainter extends Component implements PaintListener {
	
	BridgeScene scene;
	ObservableBridgeScenePainter painter;
	
	public ABackgroundPainter(BridgeScene bridgescene, ObservableBridgeScenePainter initPainter) {
		scene = bridgescene;	
		painter = initPainter;
		painter.addPaintListener(this);	
	}

	public void paint(Graphics2D g2) {
		draw(g2, scene);
	}
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		paint(g2);
	}
	public static void draw(Graphics g, BridgeScene scene) {
		draw(g, scene.getGorge());
		draw(g, scene.getGuardArea());
		draw(g, scene.getKnightArea());
	}
	public static void draw(Graphics g, Gorge gorge)
	{
		draw(g,gorge.getLeftCliff());
		draw(g,gorge.getRightCliff());
		draw(g,gorge.getBridge());
	}
	public static void draw(Graphics g, Bridge bridge) {
		draw(g, bridge.getHorizontalLines());
		draw(g, bridge.getVerticalLines());
	}
	public static void draw(Graphics g, RotatingLine line)
	{
		g.drawLine(line.getX(),line.getY(),line.getX()+line.getWidth(),line.getY()+line.getHeight());
	}
	public static void draw(Graphics g, RotatingLine[] lines){
		for (int i = 0; i<lines.length; i++) {
			draw(g, lines[i]);
		}
	}
	public static void draw(Graphics g, Shape oval)
	{
		g.drawOval(oval.getX(),oval.getY(), oval.getWidth(), oval.getHeight());
	}
//	public void propertyChange(PropertyChangeEvent evt) {
//		System.out.println(evt);
//		painter.propertyChange(evt);
//	}

}
