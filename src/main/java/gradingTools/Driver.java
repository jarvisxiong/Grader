package gradingTools;

import framework.grading.FrameworkProjectRequirements;
import framework.grading.GradingManager;
import framework.grading.ProjectRequirements;
import framework.gui.SettingsWindow;
import framework.logging.loggers.*;
import framework.logging.recorder.ConglomerateRecorder;
import framework.logging.recorder.ConglomerateRecorderFactory;
import framework.utils.GradingEnvironment;
import util.misc.Common;
import util.trace.Tracer;
import wrappers.grader.sakai.project.ProjectDatabaseWrapper;
import wrappers.grader.sakai.project.ProjectStepperDisplayerWrapper;
import grader.navigation.filter.AGradingStatusFilter;
import grader.navigation.filter.NavigationFilter;
import grader.sakai.project.ASakaiProjectDatabase;
import grader.settings.AGraderSettingsModel;
import grader.settings.GraderSettingsModel;
import grader.settings.navigation.NavigationFilterRepository;
import grader.spreadsheet.BasicFeatureGradeRecorderSelector;
import grader.spreadsheet.FeatureGradeRecorderSelector;
import grader.spreadsheet.csv.AFeatureGradeRecorderFactory;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;

/**
 * This is the entry class for the grading tools that Maven will reference.
 * Use config.properties to configure what gets run.
 */
public class Driver {

    public static void main(String[] args) {

        try {
            // Load the config file
            PropertiesConfiguration configuration = new PropertiesConfiguration("./config/config.properties");

            // Get the project name
            String projectName = configuration.getString("project.name");
            GradingEnvironment.get().setAssignmentName(projectName);
            
                        
            String defaultAssignmentsDataFolderName = configuration.getString("grader.defaultAssignmentsDataFolderName");
            GradingEnvironment.get().setDefaultAssignmentsDataFolderName(defaultAssignmentsDataFolderName);

            // Get the project requirements
            Class<?> _class = Class.forName(configuration.getString("project.requirements"));
            ProjectRequirements requirements = (ProjectRequirements) _class.newInstance();

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

            // Run the grading process
            String controller = configuration.getString("grader.controller", "GradingManager");
            GraderSettingsModel settingsModel = null;
            OEFrame settingsFrame = null;
            
            if (controller.equals("GradingManager")) {

                // Run the GraderManager
                GradingManager manager = new GradingManager(projectName, requirements);
                manager.run();

            } else if (controller.equals("SakaiProjectDatabase")) {
            	
            	 String settings = configuration.getString("grader.settings", "oe");
            	 if (settings.equalsIgnoreCase("oe")) {
                 	NavigationFilter gradingBasedFilterer = new AGradingStatusFilter();
                 	NavigationFilterRepository.register(gradingBasedFilterer);

            		  settingsModel = new AGraderSettingsModel();
            			settingsFrame = ObjectEditor.edit(settingsModel);
            			settingsFrame.setTitle("Grader Settings");
//            			frame.setSize(550, 250);
            			settingsFrame.setSize(550, 475);
            			settingsModel.awaitBegin();
//            			settingsFrame.dispose();
            	 
            		 
            	 } else {

                // Start the grading process by, first, getting the settings the running the project database
                SettingsWindow settingsWindow = SettingsWindow.create();
                settingsWindow.awaitBegin();
//                ASakaiProjectDatabase.setCurrentSakaiProjectDatabase(new ASakaiProjectDatabase(settingsWindow.getDownloadPath(), null, settingsWindow.getStart(), settingsWindow.getEnd()));
            	 }

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
                database.getOrCreateProjectStepper().setAutoAutoGrade(true);
                while (true) {
                    boolean retVal = database.nonBlockingRunProjectsInteractively();
               	   if (retVal) break;
               	   Tracer.error("Did not find any matching entries. Try again.");
               		 if (settingsModel != null)
               			 settingsModel.awaitBegin();
               	  
               	   
                }
                if (settingsFrame != null)
                	settingsFrame.dispose();
            }


        } catch (ConfigurationException e) {
            System.err.println("Error loading config file.");
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Could not find project requirements.");
            System.err.println(e.getMessage());
        } catch (InstantiationException e) {
            System.err.println("Could not create project requirements.");
            System.err.println(e.getMessage());
        } catch (IllegalAccessException e) {
            System.err.println("Could not create project requirements.");
            System.err.println(e.getMessage());
        }
    }
}
