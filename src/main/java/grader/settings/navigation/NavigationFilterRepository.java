package grader.settings.navigation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
		register (aFilterer.getName(), aFilterer.getParameters(),  aFilterer);		
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
	
	public static NavigationFilter getFilterer(String aFilterType) {
		return filterTypeToFilterer.get(aFilterType);		
	}
	
	public static void remove(String aFilterType) {
		filterTypeToParameters.remove(aFilterType);
		filterTypeToFilterer.remove(aFilterType	);
	}
	
	static {
     	NavigationFilter notesStatusFilter = new ANotesStatusFilter();
     	NavigationFilterRepository.register(notesStatusFilter);
		NavigationFilter gradingStatusFilter = new AGradingStatusFilter();
     	NavigationFilterRepository.register(gradingStatusFilter);
     	NavigationFilter letterStatusFilter = new ALetterGradeBasedFilter();
     	NavigationFilterRepository.register(letterStatusFilter);
	}

}
