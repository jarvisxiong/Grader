package widgets;


import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import util.awt.ADelegateFrame;
import util.awt.ListenableGraphics;
import util.awt.ListenablePainter;

public class ATextField extends ATextFieldInputTracker implements  ListenablePainter, TextField {
	final static int CARAT_LENGTH = 10; 
	int charWidth;
	public ATextField(ADelegateFrame theDelegateFrame) {
		super(theDelegateFrame);
		delegateFrame.addPainter(this);
	}			
	public void paint (ADelegateFrame theDelegateFrame, ListenableGraphics g) {
		Font font = g.getFont();
		metrics = g.getFontMetrics(font);
		//System.out.println(metrics.charWidth('a') + " " + metrics.charWidth('g'));
		g.drawLine(charX, charY, charX, charY - CARAT_LENGTH);
		g.drawString(stringBuffer.toString(), X_OFFSET, Y_OFFSET);
	}
	public void mousePressed(MouseEvent event) {
		super.mousePressed(event);
		delegateFrame.repaint();
	}
	public void keyTyped(KeyEvent event) {
		super.keyTyped(event);
		delegateFrame.repaint();		
	}
	
}
