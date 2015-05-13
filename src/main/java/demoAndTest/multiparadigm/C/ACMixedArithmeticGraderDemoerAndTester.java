package demoAndTest.multiparadigm.C;

import grader.assignment.GradingFeature;
import grader.navigation.NavigationKind;
import grader.navigation.filter.GradingStatus;
import grader.project.graded.ComplexProjectStepper;
import grader.project.graded.OverviewProjectStepper;
import grader.project.source.ATACommentsExtractor;
import gradingTools.Driver;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import demoAndTest.GraderDemoerAndTester;
import demoAndTest.basic.AJavaPalindromeBasedGraderDemoerAndTester;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;
//import bus.uigen.pipe.DemoerAndTester;
import tools.DirectoryUtils;
import util.misc.AClearanceManager;
import util.misc.ClearanceManager;
import util.misc.ThreadSupport;
import util.trace.Tracer;
/*
 * This is different from DemoerAndTester as it uses Assignment 1 rather than Assignment 3
 * The steps and grading criteria are hardwired
 * I suppose the test and correct directories could be made as parameters
 */
public class ACMixedArithmeticGraderDemoerAndTester extends AJavaPalindromeBasedGraderDemoerAndTester {
	
	//	 String[] args ;
	public static final  String TEST_DIR = "Test Data/Test C";
	public static final  String CORRECT_DIR = "Test Data/Correct C";
	public static final String COURSE_NO = "Comp411";


	public ACMixedArithmeticGraderDemoerAndTester(String[] anArgs) {
		super(anArgs);
	}
	@Override
	protected String testDir() {
		return TEST_DIR;
	}
	@Override
	protected String correctDir() {
		return CORRECT_DIR;
	}
	@Override
	protected String courseNo() {
		return "Comp411";
	}
	@Override
	protected String assignmentNo() {
		return "Assignment1";
	}

	 protected void initializeDirectoryAndAutoProceed() {
		if (generatingCorrectDir)
			directory = correctDir();
		else
			directory = testDir();
		clearanceManager.setAutoProceed(autoProceed);
	}
	
