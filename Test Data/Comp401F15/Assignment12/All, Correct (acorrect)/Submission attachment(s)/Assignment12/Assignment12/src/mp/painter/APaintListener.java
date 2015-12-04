package mp.painter;

import java.awt.Component;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;

import util.annotations.Tags;

@Tags({"PaintListener"})
public class APaintListener implements PaintListener{
	protected Component painter;
	
	@Override
	public void paint(Graphics2D g) {
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		painter.repaint();
	}

	@Override
	public void setScenePainter(Component painter) {
		this.painter = painter;
	}

}
