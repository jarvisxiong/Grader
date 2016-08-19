package grail.commander;
import grail.interfaces.StoreToken;
import util.models.PropertyListenerRegisterer;

public interface CommandInterpreter extends PropertyListenerRegisterer  {
	public String getCommand();
	public void setCommand(String commandLine);
	public String getErrors();
	public Runnable moveCommand(StoreToken[] input);
	public Runnable sayCommand(StoreToken[] input);
	public void asynchronousArthur();
	public void asynchronousGalahad();
	public void asynchronousLancelot();
	public void asynchronousRobin();
	public void asynchronousGuard();
}
