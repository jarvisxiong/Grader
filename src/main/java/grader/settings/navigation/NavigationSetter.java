package grader.settings.navigation;

import java.beans.PropertyChangeListener;

import util.models.PropertyListenerRegisterer;

public interface NavigationSetter  {

	AutomaticNavigationSetter getAutomaticNavigationSetter();

	void setAutomaticNavigationSetter(
			AutomaticNavigationSetter automaticNavigationSetter);

	NavigationFilterSetter getNavigationFilterSetter();

	void setNavigationFilterSetter(NavigationFilterSetter navigationFilterSetter);

	NavigationKind getNavigationKind();

	void setNavigationKind(NavigationKind navigationKind);

}
