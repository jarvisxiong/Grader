package grader.settings.navigation;

import grader.navigation.filter.AGradingStatusFilter;
import grader.navigation.filter.ALetterGradeBasedFilter;
import grader.navigation.filter.ANotesStatusFilter;
import grader.navigation.filter.NavigationFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NavigationFilterRepository {
	static Map<String, Object> filterTypeToParameters = new HashMap<>();
	static Map<String, NavigationFilter> filterTypeToFilterer = new HashMap<>();
	static List<String> filterNames = new ArrayList();

	
	public static void register (String aFilterType, Object aFilterParameterSet, NavigationFilter aFilterer ) {
		filterTypeToParameters.put(aFilterType, aFilterParameterSet );
		filterTypeToFilterer.put(aFilterType, aFilterer);
		if (!filterNames.contains(aFilterType))
			filterNames.add(aFilterType);
	}	
	public static void register (NavigationFilter aFilterer ) {
		register (aFilterer.getName(), aFilterer.getParameter(),  aFilterer);		
	}	
	public static List<String> filterTypes() {
		return filterNames;
	}
	
//	public static Set<String> filterTypes() {
//		return filterTypeToParameters.keySet();
//	}
//	
	public static Object getFilterParameters(String aFilterType) {
		return filterTypeToParameters.get(aFilterType);		
	}
	
	public static NavigationFilter getFilter(String aFilterType) {
		return filterTypeToFilterer.get(aFilterType);		
	}
	
	public static void remove(String aFilterType) {
		filterTypeToParameters.remove(aFilterType);
		filterTypeToFilterer.remove(aFilterType	);
	}
	
	static {
		NavigationFilter gradingStatusFilter = new AGradingStatusFilter();
     	NavigationFilterRepository.register(gradingStatusFilter);
     	NavigationFilter notesStatusFilter = new ANotesStatusFilter();
     	NavigationFilterRepository.register(notesStatusFilter);
     	NavigationFilter letterStatusFilter = new ALetterGradeBasedFilter();
     	NavigationFilterRepository.register(letterStatusFilter);
	}

}
