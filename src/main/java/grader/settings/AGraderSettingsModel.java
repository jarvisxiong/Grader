package grader.settings;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import framework.utils.GraderSettings;
import framework.utils.GradingEnvironment;
import grader.modules.AModuleProblemSelector;
import grader.modules.ModuleProblemSelector;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.folders.AGraderFilesSetterModel;
import grader.settings.folders.AnOnyenRangeModel;
import grader.settings.folders.GraderFilesSetterModel;
import grader.settings.folders.OnyenRangeModel;
import grader.settings.navigation.ANavigationFilterSetter;
import grader.settings.navigation.ANavigationSetter;
import grader.settings.navigation.NavigationSetter;
import util.annotations.ComponentHeight;
import util.annotations.Label;
import util.annotations.Row;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Visible;
import util.misc.Common;
import util.trace.Tracer;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@Label("Starter")
public class AGraderSettingsModel implements GraderSettingsModel{
	GraderFilesSetterModel fileBrowsing = new AGraderFilesSetterModel();
	NavigationSetter navigationSetter = new ANavigationSetter(this);
	OnyenRangeModel onyens = new AnOnyenRangeModel(this);
	ModuleProblemSelector moduleProblemSelector; 
	List<String> modules = new ArrayList();
	List<String> problems = new ArrayList();
	String currentModule;
	List<String> currentProblems;
	String currentModulePrefix;
	PropertiesConfiguration configuration, dynamicConfiguration;
	String downloadPath;
	


	//	BeginActionModel beginActionModel = new ABeginActionModel();
	boolean graderStarted;
	PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	SakaiProjectDatabase database;
	
	public AGraderSettingsModel(SakaiProjectDatabase aDatabase) {
		database = aDatabase;
		configuration = GradingEnvironment.get().getConfigurationManager().getStaticConfiguration();
		dynamicConfiguration = GradingEnvironment.get().getConfigurationManager().getDynamicConfiguration();
		
//		loadSettings();
		loadDynamicConfigurationSettings();
	}
	public AGraderSettingsModel() {
//		database = aDatabase;
		
//		AModuleProblemSelector moduleProblem = new AModuleProblemSelector(modules, problems);
		
		loadSettings();
	}
	
	void setCurrentModule(String newValue) {
		
		 currentModule = newValue;
		 currentModulePrefix = configuration.getString(currentModule + ".problems.prefix")	;
		if (currentModulePrefix == null)
			currentModulePrefix = configuration.getString("default.problems.prefix", "Assignment");
		problems.clear();
		if (downloadPath == null) {
			;
		} else {
			File folder = new File(downloadPath);
			if (!folder.exists()) {
				Tracer.error("No folder found for:" + downloadPath);				
			} else {
				File gradesFile = new File(downloadPath + "/grades.csv");
				if (gradesFile.exists()) 
					folder = folder.getParentFile();
				File[] children = folder.listFiles();
				for (File child:children) {
					if (child.getName().startsWith(currentModulePrefix))
						problems.add(child.getName());
				}
			}
		}
		
		if (moduleProblemSelector != null) {
			moduleProblemSelector.getProblem().setChoices(problems); // it is the same object but we need to fire property change
		}
		
		
	}
	
