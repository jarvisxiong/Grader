package grader.settings;

import framework.utils.GraderSettings;
import grader.basics.settings.BasicGradingEnvironment;
import grader.config.ConfigurationManagerSelector;
import grader.config.StaticConfigurationUtils;
import grader.executor.ExecutorSelector;
import grader.modules.AModuleProblemSelector;
import grader.modules.ModuleProblemManager;
import grader.modules.ModuleProblemManagerSelector;
import grader.modules.ModuleProblemSelector;
import grader.navigation.NavigationKind;
import grader.navigation.NavigationListManagerFactory;
import grader.sakai.StudentAssignment;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.folders.AGraderFilesSetterModel;
import grader.settings.folders.AnOnyenRangeModel;
import grader.settings.folders.GraderFilesSetterModel;
import grader.settings.folders.OnyenRangeModel;
import grader.settings.navigation.ANavigationSetter;
import grader.settings.navigation.NavigationSetter;
import grader.spreadsheet.FeatureGradeRecorder;
import grader.trace.settings.DownloadPathUserChange;
import grader.trace.settings.GraderSettingsEnded;
import grader.trace.settings.GraderSettingsStarted;
import grader.trace.settings.ModuleUserChange;
import grader.trace.settings.NavigationInitiated;
import grader.trace.settings.ProblemUserChange;
import gradingTools.Driver;

import java.awt.GraphicsEnvironment;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import util.annotations.ComponentHeight;
import util.annotations.Explanation;
import util.annotations.Label;
import util.annotations.Position;
import util.annotations.Row;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Visible;
import util.misc.Common;
import util.trace.Tracer;
import wrappers.framework.project.ProjectWrapper;
import wrappers.grader.sakai.project.ProjectDatabaseWrapper;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@Label("Starter")
public class AGraderSettingsModel implements GraderSettingsModel {
	
    GraderFilesSetterModel fileBrowsing = new AGraderFilesSetterModel();
    NavigationSetter navigationSetter = new ANavigationSetter(this);
    OnyenRangeModel onyens = new AnOnyenRangeModel(this);
    ModuleProblemSelector moduleProblemSelector;
    List<String> modules = new ArrayList();
    List<String> problems = new ArrayList();
    String editor;
    String diff;
    String currentModule;
    List<String> currentProblems;
    String currentProblem;
    String currentModulePrefix;
//	PropertiesConfiguration configuration, dynamicConfiguration;
//	PropertiesConfiguration dynamicConfiguration;
    GraderSettingsManager graderSettingsManager = GraderSettingsManagerSelector.getGraderSettingsManager();

    ModuleProblemManager moduleProblemManager;
    String problemDownloadPath;
    String moduleDownloadPath;
    boolean settingsLoaded;
    boolean privacyMode;
    boolean compileMode;
//	DynamicEnum moduleEnum, problemEnum;

    //	BeginActionModel beginActionModel = new ABeginActionModel();
    boolean graderStarted;
    PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    SakaiProjectDatabase database;

    public AGraderSettingsModel(SakaiProjectDatabase aDatabase) {
        database = aDatabase;
//		configuration = GradingEnvironment.get().getConfigurationManager().getStaticConfiguration();
//		dynamicConfiguration = GradingEnvironment.get().getConfigurationManager().getDynamicConfiguration();
        moduleProblemManager = ModuleProblemManagerSelector.getModuleProblemManager();

//		configuration = ConfigurationManagerSelector.getConfigurationManager().getStaticConfiguration();
//		dynamicConfiguration = ConfigurationManagerSelector.getConfigurationManager().getDynamicConfiguration();
//		loadSettings();
        String aModule = graderSettingsManager.getModule();
        modules = moduleProblemManager.getModules();
//	        problemDownloadPath = graderSettingsManager.getDownloadPath(aModule);
////			if (problemDownloadPath == null)
////	        
//////	        String downloadPath;
////	        problemDownloadPath =  ;
//	        if (problemDownloadPath != null) {
////	            String downloadPath = GraderSettings.get().get("path");
//	            fileBrowsing.getDownloadFolder().getLabel().setText(problemDownloadPath);
//	        } else {
//	        	noDownloadPath();
//	        }
        setCurrentModule(aModule);
//	        GradingSettingsStarted.newCase(this, this);
//		loadDynamicConfigurationSettings();
    }

    public AGraderSettingsModel() {
//		database = aDatabase;

//		AModuleProblemSelector moduleProblem = new AModuleProblemSelector(modules, problems);
//		loadSettings();
//		settingsLoaded = true;
    }

