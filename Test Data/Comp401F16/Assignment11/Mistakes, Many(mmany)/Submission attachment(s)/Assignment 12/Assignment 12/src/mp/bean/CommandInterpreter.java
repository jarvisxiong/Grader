package mp.bean;

import java.beans.PropertyChangeListener;

import mp.bean.commands.AvatarCommand;
import mp.bean.commands.CoordinatedAnimationCommand;
import mp.tokens.Token;
import mp.ui.Avatar;
import mp.ui.mvc.ACommandInterpreterView;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.Tags;
import util.models.PropertyListenerRegisterer;

@PropertyNames({"Command", "Table", "Errors"})
@EditablePropertyNames({"Command"})

public interface CommandInterpreter extends PropertyChangeListener {
	
	public Table<String, Avatar> getTable();
	public void setCommand(String commandline);
	public String getErrors();
	public void addPropertyChangeListener(PropertyChangeListener listener);
	public void asyncArthur();
	public void asyncGalahad();
	public void asyncLancelot();
	public void asyncRobin();
	public void asyncGuard();
	public void waitingArthur();
	public void waitingGalahad();
	public void waitingLancelot();
	public void waitingRobin();
	public void startAnimation();
	public void lockstepArthur();
	public void lockstepGalahad();
	public void lockstepLancelot();
	public void lockstepRobin();
	public void lockstepGuard();

}
