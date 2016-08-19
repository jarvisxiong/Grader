package grail.view;

import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import grail.draw.interfaces.Platform;
import grail.draw.objects.PlatformOvalShape;
import grail.paint.ObservablePainter;

public class APlatformView implements PlatformView{
	Platform object;
	ObservablePainter aModel;
	
	public APlatformView(Platform object , ObservablePainter model) {
		this.aModel = model;
		this.object = object;
		this.object.addPropertyChangeListener(this);
		PlatformOvalShape.addListenersToSubshapes(this.object, this);
		
	}



	public void propertyChange(PropertyChangeEvent evt) {
		this.aModel.repaint();	
	}



	@Override
	public void paint(Graphics2D g) {
		g.setPaint(this.object.getShadow().getColor());
		g.fillOval(this.object.getShadow().getX(), this.object.getShadow().getY(), this.object.getShadow().getWidth(), this.object.getShadow().getHeight());
		g.setPaint(this.object.getPlatform().getColor());
		g.fillOval(this.object.getPlatform().getX(), this.object.getPlatform().getY(), this.object.getPlatform().getWidth(), this.object.getPlatform().getHeight());
	}
	
}
