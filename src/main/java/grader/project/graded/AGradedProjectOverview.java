package grader.project.graded;

import framework.execution.RunningProject;
import framework.grading.testing.CheckResult;
import framework.grading.testing.Checkable;
import framework.logging.recorder.ConglomerateRecorder;
import framework.navigation.StudentFolder;
import framework.project.ProjectClassesManager;
import framework.utils.GraderSettings;
import framework.utils.GradingEnvironment;
import grader.assignment.AGradingFeature;
import grader.assignment.GradingFeature;
import grader.assignment.GradingFeatureList;
import grader.auto_notes.NotesGenerator;
import grader.documents.DocumentDisplayerRegistry;
import grader.feedback.ScoreFeedback;
import grader.file.FileProxy;
import grader.file.FileProxyUtils;
import grader.navigation.NavigationKind;
import grader.navigation.filter.ADispatchingFilter;
import grader.navigation.filter.BasicNavigationFilter;
import grader.photos.APhotoReader;
import grader.project.Project;
import grader.sakai.project.ProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.settings.navigation.NavigationSetter;
import grader.spreadsheet.FeatureGradeRecorder;
import grader.spreadsheet.FinalGradeRecorder;
import grader.spreadsheet.FinalGradeRecorderFactory;
import grader.spreadsheet.TotalScoreRecorderSelector;
import grader.spreadsheet.csv.ASakaiCSVFeatureGradeManager;
import grader.spreadsheet.csv.ASakaiCSVFinalGradeManager;
import grader.spreadsheet.csv.ASakaiFeatureGradeSheetMerger;
import grader.trace.MissingOnyenException;

import java.awt.Color;
import java.awt.Window;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JTextArea;

import org.antlr.runtime.EarlyExitException;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import bus.uigen.uiFrame;
import bus.uigen.uiFrameList;
import bus.uigen.attributes.AttributeNames;
import bus.uigen.controller.menus.SelectedMenuItem;
import bus.uigen.introspect.Attribute;
import bus.uigen.oadapters.ClassAdapter;
import bus.uigen.oadapters.ObjectAdapter;
import scala.Option;
import util.annotations.Column;
import util.annotations.ComponentHeight;
import util.annotations.ComponentWidth;
import util.annotations.Explanation;
import util.annotations.Label;
import util.annotations.PreferredWidgetClass;
import util.annotations.Row;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Visible;
import util.misc.AClearanceManager;
import util.misc.Common;
import util.misc.ThreadSupport;
import util.models.ALabelBeanModel;
import util.models.LabelBeanModel;
import util.trace.Tracer;
import wrappers.framework.project.ProjectWrapper;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public class AGradedProjectOverview  implements
		GradedProjectOverview {
	PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);
	GradedProjectTextOverview textOverview;
	ProjectStepper projectStepper;
//	public final static long UI_LOAD_TIME = 10 * 1000;
//	boolean firstTime;
//	String COMMENTS_FILE_PREFIX = "Comments";
//	// List<OEFrame> oldList;
//	// Window[] oldWindows;
//	String name = "";
	SakaiProjectDatabase projectDatabase;
//	double score;
//	List<String> documents;
//	int nextDocumentIndex = 0;
//	// ideally this stuff should really be done through property change as
//	// Josh's wrapper does
//	FeatureGradeRecorder featureGradeRecorder;
//	double multiplier = 1;
//	String onyen = "";
////	String commentsFileName = "";
//
	SakaiProject project;
////	framework.project.Project wrappedProject;
////	// FinalGradeRecorder gradeRecorder;
////	boolean hasMoreSteps = true;
//	FinalGradeRecorder totalScoreRecorder;
//	boolean manualOnyen;
//	String logFile, gradedFile, skippedFile;
//	String manualNotes = "", autoNotes = "", feedback = "", overallNotes = "";
//	List<CheckResult> featureResults;
//	List<CheckResult> restrictionResults;
//	GradingFeature selectedGradingFeature;
//	StudentFolder studentFolder;
//	Object frame;
//	uiFrame oeFrame;
//	List<ObjectAdapter> gradingObjectAdapters = new ArrayList();
//	ClassAdapter stepperViewAdapter;
//	ObjectAdapter multiplierAdapter, scoreAdapter, gradingFeaturesAdapter, overallNotesAdapter; 
//	List<Color> currentColors = new ArrayList(), nextColors = new ArrayList();
//	Color currentScoreColor, currentMultiplierColor;
////	currentOverallNotesColor;
//	Color nextScoreColor, nextMultiplierColor;
////	, nextOverallNotesColor;
//
////	boolean changed;
	Icon studentPhoto;
	LabelBeanModel photoLabelBeanModel;
//	ProjectStepper projectStepper;
//	String output = "";

	// FinalGradeRecorder gradeRecorder() {
	// return projectDatabase.getGradeRecorder();
	// }
	// FinalGradeRecorder totalScoreRecorder() {
	// return projectDatabase.getTotalScoreRecorder();
	// }
	public AGradedProjectOverview() {
		photoLabelBeanModel = new ALabelBeanModel("");
		textOverview = new AGradedProjectTextOverview();
//		addPropertyChangeListener(this); // listen to yourself to see if you have changed
	}
	public void setProjectDatabase(SakaiProjectDatabase aProjectDatabase) {
		projectDatabase = aProjectDatabase;
		textOverview.setProjectDatabase(aProjectDatabase);
		// gradeRecorder = aProjectDatabase.getGradeRecorder();
//		featureGradeRecorder = aProjectDatabase.getFeatureGradeRecorder();
//		totalScoreRecorder = aProjectDatabase.getTotalScoreRecorder();
//		projectStepper = projectDatabase.getProjectStepper();
//		registerWithGradingFeatures();
//		logFile = aProjectDatabase.getAssigmentDataFolder().getLogFileName();
//		gradedFile = aProjectDatabase.getAssigmentDataFolder()
//				.getGradedIdFileName();
//		skippedFile = aProjectDatabase.getAssigmentDataFolder()
//				.getSkippedIdFileName();
//		GraderSettingsModel graderSettings = aProjectDatabase.getGraderSettings();
////		graderSettings.getNavigationSetter().getNavigationFilterSetter().addPropertyChangeListener(this);
//		getNavigationSetter().getNavigationFilterSetter().addPropertyChangeListener(this);
		
		// recordWindows(); // the first project does not wait so we need to
		// record here

	}
//
////	boolean runExecuted;
////
////	boolean runAttempted() {
////		return runExecuted || isAutoRun() || isAutoAutoGrade();
////	}
//
//	@ComponentWidth(150)
//	@Row(0)
//	@Override
//	public String getOnyen() {
//		return onyen;
//	}

