package grader.navigation;

import bus.uigen.OEFrame;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;

public class AProjectNavigator implements ProjectNavigator {
	SakaiProjectDatabase database;
	public AProjectNavigator(SakaiProjectDatabase aDatabase) {
		database = aDatabase;
	}
	@Override
	public void navigate(GraderSettingsModel settingsModel,
			OEFrame settingsFrame, boolean exitOnompletion) {
		NavigationKind navigationKind = settingsModel.getNavigationSetter().getNavigationKind();
		switch (navigationKind) {
		case MANUAL: 
			database.getManualProjectNavigator().navigate(settingsModel, settingsFrame, false);
			break;
		case AUTOMATIC:
			database.getAutomaticProjectNavigator().navigate(settingsModel, settingsFrame, true);
			break;
		case HYBRID:
				database.getHybridProjectNavigator().navigate(settingsModel, settingsFrame, true);
				break;			
		}
		
		
		
	}

}
