package grader.sakai.project;

public class NavigationListCreatorSelector {
	static NavigationListCreator navigationListCreator = new AnUnsortedNavigationListCreator();

	public static NavigationListCreator getNavigationListCreator() {
		return navigationListCreator;
	}

	public static void setNavigationListCreator(
			NavigationListCreator navigationListCreator) {
		NavigationListCreatorSelector.navigationListCreator = navigationListCreator;
	}

}
