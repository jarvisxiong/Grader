package grader.settings.navigation;

import java.beans.PropertyChangeListener;

import util.models.DynamicEnum;
import util.models.PropertyListenerRegisterer;

public interface NavigationFilterSetter extends PropertyChangeListener, PropertyListenerRegisterer {

	DynamicEnum getNavigationFilterType();

	Object getParameter();

	void setParameter(Object newVal);

}
