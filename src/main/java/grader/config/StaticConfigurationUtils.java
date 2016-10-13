package grader.config;

import framework.grading.ProjectRequirements;
import grader.assignment.AnAssignmenDataFolder;
import grader.basics.project.Project;
import grader.basics.settings.BasicGradingEnvironment;
import grader.permissions.java.JavaProjectToPermissionFile;
import grader.requirements.interpreter.AnInterpretedRequirements;
import grader.requirements.interpreter.specification.CSVRequirementsSpecification;
import grader.sakai.project.ASakaiProjectDatabase;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsManager;
import grader.settings.GraderSettingsManagerSelector;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.experimental.theories.PotentialAssignment;

import util.trace.Tracer;

public class StaticConfigurationUtils {

	public static final String DEFAULT = "default";
	public static final String VISIT_ACTIONS = "visitActions";
	public static final String AUTO_GRADE = "autoGrade";
	public static final String AUTO_RUN = "autoRun";
	public static final String LOAD_CLASSES = "loadClasses";
	public static final String ALLOW_COMPILE_CLASSES = "compileMissingObjectCode";
	public static final String PRE_COMPILE_CLASSES = "precompileMissingObjectCode";

	public static final String FORCE_COMPILE_CLASSES = "forceCompile";
	public static final String UNZIP_FILES = "unzipFiles";
	
	public static final String CHECK_STYLE = "checkStyle";
	
	public static final String CHECK_STYLE_FILE = "checkStyleFile";


	public static final String PRIVACY = "privacy";
	public static final String EXECUTION_COMMAND = "execution";

	public static final String LANGUAGE = "language";
	public static final String REQUIREMENTS = "requirements";
	public static final String ENTRY_POINT = "entryPoint";
	public static final String BUILD_FOLDER = "buildFolder";
	public static final String PERMISSIONS = "permissions";

	public static final String CLASS_PATH = "classPath";
	public static final String OE_PATH = "oePath";
	public static final String JUNIT_PATH = "junitPath";
	public static final String LOCAL_GRADER_PATH = "localGraderPath";
	public static final String OE_AND_CLASS_PATH = "oeAndClassPath";
	public static final String CLASS_PATH_SEPARATOR = ":";

	public static final String PROCESS_TEAMS = "processTeams";

	public static final String ENTRY_TAG = "entryTag";
	public static final String ENTRY_TAGS = "entryTags";
	public static final String SLEEP_TIME = "sleepTime";
	public static final String ARGS = "args";
	public static final String START_TAGS = "startTags";
	public static final String TERMINATING = "terminating";
	public static final String GENERATE_TRACE_FILES = "trace";

	public static final String JAVA = "Java";

	public static final String CLASS_PATH_VAR = toVariable(CLASS_PATH);
	public static final String CLASS_PATH_SEPARATOR_VAR = toVariable(CLASS_PATH_SEPARATOR);
	public static final String OE_PATH_VAR = toVariable(OE_PATH);
	public static final String LOCAL_GRADER_PATH_VAR = toVariable(LOCAL_GRADER_PATH);

	public static final String JUNIT_PATH_VAR = toVariable(JUNIT_PATH);
	public static final String OE_AND_CLASS_PATH_VAR = toVariable(OE_AND_CLASS_PATH );
	public static final String PERMISSIONS_VAR = toVariable(PERMISSIONS);
	public static final String BUILD_FOLDER_VAR = toVariable(BUILD_FOLDER);
	public static final String IMPLICIT_REQUIRMENTS_ROOT = "implicitRequirementsRoot";
	public static final String DEFAULT_IMPLICIT_REQUIRMENTS_ROOT = "gradingTools";
	public static final String USE_EXECEUTOR = "useExecutor";
	public static final String EXECEUTOR = "executor";
	public static final String C_OBJ = "language.C.obj";	
	public static final String FORK_MAIN = "forkMain";
	public static  List<String> basicCommand;

	// public static final String ENTRY_TAG_VAR = toVariable(ENTRY_TAG);

	public static String toVariable(String aVariableName) {
		return "{" + aVariableName + "}";
	}

	public static String getImplicitRequirementsRoot(
			PropertiesConfiguration configuration) {
		return configuration.getString(IMPLICIT_REQUIRMENTS_ROOT,
				DEFAULT_IMPLICIT_REQUIRMENTS_ROOT);
	}
	public static List<String> autoVisitActions(
			
			GraderSettingsManager graderSettingsManager) {
		PropertiesConfiguration staticConfiguration = ConfigurationManagerSelector
				.getConfigurationManager().getStaticConfiguration();
		PropertiesConfiguration courseConfiguration = ConfigurationManagerSelector
				.getConfigurationManager().getCourseConfiguration();
		List<String> retVal = autoVisitActions(courseConfiguration, graderSettingsManager);
		if (retVal ==null)
			retVal = autoVisitActions(staticConfiguration, graderSettingsManager);
		if (retVal == null)
			retVal = new ArrayList();
		return retVal;
		 

	}

	public static List<String> autoVisitActions(
			PropertiesConfiguration configuration,
			GraderSettingsManager graderSettingsManager) {
		String module = graderSettingsManager.getModule();
		String problem = graderSettingsManager.getNormalizedProblem(module);
		List retVal = configuration.getList(module + "." + problem + "."
				+ VISIT_ACTIONS, null);
//		if (retVal == null)
//			return null;
		if (retVal == null || retVal.isEmpty()) {
			retVal = configuration.getList(module + "." + VISIT_ACTIONS, null);
		}
		if (retVal == null || retVal.isEmpty()) {
			retVal = configuration.getList(DEFAULT + "." + VISIT_ACTIONS, null);
		}

		return retVal;

	}

	public static boolean getLoadClasses(PropertiesConfiguration configuration,
			GraderSettingsManager graderSettingsManager) {

		return getInheritedBooleanModuleProblemProperty(configuration,
				graderSettingsManager, LOAD_CLASSES, false);

	}

