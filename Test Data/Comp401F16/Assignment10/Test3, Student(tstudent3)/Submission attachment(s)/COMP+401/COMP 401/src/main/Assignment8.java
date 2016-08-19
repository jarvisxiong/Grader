package main;

import java.beans.PropertyChangeListener;

import util.misc.ThreadSupport;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import grail.CharacterAnimation.BridgeScene;
import grail.CharacterAnimation.BridgeSceneInterface;
import grail.CharacterAnimation.CommandInterpreter;
import grail.CharacterAnimation.CommandInterpreterInt;
import grail.CharacterAnimation.ConsoleSceneView;
import grail.CharacterAnimation.PropertyChangeMonitor;



public class Assignment8 {

	public static void main(String[] args) {
		final int boardSize = 1200;
		final int mod30 = 30;
		final int mod10 = 10;
		final int mod5 = 5;
		final int movementPause = 50;
		final int longPause = 800;
		

		
		BridgeSceneInterface scene = new BridgeScene();
		PropertyChangeListener console = new ConsoleSceneView(scene);
		OEFrame editor = ObjectEditor.edit(scene);
		editor.setSize(boardSize,boardSize);
		editor.hideMainPanel();
		ThreadSupport.sleep(longPause);
		scene.getArthur().getArms().getLeftLine().rotate(mod10);
		ThreadSupport.sleep(longPause);
		scene.getArthur().getArms().getRightLine().rotate(mod10);
		ThreadSupport.sleep(longPause);
		scene.getArthur().getLegs().getLeftLine().rotate(mod10);
		ThreadSupport.sleep(longPause);
		scene.getArthur().getLegs().getRightLine().rotate(mod10);
		ThreadSupport.sleep(longPause);
		scene.getArthur().getArms().getLeftLine().rotate(-mod10);
		ThreadSupport.sleep(longPause);
		scene.getArthur().getArms().getRightLine().rotate(-mod10);
		ThreadSupport.sleep(longPause);
		scene.getArthur().getLegs().getLeftLine().rotate(-mod10);
		ThreadSupport.sleep(longPause);
		scene.getArthur().getLegs().getRightLine().rotate(-mod10);
		ThreadSupport.sleep(longPause);
		
		scene.getRobin().setSpeech("CHARGE!");
		for (int index = 0 ; index <= mod30 ; index++){
			scene.getRobin().move(mod5, mod5);
			ThreadSupport.sleep(movementPause);
		}
		
	}

}
