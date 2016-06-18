package gradingTools;

import static framework.grading.GradingMangerType.A_HEADLESS_GRADING_MANAGER;

import java.io.File;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import util.trace.TraceableBus;
import util.trace.Tracer;
import wrappers.grader.sakai.project.ProjectDatabaseWrapper;
import wrappers.grader.sakai.project.ProjectStepperDisplayerWrapper;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;
import framework.execution.ProcessRunnerFactory;
import framework.execution.RunnerSelector;
import framework.grading.GradingManager;
import framework.grading.GradingMangerType;
import framework.grading.ProjectRequirements;
import framework.gui.SettingsWindow;
import framework.logging.loggers.CsvLogger;
import framework.logging.loggers.FeedbackJsonLogger;
import framework.logging.loggers.FeedbackTextSummaryLogger;
import framework.logging.loggers.LocalJsonLogger;
import framework.logging.loggers.LocalTextSummaryLogger;
import framework.logging.loggers.SpreadsheetLogger;
import framework.logging.recorder.ConglomerateRecorder;
import framework.logging.recorder.ConglomerateRecorderFactory;
import framework.utils.GraderSettings;
import framework.utils.BasicGradingEnvironment;
import framework.utils.GradingEnvironment;
import grader.config.ConfigurationManagerSelector;
import grader.config.StaticConfigurationUtils;
import grader.file.zipfile.AZippedRootFolderProxy;
import grader.interaction_logger.InteractionLogWriter;
import grader.interaction_logger.InteractionLogWriterSelector;
import grader.language.LanguageDependencyManager;
import grader.modules.ModuleProblemManager;
import grader.modules.ModuleProblemManagerSelector;
import grader.navigation.NavigationKind;
import grader.project.AProject;
import grader.sakai.project.ProjectStepper;
import grader.settings.GraderSettingsManager;
import grader.settings.GraderSettingsManagerSelector;
import grader.settings.GraderSettingsModel;
import grader.settings.GraderSettingsModelSelector;
import grader.spreadsheet.BasicFeatureGradeRecorderSelector;
import grader.spreadsheet.FeatureGradeRecorderSelector;
import grader.spreadsheet.csv.AFeatureGradeRecorderFactory;
import grader.trace.settings.GraderSettingsDisplayed;
import grader.util.ExecutionUtil;

/**
 * This is the entry class for the grading tools that Maven will reference. Use
 * config.properties to configure what gets run.
 */
public class Driver {

    static PropertiesConfiguration configuration; // = ConfigurationManagerSelector.getConfigurationManager().getStaticConfiguration();

    static GradingMangerType controller;
    static ProjectDatabaseWrapper database = null;

    static GraderSettingsManager graderSettingsManager; //  = GraderSettingsManagerSelector.getGraderSettingsManager();
    static InteractionLogWriter interactionLogWriter;
    static ModuleProblemManager moduleProgramManager; // = ModuleProblemManagerSelector.getModuleProblemManager();
    static OEFrame settingsFrame = null;
    static GraderSettingsModel settingsModel;

    static File userPropsFile;
    static boolean headlessExitOnComplete = true;

    

	public static ProjectRequirements getProjectRequirements() {
        return StaticConfigurationUtils.getProjectRequirements(configuration, graderSettingsManager);

    }

