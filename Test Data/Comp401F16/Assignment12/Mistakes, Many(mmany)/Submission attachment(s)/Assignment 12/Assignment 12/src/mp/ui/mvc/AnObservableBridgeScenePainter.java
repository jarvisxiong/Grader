package mp.ui.mvc;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mp.bean.ATable;
import mp.bean.Table;
import mp.ui.Avatar;
import mp.ui.BridgeScene;
import util.annotations.Tags;

@Tags("ObservableBridgeScenePainter")

public class AnObservableBridgeScenePainter extends Component implements ObservableBridgeScenePainter {
	
	PaintListenerSupport paintListenerSupport = new APaintListenerSupport();
	BridgeScene bridgescene;
	Table avatars;
	
	public AnObservableBridgeScenePainter(BridgeScene scene) {
		bridgescene = scene;
		avatars = new ATable();
		avatars.put("0", bridgescene.getArthur());
		avatars.put("1", bridgescene.getGalahad());
		avatars.put("2", bridgescene.getLancelot());
		avatars.put("3", bridgescene.getRobin());
		avatars.put("4", bridgescene.getGuard());
		
		for (int i = 0; i < avatars.getSize(); i++) {
			((Avatar) avatars.get("" + i)).addPropertyChangeListener(this);
			((Avatar) avatars.get("" + i)).getArms().getLeftLine().addPropertyChangeListener(this);
			((Avatar) avatars.get("" + i)).getArms().getRightLine().addPropertyChangeListener(this);
			((Avatar) avatars.get("" + i)).getLegs().getLeftLine().addPropertyChangeListener(this);
			((Avatar) avatars.get("" + i)).getLegs().getRightLine().addPropertyChangeListener(this);
			((Avatar) avatars.get("" + i)).getBody().addPropertyChangeListener(this);
			((Avatar) avatars.get("" + i)).getHead().addPropertyChangeListener(this);
			((Avatar) avatars.get("" + i)).getText().addPropertyChangeListener(this);
		} 
	}
	
	@Tags("addPaintListener")
	public void addPaintListener(PaintListener l) {
		paintListenerSupport.addElement(l);
	}
	public void propertyChange(PropertyChangeEvent evt) {
		repaint();
	}
	public void paint(Graphics g) {
		super.paint(g);
		paintListenerSupport.repaintAll(g);
	}

}
