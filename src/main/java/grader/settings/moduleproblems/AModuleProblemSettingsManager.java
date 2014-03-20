package grader.settings.moduleproblems;

import grader.navigation.filter.NavigationFilter;
import grader.settings.navigation.NavigationKind;

import java.util.List;

public class AModuleProblemSettingsManager {
	public List<String> getModules() {
		return null;
		
	}
	public List<String> getProblems(String aModule, String aProblemDirectory) {
		return null;
	}
	public void setProblems(String aModule, String aProblemDirectory, List<String> aProblems) {
		
		
	}
	public String getStartingOnyen(String aModule, String aProblem) {
		return null;
	}
	
	public void setStartingOnyen(String aModule, String aProblem, String aStartOnyen) {
		;
	}
	
	public String getEndingOnyen(String aModule, String aProblem) {
		return null;
	}
	
	public void setEndingOnyen(String aModule, String aProblem, String anEndOnyen) {
		;
	}
	
	public NavigationKind getNavigationKind(String aModule, String aProblem) {
		return null;
	}
	
	public void setNavigationKind(String aModule, String aProblem, NavigationKind aNavigationKind) {
		;
	}
	
	public Boolean getAnimateGrades(String aModule, String aProblem) {
		return null;
	}
	
	public void setAnimateGrades(String aModule, String aProblem, boolean newVal) {
		;
	}
	
	public Integer getAnimationPauseTime(String aModule, String aProblem) {
		return null;
	}
	
	public NavigationFilter getAnimatePauseTime(String aModule, String aProblem) {
		return null;
	}
	
	public Object getFilterOption(String aModule, String aProblem) {
		return null;
	}

	public void setFilterOption(String aModule, String aProblem, Object newVal ) {
		;
	}


}
