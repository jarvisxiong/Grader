package mp.painter;

import java.awt.Component;
import java.awt.Graphics2D;
import java.beans.PropertyChangeListener;

public interface PaintListener extends PropertyChangeListener{
	public void paint(Graphics2D g);
	public void setScenePainter(Component painter);
}
