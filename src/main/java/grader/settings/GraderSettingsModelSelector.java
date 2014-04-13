package grader.settings;

public class GraderSettingsModelSelector {
	static GraderSettingsModel  graderSettingsModel = new AGraderSettingsModel();

	public static GraderSettingsModel getGraderSettingsModel() {
		return graderSettingsModel;
	}

	public static void setGraderSettingsModel(
			GraderSettingsModel graderSettingsModel) {
		GraderSettingsModelSelector.graderSettingsModel = graderSettingsModel;
	}
	
//	static {
//		graderSettingsManager = new AGraderSettingsManager();
//	}


}