	public static boolean getAllowCompileClasses(
			PropertiesConfiguration configuration,
			GraderSettingsManager graderSettingsManager) {

		return getInheritedBooleanModuleProblemProperty(configuration,
				graderSettingsManager, ALLOW_COMPILE_CLASSES, false);

	}

	public static boolean getPrecompileClasses(
			PropertiesConfiguration configuration,
			GraderSettingsManager graderSettingsManager) {

		return getInheritedBooleanModuleProblemProperty(configuration,
				graderSettingsManager, PRE_COMPILE_CLASSES, false);

	}

	public static boolean getUnzipFiles(PropertiesConfiguration configuration,
			GraderSettingsManager graderSettingsManager) {

		return getInheritedBooleanModuleProblemProperty(configuration,
				graderSettingsManager, UNZIP_FILES, false);

	}
	
	public static boolean getCheckStyle(PropertiesConfiguration configuration,
			GraderSettingsManager graderSettingsManager) {

		return getInheritedBooleanModuleProblemProperty(configuration,
				graderSettingsManager, CHECK_STYLE, false);

	}
	public static boolean getCheckStyle() {

		return getInheritedBooleanModuleProblemProperty(
				 CHECK_STYLE, false);

	}
	
	public static boolean isForkMainProcess (){
		return getInheritedBooleanModuleProblemProperty(
				 FORK_MAIN, true);
	}
	
	public static String getCheckStyleFile() {

		return getInheritedStringModuleProblemProperty(
				 CHECK_STYLE_FILE, AnAssignmenDataFolder.DEFAULT_CONFIGURATION_FILE);

	}

	public static boolean getForceCompileClasses(
			PropertiesConfiguration configuration,
			GraderSettingsManager graderSettingsManager) {

		return getInheritedBooleanModuleProblemProperty(configuration,
				graderSettingsManager, FORCE_COMPILE_CLASSES, false);

	}

	public static boolean getPrivacy(PropertiesConfiguration configuration,
			GraderSettingsManager graderSettingsManager) {
		// String module = graderSettingsManager.getModule();
		// String problem = graderSettingsManager.getNormalizedProblem(module);
		// Boolean retVal = configuration.getBoolean(module+"." + problem + "."
		// + MAKE_CLASS_DESCRIPTION, null);
		//
		// if (retVal == null)
		// retVal = configuration.getBoolean(module+"." +
		// MAKE_CLASS_DESCRIPTION, null);
		// if (retVal == null)
		// retVal = configuration.getBoolean(DEFAULT+"." +
		// MAKE_CLASS_DESCRIPTION, false);
		//
		// return retVal;
		return getInheritedBooleanModuleProblemProperty(configuration,
				graderSettingsManager, PRIVACY, false);

	}

	public static boolean getPrivacy(PropertiesConfiguration configuration,
			String aModule, String aProblem) {
		// String module = graderSettingsManager.getModule();
		// String problem = graderSettingsManager.getNormalizedProblem(module);
		// Boolean retVal = configuration.getBoolean(module+"." + problem + "."
		// + MAKE_CLASS_DESCRIPTION, null);
		//
		// if (retVal == null)
		// retVal = configuration.getBoolean(module+"." +
		// MAKE_CLASS_DESCRIPTION, null);
		// if (retVal == null)
		// retVal = configuration.getBoolean(DEFAULT+"." +
		// MAKE_CLASS_DESCRIPTION, false);
		//
		// return retVal;
		return getInheritedBooleanModuleProblemProperty(configuration, aModule,
				aProblem, PRIVACY, false);

	}

	public static Boolean getInheritedBooleanModuleProblemProperty(
			PropertiesConfiguration configuration,
			GraderSettingsManager graderSettingsManager, String property,
			Boolean defaultValue) {
		String module = graderSettingsManager.getModule();
		String problem = graderSettingsManager.getNormalizedProblem(module);

		return getInheritedBooleanModuleProblemProperty(configuration, module,
				problem, property, defaultValue);
		// Boolean retVal = configuration.getBoolean(module+"." + problem + "."
		// + property, null);
		//
		// if (retVal == null)
		// retVal = configuration.getBoolean(module+"." + property, null);
		// if (retVal == null)
		// retVal = configuration.getBoolean(DEFAULT+"." + property,
		// defaultValue);
		//
		// return retVal;

	}

	public static Boolean getInheritedBooleanModuleProblemProperty(
			PropertiesConfiguration configuration, String module,
			String problem, String property, Boolean defaultValue) {

		Boolean retVal = configuration.getBoolean(module + "." + problem + "."
				+ property, null);

		if (retVal == null) {
			retVal = configuration.getBoolean(module + "." + property, null);
		}
		if (retVal == null) {
			retVal = configuration.getBoolean(DEFAULT + "." + property,
					defaultValue);
		}

		return retVal;

	}
	public static Boolean getInheritedBooleanModuleProblemProperty(
			String module,
			String problem, String property, Boolean aDefaultValue) {

		Boolean retVal = getCourseOrStaticBoolean(module + "." + problem + "."
				+ property, null);

		if (retVal == null) {
			retVal = getCourseOrStaticBoolean(module + "." + property, null);
		}
		if (retVal == null) {
			retVal = getCourseOrStaticBoolean(DEFAULT + "." + property,
					aDefaultValue);
		}

		return retVal;

	}
	public static Integer getInheritedIntegerModuleProblemProperty(
			String module,
			String problem, String property, Integer aDefaultValue) {

		Integer retVal = getCourseOrStaticInteger(module + "." + problem + "."
				+ property, null);

		if (retVal == null) {
			retVal = getCourseOrStaticInteger(module + "." + property, null);
		}
		if (retVal == null) {
			retVal = getCourseOrStaticInteger(DEFAULT + "." + property,
					aDefaultValue);
		}

		return retVal;

	}
	public static String getInheritedStringModuleProblemProperty(
			String module,
			String problem, String property, String aDefaultValue) {

		String retVal = getCourseOrStaticString(module + "." + problem + "."
				+ property, null);

		if (retVal == null) {
			retVal = getCourseOrStaticString(module + "." + property, null);
		}
		if (retVal == null) {
			retVal = getCourseOrStaticString(DEFAULT + "." + property,
					aDefaultValue);
		}

		return retVal;

	}

