package grader.settings.navigation;

import java.beans.PropertyChangeListener;

import util.models.DynamicEnum;
import util.models.PropertyListenerRegistrar;

public interface NavigationFilterSetter extends PropertyChangeListener, PropertyListenerRegistrar {

	DynamicEnum getNavigationFilterType();

	Object getParameter();

	void setParameter(Object newVal);

}