//	String getCommentsFileName(SakaiProject aProject) {
//		return AGradingFeature.getFeedbackFolderName(aProject)
//				+ COMMENTS_FILE_PREFIX + AGradingFeature.FEEDBACK_FILE_SUFFIX;
//
//	}
//
//	String readComments(SakaiProject aProject) {
//		try {
//			return FileUtils.readFileToString(new File(
//					getCommentsFileName(aProject)));
//		} catch (IOException e) {
//			return "";
//		}
//	}
//
//	void writeComments(SakaiProject aProject, String newVal) {
//		if (newVal.equals("")) // why create file?
//			return;
//		try {
//			FileUtils.writeStringToFile(
//					new File(getCommentsFileName(aProject)), newVal);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	@Override
//	public void setOnyen(String anOnyen) throws MissingOnyenException {
//		internalSetOnyen(anOnyen);
//		manualOnyen = true;
//	}
//	@Override
//	 public void internalSetOnyen(String anOnyen) throws MissingOnyenException {
//		// project = projectDatabase.getProject(anOnyen);
//		String oldOnyen = onyen;
//		// this stuff will go into basic project or project stepper
////		int onyenIndex = onyens.indexOf(anOnyen);
////		if (onyenIndex < 0) {
////			Tracer.error("Student:" + anOnyen + " does not exist in specified onyen range");
////			throw new MissingOnyenException(anOnyen);
//////			return;
//////		}
////		currentOnyenIndex = onyenIndex;
////		maybeSaveState();
////		redirectProject();
////		boolean retVal = setProject(anOnyen);
////		if (!retVal) {
////			onyen = oldOnyen;
////			propertyChangeSupport.firePropertyChange("onyen", null, onyen);
////			return;
////		}
//		// again this will void a getter call when properties are redisplayed
//		propertyChangeSupport.firePropertyChange(oldOnyen, null, onyen);
//
//		// set project does most of this except the output files part
////		projectDatabase.resetRunningProject(project);
////
////		if (autoRun)
////			projectDatabase.runProject(anOnyen, project);
////		if (autoAutoGrade)
////			autoGrade();
////		manualOnyen = true;
//
//		// projectDatabase.runProjectInteractively(anOnyen, this);
//		// onyen = anOnyen;
//		// setProject( projectDatabase.getProject(anOnyen));
//		// projectDatabase.
//
//	}

//	public void setProject(String anOnyen) {
//
//		onyen = anOnyen;
//		return setProject(projectDatabase.getProject(anOnyen));
//
//	}
//
//	boolean autoRun = false;
//
//	public boolean isAutoRun() {
//		return autoRun;
//
//	}
//
//	public void setAutoRun(boolean newVal) {
//		autoRun = newVal;
//
//	}
//
//	public void autoRun() {
//		autoRun = !autoRun;
//	}
//
//	boolean autoAutoGrade = false; // should we automatically do all the auto
//									// grade
//
//	public boolean isAutoAutoGrade() {
//		return autoAutoGrade;
//
//	}
//
//	public void setAutoAutoGrade(boolean newVal) {
//		boolean oldVal = autoAutoGrade;
//		autoAutoGrade = newVal;
//		propertyChangeSupport.firePropertyChange("autoAutoGrade", autoAutoGrade, getOnyen());
//
//
//	}
//
//	public void autoAutoGrade() {
//		autoAutoGrade = !autoAutoGrade;
//	}

//	@Row(1)
//	@ComponentWidth(150)
//	@Override
//	public String getName() {
//		return name;
//	}
//	@Override
//	public void setName(String newVal) {
//		name = newVal;
//		// System.out.println("name changed to" + newVal);
//		// as we are postponing notfications, sending the name change is useful
//		 propertyChangeSupport.firePropertyChange("Name", null, newVal);
////		notifyPreconditionChanged();
//		// System.out.println("precondition notified");
//	}

//	void notifyPreconditionChanged() {
//		propertyChangeSupport.firePropertyChange("this", null, this);
//
//	}

//	@Row(2)
//	@ComponentWidth(150)
//	public double getScore() {
//		return score;
//	}

//	void registerWithGradingFeatures() {
//		if (projectDatabase == null)
//			return;
//		List<GradingFeature> gradingFeatures = projectDatabase
//				.getGradingFeatures();
//		for (GradingFeature aGradingFeature : gradingFeatures) {
//			aGradingFeature.addPropertyChangeListener(this);
//		}
//	}
//
//	void resetGradingFeatures() {
//		if (projectDatabase == null)
//			return;
//
//		GradingFeatureList gradingFeatures = projectDatabase
//				.getGradingFeatures();
//		for (GradingFeature aGradingFeature : gradingFeatures) {
//			// double lastScore =
//			// featureGradeRecorder.getGrade(project.getStudentAssignment().getStudentName(),
//			// project.getStudentAssignment().getOnyen(),
//			// aGradingFeature.getFeature());
//			// double lastScore = getGrade(aGradingFeature.getFeature());
//
//			// aGradingFeature.initScore(lastScore);
//			aGradingFeature.setProject(project);
//			// aGradingFeature.initScore(lastScore); // this resets
//
//		}
//		for (GradingFeature aGradingFeature : gradingFeatures) {
//			// double lastScore =
//			// featureGradeRecorder.getGrade(project.getStudentAssignment().getStudentName(),
//			// project.getStudentAssignment().getOnyen(),
//			// aGradingFeature.getFeature());
//			double lastScore = getGrade(aGradingFeature.getFeature());
//			String result = getSavedResult(aGradingFeature);
//			if (result != "")
//				aGradingFeature.setResult(result);
//
//			// aGradingFeature.initScore(lastScore);
//			// aGradingFeature.setProject(project);
//			// initScore was not firing updates
//			// aGradingFeature.initScore(lastScore);
//			if (lastScore != ASakaiCSVFinalGradeManager.DEFAULT_VALUE)
//
//				aGradingFeature.pureSetScore(lastScore);
//
//		}
//	}
//
//	void setComputedScore() {
//		List<GradingFeature> gradingFeatures = projectDatabase
//				.getGradingFeatures();
//		double aScore = 0;
//		for (GradingFeature aGradingFeature : gradingFeatures) {
//			aScore += aGradingFeature.getScore();
//		}
//		setScore(aScore);
//	}

//	@Visible(false)
//	public SakaiProject getProject() {
//		return project;
//	}

//	public static void writeScores(ProjectStepper aProjectStepper) {
//		ScoreFeedback scoreFeedback = aProjectStepper.getProjectDatabase().getScoreFeedback();
//		if (scoreFeedback != null)
//		    scoreFeedback
//				.writeScores(aProjectStepper);
//		// if (aProjectStepper.getProject() == null) return;
//		// FileProxy feedbackFolder =
//		// aProjectStepper.getProject().getStudentAssignment().getFeedbackFolder();
//		// String totalScoresFile = feedbackFolder.getAbsoluteName() + "/" +
//		// ASakaiProjectDatabase.DEFAULT_SCORE_FILE_NAME;
//		// try {
//		// Common.writeFile(totalScoresFile,
//		// scoresText(aProjectStepper).toString());
//		// } catch (Exception e) {
//		// e.printStackTrace();
//		// }
//
//	}

	// public static void writeScores(ProjectStepper aProjectStepper) {
	// if (project == null) return;
	// FileProxy feedbackFolder =
	// project.getStudentAssignment().getFeedbackFolder();
	// String totalScoresFile = feedbackFolder.getAbsoluteName() + "/" +
	// ASakaiProjectDatabase.DEFAULT_SCORE_FILE_NAME;
	// try {
	// Common.writeFile(totalScoresFile, scoresText().toString());
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// }

//	public static StringBuffer scoresText(ProjectStepper aProjectStepper) {
//		StringBuffer stringBuffer = new StringBuffer();
//		stringBuffer.append("Total Score:" + aProjectStepper.getScore());
//		List<GradingFeature> gradingFeatures = aProjectStepper
//				.getProjectDatabase().getGradingFeatures();
//
//		for (GradingFeature aGradingFeature : gradingFeatures) {
//			stringBuffer.append("\n");
//			stringBuffer.append(aGradingFeature.toString());
//		}
//		return stringBuffer;
//
//	}
	
