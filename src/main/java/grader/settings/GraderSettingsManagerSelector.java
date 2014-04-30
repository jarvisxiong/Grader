package grader.settings;

public class GraderSettingsManagerSelector {
	static GraderSettingsManager  graderSettingsManager = new AGraderSettingsManager();

	public static GraderSettingsManager getGraderSettingsManager() {
		return graderSettingsManager;
	}

	public static void setGraderSettingsManager(
			GraderSettingsManager graderSettingsManager) {
		GraderSettingsManagerSelector.graderSettingsManager = graderSettingsManager;
	}
	
//	static {
//		graderSettingsManager = new AGraderSettingsManager();
//	}


}
