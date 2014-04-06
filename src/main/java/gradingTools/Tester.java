package gradingTools;

import grader.assignment.GradingFeature;

import java.util.List;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;
import util.misc.AClearanceManager;
import util.misc.ClearanceManager;
import util.misc.ThreadSupport;

public class Tester implements Runnable{
	static String[] args ;
	static TestingClearanceManager clearanceManager = new ATestingClearanceManager();
	static boolean autoProceed;
	static long autoProceedPauseTime = 4000;
	public static void main (String[] anArgs) {
		ObjectEditor.setDefaultAttribute(AttributeNames.SHOW_SYSTEM_MENUS, false);
		args = anArgs;
		Thread mainThread = new Thread(new Tester());
		mainThread.start();
		OEFrame clearanceFrame = ObjectEditor.edit(clearanceManager);
		clearanceFrame.setSize(420, 220);
		doSteps();
//		waitForUserOrSleep();
//		Driver.getSettingsModel().begin();
	}
	
	public static void doSteps() {
		waitForStepper();
		changeProblem();
		doBegin();
		waitForStepper();
		changeOverallNotes();
		doNext();
		doSelect();
		changeManualScore();
		changeManualNotes();
		doValidate();
	}
	public static void waitForStepper() {
		clearanceManager.setStepDescription("Wait until setter UI comes up and then press proceed to start or advance to next step. You can use the UI controls directly but you may confuse the canned program.");
		waitForUserOrSleep();
	}
	
	public static void waitForNavigator() {
		clearanceManager.setStepDescription("Look at the console for missing submission folders. Now wait  until navigator UI comes up and then press proceed to go to first navigation step.");
		waitForUserOrSleep();
	}
	
	public static void changeProblem() {
		Driver.getSettingsModel().getModuleProblemSelector().getProblem().setValue("Assignment1");
		clearanceManager.setStepDescription("Will automatically change problem from Assignment1 to Assignment3.");
		waitForUserOrSleep();
		Driver.getSettingsModel().getModuleProblemSelector().getProblem().setValue("Assignment3");

	}
	public static void doBegin() {
		clearanceManager.setStepDescription("Will begin navigation.");
		waitForUserOrSleep();
		Driver.getSettingsModel().begin();
		ThreadSupport.sleep(4000);
	}
	
	public static void changeOverallNotes() {
		clearanceManager.setStepDescription("Will change overall notes to congratulate student. Its color should change to indicate comment presence.");
		Driver.getDatabase().getProjectStepper().setOverallNotes("");
		waitForUserOrSleep();
		Driver.getDatabase().getProjectStepper().setOverallNotes("All correct! Excellent!");
	}
	public static void doNext() {
		clearanceManager.setStepDescription("Will go to next student. Wait until the UI is refreshed before next proceed. There should be two pink items (6 and 7) for non-full points indicating attention.");
		waitForUserOrSleep();
		Driver.getDatabase().getProjectStepper().next();
	}
	public static void doSelect() {
		List<GradingFeature> gradingFeatures = Driver.getDatabase().getGradingFeatures();
		GradingFeature gradingFeature = gradingFeatures.get(6);
		GradingFeature prevGradingFeature = gradingFeatures.get(5);
		gradingFeature.setManualNotes("");
		prevGradingFeature.setManualNotes("");		
		clearanceManager.setStepDescription("Will check the select box in item 6 to select it. The auto notes box should show the reason for point deduction for the selected item. The transcript box should show the output for this feature. The manual notes will show the previous manual notes for this item, which should be empty.");
		waitForUserOrSleep();
//		List<GradingFeature> gradingFeatures = Driver.getDatabase().getGradingFeatures();
//		GradingFeature gradingFeature = gradingFeatures.get(6);
		gradingFeature.setSelected(true);
	}
	public static void changeManualScore() {
		clearanceManager.setStepDescription("Will manually override the auto computed score. The item should remain pink to indicate no reason has been given for change.");
		waitForUserOrSleep();
		List<GradingFeature> gradingFeatures = Driver.getDatabase().getGradingFeatures();
		GradingFeature gradingFeature = gradingFeatures.get(6);
		gradingFeature.setScore(15);
	}
	public static void changeManualNotes() {
		clearanceManager.setStepDescription("Will add manual notes for selected item. The manual notes and item should become green to indicate prsence of non empty comment."); 
		waitForUserOrSleep();
//		List<GradingFeature> gradingFeatures = Driver.getDatabase().getGradingFeatures();
//		GradingFeature gradingFeature = gradingFeatures.get(6);
		Driver.getDatabase().getProjectStepper().setManualNotes("The transcript  shows that indicated output syntax was misunderstood. Gave partial credit.");
//		gradingFeature.setManualNotes("The source code shows that indicated output syntax was misunderstood. Gave partial credit.");
	}
	public static void doValidate() {
		clearanceManager.setStepDescription("Will check the validate box in item 6 to indicate that the automantically computed non full score is correct. The item  color should change. In addition, the item should get selected. The auto notes box should indicate the reason for deduction. The manual notes box should contain notes indicating validation and change color to show presence of notes for selected item."); 
		waitForUserOrSleep();
		List<GradingFeature> gradingFeatures = Driver.getDatabase().getGradingFeatures();
		GradingFeature gradingFeature = gradingFeatures.get(5);
		gradingFeature.setValidate(true);
	}
	public static boolean isAutoProceed() {
		return autoProceed;
	}

	public static void setAutoProceed(boolean autoProceed) {
		Tester.autoProceed = autoProceed;
	}

	public static long getAutoProceedPauseTime() {
		return autoProceedPauseTime;
	}

	public static void setAutoProceedPauseTime(long autoProceedPauseTime) {
		Tester.autoProceedPauseTime = autoProceedPauseTime;
	}

	static void waitForUserOrSleep() {
		if (autoProceed)
			ThreadSupport.sleep(autoProceedPauseTime);
		else
			clearanceManager.waitForClearance();
	}

	@Override
	public void run() {
		Driver.main(args);
	}

}
