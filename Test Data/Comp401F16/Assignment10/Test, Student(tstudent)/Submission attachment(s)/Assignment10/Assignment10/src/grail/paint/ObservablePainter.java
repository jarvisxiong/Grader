package grail.paint;

import java.awt.Graphics;

import util.annotations.Tags;

@Tags({"ObservableBridgeScenePainter"})
public interface ObservablePainter {
	public void addPaintListener(PaintListener listener);
	public void repaint();
	public void paint(Graphics g);
}
