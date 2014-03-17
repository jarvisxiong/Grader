package grader.navigation.automatic;

import util.misc.ClearanceManager;
import util.misc.ThreadSupport;
import bus.uigen.OEFrame;
import grader.sakai.project.ASakaiProjectDatabase;
import grader.sakai.project.MissingOnyenException;
import grader.sakai.project.ProjectStepper;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;

public class AnAutomaticProjectNavigator implements AutomaticProjectNavigator{
	SakaiProjectDatabase database;
	ClearanceManager clearanceManager;
	ProjectStepper projectStepper;
	public AnAutomaticProjectNavigator(SakaiProjectDatabase aDatabase) {
		database = aDatabase;
		clearanceManager = database.getClearanceManager();
		
	}
	@Override
	public void navigate(GraderSettingsModel settingsModel,
			OEFrame settingsFrame) {
		try {
			database.startProjectStepper("");
			projectStepper = database.getProjectStepper();
			
		} catch (MissingOnyenException e) {
			e.printStackTrace(); // should never come here
		}
		if (settingsFrame != null)
			settingsFrame.dispose();
		boolean animate = settingsModel.getNavigationSetter().getAutomaticNavigationSetter().getAnimateGrades();
		Object frame = null;
		if (animate)
		frame = database.displayProjectStepper(database.getProjectStepper());
		long sleepTime = settingsModel.getNavigationSetter().getAutomaticNavigationSetter().getAnimationPauseTime()*1000;
		projectStepper.setPlayMode(true);
		int onyensSize = database.getOnyenNavigationList().size();
		while (true) {
			if (!animate)
				ThreadSupport.sleep(1000); // to avoid race conditions
			else {
			ThreadSupport.sleep(sleepTime);
			if (!projectStepper.isPlayMode())
				clearanceManager.waitForClearance();
			}
			if (projectStepper.getCurrentOnyenIndex() < onyensSize - 1)
			   projectStepper.next();
			else
				break;
			
		}
		if (frame != null)
			ASakaiProjectDatabase.dispose(frame);
		
	}

}
