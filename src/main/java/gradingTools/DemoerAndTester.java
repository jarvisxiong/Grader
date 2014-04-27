package gradingTools;

import grader.assignment.GradingFeature;
import grader.navigation.NavigationKind;
import grader.navigation.filter.GradingStatus;
import grader.project.graded.ComplexProjectStepper;
import grader.project.graded.OverviewProjectStepper;
import grader.project.source.ATACommentsExtractor;

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
	
	static final int CLEARANCE_WIDTH = 420;
	static final int CLEARANCE_HEIGHT = 260;

	
	static void startFirstSession() {
		Thread mainThread = new Thread(new DemoerAndTester());
		mainThread.start();
		OEFrame clearanceFrame = ObjectEditor.edit(clearanceManager);
		clearanceFrame.setSize(CLEARANCE_WIDTH, CLEARANCE_HEIGHT);
		clearanceFrame.setLocation(0, 0);
	}
	
	static void startSecondSession() {
		Thread mainThread = new Thread(new DemoerAndTester());
		mainThread.start();
	}
	
	public static void doSteps() {
		waitForStepper();
		initializeAndChangeProblem();
		doBegin();
		waitForNavigator();
		changeOverallNotes();
		doNext1();
		doSelect();
		changeManualScore();
		changeManualNotes();
		doValidate1();
//		explainSourceAndcommentOnCode();
		showSource();
		commentOnCode();
//		changeOverallScore();
//		changeOverallNotes2();
		showFeedback();
		showMain();
		doNext2();
//		openSource();
		showProblemHistory();
		showStudentHistory();
		showMain();
		syncSource();
		quit1();
//		System.exit(0);
		secondSession();
		waitForStepper();
		changeToNotFullyGraded();
		doBegin();
		doValidate2();
//		changeOverallNotes3();
	
		quit2();
		
	}
	public static void waitForStepper() {
		clearanceManager.setStepDescription("Wait until setter UI comes up and then press proceed to start or advance to next step. \n\nIf auto perform step is set, then press proceed to perform subsequent steps; otherwise use the UI to perform the next described step. \n\nYou can change this checkbox.");
		waitForUserOrSleep();
	}
	
	public static void waitForNavigator() {
		clearanceManager.setStepDescription("Look at the console for output of autograded steps and missing submission folders. \n\nNow wait until the navigator UI comes up and then press proceed to go to first navigation step.");
		waitForUserOrLongSleep();
	}
	
	public static void initializeAndChangeProblem() {
		Driver.getSettingsModel().getModuleProblemSelector().getProblem().setValue("Assignment1");
		Driver.getSettingsModel().getFileBrowsing().getDownloadFolder().setText("Test Data/Test 110 F13 Assignments/Assignment1");

		Driver.getSettingsModel().getNavigationSetter().getNavigationFilterSetter().setParameter(GradingStatus.ALL);

		clearanceManager.setStepDescription("Next steps are "
				+ "automatically change problem to Assignment1"
				+ ", set the download folder" 
				+ ", set the onyens"
				+ ", set navigation filter parameter to ALL"
				+ " and cleanup any previous grading results for this assignment.");
		waitForUserOrSleep();
		if (clearanceManager.isAutoPerformStep()) {
		Driver.getSettingsModel().getNavigationSetter().setNavigationKind(NavigationKind.HYBRID);
		Driver.getSettingsModel().getNavigationSetter().getNavigationFilterSetter().setParameter(GradingStatus.ALL);


		Driver.getSettingsModel().getModuleProblemSelector().getProblem().setValue("Assignment3");
		Driver.getSettingsModel().getOnyens().setDisplayedEndingOnyen("cwrong");
		Driver.getSettingsModel().getOnyens().setDisplayedStartingOnyen("acorrect");

		Driver.getSettingsModel().cleanSlate();
		}

	}
	public static void changeToNotFullyGraded() {
		clearanceManager.setStepDescription("Next step is to automatically change navigation filter from ALL to NOT_FULLY_GRADED so that we go to the next student who needs attentin.");
		waitForUserOrSleep();
		if (clearanceManager.isAutoPerformStep()) {
			Driver.getSettingsModel().getNavigationSetter().getNavigationFilterSetter().setParameter(GradingStatus.NOT_FULLY_GRADED);

		
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
	public static void doNext1() {
		clearanceManager.setStepDescription("Next step is to navigate to next student. \n\nWait until the UI is refreshed before proceeding. \n\nThere should be two pink items (6 and 7) for non-full points indicating attention.");
		waitForUserOrSleep();
		if (clearanceManager.isAutoPerformStep())
		Driver.getDatabase().getProjectStepper().next();
	}
	public static void doNext2() {
		clearanceManager.setStepDescription("Next step is to navigate to next student. \n\nAgain wait until the UI is refreshed before proceeding. ");
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
//		Driver.getDatabase().getProjectStepper().setOverallNotes("");
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
		clearanceManager.setStepDescription("Next step is to add manual notes for selected item. \n\nThe manual notes and item should become green to indicate presence of non empty comment."); 
		waitForUserOrSleep();
//		List<GradingFeature> gradingFeatures = Driver.getDatabase().getGradingFeatures();
//		GradingFeature gradingFeature = gradingFeatures.get(6);
		if (clearanceManager.isAutoPerformStep())
		Driver.getDatabase().getProjectStepper().setManualNotes("The transcript  shows that indicated output syntax was misunderstood. Gave partial credit.");
//		gradingFeature.setManualNotes("The source code shows that indicated output syntax was misunderstood. Gave partial credit.");
	}
	public static void doValidate1() {
		clearanceManager.setStepDescription("Next step is to check the validate box in item 6 to indicate that the automantically computed non full score is correct. \n\nThe item  color should change. \n\nIn addition, the item should get selected. \n\nThe auto notes box should indicate the reason for deduction. The manual notes box should contain notes indicating validation and change color to show presence of notes for selected item."); 
		waitForUserOrSleep();
		List<GradingFeature> gradingFeatures = Driver.getDatabase().getGradingFeatures();
		GradingFeature gradingFeature = gradingFeatures.get(5);
		if (clearanceManager.isAutoPerformStep())

		gradingFeature.setValidate(true);
	}
	public static void doValidate2() {
		clearanceManager.setStepDescription("Next steps is to validate the pink item (#6).");
		waitForUserOrSleep();
		List<GradingFeature> gradingFeatures = Driver.getDatabase().getGradingFeatures();
		GradingFeature gradingFeature = gradingFeatures.get(5);
		if (clearanceManager.isAutoPerformStep())

		gradingFeature.setValidate(true);
	}
	
	public static void showSource() {
		clearanceManager.setStepDescription("Next step is to go the source tab to view all of the source code.");
		waitForUserOrSleep();		
		if (clearanceManager.isAutoPerformStep()) {
			ComplexProjectStepper projectStepper = (ComplexProjectStepper)Driver.getDatabase().getProjectStepper();
			OEFrame stepperFrame = (OEFrame) projectStepper.getFrame();
			stepperFrame.focus(projectStepper, "source");			
		}
	}
	
	public static void showMain() {
		clearanceManager.setStepDescription("Next step is to go the main tab.");
		waitForUserOrSleep();		
		if (clearanceManager.isAutoPerformStep()) {
			ComplexProjectStepper projectStepper = (ComplexProjectStepper)Driver.getDatabase().getProjectStepper();
			OEFrame stepperFrame = (OEFrame) projectStepper.getFrame();
			stepperFrame.focus(projectStepper, "mainprojectstepper");					
		}
	}
	public static void showProblemHistory() {
		clearanceManager.setStepDescription("Next step is to go the problem history tab to see past source comments.");
		waitForUserOrSleep();		
		if (clearanceManager.isAutoPerformStep()) {
			ComplexProjectStepper projectStepper = (ComplexProjectStepper)Driver.getDatabase().getProjectStepper();
			OEFrame stepperFrame = (OEFrame) projectStepper.getFrame();
			stepperFrame.focus(projectStepper, "problemHistory");			
		}
	}
	public static void showStudentHistory() {
		clearanceManager.setStepDescription("Next step is to go the source history tab to see past student performance.");
		waitForUserOrSleep();		
		if (clearanceManager.isAutoPerformStep()) {
			ComplexProjectStepper projectStepper = (ComplexProjectStepper)Driver.getDatabase().getProjectStepper();
			OEFrame stepperFrame = (OEFrame) projectStepper.getFrame();
			stepperFrame.focus(projectStepper, "studentHistory");			
		}
	}
	public static void openSource() {
		clearanceManager.setStepDescription("Next step is to open source in registered editor.");
		waitForUserOrSleep();		
		if (clearanceManager.isAutoPerformStep()) {
			ComplexProjectStepper projectStepper = (ComplexProjectStepper)Driver.getDatabase().getProjectStepper();
//			OEFrame stepperFrame = (OEFrame) projectStepper.getFrame();
//			stepperFrame.focus(projectStepper, "source");	
			projectStepper.openSource();
		}
	}
	public static void syncSource() {
		clearanceManager.setStepDescription("Next step is to sync changes in external editor.");
		waitForUserOrSleep();		
		if (clearanceManager.isAutoPerformStep()) {
			ComplexProjectStepper projectStepper = (ComplexProjectStepper)Driver.getDatabase().getProjectStepper();
//			OEFrame stepperFrame = (OEFrame) projectStepper.getFrame();
//			stepperFrame.focus(projectStepper, "source");	
			projectStepper.sync();
		}
	}
	public static void commentOnCode() {
		clearanceManager.setStepDescription("Next step is to insert a comment at the start in the code congratulating the student on good style and giving him extra points. TA Comments can occur anywhere and are preceded by:" + ATACommentsExtractor.TA_STRING);
		waitForUserOrSleep();
		
		if (clearanceManager.isAutoPerformStep()) {
			ComplexProjectStepper projectStepper = (ComplexProjectStepper)Driver.getDatabase().getProjectStepper();
			
			String oldSource =  projectStepper.getSource();
			String newSource = "//TA:Excellent style +5\n" + oldSource;
			projectStepper.setSource(newSource);
			
//			String oldSource =  ((OverviewProjectStepper) Driver.getDatabase().getProjectStepper()).getSource();
//			String newSource = "//Excellent style\n" + oldSource;
//			((ComplexProjectStepper) Driver.getDatabase().getProjectStepper()).setSource(newSource);
		}

	}
	
	public static void showFeedback() {
		clearanceManager.setStepDescription("Next step is to go to the feedback tab and confirm that the manual and auto grading information about this student is in the feedback.\n\n");
		waitForUserOrSleep();		
		if (clearanceManager.isAutoPerformStep()) {
			ComplexProjectStepper projectStepper = (ComplexProjectStepper)Driver.getDatabase().getProjectStepper();
			OEFrame stepperFrame = (OEFrame) projectStepper.getFrame();
			stepperFrame.focus(projectStepper, "feedback");			
		}
	}
	
	public static void explainSourceAndcommentOnCode() {
		clearanceManager.setStepDescription("Go to feedback tab and confirm that changes you made are in the feedback.\n\n" +
										"Go to source tab to see all of the source code.\n\n" +
										"Next step is to insert a comment at the start in the code congratulating the student on good style.");
		waitForUserOrSleep();
		
		if (clearanceManager.isAutoPerformStep()) {
			ComplexProjectStepper projectStepper = (ComplexProjectStepper)Driver.getDatabase().getProjectStepper();
			OEFrame stepperFrame = (OEFrame) projectStepper.getFrame();
			stepperFrame.focus(projectStepper, "feedback");
			String oldSource =  projectStepper.getSource();
			String newSource = "//Excellent style\n" + oldSource;
			projectStepper.setSource(newSource);
			
//			String oldSource =  ((OverviewProjectStepper) Driver.getDatabase().getProjectStepper()).getSource();
//			String newSource = "//Excellent style\n" + oldSource;
//			((ComplexProjectStepper) Driver.getDatabase().getProjectStepper()).setSource(newSource);
		}

	}
	public static void changeOverallScore() {
		clearanceManager.setStepDescription("Next step is to manually increase the overall score for good style as no grading feature was created for it. ");
		waitForUserOrSleep();
		
		if (clearanceManager.isAutoPerformStep()) {
			ComplexProjectStepper projectStepper = (ComplexProjectStepper)Driver.getDatabase().getProjectStepper();
			OEFrame stepperFrame = (OEFrame) projectStepper.getFrame();
//			stepperFrame.focus(projectStepper, "mainprojectstepper");
//			ThreadSupport.sleep(clearanceManager.getAutoPauseTime()*1000);
			
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
		clearanceManager.setStepDescription("Next step is to quit the second grader session and also this test/demo session, cleaning generated files.");
		Driver.getDatabase().getProjectStepper().setExitOnQuit(true);

		waitForUserOrSleep();
		if (clearanceManager.isAutoPerformStep()) {
			((OverviewProjectStepper) Driver.getDatabase().getProjectStepper()).quit();
			Driver.getSettingsModel().cleanSlate();

		}
		System.exit(0);
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
	
	static void waitForUserOrLongSleep() {
		if (clearanceManager.isAutoProceed())
			ThreadSupport.sleep(clearanceManager.getAutoPauseTime()*1000*20);
		else
			clearanceManager.waitForClearance();
	}

	@Override
	public void run() {
		Driver.drive(0,  CLEARANCE_HEIGHT);
		OEFrame frame = (OEFrame) Driver.getDatabase().getProjectStepper().getFrame();
//		frame.setLocation(0, CLEARANCE_HEIGHT-100);
		frame.setLocation(CLEARANCE_WIDTH, 0);

//		Driver.getSettingsFrame().setLocation(C);
	}

}
