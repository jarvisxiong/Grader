package grail.CharacterAnimation;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import util.annotations.ObserverRegisterer;
import util.annotations.Tags;

@ObserverRegisterer(value = "2")
@Tags({"ConsoleSceneView"})
public class ConsoleSceneView implements PropertyChangeListener {

	public ConsoleSceneView (BridgeSceneInterface scene){
		scene.getArthur().getArms().getLeftLine().addPropertyChangeListener(this);
		scene.getArthur().getArms().getRightLine().addPropertyChangeListener(this);
		scene.getArthur().getLegs().getLeftLine().addPropertyChangeListener(this);
		scene.getArthur().getLegs().getRightLine().addPropertyChangeListener(this);
		scene.getArthur().getHead().addPropertyChangeListener(this);
		scene.getArthur().getBody().addPropertyChangeListener(this);
		scene.getArthur().getSpeech().addPropertyChangeListener(this);
		
		
		scene.getGalahad().getArms().getLeftLine().addPropertyChangeListener(this);
		scene.getGalahad().getArms().getRightLine().addPropertyChangeListener(this);
		scene.getGalahad().getLegs().getLeftLine().addPropertyChangeListener(this);
		scene.getGalahad().getLegs().getRightLine().addPropertyChangeListener(this);
		scene.getGalahad().getHead().addPropertyChangeListener(this);
		scene.getGalahad().getBody().addPropertyChangeListener(this);
		scene.getGalahad().getSpeech().addPropertyChangeListener(this);
		
		scene.getLancelot().getArms().getLeftLine().addPropertyChangeListener(this);
		scene.getLancelot().getArms().getRightLine().addPropertyChangeListener(this);
		scene.getLancelot().getLegs().getLeftLine().addPropertyChangeListener(this);
		scene.getLancelot().getLegs().getRightLine().addPropertyChangeListener(this);
		scene.getLancelot().getHead().addPropertyChangeListener(this);
		scene.getLancelot().getBody().addPropertyChangeListener(this);
		scene.getLancelot().getSpeech().addPropertyChangeListener(this);
		
		scene.getRobin().getArms().getLeftLine().addPropertyChangeListener(this);
		scene.getRobin().getArms().getRightLine().addPropertyChangeListener(this);
		scene.getRobin().getLegs().getLeftLine().addPropertyChangeListener(this);
		scene.getRobin().getLegs().getRightLine().addPropertyChangeListener(this);
		scene.getRobin().getHead().addPropertyChangeListener(this);
		scene.getRobin().getBody().addPropertyChangeListener(this);
		scene.getRobin().getSpeech().addPropertyChangeListener(this);
		
		scene.getGuard().getArms().getLeftLine().addPropertyChangeListener(this);
		scene.getGuard().getArms().getRightLine().addPropertyChangeListener(this);
		scene.getGuard().getLegs().getLeftLine().addPropertyChangeListener(this);
		scene.getGuard().getLegs().getRightLine().addPropertyChangeListener(this);
		scene.getGuard().getHead().addPropertyChangeListener(this);
		scene.getGuard().getBody().addPropertyChangeListener(this);
		scene.getGuard().getSpeech().addPropertyChangeListener(this);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println(evt);
		
	}

}