//	void setStoredOutput () {
//		StringBuffer currentOutput = Common.toText(project.getOutputFileName());		
//		 project.setCurrentOutput(currentOutput);
//		 List<GradingFeature> gradingFeatures = getProjectDatabase().getGradingFeatures();
//		 String allOutput = currentOutput.toString();
//
//			for (GradingFeature aGradingFeature : gradingFeatures) {
//				String output = RunningProject.extractFeatureTranscript(aGradingFeature.getFeature(), allOutput);
//				aGradingFeature.setOutput(output);			
//			} 
//			if (selectedGradingFeature != null) {
////		 internalSetOutput( project.getCurrentOutput().toString());
//				internalSetOutput(selectedGradingFeature.getOutput());
//			} else {
//				internalSetOutput(allOutput);
//			}
//	}

	// Josh: We want to know when a project is set, so I'm adding the project
	// property change event here.
	@Visible(false)
	@Override
	public boolean setProject(SakaiProject newVal) {
//		settingUpProject = true;
//		propertyChangeSupport.firePropertyChange(OEFrame.SUPPRESS_NOTIFICATION_PROCESSING, false, true);
////		changed = false;
//		if (newVal == null) {
//			// Josh: Added event
//			propertyChangeSupport.firePropertyChange("Project", null, null);
//			settingUpProject = false;
//			// not sending anything to feature recorder
//			return false;
//		}
//		writeScores(this);
//		runExecuted = false;
		project = newVal;

		// not needed because of Josh's addition
		// if (project == null) {
		// System.out.println("No project submitted ");
		// return false;
		// }
//		try {
//			wrappedProject = new ProjectWrapper(project, GradingEnvironment
//					.get().getAssignmentName());
//			studentFolder = ProjectWrapper.getStudentFolder(onyen);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		// setName(project.getStudentAssignment().getStudentDescription());
		setName(project.getStudentAssignment().getStudentName());

//		documents = project.getStudentAssignment().getDocuments();
//		resetGradingFeatures();
//		// documents.remove(project.getOutputFileName());
//		// documents.remove(project.getSourceFileName());
//		nextDocumentIndex = 0;
////		if (totalScoreRecorder != null)
////			setInternalScore(getGrade());
//		double savedScore = featureGradeRecorder.getGrade(getName(), onyen);
		
//		setInternalScore(savedScore);
//		if (!shouldVisit()) {
//			return false;
//		}

		// setInternalScore(gradeRecorder.getGrade(project.getStudentAssignment().getStudentName(),
		// project.getStudentAssignment().getOnyen()));

		// Josh: Added event
//		propertyChangeSupport.firePropertyChange("Project", null, project);
//
//		featureGradeRecorder.newSession(onyen);
//		// Josh's code from ProjectStepperDisplayerWrapper
//		// Figure out the late penalty
//		Option<DateTime> timestamp = studentFolder.getTimestamp();
//		// double gradePercentage = timestamp.isDefined() ?
//		// projectDatabase.getProjectRequirements().checkDueDate(timestamp.get())
//		// : 0;
//
//		if (isAutoRun() && !getGradingFeatures().isAllAutoGraded()) {
//			projectDatabase.runProject(onyen, project);
//		}
//		
//		
//		if (isAutoAutoGrade() && !getGradingFeatures().isAllAutoGraded()) {
//			autoGrade();
//			// setComputedSummary();
//			// gradePercentage = timestamp.isDefined() ?
//			// projectDatabase.getProjectRequirements().checkDueDate(timestamp.get())
//			// : 0;
//		} else {
//			multiplier = featureGradeRecorder.getEarlyLatePoints(name,
//					onyen);
//			if (multiplier == ASakaiCSVFeatureGradeManager.DEFAULT_VALUE)
//				multiplier = 1;
//			featureGradeRecorder.setEarlyLatePoints(name, onyen,
//					multiplier);
//
//			setStoredFeedback();
//
//			setStoredOutput();
//		}
//		// featureGradeRecorder.setEarlyLatePoints(name, onyen,
//		// gradePercentage);
//
//		if (selectedGradingFeature != null) {
//			internalSetNotes(getNotes(selectedGradingFeature));
////			internalSetResult(getSavedResult(selectedGradingFeature));  // could  use cached result in selected feature
//			internalSetResult(selectedGradingFeature.getResult());  
//
//		} else {
//			internalSetNotes("");
//			autoNotes = "";
//		}
//
//		internalSetComments(readComments(project));
		
		
		studentPhoto = projectDatabase.getStudentPhoto(textOverview.getOnyen(), project);
				
//				projectDatabase.getPhotoReader().getIcon(onyen);
//		photoLabelBeanModel.setIcon(studentPhoto);

		if (studentPhoto != null){
			photoLabelBeanModel.set("", studentPhoto);
		} else {
			photoLabelBeanModel.set(APhotoReader.NO_PHOTO_TITLE, studentPhoto);
		}
//		settingUpProject = false;
//		setScore();
//		setColors();
//		if (!shouldVisit()) {
//			return false;
//		}
		
//		propertyChangeSupport.firePropertyChange(OEFrame.SUPPRESS_NOTIFICATION_PROCESSING, true, false);

//		boolean changed = setCurrentColors();
//		if (changed)
//			displayColors();

		return true;
	}
	
//	boolean shouldVisit() {
//		if (manualOnyen)
//			return true;
//		GraderSettingsModel graderSettingsModel = projectDatabase.getGraderSettings();
//		if (graderSettingsModel == null) return true;
//		if (graderSettingsModel.getNavigationSetter().getNavigationKind() != NavigationKind.MANUAL)
//			return true;
//		BasicNavigationFilter navigationFilter = projectDatabase.getNavigationFilter();
//		return navigationFilter.includeProject(this, projectDatabase);
//	}
	
//	void refreshColors() {
//		// no incremental updates as score and other properties change during auto grade
//		if (settingUpProject) 
//		    return;
//		boolean changed = setCurrentColors();
//		if (changed)
//			displayColors();
//		
//	}
	
//	void setGradingFeatureColor(int index) {
//		if (settingUpProject) return;
//
//		nextColors.set(index, projectDatabase.getGradingFeatureColorer().color(projectDatabase.getGradingFeatures().get(index)) );
//
//		if (currentColors.get(index) == nextColors.get(index)) return;
//		setColor ( "GradingFeatures." + index, nextColors.get(index));
//		currentColors.set(index, nextColors.get(index));		
//	}
//	void setColor(String aPath, Color aColor) {
//		propertyChangeSupport.firePropertyChange(aPath, null, 
//				new Attribute(AttributeNames.CONTAINER_BACKGROUND, aColor));
//	}
//	public void computeNextColors() {
////		List<Color> colors = new ArrayList();
//		int i = 0;
//		for (GradingFeature aGradingFeature:projectDatabase.getGradingFeatures()) {
//			nextColors.set(i, projectDatabase.getGradingFeatureColorer().color(aGradingFeature) );
//			i++;
//		}
//		nextMultiplierColor = projectDatabase.getMultiplierColorer().color(multiplier);
//		nextScoreColor = projectDatabase.getScoreColorer().color(score);
//		nextOverallNotesColor = projectDatabase.getOverallNotesColorer().color(overallNotes);
//		
//	}
//	@Override
//	public void setMultiplierColor() {
//		if (projectStepper.) return;
//		nextMultiplierColor = projectDatabase.getMultiplierColorer().color(multiplier);
//		if (currentMultiplierColor == nextMultiplierColor ) return;
//		setColor("Multiplier",  nextMultiplierColor);
//		currentMultiplierColor = nextMultiplierColor;
//
//	}
//	@Override
//	public void setScoreColor() {
//		if (settingUpProject) return;
//		nextScoreColor = projectDatabase.getScoreColorer().color(score);
//		if (currentScoreColor == nextScoreColor ) return;
//		setColor("Score",  nextScoreColor);
//		currentScoreColor = nextScoreColor;
	
	
//	void setOverallNotesColor() {
//		if (settingUpProject) return;
//		nextOverallNotesColor = projectDatabase.getOverallNotesColorer().color(overallNotes);
//		if (currentOverallNotesColor == nextOverallNotesColor ) return;
//		setColor("OverallNotes",  nextOverallNotesColor);
//		currentOverallNotesColor = nextOverallNotesColor;
//	}
	
	
	// setCurrentColors and refreshColors should probably be combined
