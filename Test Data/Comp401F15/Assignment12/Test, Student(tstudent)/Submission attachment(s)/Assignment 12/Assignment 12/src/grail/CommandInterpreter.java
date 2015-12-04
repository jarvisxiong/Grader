package grail;

import util.annotations.Tags;
import util.models.PropertyListenerRegisterer;
import util.annotations.PropertyNames;
//import java.beans.PropertyChangeListener;
import util.annotations.EditablePropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@PropertyNames({"Command"})
@EditablePropertyNames({"Command"})
@Tags({"CommandInterpreter"})

public interface CommandInterpreter extends PropertyListenerRegisterer{
	public void setCommand(String newCommand);
	public String getCommand();
	public void asynchronousArthur();
	public void asynchronousGalahad();
	public void asynchronousRobin();
	public void asynchronousLancelot();
	public void waitingArthur();
	public void waitingGalahad();
	public void waitingRobin();
	public void waitingLancelot();
	public void startAnimation();
}