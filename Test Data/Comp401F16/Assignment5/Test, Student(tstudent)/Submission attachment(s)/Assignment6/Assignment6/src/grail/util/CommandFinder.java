/** @author Ian Hoyt-McCullough
 *  @version 9/10/2015
 */

package grail.util;

import grail.tokens.MoveCommand;
import grail.tokens.ApproachCommand;
import grail.tokens.SayCommand;
import grail.tokens.CallCommand;
import grail.tokens.DefineCommand;
import grail.tokens.ProceedCommand;
import grail.tokens.RepeatCommand;
import grail.tokens.RotateLeftArmCommand;
import grail.tokens.RotateRightArmCommand;
import grail.tokens.SleepCommand;
import grail.tokens.ThreadCommand;
import grail.tokens.WaitCommand;
import grail.tokens.UndoCommand;
import grail.tokens.RedoCommand;
import grail.tokens.WordTokenBean;
import grail.tokens.AWordTokenBean;

public class CommandFinder {
	
	public static WordTokenBean findCommand(String newWord) {
		String test = newWord.toLowerCase();
		switch(test) {
			case "move":
				return new MoveCommand(test);
			case "say":
				return new SayCommand(test);
			case "call":
				return new CallCommand(test);
			case "define":
				return new DefineCommand(test);
			case "proceed":
				return new ProceedCommand(test);
			case "repeat":
				return new RepeatCommand(test);
			case "rotateleftarm":
				return new RotateLeftArmCommand(test);
			case "rotaterightarm":
				return new RotateRightArmCommand(test);
			case "sleep":
				return new SleepCommand(test);
			case "thread":
				return new ThreadCommand(test);
			case "wait":
				return new WaitCommand(test);
			case "undo":
				return new UndoCommand(test);
			case "redo":
				return new RedoCommand(test);
			case "approach":
				return new ApproachCommand(test);
			default:
				return new AWordTokenBean(test);
		}
	}
}