    public static void drive(String[] args, int settingsFrameX, int settingsFrameY) {
//	  ObjectEditor.setDefaultAttribute(AttributeNames.SHOW_SYSTEM_MENUS, false);
//        BasicGradingEnvironment.set(new GradingEnvironment());

        setTracing();

//	  ObjectEditor.setDefaultAttribute(AttributeNames.SHOW_DEBUG_INFO_WITH_TOOL_TIP, false);

        ConfigurationManagerSelector.getConfigurationManager().init(args); // need to do this

        configuration = ConfigurationManagerSelector.getConfigurationManager().getStaticConfiguration();
        // moved
        // , in progress
        // this does nothing but slow things down, we should use some other mechanism to find requirements
//        (new ARequirementsToCourseInfoTranslator()).findAssignmentsDirectory(configuration);

        controller = GradingMangerType.getFromConfigName(configuration.getString("grader.controller", "GradingManager"));
//        if (!controller.equals("AHeadlessGradingManager")) {
        // want static confoguration utils to be set by this time, so this should not happen prematurely
        BasicGradingEnvironment.set(new GradingEnvironment()); 
        RunnerSelector.setFactory(new ProcessRunnerFactory());
        if (isHeadless()) {
            ObjectEditor.setShowStartView(false);
        } else {
            ObjectEditor.setDefaultAttribute(AttributeNames.SHOW_SYSTEM_MENUS, false);
            ObjectEditor.setDefaultAttribute(AttributeNames.SHOW_DEBUG_INFO_WITH_TOOL_TIP, false);
        }

        interactionLogWriter = InteractionLogWriterSelector.getInteractionLogWriter();
        TraceableBus.addTraceableListener(interactionLogWriter);

        moduleProgramManager = ModuleProblemManagerSelector.getModuleProblemManager();
        moduleProgramManager.init();
        graderSettingsManager = GraderSettingsManagerSelector.getGraderSettingsManager();
        graderSettingsManager.init();
        if (isHeadless()) {
            setupHeadlessGrader(args);
        }
        BasicGradingEnvironment.get().setPrecompileMissingObjectCode(StaticConfigurationUtils.getPrecompileClasses(configuration, graderSettingsManager));
        BasicGradingEnvironment.get().setForceCompile(StaticConfigurationUtils.getForceCompileClasses(configuration, graderSettingsManager));
        // Get the project name
        String projectName = configuration.getString("project.name");
      

        BasicGradingEnvironment.get().setAssignmentName(projectName);

        ProjectRequirements requirements = null;

        // Logging
        ConglomerateRecorder recorder = ConglomerateRecorder.getInstance();

        // Run the grading process
//        String controller = configuration.getString("grader.controller", "GradingManager");
        settingsModel = null;

        String goToOnyen = "";

//        requirements = StaticConfigurationUtils.getProjectRequirements(configuration, graderSettingsManager);
//        requirements = getProjectRequirements();
        GradingManager manager;
        /*switch (controller) {
         case A_GUI_GRADING_MANAGER: {
         // Logging
         //                ConglomerateRecorder recorder = ConglomerateRecorder.getInstance();
         recorder.setProjectRequirements(requirements);
         initLoggers(requirements, configuration);

         // Run the GraderManager
         manager = new AGUIGradingManager(projectName, requirements);
         manager.run();
         break;
         }
         case A_HEADLESS_GRADING_MANAGER: {
         // Run the GraderManager
         manager = new AHeadlessGradingManager(projectName, requirements, configuration, graderSettingsManager);
         manager.run();
         break;
         }
         case SAKAI_PROJECT_DATABASE: {*/
        //Logging/results saving
//                FeatureGradeRecorderSelector.setFactory(new ConglomerateRecorderFactory());
//                BasicFeatureGradeRecorderSelector.setFactory(new AFeatureGradeRecorderFactory());
//            	  ProjectDatabaseWrapper database = new ProjectDatabaseWrapper();
        String settings = configuration.getString("grader.settings", "oe");
//            	  String settingsTry = configuration.getString("Grader.Settings");
        if (settings.equalsIgnoreCase("oe")) {
//                    NavigationFilter gradingBasedFilterer = new AGradingStatusFilter();
//                 	
//                    NavigationFilterRepository.register(gradingBasedFilterer);

//                    settingsModel = new AGraderSettingsModel(null);
            synchronized (Driver.class) {
                settingsModel = GraderSettingsModelSelector.getGraderSettingsModel();
//                settingsModel.setPrivacyMode(StaticConfigurationUtils.getPrivacy(configuration, graderSettingsManager));

                settingsModel.init();
            }

            // handle the clean slate argss
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("--clean-slate")) {
                    i++;
                    if (i == args.length || args[i].startsWith("-")) {
                        settingsModel.cleanSlate();
                        break;
                    } else {
                        String onyenArg = args[i];
                        if (onyenArg.startsWith("{")) {
                            String[] onyenList = onyenArg.substring(1, onyenArg.length() - 1).split(",");
                            for (String onyen : onyenList) {
                                if (onyen.contains("-")) {
                                    String[] onyenLimits = onyen.split("-");
                                    //                            settingsModel.cleanSlate(onyenLimits[0], onyenLimits[1]);
                                } else {
                                    settingsModel.cleanSlate(onyen);
                                }
                            }
                        } else if (onyenArg.contains("-")) {
                            String[] onyenLimits = onyenArg.split("-");
//                            settingsModel.cleanSlate(onyenLimits[0], onyenLimits[1]);
                        } else {
                            settingsModel.cleanSlate(onyenArg);
                        }
                        break;
                    }
                } else if (args[i].equals("--compile-executor")) {
                    settingsModel.compileExecutor();
                }
            }
//            for(String arg : args) {
//                if (arg.equals("--clean-slate")) {
//                    settingsModel.cleanSlate();
//                }
//            }
            if (isNotHeadless()) {
                settingsFrame = ObjectEditor.edit(settingsModel);
                settingsFrame.setLocation(settingsFrameX, settingsFrameY);
                settingsFrame.setTitle("Grader Assistant Starter");

//                settingsFrame.setSize(600, 550);
                settingsFrame.setSize(600, 570);

                GraderSettingsDisplayed.newCase(settingsModel, Driver.class);
                settingsModel.awaitBegin();
            } else {
                settingsModel.getNavigationSetter().setNavigationKind(NavigationKind.AUTOMATIC);
                settingsModel.preSettings();
                settingsModel.postSettings();
                settingsModel.begin();
                settingsModel.cleanSlate();
            }
//            settingsModel.maybePreCompile();
            initAssignmentDataFolder();

