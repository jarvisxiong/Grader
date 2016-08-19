package mp.commandobjects;

public interface Parser {
	public Runnable parseCommand();
	public SayCommandObject parseSayCommand();
	public MoveCommandObject parseMoveCommand();
	public ApproachCommandObject parseApproachCommand();
	public PassCommandObject parsePassCommand();
	public FailCommandObject parseFailCommand();
	public CommandList parseCommandList();
	public RepeatCommandObject parseRepeatCommand();
	public int parseNumber();
	
	public void setCommandText(String commandText);
	public Runnable getCommandObject();
}
