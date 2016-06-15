package grader.navigation.hybrid;

import grader.navigation.NavigationKind;
import grader.navigation.ProjectNavigator;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import bus.uigen.OEFrame;

public class AHybridProjectNavigator implements HybridProjectNavigator{
	SakaiProjectDatabase database;
	ProjectNavigator automaticNavigator, manualNavigator;
	public AHybridProjectNavigator(SakaiProjectDatabase aDatabase) {
		database = aDatabase;
		automaticNavigator = database.getAutomaticProjectNavigator();
		manualNavigator = database.getManualProjectNavigator();
	}

	@Override
	public void navigate(GraderSettingsModel settingsModel,
			OEFrame settingsFrame, boolean exitOCompletion) {
		settingsModel.getNavigationSetter().setNavigationKind(NavigationKind.AUTOMATIC);		
		automaticNavigator.navigate(settingsModel, settingsFrame, false);
		settingsModel.getNavigationSetter().setNavigationKind(NavigationKind.MANUAL);	
		manualNavigator.navigate(settingsModel, settingsFrame, true);		
	}

}
