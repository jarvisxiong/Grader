package demoAndTest;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JTextArea;

import util.annotations.Column;
import util.annotations.ComponentWidth;
import util.annotations.Label;
import util.annotations.PreferredWidgetClass;
import util.annotations.Row;
import util.annotations.Visible;
import util.misc.AClearanceManager;
import util.misc.ClearanceManager;

public class ADemoAndTestingClearanceManager extends AClearanceManager implements DemoAndTestingClearanceManager{
	
	PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	boolean autoPerformStep = true;
	
	boolean autoProceed;
	long autoPauseTime = 2;
	
	
	String stepDescription = "";
	@Row(3)
//	@Column(0)
	public synchronized void proceed() {
		super.proceed();
	}
	@Visible(false)
	public synchronized void waitForClearance() {
		super.waitForClearance();
	}
	@Row(0)
//	@Column(0)
//	@ComponentWidth(25)
	public boolean isAutoProceed() {
		return autoProceed;
	}
	public void setAutoProceed(boolean newVal) {
		boolean oldValue = this.autoProceed;
		this.autoProceed = newVal;
		propertyChangeSupport.firePropertyChange("AutoProceed", oldValue, newVal);
	}
	@Row(2)
//	@Column(1)
	public long getAutoPauseTime() {
		return autoPauseTime;
	}
	public void setAutoPauseTime(long autoPauseTime) {
		this.autoPauseTime = autoPauseTime;
	}
	@Row(4)
	@PreferredWidgetClass(JTextArea.class)
	@ComponentWidth(380)
	@Label("Next Step Description")
	public String getStepDescription() {
		return stepDescription;
	}
	public void setStepDescription(String newValue) {
		String oldValue = stepDescription;
		this.stepDescription = newValue;
		propertyChangeSupport.firePropertyChange("StepDescription", oldValue, newValue);

	}
	@Row(1)
//	@Column(1)
	public boolean isAutoPerformStep() {
		return autoPerformStep;
	}
	public void setAutoPerformStep(boolean autoPerformStep) {
		this.autoPerformStep = autoPerformStep;
	}
	@Override
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyChangeSupport.addPropertyChangeListener(aListener);
		
	}

}
