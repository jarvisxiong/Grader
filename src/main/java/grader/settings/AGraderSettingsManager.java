package grader.settings;

import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import framework.utils.GraderSettings;
import framework.utils.GradingEnvironment;
import grader.config.ConfigurationManagerSelector;
import grader.modules.AModuleProblemSelector;
import grader.modules.ModuleProblemManager;
import grader.modules.ModuleProblemManagerSelector;
import grader.navigation.filter.NavigationFilter;
import grader.settings.navigation.NavigationKind;

public class AGraderSettingsManager implements GraderSettingsManager{
	 public static final String EDITOR = "editor";	   
	   public static final String MODULE = "currentModule";
	   public static final String PROBLEM_PATH = "path";
	   public static final String PROBLEM_NAME = "problem";
	   public static final String START_ONYEN = "start";
	   public static final String END_ONYEN = "end";
	   PropertiesConfiguration dynamicConfiguration = 
			   ConfigurationManagerSelector.getConfigurationManager().getDynamicConfiguration();
	   ModuleProblemManager moduleProblemManager= ModuleProblemManagerSelector.getModuleProblemManager();
	   
	   public AGraderSettingsManager() {
		   maybeConvertToDynamicConfiguration();
	   }
//	   @Override
//	   public void init (ModuleProblemManager initValue) {
//		   moduleProblemManager = initValue;
//	   }

	   void maybeConvertToDynamicConfiguration() {
		 	Map<String, String> settings = GraderSettings.get().getSettings();
//	    	PropertiesConfiguration dynamicConfiguration = GradingEnvironment.get().getConfigurationManager().getDynamicConfiguration();
	    	if (!dynamicConfiguration.isEmpty()) return;
	    	for (String key : settings.keySet())
	            dynamicConfiguration.setProperty(key, settings.get(key));
	    	try {
				dynamicConfiguration.save();
			} catch (ConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
	
	@Override
	public String getStartingOnyen(String aModule) {
		String startingOnyen =  dynamicConfiguration.getString(aModule + "." + START_ONYEN,     	
        		
        		dynamicConfiguration.getString(START_ONYEN));
	  return startingOnyen;
	}

	@Override
	public void setStartingOnyen(String aModule,
			String aStartOnyen) {
		dynamicConfiguration.setProperty(aModule + "." + START_ONYEN, aStartOnyen);
		dynamicConfiguration.setProperty(START_ONYEN, aStartOnyen);  
		
	}

	@Override
	public String getEndingOnyen(String aModule) {
		String endingOnyen =  dynamicConfiguration.getString(aModule + "." + END_ONYEN,     	
        		
        		dynamicConfiguration.getString(END_ONYEN));
	  return endingOnyen;
	}

	@Override
	public void setEndingOnyen(String aModule,
			String anEndOnyen) {
		dynamicConfiguration.setProperty(aModule + "." + END_ONYEN, anEndOnyen);
		dynamicConfiguration.setProperty(END_ONYEN, anEndOnyen); 
		
	}

	@Override
	public NavigationKind getNavigationKind(String aModule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNavigationKind(String aModule,
			NavigationKind aNavigationKind) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean getAnimateGrades(String aModule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAnimateGrades(String aModule, boolean newVal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getAnimationPauseTime(String aModule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigationFilter getAnimatePauseTime(String aModule) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getEditor() {
		 String editor = dynamicConfiguration.getString(EDITOR);
			if (editor != null) {
//				editor = GraderSettings.get().get("editor");
	            GradingEnvironment.get().setEditor(editor); // why not for path also, perhaps its not used later?
	        } else
	            editor = GradingEnvironment.get().getEditor();
		return editor;
	}


	@Override
	public void setEditor(String newValue) {
		dynamicConfiguration.setProperty(EDITOR, newValue);
		
	}
	@Override
	public String getModule() {
		 List<String> modules = moduleProblemManager.getModules();

			String aModule = dynamicConfiguration.getString(MODULE, modules.get(0));
			return aModule;
	}
	
	@Override
	public void setModule(String newValue) {
		dynamicConfiguration.setProperty(MODULE, newValue);
		
	}

	@Override
	public String getDownloadPath(String aModule) {
		String problemDownloadPath =  dynamicConfiguration.getString(aModule + "." + PROBLEM_PATH,
				dynamicConfiguration.getString(PROBLEM_PATH));
		return problemDownloadPath;
	}

	@Override
	public void setDownloadPath(String aModule, String aNewValue) {
		dynamicConfiguration.setProperty(aModule + "." + PROBLEM_PATH, aNewValue);
		
	}
	@Override
	public String getProblem(String aModule) {
		String problemDownloadPath =  dynamicConfiguration.getString(aModule + "." + PROBLEM_NAME,
				dynamicConfiguration.getString(PROBLEM_NAME));
		return problemDownloadPath;
	}

	@Override
	public void setProblem(String aModule, String aNewValue) {
		dynamicConfiguration.setProperty(aModule + "." + PROBLEM_NAME, aNewValue.replaceAll("\\s+", ""));		
	}
	@Override
	public String replaceModuleProblemVars(String  original) {
		String moduleName = getModule();
		String problemName = getProblem(moduleName);
		String retVal = original;
//		String problemName = dynamicConfiguration.getString(AGraderSettingsModel.MODULE + "." + AGraderSettingsModel.MODULE);
		retVal = retVal.replace("{moduleName}", moduleName);
		retVal = retVal.replace("{ModuleName}", moduleName);
		retVal = retVal.replace("{modulename}", moduleName.toLowerCase());
		
		retVal = retVal.replace("{problemName}", problemName);
		retVal = retVal.replace("{ProblemName}", problemName);
		retVal = retVal.replace("{problemname}", problemName.toLowerCase());
		return retVal;
		
	}
    
	@Override
	public void save() {
      try {
			dynamicConfiguration.save();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