            projectName = settingsModel.getCurrentProblem(); // get the current one
            BasicGradingEnvironment.get().setAssignmentName(projectName);

            // moving code below
//            requirements = getProjectRequirements();
//            recorder.setProjectRequirements(requirements);
//            if (requirements == null) {
//            	System.err.println("Exiting because selected assignment does not have any associated requirements. Please add requirements or select correct assignment after restarting.");
//            	System.exit(-1);
//            }
//            	
//            initLoggers(requirements, configuration);
//            initAssignmentDataFolder();
        } else if (isNotHeadless()) {

            // Start the grading process by, first, getting the settings the running the project database
            SettingsWindow settingsWindow = SettingsWindow.create();
            settingsWindow.awaitBegin();

//            ASakaiProjectDatabase.setCurrentSakaiProjectDatabase(new ASakaiProjectDatabase(settingsWindow.getDownloadPath(), null, settingsWindow.getStart(), settingsWindow.getEnd()));
        }
//        String projectName = configuration.getString("project.name");
//                Object projectProperty = configuration.getProperty("project.name");
//                GradingEnvironment.get().setAssignmentName(projectName);

        // Logging/results saving
        FeatureGradeRecorderSelector.setFactory(new ConglomerateRecorderFactory());
        BasicFeatureGradeRecorderSelector.setFactory(new AFeatureGradeRecorderFactory());

        // Create the database
//        ProjectDatabaseWrapper database = new ProjectDatabaseWrapper();
        boolean loadClasses = StaticConfigurationUtils.getLoadClasses(configuration, graderSettingsManager);
        BasicGradingEnvironment.get().setLoadClasses(loadClasses);
        String language = StaticConfigurationUtils.getLanguage();
        LanguageDependencyManager.setLanguage(language);
        BasicGradingEnvironment.get().setCompileMissingObjectCode(StaticConfigurationUtils.getAllowCompileClasses(configuration, graderSettingsManager));
        BasicGradingEnvironment.get().setUnzipFiles(StaticConfigurationUtils.getUnzipFiles(configuration, graderSettingsManager));
//        AProject.setCheckStyle(StaticConfigurationUtils.getCheckStyle(configuration, graderSettingsManager));
        BasicGradingEnvironment.get().setCheckStyle(StaticConfigurationUtils.getCheckStyle());
        ExecutionUtil.setUseMethodAndConstructorTimeOut(true);

        // before we load the database, see if we need to precompile
        settingsModel.maybePreCompile();
        settingsModel.maybePreUnzip();

        database = new ProjectDatabaseWrapper();
        database.setGraderSettings(settingsModel);
        database.setScoreFeedback(null); // we will be writing to feedback file which is more complete
//        ASakaiProjectDatabase.setCurrentSakaiProjectDatabase(database);
        // moved code from above
        requirements = getProjectRequirements();
        System.out.println ("got requirements:" + requirements);
//        System.out.println ("SLEEPING");
//        try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        recorder.setProjectRequirements(requirements);
        if (requirements == null) {
            System.err.println("Returning because selected assignment does not have any associated requirements. Please add requirements or select correct assignment after restarting.");
            return;
//            System.exit(-1);
        }

        initLoggers(requirements, configuration);

        database.setProjectRequirements(requirements);

            ConglomerateRecorder.getInstance().setBasicFeatureGradeRecorder(BasicFeatureGradeRecorderSelector.createFeatureGradeRecorder(database));

        // Possibly set the stepper displayer
        boolean useFrameworkGUI = configuration.getBoolean("grader.controller.useFrameworkGUI", false) && isNotHeadless();
        if (useFrameworkGUI) {
            database.setProjectStepperDisplayer(new ProjectStepperDisplayerWrapper());
        }

        // Feedback
//        database.setAutoFeedback(ConglomerateRecorder.getInstance());
        database.setManualFeedback(ConglomerateRecorder.getInstance());

//        List<String> visitActions = StaticConfigurationUtils.autoVisitActions(configuration, graderSettingsManager);
        List<String> visitActions = StaticConfigurationUtils.autoVisitActions(graderSettingsManager);

        ProjectStepper projectStepper = database.getOrCreateProjectStepper();
//                    OEFrame settingsFrame = (OEFrame) projectStepper.getFrame();
        if (visitActions.contains(StaticConfigurationUtils.AUTO_GRADE)) {
            projectStepper.setAutoAutoGrade(true);
        }
        if (visitActions.contains(StaticConfigurationUtils.AUTO_RUN)) {
            projectStepper.setAutoRun(true);
        }
