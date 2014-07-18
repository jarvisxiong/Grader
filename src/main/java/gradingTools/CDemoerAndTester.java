package gradingTools;

import grader.assignment.GradingFeature;
import grader.navigation.NavigationKind;
import grader.navigation.filter.GradingStatus;
import grader.project.graded.ComplexProjectStepper;
import grader.project.graded.OverviewProjectStepper;
import grader.project.source.ATACommentsExtractor;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;
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
public class CDemoerAndTester extends DemoerAndTester {
//	static String[] args ;
	public final static String TEST_DIR = "Test Data/Test C";
	public final static String CORRECT_DIR = "Test Data/Correct C";
	

	

	public static void main (String[] anArgs) {
		ObjectEditor.setDefaultAttribute(AttributeNames.SHOW_SYSTEM_MENUS, false);
//		Tracer.showInfo(true);
//		Tracer.setKeywordPrintStatus(DirectoryUtils.class, true);
		args = anArgs;
		Tracer.info(CDemoerAndTester.class, "test");

		startFirstSession();

		doSteps();

	}
	


	

	
	static void initializeDirectoryAndAutoProceed() {
		if (generatingCorrectDir)
			directory = CORRECT_DIR;
		else
			directory = TEST_DIR;
		clearanceManager.setAutoProceed(autoProceed);
	}
	
	public static void doSteps() {
	
		initializeDirectoryAndAutoProceed();
		waitForStepper1();
		initializeProblem();
		doBegin1();
		waitForNavigator1();
		ignoreUngraded();
		showSource();
		showMain();
		doNext();
		doNext();
		
		if (!generatingCorrectDir)
		checkWithCorrectResults();
		quit2();
		
	}
	public static void checkWithCorrectResults() {
//		String assignmentName = Common.absoluteNameToLocalName(directory);
//		File correctDir = new File ("Test Data/Correct 110 F13 Results/Assignment3");
		File correctDir = new File (CORRECT_DIR + "/Assignment1");

//		File testDir = new File ("Test Data/Test 110 F13 Assignments/Assignment3");
		File testDir = new File (TEST_DIR + "/Assignment1");

		String[] ignoreSuffixesArray = {".zip", ".ini", ".json", "Submission attachment(s)"};
//		String[] ignoreSuffixesArray = {".zip", ".ini", ".json"};

		List<String> ignoreSuffixesList = Arrays.asList(ignoreSuffixesArray);
		System.out.println(DirectoryUtils.compare (correctDir, testDir, ignoreSuffixesList));
	}
	
	public static void initializeProblem() {
		Driver.getSettingsModel().getModuleProblemSelector().getModule().setValue("Comp411");

		Driver.getSettingsModel().getModuleProblemSelector().getProblem().setValue("Assignment1");
//		Driver.getSettingsModel().getFileBrowsing().getDownloadFolder().setText("Test Data/Test 110 F13 Assignments/Assignment1");
		Driver.getSettingsModel().getFileBrowsing().getDownloadFolder().setText(directory + "/Assignment1");


		Driver.getSettingsModel().getNavigationSetter().getNavigationFilterSetter().setParameter(GradingStatus.ALL);

		if (clearanceManager.isAutoPerformStep()) {
		Driver.getSettingsModel().getNavigationSetter().setNavigationKind(NavigationKind.HYBRID);
		Driver.getSettingsModel().getNavigationSetter().getNavigationFilterSetter().setParameter(GradingStatus.ALL);


		Driver.getSettingsModel().getOnyens().setDisplayedEndingOnyen("jbartel");
		Driver.getSettingsModel().getOnyens().setDisplayedStartingOnyen("acorrect");

		Driver.getSettingsModel().cleanSlate();
		}

	}
	
	public static void doBegin1() {
		clearanceManager.setStepDescription("Next step is to begin navigation. \n\nThe settings window will remain on the screen while the auto grading phase is being performed and then the navigator window will appear.");
		waitForUserOrSleep();
		if (clearanceManager.isAutoPerformStep())
		Driver.getSettingsModel().begin();
//		ThreadSupport.sleep(4000);
	}
	

	public static void ignoreUngraded() {
		clearanceManager.setStepDescription("Next step is to ignore the pink items.");
		waitForUserOrSleep();
		if (clearanceManager.isAutoPerformStep())
		((OverviewProjectStepper) Driver.getDatabase().getProjectStepper()).setProceedWhenDone(false);
	}
	
	
	public static void doNext() {
		clearanceManager.setStepDescription("Next step is to navigate to next student. \n\nWait until the UI is refreshed before proceeding. \n\nThere should be two pink items (6 and 7) for non-full points indicating attention.");
		waitForUserOrSleep();
		if (clearanceManager.isAutoPerformStep())
		Driver.getDatabase().getProjectStepper().next();
	}
	
	

	
	
	public static void quit2() {
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
	
	
	


	

}
