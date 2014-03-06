package grader.settings.navigation;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.swing.JRadioButton;

import util.annotations.Label;
import util.annotations.PreferredWidgetClass;
import util.annotations.Row;
import util.models.ADynamicEnum;
import util.models.DynamicEnum;
import bus.uigen.ObjectEditor;

public class ANavigationSetter implements NavigationSetter {
	NavigationKind navigationKind = NavigationKind.AUTOMATIC_THEN_MANUAL;
	AutomaticNavigationSetter automaticNavigationSetter = new AnAutomaticNavigationSetter();
	DynamicEnum<String> navigationFilterEnum;
	NavigationFilter currentNavigationFilter;
	String currentNavigationFilterName;
	Object navigationParameters;
	PropertyChangeSupport propertyChangeSupport =  new PropertyChangeSupport(this);
	
	
	public ANavigationSetter() {
		constructNavigationFilterTypes();
	}
	@Row(0)
	@PreferredWidgetClass(JRadioButton.class)
	public NavigationKind getNavigationKind() {
		return navigationKind;
	}
	public void setNavigationKind(NavigationKind navigationKind) {
		this.navigationKind = navigationKind;
	}
	@Row(1)
	@Label("Automatic Navigation Parameters")
	public AutomaticNavigationSetter getAutomaticNavigationSetter() {
		return automaticNavigationSetter;
	}
	public void setAutomaticNavigationSetter(
			AutomaticNavigationSetter automaticNavigationSetter) {
		this.automaticNavigationSetter = automaticNavigationSetter;
	}
	
	void constructNavigationFilterTypes () {
		Set<String> filterNames = NavigationFilterManager.filterTypes();
		List<String> filterNamesList = new ArrayList(filterNames);
		Collections.sort(filterNamesList);
		navigationFilterEnum = new ADynamicEnum(filterNamesList);
		navigationFilterEnum.addPropertyChangeListener(this);
		if (filterNamesList.size() > 0) {
		  currentNavigationFilterName =  filterNamesList.get(0);
		  currentNavigationFilter = NavigationFilterManager.getFilterer(currentNavigationFilterName);
		}
	}
	
//	public boolean preGetNavigationFilterTypes() {
//		return  navigationFilterEnum.choicesSize() > 0;
//	}
//	public boolean preGetParameters() {
//		return preGetNavigationFilterTypes() && currentNavigationFilter != null && currentNavigationFilter.getParameters() != null;
//	}
	@Row(2)
	public DynamicEnum getNavigationFilterTypes() {
		return navigationFilterEnum;
	}
	
	
	@Row(3)
	public Object getFilterParameters() {
		return currentNavigationFilter.getParameters();
	}
	
	
	public static void main (String[] args) {
		NavigationFilter gradingStatusFilter = new AGradingStatusFilter();
     	NavigationFilterManager.register(gradingStatusFilter);
     	NavigationFilter notesStatusFilter = new ANotesStatusFilter();
     	NavigationFilterManager.register(notesStatusFilter);
		NavigationSetter navigationSetter = new ANavigationSetter();
		ObjectEditor.edit(navigationSetter);
	}
	@Override
	public void propertyChange(PropertyChangeEvent anEvent) {
		if (anEvent.getSource() == navigationFilterEnum && anEvent.getPropertyName().equals("value")) {
			currentNavigationFilterName = (String) anEvent.getNewValue();
			Object oldParameters = currentNavigationFilter.getParameters();
			currentNavigationFilter = NavigationFilterManager.getFilterer(currentNavigationFilterName);
			Object newParameters = currentNavigationFilter.getParameters();
			propertyChangeSupport.firePropertyChange("FilterParameters", oldParameters, newParameters);
		}
		
		
	}
	@Override
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyChangeSupport.addPropertyChangeListener(aListener);
		
	}
	
}