//            if (visitActions.contains(MAKE_CLASS_DESCRIPTION)) {
//                AProject.setMakeClassDescriptions(true);      
        if (isNotHeadless()) {
            database.getProjectNavigator().navigate(settingsModel, settingsFrame, true);
        } else {
            database.getAutomaticProjectNavigator().navigate(settingsModel, null, isHeadlessExitOnComplete());
        }
    }

    public static ProjectDatabaseWrapper getDatabase() {
        return database;
    }

    public static OEFrame getSettingsFrame() {
        return settingsFrame;
    }

    public static GraderSettingsModel getSettingsModel() {
        synchronized (Driver.class) {
            return settingsModel;
        }
    }

    public static void initAssignmentDataFolder() {
        String defaultAssignmentsDataFolderName = configuration.getString("grader.defaultAssignmentsDataFolderName");
        defaultAssignmentsDataFolderName = graderSettingsManager.replaceModuleProblemVars(defaultAssignmentsDataFolderName);
        BasicGradingEnvironment.get().setDefaultAssignmentsDataFolderName(defaultAssignmentsDataFolderName);
    }

    public static void initLoggers(ProjectRequirements requirements, PropertiesConfiguration configuration) {
        try {
            // Logging
            ConglomerateRecorder recorder = ConglomerateRecorder.getInstance();
            recorder.setProjectRequirements(requirements);

            String[] loggingMethods = configuration.getString("grader.logger", "csv").split("\\s*\\+\\s*");
            //lazy coding means feedback should be the last step so that isSaved works correctly

            for (String method : loggingMethods) {

                // Add loggers
                if (method.equals("local") || method.equals("local-txt")) {
                    recorder.addLogger(new LocalTextSummaryLogger());
                }
                if (method.equals("local") || method.equals("local-json")) {
                    recorder.addLogger(new LocalJsonLogger());
                }
                if (method.equals("feedback") || method.equals("feedback-txt")) {
                    recorder.addLogger(new FeedbackTextSummaryLogger());
                }
                if (method.equals("feedback") || method.equals("feedback-json")) {
                    recorder.addLogger(new FeedbackJsonLogger());
                }
                if (method.equals("spreadsheet")) {
                    recorder.addLogger(new SpreadsheetLogger(requirements));
                }
                if (method.equals("csv")) {
                    recorder.addLogger(new CsvLogger());
                }
            }
        } catch (ConfigurationException e) {
            System.err.println("Error loading config file.");
            System.err.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        drive(args, 0, 0);
    }
   
    public static void setTracing() {
//		Tracer.showInfo(true);
//		Tracer.setKeywordPrintStatus(OverallNotesChanged.class, true);
//		Tracer.setKeywordPrintStatus(Tracer.ALL_KEYWORDS, true);
    }

    private static boolean isHeadless() {
        return controller.equals(A_HEADLESS_GRADING_MANAGER);
    }

    private static boolean isNotHeadless() {
        return !controller.equals(A_HEADLESS_GRADING_MANAGER);
    }

    private static void setupHeadlessGrader(String[] userProperties) {
        String course = "";
        String problem = "";
        String start = "";
        String end = "";
        String path = "";
        for (int i = 0; i < userProperties.length; i++) {

            userProperties[i] = userProperties[i].trim();
            switch (userProperties[i]) {
                case "--course-name":
                    course = userProperties[++i].trim();
                    break;
                case "--project-name":
                    problem = userProperties[++i].trim();
                    break;
                case "--headless-path":
                    path = userProperties[++i].trim();
                    break;
                case "--headless-start":
                    start = userProperties[++i].trim();
                    break;
                case "--headless-end":
                    end = userProperties[++i].trim();
                    break;
            }
        }
        course = Character.toUpperCase(course.charAt(0)) + course.substring(1).toLowerCase();
        graderSettingsManager.setModule(course);
        graderSettingsManager.setStartingOnyen(course, start);
        GraderSettings.get().set("start", start);
        graderSettingsManager.setEndingOnyen(course, end);
        GraderSettings.get().set("end", end);
        graderSettingsManager.setProblem(course, problem);
        graderSettingsManager.setDownloadPath(course, path);
        GraderSettings.get().set("path", path);
    }

    public static PropertiesConfiguration getConfiguration() {
        return configuration;
    }

    public static void setConfiguration(PropertiesConfiguration configuration) {
        Driver.configuration = configuration;
    }
    public static boolean isHeadlessExitOnComplete() {
		return headlessExitOnComplete;
	}

	public static void setHeadlessExitOnComplete(boolean headlessExitOnComplete) {
		Tracer.showInfo(true);
		Tracer.setKeywordPrintStatus(AZippedRootFolderProxy.class, true);
		Driver.headlessExitOnComplete = headlessExitOnComplete;
	}
}
