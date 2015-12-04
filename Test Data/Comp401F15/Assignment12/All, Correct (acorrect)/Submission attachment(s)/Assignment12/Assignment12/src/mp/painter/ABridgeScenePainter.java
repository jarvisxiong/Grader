package mp.painter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import util.annotations.Tags;

@Tags({"ObservableBridgeScenePainter"})
public class ABridgeScenePainter extends JPanel implements BridgeScenePainter{
	protected ArrayList<PaintListener> listeners;
	private final int width = 800;
	private final int height = 600;
	
	public ABridgeScenePainter(){
		listeners = new ArrayList<PaintListener>(); 
		setVisible(true);
	}
	
	@Override
	public void paint(Graphics g){
		g.clearRect(0, 0, width, height);
		for(PaintListener listener: listeners){
			listener.paint((Graphics2D)g);
		}
	}
	
	@Tags({"addPaintListener"})
	public void addPaintListener(PaintListener listener){
		listener.setScenePainter(this);
		listeners.add(listener);
	}
}
