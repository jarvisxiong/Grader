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
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;

public class ANavigationSetter implements NavigationSetter {
	NavigationKind navigationKind = NavigationKind.AUTOMATIC_THEN_MANUAL;
	AutomaticNavigationSetter automaticNavigationSetter = new AnAutomaticNavigationSetter();
	NavigationFilterSetter navigationFilterSetter = new ANavigationFilterSetter();
//	DynamicEnum<String> navigationFilterEnum;
//	NavigationFilter currentNavigationFilter;
//	String currentNavigationFilterName;
//	Object navigationParameters;
//	PropertyChangeSupport propertyChangeSupport =  new PropertyChangeSupport(this);
//	
	
	
	public ANavigationSetter() {
//		constructNavigationFilterTypes();
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
	@Label("Automatic Navigation Options")
	public AutomaticNavigationSetter getAutomaticNavigationSetter() {
		return automaticNavigationSetter;
	}
	public void setAutomaticNavigationSetter(
			AutomaticNavigationSetter automaticNavigationSetter) {
		this.automaticNavigationSetter = automaticNavigationSetter;
	}
	@Row(2)
	@Label("Manual Navigation Filter") // theoretically this could be used also for automatic navigation
	public NavigationFilterSetter getNavigationFilterSetter() {
		return navigationFilterSetter;
	}
	public void setNavigationFilterSetter(
			NavigationFilterSetter navigationFilterSetter) {
		this.navigationFilterSetter = navigationFilterSetter;
	}
	
//	void constructNavigationFilterTypes () {
//		Set<String> filterNames = NavigationFilterRepository.filterTypes();
//		List<String> filterNamesList = new ArrayList(filterNames);
//		Collections.sort(filterNamesList);
//		navigationFilterEnum = new ADynamicEnum(filterNamesList);
//		navigationFilterEnum.addPropertyChangeListener(this);
//		if (filterNamesList.size() > 0) {
//		  currentNavigationFilterName =  filterNamesList.get(0);
//		  currentNavigationFilter = NavigationFilterRepository.getFilterer(currentNavigationFilterName);
//		}
//	}
//	
////	public boolean preGetNavigationFilterTypes() {
////		return  navigationFilterEnum.choicesSize() > 0;
////	}
////	public boolean preGetParameters() {
////		return preGetNavigationFilterTypes() && currentNavigationFilter != null && currentNavigationFilter.getParameters() != null;
////	}
//	@Row(2)	
//	public DynamicEnum getNavigationFilterType() {
//		return navigationFilterEnum;
//	}
//	
//	
//	@Row(3)
//	@PreferredWidgetClass(JRadioButton.class)
//	@Label("Navigation Filter Options")
//	public Object getFilterOptions() {
//		return currentNavigationFilter.getParameters();
//	}
//
//	@Override
//	public void propertyChange(PropertyChangeEvent anEvent) {
//		if (anEvent.getSource() == navigationFilterEnum && anEvent.getPropertyName().equals("value")) {
//			currentNavigationFilterName = (String) anEvent.getNewValue();
//			Object oldParameters = currentNavigationFilter.getParameters();
//			currentNavigationFilter = NavigationFilterRepository.getFilterer(currentNavigationFilterName);
//			Object newParameters = currentNavigationFilter.getParameters();
//			propertyChangeSupport.firePropertyChange("FilterOptions", oldParameters, newParameters);
//		}
//		
//		
//	}
//	@Override
//	public void addPropertyChangeListener(PropertyChangeListener aListener) {
//		propertyChangeSupport.addPropertyChangeListener(aListener);
//		
//	}
	
	
	public static void main (String[] args) {
//		NavigationFilter gradingStatusFilter = new AGradingStatusFilter();
//     	NavigationFilterRepository.register(gradingStatusFilter);
//     	NavigationFilter notesStatusFilter = new ANotesStatusFilter();
//     	NavigationFilterRepository.register(notesStatusFilter);
//     	NavigationFilter letterStatusFilter = new ALetterGradeBasedFilter();
//     	NavigationFilterRepository.register(letterStatusFilter);
		NavigationSetter navigationSetter = new ANavigationSetter();		
		OEFrame frame = ObjectEditor.edit(navigationSetter);
		frame.setSize(600, 300);
	}
	
}
