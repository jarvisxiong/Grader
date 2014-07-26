package framework.execution;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import util.models.ConsoleModel;

public abstract class AnAbstractOutputBasedInputGenerator implements OutputBasedInputGenerator {
	protected List<ProcessInputListener> processInputListeners = new ArrayList();
	protected boolean terminatedSuccessfully;

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


	public boolean isTerminatedSuccessfully() {
		return terminatedSuccessfully;
	}

	public void setTerminatedSuccessfully(boolean terminatedSuccessfully) {
		this.terminatedSuccessfully = terminatedSuccessfully;
	}
	

}