//		boolean setCurrentColors() {
//			 computeNextColors();
//			boolean changed = !currentColors.equals(nextColors) ||
//					(currentMultiplierColor != nextMultiplierColor) ||
//					(currentOverallNotesColor != nextOverallNotesColor) ||
//					(currentScoreColor != nextScoreColor);
//			if (changed) {
//				for (int i=0; i < currentColors.size();i++) {
//					currentColors.set(i, nextColors.get(i));
//				}
//				currentScoreColor = nextScoreColor;
//				currentMultiplierColor = nextMultiplierColor;
//				currentOverallNotesColor = nextOverallNotesColor;
//			}
//			return changed;
//		}
	
//	void setGradingFeatureColors() {
//		if (settingUpProject) 
//		    return;
////		for (int i = 0; i < gradingObjectAdapters.size(); i++) {
//		for (int i = 0; i < projectDatabase.getGradingFeatures().size(); i++) {
//
////			if (currentColors.get(i) != nextColors.get(i)) {
//				setGradingFeatureColor(i);
////				setGradingFeatureColor(i);
////				currentColors.set(i, nextColors.get(i));				
////			}			
//		}
//		
//	}
	
//	void setColors() {
//		// no incremental updates as score and other properties change during auto grade
//		if (settingUpProject) 
//		    return;
////		computeNextColors();
////		setGradingFeatureColors();
//		setMultiplierColor();
////		setOverallNotesColor();
//		setScoreColor();
//		
//	}
	
//	void displayColors() {
//		for (int i = 0; i < gradingObjectAdapters.size(); i++) {
//			ObjectAdapter gradingAdapter =  gradingObjectAdapters.get(i);
//			gradingAdapter.setTempAttributeValue(AttributeNames.CONTAINER_BACKGROUND, currentColors.get(i));
//		}
//		
//		
//		stepperViewAdapter.get("score")
//		.setTempAttributeValue(AttributeNames.COMPONENT_BACKGROUND, currentScoreColor);
//
//		scoreAdapter.setTempAttributeValue(AttributeNames.COMPONENT_BACKGROUND, currentScoreColor);
//		multiplierAdapter.setTempAttributeValue(AttributeNames.COMPONENT_BACKGROUND, currentMultiplierColor);
//		overallNotesAdapter.setTempAttributeValue(AttributeNames.COMPONENT_BACKGROUND, currentOverallNotesColor);
//
//
//		oeFrame.refresh();
////		stepperViewAdapter.refreshAttributes();
//	}

	
//
//	public boolean preOutput() {
//		// return project.canBeRun();
//		return runAttempted() && project.canBeRun();
//
//	}
//
//	public boolean preRun() {
//		return project.canBeRun() && !autoRun
//		// && !runExecuted
//		;
//	}
//
//	@Row(3)
//	@ComponentWidth(100)
//	public void run() {
//		runExecuted = true;
//		projectDatabase.runProject(getOnyen(), project);
//		project.setHasBeenRun(true);
//		for (GradingFeature gradingFeature : projectDatabase
//				.getGradingFeatures()) {
//			if (gradingFeature.isAutoGradable()) {
//				gradingFeature.firePropertyChange("this", null, gradingFeature);
//			}
//		}
//
//	}
//
//	void unSelectOtherGradingFeatures(GradingFeature currentGradingFeature) {
//		for (GradingFeature gradingFeature : projectDatabase
//				.getGradingFeatures()) {
//			if (currentGradingFeature == gradingFeature)
//				continue;
//			gradingFeature.setSelected(false);
//		}
//	}
//
//	@Row(4)
//	@ComponentWidth(100)
//	public void output() {
//		project.setHasBeenRun(true);
//
//		projectDatabase.displayOutput();
//
//	}
//
//	public boolean preSources() {
//		// return project.canBeRun();
//		return project.runChecked() || project.hasBeenRun();
//		// return true;
//
//	}
//
//	@Row(5)
//	@ComponentWidth(100)
//	public void sources() {
//		project.setHasBeenRun(true);
//
//		project.displaySource(projectDatabase);
//
//	}
//
//	@Row(6)
//	@ComponentWidth(100)
//	public void nextDocument() {
//		if (nextDocumentIndex >= documents.size()) {
//			System.out.println("No documents to display");
//			return;
//		}
//		String nextDocument = documents.get(nextDocumentIndex);
//		nextDocumentIndex++;
//		if (nextDocumentIndex == documents.size())
//			nextDocumentIndex = 0;
//		DocumentDisplayerRegistry.display(nextDocument);
//
//	}
//
//	@Row(7)
//	@ComponentWidth(100)
//	public void comments() {
//		DocumentDisplayerRegistry.display(project.getStudentAssignment()
//				.getCommentsFileName());
//	}

//	void setInternalScore(double newVal) {
//		score = newVal;
//		if (!settingUpProject) {
//			setScoreColor();
//			propertyChangeSupport.firePropertyChange("Score", null, newVal);
//		}
//	}
//
//	void setGrade(double newVal) {
//		// This will be a spurious message to conglomerate as t serves as total
//		// and feature recorder
//		if (! (totalScoreRecorder instanceof ConglomerateRecorder))
//		totalScoreRecorder.setGrade(project.getStudentAssignment()
//				.getStudentName(), project.getStudentAssignment().getOnyen(),
//				newVal);
//
//	}
//
//	double getGrade() {
//		return totalScoreRecorder.getGrade(project.getStudentAssignment()
//				.getStudentName(), project.getStudentAssignment().getOnyen());
//
//	}

//	void setGrade(String aFeature, double newVal) {
//		if (!settingUpProject)
//		featureGradeRecorder.setGrade(project.getStudentAssignment()
//				.getStudentName(), project.getStudentAssignment().getOnyen(),
//				aFeature, newVal);
//
//	}

//	double getGrade(String aFeature) {
//		return featureGradeRecorder.getGrade(project.getStudentAssignment()
//				.getStudentName(), project.getStudentAssignment().getOnyen(),
//				aFeature);
//
//	}