    @Override
    @Visible(false)
    public void init() {
//    	graderSettingsManager.init();
    	projectDatabase = null; // added this for reusing this for multiple drive invocations
        currentProblem = null; // so that it is refreshed in refreshAll
    	setGraderStarted(false);
        //graderStarted = false;
//		configuration = GradingEnvironment.get().getConfigurationManager().getStaticConfiguration();
//		dynamicConfiguration = GradingEnvironment.get().getConfigurationManager().getDynamicConfiguration();
        moduleProblemManager = ModuleProblemManagerSelector.getModuleProblemManager();

//		configuration = ConfigurationManagerSelector.getConfigurationManager().getStaticConfiguration();
//		dynamicConfiguration = ConfigurationManagerSelector.getConfigurationManager().getDynamicConfiguration();
//		loadSettings();
        String aModule = graderSettingsManager.getModule();
        modules = moduleProblemManager.getModules();
//	        problemDownloadPath = graderSettingsManager.getDownloadPath(aModule);
////			if (problemDownloadPath == null)
////	        
//////	        String downloadPath;
////	        problemDownloadPath =  ;
//	        if (problemDownloadPath != null) {
////	            String downloadPath = GraderSettings.get().get("path");
//	            fileBrowsing.getDownloadFolder().getLabel().setText(problemDownloadPath);
//	        } else {
//	        	noDownloadPath();
//	        }
        setCurrentModule(aModule);
//		loadSettings();
//		settingsLoaded = true;
    }

    @Override
    @Visible(false)
    public boolean isSettingsLoaded() {
        return settingsLoaded;
    }

    @Override
    @Visible(false)
    public String getCurrentModule() {
        return currentModule;
    }
    // never claled other than from setCurrentModule
    void basicSetCurrentModule(String newValue) {
    	Tracer.info(this, "Setting Current module to:" + newValue);
        ModuleUserChange.newCase(currentModule, this, this);
        currentModule = newValue;
//        refreshAll();
//		 ModuleUserChange.newCase(currentModule, this, this);

    }

    void setCurrentModule(String newValue) {
//    	System.out.println ("Current module to:" + newValue);
//        ModuleUserChange.newCase(currentModule, this, this);
//        currentModule = newValue;
    	basicSetCurrentModule(newValue);
        refreshAll();
//		 ModuleUserChange.newCase(currentModule, this, this);

    }

    void noDownloadPath() {
        if (!GraphicsEnvironment.isHeadless()) {
            JOptionPane.showMessageDialog(null, "No stored download path. When the settings window comes up, please enter correct download path for a problem in module:" + currentModule + " or change the module.");
        } else {
            Tracer.error("No stored download path.");
        }

    }

    void noValidDownloadPath(String aPath) {
        if (!GraphicsEnvironment.isHeadless()) {
            JOptionPane.showMessageDialog(null, "No folder found for download path:" + problemDownloadPath + " . When the settings window comes up, please enter correct download path for a problem in module:" + currentModule + " or change the module.");
        } else {
            Tracer.error("No valid download path.");
        }
    }

