package graphics.view;

import java.beans.PropertyChangeListener;

import util.APropertyChangeListenerManager;
import util.models.PropertyListenerRegisterer;

public class ProgressMonitorController implements PropertyListenerRegisterer {
	private int currentStep, totalSteps;
	
	private final APropertyChangeListenerManager pclm = new APropertyChangeListenerManager(this);
	
	public ProgressMonitorController(int steps) {
		currentStep = 0;
		totalSteps = steps;
	}

	public void stepComplete() {
		if (currentStep < totalSteps) {
			pclm.firePropertyChange("Step", currentStep ++, currentStep);
		}
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pclm.addPropertyChangeListener(listener);
	}
}