//	@Override
//	public void setScore(double newVal) {
//		if (score == newVal) return;
//		double oldVal = score;
//		setInternalScore(newVal);
//		if (totalScoreRecorder != null)
//
//			// if (gradeRecorder != null)
//			setGrade(newVal);
//		featureGradeRecorder.setGrade(name, getOnyen(), newVal);
//		NotesGenerator notesGenerator = projectDatabase.getNotesGenerator();
//		projectStepper.setOverallNotes(notesGenerator.appendNotes(
//				projectStepper.getOverallNotes(), 
//				notesGenerator.totalScoreOverrideNotes(projectStepper, oldVal, newVal)));
//		
////		
////		String aNotes = projectDatabase.getNotesGenerator().totalScoreOverrideNotes(this, oldVal, newVal);
////		String oldOverallNotes = getOverallNotes();
////		String newNotes = oldOverallNotes + " " + aNotes;
////		setOverallNotes(newNotes);
//		
//		
//		// gradeRecorder.setGrade(project.getStudentAssignment().getStudentName(),
//		// project.getStudentAssignment().getOnyen(), newVal);
//		// score = newVal;
//		// propertyChangeSupport.firePropertyChange("Score", null, newVal);
//	}

//	@Override
//	public boolean preGetGradingFeatures() {
//		return projectDatabase != null
//				&& projectDatabase.getGradingFeatures().size() > 0;
//	}
//
//	@Override
//	public boolean preAutoGrade() {
//		// return project.runChecked() && project.canBeRun() &&
//		// preGetGradingFeatures();
//		return /* project.runChecked() && project.canBeRun() && */preGetGradingFeatures();
//
//	}
	boolean settingUpProject;
	//
//	@Row(8)
//	@ComponentWidth(100)
//	@Override
//	public void autoGrade() {
//		project.setHasBeenRun(true);
//		changed = true;
//		project.clearOutput();
//		for (GradingFeature gradingFeature : projectDatabase
//				.getGradingFeatures()) {
//			if (gradingFeature.isAutoGradable()) {
//				gradingFeature.pureSetGraded(true);
//			}
//		}
//		featureResults = projectDatabase.getProjectRequirements()
//				.checkFeatures(wrappedProject);
//		restrictionResults = projectDatabase.getProjectRequirements()
//				.checkRestrictions(wrappedProject);
//		GradingFeatureList features = projectDatabase.getGradingFeatures();
//		setComputedScore(); // will trigger change occurred
//		for (int i = 0; i < features.size(); i++) {
//			// Figure out the score for the feature/restriction
//			double score = (i < featureResults.size()) ? featureResults.get(i)
//					.getScore() : restrictionResults.get(
//					i - featureResults.size()).getScore();
//
//			// Save the comments. We save them in the ConglomerateRecorder so
//			// that, if it is being used as the
//			// manual feedback, they will be pulled in.
//
//			// correcting josh's code to separate feature comments and results
//			// featureGradeRecorder.setFeatureComments(featureResults.get(i).getNotes());
//			// featureGradeRecorder.setFeatureResults(featureResults.get(i).getResults());
//			featureGradeRecorder
//					.setFeatureComments((i < featureResults.size()) ? featureResults
//							.get(i).getNotes() : restrictionResults.get(
//							i - featureResults.size()).getNotes());
//			featureGradeRecorder
//					.setFeatureResults((i < featureResults.size()) ? featureResults
//							.get(i).getResults() : restrictionResults.get(
//							i - featureResults.size()).getResults());
//			
//			features.get(i).setNotes(
//					(i < featureResults.size()) ? featureResults.get(i)
//							.getNotes() : restrictionResults.get(
//							i - featureResults.size()).getNotes());
//			
//			String result = (i < featureResults.size()) ? featureResults.get(i)
//					.getTarget().getSummary() : restrictionResults
//					.get(i - featureResults.size()).getTarget()
//					.getSummary();
//			// in memory save
//			features.get(i).setResult(result);
//			// save to the excel file so we can read it later
//			featureGradeRecorder.setResult(getName(), getOnyen(), features.get(i).getFeature(), 
//					result);			
//			features.get(i).setScore(score);
//
//			// Save the score
//			featureGradeRecorder.setGrade(getName(), getOnyen(), features.get(i)
//					.getFeature(), score);
//		}
//		setComputedFeedback();
//		setStoredOutput();
//		// Josh's code from ProjectStepperDisplayerWrapper
//				// Figure out the late penalty
//		Option<DateTime> timestamp = studentFolder.getTimestamp();
//				// double gradePercentage = timestamp.isDefined() ?
//				// projectDatabase.getProjectRequirements().checkDueDate(timestamp.get())
//				// : 0;
//		double aMultiplier = timestamp.isDefined() ?
//				 projectDatabase.getProjectRequirements().checkDueDate(timestamp.get())
//				 : 0;
//		internalSetMultiplier(aMultiplier);
////		featureGradeRecorder.setEarlyLatePoints(name, onyen, aMultiplier);
//		// setSummary();
//		
//
//	}

//	@Override
//	@Row(9)
//	public GradingFeatureList getGradingFeatures() {
//		if (projectDatabase != null)
//			return projectDatabase.getGradingFeatures();
//		else
//			return null;
//
//	}
//
//	public boolean preProceed() {
//		// if manuelOnyen then we will go to next step
//		// cannot proceed if it is not all graded
//		// why check for manualOnyen?
//		return (hasMoreSteps || manualOnyen) && isAllGraded();
//	}
//
//	public boolean preSkip() {
//		return !preProceed();
//	}
//
//	@Row(10)
//	@Override
//	public boolean isAllGraded() {
//		return getGradingFeatures().isAllGraded();
//	}
//
//	// @Row(11)
//	// @ComponentWidth(100)
//	@Visible(false)
//	public synchronized void proceed() {
//		super.proceed();
//		if (manualOnyen)
//			writeScores(this);
//		manualOnyen = false;
//		try {
//			// Common.appendText(logFile, onyen + " Skipped " +
//			// Common.currentTimeAsDate() + "\n\r");
//			Common.appendText(gradedFile, getOnyen() + "\n");
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

	// @Row(12)
	// @ComponentWidth(100)
	// public synchronized void skip() {
	// proceed();
	// try {
	// Common.appendText(logFile, onyen + " Skipped " +
	// Common.currentTimeAsDate() + "\n");
	// Common.appendText(skippedFile, onyen + "\n");
	// List<String> list = FileProxyUtils.toList(new File(logFile));
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// // should put person in skipped list
	//
	// }
	
//	boolean noNextFilteredRecords;
//
//	@Override
//	public boolean preNext() {
//		return !noNextFilteredRecords && preProceed() && currentOnyenIndex < onyens.size() - 1;
//		// this does not make sense, next is a stronger condition than next
//
//		// return !preDone() && nextOnyenIndex < onyens.size() - 1 ;
//	}
//
//	@Row(12)
//	@ComponentWidth(100)
//	@Override
//	public synchronized void next() {
//
//		try {
//			// Common.appendText(logFile, getOnyen() + " Skipped " +
//			// Common.currentTimeAsDate() + "\n");
//			Common.appendText(skippedFile, getOnyen() + "\n");
//			List<String> list = FileProxyUtils.toList(new File(logFile));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		 move(true);
//		// should put person in skipped list
//
//	}
//	boolean noPreviousFilteredRecords;
//
//	@Override
//	public boolean prePrevious() {
//		return !noPreviousFilteredRecords && currentOnyenIndex > 0;
//	}
//
//	@Row(13)
//	@ComponentWidth(100)
//	@Override
//	public synchronized void previous() {
//
//		// try {
//		// Common.appendText(logFile, onyen + " Skipped " +
//		// Common.currentTimeAsDate() + "\n");
//		// Common.appendText(skippedFile, onyen + "\n");
//		// List<String> list = FileProxyUtils.toList(new File(logFile));
//		// } catch (IOException e) {
//		// // TODO Auto-generated catch block
//		// e.printStackTrace();
//		// }
//		 move(false);
//		// should put person in skipped list
//
//	}
//	
//	void validate (GradingFeature aGradingFeature) {
//		NotesGenerator notesGenerator = projectDatabase.getNotesGenerator();
//		setManualNotes(notesGenerator.appendNotes(
//				getManualNotes(), 
//				notesGenerator.validationNotes(this, aGradingFeature)));
//		
////		
////		String aNotes = projectDatabase.getNotesGenerator().validationNotes(this, aGradingFeature);
////		setManualNotes(aNotes);
//		
////		setNotes(aGradingFeature, aNotes);;
//	}
//	@Row(14)
//	@Override
//	@ComponentWidth(100)
//	public void validate() {
//		if (selectedGradingFeature != null) {
//			validate(selectedGradingFeature);
//		}
//		
//	}