	public static Integer getInheritedIntegerModuleProblemProperty(
			PropertiesConfiguration configuration, String module,
			String problem, String property, Integer defaultValue) {

		Integer retVal = configuration.getInteger(module + "." + problem + "."
				+ property, null);

		if (retVal == null) {
			retVal = configuration.getInteger(module + "." + property, null);
		}
		if (retVal == null) {
			retVal = configuration.getInteger(DEFAULT + "." + property,
					defaultValue);
		}

		return retVal;

	}

	public static String[] getExecutionCommand(Project aProject,
			File aBuildFolder) {
		String anEntryPoint = getInheritedStringModuleProblemProperty(
				toVariable(ENTRY_POINT), null);

		return getExecutionCommand(aProject, aBuildFolder, anEntryPoint);
	}

	public static String[] getExecutionCommand(Project aProject,
			File aBuildFolder, String anEntryPoint) {

		return getExecutionCommand(aProject, null, aBuildFolder, anEntryPoint,
				"", new String[0]);

	}
	public static String[] getExecutionCommand(Project aProject,
			File aBuildFolder, String anEntryPoint, String[] anArgs) {

		return getExecutionCommand(aProject, null, aBuildFolder, anEntryPoint,
				"", anArgs);

	}

	public static List<String> getBasicCommand() {
		if (basicCommand == null) {
			basicCommand = getInheritedListModuleProblemProperty(EXECUTION_COMMAND);
		}
//		return getInheritedListModuleProblemProperty(EXECUTION_COMMAND);
		return basicCommand;

	}
	
	public static boolean hasClassPath() {
		getBasicCommand();
		if (basicCommand == null) {
			return false;
		}
		for (String aCommand:basicCommand) {
			if (aCommand.contains(CLASS_PATH_VAR) || aCommand.contains(OE_AND_CLASS_PATH_VAR)) {
				return true;
			}
		}
		return false;	
	}
	
	public static boolean hasOEClassPath() {
		getBasicCommand();
		if (basicCommand == null) {
			return false;
		}
		for (String aCommand:basicCommand) {
			if (aCommand.contains(OE_PATH_VAR) || aCommand.contains(OE_AND_CLASS_PATH_VAR)) {
				return true;
			}
		}
		return false;	
	}
	
	public static boolean hasOEOrClassPath() {
		return hasClassPath() || hasOEClassPath();
	}
	
	static String[] emptyEntryPoints = {};
	protected static String[] potentialMainEntryPoints;
//	public static String[] getPotentialMainEntryPointNames() {
//		String retVal = getInheritedStringModuleProblemProperty(ENTRY_POINT, null);
//		if (retVal != null) {
//			GraderSettingsManager manager = GraderSettingsManagerSelector.getGraderSettingsManager();
//			retVal = manager.replaceModuleProblemVars(retVal);
//			return new String[]{retVal.replaceAll(" ", "")};
//		}
//		return emptyEntryPoints;
//	}
	
	public static void setPotentialMainEntryPointNames(String[] aNames) {
		potentialMainEntryPoints = aNames;
	}
	
	public static String[] getPotentialMainEntryPointNames() {
		if (potentialMainEntryPoints == null) {
		String retVal = getInheritedStringModuleProblemProperty(ENTRY_POINT, null);
		if (retVal != null) {
			GraderSettingsManager manager = GraderSettingsManagerSelector.getGraderSettingsManager();
			retVal = manager.replaceModuleProblemVars(retVal);
			potentialMainEntryPoints = new String[]{retVal.replaceAll(" ", "")};
		} else {
			potentialMainEntryPoints = emptyEntryPoints;
		}
		}
		return potentialMainEntryPoints;
	}

	public static List<String> getBasicCommand(String aProcessName) {
		List<String> retVal = getInheritedListModuleProblemProperty(aProcessName
				+ "." + EXECUTION_COMMAND);
		if (retVal.isEmpty()) {
			return getBasicCommand();
		} else {
			return retVal;
		}
	}

	public static boolean hasEntryPoint(List<String> aCommand) {
		return hasSubString(aCommand, ENTRY_POINT);
	}

	public static boolean hasSubString(List<String> aCommand, String aSubString) {
		for (String aCommmandComponent : aCommand) {
			if (aCommmandComponent.contains(aSubString)) {
				return true;
			}
		}
		return false;
	}

	public static boolean hasEntryTag(List<String> aProcessCommand) {
		return hasSubString(aProcessCommand, ENTRY_TAG);
	}

	public static boolean hasEntryTags(List<String> aProcessCommand) {
		return hasSubString(aProcessCommand, ENTRY_TAGS);
	}

	public static boolean haArgs(String aProcessCommand) {
		return aProcessCommand.contains(ARGS);
	}
	
	static boolean doPermissions = true;
	
//	public static final String FILE_SEPARATOR = System.getProperty("file.separator");
	public static final String FILE_SEPARATOR = "/";


