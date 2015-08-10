package demoAndTest.basic;

import grader.assignment.GradingFeature;
import grader.navigation.NavigationKind;
import grader.navigation.filter.GradingStatus;
import grader.project.source.ATACommentsExtractor;
import grader.steppers.ComplexProjectStepper;
import grader.steppers.OverviewProjectStepper;
import gradingTools.Driver;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import demoAndTest.ADemoAndTestingClearanceManager;
import demoAndTest.DemoAndTestingClearanceManager;
import demoAndTest.GraderDemoerAndTester;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;
import tools.DirectoryUtils;
import util.misc.AClearanceManager;
import util.misc.ClearanceManager;
import util.misc.Common;
import util.misc.ThreadSupport;
import util.trace.Tracer;
/*
 * This is different from Driver in that it has a particular assignment hardwired.
 * This is assignment 3 from Jacob's 110. It would have been better to do Assignment 1
 * for all of the demo cases (C, Java, distriuted) in retrospect.
 * The file and test directories are also hardwired
 * Problem is that these entries are 
 * How to override them, unless from configuration file
 */
public class AJavaPalindromeBasedGraderDemoerAndTester implements  GraderDemoerAndTester{
	 String[] args ;
	public static final  String TEST_DIR = "Test Data/Test 110 F13 Assignments";
	public static final  String CORRECT_DIR = "Test Data/Correct 110 F13 Results";
	public static final String COURSE_NO = "Comp110";
	public static final int AUTO_PAUSE_SECONDS = 2; // seconds
	public static final int MEDIUM_PAUSE_SECONDS = 5;
	public static final int LONG_PAUSE_SECONDS = 30;
	
	protected String directory;
	
	protected boolean  generatingCorrectDir = false;
	
	protected boolean autoProceed = false;
//	 boolean  generatingCorrectDir = true;

	
	

	 protected DemoAndTestingClearanceManager clearanceManager = new ADemoAndTestingClearanceManager();
//	 boolean autoProceed;
//	 long autoProceedPauseTime = 4000;
	 
	public AJavaPalindromeBasedGraderDemoerAndTester(String[] anArgs) {
		args = anArgs;
		clearanceManager.setAutoPauseTime(getAutoPauseSeconds());
	}
	protected int getAutoPauseSeconds() {
		return AUTO_PAUSE_SECONDS;
	}
	
	protected int getMediumPauseSeconds() {
		return MEDIUM_PAUSE_SECONDS;
	}
	
	protected int getLongAutoPauseSeconds() {
		return LONG_PAUSE_SECONDS;
	}
	/* (non-Javadoc)
	 * @see gradingTools.GraderDemoerAndTester#isAutoProceed()
	 */
	@Override
	public  boolean isAutoProceed() {
		return autoProceed;
	}
	protected String testDir() {
		return TEST_DIR;
	}
	
	protected String correctDir() {
		return CORRECT_DIR;
	}
	
	protected String assignmentNo() {
		return "Assignment3";
	}
	
	protected String courseNo() {
		return COURSE_NO;
	}

	/* (non-Javadoc)
	 * @see gradingTools.GraderDemoerAndTester#setAutoProceed(boolean)
	 */
	@Override
	public  void setAutoProceed(boolean autoProceed) {
		this.autoProceed = autoProceed;
	}
	/* (non-Javadoc)
	 * @see gradingTools.GraderDemoerAndTester#isGeneratingCorrectDir()
	 */
	@Override
	public  boolean isGeneratingCorrectDir() {
		return generatingCorrectDir;
	}

	/* (non-Javadoc)
	 * @see gradingTools.GraderDemoerAndTester#setGeneratingCorrectDir(boolean)
	 */
	@Override
	public  void setGeneratingCorrectDir(boolean generatingCorrectDir) {
		this.generatingCorrectDir = generatingCorrectDir;
	}
	
	/* (non-Javadoc)
	 * @see gradingTools.GraderDemoerAndTester#demoAndTest()
	 */
	@Override
	public void demoAndTest() {
		ObjectEditor.setDefaultAttribute(AttributeNames.SHOW_SYSTEM_MENUS, false);
		startFirstSession();
		doSteps();
	}
	

