package grader.settings;

import grader.modules.ModuleProblemManager;
import grader.navigation.filter.NavigationFilter;
import grader.settings.navigation.NavigationKind;

public interface GraderSettingsManager {
	
	public String getEditor();
	public void setEditor(String newValue);
	
    public String getStartingOnyen(String aModule);
	
	public void setStartingOnyen(String aModule, String aStartOnyen) ;
	
    public String getDownloadPath(String aModule);
	
	public void setDownloadPath(String aModule, String aNewValue) ;
	
	
	public String getEndingOnyen(String aModule) ;
	
	public void setEndingOnyen(String aModule, String anEndOnyen);
	
	public NavigationKind getNavigationKind(String aModule) ;
	
	public void setNavigationKind(String aModule, NavigationKind aNavigationKind);
	
	public Boolean getAnimateGrades(String aModule);
	
	public void setAnimateGrades(String aModule, boolean newVal);
	
	public Integer getAnimationPauseTime(String aModule) ;
	
	public NavigationFilter getAnimatePauseTime(String aModule) ;
	String getModule();
	void setModule(String newValue);
	void save();
	String getProblem(String aModule);
	void setProblem(String aModule, String aNewValue);
//	void init(ModuleProblemManager initValue);
	String replaceModuleProblemVars(String original);
	

}
