package grail;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import main.BroadcastingClearanceManager;
import main.ScannerBeanInterface;
import mp.BridgeScene;
import util.annotations.Tags;
import util.annotations.PropertyNames;
import util.annotations.EditablePropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@Tags({"CommandInterpreter"})
@PropertyNames({"Command"})
@EditablePropertyNames({"Command"})

public class ACommandInterpreter implements CommandInterpreter{
	List<PropertyChangeListener> listeners = new ArrayList<PropertyChangeListener>(); //List that stores all Observers
	String command = "initial";
	String oldCommand;
	ExecutableSay says;
	TokenInterface[] tokens;
	BridgeScene aBridgeScene;
	ScannerBeanInterface scannerBean;
	Parser parser;
	final int SLEEP = 10;
	AnimatingCommand arthur;
	AnimatingCommand robin;
	AnimatingCommand galahad;
	AnimatingCommand lancelot;
	AnimatingCommand arthur2;
	AnimatingCommand robin2;
	AnimatingCommand galahad2;
	AnimatingCommand lancelot2;
	BroadcastingClearanceManager manager;
	
	public ACommandInterpreter (BridgeScene newBridgeScene, ScannerBeanInterface newScannerBean, BroadcastingClearanceManager manager2) {
		aBridgeScene = newBridgeScene;
		scannerBean = newScannerBean;
		parser = new AParser(aBridgeScene);
		arthur = new AnAnimatingCommand(aBridgeScene.getArthur());
		robin = new AnAnimatingCommand(aBridgeScene.getRobin());
		galahad = new AnAnimatingCommand(aBridgeScene.getGalahad());
		lancelot = new AnAnimatingCommand(aBridgeScene.getLancelot());
		arthur2 = new AnAnimatingCommand(aBridgeScene.getArthur(),manager2);
		robin2 = new AnAnimatingCommand(aBridgeScene.getRobin(),manager2);
		galahad2 = new AnAnimatingCommand(aBridgeScene.getGalahad(),manager2);
		lancelot2 = new AnAnimatingCommand(aBridgeScene.getLancelot(),manager2);
		manager = manager2;
	}
	
	public void setCommand (String newCommand) {
		oldCommand = command;
		command = newCommand + " ";
		parser.setCommandText(command);
		Runnable temp = parser.parseCommand();
		temp.run();
		PropertyChangeEvent newEvent = new PropertyChangeEvent(this, "Command", oldCommand, command);
		alertListeners(newEvent);
		}
	
	public String getCommand () {return command;}


@Tags({"asynchronousArthuer"})
	public void asynchronousArthur () {
		Thread thread = new Thread(arthur);
		thread.start();
}

@Tags({"asynchronousGalahad"})
	public void asynchronousGalahad () {
		Thread thread = new Thread(galahad);
		thread.start();
	}

@Tags({"asynchronousRobin"})
	public void asynchronousRobin () {
		Thread thread = new Thread(robin);
		thread.start();
	}

@Tags({"asynchronousLancelot"})
	public void asynchronousLancelot () {
		Thread thread = new Thread(lancelot);
		thread.start();
	}

	public void addPropertyChangeListener(PropertyChangeListener aListener) 	{listeners.add(aListener);}
	
	protected void alertListeners(PropertyChangeEvent newEvent) {
		for (int x = 0; x < listeners.size(); x++) {
			listeners.get(x).propertyChange(newEvent);
			}
		}

@Tags({"waitingArthur"})
	public void waitingArthur() {
		Thread thread = new Thread(arthur2);
//		manager.waitForProceed();
		thread.start();
	}
@Tags({"waitingGalahad"})
	public void waitingGalahad() {
		Thread thread = new Thread(galahad2);
//		manager.waitForProceed();
		thread.start();
	}
@Tags({"waitingRobin"})
	public void waitingRobin() {
		Thread thread = new Thread(robin2);
//		manager.waitForProceed();
		thread.start();
	}
@Tags({"waitingLancelot"})
	public void waitingLancelot() {
		Thread thread = new Thread(lancelot2);
//		manager.waitForProceed();
		thread.start();
	}

@Tags({"startAnimation"})
	public void startAnimation() {manager.proceedAll();}

 }
