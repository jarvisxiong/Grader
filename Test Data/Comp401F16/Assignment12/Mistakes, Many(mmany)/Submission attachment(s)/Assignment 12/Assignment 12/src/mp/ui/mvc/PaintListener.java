package mp.ui.mvc;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import util.annotations.Tags;

@Tags("PaintListener")

public interface PaintListener {

	void paint (Graphics2D g);

	void paint(Graphics g);
	
}
