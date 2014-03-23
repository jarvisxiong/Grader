package grader.settings.folders;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import grader.settings.GraderSettingsModel;
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
		String oldValue = newValue;
		this.startingOnyen = newValue;
		propertyChangeSupport.firePropertyChange("startingOnyen", oldValue, newValue);
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
