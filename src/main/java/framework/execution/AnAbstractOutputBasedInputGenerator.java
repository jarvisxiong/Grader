package framework.execution;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.models.ConsoleModel;

public abstract class AnAbstractOutputBasedInputGenerator implements OutputBasedInputGenerator {
	protected List<ProcessInputListener> processInputListeners = new ArrayList();
	protected Map<String, Boolean> processToTerminatedSucessfully = new HashMap();
//	protected boolean terminatedSuccessfully;

//	@Override
//	public void propertyChange(PropertyChangeEvent evt) {
//		if (evt.getPropertyName().equals(OUTPUT_LINE)) {
//			processNewOutputLine(null, (String) evt.getNewValue());
//		}		
//	}

//	@Override
//	public void addPropertyChangeListener(PropertyChangeListener aListener) {
//		propertyChangeSupport.addPropertyChangeListener(aListener);		
//	}
	
	

	public void notifyNewInput(String aProcessName, String anInput) {
		for (ProcessInputListener aListener:processInputListeners) {
			aListener.newInput(aProcessName, anInput);
		}
	}
	
	public void notifyTermination(String aProcessName) {
		for (ProcessInputListener aListener:processInputListeners) {
			aListener.inputTerminated(aProcessName);
		}
	}

//	@Override
//	public void newOutputLine(String aProcessName, String anOutputLine) {
//		
//	}

	@Override
	public void addProcessInputListener(ProcessInputListener aListener) {
		processInputListeners.add(aListener);
		
	}

	@Override
	public void removeProcessInputListener(ProcessInputListener aListener) {
		processInputListeners.remove(aListener);		
	}
	
	public static boolean getValue(Map<String, Boolean> aMap, String aKey) {
		if (aKey == null)
			aKey = "";
		Boolean retVal = aMap.get(aKey);
		if (retVal == null)
			return false;
		return retVal;
		
	}
	
	public boolean isTerminatedSuccessfully(String aProcess) {
		return getValue(processToTerminatedSucessfully, aProcess);
	}

	public void setTerminatedSuccessfully(String aProcess, boolean terminatedSuccessfully) {
		if (aProcess == null)
			aProcess = "";
		processToTerminatedSucessfully.put(aProcess, terminatedSuccessfully);
	}
	

}
