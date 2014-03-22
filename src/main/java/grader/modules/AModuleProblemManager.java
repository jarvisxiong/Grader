package grader.modules;

import grader.config.ConfigurationManagerSelector;
import grader.navigation.filter.NavigationFilter;
import grader.settings.navigation.NavigationKind;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.PropertiesConfiguration;

import util.trace.Tracer;

public class AModuleProblemManager implements ModuleProblemManager{
	PropertiesConfiguration configuration, dynamicConfiguration;
	List<String> modules;
	public AModuleProblemManager() {
		configuration = ConfigurationManagerSelector.getConfigurationManager().getStaticConfiguration();
		dynamicConfiguration = ConfigurationManagerSelector.getConfigurationManager().getDynamicConfiguration();
	}
	public List<String> getModules() {
		if (modules != null) return modules;
		
		 List objectModules = configuration.getList("modules");
		 List<String> 	modules = objectModules;
			if (objectModules.size() == 0) {
				Tracer.error("No modules specified in configuration file!");
				System.exit(-1);
			}
		return modules;
		
	}
    public String getModulePrefix(String aModule) {
    	String retVal =  configuration.getString(aModule + ".problems.prefix")	;

		if (retVal == null)
			retVal = configuration.getString("default.problems.prefix", "Assignment");
		return retVal;
	}
    
//    public String getCurrentModule() {
//    	List<String> modules = getModules();
//    	return  dynamicConfiguration.getString("currentModule", modules.get(0));
//    	
//    }

	public String getProblemsAndCurrentProblem(String aModule, String downloadPath, List<String> problems) {
		problems.clear();
//		List<String> problems = new ArrayList();
		String currentModulePrefix =getModulePrefix(aModule);
		problems.clear();
		if (downloadPath != null) {
			File folder = new File(downloadPath);
			if (!folder.exists()) {
				Tracer.error("No folder found for:" + downloadPath);				
			} else {
				File gradesFile = new File(downloadPath + "/grades.csv"); // is this a sakai assignment folder
				if (gradesFile.exists()) 
					folder = folder.getParentFile();
				File[] children = folder.listFiles();
				for (File child:children) {
					if (child.getName().startsWith(currentModulePrefix)) {
						problems.add(child.getName());
					}
				}
			}
		}
		if (problems.size() > 0)
			return problems.get(problems.size() - 1);
		else
			return null;
//		return problems;
	}
	
	
	
	
//	public void setProblems(String aModule, String aProblemDirectory, List<String> aProblems) {
//		
//		
//	}
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
