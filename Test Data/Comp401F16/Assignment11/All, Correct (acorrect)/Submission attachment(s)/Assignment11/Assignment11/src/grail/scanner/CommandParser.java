package grail.scanner;

public interface CommandParser {
	public void setCommandText(String line);
	public String getCommandText();
	public Runnable getCommandObject();
	public String getErrors();
}
