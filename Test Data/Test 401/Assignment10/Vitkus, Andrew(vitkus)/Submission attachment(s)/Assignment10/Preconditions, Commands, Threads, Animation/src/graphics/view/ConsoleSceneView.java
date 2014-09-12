package graphics.view;

import java.beans.PropertyChangeEvent;

import util.annotations.Tags;

@Tags( { "Console Scene View" } )
public class ConsoleSceneView implements IConsoleSceneView {
	
	public ConsoleSceneView(IBridgeScene scene) {
		/*
		 * Scene
		 */
		scene.addPropertyChangeListener(this);
		
		/*
		 * Scene Parts
		 */
		scene.getBridge().addPropertyChangeListener(this);
		scene.getCloseLand().addPropertyChangeListener(this);
		scene.getFarLand().addPropertyChangeListener(this);
		scene.getGuardArea().addPropertyChangeListener(this);
		scene.getKnightArea().addPropertyChangeListener(this);
		
		/*
		 * Arthur
		 */
		scene.getArthur().getArms().getLeftLine().addPropertyChangeListener(this);
		scene.getArthur().getArms().getRightLine().addPropertyChangeListener(this);
		scene.getArthur().getBody().addPropertyChangeListener(this);
		scene.getArthur().getHead().addPropertyChangeListener(this);
		scene.getArthur().getLegs().getLeftLine().addPropertyChangeListener(this);
		scene.getArthur().getLegs().getRightLine().addPropertyChangeListener(this);
		scene.getArthur().getNeck().addPropertyChangeListener(this);
		scene.getArthur().getText().addPropertyChangeListener(this);

		/*
		 * Galahad
		 */
		scene.getGalahad().getArms().getLeftLine().addPropertyChangeListener(this);
		scene.getGalahad().getArms().getRightLine().addPropertyChangeListener(this);
		scene.getGalahad().getBody().addPropertyChangeListener(this);
		scene.getGalahad().getHead().addPropertyChangeListener(this);
		scene.getGalahad().getLegs().getLeftLine().addPropertyChangeListener(this);
		scene.getGalahad().getLegs().getRightLine().addPropertyChangeListener(this);
		scene.getGalahad().getNeck().addPropertyChangeListener(this);
		scene.getGalahad().getText().addPropertyChangeListener(this);
		
		/*
		 * Guard
		 */
		scene.getGuard().getArms().getLeftLine().addPropertyChangeListener(this);
		scene.getGuard().getArms().getRightLine().addPropertyChangeListener(this);
		scene.getGuard().getBody().addPropertyChangeListener(this);
		scene.getGuard().getHead().addPropertyChangeListener(this);
		scene.getGuard().getLegs().getLeftLine().addPropertyChangeListener(this);
		scene.getGuard().getLegs().getRightLine().addPropertyChangeListener(this);
		scene.getGuard().getNeck().addPropertyChangeListener(this);
		scene.getGuard().getText().addPropertyChangeListener(this);
		
		/*
		 * Lanelot
		 */
		scene.getLancelot().getArms().getLeftLine().addPropertyChangeListener(this);
		scene.getLancelot().getArms().getRightLine().addPropertyChangeListener(this);
		scene.getLancelot().getBody().addPropertyChangeListener(this);
		scene.getLancelot().getHead().addPropertyChangeListener(this);
		scene.getLancelot().getLegs().getLeftLine().addPropertyChangeListener(this);
		scene.getLancelot().getLegs().getRightLine().addPropertyChangeListener(this);
		scene.getLancelot().getNeck().addPropertyChangeListener(this);
		scene.getLancelot().getText().addPropertyChangeListener(this);
		
		/*
		 * Robin
		 */
		scene.getRobin().getArms().getLeftLine().addPropertyChangeListener(this);
		scene.getRobin().getArms().getRightLine().addPropertyChangeListener(this);
		scene.getRobin().getBody().addPropertyChangeListener(this);
		scene.getRobin().getHead().addPropertyChangeListener(this);
		scene.getRobin().getLegs().getLeftLine().addPropertyChangeListener(this);
		scene.getRobin().getLegs().getRightLine().addPropertyChangeListener(this);
		scene.getRobin().getNeck().addPropertyChangeListener(this);
		scene.getRobin().getText().addPropertyChangeListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println(evt.getPropertyName() + ": " + evt.getOldValue() + " -> " + evt.getNewValue());
	}

}
