package mp.helpers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import main.BridgeScene;
import util.annotations.Tags;

@Tags({"ConsoleSceneView"})
public class AConsoleSceneView implements PropertyChangeListener{
	
	public AConsoleSceneView(BridgeScene scene){
		scene.addPropertyChangeListener(this);
		
		scene.getArthur().getArms().getLeftLine().addPropertyChangeListener(this);
		scene.getArthur().getArms().getRightLine().addPropertyChangeListener(this);
		scene.getArthur().getHead().addPropertyChangeListener(this);
		scene.getArthur().getLegs().getLeftLine().addPropertyChangeListener(this);
		scene.getArthur().getLegs().getRightLine().addPropertyChangeListener(this);
		scene.getArthur().getText().addPropertyChangeListener(this);
		scene.getArthur().getTorso().addPropertyChangeListener(this);
		
		scene.getLancelot().getArms().getLeftLine().addPropertyChangeListener(this);
		scene.getLancelot().getArms().getRightLine().addPropertyChangeListener(this);
		scene.getLancelot().getHead().addPropertyChangeListener(this);
		scene.getLancelot().getLegs().getLeftLine().addPropertyChangeListener(this);
		scene.getLancelot().getLegs().getRightLine().addPropertyChangeListener(this);
		scene.getLancelot().getText().addPropertyChangeListener(this);
		scene.getLancelot().getTorso().addPropertyChangeListener(this);
		
		scene.getRobin().getArms().getLeftLine().addPropertyChangeListener(this);
		scene.getRobin().getArms().getRightLine().addPropertyChangeListener(this);
		scene.getRobin().getHead().addPropertyChangeListener(this);
		scene.getRobin().getLegs().getLeftLine().addPropertyChangeListener(this);
		scene.getRobin().getLegs().getRightLine().addPropertyChangeListener(this);
		scene.getRobin().getText().addPropertyChangeListener(this);
		scene.getRobin().getTorso().addPropertyChangeListener(this);
		
		scene.getGalahad().getArms().getLeftLine().addPropertyChangeListener(this);
		scene.getGalahad().getArms().getRightLine().addPropertyChangeListener(this);
		scene.getGalahad().getHead().addPropertyChangeListener(this);
		scene.getGalahad().getLegs().getLeftLine().addPropertyChangeListener(this);
		scene.getGalahad().getLegs().getRightLine().addPropertyChangeListener(this);
		scene.getGalahad().getText().addPropertyChangeListener(this);
		scene.getGalahad().getTorso().addPropertyChangeListener(this);
		
		scene.getGuard().getArms().getLeftLine().addPropertyChangeListener(this);
		scene.getGuard().getArms().getRightLine().addPropertyChangeListener(this);
		scene.getGuard().getHead().addPropertyChangeListener(this);
		scene.getGuard().getLegs().getLeftLine().addPropertyChangeListener(this);
		scene.getGuard().getLegs().getRightLine().addPropertyChangeListener(this);
		scene.getGuard().getText().addPropertyChangeListener(this);
		scene.getGuard().getTorso().addPropertyChangeListener(this);
		
		scene.getGorge().getBridgeBottom().addPropertyChangeListener(this);
		scene.getGorge().getBridgeTop().addPropertyChangeListener(this);
		scene.getGorge().getLeftLine().addPropertyChangeListener(this);
		scene.getGorge().getRightLine().addPropertyChangeListener(this);
		
		scene.getGuardArea().addPropertyChangeListener(this);
		scene.getKnightArea().addPropertyChangeListener(this);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println(evt);
	}

}