	public static String quotePath(String path) {
		if (!path.contains(" ")) return path;
	    boolean startSlash = path.startsWith("\\") || path.startsWith("/");
	    boolean endSlash = path.endsWith("\\") || path.endsWith("/");
	    String[] split = path.split("[\\\\/]+");

	    StringBuilder quotPath = new StringBuilder(path.length());

	    if (startSlash) {
	        quotPath.append(FILE_SEPARATOR);
	    }
	    
	    for(int i = 0; i < split.length; i ++) {
	    	String s = split[i];
	        if (s.contains(" ")) {
//	            s = "\"" + s + "\"";
	            s = "\\\"" + s + "\\\"";

	        }
	        quotPath.append(s);
	        if (i+1 < split.length) {
	            quotPath.append(FILE_SEPARATOR);
	        }
	    }
	    
	    if (endSlash) {
	        quotPath.append(FILE_SEPARATOR);
	    }
	    
	    return quotPath.toString();
	}
	
	public static int getClassPathFlagIndex(List<String> aBasicCommand) {
		int aCpIndex = aBasicCommand.indexOf("-cp");
		if (aCpIndex < 0) 
			aCpIndex = aBasicCommand.indexOf("-classpath");
		return aCpIndex;
	}
	
	public static String getExecutionCommandRawClassPath() {
		List<String> aBasicCommand = getBasicCommand();
		int aCpIndex = getClassPathFlagIndex(aBasicCommand);
		if (aCpIndex < 0)
			return null;
		if (aCpIndex + 1 >= aBasicCommand.size())
			return null;
		return getReplacedRawClassPath(aBasicCommand.get(aCpIndex + 1));
		
	}
	
	public static String getReplacedRawClassPath (String command) {
		// do we really need all of these ifs, more efficient without them? - debugging will be easier
					// all of these will be in the same command
					if (command.contains(CLASS_PATH_VAR)) {

						command = command.replace(CLASS_PATH_VAR,
								BasicGradingEnvironment.get().getClassPath());
					}

					if (command.contains(CLASS_PATH_SEPARATOR_VAR)) {
						command = command.replace(CLASS_PATH_SEPARATOR_VAR,
								BasicGradingEnvironment.get().getClassPathSeparator());
					}
					 
					if (command.contains(LOCAL_GRADER_PATH_VAR)) {

						command = command.replace(LOCAL_GRADER_PATH_VAR,
						// BasicGradingEnvironment.get().getClasspath());
								BasicGradingEnvironment.get().getLocalGraderClassPath());

					} 
					if (command.contains(OE_PATH_VAR)) {
						if (command.toLowerCase().contains("local")) { // we already have oeall
							command = command.replace(OE_PATH_VAR,
									// BasicGradingEnvironment.get().getClasspath());
									"");
						} else {
						command = command.replace(OE_PATH_VAR,
						// BasicGradingEnvironment.get().getClasspath());
								BasicGradingEnvironment.get().getOEClassPath());
						}

					}

					if (command.contains(JUNIT_PATH_VAR)) {
						command = command.replace(JUNIT_PATH_VAR,
								BasicGradingEnvironment.get().getJUnitClassPath());
						// } else if (command.contains(OE_AND_CLASS_PATH_VAR)) {
						// command = command.replace(OE_AND_CLASS_PATH_VAR,
						// BasicGradingEnvironment.get().getClassPath());
					} 
					String aClassPathSeparator = BasicGradingEnvironment.get().getClassPathSeparator();
					if (duplicatedClassPathSeparator == null) {
						//just avoding new String creation
						duplicatedClassPathSeparator = aClassPathSeparator + aClassPathSeparator;
					}
					// certain libraries may not exist, specially in the server, see what happens without them
					command = command.replaceAll(duplicatedClassPathSeparator, aClassPathSeparator);
					return command;
					// javac wants no quotes!
//					String anOSPath = BasicGradingEnvironment.get().toOSClassPath(command);
//					return anOSPath;
	}
	static String duplicatedClassPathSeparator;
	public static void replaceClassPathVars (List<String> basicCommand) {
		int aCpIndex = getClassPathFlagIndex(basicCommand);
		if (aCpIndex < 0)
			return ;
		
		if (aCpIndex + 1 >= basicCommand.size()) {
			Tracer.warning("Nothing follows classpath flag");
			return ;
		}
		String aReplacement = getReplacedRawClassPath(basicCommand.get(aCpIndex + 1));
		String anOSPath = BasicGradingEnvironment.get().toOSClassPath(aReplacement);
		basicCommand.set(aCpIndex + 1, anOSPath);

		
//		for (int aCommandIndex = 0; aCommandIndex < basicCommand.size(); aCommandIndex++) {
//
//			String command = basicCommand.get(aCommandIndex);
//			
////			// do we really need all of these ifs, more efficient without them? - debugging will be easier
////			// all of these will be in the same command
////			if (command.contains(CLASS_PATH_VAR)) {
////
////				command = command.replace(CLASS_PATH_VAR,
////						BasicGradingEnvironment.get().getClassPath());
////			}
////
////			if (command.contains(CLASS_PATH_SEPARATOR_VAR)) {
////				command = command.replace(CLASS_PATH_SEPARATOR_VAR,
////						BasicGradingEnvironment.get().getClassPathSeparator());
////			}
////			if (command.contains(OE_PATH_VAR)) {
////
////				command = command.replace(OE_PATH_VAR,
////				// BasicGradingEnvironment.get().getClasspath());
////						BasicGradingEnvironment.get().getOEClassPath());
////
////			} 
////			if (command.contains(JUNIT_PATH_VAR)) {
////				command = command.replace(JUNIT_PATH_VAR,
////						BasicGradingEnvironment.get().getJUnitClassPath());
////				// } else if (command.contains(OE_AND_CLASS_PATH_VAR)) {
////				// command = command.replace(OE_AND_CLASS_PATH_VAR,
////				// BasicGradingEnvironment.get().getClassPath());
////			} 
//			command = getReplacedClassPath(command);
//			basicCommand.set(aCommandIndex, command);
//		}
	}
	
