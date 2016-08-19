package main;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.models.PropertyListenerRegisterer;


@Tags({"ConsoleSceneView"})
public class ConsoleSceneView implements ConsoleSceneViewInter{
	BridgeSceneInterface scene;
	
	public ConsoleSceneView(BridgeSceneInterface window){
		this.scene = window;
		//Arthur------------------------------------------------------------------------------------------------
		/*
		window.getArthur().getText().addPropertyChangeListener(this);
		window.getArthur().getHead().addPropertyChangeListener(this);
		window.getArthur().getArms().getLeftLine().addPropertyChangeListener(this);
		window.getArthur().getArms().getRightLine().addPropertyChangeListener(this);
		window.getArthur().getBody().addPropertyChangeListener(this);
		window.getArthur().getLegs().getLeftLine().addPropertyChangeListener(this);
		window.getArthur().getLegs().getRightLine().addPropertyChangeListener(this);
		
		//Galahad------------------------------------------------------------------------------------------------
		window.getGalahad().getText().addPropertyChangeListener(this);
		window.getGalahad().getHead().addPropertyChangeListener(this);
		window.getGalahad().getArms().getLeftLine().addPropertyChangeListener(this);
		window.getGalahad().getArms().getRightLine().addPropertyChangeListener(this);
		window.getGalahad().getBody().addPropertyChangeListener(this);
		window.getGalahad().getLegs().getLeftLine().addPropertyChangeListener(this);
		window.getGalahad().getLegs().getRightLine().addPropertyChangeListener(this);
		//Lancelot------------------------------------------------------------------------------------------------
		window.getLancelot().getText().addPropertyChangeListener(this);
		window.getLancelot().getHead().addPropertyChangeListener(this);
		window.getLancelot().getArms().getLeftLine().addPropertyChangeListener(this);
		window.getLancelot().getArms().getRightLine().addPropertyChangeListener(this);
		window.getLancelot().getBody().addPropertyChangeListener(this);
		window.getLancelot().getLegs().getLeftLine().addPropertyChangeListener(this);
		window.getLancelot().getLegs().getRightLine().addPropertyChangeListener(this);
		//Robin------------------------------------------------------------------------------------------------
		window.getRobin().getText().addPropertyChangeListener(this);
		window.getRobin().getHead().addPropertyChangeListener(this);
		window.getRobin().getArms().getLeftLine().addPropertyChangeListener(this);
		window.getRobin().getArms().getRightLine().addPropertyChangeListener(this);
		window.getRobin().getBody().addPropertyChangeListener(this);
		window.getRobin().getLegs().getLeftLine().addPropertyChangeListener(this);
		window.getRobin().getLegs().getRightLine().addPropertyChangeListener(this);
		//Guard------------------------------------------------------------------------------------------------
		window.getGuard().getText().addPropertyChangeListener(this);
		window.getGuard().getHead().addPropertyChangeListener(this);
		window.getGuard().getArms().getLeftLine().addPropertyChangeListener(this);
		window.getGuard().getArms().getRightLine().addPropertyChangeListener(this);
		window.getGuard().getBody().addPropertyChangeListener(this);
		window.getGuard().getLegs().getLeftLine().addPropertyChangeListener(this);
		window.getGuard().getLegs().getRightLine().addPropertyChangeListener(this);
		*/
		window.addPropertyChangeListener(this);
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		String current = "";
		if (arg0.getSource() == scene.getArthur().getText())
			current = "Arthur's Text";
		else if (arg0.getSource() == scene.getArthur().getHead())
			current = "Arthur's Head";
		else if (arg0.getSource() == scene.getArthur().getArms().getLeftLine())
			current = "Arthur's Left Arm";
		else if (arg0.getSource() == scene.getArthur().getArms().getRightLine())
			current = "Arthur's Right Arm";
		else if (arg0.getSource() == scene.getArthur().getBody())
			current = "Arthur's Body";
		else if (arg0.getSource() == scene.getArthur().getLegs().getLeftLine())
			current = "Arthur's Left Leg";
		else if (arg0.getSource() == scene.getArthur().getLegs().getRightLine())
			current = "Arthur's Right Leg";
		
		else if (arg0.getSource() == scene.getGalahad().getText())
			current = "Galahad's Text";
		else if (arg0.getSource() == scene.getGalahad().getHead())
			current = "Galahad's Head";
		else if (arg0.getSource() == scene.getGalahad().getArms().getLeftLine())
			current = "Galahad's Left Arm";
		else if (arg0.getSource() == scene.getGalahad().getArms().getRightLine())
			current = "Galahad's Right Arm";
		else if (arg0.getSource() == scene.getGalahad().getBody())
			current = "Galahad's Body";
		else if (arg0.getSource() == scene.getGalahad().getLegs().getLeftLine())
			current = "Galahad's Left Leg";
		else if (arg0.getSource() == scene.getGalahad().getLegs().getRightLine())
			current = "Galahad's Right Leg";
		
		else if (arg0.getSource() == scene.getLancelot().getText())
			current = "Lancelot's Text";
		else if (arg0.getSource() == scene.getLancelot().getHead())
			current = "Lancelot's Head";
		else if (arg0.getSource() == scene.getLancelot().getArms().getLeftLine())
			current = "Lancelot's Left Arm";
		else if (arg0.getSource() == scene.getLancelot().getArms().getRightLine())
			current = "Lancelot's Right Arm";
		else if (arg0.getSource() == scene.getLancelot().getBody())
			current = "Lancelot's Body";
		else if (arg0.getSource() == scene.getLancelot().getLegs().getLeftLine())
			current = "Lancelot's Left Leg";
		else if (arg0.getSource() == scene.getLancelot().getLegs().getRightLine())
			current = "Lancelot's Right Leg";
		
		else if (arg0.getSource() == scene.getRobin().getText())
			current = "Robin's Text";
		else if (arg0.getSource() == scene.getRobin().getHead())
			current = "Robin's Head";
		else if (arg0.getSource() == scene.getRobin().getArms().getLeftLine())
			current = "Robin's Left Arm";
		else if (arg0.getSource() == scene.getRobin().getArms().getRightLine())
			current = "Robin's Right Arm";
		else if (arg0.getSource() == scene.getRobin().getBody())
			current = "Robin's Body";
		else if (arg0.getSource() == scene.getRobin().getLegs().getLeftLine())
			current = "Robin's Left Leg";
		else if (arg0.getSource() == scene.getRobin().getLegs().getRightLine())
			current = "Robin's Right Leg";
		
		else if (arg0.getSource() == scene.getGuard().getText())
			current = "Guard's Text";
		else if (arg0.getSource() == scene.getGuard().getHead())
			current = "Guard's Head";
		else if (arg0.getSource() == scene.getGuard().getArms().getLeftLine())
			current = "Guard's Left Arm";
		else if (arg0.getSource() == scene.getGuard().getArms().getRightLine())
			current = "Guard's Right Arm";
		else if (arg0.getSource() == scene.getGuard().getBody())
			current = "Guard's Body";
		else if (arg0.getSource() == scene.getGuard().getLegs().getLeftLine())
			current = "Guard's Left Leg";
		else if (arg0.getSource() == scene.getGuard().getLegs().getRightLine())
			current = "Guard's Right Leg";
			
		System.out.println("Object: " + current + "\t Property name: "
				+ arg0.getPropertyName() + "\t old value: "
				+ arg0.getOldValue() + "\t new value: " + arg0.getNewValue());
	}
	public void register (PropertyListenerRegisterer aPropertyChangeRegister){
		aPropertyChangeRegister.addPropertyChangeListener(this);        
	}
	
}
