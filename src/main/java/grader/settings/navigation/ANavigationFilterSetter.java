package grader.settings.navigation;

import grader.navigation.filter.NavigationFilter;

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
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;

public class ANavigationFilterSetter implements NavigationFilterSetter {
//	NavigationKind navigationKind = NavigationKind.AUTOMATIC_THEN_MANUAL;
//	AutomaticNavigationSetter automaticNavigationSetter = new AnAutomaticNavigationSetter();
	DynamicEnum<String> navigationFilterEnum;
	NavigationFilter currentNavigationFilter;
	String currentNavigationFilterName;
	Object navigationParameters;
	PropertyChangeSupport propertyChangeSupport =  new PropertyChangeSupport(this);
	
	
	public ANavigationFilterSetter() {
		constructNavigationFilterTypes();
	}
	
	
	void constructNavigationFilterTypes () {
//		Set<String> filterNames = NavigationFilterRepository.filterTypes();
//		List<String> filterNamesList = new ArrayList(filterNames);
//		Collections.sort(filterNamesList);
		List<String> filterNamesList = NavigationFilterRepository.filterTypes();

		navigationFilterEnum = new ADynamicEnum(filterNamesList);
		navigationFilterEnum.addPropertyChangeListener(this);
		if (filterNamesList.size() > 0) {
		  currentNavigationFilterName =  filterNamesList.get(0);
		  currentNavigationFilter = NavigationFilterRepository.getFilter(currentNavigationFilterName);
		}
	}
	
//	public boolean preGetNavigationFilterTypes() {
//		return  navigationFilterEnum.choicesSize() > 0;
//	}
//	public boolean preGetParameters() {
//		return preGetNavigationFilterTypes() && currentNavigationFilter != null && currentNavigationFilter.getParameters() != null;
//	}
	@Override
	@Row(2)	
	@Label("Filter Type")
	public DynamicEnum getNavigationFilterType() {
		return navigationFilterEnum;
	}
	
	@Override
	@Row(3)
	@PreferredWidgetClass(JRadioButton.class)
	@Label("Filter Options")
	public Object getFilterParameter() {
		return currentNavigationFilter.getParameter();
	}

	@Override
	public void propertyChange(PropertyChangeEvent anEvent) {
		if (anEvent.getSource() == navigationFilterEnum && anEvent.getPropertyName().equals("value")) {
			currentNavigationFilterName = (String) anEvent.getNewValue();
			Object oldParameters = currentNavigationFilter.getParameter();
			currentNavigationFilter = NavigationFilterRepository.getFilter(currentNavigationFilterName);
			Object newParameters = currentNavigationFilter.getParameter();
			propertyChangeSupport.firePropertyChange("FilterOptions", oldParameters, newParameters);
		}
		
		
	}
	@Override
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyChangeSupport.addPropertyChangeListener(aListener);
		
	}
	
	
	public static void main (String[] args) {
//		NavigationFilter gradingStatusFilter = new AGradingStatusFilter();
//     	NavigationFilterRepository.register(gradingStatusFilter);
//     	NavigationFilter notesStatusFilter = new ANotesStatusFilter();
//     	NavigationFilterRepository.register(notesStatusFilter);
		NavigationFilterSetter navigationFilterSetter = new ANavigationFilterSetter();
		OEFrame frame = ObjectEditor.edit(navigationFilterSetter);
		frame.setSize(600, 300);
	}
	
}
