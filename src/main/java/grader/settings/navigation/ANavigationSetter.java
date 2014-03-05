package grader.settings.navigation;

import bus.uigen.ObjectEditor;

public class ANavigationSetter implements NavigationSetter {
	NavigationKind navigationKind = NavigationKind.AUTOMATIC_THEN_MANUAL;
	AutomaticNavigationSetter automaticNavigationSetter = new AnAutomaticNavigationSetter();
	public NavigationKind getNavigationKind() {
		return navigationKind;
	}
	public void setNavigationKind(NavigationKind navigationKind) {
		this.navigationKind = navigationKind;
	}
	public AutomaticNavigationSetter getAutomaticNavigationSetter() {
		return automaticNavigationSetter;
	}
	public void setAutomaticNavigationSetter(
			AutomaticNavigationSetter automaticNavigationSetter) {
		this.automaticNavigationSetter = automaticNavigationSetter;
	}
	
	public static void main (String[] args) {
		NavigationSetter navigationSetter = new ANavigationSetter();
		ObjectEditor.edit(navigationSetter);
	}
	
}
