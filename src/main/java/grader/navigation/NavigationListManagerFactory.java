package grader.navigation;

public class NavigationListManagerFactory {
	static NavigationListManager navigationListManager = new AlphabeticNavigationListManager();
	
	public static NavigationListManager getNavigationListManager() {
		return navigationListManager;
	}
	public static void setNavigationListManager(NavigationListManager newVal) {
		navigationListManager = newVal;
	}
}