	public static void replacePermissionVariables(List<String> basicCommand, Project aProject) {
		for (int aCommandIndex = 0; aCommandIndex < basicCommand.size(); aCommandIndex++) {

			String command = basicCommand.get(aCommandIndex);
			if (doPermissions && command.contains(PERMISSIONS_VAR)) {

				String aPolicyFilePath = JavaProjectToPermissionFile
						.getPermissionFile(aProject).getAbsolutePath();
				try {
					aPolicyFilePath = JavaProjectToPermissionFile
							.getPermissionFile(aProject).getCanonicalPath();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				aPolicyFilePath = aPolicyFilePath.replace("\\", "/");

				aPolicyFilePath = quotePath(aPolicyFilePath);

				command = command.replace(PERMISSIONS_VAR, aPolicyFilePath);
				basicCommand.set(aCommandIndex, command);

			}
		}
	}
	
	public static void replaceEntryPoint(List<String> basicCommand,  String anEntryPoint,
			String anEntryTagTarget) {
		for (int aCommandIndex = 0; aCommandIndex < basicCommand.size(); aCommandIndex++) {

			String command = basicCommand.get(aCommandIndex);
			if (anEntryPoint != null) {
				command = command
						.replace(toVariable(ENTRY_POINT), anEntryPoint);
			}
			if (anEntryTagTarget != null) {
				command = command.replace(toVariable(ENTRY_TAGS),
						anEntryTagTarget);
				command = command.replace(toVariable(ENTRY_TAG),
						anEntryTagTarget); // will match tags also

			}
			basicCommand.set(aCommandIndex, command);
		}
	}
	public static void replaceBuildFolder(List<String> basicCommand,  File aBuildFolder) {
		for (int aCommandIndex = 0; aCommandIndex < basicCommand.size(); aCommandIndex++) {

			String command = basicCommand.get(aCommandIndex);
			// we should always have a build folder I suppose
			// this is meant for C like programs
			if (command.contains(BUILD_FOLDER_VAR)) {
			command = command.replace(BUILD_FOLDER_VAR,
					aBuildFolder.getAbsolutePath());
			basicCommand.set(aCommandIndex, command);

			}
		}
	}
	public static void replaceArgs(List<String> basicCommand,  String[] anArgs) {
		int argsIndex = basicCommand.indexOf(toVariable(ARGS));
		if (argsIndex >= 0) {
			basicCommand.remove(argsIndex);
			for (int i = 0; i < anArgs.length; i++) {
				basicCommand.add(argsIndex + i, anArgs[i]);
			}

		}
	}

	public static String[] getExecutionCommand(Project aProject,
			String aProcessName, File aBuildFolder, String anEntryPoint,
			String anEntryTagTarget, String[] anArgs) {

		List<String> basicCommand = null;
		if (aProcessName == null || aProcessName.isEmpty()) {
		
			basicCommand = getBasicCommand();
		} else {
		
			basicCommand = getBasicCommand(aProcessName);

		}
//		List<String> retVal = new ArrayList(basicCommand.size());
		List<String> retVal = new ArrayList(basicCommand.size() + 5); // to accommodate args
		retVal.addAll(basicCommand);
		replaceClassPathVars(retVal);
		replacePermissionVariables(retVal, aProject);
		replaceEntryPoint(retVal, anEntryPoint, anEntryTagTarget);
		replaceBuildFolder(retVal, aBuildFolder);
		replaceArgs(retVal, anArgs);
		return retVal.toArray(new String[0]);


//		
//		
//		for (int aCommandIndex = 0; aCommandIndex < basicCommand.size(); aCommandIndex++) {
//
//			String command = basicCommand.get(aCommandIndex);
//			if (command.contains(CLASS_PATH_VAR)) {
//
//				command = command.replace(CLASS_PATH_VAR,
//						BasicGradingEnvironment.get().getClassPath());
//
//			} else if (command.contains(CLASS_PATH_SEPARATOR_VAR)) {
//				command = command.replace(CLASS_PATH_SEPARATOR_VAR,
//						BasicGradingEnvironment.get().getClassPathSeparator());
//			} else if (command.contains(OE_PATH_VAR)) {
//
//				command = command.replace(OE_PATH_VAR,
//				// BasicGradingEnvironment.get().getClasspath());
//						BasicGradingEnvironment.get().getOEClassPath());
//
//			} else if (command.contains(JUNIT_PATH_VAR)) {
//				command = command.replace(JUNIT_PATH_VAR,
//						BasicGradingEnvironment.get().getJUnitClassPath());
//				// } else if (command.contains(OE_AND_CLASS_PATH_VAR)) {
//				// command = command.replace(OE_AND_CLASS_PATH_VAR,
//				// BasicGradingEnvironment.get().getClassPath());
//			} else if (doPermissions && command.contains(PERMISSIONS_VAR)) {
//
//				String aPolicyFilePath = JavaProjectToPermissionFile
//						.getPermissionFile(aProject).getAbsolutePath();
//				try {
//					aPolicyFilePath = JavaProjectToPermissionFile
//							.getPermissionFile(aProject).getCanonicalPath();
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//				aPolicyFilePath = aPolicyFilePath.replace("\\", "/");
//
//				aPolicyFilePath = quotePath(aPolicyFilePath);
//
//				command = command.replace(PERMISSIONS_VAR, aPolicyFilePath
//
//				);
//			}
//
//			if (anEntryPoint != null) {
//				command = command
//						.replace(toVariable(ENTRY_POINT), anEntryPoint);
//			}
//			if (anEntryTagTarget != null) {
//				command = command.replace(toVariable(ENTRY_TAGS),
//						anEntryTagTarget);
//				command = command.replace(toVariable(ENTRY_TAG),
//						anEntryTagTarget); // will match tags also
//
//			}
//
//			command = command.replace(toVariable(BUILD_FOLDER),
//					aBuildFolder.getAbsolutePath());
//
//			retVal.add(command);
//		}
//		int argsIndex = retVal.indexOf(toVariable(ARGS));
//		if (argsIndex >= 0) {
//			retVal.remove(argsIndex);
//			for (int i = 0; i < anArgs.length; i++) {
//				retVal.add(argsIndex + i, anArgs[i]);
//			}
//
//		}
//		return retVal.toArray(new String[0]);

	}
	// this stuff worked with oritinal class path stuff
//	public static String[] getExecutionCommand(Project aProject,
//			String aProcessName, File aBuildFolder, String anEntryPoint,
//			String anEntryTagTarget, String[] anArgs) {
//
//		List<String> basicCommand = null;
//		if (aProcessName == null || aProcessName.isEmpty()) {
//			// basicCommand =
//			// getInheritedListModuleProblemProperty(EXECUTION_COMMAND);
//			basicCommand = getBasicCommand();
//		} else {
//			// basicCommand = getInheritedListModuleProblemProperty(aProcessName
//			// + "." + EXECUTION_COMMAND);
//			basicCommand = getBasicCommand(aProcessName);
//
//		}
//		List<String> retVal = new ArrayList(basicCommand.size());
//		// for (int aCommandIndex = 0; aCommandIndex < basicCommand.size();
//		// aCommandIndex++) {
//		//
//		// String command =
//		// basicCommand.get(aCommandIndex).replace(toVariable(CLASS_PATH),
//		// GradingEnvironment.get().getClasspath());
//		// command = command.replace(toVariable(PERMISSIONS),
//		// "\"" +
//		// JavaProjectToPermissionFile.getPermissionFile(aProject).getAbsolutePath()
//		// + "\"");
//		// if (anEntryPoint != null) {
//		// command = command.replace(toVariable(ENTRY_POINT), anEntryPoint);
//		// }
//		// if (anEntryTagTarget != null) {
//		// command = command.replace(toVariable(ENTRY_TAGS), anEntryTagTarget);
//		// command = command.replace(toVariable(ENTRY_TAG), anEntryTagTarget);
//		// // will match tags also
//		//
//		// }
//		//
//		// // if (anEntryTagTarget != null)
//		// command = command.replace(toVariable(BUILD_FOLDER),
//		// aBuildFolder.getAbsolutePath());
//		//
//		// retVal.add(command);
//		// }
//
//		for (int aCommandIndex = 0; aCommandIndex < basicCommand.size(); aCommandIndex++) {
//
//			String command = basicCommand.get(aCommandIndex);
//			if (command.contains(CLASS_PATH_VAR)) {
////				command = command.replace(CLASS_PATH_VAR, "\""
////						+ GradingEnvironment.get().getClasspath() + "\"");
//				command = command.replace(CLASS_PATH_VAR, 
//						BasicGradingEnvironment.get().getClasspath());
//
//				// } else if (command.contains(PERMISSIONS_VAR)) {
//				// command = command.replace(PERMISSIONS_VAR,
//				// "\"" +
//				// JavaProjectToPermissionFile.getPermissionFile(aProject).getAbsolutePath()
//				// + "\"");
//				// }
//			} else if (command.contains(OE_PATH_VAR)) {
////				command = command.replace(CLASS_PATH_VAR, "\""
////				+ GradingEnvironment.get().getClasspath() + "\"");
//		         command = command.replace(OE_PATH_VAR, 
//				BasicGradingEnvironment.get().getClasspath());
//
//	      } else if (command.contains(OE_AND_CLASS_PATH_VAR)) {
//	    	   command = command.replace(OE_AND_CLASS_PATH_VAR, 
//	   				BasicGradingEnvironment.get().getClasspath());
//	      }
//			else if (doPermissions && command.contains(PERMISSIONS_VAR)) {
//				// URL policyFileURL =
//				// Class.class.getResource("/server/model/easy.policy");
//				String aPolicyFilePath = JavaProjectToPermissionFile
//						.getPermissionFile(aProject).getAbsolutePath();
//				try {
//					aPolicyFilePath = JavaProjectToPermissionFile
//							.getPermissionFile(aProject).getCanonicalPath();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				aPolicyFilePath = aPolicyFilePath.replace("\\", "/");
//
////				aPolicyFilePath = "C:/Users/dewan/Downloads/A7/\"Assignment 7\"/permissions0.txt";
//				
//				aPolicyFilePath = quotePath(aPolicyFilePath);
//
////				try {
////					URL aPermissionFileURL = Class.class
////							.getResource(aPolicyFilePath);
////
////					aPolicyFilePath = URLDecoder.decode(
////							aPermissionFileURL.getFile(), "UTF-8");
////				} catch (Exception e) {
////					System.err.println("Could not decode: " + e.getMessage());
////				}
//				command = command.replace(
//						PERMISSIONS_VAR,
////						"\"" +
//								aPolicyFilePath
////								JavaProjectToPermissionFile
////										.getPermissionFile(aProject)
////										.getAbsolutePath() + 
////										+ "\""
//								);
//			}
//
//			if (anEntryPoint != null) {
//				command = command
//						.replace(toVariable(ENTRY_POINT), anEntryPoint);
//			}
//			if (anEntryTagTarget != null) {
//				command = command.replace(toVariable(ENTRY_TAGS),
//						anEntryTagTarget);
//				command = command.replace(toVariable(ENTRY_TAG),
//						anEntryTagTarget); // will match tags also
//
//			}
//
//			// if (anEntryTagTarget != null)
//			command = command.replace(toVariable(BUILD_FOLDER),
//					aBuildFolder.getAbsolutePath());
//
//			retVal.add(command);
//		}
//		int argsIndex = retVal.indexOf(toVariable(ARGS));
//		if (argsIndex >= 0) {
//			retVal.remove(argsIndex);
//			for (int i = 0; i < anArgs.length; i++) {
//				retVal.add(argsIndex + i, anArgs[i]);
//			}
//
//		}
//		return retVal.toArray(new String[0]);
//
//	}
	public static List getCourseOrStaticList(String aProperty, List aDefault) {
		PropertiesConfiguration staticConfiguration = ConfigurationManagerSelector
				.getConfigurationManager().getStaticConfiguration();
		PropertiesConfiguration courseConfiguration = ConfigurationManagerSelector
				.getConfigurationManager().getCourseConfiguration();
		 List aRetVal = courseConfiguration.getList(aProperty, aDefault);
         if (aRetVal == null)
         	aRetVal = staticConfiguration.getList(aProperty, aDefault);
         return aRetVal;		
	}
	
	public static String getCourseOrStaticString(String aProperty, String aDefault) {
		PropertiesConfiguration staticConfiguration = ConfigurationManagerSelector
				.getConfigurationManager().getStaticConfiguration();
		PropertiesConfiguration courseConfiguration = ConfigurationManagerSelector
				.getConfigurationManager().getCourseConfiguration();
		 String aRetVal = courseConfiguration.getString(aProperty, aDefault);
         if (aRetVal == null)
         	aRetVal = staticConfiguration.getString(aProperty, aDefault);
         return aRetVal;		
	}
	
	public static Boolean getCourseOrStaticBoolean(String aProperty, Boolean aDefault) {
		PropertiesConfiguration staticConfiguration = ConfigurationManagerSelector
				.getConfigurationManager().getStaticConfiguration();
		PropertiesConfiguration courseConfiguration = ConfigurationManagerSelector
				.getConfigurationManager().getCourseConfiguration();
		 Boolean aRetVal = courseConfiguration.getBoolean(aProperty, aDefault);
         if (aRetVal == null)
         	aRetVal = staticConfiguration.getBoolean(aProperty, aDefault);
         return aRetVal;		
	}
	
	public static Integer getCourseOrStaticInteger(String aProperty, Integer aDefault) {
		PropertiesConfiguration staticConfiguration = ConfigurationManagerSelector
				.getConfigurationManager().getStaticConfiguration();
		PropertiesConfiguration courseConfiguration = ConfigurationManagerSelector
				.getConfigurationManager().getCourseConfiguration();
		 Integer aRetVal = courseConfiguration.getInteger(aProperty, aDefault);
         if (aRetVal == null)
         	aRetVal = staticConfiguration.getInteger(aProperty, aDefault);
         return aRetVal;		
	}

	public static String getInheritedStringModuleProblemProperty(
			String property, String defaultValue) {
//		PropertiesConfiguration configuration = ConfigurationManagerSelector
//				.getConfigurationManager().getStaticConfiguration();
		GraderSettingsManager graderSettingsManager = GraderSettingsManagerSelector
				.getGraderSettingsManager();
		String aModule = graderSettingsManager.getModule();
		String aProblem = graderSettingsManager.getNormalizedProblem(aModule);
//		return getInheritedStringModuleProblemProperty(configuration, aModule,
//				aProblem, property, defaultValue);
		return getInheritedStringModuleProblemProperty(aModule,
				aProblem, property, defaultValue);

	}

	public static Boolean getInheritedBooleanModuleProblemProperty(
			String property, boolean defaultValue) {
//		PropertiesConfiguration configuration = ConfigurationManagerSelector
//				.getConfigurationManager().getStaticConfiguration();
		GraderSettingsManager graderSettingsManager = GraderSettingsManagerSelector
				.getGraderSettingsManager();
		String aModule = graderSettingsManager.getModule();
		if (aModule ==null) {
			System.err.println("Null module:");
			return defaultValue;
		}
		String aProblem = graderSettingsManager.getNormalizedProblem(aModule);
//		return getInheritedBooleanModuleProblemProperty(configuration, aModule,
//				aProblem, property, defaultValue);
		return getInheritedBooleanModuleProblemProperty(aModule,
				aProblem, property, defaultValue);

	}

	public static Integer getInheritedIntegerModuleProblemProperty(
			String property, Integer defaultValue) {
//		PropertiesConfiguration configuration = ConfigurationManagerSelector
//				.getConfigurationManager().getStaticConfiguration();
		GraderSettingsManager graderSettingsManager = GraderSettingsManagerSelector
				.getGraderSettingsManager();
		String aModule = graderSettingsManager.getModule();
		String aProblem = graderSettingsManager.getNormalizedProblem(aModule);
//		return getInheritedIntegerModuleProblemProperty(configuration, aModule,
//				aProblem, property, defaultValue);
		return getInheritedIntegerModuleProblemProperty( aModule,
		aProblem, property, defaultValue);

	}

	public static String getLanguage() {
		return getInheritedStringModuleProblemProperty(LANGUAGE, JAVA);

	}

	public static List<String> getInheritedListModuleProblemProperty(
			String property) {
		PropertiesConfiguration configuration = ConfigurationManagerSelector
				.getConfigurationManager().getStaticConfiguration();
		PropertiesConfiguration courseConfiguration = ConfigurationManagerSelector
				.getConfigurationManager().getCourseConfiguration();
		GraderSettingsManager graderSettingsManager = GraderSettingsManagerSelector
				.getGraderSettingsManager();
		String aModule = graderSettingsManager.getModule();
		String aProblem = graderSettingsManager.getNormalizedProblem(aModule);
		List<String> retVal = getInheritedListModuleProblemProperty(courseConfiguration, aModule,
				aProblem, property);
		if (retVal.isEmpty())
			retVal =  getInheritedListModuleProblemProperty(configuration, aModule,
					aProblem, property);
		return retVal;

	}

	// public static String getInheritedStringModuleProblemProperty( String
	// property, String defaultValue) {
	// PropertiesConfiguration configuration =
	// ConfigurationManagerSelector.getConfigurationManager().getStaticConfiguration();
	// GraderSettingsManager graderSettingsManager =
	// GraderSettingsManagerSelector.getGraderSettingsManager();
	// String aModule = graderSettingsManager.getModule();
	// String aProblem = graderSettingsManager.getNormalizedProblem(aModule);
	// return getInheritedStringModuleProblemProperty(configuration, aModule ,
	// aProblem, property, null);
	//
	// }
	public static String getInheritedStringModuleProblemProperty(
			PropertiesConfiguration configuration, String module,
			String problem, String property, String defaultValue) {

		String retVal = configuration.getString(module + "." + problem + "."
				+ property, null);

		if (retVal == null) {
			retVal = configuration.getString(module + "." + property, null);
		}
		if (retVal == null) {
			retVal = configuration.getString(DEFAULT + "." + property,
					defaultValue);
		}

		return retVal;

	}

	public static List<String> getInheritedListModuleProblemProperty(
			PropertiesConfiguration configuration, String module,
			String problem, String property) {

		List retVal = configuration.getList(module + "." + problem + "."
				+ property);

		if (retVal.isEmpty()) {
			retVal = configuration.getList(module + "." + property);
		}
		if (retVal.isEmpty()) {
			retVal = configuration.getList(DEFAULT + "." + property);
		}

		return retVal;

	}
	public static List<String> getInheritedListModuleProblemProperty(
			String module,
			String problem, String property) {

		List retVal = getCourseOrStaticList(module + "." + problem + "."
				+ property, null);

		if (retVal.isEmpty()) {
			retVal = getCourseOrStaticList(module + "." + property, null);
		}
		if (retVal.isEmpty()) {
			retVal = getCourseOrStaticList(DEFAULT + "." + property, null);
		}

		return retVal;

	}

	public static ProjectRequirements getProjectRequirements(
			PropertiesConfiguration configuration,
			GraderSettingsManager graderSettingsManager) {

		ProjectRequirements requirements = null;

		String requirementsSpec = "";

		try {
			requirementsSpec = getInheritedStringModuleProblemProperty(
					REQUIREMENTS,
					configuration.getString("project.requirements"));

			// compatibility with Josh's spec
			// String module = graderSettingsManager.getModule();
			// requirementsSpec = configuration.getString(module +
			// ".requirements");
			// if (requirementsSpec == null) {
			// requirementsSpec = configuration.getString(module.toLowerCase() +
			// ".requirements");
			// if (requirementsSpec == null) {
			// requirementsSpec =
			// configuration.getString("default.requirements");
			// if (requirementsSpec == null) {
			// requirementsSpec =
			// configuration.getString("project.requirements"); // upward
			// compatibilty
			// }
			// }
			// }
			String normalizedRequirementsSpec = graderSettingsManager
					.replaceModuleProblemVars(requirementsSpec);

			// }
			// Class<?> _class =
			// Class.forName(configuration.getString("project.requirements"));
			Class<?> _class = Class.forName(normalizedRequirementsSpec);

			requirements = (ProjectRequirements) _class.newInstance();
		} catch (ClassNotFoundException e) {
			requirements = getInterpretedRequirements();
			if (requirements == null)
				System.err.println("Could not find project requirements:"
						+ requirementsSpec);
			// System.err.println(e.getMessage());
		} catch (InstantiationException e) {
			System.err.println("Could not create project requirements."
					+ requirements);
			System.err.println(e.getMessage());
		} catch (IllegalAccessException e) {
			System.err.println("Could not create project requirements."
					+ requirements);
			System.err.println(e.getMessage());
		}
		return requirements;

	}

	public static ProjectRequirements getInterpretedRequirements() {
		// SakaiProjectDatabase aDatabase = null;

		SakaiProjectDatabase aDatabase = ASakaiProjectDatabase
				.getCurrentSakaiProjectDatabase();
		try {
			CSVRequirementsSpecification aSpecification = aDatabase
					.getCSVRequirementsSpecification();
			return new AnInterpretedRequirements(aSpecification);

		} catch (Exception e) {
			System.out
					.println("Could not find interpreted requirements "
							+ AnAssignmenDataFolder.DEFAULT_REQUIREMENTS_SPREADHEET_NAME
							+ " in assignment data folder:"
							+ aDatabase.getAssignmentDataFolder()
									.getMixedCaseAbsoluteName());
			return null;
			// e.printStackTrace();
		}

	}

	public static List<String> getProcessTeams() {
		return getInheritedListModuleProblemProperty(PROCESS_TEAMS);
	}

	public static List<String> getProcessArgs(String aProcess) {
		return getInheritedListModuleProblemProperty(aProcess + "." + ARGS);
	}

	public static List<String> getProcessStartTags(String aProcess) {
		return getInheritedListModuleProblemProperty(aProcess + "."
				+ START_TAGS);
	}

	public static Boolean getTrace() {
		return getInheritedBooleanModuleProblemProperty(GENERATE_TRACE_FILES,
				false);
	}

	public static final int DEFAULT_SLEEP_TIME = 2000;

	public static Integer getSleepTime(String aProcess) {
		return getInheritedIntegerModuleProblemProperty(aProcess + "."
				+ SLEEP_TIME, DEFAULT_SLEEP_TIME);
	}

	public static String getEntryTag(String aProcess) {
		return getInheritedStringModuleProblemProperty(aProcess + "."
				+ ENTRY_TAG, null);
	}

	public static List<String> getEntryTags(String aProcess) {
		return getInheritedListModuleProblemProperty(aProcess + "."
				+ ENTRY_TAGS);
	}

	public static String getEntryPoint(String aProcess) {
		return getInheritedStringModuleProblemProperty(aProcess + "."
				+ ENTRY_POINT, null);
	}

	public static List<String> getProcesses(String aProcessTeam) {
		return getInheritedListModuleProblemProperty(aProcessTeam);
	}

	public static List<String> getTerminatingProcesses(String aProcessTeam) {
		return getInheritedListModuleProblemProperty(aProcessTeam + "."
				+ TERMINATING);
	}

}
