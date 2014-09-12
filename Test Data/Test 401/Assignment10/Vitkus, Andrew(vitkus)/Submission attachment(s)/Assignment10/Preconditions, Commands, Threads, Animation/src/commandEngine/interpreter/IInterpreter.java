package commandEngine.interpreter;

import bus.uigen.OEFrame;
import util.annotations.Tags;
import util.models.PropertyListenerRegisterer;

@Tags({ "Command Interpreter" })
public interface IInterpreter extends PropertyListenerRegisterer {
	public void setOEFrame(OEFrame frame);
	public OEFrame getOEFrame();
	public void setCommand(String command);
	public String getCommand();
	public String getError();
	public void animateArthur();
	public void animateGalahad();
	public void animateLancelot();
	public void animateRobin();
	public void animateGuard();
}
