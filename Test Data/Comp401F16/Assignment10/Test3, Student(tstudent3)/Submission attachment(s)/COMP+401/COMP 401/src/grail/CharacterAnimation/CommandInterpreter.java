package grail.CharacterAnimation;

import util.annotations.Tags;
import mp.CommanProcessing.DefaultInterface;
import mp.CommanProcessing.Move;
import mp.CommanProcessing.NumberInterface;
import mp.CommanProcessing.Say;
import mp.CommanProcessing.ScannerBean;
import mp.CommanProcessing.ScannerBeanInterface;
import mp.CommanProcessing.SignInterface;


@Tags({"CommandInterpreter","ErrorResilient"})
public class CommandInterpreter implements CommandInterpreterInt {

	String command;
	ScannerBeanInterface scanner;
	DefaultInterface[] commandArray;
	int commandExecutionIndex = 0;
	TableInt table = new Table();
	BridgeSceneInterface scene;
	String status;
	
	public CommandInterpreter(BridgeSceneInterface newScene){
		scene = newScene;
		table.put("arthur", scene.getArthur());
		table.put("galahad", scene.getGalahad());
		table.put("lancelot", scene.getLancelot());
		table.put("robin", scene.getRobin());
		table.put("guard", scene.getGuard());
		
		
	}
	
	

	public void setCommand(String newCommand) {
		command = newCommand;
		scanner = new ScannerBean();
		scanner.tokenFinder(command);
		commandArray = scanner.getCompactArray();
		status = "";
		

		if (commandArray[0] instanceof Say){
			if (commandArray.length>0){
				scene.sayScene(commandArray[1].getInput());

			}
			else{
				status = "Error: Say command missing text.";
			}
		}
		else if (commandArray[0] instanceof Move){
			if (commandArray.length==2+1+1){
				((AvatarInterface) table.get(commandArray[1].getInput())).move
				(((NumberInterface) commandArray[2]).getValue(),((NumberInterface)
						commandArray[2+1]).getValue());
			
			}
			else {
				status = "Error: Move command missing required info.";
			}
		}
		else {
			status = "Error: Command is not supported or is invalid.";
		}
		

	}

	public String getCommand() {
		return command;
	}
	
	public String getStatus(){
		return status;
	}

}	

