package gradingTools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import framework.grading.FrameworkProjectRequirements;
import framework.grading.GradingManager;
import framework.grading.ProjectRequirements;
import framework.gui.SettingsWindow;
import framework.logging.loggers.*;
import framework.logging.recorder.ConglomerateRecorder;
import framework.logging.recorder.ConglomerateRecorderFactory;
import framework.utils.GraderSettings;
import framework.utils.GradingEnvironment;
import util.misc.Common;
import util.trace.TraceableBus;
import util.trace.Tracer;
import wrappers.grader.sakai.project.ProjectDatabaseWrapper;
import wrappers.grader.sakai.project.ProjectStepperDisplayerWrapper;
import grader.config.AConfigurationManager;
import grader.config.ConfigurationManagerSelector;
import grader.modules.ModuleProblemManager;
import grader.modules.ModuleProblemManagerSelector;
import grader.navigation.filter.AGradingStatusFilter;
import grader.navigation.filter.NavigationFilter;
import grader.sakai.project.ASakaiProjectDatabase;
import grader.settings.AGraderSettingsModel;
import grader.settings.GraderSettingsManager;
import grader.settings.GraderSettingsManagerSelector;
import grader.settings.GraderSettingsModel;
import grader.settings.navigation.NavigationFilterRepository;
import grader.spreadsheet.BasicFeatureGradeRecorderSelector;
import grader.spreadsheet.FeatureGradeRecorderSelector;
import grader.spreadsheet.csv.AFeatureGradeRecorderFactory;
import grader.trace.GraderTracerSelector;
import grader.trace.MissingOnyenException;
import grader.trace.overall_notes.OverallNotesChanged;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;

/**
 * This is the entry class for the grading tools that Maven will reference.
 * Use config.properties to configure what gets run.
 */
public class Driver {
	
	public static void setTracing() {
		Tracer.showInfo(true);
		Tracer.setKeywordPrintStatus(OverallNotesChanged.class, true);
	}
	
	public static void initLoggers(ProjectRequirements requirements, PropertiesConfiguration configuration) {
		try {
		// Logging
        ConglomerateRecorder recorder = ConglomerateRecorder.getInstance();
        recorder.setProjectRequirements(requirements);

        String[] loggingMethods = configuration.getString("grader.logger", "csv").split("\\s*\\+\\s*");
       //lazy coding means feedback should be the last step so that isSaved works correctly

        for (String method :loggingMethods) {

            // Add loggers
            if (method.equals("local") || method.equals("local-txt"))
                recorder.addLogger(new LocalTextSummaryLogger());
            if (method.equals("local") || method.equals("local-json"))
                recorder.addLogger(new LocalJsonLogger());
            if (method.equals("feedback") || method.equals("feedback-txt"))
                recorder.addLogger(new FeedbackTextSummaryLogger());
            if (method.equals("feedback") || method.equals("feedback-json"))
                recorder.addLogger(new FeedbackJsonLogger());
            if (method.equals("spreadsheet"))
                recorder.addLogger(new SpreadsheetLogger(requirements));
            if (method.equals("csv"))
                recorder.addLogger(new CsvLogger());
        }
		} catch (ConfigurationException e) {
            System.err.println("Error loading config file.");
            System.err.println(e.getMessage());
		}
		
	}
	
	public static String VISIT_ACTIONS = "visitActions";
	public static String AUTO_GRADE = "autoGrade";
	public static String AUTO_RUN = "autoRun";
	
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
	
	public  static ProjectRequirements  getProjectRequirements(PropertiesConfiguration configuration,
			GraderSettingsManager graderSettingsManager ) {
		ProjectRequirements requirements = null;
		
		try {
			// compatibility with Josh's spec
//			String requirementsSpec = configuration.getString("project.requirements");
//			if (requirementsSpec == null) {
//				Comp401.requirements = gradingTools.{problemname}
				String module = graderSettingsManager.getModule();
				String requirementsSpec = configuration.getString(module + ".requirements");
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
        System.err.println("Could not find project requirements.");
        System.err.println(e.getMessage());
	}  catch (InstantiationException e) {
        System.err.println("Could not create project requirements.");
        System.err.println(e.getMessage());
    } catch (IllegalAccessException e) {
        System.err.println("Could not create project requirements.");
        System.err.println(e.getMessage());
    }
		return requirements;
		
		
	}

