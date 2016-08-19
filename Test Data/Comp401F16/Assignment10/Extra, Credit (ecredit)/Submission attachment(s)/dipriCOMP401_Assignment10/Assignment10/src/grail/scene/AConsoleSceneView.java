package grail.scene;

import java.beans.PropertyChangeEvent;

import grail.BridgeScene;
import grail.ConsoleSceneView;
import util.annotations.Tags;



@Tags({"ConsoleSceneView"})
public class AConsoleSceneView implements ConsoleSceneView {
BridgeScene bridge;

public AConsoleSceneView(BridgeScene aScene){
	bridge = aScene;
	
	aScene.addPropertyChangeListener(this);
	
//	aScene.getArthur().getAHead().addPropertyChangeListener(this);
//	aScene.getArthur().getArms().getLeftLine().addPropertyChangeListener(this);
//	aScene.getArthur().getArms().getRightLine().addPropertyChangeListener(this);
//	aScene.getArthur().getBody().addPropertyChangeListener(this);
//	aScene.getArthur().getLegs().getLeftLine().addPropertyChangeListener(this);
//	aScene.getArthur().getLegs().getRightLine().addPropertyChangeListener(this);
//	aScene.getArthur().getText().addPropertyChangeListener(this);
//	
//	aScene.getRobin().getAHead().addPropertyChangeListener(this);
//	aScene.getRobin().getArms().getLeftLine().addPropertyChangeListener(this);
//	aScene.getRobin().getArms().getRightLine().addPropertyChangeListener(this);
//	aScene.getRobin().getBody().addPropertyChangeListener(this);
//	aScene.getRobin().getLegs().getLeftLine().addPropertyChangeListener(this);
//	aScene.getRobin().getLegs().getRightLine().addPropertyChangeListener(this);
//	aScene.getRobin().getText().addPropertyChangeListener(this);
//	
//	aScene.getGuard().getAHead().addPropertyChangeListener(this);
//	aScene.getGuard().getArms().getLeftLine().addPropertyChangeListener(this);
//	aScene.getGuard().getArms().getRightLine().addPropertyChangeListener(this);
//	aScene.getGuard().getBody().addPropertyChangeListener(this);
//	aScene.getGuard().getLegs().getLeftLine().addPropertyChangeListener(this);
//	aScene.getGuard().getLegs().getRightLine().addPropertyChangeListener(this);
//	aScene.getGuard().getText().addPropertyChangeListener(this);
//	
//	aScene.getLancelot().getAHead().addPropertyChangeListener(this);
//	aScene.getLancelot().getArms().getLeftLine().addPropertyChangeListener(this);
//	aScene.getLancelot().getArms().getRightLine().addPropertyChangeListener(this);
//	aScene.getLancelot().getBody().addPropertyChangeListener(this);
//	aScene.getLancelot().getLegs().getLeftLine().addPropertyChangeListener(this);
//	aScene.getLancelot().getLegs().getRightLine().addPropertyChangeListener(this);
//	aScene.getLancelot().getText().addPropertyChangeListener(this);
//	
//	aScene.getGalahad().getAHead().addPropertyChangeListener(this);
//	aScene.getGalahad().getArms().getLeftLine().addPropertyChangeListener(this);
//	aScene.getGalahad().getArms().getRightLine().addPropertyChangeListener(this);
//	aScene.getGalahad().getBody().addPropertyChangeListener(this);
//	aScene.getGalahad().getLegs().getLeftLine().addPropertyChangeListener(this);
//	aScene.getGalahad().getLegs().getRightLine().addPropertyChangeListener(this);
//	aScene.getGalahad().getText().addPropertyChangeListener(this);
//	
//	aScene.getBotBridge().addPropertyChangeListener(this);
//	aScene.getTopBridge().addPropertyChangeListener(this);
//	aScene.getGuardArea().addPropertyChangeListener(this);
//	aScene.getKnightArea().addPropertyChangeListener(this);
//	aScene.getLeftEdge().addPropertyChangeListener(this);
//	aScene.getRightEdge().addPropertyChangeListener(this);
}

public void propertyChange(PropertyChangeEvent evt) {
	System.out.println(evt);
}
}
