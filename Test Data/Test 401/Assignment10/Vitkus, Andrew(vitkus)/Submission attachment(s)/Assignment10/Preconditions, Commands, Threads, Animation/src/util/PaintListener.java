package util;

import java.awt.Graphics2D;

import util.annotations.Tags;

@Tags({ "Paint Listener" })
public interface PaintListener extends java.util.EventListener {
	public void paint(Graphics2D g);
}
