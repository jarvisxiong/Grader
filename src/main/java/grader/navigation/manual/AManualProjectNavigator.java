package grader.navigation.manual;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.JOptionPane;

import bus.uigen.OEFrame;
import framework.execution.reflectionObjects.ManualProject;
import grader.sakai.project.ASakaiProjectDatabase;
import grader.sakai.project.MissingOnyenException;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import util.trace.Tracer;

public class AManualProjectNavigator implements ManualProjectNavigator {
	SakaiProjectDatabase database;
	public AManualProjectNavigator(SakaiProjectDatabase aDatabase) {
		database = aDatabase;
	}
	
	public void navigate(GraderSettingsModel settingsModel, OEFrame settingsFrame) {
		if (settingsModel == null) return;
		while (true) {
		String goToOnyen = settingsModel.getOnyens().getGoToOnyen();
		Object aFrame = null;
		try {
		
			boolean retVal = database.startProjectStepper(goToOnyen);
//			 aFrame = database.displayProjectStepper(database.getProjectStepper());
			if (retVal)
				break;
			else {
				String message = "Did not find any entries matching filter. Try again.";
				Tracer.error(message);
				JOptionPane.showMessageDialog(null, message);
//				ASakaiProjectDatabase.dispose(aFrame);
			}
			
		} catch (MissingOnyenException moe) {
			String message = "Student:" + goToOnyen + " not in specified range. Try again.";
			Tracer.error(message);
			JOptionPane.showMessageDialog(null, message);

	//			ASakaiProjectDatabase.dispose(aFrame);
		}
		
			
			settingsModel.awaitBegin();
//
//	}
//	// if (!goToOnyen.isEmpty()) {
//	//
//	// ASakaiProjectDatabase.setVisible(database.getProjectStepper().getFrame(),
//	// true);
	}
	if (settingsFrame != null)
		settingsFrame.dispose();
	database.displayProjectStepper(database.getProjectStepper());

	}

}
