package gradingTools;

import java.beans.PropertyChangeListener;

import util.misc.ClearanceManager;
import util.models.PropertyListenerRegisterer;

public interface TestingClearanceManager extends ClearanceManager, PropertyListenerRegisterer{
	public boolean isAutoProceed();
	public void setAutoProceed(boolean autoProceed) ;
	public String getStepDescription() ;
	public void setStepDescription(String stepDescription) ;
	public long getAutoPauseTime();
	public void setAutoPauseTime(long autoPauseTime) ;


}
