package grader.settings;

import java.beans.PropertyChangeListener;

import grader.modules.ModuleProblemSelector;
import grader.settings.folders.GraderFilesSetterModel;
import grader.settings.folders.OnyenRangeModel;
import grader.settings.navigation.NavigationSetter;
import util.annotations.ComponentHeight;
import util.annotations.Row;
import util.annotations.Visible;
import util.models.PropertyListenerRegisterer;

public interface GraderSettingsModel extends PropertyListenerRegisterer, PropertyChangeListener{
	public GraderFilesSetterModel getFileBrowsing() ;
	public void setFileBrowsing(GraderFilesSetterModel fileBrowsing);
	public OnyenRangeModel getOnyens() ;
	public void setOnyens(OnyenRangeModel onyens) ;
	public  void begin() ;
	public  void awaitBegin() ;
	NavigationSetter getNavigationSetter();
	void setNavigationSetter(NavigationSetter navigationSetter);
	boolean isGraderStarted();
	void setGraderStarted(boolean graderStarted);
	String getCurrentProblem();
	ModuleProblemSelector getModuleProblemSelector();
	void setModuleProblemSelector(ModuleProblemSelector moduleProblemSelector);

}
