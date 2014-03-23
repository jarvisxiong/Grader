package grader.navigation.automatic;

import javax.swing.JOptionPane;

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
			OEFrame settingsFrame, boolean exitOnCompletion) {
		boolean animate = settingsModel.getNavigationSetter().getAutomaticNavigationSetter().getAnimateGrades();
		if (animate && settingsFrame != null)
			settingsFrame.dispose(); // keep only one frame around at a time
		try {
			database.startProjectStepper("");
			projectStepper = database.getProjectStepper();
			
		} catch (MissingOnyenException e) {
			e.printStackTrace(); // should never come here
		}
//		if (settingsFrame != null)
//			settingsFrame.dispose();
//		boolean animate = settingsModel.getNavigationSetter().getAutomaticNavigationSetter().getAnimateGrades();
		Object frame = null;
		if (animate) {
		frame = database.displayProjectStepper(database.getProjectStepper());
		}
		long sleepTime = settingsModel.getNavigationSetter().getAutomaticNavigationSetter().getAnimationPauseTime()*1000;
		projectStepper.setPlayMode(true);
		int onyensSize = database.getOnyenNavigationList().size();
		while (true) {
			if (!animate)
				;// ThreadSupport.sleep(1000); // to avoid race conditions
			else {
			ThreadSupport.sleep(sleepTime);
			if (!projectStepper.isPlayMode())
				clearanceManager.waitForClearance();
			}
			if (projectStepper.getCurrentOnyenIndex() < onyensSize - 1)
			   projectStepper.move(true);
			else {
				projectStepper.save();
				break;
			}
			
		}
		if (frame != null)
			ASakaiProjectDatabase.dispose(frame);
		else if (settingsFrame != null)
			settingsFrame.dispose(); // visual indication things are complete
		String automaticExitMessage = "Automatic grading complete.";
		System.out.println(automaticExitMessage);
//		JOptionPane.showMessageDialog(null, "Automatic grading complete.");
		if (exitOnCompletion)
		System.exit(0);
		
	}

}
