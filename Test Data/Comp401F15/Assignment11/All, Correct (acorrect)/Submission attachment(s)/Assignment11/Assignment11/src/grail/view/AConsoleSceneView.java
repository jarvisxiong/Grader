package grail.view;

import grail.shape.BridgeSceneInterface;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import shapes.*;
import util.annotations.Tags;

@Tags({ "ConsoleSceneView" })
public class AConsoleSceneView implements ConsoleSceneView{
	BridgeSceneInterface scene;

	public AConsoleSceneView(BridgeSceneInterface newScene) {
		scene = newScene;
		// Register with scene object
		scene.addPropertyChangeListener(this);
		//Register with Arthur's body parts
		scene.getArthur().getHead().addPropertyChangeListener(this);
		scene.getArthur().getWaist().addPropertyChangeListener(this);
		//scene.getArthur().getWaist().addPropertyChangeListener(this);

		scene.getArthur().getArms().getLeftLine()
				.addPropertyChangeListener(this);
		scene.getArthur().getArms().getRightLine()
				.addPropertyChangeListener(this);
		scene.getArthur().getLegs().getLeftLine()
				.addPropertyChangeListener(this);
		scene.getArthur().getLegs().getRightLine()
				.addPropertyChangeListener(this);
		scene.getArthur().getText().addPropertyChangeListener(this);
		 /* 
		// Register with Robin's body parts
		scene.getRobin().getHead().addPropertyChangeListener(this);
		scene.getRobin().getWaist().addPropertyChangeListener(this);
		scene.getRobin().getArms().getLeftLine()
				.addPropertyChangeListener(this);
		scene.getRobin().getArms().getRightLine()
				.addPropertyChangeListener(this);
		scene.getRobin().getLegs().getLeftLine()
				.addPropertyChangeListener(this);
		scene.getRobin().getLegs().getRightLine()
				.addPropertyChangeListener(this);
		scene.getRobin().getText().addPropertyChangeListener(this);
		// Register with Lancelot's body parts
		scene.getLancelot().getHead().addPropertyChangeListener(this);
		scene.getLancelot().getWaist().addPropertyChangeListener(this);
		scene.getLancelot().getArms().getLeftLine()
				.addPropertyChangeListener(this);
		scene.getLancelot().getArms().getRightLine()
				.addPropertyChangeListener(this);
		scene.getLancelot().getLegs().getLeftLine()
				.addPropertyChangeListener(this);
		scene.getLancelot().getLegs().getRightLine()
				.addPropertyChangeListener(this);
		scene.getLancelot().getText().addPropertyChangeListener(this);
		// Register with Galahad's body parts
		scene.getGalahad().getHead().addPropertyChangeListener(this);
		scene.getGalahad().getWaist().addPropertyChangeListener(this);
		scene.getGalahad().getArms().getLeftLine()
				.addPropertyChangeListener(this);
		scene.getGalahad().getArms().getRightLine()
				.addPropertyChangeListener(this);
		scene.getGalahad().getLegs().getLeftLine()
				.addPropertyChangeListener(this);
		scene.getGalahad().getLegs().getRightLine()
				.addPropertyChangeListener(this);
		scene.getGalahad().getText().addPropertyChangeListener(this);
		// Register with Guard's body parts
		scene.getGuard().getHead().addPropertyChangeListener(this);
		scene.getGuard().getWaist().addPropertyChangeListener(this);
		scene.getGuard().getArms().getLeftLine()
				.addPropertyChangeListener(this);
		scene.getGuard().getArms().getRightLine()
				.addPropertyChangeListener(this);
		scene.getGuard().getLegs().getLeftLine()
				.addPropertyChangeListener(this);
		scene.getGuard().getLegs().getRightLine()
				.addPropertyChangeListener(this);
		scene.getGuard().getText().addPropertyChangeListener(this);
		// Register with other atomic shapes
		scene.getGuardArea().addPropertyChangeListener(this);
		scene.getKnightArea().addPropertyChangeListener(this);
		scene.getGorge().getFirstLine().addPropertyChangeListener(this);
		scene.getGorge().getBridge().addPropertyChangeListener(this);
		scene.getGorge().getSecondLine().addPropertyChangeListener(this);
		scene.getGorge().getLeftEdge().addPropertyChangeListener(this);
		scene.getGorge().getRightEdge().addPropertyChangeListener(this);
		*/
	}

