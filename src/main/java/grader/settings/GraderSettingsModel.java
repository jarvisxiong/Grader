package grader.settings;

import java.beans.PropertyChangeListener;

import grader.modules.ModuleProblemSelector;
import grader.settings.folders.GraderFilesSetterModel;
import grader.settings.folders.OnyenRangeModel;
import grader.settings.navigation.NavigationSetter;
import util.annotations.ComponentHeight;
import util.annotations.Row;
import util.annotations.Visible;
import util.models.PropertyListenerRegistrar;

public interface GraderSettingsModel extends PropertyListenerRegistrar, PropertyChangeListener{
	public GraderFilesSetterModel getFileBrowsing() ;
	public void setFileBrowsing(GraderFilesSetterModel fileBrowsing);
	public OnyenRangeModel getOnyens() ;
	public void setOnyens(OnyenRangeModel onyens) ;
        public void preSettings();
        public void postSettings();
	public void begin() ;
	public void awaitBegin() ;
	NavigationSetter getNavigationSetter();
	void setNavigationSetter(NavigationSetter navigationSetter);
	boolean isGraderStarted();
	void setGraderStarted(boolean graderStarted);
	String getCurrentProblem();
	ModuleProblemSelector getModuleProblemSelector();
	void setModuleProblemSelector(ModuleProblemSelector moduleProblemSelector);
	void cleanAllSubmissionFolders();
	void resetFeatureSpreadsheet();
	void cleanSlate();
	String getCurrentModule();
	boolean isSettingsLoaded();
	void init();
	public boolean isPrivacyMode() ;
	public void setPrivacyMode(boolean newValue) ;
	public void togglePrivacyMode() ;
	boolean getCompileMode();
	boolean maybePreCompile();
	void cleanSlate(String anOnyen);
	boolean maybePreUnzip();
	void compileExecutor();

}
