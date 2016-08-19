package mp.ui.mvc;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mp.bean.ATable;
import mp.bean.Table;
import mp.ui.BridgeScene;
import mp.ui.Avatar;

public class ConsoleSceneView implements PropertyChangeListener {
	
	BridgeScene bridgescene;
	Table avatars;
	
	public ConsoleSceneView(BridgeScene scene) {
		bridgescene = scene;
		scene.addPropertyChangeListener(this);
		
		avatars = new ATable();
		avatars.put("0", bridgescene.getArthur());
		avatars.put("1", bridgescene.getGalahad());
		avatars.put("2", bridgescene.getLancelot());
		avatars.put("3", bridgescene.getRobin());
		avatars.put("4", bridgescene.getGuard());
		for (int i = 0; i < avatars.getSize(); i++) {
			((Avatar) avatars.get("" + i)).getArms().getLeftLine().addPropertyChangeListener(this);
			((Avatar) avatars.get("" + i)).getArms().getRightLine().addPropertyChangeListener(this);
			((Avatar) avatars.get("" + i)).getLegs().getLeftLine().addPropertyChangeListener(this);
			((Avatar) avatars.get("" + i)).getLegs().getRightLine().addPropertyChangeListener(this);
			((Avatar) avatars.get("" + i)).getBody().addPropertyChangeListener(this);
			((Avatar) avatars.get("" + i)).getHead().addPropertyChangeListener(this);
			((Avatar) avatars.get("" + i)).getText().addPropertyChangeListener(this);
		} 
	}

	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println(evt);
	}
	
}
