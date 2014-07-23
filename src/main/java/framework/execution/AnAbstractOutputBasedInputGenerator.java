package framework.execution;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import util.models.ConsoleModel;

public abstract class AnAbstractOutputBasedInputGenerator implements OutputBasedInputGenerator {
	PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(OUTPUT_LINE)) {
			processNewOutputLine((String) evt.getNewValue());
		}		
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyChangeSupport.addPropertyChangeListener(aListener);		
	}
	
	public void generateNewInput(String aNewInput) {
		propertyChangeSupport.firePropertyChange(INPUT, "", aNewInput);
	}


	
	

}
