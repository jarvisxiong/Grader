package grader.navigation;

import bus.uigen.OEFrame;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.settings.navigation.NavigationKind;

public class AProjectNavigator implements ProjectNavigator {
	SakaiProjectDatabase database;
	public AProjectNavigator(SakaiProjectDatabase aDatabase) {
		database = aDatabase;
	}
	@Override
	public void navigate(GraderSettingsModel settingsModel,
			OEFrame settingsFrame) {
		NavigationKind navigationKind = settingsModel.getNavigationSetter().getNavigationKind();
		switch (navigationKind) {
		case MANUAL: 
			database.getManualProjectNavigator().navigate(settingsModel, settingsFrame);
			break;
		case AUTOMATIC:
			database.getAutomaticProjectNavigator().navigate(settingsModel, settingsFrame);
			break;
		default:
				database.getManualProjectNavigator().navigate(settingsModel, settingsFrame);
				break;			
		}
		
		
		
	}

}