	public  void doSteps() {
	
		initializeDirectoryAndAutoProceed();
		waitForStepper1();
		initializeProblem();
		doBegin1();
		waitForNavigator1();
		doFullScoreClearMessages();
		

		showSource();
		showMain();
		ignoreUngraded();

		doNext();
		doSelectClearMessages();
		changeManualScoreClearMessages();
		changeManualNotesClearMessages();

		doNext();
		
		if (!generatingCorrectDir)
		checkWithCorrectResults();
		quit2();
		
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
	
	public  void initializeProblem() {
		Driver.getSettingsModel().
			getModuleProblemSelector().
				getModule().
					setValue(courseNo());

		Driver.getSettingsModel().getModuleProblemSelector().getProblem().setValue(assignmentNo());
//		Driver.getSettingsModel().getFileBrowsing().getDownloadFolder().setText("Test Data/Test 110 F13 Assignments/Assignment1");
		Driver.getSettingsModel().getFileBrowsing().getDownloadFolder().setText(directory + "/" + assignmentNo());


		Driver.getSettingsModel().getNavigationSetter().getNavigationFilterSetter().setParameter(GradingStatus.ALL);

		if (clearanceManager.isAutoPerformStep()) {
		Driver.getSettingsModel().getNavigationSetter().setNavigationKind(NavigationKind.HYBRID);
		Driver.getSettingsModel().getNavigationSetter().getNavigationFilterSetter().setParameter(GradingStatus.ALL);


		Driver.getSettingsModel().getOnyens().setDisplayedEndingOnyen("jbartel");
		Driver.getSettingsModel().getOnyens().setDisplayedStartingOnyen("acorrect");

		Driver.getSettingsModel().cleanSlate();
		}

	}
	
	public  void doBegin1() {
		clearanceManager.setStepDescription("Next step is to begin navigation. \n\nThe settings window will remain on the screen while the auto grading phase is being performed and then the navigator window will appear.");
		waitForUserOrSleep();
		if (clearanceManager.isAutoPerformStep())
		Driver.getSettingsModel().begin();
//		ThreadSupport.sleep(4000);
	}
	public  void doSelectClearMessages() {
		List<GradingFeature> gradingFeatures = Driver.getDatabase().getGradingFeatures();
//		GradingFeature gradingFeature = gradingFeatures.get(6);
		GradingFeature gradingFeature = gradingFeatures.get(clearMessagesItem());

//		GradingFeature prevGradingFeature = gradingFeatures.get(5);
//		gradingFeature.setManualNotes("");
//		prevGradingFeature.setManualNotes("");	
//		Driver.getDatabase().getProjectStepper().setOverallNotes("");
		clearanceManager.setStepDescription("Next step is check the select box in  clear messsages item (#7) to select it. The manual notes box gets tied to this item and becomes red also to indicate the lack of notes for a score of 0");
		waitForUserOrSleep();
//		List<GradingFeature> gradingFeatures = Driver.getDatabase().getGradingFeatures();
//		GradingFeature gradingFeature = gradingFeatures.get(6);
		if (clearanceManager.isAutoPerformStep())
		gradingFeature.setSelected(true);
	}
	protected int clearMessagesItem() {
		return 6;
	}
	public  void doFullScoreClearMessages() {
		clearanceManager.setStepDescription("Next step is to check the full credit box in clear messages item (#7) to give full score. \n\nThe item should become uncolored to indicate this is normal  . \n\nIn addition, the item should get selected in case we want to comment on it."); 
		waitForUserOrSleep();
		List<GradingFeature> gradingFeatures = Driver.getDatabase().getGradingFeatures();
//		GradingFeature gradingFeature = gradingFeatures.get(6);
		GradingFeature gradingFeature = gradingFeatures.get(clearMessagesItem());

		if (clearanceManager.isAutoPerformStep())

		gradingFeature.setFullCredit(true);
	}
	
	public  void changeManualScoreClearMessages() {
		clearanceManager.setStepDescription("Next step is to enter partial score for the red manual grading item. \n\nThe item and notes should become pink to indicate no reason has been given for not giving full score, which can be given by simply pressing the graded button.");
		waitForUserOrSleep();
		List<GradingFeature> gradingFeatures = Driver.getDatabase().getGradingFeatures();
//		GradingFeature gradingFeature = gradingFeatures.get(6);
		GradingFeature gradingFeature = gradingFeatures.get(clearMessagesItem());

		if (clearanceManager.isAutoPerformStep())
		gradingFeature.setScore(7);
	}
	public  void changeManualNotesClearMessages() {
		clearanceManager.setStepDescription("Next step is to add manual notes for selected item. \n\nThe manual notes and item should become green to indicate presence of non empty comment."); 
		waitForUserOrSleep();
//		List<GradingFeature> gradingFeatures = Driver.getDatabase().getGradingFeatures();
//		GradingFeature gradingFeature = gradingFeatures.get(6);
		if (clearanceManager.isAutoPerformStep())
		Driver.getDatabase().getProjectStepper().setManualNotes("Nice  messages, could have used message box.");
//		gradingFeature.setManualNotes("The source code shows that indicated output syntax was misunderstood. Gave partial credit.");
	}

	public  void ignoreUngraded() {
		clearanceManager.setStepDescription("Next step is to ignore the remaining pink and red items in this and other records by unchecking the StopIfNotDone box");
		waitForUserOrSleep();
		if (clearanceManager.isAutoPerformStep())
		((OverviewProjectStepper) Driver.getDatabase().getProjectStepper()).setProceedWhenDone(false);
	}
	
	
	public  void doNext() {
		clearanceManager.setStepDescription("Next step is to navigate to next student. \n\nWait until the UI is refreshed before proceeding. \n\nThere should be again one pink and red items (5 and 7) for non-full points indicating attention.");
		waitForUserOrSleep();
		if (clearanceManager.isAutoPerformStep())
		Driver.getDatabase().getProjectStepper().next();
	}
	
	

	
	
	public  void quit2() {
		clearanceManager.setStepDescription("Next step is to quit the  grader session and also this test/demo session, cleaning generated files.");
		Driver.getDatabase().getProjectStepper().setExitOnQuit(false);

		waitForUserOrSleep();
		if (clearanceManager.isAutoPerformStep()) {
		

			((OverviewProjectStepper) Driver.getDatabase().getProjectStepper()).quit();
			if (!generatingCorrectDir)
			Driver.getSettingsModel().cleanSlate();

		}
		System.exit(0);
	}
	
	
	

	public static  void main (String[] anArgs) {
//		ObjectEditor.setDefaultAttribute(AttributeNames.SHOW_SYSTEM_MENUS, false);
//		Tracer.showInfo(true);
//		Tracer.setKeywordPrintStatus(DirectoryUtils.class, true);
		GraderDemoerAndTester aDemoerAndTester = new ACMixedArithmeticGraderDemoerAndTester(anArgs);
//		args = anArgs;
		Tracer.info(ACMixedArithmeticGraderDemoerAndTester.class, "test");
		aDemoerAndTester.demoAndTest();
		
//		aDemoerAndTester.demoAndTest();
//
//		startFirstSession();
//
//		doSteps();

	}

	

}
