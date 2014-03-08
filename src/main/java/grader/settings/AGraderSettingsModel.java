package grader.settings;

import framework.utils.GraderSettings;
import framework.utils.GradingEnvironment;
import grader.settings.folders.AGraderFilesSetterModel;
import grader.settings.folders.AnOnyenRangeModel;
import grader.settings.folders.GraderFilesSetterModel;
import grader.settings.folders.OnyenRangeModel;
import grader.settings.navigation.ANavigationFilterSetter;
import grader.settings.navigation.ANavigationSetter;
import grader.settings.navigation.NavigationSetter;
import util.annotations.ComponentHeight;
import util.annotations.Row;
import util.annotations.Visible;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;

public class AGraderSettingsModel implements GraderSettingsModel{
	GraderFilesSetterModel fileBrowsing = new AGraderFilesSetterModel();
	NavigationSetter navigationSetter = new ANavigationSetter();
	OnyenRangeModel onyens = new AnOnyenRangeModel();
	BeginActionModel beginActionModel = new ABeginActionModel();
	
	public AGraderSettingsModel() {
		loadSettings();
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
	public GraderFilesSetterModel getFileBrowsing() {
		return fileBrowsing;
	}
	public void setFileBrowsing(GraderFilesSetterModel fileBrowsing) {
		this.fileBrowsing = fileBrowsing;
	}
	@Row(1)
	public OnyenRangeModel getOnyens() {
		return onyens;
	}
	public void setOnyens(OnyenRangeModel onyens) {
		this.onyens = onyens;
	}
	@Row(2)
	public NavigationSetter getNavigationSetter() {
		return navigationSetter;
	}

	public void setNavigationSetter(NavigationSetter navigationSetter) {
		this.navigationSetter = navigationSetter;
	}

	@Row(3)
	@ComponentHeight(25)
	public synchronized void begin() {
		notify();
		
	}
	
	
	@Visible(false)
	public synchronized void awaitBegin() {
		try {
			wait();
			saveSettings();
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

	public static void main (String[] args) {
		AGraderSettingsModel startModel = new AGraderSettingsModel();
		OEFrame frame = ObjectEditor.edit(startModel);
		frame.setTitle("Grader Settings");
		frame.setSize(550, 475);
	}
	

}
