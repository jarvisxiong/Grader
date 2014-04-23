package grader.settings.folders;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import grader.settings.GraderSettingsModel;
import grader.trace.settings.EndOnyenUserChange;
import grader.trace.settings.StartOnyenUserChange;
import util.annotations.Explanation;
import util.annotations.Label;
import util.annotations.Row;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Visible;
import bus.uigen.ObjectEditor;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public class AnOnyenRangeModel implements OnyenRangeModel{	
	public static final String ANONYMOUS = "*******";
	String startingOnyen = "", endingOnyen = "", goToOnyen = "";
	GraderSettingsModel graderSettings;
	PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	public AnOnyenRangeModel(GraderSettingsModel aGraderSettings) {
		graderSettings = aGraderSettings;
	}
	@Visible(false)
	public String getStartingOnyen() {
		return startingOnyen;
	}
    @Row(0)
    @Label("Starting Onyen:")
    public String getDisplayedStartingOnyen() {
    	if (graderSettings.isPrivacyMode())
    	return ANONYMOUS;
    	else return getStartingOnyen();
//		return startingOnyen;
	}

	public void setStartingOnyen(String newValue) {
		String oldValue = startingOnyen;
		this.startingOnyen = newValue;
		propertyChangeSupport.firePropertyChange("startingOnyen", oldValue, newValue);
		if (graderSettings.isGraderStarted() && !oldValue.equals(newValue))
		StartOnyenUserChange.newCase(newValue, graderSettings, this);
	}
	@Visible(false)
	public String getEndingOnyen() {
		return endingOnyen;
	}
	@Row(1)
    @Label("Ending Onyen:")
	public String getDisplayedEndingOnyen() {
		if (graderSettings.isPrivacyMode())
	    	return ANONYMOUS;
	    	else return getEndingOnyen();
	}

	public void setEndingOnyen(String newValue) {
		String oldValue = endingOnyen;
		this.endingOnyen = newValue;
		propertyChangeSupport.firePropertyChange("endingOnyen", oldValue, newValue);
		if (graderSettings.isGraderStarted() &&  !oldValue.equals(newValue))
			EndOnyenUserChange.newCase(newValue, graderSettings, this);

	}
	@Row(2)
	@Override
	@Explanation("The onyen you will start at when you review grades")
	public String getGoToOnyen() {
		return goToOnyen;
	}
    @Override
	public void setGoToOnyen(String goToOnyen) {
		this.goToOnyen = goToOnyen;
	}

	
	@Override
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyChangeSupport.addPropertyChangeListener(aListener);
		
	}
	public static void main (String[] args) {
		AnOnyenRangeModel onyenRangeModel = new AnOnyenRangeModel(null);
		ObjectEditor.edit(onyenRangeModel);
	}
	
	

}
