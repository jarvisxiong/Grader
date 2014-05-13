package grader.config;

import framework.grading.ProjectRequirements;
import framework.utils.GradingEnvironment;
import grader.settings.GraderSettingsManager;
import grader.settings.GraderSettingsManagerSelector;

import java.io.File;
import java.util.List;

import org.apache.commons.configuration.PropertiesConfiguration;

public class StaticConfigurationUtils {
	public static String VISIT_ACTIONS = "visitActions";
	public static String AUTO_GRADE = "autoGrade";
	public static String AUTO_RUN = "autoRun";
	public static String LOAD_CLASSES = "loadClasses";
	public static String ALLOW_COMPILE_CLASSES = "compileMissingObjectCode";
	public static String FORCE_COMPILE_CLASSES = "forceCompile";

	public static String PRIVACY = "privacy";
	public static String EXECUTION_COMMAND = "execution";
	public static String LANGUAGE = "language";
	public static String REQUIREMENTS = "requirements";

	
	
	public static List<String> autoVisitActions(PropertiesConfiguration configuration, GraderSettingsManager graderSettingsManager) {
		String module = graderSettingsManager.getModule();
		String problem = graderSettingsManager.getNormalizedProblem(module);
		List retVal = configuration.getList(module+"." + problem + "." + VISIT_ACTIONS);
		if (retVal.isEmpty())
			retVal = configuration.getList(module+"." + VISIT_ACTIONS);
		if (retVal.isEmpty())
			retVal = configuration.getList("default"+"." + VISIT_ACTIONS);
		
		return retVal;
		
	}
	
	public static boolean getLoadClasses(PropertiesConfiguration configuration, GraderSettingsManager graderSettingsManager) {

		return  getInheritedBooleanModuleProblemProperty(configuration, graderSettingsManager, LOAD_CLASSES, false);
		
	}
	
	public static boolean getAllowCompileClasses(PropertiesConfiguration configuration, GraderSettingsManager graderSettingsManager) {

		return  getInheritedBooleanModuleProblemProperty(configuration, graderSettingsManager, ALLOW_COMPILE_CLASSES, false);
		
	}
	public static boolean getForceCompileClasses(PropertiesConfiguration configuration, GraderSettingsManager graderSettingsManager) {

		return  getInheritedBooleanModuleProblemProperty(configuration, graderSettingsManager, FORCE_COMPILE_CLASSES, false);
		
	}
	public static boolean getPrivacy(PropertiesConfiguration configuration, GraderSettingsManager graderSettingsManager) {
//		String module = graderSettingsManager.getModule();
//		String problem = graderSettingsManager.getNormalizedProblem(module);
//		Boolean retVal = configuration.getBoolean(module+"." + problem + "." + MAKE_CLASS_DESCRIPTION, null);
//			
//		if (retVal == null)
//			retVal = configuration.getBoolean(module+"." + MAKE_CLASS_DESCRIPTION, null);
//		if (retVal == null)
//			retVal = configuration.getBoolean("default"+"." + MAKE_CLASS_DESCRIPTION, false);
//		
//		return retVal;
		return  getInheritedBooleanModuleProblemProperty(configuration, graderSettingsManager, PRIVACY, false);
		
	}
	
	public static boolean getPrivacy(PropertiesConfiguration configuration, String aModule, String aProblem) {
//		String module = graderSettingsManager.getModule();
//		String problem = graderSettingsManager.getNormalizedProblem(module);
//		Boolean retVal = configuration.getBoolean(module+"." + problem + "." + MAKE_CLASS_DESCRIPTION, null);
//			
//		if (retVal == null)
//			retVal = configuration.getBoolean(module+"." + MAKE_CLASS_DESCRIPTION, null);
//		if (retVal == null)
//			retVal = configuration.getBoolean("default"+"." + MAKE_CLASS_DESCRIPTION, false);
//		
//		return retVal;
		return  getInheritedBooleanModuleProblemProperty(configuration, aModule, aProblem, PRIVACY, false);
		
	}
	
	public static Boolean getInheritedBooleanModuleProblemProperty(PropertiesConfiguration configuration, GraderSettingsManager graderSettingsManager, String property, Boolean defaultValue) {
		String module = graderSettingsManager.getModule();
		String problem = graderSettingsManager.getNormalizedProblem(module);
		
		return getInheritedBooleanModuleProblemProperty(configuration, module, problem, property, defaultValue);
//		Boolean retVal = configuration.getBoolean(module+"." + problem + "." + property, null);
//			
//		if (retVal == null)
//			retVal = configuration.getBoolean(module+"." + property, null);
//		if (retVal == null)
//			retVal = configuration.getBoolean("default"+"." + property, defaultValue);
//		
//		return retVal;
		
	}
	
	
	
	public static Boolean getInheritedBooleanModuleProblemProperty(PropertiesConfiguration configuration, String module, String problem, String property, Boolean defaultValue) {
		
		Boolean retVal = configuration.getBoolean(module+"." + problem + "." + property, null);
			
		if (retVal == null)
			retVal = configuration.getBoolean(module+"." + property, null);
		if (retVal == null)
			retVal = configuration.getBoolean("default"+"." + property, defaultValue);
		
		return retVal;
		
	}
	