    @Visible(false)
    public void refreshAll() {
        loadDynamicConfigurationSettings();
        refreshOnyens(currentModule);
//		problems.clear();
//		List<String> problems = new ArrayList();
        String currentModulePrefix = moduleProblemManager.getModulePrefix(currentModule);
        problems.clear();
        problemDownloadPath = graderSettingsManager.getDownloadPath(currentModule);
//		problemDownloadPath = moduleDownloadPath + "\\" +  currentProblem;
        if (problemDownloadPath != null) {
            File folder = new File(problemDownloadPath);
//                        System.out.println("~~~ " + folder.getAbsolutePath() + " ~~~");
            if (!folder.exists()) {
//				JOptionPane.showMessageDialog(null, "Please enter download path for current problem in module:" + currentModule);
                noValidDownloadPath(problemDownloadPath);
                return;
//				Tracer.error("No folder found for:" + downloadPath);				
            } else {
                File gradesFile = new File(problemDownloadPath + "/grades.csv"); // is this a sakai assignment folder
//                String aFolderName = null;
                if (gradesFile.exists()) {
                    if (currentProblem == null) {
//                    	aFolderName = folder.getName();
                    	basicSetCurrentProblem(folder.getName());
//                        currentProblem = folder.getName();
                    }
                    folder = folder.getParentFile();
                }
//				try {
                moduleDownloadPath = folder.getAbsolutePath();
//                if (aFolderName != null && currentProblem == null) {
//                	setCurrentProblem(aFolderName);
//                }
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
                File[] children = folder.listFiles();
                long latestTime = 0;
                boolean noSetProblem = currentProblem == null;

//				currentProblem;
                for (File child : children) {
                    if (child.getName().startsWith(currentModulePrefix) && !child.getName().equals("AssignmentsData")) {
//						String normalizedName = child.getName().replaceAll("\\s+", "");
                        String normalizedName = child.getName();

                        problems.add(normalizedName);
                        if (child.getAbsolutePath().equals(problemDownloadPath)) {
                            currentProblem = normalizedName;
                        }
                        if (noSetProblem && child.lastModified() > latestTime) {
                            currentProblem = normalizedName;
//							try {
                            problemDownloadPath = child.getAbsolutePath();
//							} catch (IOException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}

                        }
                    }
                }
            }
        }
//		fileBrowsing.getDownloadFolder().setText(problemDownloadPath);
        refreshProblemDownloadPath();
        if (moduleProblemSelector != null) {
            moduleProblemSelector.getModule().setValue(currentModule);
//				String savedProblem = currentProblem;
            moduleProblemSelector.getProblem().setChoices(problems, currentProblem); // it is the same object but we need to fire property change
//				moduleProblemSelector.getProblem().setValue(savedProblem); // current problem mught be resets

        }
        graderSettingsManager.setModule(currentModule);
        graderSettingsManager.setProblem(currentModule, currentProblem);

        boolean isPrivacy = StaticConfigurationUtils.getPrivacy(
                ConfigurationManagerSelector.getConfigurationManager().getStaticConfiguration(),
                graderSettingsManager);
        setPrivacyMode(isPrivacy);
//		if (problems.size() > 0) {
//			currentProblem = problems.get(problems.size() - 1);
//		else
//			currentProblem = null;
//		return problems;
    }

    void refreshProblemDownloadPath() {
        if (problemDownloadPath != null) {
        	Tracer.info (this, "Refreshing problem download path:" + problemDownloadPath);
            fileBrowsing.getDownloadFolder().setText(problemDownloadPath);
        } else {
        	Tracer.error("Null problem download path");
        }
        GraderSettings.get().set("path", problemDownloadPath);

    }

    void loadSettings() {

        String editor;
        if (GraderSettings.get().has("editor")) {
            editor = GraderSettings.get().get("editor");
            BasicGradingEnvironment.get().setEditor(editor); // why not for path also, perhaps its not used later?
        } else {
            editor = BasicGradingEnvironment.get().getEditor();
        }
        fileBrowsing.getTextEditor().getLabel().setText(editor);
//        String downloadPath;
        if (GraderSettings.get().has("path")) {
            String downloadPath = GraderSettings.get().get("path");
            fileBrowsing.getDownloadFolder().getLabel().setText(downloadPath);
        }
        if (GraderSettings.get().has(START_ONYEN)) {
            String startingOnyen = GraderSettings.get().get(START_ONYEN);
            onyens.setDisplayedStartingOnyen(startingOnyen);
        }
        if (GraderSettings.get().has(END_ONYEN)) {
            String endingOnyen = GraderSettings.get().get(END_ONYEN);
            onyens.setDisplayedEndingOnyen(endingOnyen);
        }
//        List objectModules = GradingEnvironment.get().getConfigurationManager().getStaticConfiguration().getList("modules");
//		modules = objectModules;
//		if (objectModules.size() == 0) {
//			Tracer.error("No modules specified in configuration file!");
//			System.exit(-1);
//		}
//		setCurrentModule(GradingEnvironment.get().getConfigurationManager().getDynamicConfiguration().getString("currentModule", modules.get(0)));
//		String currentModulePrefix =  GradingEnvironment.get().getConfigurationManager().getStaticConfiguration().getString(currentModule + ".problems.prefix")	;
//		if (currentModulePrefix == null)
//			currentModulePrefix = GradingEnvironment.get().getConfigurationManager().getStaticConfiguration().getString("default.problems.prefix", "Assignment");
//				Common.arrayToArrayList(new String[] {"Comp110", "Comp401"});
//		List<String> problems = Common.arrayToArrayList(new String[] {"A1", "A2"});
//		moduleProblemSelector = new AModuleProblemSelector(objectModules, problems);
    }
//	   void maybeConvertToDynamicConfiguration() {
//		 	Map<String, String> settings = GraderSettings.get().getSettings();
////	    	PropertiesConfiguration dynamicConfiguration = GradingEnvironment.get().getConfigurationManager().getDynamicConfiguration();
//	    	if (!dynamicConfiguration.isEmpty()) return;
//	    	for (String key : settings.keySet())
//	            dynamicConfiguration.setProperty(key, settings.get(key));
//	    	try {
//				dynamicConfiguration.save();
//			} catch (ConfigurationException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	    	
//	    }

