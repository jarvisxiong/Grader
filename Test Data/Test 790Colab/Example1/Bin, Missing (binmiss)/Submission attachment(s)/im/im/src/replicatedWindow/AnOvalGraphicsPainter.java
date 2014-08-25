package replicatedWindow;

import java.awt.Color;
import java.awt.Graphics;

import util.awt.GraphicsPainter;

public class AnOvalGraphicsPainter implements GraphicsPainter {
	DelegatingTelepointerGlassPane glassPane;
	public AnOvalGraphicsPainter(DelegatingTelepointerGlassPane aGlassPane) {
		glassPane = aGlassPane;		
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(glassPane.getPointerX(), 
				glassPane.getPointerY(), 
				glassPane.getPointerWidth(), glassPane.getPointerHeight());
	}

}