	void loadSettings() {
		
		
		String editor;
		if (GraderSettings.get().has("editor")) {
			editor = GraderSettings.get().get("editor");
            GradingEnvironment.get().setEditor(editor); // why not for path also, perhaps its not used later?
        } else
            editor = GradingEnvironment.get().getEditor();
        fileBrowsing.getTextEditor().getLabel().setText(editor);
//        String downloadPath;
        if (GraderSettings.get().has("path")) {
            String downloadPath = GraderSettings.get().get("path");
            fileBrowsing.getDownloadFolder().getLabel().setText(downloadPath);
        }
        if (GraderSettings.get().has("start")) {
        	String startingOnyen = GraderSettings.get().get("start");
        	onyens.setStartingOnyen(startingOnyen);
        }
        if (GraderSettings.get().has("end")) {
        	String endingOnyen = GraderSettings.get().get("end");
        	onyens.setEndingOnyen(endingOnyen);
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
	   void maybeConvertToDynamicConfiguration() {
		 	Map<String, String> settings = GraderSettings.get().getSettings();
	    	PropertiesConfiguration dynamicConfiguration = GradingEnvironment.get().getConfigurationManager().getDynamicConfiguration();
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
	
	void loadDynamicConfigurationSettings() {
		
		maybeConvertToDynamicConfiguration();
		String editor = dynamicConfiguration.getString("editor");
		if (editor != null) {
//			editor = GraderSettings.get().get("editor");
            GradingEnvironment.get().setEditor(editor); // why not for path also, perhaps its not used later?
        } else
            editor = GradingEnvironment.get().getEditor();
        fileBrowsing.getTextEditor().getLabel().setText(editor);
//        String downloadPath;
        downloadPath =  dynamicConfiguration.getString("path");
        if (downloadPath != null) {
//            String downloadPath = GraderSettings.get().get("path");
            fileBrowsing.getDownloadFolder().getLabel().setText(downloadPath);
        }
//        else {
//        	 fileBrowsing.getDownloadFolder().getLabel().setText("Please enter  folder");
//        }
        String startingOnyen =  dynamicConfiguration.getString("start");
        
        if (startingOnyen != null) {
//        	String startingOnyen = GraderSettings.get().get("start");
        	onyens.setStartingOnyen(startingOnyen);
        }
        String endingOnyen = dynamicConfiguration.getString("end");
        if (endingOnyen != null) {
        	onyens.setEndingOnyen(endingOnyen);
        }
        List objectModules = GradingEnvironment.get().getConfigurationManager().getStaticConfiguration().getList("modules");
		modules = objectModules;
		if (objectModules.size() == 0) {
			Tracer.error("No modules specified in configuration file!");
			System.exit(-1);
		}
		 currentModulePrefix =  GradingEnvironment.get().getConfigurationManager().getStaticConfiguration().getString(currentModule + ".problems.prefix")	;

		if (currentModulePrefix == null)
			currentModulePrefix = GradingEnvironment.get().getConfigurationManager().getStaticConfiguration().getString("default.problems.prefix", "Assignment");
//				Common.arrayToArrayList(new String[] {"Comp110", "Comp401"});
//		List<String> problems = Common.arrayToArrayList(new String[] {"A1", "A2"});
		setCurrentModule(GradingEnvironment.get().getConfigurationManager().getDynamicConfiguration().getString("currentModule", modules.get(0)));

		moduleProblemSelector = new AModuleProblemSelector(modules, problems);
	}
	
	
	void saveSettings() {
        // Update the settings
		String editor = fileBrowsing.getTextEditor().getLabel().getText();
		String downloadPath = fileBrowsing.getDownloadFolder().getLabel().getText();
		String startingOnyen = onyens.getStartingOnyen();
		String endingOnyen = onyens.getEndingOnyen();
        GraderSettings.get().set("start", startingOnyen);
        GraderSettings.get().set("end", endingOnyen);
        GraderSettings.get().set("path", downloadPath);
//        ASakaiProjectDatabase.setCurrentSakaiProjectDatabase(new ASakaiProjectDatabase(downloadPath, null));
        GraderSettings.get().set("editor", editor);
        GraderSettings.get().save();
	}
	@Row(0)
	public ModuleProblemSelector getModuleProblemSelector() {
		return moduleProblemSelector;
	}
	public void setModuleProblemSelector(ModuleProblemSelector moduleProblemSelector) {
		this.moduleProblemSelector = moduleProblemSelector;
	}
	
	@Row(1)
	public GraderFilesSetterModel getFileBrowsing() {
		return fileBrowsing;
	}
	public void setFileBrowsing(GraderFilesSetterModel fileBrowsing) {
		this.fileBrowsing = fileBrowsing;
	}
	@Row(2)
	public OnyenRangeModel getOnyens() {
		return onyens;
	}
	public void setOnyens(OnyenRangeModel onyens) {
		this.onyens = onyens;
	}	
	@Row(3)
	@Override
//	@Visible(false)
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
	public synchronized void begin() {
		notify();
		
	}
	
	
	@Visible(false)
	public synchronized void awaitBegin() {
		graderStarted = false;
		// see comment about race conditions
//		propertyChangeSupport.firePropertyChange("this", null, this); // evaluate pre conditions
		try {
			wait();
			saveSettings();
			graderStarted = true;
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
		this.graderStarted = graderStarted;
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

	public static void main (String[] args) {
		AGraderSettingsModel startModel = new AGraderSettingsModel(null);
		OEFrame frame = ObjectEditor.edit(startModel);
		frame.setTitle("Grader Settings");
		frame.setSize(550, 475);
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyChangeSupport.addPropertyChangeListener(aListener);
		
	}
	

}
