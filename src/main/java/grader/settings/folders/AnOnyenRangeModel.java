package grader.settings.folders;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import grader.settings.GraderSettingsModel;
import grader.trace.settings.UserStartOnyenChange;
import util.annotations.Explanation;
import util.annotations.Row;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import bus.uigen.ObjectEditor;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public class AnOnyenRangeModel implements OnyenRangeModel{
	String startingOnyen = "", endingOnyen = "", goToOnyen = "";
	GraderSettingsModel graderSettings;
	PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	public AnOnyenRangeModel(GraderSettingsModel aGraderSettings) {
		graderSettings = aGraderSettings;
	}
    @Row(0)
	public String getStartingOnyen() {
		return startingOnyen;
	}

	public void setStartingOnyen(String newValue) {
		String oldValue = startingOnyen;
		this.startingOnyen = newValue;
		propertyChangeSupport.firePropertyChange("startingOnyen", oldValue, newValue);
		UserStartOnyenChange.newCase(newValue, graderSettings, this);
	}
	@Row(1)
	public String getEndingOnyen() {
		return endingOnyen;
	}

	public void setEndingOnyen(String newValue) {
		String oldValue = endingOnyen;
		this.endingOnyen = newValue;
		propertyChangeSupport.firePropertyChange("endingOnyen", oldValue, newValue);

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
