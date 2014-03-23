package grader.settings.folders;

import java.beans.PropertyChangeListener;

import util.models.PropertyListenerRegisterer;

public interface OnyenRangeModel extends PropertyListenerRegisterer{
	public String getStartingOnyen() ;
	public void setStartingOnyen(String startingOnyen) ;
	public String getEndingOnyen();

	public void setEndingOnyen(String endingOnyen) ;
	String getGoToOnyen();
	void setGoToOnyen(String goToOnyen);

}
