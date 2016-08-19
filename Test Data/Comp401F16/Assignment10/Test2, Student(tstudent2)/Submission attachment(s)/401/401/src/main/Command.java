package main;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
import util.annotations.Visible;

@Tags({"CommandInterpreter"})

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public interface Command {
	
	public String scan(String[] input);
	@Visible(false)
	public String[] getCommand();
	public void setCommand(String input);
	public Thread setMoveT(String output, int moveX, int moveY);
	public Thread setSay();
	public void setMove(String output, int moveX, int moveY);
	public void asynArthur();
	public void asynGalahad();
	public void asynLancelot();
	public void asynRobin();
	
}