    public static final String EDITOR = "editor";
    public static final String MODULE = "currentModule";
    public static final String PROBLEM_PATH = "path";
    public static final String START_ONYEN = "start";
    public static final String END_ONYEN = "end";

    void refreshOnyens(String aModule) {
        String startingOnyen = graderSettingsManager.getStartingOnyen(aModule);

        if (startingOnyen != null) {
//        	String startingOnyen = GraderSettings.get().get("start");
            onyens.setDisplayedStartingOnyen(startingOnyen);
        }
//        String endingOnyen = dynamicConfiguration.getString(aModule + "." + END_ONYEN,
//        		dynamicConfiguration.getString(END_ONYEN));
        String endingOnyen = graderSettingsManager.getEndingOnyen(aModule);
        if (endingOnyen != null) {
            onyens.setDisplayedEndingOnyen(endingOnyen);
        }
    }

    void loadDynamicConfigurationSettings() {

        problemDownloadPath = graderSettingsManager.getDownloadPath(currentModule);
//		if (problemDownloadPath == null)
//        
////        String downloadPath;
//        problemDownloadPath =  ;
        if (problemDownloadPath != null) {
//            String downloadPath = GraderSettings.get().get("path");
            fileBrowsing.getDownloadFolder().getLabel().setText(problemDownloadPath);
        } else {
            noDownloadPath();
        }
//		maybeConvertToDynamicConfiguration();
        editor = graderSettingsManager.getEditor();
        diff = graderSettingsManager.getDiff();

//		 editor = dynamicConfiguration.getString(EDITOR);
//		if (editor != null) {
////			editor = GraderSettings.get().get("editor");
//            GradingEnvironment.get().setEditor(editor); // why not for path also, perhaps its not used later?
//        } else
//            editor = GradingEnvironment.get().getEditor();
        fileBrowsing.getTextEditor().getLabel().setText(editor);
        fileBrowsing.getDiff().getLabel().setText(diff);
        String aModule = currentModule;
//        String aModule = graderSettingsManager.getModule();
//        modules = moduleProblemManager.getModules();
//
//		String aModule = dynamicConfiguration.getString(MODULE, modules.get(0));
//		setCurrentModule(dynamicConfiguration.getString("currentModule", modules.get(0)));

//		if (aModule == null) {
//			if (modules != null && modules.size() > 0)
//				aModule = modules.get(0);
//		} else {
//			aModule = "YourCourse";
//			if (modules == null) modules = new ArrayList();
//			modules.add(aModule);
//		}
//
//		problemDownloadPath =  dynamicConfiguration.getString(aModule + "." + PROBLEM_PATH,
//				dynamicConfiguration.getString(PROBLEM_PATH));
//		problemDownloadPath = graderSettingsManager.getDownloadPath(aModule);
//		if (problemDownloadPath == null)
//        
////        String downloadPath;
//        problemDownloadPath =  ;
//        if (problemDownloadPath != null) {
////            String downloadPath = GraderSettings.get().get("path");
//            fileBrowsing.getDownloadFolder().getLabel().setText(problemDownloadPath);
//        } else {
//        	noDownloadPath();
//        }
        NavigationKind aNavigationKind = graderSettingsManager.getNavigationKind(aModule);
        if (aNavigationKind != null) {
            navigationSetter.setNavigationKind(aNavigationKind);
        }
        navigationSetter.getAutomaticNavigationSetter().setAnimateGrades(
                graderSettingsManager.getAnimateGrades(aModule));

        navigationSetter.getAutomaticNavigationSetter().setAnimationPauseTime(
                graderSettingsManager.getAnimationPauseTime(aModule));
        String aNavigationFilter = graderSettingsManager.getNavigationFilter(aModule);
        if (aNavigationFilter != null) {
            navigationSetter.getNavigationFilterSetter().getNavigationFilterType().setValue(aNavigationFilter);
            String optionString = graderSettingsManager.getNavigationFilterOption(aModule, aNavigationFilter);
            Object currentOption = navigationSetter.getNavigationFilterSetter().getParameter();
            Object newOption = Common.fromString(currentOption.getClass(), optionString);
            navigationSetter.getNavigationFilterSetter().setParameter(newOption);
        }

//        else {
//        	 fileBrowsing.getDownloadFolder().getLabel().setText("Please enter  folder");
//        }
//        String startingOnyen =  dynamicConfiguration.getString(aModule + "." + START_ONYEN,     	
//        		
//        		dynamicConfiguration.getString(START_ONYEN));
//        refreshOnyens(aModule);
//        String startingOnyen =  graderSettingsManager.getStartingOnyen(aModule);
//        
//        if (startingOnyen != null) {
////        	String startingOnyen = GraderSettings.get().get("start");
//        	onyens.setStartingOnyen(startingOnyen);
//        }
////        String endingOnyen = dynamicConfiguration.getString(aModule + "." + END_ONYEN,
////        		dynamicConfiguration.getString(END_ONYEN));
//        String endingOnyen = graderSettingsManager.getEndingOnyen(aModule);
//        if (endingOnyen != null) {
//        	onyens.setEndingOnyen(endingOnyen);
//        }
//        List objectModules = configuration.getList("modules");
//        
//		modules = objectModules;
//		if (objectModules.size() == 0) {
//			Tracer.error("No modules specified in configuration file!");
//			System.exit(-1);
//		}
//		 currentModulePrefix =  configuration.getString(currentModule + ".problems.prefix")	;
//
//		if (currentModulePrefix == null)
//			currentModulePrefix = configuration.getString("default.problems.prefix", "Assignment");
//				Common.arrayToArrayList(new String[] {"Comp110", "Comp401"});
//		List<String> problems = Common.arrayToArrayList(new String[] {"A1", "A2"});
//		setCurrentModule(dynamicConfiguration.getString("currentModule", modules.get(0)));
//		setCurrentModule(aModule);
        if (moduleProblemSelector == null) {

            moduleProblemSelector = new AModuleProblemSelector(modules, problems);
            moduleProblemSelector.getProblem().setValue(currentProblem);
            moduleProblemSelector.getModule().setValue(currentModule);
            moduleProblemSelector.getModule().addPropertyChangeListener(this);
            moduleProblemSelector.getProblem().addPropertyChangeListener(this);
            fileBrowsing.getDownloadFolder().getLabel().addPropertyChangeListener(this);
            onyens.addPropertyChangeListener(this);
        }

    }

