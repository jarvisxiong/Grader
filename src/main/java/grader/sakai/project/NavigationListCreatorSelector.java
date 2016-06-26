package grader.sakai.project;

import grader.navigation.NavigationListManager;

public class NavigationListCreatorSelector {
	static NavigationListManager navigationListCreator = new AnUnsortedNavigationListCreator();

	public static NavigationListManager getNavigationListCreator() {
		return navigationListCreator;
	}

	public static void setNavigationListCreator(
			NavigationListManager navigationListCreator) {
		NavigationListCreatorSelector.navigationListCreator = navigationListCreator;
	}

}