//	String filter = "";

//	@Row(14)
//	@ComponentWidth(150)
//	@Override
//	public String getNavigationFilter() {
//		return projectDatabase.getNavigationFilter();
//	}
//
//	@Override
//	public void setNavigationFilter(String newVal) {
//		projectDatabase.setNavigationFilter(newVal);
//		configureNavigationList();
//		runProjectsInteractively();
//	}

//	@Row(15)
//	public double getMultiplier() {
//		return multiplier;
//	}
//	
//	public void internalSetMultiplier(double newValue) {
//		double oldValue = multiplier;
//		multiplier = newValue;
//		featureGradeRecorder.setEarlyLatePoints(getName(), getOnyen(),
//				multiplier);
//		setMultiplierColor();
//		propertyChangeSupport.firePropertyChange("multiplier", oldValue, newValue);
//	}
//	
//	public void setMultiplier(double newValue) {
//		double oldVal = multiplier;
//		if (oldVal == newValue) return;
//		internalSetMultiplier(newValue);
//		NotesGenerator notesGenerator = projectDatabase.getNotesGenerator();
//		projectStepper.setOverallNotes(notesGenerator.appendNotes(
//				projectStepper.getOverallNotes(), 
//				notesGenerator.multiplierOverrideNotes(projectStepper, oldVal, newValue)));
////		String aNotes = projectDatabase.getNotesGenerator().multiplierOverrideNotes(this, oldVal, newValue);
////		String oldOverallNotes = getOverallNotes();
////		String newNotes = oldOverallNotes + " " + aNotes;
////		setOverallNotes(newNotes);
//		
//	}

//	@Row(16)
//	@ComponentWidth(400)
////	@Label("Auto Notes")
//
//	public String getAutoNotes() {
//		return autoNotes;
//	}
//
//	@Row(17)
//	@ComponentWidth(400)
////	@Label("Manual Notes:")
//	public String getManualNotes() {
//		return manualNotes;
//	}
//
//	public boolean preSetManualNotes() {
//		return selectedGradingFeature != null;
//	}
//
//	void internalSetNotes(String newVal) {
//		String oldVal = manualNotes;
//
//		manualNotes = newVal;
//
//		propertyChangeSupport.firePropertyChange("manualNotes", oldVal, newVal);
//	}
//	
//	void internalSetOutput(String newVal) {
//		String oldVal = output;
//
//		output = newVal;
//
//		propertyChangeSupport.firePropertyChange("output", oldVal, newVal);
//	}
//
//	void internalSetResult(String newVal) {
//		String oldVal = autoNotes;
//
//		autoNotes = newVal;
//
//		propertyChangeSupport.firePropertyChange("AutoNotes", oldVal, newVal);
//	}
//
//	public void setManualNotes(String newVal) {
//		if (preSetManualNotes()) {
//			setNotes(selectedGradingFeature, newVal);
//			featureGradeRecorder.setFeatureComments(newVal);
//		}
//
//		setComputedFeedback();
//		internalSetNotes(newVal);
//		setGradingFeatureColors();
////		changed = true;
//		// notes = newVal;
//
//		// propertyChangeSupport.firePropertyChange("notes", oldVal, newVal);
//
//	}
//
//	@Row(18)
////	@Label("Overall Notes")
//	@ComponentWidth(800)
//	public String getOverallNotes() {
//		return overallNotes;
//
//	}
//
//	void internalSetComments(String newVal) {
//		String oldVal = overallNotes;
//
//		overallNotes = newVal;
//
//		propertyChangeSupport.firePropertyChange("comments", oldVal, newVal);
//		
//	}
//
//	public void setOverallNotes(String newVal) {
//		internalSetComments(newVal);
//		// String oldVal = newVal;
//		// comments = newVal;
//		// propertyChangeSupport.firePropertyChange("comments", oldVal, newVal);
//		featureGradeRecorder.save(overallNotes);
//		writeComments(project, newVal);
//		setComputedFeedback();
//		
//
//	}
//
//	void setComputedFeedback() {
//		String oldVal = feedback;
//		feedback = featureGradeRecorder.computeSummary();
//		propertyChangeSupport.firePropertyChange("feedback", oldVal, feedback);
//	}
//
//	void setStoredFeedback() {
//		String oldVal = feedback;
//		feedback = featureGradeRecorder.getStoredSummary();
//		propertyChangeSupport.firePropertyChange("feedback", oldVal, feedback);
//	}
	
	@Column(0)
	@ComponentHeight(80)
	@ComponentWidth(80)
	@Override
	@Explanation("If no photo, you need to populate photo directory in AssignmentsData folder")
	public LabelBeanModel getPhoto() {
		return photoLabelBeanModel;
	}
//	@Row(20)
//	@Override
//	@ComponentWidth(600)
//	@ComponentHeight(100)
//	@PreferredWidgetClass(JTextArea.class)
//	public String getFeedback() {
//		return feedback;
//
//	}
//	
//	@Visible(true)
//	@Row(21)
////	@Override
//	@ComponentWidth(600)
//	@ComponentHeight(100)
//	@PreferredWidgetClass(JTextArea.class)
//	public String getTranscript() {
//		return output;
//
//	}
//	public boolean preGetNavigationSetter() {
//		return projectDatabase.getGraderSettings() != null;
//	}
//	@Row(22)
//	@Override
//	public NavigationSetter getNavigationSetter() {
//		return projectDatabase.getGraderSettings().getNavigationSetter();
//	}
	

//	@Override
//	public boolean hasMoreSteps() {
//		return hasMoreSteps;
//	}
//
//	@Override
//	public void setHasMoreSteps(boolean newVal) {
//		if (hasMoreSteps == newVal)
//			return;
//		hasMoreSteps = newVal;
//		if (hasMoreSteps == false)
//			writeScores(this);
//
//		notifyPreconditionChanged();
//
//	}

	
//
//	@Override
//	public synchronized void waitForClearance() {
//
//		super.waitForClearance();
//		
//
//	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyChangeSupport.addPropertyChangeListener(aListener);
	}
//
//	CheckResult checkableToResult(Checkable aCheckable) {
//		try {
//			for (CheckResult checkResult : featureResults) {
//				if (checkResult.getTarget() == aCheckable)
//					return checkResult;
//			}
//
//			for (CheckResult checkResult : restrictionResults) {
//				if (checkResult.getTarget() == aCheckable)
//					return checkResult;
//			}
//		} catch (Exception e) {
//			return null;
//		}
//		return null;
//
//	}

