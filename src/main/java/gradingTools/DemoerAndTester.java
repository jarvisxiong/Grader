package gradingTools;

import grader.assignment.GradingFeature;
import grader.project.graded.ComplexProjectStepper;
import grader.project.graded.OverviewProjectStepper;

import java.util.List;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;
import util.misc.AClearanceManager;
import util.misc.ClearanceManager;
import util.misc.ThreadSupport;

public class DemoerAndTester implements Runnable{
	static String[] args ;
	static DemoAndTestingClearanceManager clearanceManager = new ADemoAndTestingClearanceManager();
//	static boolean autoProceed;
//	static long autoProceedPauseTime = 4000;
	public static void main (String[] anArgs) {
		ObjectEditor.setDefaultAttribute(AttributeNames.SHOW_SYSTEM_MENUS, false);
		args = anArgs;
//		Thread mainThread = new Thread(new Tester());
//		mainThread.start();
//		OEFrame clearanceFrame = ObjectEditor.edit(clearanceManager);
//		clearanceFrame.setSize(420, 260);
//		clearanceFrame.setLocation(0, 0);
		startFirstSession();

		doSteps();
//		waitForUserOrSleep();
//		Driver.getSettingsModel().begin();
	}
	
	static void startFirstSession() {
		Thread mainThread = new Thread(new DemoerAndTester());
		mainThread.start();
		OEFrame clearanceFrame = ObjectEditor.edit(clearanceManager);
		clearanceFrame.setSize(420, 260);
		clearanceFrame.setLocation(0, 0);

	}
	
	static void startSecondSession() {
		Thread mainThread = new Thread(new DemoerAndTester());
		mainThread.start();
		

	}
	
	public static void doSteps() {
		waitForStepper();
		changeProblem();
		doBegin();
		waitForNavigator();
		changeOverallNotes();
		doNext();
		doSelect();
		changeManualScore();
		changeManualNotes();
		doValidate();
		commentOnCode();
		changeOverallScore();
		changeOverallNotes2();
		quit1();
		secondSession();
		waitForStepper();		
		doBegin();
		changeOverallNotes3();
		quit2();
		
	}
	public static void waitForStepper() {
		clearanceManager.setStepDescription("Wait until setter UI comes up and then press proceed to start or advance to next step. \n\nIf auto perform step is set, then press proceed to perform subsequent steps; otherwise use the UI to perform the next described step. \n\nYou can change this checkbox.");
		waitForUserOrSleep();
	}
	
	public static void waitForNavigator() {
		clearanceManager.setStepDescription("Look at the console for output of autograded steps and missing submission folders. \n\nNow wait until the navigator UI comes up and then press proceed to go to first navigation step.");
		waitForUserOrSleep();
	}
	
	public static void changeProblem() {
		Driver.getSettingsModel().getModuleProblemSelector().getProblem().setValue("Assignment1");
		clearanceManager.setStepDescription("Next step is to automatically change problem from Assignment1 to Assignment3 and cleanup any previous grading results for this assignment.");
		waitForUserOrSleep();
		if (clearanceManager.isAutoPerformStep()) {
		Driver.getSettingsModel().getModuleProblemSelector().getProblem().setValue("Assignment3");
		Driver.getSettingsModel().cleanSlate();
		}

	}
	public static void doBegin() {
		clearanceManager.setStepDescription("Next step is to begin navigation. \n\nThe settings window will remain on the screen while the auto grading phase is being performed and then the navigator window will appear.");
		waitForUserOrSleep();
		if (clearanceManager.isAutoPerformStep())
		Driver.getSettingsModel().begin();
//		ThreadSupport.sleep(4000);
	}
	
