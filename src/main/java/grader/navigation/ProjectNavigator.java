package grader.navigation;

import grader.settings.GraderSettingsModel;
import bus.uigen.OEFrame;

public interface ProjectNavigator {
	public void navigate(GraderSettingsModel settingsModel, OEFrame settingsFrame, boolean exitOnompletion);

}
