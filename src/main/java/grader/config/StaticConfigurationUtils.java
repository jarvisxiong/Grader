package grader.config;

import framework.grading.ProjectRequirements;
import grader.settings.GraderSettingsManager;

import java.util.List;

import org.apache.commons.configuration.PropertiesConfiguration;

public class StaticConfigurationUtils {
	public static String VISIT_ACTIONS = "visitActions";
	public static String AUTO_GRADE = "autoGrade";
	public static String AUTO_RUN = "autoRun";
	public static String MAKE_CLASS_DESCRIPTION = "makeClassDescription";
	public static String PRIVACY = "privacy";
	
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
	
	public static boolean getMakeClassDescription(PropertiesConfiguration configuration, GraderSettingsManager graderSettingsManager) {
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
		return  getInheritedBooleanModuleProblemProperty(configuration, graderSettingsManager, MAKE_CLASS_DESCRIPTION, false);
		
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
	
	public static Boolean getInheritedBooleanModuleProblemProperty(PropertiesConfiguration configuration, GraderSettingsManager graderSettingsManager, String property, Boolean defaultValue) {
		String module = graderSettingsManager.getModule();
		String problem = graderSettingsManager.getNormalizedProblem(module);
		Boolean retVal = configuration.getBoolean(module+"." + problem + "." + property, null);
			
		if (retVal == null)
			retVal = configuration.getBoolean(module+"." + property, null);
		if (retVal == null)
			retVal = configuration.getBoolean("default"+"." + property, defaultValue);
		
		return retVal;
		
	}
	
	public  static ProjectRequirements  getProjectRequirements(PropertiesConfiguration configuration,
			GraderSettingsManager graderSettingsManager ) {
		ProjectRequirements requirements = null;
		String requirementsSpec = "";
		
		try {
			// compatibility with Josh's spec
//			String requirementsSpec = configuration.getString("project.requirements");
//			if (requirementsSpec == null) {
//				Comp401.requirements = gradingTools.{problemname}
				String module = graderSettingsManager.getModule();
				 requirementsSpec = configuration.getString(module + ".requirements");
				if (requirementsSpec == null) {
					requirementsSpec = configuration.getString(module.toLowerCase() + ".requirements");
					if (requirementsSpec == null) {
						requirementsSpec = configuration.getString("default.requirements");
						if (requirementsSpec == null) {
							requirementsSpec = configuration.getString("project.requirements"); // upward compatibilty
						}
					}
				}
				
				requirementsSpec = graderSettingsManager.replaceModuleProblemVars(requirementsSpec);
					
//			}
			
			
//		   Class<?> _class = Class.forName(configuration.getString("project.requirements"));
		   Class<?> _class = Class.forName(requirementsSpec);

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