	public static void changeOverallNotes() {
		clearanceManager.setStepDescription("Next step is to change the overall notes to congratulate the student. \n\nIts color should change to indicate the comment presence.");
//		Driver.getDatabase().getProjectStepper().setOverallNotes("");
		waitForUserOrSleep();
		if (clearanceManager.isAutoPerformStep())
		Driver.getDatabase().getProjectStepper().setOverallNotes("All correct!");
	}
	public static void doNext() {
		clearanceManager.setStepDescription("Next step is to navigate to next student. \n\nWait until the UI is refreshed before proceeding. \n\nThere should be two pink items (6 and 7) for non-full points indicating attention.");
		waitForUserOrSleep();
		if (clearanceManager.isAutoPerformStep())
		Driver.getDatabase().getProjectStepper().next();
	}
	public static void doSelect() {
		List<GradingFeature> gradingFeatures = Driver.getDatabase().getGradingFeatures();
		GradingFeature gradingFeature = gradingFeatures.get(6);
		GradingFeature prevGradingFeature = gradingFeatures.get(5);
//		gradingFeature.setManualNotes("");
//		prevGradingFeature.setManualNotes("");	
		Driver.getDatabase().getProjectStepper().setOverallNotes("");
		clearanceManager.setStepDescription("Next step is check the select box in item 7 to select it.\n\nThe auto notes box should show the reason for point deduction for the selected item. \n\nThe transcript box should show the output for this feature. \n\nThe manual notes will show the previous manual notes for this item, which should be empty.");
		waitForUserOrSleep();
//		List<GradingFeature> gradingFeatures = Driver.getDatabase().getGradingFeatures();
//		GradingFeature gradingFeature = gradingFeatures.get(6);
		if (clearanceManager.isAutoPerformStep())
		gradingFeature.setSelected(true);
	}
	public static void changeManualScore() {
		clearanceManager.setStepDescription("Next step is to manually override the auto computed score. \n\nThe item should remain pink to indicate no reason has been given for change.");
		waitForUserOrSleep();
		List<GradingFeature> gradingFeatures = Driver.getDatabase().getGradingFeatures();
		GradingFeature gradingFeature = gradingFeatures.get(6);
		if (clearanceManager.isAutoPerformStep())
		gradingFeature.setScore(gradingFeature.getScore() * 1.1);
	}
	public static void changeManualNotes() {
		clearanceManager.setStepDescription("Next step is to add manual notes for selected item. \n\nThe manual notes and item should become green to indicate prsence of non empty comment."); 
		waitForUserOrSleep();
//		List<GradingFeature> gradingFeatures = Driver.getDatabase().getGradingFeatures();
//		GradingFeature gradingFeature = gradingFeatures.get(6);
		if (clearanceManager.isAutoPerformStep())
		Driver.getDatabase().getProjectStepper().setManualNotes("The transcript  shows that indicated output syntax was misunderstood. Gave partial credit.");
//		gradingFeature.setManualNotes("The source code shows that indicated output syntax was misunderstood. Gave partial credit.");
	}
	public static void doValidate() {
		clearanceManager.setStepDescription("Next step is to check the validate box in item 6 to indicate that the automantically computed non full score is correct. \n\nThe item  color should change. \n\nIn addition, the item should get selected. \n\nThe auto notes box should indicate the reason for deduction. The manual notes box should contain notes indicating validation and change color to show presence of notes for selected item."); 
		waitForUserOrSleep();
		List<GradingFeature> gradingFeatures = Driver.getDatabase().getGradingFeatures();
		GradingFeature gradingFeature = gradingFeatures.get(5);
		if (clearanceManager.isAutoPerformStep())

		gradingFeature.setValidate(true);
	}
	public static void commentOnCode() {
		clearanceManager.setStepDescription("Go to feedback tab and confirm that changes you made are in the feedback.\n\n" +
										"Go to source tab to see all of the source code.\n\n" +
										"Next step is to insert a comment at the start in the code congratulating the student on good style.");
		waitForUserOrSleep();
		
		if (clearanceManager.isAutoPerformStep()) {
			String oldSource =  ((OverviewProjectStepper) Driver.getDatabase().getProjectStepper()).getSource();
			String newSource = "//Excellent style\n" + oldSource;
			((ComplexProjectStepper) Driver.getDatabase().getProjectStepper()).setSource(newSource);
		}

	}
	public static void changeOverallScore() {
		clearanceManager.setStepDescription("Go to main tab \n\n" +
										"Next step is to manually increase the overall score for good style as no grading feature was created for it.");
		waitForUserOrSleep();
		
		if (clearanceManager.isAutoPerformStep()) {
			double oldScore = Driver.getDatabase().getProjectStepper().getScore();
			 Driver.getDatabase().getProjectStepper().setScore(oldScore + 5);
		}

	}
	public static void changeOverallNotes2() {
		clearanceManager.setStepDescription("Next step is to change the overall notes to explain the reason for increase. ");
//		Driver.getDatabase().getProjectStepper().setOverallNotes("");
		waitForUserOrSleep();
		if (clearanceManager.isAutoPerformStep()) {
		Driver.getDatabase().getProjectStepper().setOverallNotes(Driver.getDatabase().getProjectStepper().getOverallNotes() + "\nExtra credit for style.");
		}
	}
	public static void quit1() {
		clearanceManager.setStepDescription("Next step is to quit the first grader session.");
		Driver.getDatabase().getProjectStepper().setExitOnQuit(false);

		waitForUserOrSleep();
		if (clearanceManager.isAutoPerformStep()) {
			((OverviewProjectStepper) Driver.getDatabase().getProjectStepper()).quit();
		}
//		System.exit(0);
	}
	public static void quit2() {
		clearanceManager.setStepDescription("Next step is to quit the second grader session and also this test/demo session.");
		Driver.getDatabase().getProjectStepper().setExitOnQuit(true);

		waitForUserOrSleep();
		if (clearanceManager.isAutoPerformStep()) {
			((OverviewProjectStepper) Driver.getDatabase().getProjectStepper()).quit();
		}
//		System.exit(0);
	}
	
	public static void secondSession() {
		clearanceManager.setStepDescription("Next step is to start new session. \n\nThis time we will not clean previous slate; continuing from saved data about the first session.");
		waitForUserOrSleep();
		startSecondSession();
		
	}
	public static void changeOverallNotes3() {
		clearanceManager.setStepDescription("Next step is to change the overall notes to further congratulate the student");
		
		waitForUserOrSleep();
		if (clearanceManager.isAutoPerformStep()) {
		Driver.getDatabase().getProjectStepper().setOverallNotes(Driver.getDatabase().getProjectStepper().getOverallNotes() + "\nExcellent - best performace!");
		}
	}
	
//	public static boolean isAutoProceed() {
//		return autoProceed;
//	}
//
//	public static void setAutoProceed(boolean autoProceed) {
//		Tester.autoProceed = autoProceed;
//	}
//
//	public static long getAutoProceedPauseTime() {
//		return autoProceedPauseTime;
//	}
//
//	public static void setAutoProceedPauseTime(long autoProceedPauseTime) {
//		Tester.autoProceedPauseTime = autoProceedPauseTime;
//	}

	static void waitForUserOrSleep() {
		if (clearanceManager.isAutoProceed())
			ThreadSupport.sleep(clearanceManager.getAutoPauseTime()*1000);
		else
			clearanceManager.waitForClearance();
	}

	@Override
	public void run() {
		Driver.main(args);
	}

}
