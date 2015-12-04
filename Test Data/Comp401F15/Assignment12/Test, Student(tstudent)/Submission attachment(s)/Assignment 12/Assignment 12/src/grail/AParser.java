package grail;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import main.ATable;
import main.Assignment12;
import main.Table;
import mp.Avatar;
import mp.BridgeScene;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;
import util.annotations.Visible;
@PropertyNames({"CommandText"})
@EditablePropertyNames({"CommandText"})
@Tags({"Parser"})
public class AParser implements Parser{
	int index;
	String command;
	String oldCommand;
	TokenInterface[] tokens;
	Table aTable = new ATable();
	BridgeScene aBridgeScene;
	Runnable runs;
	final int MOVE_TOKEN_SIZE = 4;
	final int SAY_TOKEN_SIZE = 2;
	final int PASSED_TOKEN_SIZE = 1;
	final int FAILED_TOKEN_SIZE = 1;
	final int APPROACH_TOKEN_SIZE = 2;
	List<PropertyChangeListener> listeners = new ArrayList<PropertyChangeListener>(); //List that stores all Observers
	
	
	public AParser (BridgeScene theBridgeScene) {
		aBridgeScene = theBridgeScene;
		fillList();
		index = 0;
	}
	
	public void setCommandText(String commandText) {
		oldCommand = command;
		command = commandText;
		tokens = Assignment12.scanner(commandText);
		index = 0;
//		PropertyChangeEvent newEvent = new PropertyChangeEvent(this, "CommandInterpreter", oldCommand, commandText);
//		alertListeners(newEvent);
		}
	
	public String getCommandText() {return command;}
	
@Visible(false)
	public Runnable parseCommand() {
		TokenInterface[] newTokens = tokens;
		if (newTokens[index] instanceof MoveCommand) {
			Runnable moves = parseMove(newTokens);
			return moves;
		}
		else if (newTokens[index] instanceof SayCommand) {
			Runnable says =parseSay(newTokens);
			return says;
		}
		else if ("failed".equalsIgnoreCase(newTokens[index].getInput())) {
			Runnable failed = parseFail();
			return failed;
		}
		else if ("passed".equalsIgnoreCase(newTokens[index].getInput())) {
			Runnable passed = parsePass();
			index += PASSED_TOKEN_SIZE;
			return passed;
		}
		else if (newTokens[index] instanceof ApproachCommand) {
			Runnable approach = parseApproach(newTokens);
			index += APPROACH_TOKEN_SIZE;
			return approach;
		}
		else if (newTokens[index] instanceof RepeatCommand) {
			Runnable repeat = parseRepeat(newTokens);
			return repeat;
		}
		else if (newTokens[index] instanceof Start) {
			Runnable list = parseCommandList(newTokens);
			return list;
		}
		else {return null;}
	}
	
@Tags({"parseMove"})
	public Runnable parseMove(TokenInterface[] newTokens) {
		Avatar temp1 = (Avatar)aTable.get(newTokens[index+1].getInput().toLowerCase());
		NumberInterface temp2 = (NumberInterface)newTokens[index+2];
		NumberInterface temp3 = (NumberInterface)newTokens[index+3];
		ExecutableMove move = new ExecMoveCommand(temp1, temp2.getValue(), temp3.getValue());
		index += MOVE_TOKEN_SIZE;
		return move;
	}

@Tags({"parseSay"})
	public Runnable parseSay(TokenInterface[] newTokens) {
		QuoteInterface temp = (Quote)newTokens[index+1];
		ExecutableSay say = new ExecSayCommand(aBridgeScene, temp.getValue());
		index += SAY_TOKEN_SIZE;
		return say;
	}

@Tags({"parseFail"})
	public Runnable parseFail() {
		index += FAILED_TOKEN_SIZE;
		return new ExecFailCommand(aBridgeScene);
	}

@Tags({"parsePass"})
	public Runnable parsePass() {
		index += PASSED_TOKEN_SIZE;
		return new ExecPassCommand(aBridgeScene);
	}

@Tags({"parseApproach"})
	public Runnable parseApproach(TokenInterface[] newTokens) {
		Avatar temp = (Avatar)aTable.get(newTokens[index+1].getInput().toLowerCase());
		Runnable approach = new ExecApproachCommand(aBridgeScene, temp);
		index += APPROACH_TOKEN_SIZE;
		return approach;
	}

@Tags({"parseRepeat"})
	public Runnable parseRepeat(TokenInterface[] newTokens) {
		NumberInterface temp = (NumberInterface)newTokens[index+1];
		int reps = temp.getValue();
		TokenInterface[] subarray = subarray(newTokens, 2, newTokens.length-2);
		tokens = subarray;
		Runnable command = parseCommand();
		Runnable repeat = new ExecRepeatCommand(reps, command);
		return repeat;
	}

@Tags({"parseCommandList"}) 
	public Runnable parseCommandList(TokenInterface[] newTokens) {
	
		Runnable list = new ExecCommandList();
		list = repeatingParser(newTokens);
		return list;
	}

	private TokenInterface[] subarray(TokenInterface[] tokens, int start, int size) {
		TokenInterface[] sub = new TokenInterface[size];
		for (int x = 0; x < size; x++) {
			sub[x] = tokens[x+start];
		}
		return sub;
	}

	private Runnable repeatingParser(TokenInterface[] newTokens) {
		
		ExecutableCommandList list = new ExecCommandList();
		TokenInterface[] subarray = subarray(newTokens, 1, newTokens.length-2); 
		
		while(index < subarray.length) {
			
			if (subarray[index] instanceof MoveCommand) {
				list.add(parseMove(subarray));
			}
			
			else if (subarray[index] instanceof SayCommand) {
				list.add(parseSay(subarray));
			}
			
			else if ("failed".equalsIgnoreCase(newTokens[index].getInput())) {
				list.add(parseFail());
			}
			
			else if ("passed".equalsIgnoreCase(newTokens[index].getInput())) {
				list.add(parsePass());
			}
			
			else if (subarray[index] instanceof ApproachCommand) {
				list.add(parseApproach(subarray));
			}
			
			else if (subarray[index] instanceof RepeatCommand) {
				list.add(parseRepeat(subarray));
			}
			
			else if (subarray[index] instanceof Start) {
				list.add(parseCommandList(subarray));		
			}	
			else if (subarray[index] instanceof End) {break;}
		}
		return list;
	}
		







	private void fillList() {
		aTable.put("arthur", aBridgeScene.getArthur());
		aTable.put("galahad", aBridgeScene.getGalahad());
		aTable.put("robin", aBridgeScene.getRobin());
		aTable.put("guard", aBridgeScene.getGuard());
		aTable.put("lancelot", aBridgeScene.getLancelot());
	}

	public void addPropertyChangeListener(PropertyChangeListener aListener) {listeners.add(aListener);}
	
	protected void alertListeners(PropertyChangeEvent newEvent) {
		for (int x = 0; x < listeners.size(); x++) {
			listeners.get(x).propertyChange(newEvent);
			}
		}
	
}