	public static String[] getExecutionCommand(File buildFolder, String entryPoint) {
		List<String> basicCommand = getInheritedListModuleProblemProperty(EXECUTION_COMMAND);
		String[] retVal = new String[basicCommand.size()];
		for (int i = 0; i < basicCommand.size(); i++) {
		String withClassPath = basicCommand.get(i).replace("{classPath}", GradingEnvironment.get().getClasspath());
		String withEntryPoint = withClassPath.replace("{entryPoint}", entryPoint);
		String withBuildFolder = withEntryPoint.replace("{buildFolder}", buildFolder.getAbsolutePath());
		retVal[i] = withBuildFolder;
		}
		return retVal;
		
	}
	
public static String getInheritedStringModuleProblemProperty( String property, String defaultValue) {
	PropertiesConfiguration configuration = ConfigurationManagerSelector.getConfigurationManager().getStaticConfiguration();
	GraderSettingsManager graderSettingsManager = GraderSettingsManagerSelector.getGraderSettingsManager();
	String aModule = graderSettingsManager.getModule();
	String aProblem = graderSettingsManager.getNormalizedProblem(aModule);
		return getInheritedStringModuleProblemProperty(configuration, aModule , 
				aProblem, property, defaultValue);
		
	}

public static String getLanguage() {
	return getInheritedStringModuleProblemProperty(LANGUAGE, "Java");
	
}


public static List<String> getInheritedListModuleProblemProperty( String property) {
	PropertiesConfiguration configuration = ConfigurationManagerSelector.getConfigurationManager().getStaticConfiguration();
	GraderSettingsManager graderSettingsManager = GraderSettingsManagerSelector.getGraderSettingsManager();
	String aModule = graderSettingsManager.getModule();
	String aProblem = graderSettingsManager.getNormalizedProblem(aModule);
		return getInheritedListModuleProblemProperty(configuration, aModule , 
				aProblem, property);
		
	}

//public static String getInheritedStringModuleProblemProperty( String property, String defaultValue) {
//	PropertiesConfiguration configuration = ConfigurationManagerSelector.getConfigurationManager().getStaticConfiguration();
//	GraderSettingsManager graderSettingsManager = GraderSettingsManagerSelector.getGraderSettingsManager();
//	String aModule = graderSettingsManager.getModule();
//	String aProblem = graderSettingsManager.getNormalizedProblem(aModule);
//		return getInheritedStringModuleProblemProperty(configuration, aModule , 
//				aProblem, property, null);
//		
//	}
	
public static String getInheritedStringModuleProblemProperty(PropertiesConfiguration configuration, String module, String problem, String property, String defaultValue) {
		
		String retVal = configuration.getString(module+"." + problem + "." + property, null);
			
		if (retVal == null)
			retVal = configuration.getString(module+"." + property, null);
		if (retVal == null)
			retVal = configuration.getString("default"+"." + property, defaultValue);
		
		return retVal;
		
	}

public static List<String> getInheritedListModuleProblemProperty(PropertiesConfiguration configuration, String module, String problem, String property) {
	
	List retVal = configuration.getList(module+"." + problem + "." + property);
		
	if (retVal.isEmpty())
		retVal = configuration.getList(module+"." + property);
	if (retVal.isEmpty())
		retVal = configuration.getList("default"+"." + property);
	
	return retVal;
	
}
	
	public  static ProjectRequirements  getProjectRequirements(PropertiesConfiguration configuration,
			GraderSettingsManager graderSettingsManager ) {
		
		
		ProjectRequirements requirements = null;
		
		String requirementsSpec = "";
		
		try {
			 requirementsSpec = getInheritedStringModuleProblemProperty(REQUIREMENTS, configuration.getString("project.requirements"));

			// compatibility with Josh's spec

//				String module = graderSettingsManager.getModule();
//				 requirementsSpec = configuration.getString(module + ".requirements");
//				if (requirementsSpec == null) {
//					requirementsSpec = configuration.getString(module.toLowerCase() + ".requirements");
//					if (requirementsSpec == null) {
//						requirementsSpec = configuration.getString("default.requirements");
//						if (requirementsSpec == null) {
//							requirementsSpec = configuration.getString("project.requirements"); // upward compatibilty
//						}
//					}
//				}
				
			String	normalizedRequirementsSpec = graderSettingsManager.replaceModuleProblemVars(requirementsSpec);
					
//			}
			
			
//		   Class<?> _class = Class.forName(configuration.getString("project.requirements"));
		   Class<?> _class = Class.forName(normalizedRequirementsSpec);

            requirements = (ProjectRequirements) _class.newInstance();
	} catch (ClassNotFoundException e) {
        System.err.println("Could not find project requirements:" + requirementsSpec);
        System.err.println(e.getMessage());
	}  catch (InstantiationException e) {
        System.err.println("Could not create project requirements." + requirements);
        System.err.println(e.getMessage());
    } catch (IllegalAccessException e) {
        System.err.println("Could not create project requirements." + requirements);
        System.err.println(e.getMessage());
    }
		return requirements;
		
		
	}

}
