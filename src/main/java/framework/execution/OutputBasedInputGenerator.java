package framework.execution;

import java.beans.PropertyChangeListener;

import util.models.ConsoleModel;
import util.models.PropertyListenerRegisterer;

public interface OutputBasedInputGenerator extends PropertyChangeListener, PropertyListenerRegisterer {
	public final String OUTPUT_LINE = ConsoleModel.OUTPUT_LINE;
	public final String INPUT = "input";
	 void processNewOutputLine(String aNewOutputLine);
	 void generateNewInput(String aNewInput);
	

}
