package grail.view;

import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import grail.draw.interfaces.Rectangle;
import grail.paint.ObservablePainter;

public class ABackgroundView implements BackgroundView {
	ObservablePainter aModel;
	Rectangle aBackground;
	
	public ABackgroundView(Rectangle background, ObservablePainter model) {
		this.aModel = model;
		this.aBackground = background;
		this.aBackground.addPropertyChangeListener(this);
	}

	
	public void paint(Graphics2D g) {
		g.setPaint(this.aBackground.getColor());
		g.setBackground(this.aBackground.getColor());
		draw(g,this.aBackground);
	}
	
	public void draw(Graphics2D g, Rectangle background) {
		g.fillRect(background.getX(), background.getY(), background.getWidth(), background.getHeight());
	}


	public void propertyChange(PropertyChangeEvent evt) {
		this.aModel.repaint();
	}

}