    public static void main(String[] args) {
    	setTracing();
		ObjectEditor.setDefaultAttribute(AttributeNames.SHOW_DEBUG_INFO_WITH_TOOL_TIP, false);

//    	String[] retVal = "main.foo".split(".");
//    	retVal = "main:foo".split(":");
//    	retVal = "main.foo.foo".split("\\.");

//        try {
        
        	
            // Load the config file
//        	GradingEnvironment.get().setConfigurationManager(new AConfigurationManager());
    		TraceableBus.addTraceableListener(GraderTracerSelector.getGraderTracer());

        	PropertiesConfiguration configuration = ConfigurationManagerSelector.getConfigurationManager().getStaticConfiguration();
        	ModuleProblemManager moduleProgramManager = ModuleProblemManagerSelector.getModuleProblemManager();
        	GraderSettingsManager graderSettingsManager = GraderSettingsManagerSelector.getGraderSettingsManager();
//        	moduleProgramManager.init(graderSettingsManager);
//        	graderSettingsManager.init(moduleProgramManager);
//        	PropertiesConfiguration configuration = GradingEnvironment.get().getConfigurationManager().getStaticConfiguration();
//            PropertiesConfiguration configuration = new PropertiesConfiguration("./config/config.properties");
//            GradingEnvironment.get().getConfigurationManager().setStaticConfiguration(configuration);
//            String dynamicConfigurationHolder = configuration.getString("grader.dynamicConfiguration", "dynamicconfig.properties");
//            File file = new File(dynamicConfigurationHolder);
//            if (!file.exists()) {
//            	file.createNewFile();
//            }
//            PropertiesConfiguration dynamicConfiguration =  new PropertiesConfiguration(dynamicConfigurationHolder);
//            AConfigurationManager.setDynamicConfiguration(dynamicConfiguration);
//
//            GraderSettings.get().convertToDynamicConfiguration();
//        	GraderSettings.get().convertToDynamicConfiguration();
       	   
            // Get the project name
            String projectName = configuration.getString("project.name");
//            Object projectProperty = configuration.getProperty("project.name");
            GradingEnvironment.get().setAssignmentName(projectName);
            
//                        
//            String defaultAssignmentsDataFolderName = configuration.getString("grader.defaultAssignmentsDataFolderName");
//            defaultAssignmentsDataFolderName = moduleProgramManager.replaceModuleProblemVars(defaultAssignmentsDataFolderName);
//            GradingEnvironment.get().setDefaultAssignmentsDataFolderName(defaultAssignmentsDataFolderName);

            // Get the project requirements
//            Class<?> _class = Class.forName(configuration.getString("project.requirements"));
//            ProjectRequirements requirements = (ProjectRequirements) _class.newInstance();
//            ProjectRequirements requirements = getProjectRequirements(configuration);
            ProjectRequirements requirements = null;
//
//            // Logging
            ConglomerateRecorder recorder = ConglomerateRecorder.getInstance();
//            recorder.setProjectRequirements(requirements);
//            initLoggers(requirements, configuration);

//            String[] loggingMethods = configuration.getString("grader.logger", "csv").split("\\s*\\+\\s*");
//           //lazy coding means feedback should be the last step so that isSaved works correctly
//
//            for (String method :loggingMethods) {
//
//                // Add loggers
//                if (method.equals("local") || method.equals("local-txt"))
//                    recorder.addLogger(new LocalTextSummaryLogger());
//                if (method.equals("local") || method.equals("local-json"))
//                    recorder.addLogger(new LocalJsonLogger());
//                if (method.equals("feedback") || method.equals("feedback-txt"))
//                    recorder.addLogger(new FeedbackTextSummaryLogger());
//                if (method.equals("feedback") || method.equals("feedback-json"))
//                    recorder.addLogger(new FeedbackJsonLogger());
//                if (method.equals("spreadsheet"))
//                    recorder.addLogger(new SpreadsheetLogger(requirements));
//                if (method.equals("csv"))
//                    recorder.addLogger(new CsvLogger());
//            }

            // Run the grading process
            String controller = configuration.getString("grader.controller", "GradingManager");
            GraderSettingsModel settingsModel = null;
            OEFrame settingsFrame = null;
            String goToOnyen = "";
            
            if (controller.equals("GradingManager")) {
            	  requirements = getProjectRequirements(configuration, graderSettingsManager);

                 // Logging
//                 ConglomerateRecorder recorder = ConglomerateRecorder.getInstance();
                 recorder.setProjectRequirements(requirements);
            	initLoggers(requirements, configuration);

                // Run the GraderManager
                GradingManager manager = new GradingManager(projectName, requirements);
                manager.run();

            } else if (controller.equals("SakaiProjectDatabase")) {
//            	 // Logging/results saving
//                FeatureGradeRecorderSelector.setFactory(new ConglomerateRecorderFactory());
//                BasicFeatureGradeRecorderSelector.setFactory(new AFeatureGradeRecorderFactory());
//            	 ProjectDatabaseWrapper database = new ProjectDatabaseWrapper();
            	 String settings = configuration.getString("grader.settings", "oe");
//            	 String settingsTry = configuration.getString("Grader.Settings");
            	 if (settings.equalsIgnoreCase("oe")) {
                 	NavigationFilter gradingBasedFilterer = new AGradingStatusFilter();
                 	NavigationFilterRepository.register(gradingBasedFilterer);

            		  settingsModel = new AGraderSettingsModel(null);
            			settingsFrame = ObjectEditor.edit(settingsModel);
            			settingsFrame.setTitle("Grader Assistant Starter");
//            			frame.setSize(550, 250);
//            			settingsFrame.setSize(550, 475);
//            			settingsFrame.setSize(550, 530);
            			settingsFrame.setSize(600, 550);


            			settingsModel.awaitBegin();
            			projectName = settingsModel.getCurrentProblem(); // get the current one
            			  GradingEnvironment.get().setAssignmentName(projectName);
            			  requirements = getProjectRequirements(configuration, graderSettingsManager);

                         // Logging
//                         ConglomerateRecorder recorder = ConglomerateRecorder.getInstance();
                         recorder.setProjectRequirements(requirements);
                      initLoggers(requirements, configuration);
                      String defaultAssignmentsDataFolderName = configuration.getString("grader.defaultAssignmentsDataFolderName");
                      defaultAssignmentsDataFolderName = graderSettingsManager.replaceModuleProblemVars(defaultAssignmentsDataFolderName);
                      GradingEnvironment.get().setDefaultAssignmentsDataFolderName(defaultAssignmentsDataFolderName);

//            			goToOnyen = settingsModel.getOnyens().getGoToOnyen();
//            			settingsFrame.dispose();
            	 
            		 
            	 } else {

                // Start the grading process by, first, getting the settings the running the project database
                SettingsWindow settingsWindow = SettingsWindow.create();
                settingsWindow.awaitBegin();
//                ASakaiProjectDatabase.setCurrentSakaiProjectDatabase(new ASakaiProjectDatabase(settingsWindow.getDownloadPath(), null, settingsWindow.getStart(), settingsWindow.getEnd()));
            	 }
//            	 String projectName = configuration.getString("project.name");
////               Object projectProperty = configuration.getProperty("project.name");
//               GradingEnvironment.get().setAssignmentName(projectName);
               

                // Logging/results saving
                FeatureGradeRecorderSelector.setFactory(new ConglomerateRecorderFactory());
                BasicFeatureGradeRecorderSelector.setFactory(new AFeatureGradeRecorderFactory());

                // Create the database
                ProjectDatabaseWrapper database = new ProjectDatabaseWrapper();
                database.setGraderSettings(settingsModel);
                database.setScoreFeedback(null); // we will be writing to feedback file which is more complete
//             ASakaiProjectDatabase.setCurrentSakaiProjectDatabase(database);

                database.addProjectRequirements(requirements);
                
                ConglomerateRecorder.getInstance().setBasicFeatureGradeRecorder(BasicFeatureGradeRecorderSelector.createFeatureGradeRecorder(database));

                // Possibly set the stepper displayer
                boolean useFrameworkGUI = configuration.getBoolean("grader.controller.useFrameworkGUI", false);
                if (useFrameworkGUI)
                    database.setProjectStepperDisplayer(new ProjectStepperDisplayerWrapper());

                // Feedback
//                database.setAutoFeedback(ConglomerateRecorder.getInstance());
                database.setManualFeedback(ConglomerateRecorder.getInstance());
                List<String> visitActions = autoVisitActions(configuration, graderSettingsManager);
                if (visitActions.contains(AUTO_GRADE))                	
                	database.getOrCreateProjectStepper().setAutoAutoGrade(true);
                if (visitActions.contains(AUTO_RUN))  
                	database.getOrCreateProjectStepper().setAutoRun(true);

                database.getProjectNavigator().navigate(settingsModel, settingsFrame, true);                
//				while (true) {
//					try {
//						if (settingsModel != null)
//						goToOnyen = settingsModel.getOnyens().getGoToOnyen();
//						boolean retVal = database.nonBlockingRunProjectsInteractively(goToOnyen);
//						if (retVal)
//							break;
//						else
//							Tracer.error("Did not find any entries matching filter. Try again.");
//						
//					} catch (MissingOnyenException moe) {
//						Tracer.error("Student:" + goToOnyen + " not in specified range. Try again.");
//					}
//
//
//					if (settingsModel != null)
//						settingsModel.awaitBegin();
//
//				}
//				if (settingsFrame != null)
//					settingsFrame.dispose();
			}


//        } catch (ConfigurationException e) {
//            System.err.println("Error loading config file.");
//            System.err.println(e.getMessage());
//        } catch (ClassNotFoundException e) {
//            System.err.println("Could not find project requirements.");
//            System.err.println(e.getMessage());
//        } catch (InstantiationException e) {
//            System.err.println("Could not create project requirements.");
//            System.err.println(e.getMessage());
//        } catch (IllegalAccessException e) {
//            System.err.println("Could not create project requirements.");
//            System.err.println(e.getMessage());
//        }
////        catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
    }
}