//	CheckResult gradingFeatureToCheckResult(GradingFeature aGradingFeature) {
//		Checkable checkable = projectDatabase.getRequirement(aGradingFeature);
//		if (checkable != null) {
//			return checkableToResult(checkable);
//		}
//		return null;
//	}
//	
//	String getSavedResult(GradingFeature aGradingFeature) {
//		return featureGradeRecorder.getResult(getName(), getOnyen(), aGradingFeature.getFeature());
//	}
//
//	String getInMemoryResult(GradingFeature aGradingFeature) {
//		return aGradingFeature.getResult();
////		CheckResult checkResult = gradingFeatureToCheckResult(aGradingFeature);
////		if (checkResult != null) {
////			return checkResult.getMessage();
////		}
////
////		return "";
//	}
//
//	void setNotes(GradingFeature aGradingFeature, String aNotes) {
//		featureGradeRecorder.setFeatureComments(aNotes);
//		featureGradeRecorder.comment(aGradingFeature);
//		aGradingFeature.setNotes(aNotes);
//		// CheckResult checkResult =
//		// gradingFeatureToCheckResult(aGradingFeature);
//		// if (checkResult != null) {
//		// checkResult.setNotes(aNotes);
//		// }
//
//	}
//
//	String getNotes(GradingFeature aGradingFeature) {
//		String retVal = aGradingFeature.getNotes();
//		// CheckResult checkResult =
//		// gradingFeatureToCheckResult(aGradingFeature);
//		// if (checkResult != null) {
//		// return checkResult.getNotes(); }
//		if (retVal == null)
//			retVal = "";
//
//		return retVal;
//	}
//	
//	void refreshSelectedFeature() {
//		if (selectedGradingFeature != null)
//		manualNotes = getNotes(selectedGradingFeature);
//	}
//	
//	void setSelectedFeature (GradingFeature gradingFeature) {
//		
//			manualNotes = getNotes(gradingFeature);
//			autoNotes = getInMemoryResult(gradingFeature);
//			// log = gradingFeature.getFeature();
//			selectedGradingFeature = gradingFeature;
//			output = selectedGradingFeature.getOutput();
//			unSelectOtherGradingFeatures(gradingFeature);
//		
//	}

//	@Override
//	public void propertyChange(PropertyChangeEvent evt) {
//		if (evt.getSource() instanceof GradingFeature
//				&& evt.getPropertyName().equalsIgnoreCase("score")) {
//			GradingFeature aGradingFeature = (GradingFeature) evt.getSource();
//			// setInternalScore(aGradingFeature.getScore());
//			if (!settingUpProject)
////			setComputedScore();
//			setGrade(aGradingFeature.getFeature(), aGradingFeature.getScore());
////			setSelectedFeature(aGradingFeature);// auto select
//			if (!settingUpProject) {
//			changed = true;
//			setComputedFeedback();
//			setGradingFeatureColors();
//			aGradingFeature.setSelected(true); 
//			
//			}
//
//			
//		} else if (evt.getSource() instanceof GradingFeature
//				&& evt.getPropertyName().equalsIgnoreCase("selected") && !settingUpProject) {
//			GradingFeature gradingFeature = (GradingFeature) evt.getSource();
//			if ((Boolean) evt.getNewValue()) {
//				setSelectedFeature(gradingFeature);
////				manualNotes = getNotes(gradingFeature);
////				autoNotes = getInMemoryResult(gradingFeature);
////				// log = gradingFeature.getFeature();
////				selectedGradingFeature = gradingFeature;
////				output = selectedGradingFeature.getOutput();
////				unSelectOtherGradingFeatures(gradingFeature);
//			} else {
//				// this may be a bounced feature
//				// selectedGradingFeature = null;
//				// unSelectOtherGradingFeatures(null);
//				// setNotes("");
//			}
//
//		} else if (evt.getSource() == this) {
//			// will get even name and onyen changes - let us focus on the changes that really need to be saved in the setters
//			if (!settingUpProject)
//
//			changed = true;
//			return; // do not want to execute the statement below as it  will cause infinite recursion
//			
//		} else if (evt.getSource() == getNavigationSetter().getNavigationFilterSetter()) {
//			noNextFilteredRecords = false;
//			noPreviousFilteredRecords = false;
//		}
//		if (!settingUpProject) { 
//		refreshSelectedFeature(); // we know a user action occurred
//	
//		propertyChangeSupport.firePropertyChange("this", null, this); // an
//																		// event
//																		// from
//																		// grading
//																		// features,
//																		// perhaps
//																		// our
//																		// precondition
//																		// chnaged
//																		// such
//																		// as
//																		// auoGraded
//		}
//
//	}

	// List<OEFrame> newList = new ArrayList( uiFrameList.getList());

	//
	// for (OEFrame frame:newList) {
	// if (oldList.contains(frame))
	// continue;
	// frame.dispose(); // will this work
	// }
	// Window[] newWindows = Window.getWindows();
	//
	//
	// for (Window frame:newWindows) {
	// if (Common.containsReference(oldWindows, frame)) {
	// continue;
	// }
	// frame.dispose();
	// }
	@Override
	@Visible(false)
	public SakaiProjectDatabase getProjectDatabase() {
		// TODO Auto-generated method stub
		return projectDatabase;
	}

