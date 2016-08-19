package grail.paint;

import java.awt.Graphics2D;

import util.annotations.Tags;

@Tags({"PaintListener"})
public interface PaintListener {
	public void paint(Graphics2D g);
}
