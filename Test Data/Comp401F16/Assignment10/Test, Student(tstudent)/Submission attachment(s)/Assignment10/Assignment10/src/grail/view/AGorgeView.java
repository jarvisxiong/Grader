package grail.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import grail.draw.interfaces.BridgeMoat;
import grail.draw.interfaces.RotatingShape;
import grail.draw.objects.BasicBridgeMoat;
import grail.paint.ObservablePainter;

public class AGorgeView implements GorgeView{
	ObservablePainter aModel;
	BridgeMoat moat;
	
	public AGorgeView(BridgeMoat moat, ObservablePainter model) {
		this.aModel = model;
		this.moat = moat;
		this.moat.addPropertyChangeListener(this);
		BasicBridgeMoat.addListenerToSubShapes(this.moat, this);
	}

	@Override
	public void paint(Graphics2D g) {
		g.setPaint(Color.BLACK);
		draw(g, this.moat.getBridgeTop());
		draw(g, this.moat.getBridgeBottom());
		draw(g, this.moat.getMoatLeft());
		draw(g, this.moat.getMoatRight());
		draw(g, this.moat.getPlankOne());
		draw(g, this.moat.getPlankTwo());
		draw(g, this.moat.getPlankThree());
		draw(g, this.moat.getPlankFour());
		draw(g, this.moat.getPlankFive());
		draw(g, this.moat.getPlankSix());
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		this.aModel.repaint();
		
	}
	
    public void draw(Graphics2D g, RotatingShape line) {
    	g.drawLine(line.getX(), line.getY(), line.getX() + line.getWidth(), line.getY() + line.getHeight());
    }

}
