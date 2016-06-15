package grader.modules;

import java.util.List;

public interface ModuleProblemManager {
	public List<String> getModules();
    public String getModulePrefix(String aModule) ;
	void init();
    
//    public String getCurrentModule() ;

//	public String getProblemsAndCurrentProblem(String aModule, String aDownloadPath, List<String> problems);
//	public void setProblems(String aModule, String aProblemDirectory, List<String> aProblems) {
//		
//		
//	}
//	public String getStartingOnyen(String aModule, String aProblem);
//	
//	public void setStartingOnyen(String aModule, String aProblem, String aStartOnyen) ;
//	
//	public String getEndingOnyen(String aModule, String aProblem) ;
//	
//	public void setEndingOnyen(String aModule, String aProblem, String anEndOnyen);
//	
//	public NavigationKind getNavigationKind(String aModule, String aProblem) ;
//	
//	public void setNavigationKind(String aModule, String aProblem, NavigationKind aNavigationKind);
//	
//	public Boolean getAnimateGrades(String aModule, String aProblem);
//	
//	public void setAnimateGrades(String aModule, String aProblem, boolean newVal);
//	
//	public Integer getAnimationPauseTime(String aModule, String aProblem) ;
//	
//	public NavigationFilter getAnimatePauseTime(String aModule, String aProblem) ;
//	
//	public Object getFilterOption(String aModule, String aProblem) ;
//
//	public void setFilterOption(String aModule, String aProblem, Object newVal ) ;
//	String replaceModuleProblemVars(String original);
//	void init(GraderSettingsManager aGraderSettingsManager);

}