//	List<String> onyens;
//	int currentOnyenIndex = 0;
//	int filteredOnyenIndex = 0;
//	String nextOnyen;
//
//	@Override
//	public void configureNavigationList() {
//		onyens = projectDatabase.getOnyenNavigationList();
//		currentOnyenIndex = 0;
//		hasMoreSteps = true;
//	}
//
//	@Override
//	public boolean preRunProjectsInteractively() {
//		return onyens != null && currentOnyenIndex < onyens.size();
//	}
//	
//	void setObjectAdapters() {
//		if (oeFrame == null) return;
//		for (GradingFeature gradingFeature:projectDatabase.getGradingFeatures()) {
//			ObjectAdapter featureAdapter = oeFrame.getObjectAdapter(gradingFeature);	
////			featureAdapter.setTempAttributeValue(AttributeNames.CONTAINER_BACKGROUND, Color.PINK);
////			featureAdapter.refresh();
//			gradingObjectAdapters.add(featureAdapter);
//		}
//		gradingFeaturesAdapter = oeFrame.getObjectAdapter(projectDatabase.getGradingFeatures());
//		if (gradingFeaturesAdapter == null) return;
//		stepperViewAdapter = (ClassAdapter) gradingFeaturesAdapter.getParentAdapter(); // this might be a delegator
//		scoreAdapter = stepperViewAdapter.get("score");
//		multiplierAdapter = stepperViewAdapter.get("multiplier");
//		overallNotesAdapter = stepperViewAdapter.get("overallNotes");
//		
//
//	}
//	
//	public boolean runProjectsInteractively() {
//	
//			try {
//				return runProjectsInteractively("");
//			} catch (MissingOnyenException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace(); // this cannot happen
//				return false;
//			}
//	}
//	
//
//	@Override
//	public boolean runProjectsInteractively(String aGoToOnyen) throws MissingOnyenException {
//		
//		if (!preRunProjectsInteractively()) {
//			Tracer.error("Projects not configured");
//			hasMoreSteps = false;
//			return false;
//		}
//		
//		String anOnyen = aGoToOnyen;
//		if (aGoToOnyen.isEmpty()) {
//		
//			anOnyen= onyens.get(currentOnyenIndex);
//		} else {
//			currentOnyenIndex = onyens.indexOf(anOnyen);
//			if (currentOnyenIndex == -1) {
//				throw new MissingOnyenException(anOnyen);
//			}
//		}
//		SakaiProject aProject = projectDatabase.getProject(anOnyen);
//		projectDatabase.initIO();
//
//		projectDatabase.recordWindows();
//		featureGradeRecorder.setGradingFeatures(projectDatabase
//				.getGradingFeatures());
//		for (int i = 0; i < projectDatabase.getGradingFeatures().size(); i++) {
//			currentColors.add(null);
//			nextColors.add(null);
//		}
//		setObjectAdapters();
//		boolean retVal = setProject(anOnyen);
//		if (!retVal) {
//			 next();
//			 if (!hasMoreSteps) {
//				 currentOnyenIndex = filteredOnyenIndex;
//				 return false;
//			 }
//		}
//		filteredOnyenIndex = currentOnyenIndex;
//		return true;
//		
//
//	}
//	
//
//	@Override
//	public boolean preDone() {
//		// return preProceed();
//		// we are done but do not have another step
//		return preProceed() && !preNext();
//	}
//	void maybeSaveState() {
//		// josh's code
//				// no serialization otherwise
//				if (changed || 
//						!featureGradeRecorder.logSaved()) 
//				featureGradeRecorder.finish();
//
//				// my original code
//				projectDatabase.resetIO();
//				projectDatabase.clearWindows();
//	}
//	void redirectProject() {
//		projectDatabase.initIO();
//		projectDatabase.recordWindows();
//	}
//	
//	void setFailedMoveFlags(boolean forward) {
//		if (forward)
//			noNextFilteredRecords = true;
//		else
//			noPreviousFilteredRecords = true;
//	}
//	
//	void setSuccessfulMoveFlags(boolean forward) {
//		if (forward)
//			noNextFilteredRecords = false;
//		else
//			noPreviousFilteredRecords = false;
//	}
//	
//	
//
//	@Override
//	public synchronized boolean move(boolean forward) {
//		maybeSaveState();
////		// josh's code
////		// no serialization otherwise
////		if (changed || 
////				!featureGradeRecorder.logSaved()) 
////		featureGradeRecorder.finish();
////
////		// my original code
////		projectDatabase.resetIO();
////		projectDatabase.clearWindows();
//		if (forward) {
//			currentOnyenIndex++;
//
//			if (currentOnyenIndex >= onyens.size()) {
//				hasMoreSteps = false;
//				return false;
//			}
//		} else {
//			currentOnyenIndex--;
//			if (currentOnyenIndex < 0) {
//				hasMoreSteps = false;
//				return false;
//			}
//
//		}
//		redirectProject();
//		String anOnyen = onyens.get(currentOnyenIndex);
//		SakaiProject aProject = projectDatabase.getProject(anOnyen);
////		redirectProject();
////		projectDatabase.initIO();
////		projectDatabase.recordWindows();
//		boolean projectSet = setProject(anOnyen);
//		if (!projectSet) {
//			boolean retVal = move(forward);
//			if (!retVal && filteredOnyenIndex != currentOnyenIndex) {
//				currentOnyenIndex = filteredOnyenIndex;
//				try {
//					internalSetOnyen(onyens.get(filteredOnyenIndex));
//				} catch (MissingOnyenException e) {
//					e.printStackTrace(); // this should never be executed
//				}
//				Tracer.error("Cannot move as no more records that satisfy selection condition");
//				setFailedMoveFlags(forward);
//			} else {
//				filteredOnyenIndex = currentOnyenIndex;
//				setSuccessfulMoveFlags(forward);
//			}
//			return retVal;
//		}
//		filteredOnyenIndex = currentOnyenIndex;
//		return true;
//			
//		// these two steps should go into setProject unless there is something
//		// subttle here, specially as the stepProject step below is commented
//		// put
//		// if (isAutoRun())
//		// projectDatabase.runProject(anOnyen, aProject);
//		// if (isAutoAutoGrade())
//		// autoGrade();
//
//		// setProject(anOnyen);
//	}
//
//	@Override
//	@Row(11)
//	@ComponentWidth(100)
//	public synchronized void done() {
//
//		if (manualOnyen)
//			writeScores(this);
//		try {
//			// Common.appendText(logFile, onyen + " Skipped " +
//			// Common.currentTimeAsDate() + "\n\r");
//			Common.appendText(gradedFile, getOnyen() + "\n");
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		move(true);
//	
//
//	}
//
//	@Visible(false)
//	@Override
//	public void setFrame(Object aFrame) {
//		frame = aFrame;
//		if (aFrame instanceof uiFrame) {
//			oeFrame = (uiFrame) aFrame;
////		oeFrame = aFrame;
////			setObjectAdapters();
//		}
//	}
//
//	@Visible(false)
//	@Override
//	public Object getFrame() {
//		return frame;
//	}
	
//	@Override
//	public void computeNextColors() {
////		List<Color> colors = new ArrayList();
////		int i = 0;
////		for (GradingFeature aGradingFeature:projectDatabase.getGradingFeatures()) {
////			nextColors.set(i, projectDatabase.getGradingFeatureColorer().color(aGradingFeature) );
////			i++;
////		}
//		nextMultiplierColor = projectDatabase.getMultiplierColorer().color(multiplier);
//		nextScoreColor = projectDatabase.getScoreColorer().color(score);
////		nextOverallNotesColor = projectDatabase.getOverallNotesColorer().color(overallNotes);
//		
//	}
//	@Visible(true)
	@Column(1)
	public GradedProjectTextOverview getTextOverview() {
		return textOverview;
	}
	public void setTextOverview(GradedProjectTextOverview textOverview) {
		this.textOverview = textOverview;
	}
	
	public void setOnyen(String anOnyen) throws MissingOnyenException {
		textOverview.setOnyen(anOnyen);
	}
//	@Visible(false)
	public SakaiProject getProject() {
		return textOverview.getProject();
	}
	public String getName() {
		return textOverview.getName();
	}
	public void setName(String newVal) {
		textOverview.setName(newVal);
	}
	public void setScore(double newVal) {
		textOverview.setScore(newVal);
	}
	public double getScore() {
		return textOverview.getScore();
	}
	public double getMultiplier() {
		return textOverview.getMultiplier();
	}
	public void internalSetMultiplier(double newValue) {
		textOverview.internalSetMultiplier(newValue);
	}
	public void setMultiplier(double newValue) {
		textOverview.setMultiplier(newValue);
	}
	public String getOnyen() {
		return textOverview.getOnyen();
	}
	public void internalSetOnyen(String anOnyen) throws MissingOnyenException {
		textOverview.internalSetOnyen(anOnyen);
	}
	@Override
	public void computeNextColors() {
		textOverview.computeNextColors();
		
	}
	@Override
	public void internalSetScore(double newVal) {
		textOverview.internalSetScore(newVal);
		
	}
	
	@Override
	public void setMultiplierColor() {
		textOverview.setMultiplierColor();
		
	}
	@Override
	public void setFrame(Object aFrame) {
		textOverview.setFrame(aFrame);
		
	}
	@Override
	public void setScoreColor() {
		textOverview.setScoreColor();
		
	}
	
	public static void main(String[] args) {
		ObjectEditor.edit(new AGradedProjectOverview());
	}
	

}