	public void propertyChange(PropertyChangeEvent event) {
		String current = "";
		// Check for scene object
		if (event.getSource() == scene)
			current = "Scene";
		// Check for Arthur's body parts
		else if (event.getSource() == scene.getArthur().getHead())
			current = "Arthur's Head";
		else if (event.getSource() == scene.getArthur().getText())
			current = "Arthur's Text";
		else if (event.getSource() == scene.getArthur().getWaist())
			current = "Arthur's Body";
		else if (event.getSource() == scene.getArthur().getArms().getLeftLine())
			current = "Arthur's Left Arm";
		else if (event.getSource() == scene.getArthur().getArms()
				.getRightLine())
			current = "Arthur's Right Arm";
		else if (event.getSource() == scene.getArthur().getLegs().getLeftLine())
			current = "Arthur's Left Leg";
		else if (event.getSource() == scene.getArthur().getLegs()
				.getRightLine())
			current = "Arthur's Right Leg";
		 /* 
		// Check for Robin's body parts
		else if (event.getSource() == scene.getRobin().getHead())
			current = "Robin's Head";
		else if (event.getSource() == scene.getRobin().getText())
			current = "Robin's Speech";
		else if (event.getSource() == scene.getRobin().getWaist())
			current = "Robin's Body";
		else if (event.getSource() == scene.getRobin().getArms().getLeftLine())
			current = "Robin's Left Arm";
		else if (event.getSource() == scene.getRobin().getArms().getRightLine())
			current = "Robin's Right Arm";
		else if (event.getSource() == scene.getRobin().getLegs().getLeftLine())
			current = "Robin's Left Leg";
		else if (event.getSource() == scene.getRobin().getLegs().getRightLine())
			current = "Robin's Right Leg";
		// Check for Lancelot's body parts
		else if (event.getSource() == scene.getLancelot().getHead())
			current = "Lancelot's Head";
		else if (event.getSource() == scene.getLancelot().getText())
			current = "Lancelot's Speech";
		else if (event.getSource() == scene.getLancelot().getWaist())
			current = "Lancelot's Body";
		else if (event.getSource() == scene.getLancelot().getArms()
				.getLeftLine())
			current = "Lancelot's Left Arm";
		else if (event.getSource() == scene.getLancelot().getArms()
				.getRightLine())
			current = "Lancelot's Right Arm";
		else if (event.getSource() == scene.getLancelot().getLegs()
				.getLeftLine())
			current = "Lancelot's Left Leg";
		else if (event.getSource() == scene.getLancelot().getLegs()
				.getRightLine())
			current = "Lancelot's Right Leg";
		*/
		// Check for Galahad's body parts
		else if (event.getSource() == scene.getGalahad().getHead())
			current = "Galahad's Head";
		else if (event.getSource() == scene.getGalahad().getText())
			current = "Galahad's Speech";
		else if (event.getSource() == scene.getGalahad().getWaist())
			current = "Galahad's Body";
		else if (event.getSource() == scene.getGalahad().getArms()
				.getLeftLine())
			current = "Galahad's Left Arm";
		else if (event.getSource() == scene.getGalahad().getArms()
				.getRightLine())
			current = "Galahad's Right Arm";
		else if (event.getSource() == scene.getGalahad().getLegs()
				.getLeftLine())
			current = "Galahad's Left Leg";
		else if (event.getSource() == scene.getGalahad().getLegs()
				.getRightLine())
			current = "Galahad's Right Leg";
		
		/*
		// Check for Guard's body parts
		else if (event.getSource() == scene.getGuard().getHead())
			current = "Guard's Head";
		else if (event.getSource() == scene.getGuard().getText())
			current = "Guard's Speech";
		else if (event.getSource() == scene.getGuard().getWaist())
			current = "Guard's Body";
		else if (event.getSource() == scene.getGuard().getArms().getLeftLine())
			current = "Guard's Left Arm";
		else if (event.getSource() == scene.getGuard().getArms().getRightLine())
			current = "Guard's Right Arm";
		else if (event.getSource() == scene.getGuard().getLegs().getLeftLine())
			current = "Guard's Left Leg";
		else if (event.getSource() == scene.getGuard().getLegs().getRightLine())
			current = "Guard's Right Leg";
		// Check for other atomic shapes
		else if (event.getSource() == scene.getGuardArea())
			current = "Guard's Platform";
		else if (event.getSource() == scene.getKnightArea())
			current = "Knight's Speech";
		else if (event.getSource() == scene.getGorge().getFirstLine())
			current = "Top Gap of Gorge";
		else if (event.getSource() == scene.getGorge().getBridge())
			current = "Bridge";
		else if (event.getSource() == scene.getGorge().getSecondLine())
			current = "Bottom Gap of Gorge";
		else if (event.getSource() == scene.getGorge().getFirstLine())
			current = "Left Edge of Gorge";
		else if (event.getSource() == scene.getGorge().getSecondLine())
			current = "Right Edge of Gorge";
		else
			current = "Unknown Source, probably ghosts";
		*/
		System.out.println("Object: " + current + "\t Property name: "
				+ event.getPropertyName() + "\t old value: "
				+ event.getOldValue() + "\t new value: " + event.getNewValue());
		System.out.println();
	}
}