package grader.settings.navigation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NavigationFilterRepository {
	static Map<String, Object> filterTypeToParameters = new HashMap<>();
	static Map<String, NavigationFilter> filterTypeToFilterer = new HashMap<>();

	
	public static void register (String aFilterType, Object aFilterParameterSet, NavigationFilter aFilterer ) {
		filterTypeToParameters.put(aFilterType, aFilterParameterSet );
		filterTypeToFilterer.put(aFilterType, aFilterer);
	}
	
	public static void register (NavigationFilter aFilterer ) {
		filterTypeToParameters.put(aFilterer.getName(), aFilterer.getParameters() );
		filterTypeToFilterer.put(aFilterer.getName(), aFilterer);
	}
	
	
	public static Set<String> filterTypes() {
		return filterTypeToParameters.keySet();
	}
	
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

}