	public static  void main (String[] anArgs) {
//		ObjectEditor.setDefaultAttribute(AttributeNames.SHOW_SYSTEM_MENUS, false);
		GraderDemoerAndTester demoerAndTester = new AJavaPalindromeBasedGraderDemoerAndTester(anArgs);
//		Tracer.showInfo(true);
//		Tracer.setKeywordPrintStatus(DirectoryUtils.class, true);
//		args = anArgs;
//		Tracer.info(this, "test");
//		Thread mainThread = new Thread(new Tester());
//		mainThread.start();
//		OEFrame clearanceFrame = ObjectEditor.edit(clearanceManager);
//		clearanceFrame.setSize(420, 260);
//		clearanceFrame.setLocation(0, 0);
		demoerAndTester.demoAndTest();
//		demoerAndTester.startFirstSession();
//
//		demoerAndTester.doSteps();
//		waitForUserOrSleep();
//		Driver.getSettingsModel().begin();
	}
	
	 final int CLEARANCE_WIDTH = 420;
	 final int CLEARANCE_HEIGHT = 260;

	
	/* (non-Javadoc)
	 * @see gradingTools.GraderDemoerAndTester#startFirstSession()
	 */
	@Override
	public void startFirstSession() {
//		Thread mainThread = new Thread(new APalindromeBasedDemoerAndTester());
		Thread mainThread = new Thread(this);

		mainThread.start();
		OEFrame clearanceFrame = ObjectEditor.edit(clearanceManager);
		clearanceFrame.setSize(CLEARANCE_WIDTH, CLEARANCE_HEIGHT);
		clearanceFrame.setLocation(0, 0);
	}
	
	/* (non-Javadoc)
	 * @see gradingTools.GraderDemoerAndTester#startSecondSession()
	 */
	@Override
	public void startSecondSession() {
//		Thread mainThread = new Thread(new APalindromeBasedDemoerAndTester());
		Thread mainThread = new Thread(this);

		mainThread.start();
	}
	
	 void initializeDirectoryAndAutoProceed() {
		if (generatingCorrectDir)
			directory = CORRECT_DIR;
		else
			directory = TEST_DIR;
		clearanceManager.setAutoProceed(autoProceed);
	}
	
	public  void doSteps() {
		initializeDirectoryAndAutoProceed();
//		if (generatingCorrectDir)
//			directory = CORRECT_DIR;
//		else
//			directory = TEST_DIR;
//		clearanceManager.setAutoProceed(autoProceed);
		waitForStepper1();
		initializeAndChangeProblem();
		doBegin1();
		waitForNavigator1();
		changeOverallNotes();
		doNext1();
		doSelect6();
		changeManualScore6();
		changeManualNotes6();
		doValidate5_pass1();
		doValidate34_pass1();
//		explainSourceAndcommentOnCode();
		showSourceChecks();
		showSource();
		commentOnCode();
//		changeOverallScore();
//		changeOverallNotes2();
		showFeedback();
		showMain();
		doNext2();
		doValidate34_pass1();

//		openSource();
		showProblemHistory();
		showStudentHistory();
		showMain();
		syncSource();
//		cleanUpOnyen();
		quit1();
//		System.exit(0);
		secondSession();
		waitForStepper2();
		changeToNotFullyGraded();
		doBegin1();
		waitForNavigator2();
		doValidate5_pass2();
//		changeOverallNotes3();
		if (!generatingCorrectDir) {
                    checkWithCorrectResults();
                }
		quit2();
		
	}
	public  void waitForStepper1() {
		clearanceManager.setStepDescription("Wait until the setter UI comes up and then press proceed to start or advance to next step. \n\nIf auto perform step is set, then press proceed to perform subsequent steps; otherwise use the UI to perform the next described step. \n\nYou can change this checkbox.");
		waitForUserOrSleep();
	}
	
	public  void waitForStepper2() {
		clearanceManager.setStepDescription("As in the first round, wait until the setter UI comes up before proceeding.");
		waitForUserOrSleep();
	}
	
	public  void waitForNavigator1() {
		clearanceManager.setStepDescription("Look at the console for output of autograded steps and missing submission folders. \n\nNow wait until the navigator UI comes up and then press proceed to go to first navigation step.");
		waitForUserOrLongSleep();
	}
	
	public  void waitForNavigator2() {
		clearanceManager.setStepDescription("Now wait until the navigator UI comes up and then press proceed to go to first navigation step in the second round. There should be no autograding this time.");
		waitForUserOrMediumSleep();
	}
	
