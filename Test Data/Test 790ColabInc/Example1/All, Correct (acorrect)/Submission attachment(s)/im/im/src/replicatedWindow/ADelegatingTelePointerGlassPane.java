package replicatedWindow;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import bus.uigen.widgets.Painter;
import util.awt.AGlassPaneRedispatcher;
import util.awt.GraphicsPainter;

public class ADelegatingTelePointerGlassPane extends JPanel implements DelegatingTelepointerGlassPane{
	public static final int DIAMETER = 10;
	int x = 50;
	int y = 50;
	int clickX, clickY;
	boolean pointerSelected;
	JFrame frame;
	protected int pointerWidth = DIAMETER, pointerHeight = DIAMETER;
	protected List<GraphicsPainter> painters = new ArrayList();
	
	
	public ADelegatingTelePointerGlassPane(JFrame aFrame) {
		frame = aFrame;
		this.setFocusable(false);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
//		this.addKeyListener(this);
////		this.addKeyListener(this);
//		long eventMask = AWTEvent.MOUSE_MOTION_EVENT_MASK
//			    + AWTEvent.MOUSE_EVENT_MASK
//			    + AWTEvent.KEY_EVENT_MASK;
//		Toolkit.getDefaultToolkit().addAWTEventListener( this, eventMask);
		this.setOpaque(false);
		AGlassPaneRedispatcher redispatcher = new AGlassPaneRedispatcher(this, aFrame);
		aFrame.setGlassPane(this);
		this.setVisible(true);
	}

	public void paint(Graphics g) {
		super.paint(g);
//		g.setColor(Color.RED);
//		g.fillOval(x, y, DIAMETER, DIAMETER);
		notifyPainters(g);
	}
	@Override
	public int getPointerX() {
		return x;
	}
	@Override
	public void setPointerX(int newVal) {
		x = newVal;
	}
	@Override
	public int getPointerY() {
		return y;
	}
	@Override

	public void setPointerY(int newVal) {
		y = newVal;
	}
	@Override
	public int getPointerWidth() {
		return pointerWidth;
	}
	@Override
	public void setPointerWidth(int aWidth) {
		pointerWidth = aWidth;
	}
	@Override
	public int getPointerHeight() {
		return pointerHeight;
	}
	@Override
	public void setPointerHeight(int aHeight) {
		pointerHeight = aHeight;
	}
	 
	boolean inTelePointer (MouseEvent event) {
		return event.getX() >= x && event.getX() <= (event.getX() + getPointerWidth()) &&
				event.getY() >= y && event.getY() <= (event.getY() + getPointerHeight());
	}
	@Override
	 public void eventDispatched(AWTEvent event) { 
		System.out.println("foo");
		  if (event instanceof MouseEvent) { 
	            MouseEvent me = (MouseEvent) event; 
	            if (!SwingUtilities.isDescendingFrom(me.getComponent(), frame)) { 
	                return; 
	            } 
	           
	            if (me.getID() == MouseEvent.MOUSE_EXITED && me.getComponent() == frame) { 
	                x = -1; 
	                y = -1;
	            } else { 
	                MouseEvent converted = SwingUtilities.convertMouseEvent(me.getComponent(), me, frame.getGlassPane()); 
	                Point point = converted.getPoint(); 
	                x = point.x;
	                y = point.y;
	            } 
	            repaint(); 
	        } 
	    } 
	
	@Override
	protected void processEvent (AWTEvent anEvent) {
		super.processEvent(anEvent);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if (inTelePointer (arg0)) {
			pointerSelected = true;
			clickX = arg0.getX();
			clickY = arg0.getY();
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		pointerSelected = false;
		
	}
	@Override
	public void mouseDragged(MouseEvent event) {
		if (!pointerSelected) return;
		int incX = event.getX() - clickX;
		int incY = event.getY() - clickY;
		clickX = event.getX();
		clickY = event.getY();
		x += incX;
		y += incY;
		repaint();
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
//	@Override
//	public void keyPressed(KeyEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void keyReleased(KeyEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void keyTyped(KeyEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		System.out.println("Key pressed in relepointer");
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPainter(GraphicsPainter aPainter) {
		painters.add(aPainter);
		
	}

	@Override
	public void removePainter(GraphicsPainter aPainter) {
		painters.remove(aPainter);
		
	}
	
	protected void notifyPainters(Graphics g) {
		for (GraphicsPainter aPainter:painters) {
			aPainter.paint(g);
			
		}
	}
	    

}