    void saveSettings() {
        // Update the settings
        String editor = fileBrowsing.getTextEditor().getLabel().getText();
        String downloadPath = fileBrowsing.getDownloadFolder().getLabel().getText();
        String diff = fileBrowsing.getDiff().getLabel().getText();
        String startingOnyen = onyens.getStartingOnyen();
        String endingOnyen = onyens.getEndingOnyen();
        GraderSettings.get().set(START_ONYEN, startingOnyen);
        GraderSettings.get().set(END_ONYEN, endingOnyen);
        Tracer.info (this, "Saving PROBLEM_PATH:" + downloadPath);
        GraderSettings.get().set(PROBLEM_PATH, downloadPath);
        BasicGradingEnvironment.get().setAssignmentName(currentProblem);
//        ASakaiProjectDatabase.setCurrentSakaiProjectDatabase(new ASakaiProjectDatabase(downloadPath, null));
        GraderSettings.get().set("editor", editor);
        GraderSettings.get().set("diff", diff);

        GraderSettings.get().save();
        graderSettingsManager.setEditor(editor);
        graderSettingsManager.setModule(currentModule);
        graderSettingsManager.setDownloadPath(currentModule, downloadPath);
        graderSettingsManager.setStartingOnyen(currentModule, startingOnyen);
        graderSettingsManager.setEndingOnyen(currentModule, endingOnyen);
        graderSettingsManager.setProblem(currentModule, currentProblem);
        graderSettingsManager.setDiff(diff);

        graderSettingsManager.setNavigationKind(currentModule, navigationSetter.getNavigationKind());
        graderSettingsManager.setAnimateGrades(currentModule, navigationSetter.getAutomaticNavigationSetter().getAnimateGrades());

        graderSettingsManager.setAnimationPauseTime(currentModule, navigationSetter.getAutomaticNavigationSetter().getAnimationPauseTime());

        String navigationFilter = navigationSetter.getNavigationFilterSetter().getNavigationFilterType().getValue().toString();
        graderSettingsManager.setNavigationFilter(currentModule,
                navigationFilter);
        graderSettingsManager.setNavigationFilterOption(currentModule, navigationFilter, navigationSetter.getNavigationFilterSetter().getParameter());

        graderSettingsManager.save();

//
//        
//        
//        
//        dynamicConfiguration.setProperty(EDITOR, editor);
//        dynamicConfiguration.setProperty(MODULE, currentModule);
//        dynamicConfiguration.setProperty(currentModule + "." + PROBLEM_PATH, downloadPath);
//        dynamicConfiguration.setProperty(PROBLEM_PATH, downloadPath);
//        dynamicConfiguration.setProperty(START_ONYEN, startingOnyen);
//        dynamicConfiguration.setProperty(currentModule + "." + START_ONYEN, startingOnyen);
//        dynamicConfiguration.setProperty(END_ONYEN, endingOnyen);
//        dynamicConfiguration.setProperty(currentModule + "." + END_ONYEN, endingOnyen);
//        try {
//			dynamicConfiguration.save();
//		} catch (ConfigurationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }

    @Override
    @Row(0)
    public ModuleProblemSelector getModuleProblemSelector() {
        return moduleProblemSelector;
    }

    @Override
    public void setModuleProblemSelector(ModuleProblemSelector moduleProblemSelector) {
        this.moduleProblemSelector = moduleProblemSelector;
    }

    @Row(1)
    @Explanation("Editor and assignment folder.")
    public GraderFilesSetterModel getFileBrowsing() {
        return fileBrowsing;
    }

    public void setFileBrowsing(GraderFilesSetterModel fileBrowsing) {
        this.fileBrowsing = fileBrowsing;
    }

    @Row(2)
    @Explanation("Specification of alphabetically sorted list of student onyens.")
    public OnyenRangeModel getOnyens() {
        return onyens;
    }

    public void setOnyens(OnyenRangeModel onyens) {
        this.onyens = onyens;
    }

    @Row(3)
    @Override
//	@Visible(false)
    @Explanation("Automatic and manual navigation settings.")
    public NavigationSetter getNavigationSetter() {
        return navigationSetter;
    }

    @Override
    public void setNavigationSetter(NavigationSetter navigationSetter) {
        this.navigationSetter = navigationSetter;
    }

    public boolean preBegin() {
        return !isGraderStarted();
    }

    @Row(4)
    @ComponentHeight(25)
    @Explanation("Start navigation through the projects of the selected onyens.")
    public synchronized void begin() {

        NavigationInitiated.newCase(this, this);
        notify();

    }
    @Visible(false)
    public synchronized void preSettings() {
        GraderSettingsStarted.newCase(this, this);

        setGraderStarted(false);
        //graderStarted = false;
    }
    @Visible(false)
    public synchronized void postSettings() {
        //GraderSettingsStarted.newCase(this, this);

        //graderStarted = false;
        // see comment about race conditions
//		propertyChangeSupport.firePropertyChange("this", null, this); // evaluate pre conditions
        saveSettings();
        setGraderStarted(true);
        //graderStarted = true;
        GraderSettingsEnded.newCase(this, this);

            // this can cause concurrent changed to object editor  leading to race conditions
//			propertyChangeSupport.firePropertyChange("this", null, this); // evaluate pre conditions
    }

    @Visible(false)
    public synchronized void awaitBegin() {
        preSettings();
        //GraderSettingsStarted.newCase(this, this);

        //graderStarted = false;
        // see comment about race conditions
//		propertyChangeSupport.firePropertyChange("this", null, this); // evaluate pre conditions
        try {
            wait();
            postSettings();
            //saveSettings();
            //graderStarted = true;
            //GraderSettingsEnded.newCase(this, this);

            // this can cause concurrent changed to object editor  leading to race conditions
//			propertyChangeSupport.firePropertyChange("this", null, this); // evaluate pre conditions
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
//	public BeginActionModel getBeginActionModel() {
//		return beginActionModel;
//	}
//	public void setBeginActionModel(BeginActionModel beginActionModel) {
//		this.beginActionModel = beginActionModel;
//	}

    @Visible(false)
    @Override
    public boolean isGraderStarted() {
        return graderStarted;
    }

    @Override
    @Visible(false)
    public void setGraderStarted(boolean graderStarted) {
//        System.out.println("Grader started: " + this.graderStarted + " -> " + graderStarted);
        this.graderStarted = graderStarted;
        propertyChangeSupport.firePropertyChange("this", null, this); // evaluate pre conditions

    }
//	
//	public void removeFeatureSpreadsheet() {
//		boolean retVal = database.getAssigmentDataFolder().removeFeatureGradeFile();
//		
//	}
//	
//	public boolean preRestoreFeatureSpreadsheet() {
//		return database.getAssigmentDataFolder().backupExists();
//	}
//	
//	public void restoreFeatureSpreadsheet() {
//		boolean retVal = database.getAssigmentDataFolder().restoreFeatureGradeFile();
//		
//	}
    
    void basicSetCurrentProblem(String aProblem) {
        ProblemUserChange.newCase(currentProblem, this, this);
    	Tracer.info(this, "Setting current problem to:" + aProblem);
        currentProblem = aProblem;
    }

    void setCurrentProblem(String aProblem) {
//    	System.out.println("Setting current problem to:" + aProblem);
//        currentProblem = aProblem;
    	basicSetCurrentProblem(aProblem);
//        problemDownloadPath = moduleDownloadPath + "\\" + currentProblem;
        problemDownloadPath = moduleDownloadPath + System.getProperty("file.separator") + currentProblem;
        refreshProblemDownloadPath();
//		ProblemUserChange.newCase(currentProblem, this, this);

    }

    @Override
    @Visible(false)
    public String getCurrentProblem() {
        return currentProblem;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener aListener) {
        propertyChangeSupport.addPropertyChangeListener(aListener);

        settingsLoaded = true; // OE has opened it

    }
    SakaiProjectDatabase projectDatabase;

    void maybeCreateProjectDatabase() {
        if (projectDatabase != null) {
            return;
        }
        saveSettings();
        Driver.initAssignmentDataFolder();
        projectDatabase = new ProjectDatabaseWrapper();
        projectDatabase.setGraderSettings(this);
    }

    @Visible(false)
    @Override
    public void cleanAllSubmissionFolders() {
//		saveSettings();
//		Driver.initAssignmentDataFolder();
//		projectDatabase = new ProjectDatabaseWrapper();
//		projectDatabase.setGraderSettings(this);
        maybeCreateProjectDatabase();
        projectDatabase.getStudentAssignmentDatabase().cleanAllFeedbackAndSubmissionFolders();

    }

    @Override
    @Position(4)
    // making this 3 causes OE to put it at position 2, bug in OE
    public void resetFeatureSpreadsheet() {
        maybeCreateProjectDatabase();
        projectDatabase.getAssignmentDataFolder().removeFeatureGradeFile();
    }

    @Override
    @Explanation("Reset grades of all students in the class, cleaning the entire spreadsheet")
    @Position(2)
    public void cleanSlateAll() {
    	System.out.println("Clearing scores of all students");
        maybeCreateProjectDatabase();
        projectDatabase.getAssignmentDataFolder().removeFeatureGradeFile();
        projectDatabase.getStudentAssignmentDatabase().cleanAllFeedbackAndSubmissionFolders();
    }

    @Override
    @Position(6)
    public void compileExecutor() {
        ExecutorSelector.getExecutor().compile();
    }

    @Override
    @Position(0)
    @Explanation("Reset grades of student specified as argument of this operation")
    public void cleanSlate(String anOnyen) {
    	System.out.println("Clearing scores of student:" + anOnyen);

        maybeCreateProjectDatabase();
        FeatureGradeRecorder featureGradeRecorder = projectDatabase.getFeatureGradeRecorder();

        featureGradeRecorder.clearGrades(anOnyen, "");
        SakaiProject aProject = projectDatabase.getProject(anOnyen);
        if (aProject == null) {
            System.out.println("Did not find project of:" + anOnyen + " nothing to clean");
            return;
        }
        StudentAssignment aStudentAssignment = aProject.getStudentAssignment();
        if (aStudentAssignment != null) {
            aStudentAssignment.cleanFeedbackFolder();
            aStudentAssignment.cleanSubmissionFolder();
        }

//        projectDatabase.getAssignmentDataFolder().removeFeatureGradeFile();
//        projectDatabase.getStudentAssignmentDatabase().cleanFeedbackAndSubmissionFolder(anOnyen);
    }
    @Override
    @Position(1)
    @Explanation("Reset grades of student specified by start, end, and gotoonyens")
    public void cleanSlateSpecified() {
    	List<String> anOnyens = NavigationListManagerFactory.getNavigationListManager().getRawOnyenNavigationList();
    	for (String anOnyen:anOnyens) {
    		cleanSlate(anOnyen);
    	}
    	
    }

//	public void maybePreCompile() {
////      	if (!AProject.isCompileMissingObjectCode()) return;
//		maybeCreateProjectDatabase();
//		List<String> onyens = projectDatabase.getOnyenNavigationList();
//		OnyenRangeModel anOnyenRangeModel = getOnyens();
//		String aStartOnyen = anOnyenRangeModel.getStartingOnyen();
//		String anEndOnyen = anOnyenRangeModel.getEndingOnyen();
//		
//		for (String anOnyen:onyens) {
//			if (aStartOnyen.compareTo(anOnyen) <= 0 && anEndOnyen.compareTo(anOnyen) >= 0) {
//				try {
//					new ProjectWrapper(projectDatabase.getProject(anOnyen), anOnyen);
//				} catch (FileNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}		
//		
//	}
    @Override
    @Visible(false)
    public boolean maybePreUnzip() {
        if (!BasicGradingEnvironment.get().isUnzipFiles()) {
            return false;
        }
        maybeCreateProjectDatabase();

        List<String> onyens = projectDatabase.getOnyenNavigationList();
//		OnyenRangeModel anOnyenRangeModel = getOnyens();
//		String aStartOnyen = GraderSettings.get().
//		String anEndOnyen = anOnyenRangeModel.getEndingOnyen();

        for (String anOnyen : onyens) {
            try {
                System.out.println("Unzipping directory for onyen:" + anOnyen);
                ProjectWrapper.getDirectoryAndMaybeUnzip(projectDatabase.getProject(anOnyen));
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
//		e.printStackTrace();
                System.out.println("Could not unzip project for student:" + anOnyen + " " + e);
                e.printStackTrace();
            }
////			if (aStartOnyen.compareTo(anOnyen) <= 0 && anEndOnyen.compareTo(anOnyen) >= 0) {
//            try {
//                new ProjectWrapper(projectDatabase.getProject(anOnyen), anOnyen);
//            } catch (FileNotFoundException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//			}
        }

        return true;

    }

    @Override
    @Visible(false)
    public boolean maybePreCompile() {
        if (!BasicGradingEnvironment.get().isPreCompileMissingObjectCode()) {
            return false;
        }
        maybeCreateProjectDatabase();

        List<String> onyens = projectDatabase.getOnyenNavigationList();
//		OnyenRangeModel anOnyenRangeModel = getOnyens();
//		String aStartOnyen = GraderSettings.get().
//		String anEndOnyen = anOnyenRangeModel.getEndingOnyen();

        for (String anOnyen : onyens) {
//			if (aStartOnyen.compareTo(anOnyen) <= 0 && anEndOnyen.compareTo(anOnyen) >= 0) {
            try {
                new ProjectWrapper(projectDatabase.getProject(anOnyen), anOnyen);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
//			}
        }
        return true;

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getSource() == moduleProblemSelector.getProblem()) {
            String newProblem = (String) moduleProblemSelector.getProblem().getValue();
            if (newProblem == null) {
                return;
            }

            if (currentProblem != null && currentProblem.equals(moduleProblemSelector.getProblem().getValue())) {
                return;
            }
            setCurrentProblem(moduleProblemSelector.getProblem().getValue());
//            ProblemUserChange.newCase(currentProblem, this, this);

//			currentProblem = moduleProblemSelector.getProblem().getValue();
//			problemDownloadPath = moduleDownloadPath + "/" +  currentModule;
//			refreshProblemDownloadPath();
        } else if (evt.getSource() == moduleProblemSelector.getModule()) {
            if (currentModule.equals(moduleProblemSelector.getModule().getValue())) {
                return;
            }
            setCurrentModule(moduleProblemSelector.getModule().getValue());
//            ModuleUserChange.newCase(currentModule, this, this);

        } else if (evt.getSource() == fileBrowsing.getDownloadFolder().getLabel()) {
            String newPath = fileBrowsing.getDownloadFolder().getLabel().getText();
            if (newPath == null) {
                return;
            }
            if (problemDownloadPath != null && problemDownloadPath.equals(newPath)) {
                return; // bounce back
            }
            graderSettingsManager.setDownloadPath(currentModule, newPath);
            refreshAll();
            DownloadPathUserChange.newCase(newPath, this, this);
        } else if (evt.getSource() == onyens) {
        	if (evt.getPropertyName().equals("displayedStartingOnyen")) {
        		GraderSettings.get().set(START_ONYEN,  (String)evt.getNewValue());
        		
        	} else if (evt.getPropertyName().equals("displayedEndingOnyen")) {
        		GraderSettings.get().set(END_ONYEN, (String) evt.getNewValue());
        	}
        }

    }

    @Visible(false)
    public boolean isPrivacyMode() {
        return privacyMode;
    }
    @Visible(false)
    public void setPrivacyMode(boolean newValue) {
        if (privacyMode == newValue) {
            return;
        }
        this.privacyMode = newValue;
        propertyChangeSupport.firePropertyChange("onyens", null, onyens);
    }
    @Position(5)
    public void togglePrivacyMode() {
        setPrivacyMode(!privacyMode);
    }

    @Visible(false)
    @Override
    public boolean getCompileMode() {
        return compileMode;
    }

    public static void main(String[] args) {
        AGraderSettingsModel startModel = new AGraderSettingsModel(null);
        OEFrame frame = ObjectEditor.edit(startModel);
        frame.setTitle("Grader Settings");
        frame.setSize(550, 475);
    }

}