	public void cleanUpOnyen() {
		clearanceManager.setStepDescription("Now proceed to clean up acorrect so that it is regraded");
		if (clearanceManager.isAutoPerformStep()) {
			Driver.getSettingsModel().cleanSlate("acorrect");

		
		}
		

	}
	
	public  void initializeAndChangeProblem() {
            while(Driver.getSettingsModel() == null) {
                ThreadSupport.sleep(100);
            }
                
		Driver.getSettingsModel().getModuleProblemSelector().getModule().setValue(courseNo());

		Driver.getSettingsModel().getModuleProblemSelector().getProblem().setValue("Assignment1");
//		Driver.getSettingsModel().getFileBrowsing().getDownloadFolder().setText("Test Data/Test 110 F13 Assignments/Assignment1");
		Driver.getSettingsModel().getFileBrowsing().getDownloadFolder().setText(directory + "/Assignment1");


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


//		Driver.getSettingsModel().getModuleProblemSelector().getProblem().setValue("Assignment3");
		Driver.getSettingsModel().getModuleProblemSelector().getProblem().setValue(assignmentNo());

		Driver.getSettingsModel().getOnyens().setDisplayedEndingOnyen("nosub");
		Driver.getSettingsModel().getOnyens().setDisplayedStartingOnyen("acorrect");

		Driver.getSettingsModel().cleanSlate();
		}

	}
	public  void changeToNotFullyGraded() {
		clearanceManager.setStepDescription("Next step is to automatically change navigation filter from ALL to NOT_FULLY_GRADED so that we go to the next student who needs attentin.");
		waitForUserOrSleep();
		if (clearanceManager.isAutoPerformStep()) {
			Driver.getSettingsModel().getNavigationSetter().getNavigationFilterSetter().setParameter(GradingStatus.NOT_FULLY_GRADED);

		
		}

	}
	public  void doBegin1() {
		clearanceManager.setStepDescription("Next step is to begin navigation. \n\nThe settings window will remain on the screen while the auto grading phase is being performed and then the navigator window will appear.");
		waitForUserOrSleep();
		if (clearanceManager.isAutoPerformStep())
		Driver.getSettingsModel().begin();
//		ThreadSupport.sleep(4000);
	}
	
	public  void doBegin2() {
		clearanceManager.setStepDescription("Next step is to begin  the second round of navigation. ");
		waitForUserOrSleep();
		if (clearanceManager.isAutoPerformStep())
		Driver.getSettingsModel().begin();
//		ThreadSupport.sleep(4000);
	}
	
	public  void changeOverallNotes() {
		clearanceManager.setStepDescription("Next step is to change the overall notes to congratulate the student. \n\nIts color should change to indicate the comment presence.");
//		Driver.getDatabase().getProjectStepper().setOverallNotes("");
		waitForUserOrSleep();
		if (clearanceManager.isAutoPerformStep())
		Driver.getDatabase().getProjectStepper().setOverallNotes("All correct!");
	}
	public  void doNext1() {
		clearanceManager.setStepDescription("Next step is to navigate to next student. \n\nWait until the UI is refreshed before proceeding. \n\nThere should be four pink items (3, 4, 8 and 9) for non-full points indicating attention.");
		waitForUserOrSleep();
		if (clearanceManager.isAutoPerformStep())
		Driver.getDatabase().getProjectStepper().next();
	}
	public  void doNext2() {
		clearanceManager.setStepDescription("Next step is to navigate to next student. \n\nAgain wait until the UI is refreshed before proceeding. ");
		waitForUserOrSleep();
		if (clearanceManager.isAutoPerformStep())
		Driver.getDatabase().getProjectStepper().next();
	}
	public  void doSelect6() {
		List<GradingFeature> gradingFeatures = Driver.getDatabase().getGradingFeatures();
		GradingFeature gradingFeature = gradingFeatures.get(8);
//		GradingFeature prevGradingFeature = gradingFeatures.get(5);
//		gradingFeature.setManualNotes("");
//		prevGradingFeature.setManualNotes("");	
//		Driver.getDatabase().getProjectStepper().setOverallNotes("");
		clearanceManager.setStepDescription("Next step is check the select box in item 9 to select it.\n\nThe auto notes box should show the reason for point deduction for the selected item. \n\nThe transcript box should show the output for this feature. \n\nThe manual notes will show the previous manual notes for this item, which should be empty.");
		waitForUserOrSleep();
//		List<GradingFeature> gradingFeatures = Driver.getDatabase().getGradingFeatures();
//		GradingFeature gradingFeature = gradingFeatures.get(6);
		if (clearanceManager.isAutoPerformStep())
		gradingFeature.setSelected(true);
	}
	public  void changeManualScore6() {
		clearanceManager.setStepDescription("Next step is to manually override the auto computed score. \n\nThe item should remain pink to indicate no reason has been given for change.");
		waitForUserOrSleep();
		List<GradingFeature> gradingFeatures = Driver.getDatabase().getGradingFeatures();
		GradingFeature gradingFeature = gradingFeatures.get(8);
		if (clearanceManager.isAutoPerformStep())
		gradingFeature.setScore(gradingFeature.getScore() * 1.1);
	}
	public  void changeManualNotes6() {
		clearanceManager.setStepDescription("Next step is to add manual notes for selected item. \n\nThe manual notes and item should become green to indicate presence of non empty comment."); 
		waitForUserOrSleep();
//		List<GradingFeature> gradingFeatures = Driver.getDatabase().getGradingFeatures();
//		GradingFeature gradingFeature = gradingFeatures.get(6);
		if (clearanceManager.isAutoPerformStep())
		Driver.getDatabase().getProjectStepper().setManualNotes("The transcript  shows that indicated output syntax was misunderstood. Gave partial credit.");
//		gradingFeature.setManualNotes("The source code shows that indicated output syntax was misunderstood. Gave partial credit.");
	}
	public  void doValidate5_pass1() {
		clearanceManager.setStepDescription("Next step is to check the validate box in item 8 to indicate that the automantically computed non full score is correct. \n\nThe item  color should change. \n\nIn addition, the item should get selected. \n\nThe auto notes box should indicate the reason for deduction. The manual notes box should contain notes indicating validation and change color to show presence of notes for selected item."); 
		waitForUserOrSleep();
		List<GradingFeature> gradingFeatures = Driver.getDatabase().getGradingFeatures();
		GradingFeature gradingFeature = gradingFeatures.get(7);
		if (clearanceManager.isAutoPerformStep())

		gradingFeature.setValidate(true);
	}
	public  void doValidate34_pass1() {
		clearanceManager.setStepDescription("Next step is to valdiate 3 and 4"); 
		waitForUserOrSleep();
		List<GradingFeature> gradingFeatures = Driver.getDatabase().getGradingFeatures();
		GradingFeature gradingFeature7 = gradingFeatures.get(7);
		GradingFeature gradingFeature2 = gradingFeatures.get(2);
		GradingFeature gradingFetaure3 = gradingFeatures.get(3);

		if (clearanceManager.isAutoPerformStep()) {

		gradingFeature7.setValidate(true);
		gradingFeature2.setValidate(true);
		gradingFetaure3.setValidate(true);
		}
	}
	public  void doValidate5_pass2() {
		clearanceManager.setStepDescription("Next steps is to validate the pink items.");
		waitForUserOrSleep();
		List<GradingFeature> gradingFeatures = Driver.getDatabase().getGradingFeatures();
		GradingFeature gradingFeature5 = gradingFeatures.get(5);
		GradingFeature gradingFeature2 = gradingFeatures.get(2);
		GradingFeature gradingFetaure3 = gradingFeatures.get(3);
//		if (clearanceManager.isAutoPerformStep())
//
//		gradingFeature.setValidate(true);
		

		if (clearanceManager.isAutoPerformStep()) {

		gradingFeature5.setValidate(true);
		gradingFeature2.setValidate(true);
		gradingFetaure3.setValidate(true);
		}
	}
	
	
	
	public boolean navigatorReady() {
		ComplexProjectStepper projectStepper = (ComplexProjectStepper)Driver.getDatabase().getProjectStepper();
		if (projectStepper == null) return false;
		OEFrame stepperFrame = (OEFrame) projectStepper.getFrame();
		if (stepperFrame == null) return false;
		return true;
	}
	public  void showSource() {
		clearanceManager.setStepDescription("Next step is to go the source tab to view all of the source code, which may be non distriuted Java or C, or distributed Java, depending on which directory this program is bound to.");
		waitForUserOrSleep();		
		if (clearanceManager.isAutoPerformStep()) {
			ComplexProjectStepper projectStepper = (ComplexProjectStepper)Driver.getDatabase().getProjectStepper();
			OEFrame stepperFrame = (OEFrame) projectStepper.getFrame();
			if (stepperFrame == null) {
				System.err.println("Oops, proceed occurred before auto grading completed");
			}
			stepperFrame.focus(projectStepper, "source");			
		}
	}
	
	public  void showSourceChecks() {
		clearanceManager.setStepDescription("Next step is to go the source checks tab to view source code analysis.");
		waitForUserOrSleep();		
		if (clearanceManager.isAutoPerformStep()) {
			ComplexProjectStepper projectStepper = (ComplexProjectStepper)Driver.getDatabase().getProjectStepper();
			OEFrame stepperFrame = (OEFrame) projectStepper.getFrame();
			if (stepperFrame == null) {
				System.err.println("Oops, proceed occurred before auto grading completed");
			}
			stepperFrame.focus(projectStepper, "sourceChecks");			
		}
	}
	
	public  void showMain() {
		clearanceManager.setStepDescription("Next step is to go the main tab.");
		waitForUserOrSleep();		
		if (clearanceManager.isAutoPerformStep()) {
			ComplexProjectStepper projectStepper = (ComplexProjectStepper)Driver.getDatabase().getProjectStepper();
			OEFrame stepperFrame = (OEFrame) projectStepper.getFrame();
			stepperFrame.focus(projectStepper, "mainprojectstepper");					
		}
	}
	public  void showProblemHistory() {
		clearanceManager.setStepDescription("Next step is to go the problem history tab to see past source comments.");
		waitForUserOrSleep();		
		if (clearanceManager.isAutoPerformStep()) {
			ComplexProjectStepper projectStepper = (ComplexProjectStepper)Driver.getDatabase().getProjectStepper();
			OEFrame stepperFrame = (OEFrame) projectStepper.getFrame();
			stepperFrame.focus(projectStepper, "problemHistory");			
		}
	}
	public  void showStudentHistory() {
		clearanceManager.setStepDescription("Next step is to go the source history tab to see past student performance.");
		waitForUserOrSleep();		
		if (clearanceManager.isAutoPerformStep()) {
			ComplexProjectStepper projectStepper = (ComplexProjectStepper)Driver.getDatabase().getProjectStepper();
			OEFrame stepperFrame = (OEFrame) projectStepper.getFrame();
			stepperFrame.focus(projectStepper, "studentHistory");			
		}
	}
	public  void openSource() {
		clearanceManager.setStepDescription("Next step is to open source in registered editor.");
		waitForUserOrSleep();		
		if (clearanceManager.isAutoPerformStep()) {
			ComplexProjectStepper projectStepper = (ComplexProjectStepper)Driver.getDatabase().getProjectStepper();
//			OEFrame stepperFrame = (OEFrame) projectStepper.getFrame();
//			stepperFrame.focus(projectStepper, "source");	
			projectStepper.openSource();
		}
	}
	public  void syncSource() {
		clearanceManager.setStepDescription("Next step is to sync changes in external editor.");
		waitForUserOrSleep();		
		if (clearanceManager.isAutoPerformStep()) {
			ComplexProjectStepper projectStepper = (ComplexProjectStepper)Driver.getDatabase().getProjectStepper();
//			OEFrame stepperFrame = (OEFrame) projectStepper.getFrame();
//			stepperFrame.focus(projectStepper, "source");	
			projectStepper.sync();
		}
	}
	public  void commentOnCode() {
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
	
	public  void showFeedback() {
		clearanceManager.setStepDescription("Next step is to go to the feedback tab and confirm that the manual and auto grading information about this student is in the feedback.\n\n");
		waitForUserOrSleep();		
		if (clearanceManager.isAutoPerformStep()) {
			ComplexProjectStepper projectStepper = (ComplexProjectStepper)Driver.getDatabase().getProjectStepper();
			OEFrame stepperFrame = (OEFrame) projectStepper.getFrame();
			stepperFrame.focus(projectStepper, "feedback");			
		}
	}
	
	public  void explainSourceAndcommentOnCode() {
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
	public  void changeOverallScore() {
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
	
	public  void changeOverallNotes2() {
		clearanceManager.setStepDescription("Next step is to change the overall notes to explain the reason for increase. ");
//		Driver.getDatabase().getProjectStepper().setOverallNotes("");
		waitForUserOrSleep();
		if (clearanceManager.isAutoPerformStep()) {
		Driver.getDatabase().getProjectStepper().setOverallNotes(Driver.getDatabase().getProjectStepper().getOverallNotes() + "\nExtra credit for style.");
		}
	}
	public  void quit1() {
		clearanceManager.setStepDescription("Next step is to quit the first grader session.");
		Driver.getDatabase().getProjectStepper().setExitOnQuit(false);

		waitForUserOrSleep();
		if (clearanceManager.isAutoPerformStep()) {
//			cleanUpOnyen();

			
			((OverviewProjectStepper) Driver.getDatabase().getProjectStepper()).quit();
		}
//		System.exit(0);
	}
	public  void quit2() {
		clearanceManager.setStepDescription("Next step is to quit the second grader session and also this test/demo session, cleaning generated files.");
		Driver.getDatabase().getProjectStepper().setExitOnQuit(false);

		waitForUserOrSleep();
		if (clearanceManager.isAutoPerformStep()) {
		

			((OverviewProjectStepper) Driver.getDatabase().getProjectStepper()).quit();
			if (!generatingCorrectDir)
			Driver.getSettingsModel().cleanSlate();

		}
		System.exit(0);
	}
	
	/* (non-Javadoc)
	 * @see gradingTools.GraderDemoerAndTester#secondSession()
	 */
	@Override
	public  void secondSession() {
		clearanceManager.setStepDescription("Next step is to start new session. \n\nThis time we will not clean previous slate; continuing from saved data about the first session.");
		waitForUserOrSleep();
		startSecondSession();
		
	}
	public  void changeOverallNotes3() {
		clearanceManager.setStepDescription("Next step is to change the overall notes to further congratulate the student");
		
		waitForUserOrSleep();
		if (clearanceManager.isAutoPerformStep()) {
		Driver.getDatabase().getProjectStepper().setOverallNotes(Driver.getDatabase().getProjectStepper().getOverallNotes() + "\nExcellent - best performace!");
		}
	}
	
	public  void checkWithCorrectResults() {
//		String assignmentName = Common.absoluteNameToLocalName(directory);
//		File correctDir = new File ("Test Data/Correct 110 F13 Results/Assignment3");
		File correctDir = new File (correctDir() + "/" + assignmentNo());

//		File testDir = new File ("Test Data/Test 110 F13 Assignments/Assignment3");
		File testDir = new File (testDir() + "/" + assignmentNo());

		String[] ignoreSuffixesArray = {".zip", ".ini", ".json", "Submission attachment(s)"};
//		String[] ignoreSuffixesArray = {".zip", ".ini", ".json"};

		List<String> ignoreSuffixesList = Arrays.asList(ignoreSuffixesArray);
		System.out.println(DirectoryUtils.compare (correctDir, testDir, ignoreSuffixesList));
	}
	
//	public  boolean isAutoProceed() {
//		return autoProceed;
//	}
//
//	public  void setAutoProceed(boolean autoProceed) {
//		Tester.autoProceed = autoProceed;
//	}
//
//	public  long getAutoProceedPauseTime() {
//		return autoProceedPauseTime;
//	}
//
//	public  void setAutoProceedPauseTime(long autoProceedPauseTime) {
//		Tester.autoProceedPauseTime = autoProceedPauseTime;
//	}

	protected void waitForUserOrSleep() {
		if (clearanceManager.isAutoProceed())
			ThreadSupport.sleep(clearanceManager.getAutoPauseTime()*1000);
		else
			clearanceManager.waitForClearance();
	}
	
	protected void waitForUserOrLongSleep() {
		if (clearanceManager.isAutoProceed()) {
			ThreadSupport.sleep(clearanceManager.getAutoPauseTime()*1000*getLongAutoPauseSeconds());
			
		} else
			clearanceManager.waitForClearance();
		if (! navigatorReady()) {
			System.out.println("Premature proceed to navigator, redoing the wait.");
			waitForUserOrLongSleep();
		}
	}
	
	protected void waitForUserOrMediumSleep() {
		if (clearanceManager.isAutoProceed())
			ThreadSupport.sleep(clearanceManager.getAutoPauseTime()*1000*getMediumPauseSeconds());
		else
			clearanceManager.waitForClearance();
	}

	@Override
	public void run() {
		Driver.drive(args, 0,  CLEARANCE_HEIGHT);
		OEFrame frame = (OEFrame) Driver.getDatabase().getProjectStepper().getFrame();
//		frame.setLocation(0, CLEARANCE_HEIGHT-100);
		frame.setLocation(CLEARANCE_WIDTH, 0);

//		Driver.getSettingsFrame().setLocation(C);
	}

}
