package grail.commander;
import util.models.PropertyListenerRegisterer;

public interface CommandInterpreter extends PropertyListenerRegisterer  {
	public String getCommand();
	public void setCommand(String commandLine);
	public String getErrors();
	public void asynchronousArthur();
	public void asynchronousGalahad();
	public void asynchronousLancelot();
	public void asynchronousRobin();
	public void asynchronousGuard();
	public void waitingArthur();
	public void waitingGalahad();
	public void waitingLancelot();
	public void waitingRobin();
	public void startAnimation();
	public void lockstepArthur();
	public void lockstepLancelot();
	public void lockstepGalahad();
	public void lockstepRobin();
	public void lockstepGuard();
}
