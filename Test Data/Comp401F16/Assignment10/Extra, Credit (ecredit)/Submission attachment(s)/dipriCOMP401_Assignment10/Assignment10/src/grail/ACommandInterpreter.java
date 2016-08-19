package grail;

import grail.scene.BodyConstructor;
import grail.scene.MoveCommand;
import grail.scene.SayCommand;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import java.lang.Thread;

@Tags({"CommandInterpreter"})
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"command"})
@EditablePropertyNames({"command"})
public class ACommandInterpreter implements CommandInterpreter{

	BridgeScene bridge;
	final static int BEAN_INPUT_INDEX_3 = 3;
	ScannerInterface scannerBean;
	Table aTable = new ATable();
	String command = "";
	Token[] beanInput;
	public ACommandInterpreter (BridgeScene aBridge, ScannerInterface aScannerBean){
		bridge = aBridge;
		scannerBean = aScannerBean;
		aTable.put("Arthur", bridge.getArthur());
		aTable.put("Robin", bridge.getRobin());
		aTable.put("Galahad", bridge.getGalahad());
		aTable.put("Guard", bridge.getGuard());
		aTable.put("Lancelot", bridge.getLancelot());
	}
	
	public String getCommand(){
		return command;
	}
	public void setCommand(String aCommand){
		command = aCommand;
		scannerBean.setScannedString(aCommand);
		beanInput = scannerBean.getToken();
		
		if (beanInput[0] instanceof Say){
			parseSay().run();
		}
		
		else if (beanInput[0] instanceof Move){
			parseMove().run();
		}

	}
	
	public Runnable parseSay(){
		Runnable say = new SayCommand(bridge, beanInput[1].getInput());
		return say;
	}
	
	public Runnable parseMove(){
		Runnable move = new MoveCommand((BodyInterface)aTable.get(beanInput[1].getInput()), ((ANumber)beanInput[2]).getValue(), ((ANumber)beanInput[BEAN_INPUT_INDEX_3]).getValue());
		return move;
	}
	
	@Tags({"asynchronousArthur"})
	public void animateArthur(){
		Runnable animator = new AnimatingCommand(bridge.getArthur());
		Thread arthurThread = new Thread(animator, "arthur");
		arthurThread.start();
		
	}
	
	@Tags({"asynchronousGalahad"})
	public void animateGalahad(){
		Runnable animator = new AnimatingCommand(bridge.getGalahad());
		Thread arthurThread = new Thread(animator, "galahad");
		arthurThread.start();
	}
	
	@Tags({"asynchronousLancelot"})
	public void animateLancelot(){
		Runnable animator = new AnimatingCommand(bridge.getLancelot());
		Thread arthurThread = new Thread(animator, "lancelot");
		arthurThread.start();
	}
	
	@Tags({"asynchronousRobin"})
	public void animateRobin(){
		Runnable animator = new AnimatingCommand(bridge.getRobin());
		Thread arthurThread = new Thread(animator, "robin");
		arthurThread.start();
	}

}
