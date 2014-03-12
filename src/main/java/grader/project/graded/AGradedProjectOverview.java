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
import grader.navigation.filter.ADispatchingFilter;
import grader.navigation.filter.BasicNavigationFilter;
import grader.photos.APhotoReader;
import grader.project.Project;
import grader.sakai.project.MissingOnyenException;
import grader.sakai.project.ProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.settings.navigation.NavigationKind;
import grader.settings.navigation.NavigationSetter;
import grader.spreadsheet.FeatureGradeRecorder;
import grader.spreadsheet.FinalGradeRecorder;
import grader.spreadsheet.FinalGradeRecorderFactory;
import grader.spreadsheet.TotalScoreRecorderSelector;
import grader.spreadsheet.csv.ASakaiCSVFeatureGradeManager;
import grader.spreadsheet.csv.ASakaiCSVFinalGradeManager;
import grader.spreadsheet.csv.ASakaiFeatureGradeSheetMerger;

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
public class AGradedProjectOverview extends AClearanceManager implements
		GradedProjectOverview {
	PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);
	public final static long UI_LOAD_TIME = 10 * 1000;
	boolean firstTime;
	String COMMENTS_FILE_PREFIX = "Comments";
	// List<OEFrame> oldList;
	// Window[] oldWindows;
	String name = "";
	SakaiProjectDatabase projectDatabase;
	double score;
	List<String> documents;
	int nextDocumentIndex = 0;
	// ideally this stuff should really be done through property change as
	// Josh's wrapper does
	FeatureGradeRecorder featureGradeRecorder;
	double multiplier = 1;
	String onyen = "";
	String commentsFileName = "";

	SakaiProject project;
	framework.project.Project wrappedProject;
	// FinalGradeRecorder gradeRecorder;
	boolean hasMoreSteps = true;
	FinalGradeRecorder totalScoreRecorder;
	boolean manualOnyen;
	String logFile, gradedFile, skippedFile;
	String manualNotes = "", autoNotes = "", feedback = "", overallNotes = "";
	List<CheckResult> featureResults;
	List<CheckResult> restrictionResults;
	GradingFeature selectedGradingFeature;
	StudentFolder studentFolder;
	Object frame;
	uiFrame oeFrame;
	List<ObjectAdapter> gradingObjectAdapters = new ArrayList();
	ClassAdapter stepperViewAdapter;
	ObjectAdapter multiplierAdapter, scoreAdapter, gradingFeaturesAdapter, overallNotesAdapter; 
	List<Color> currentColors = new ArrayList(), nextColors = new ArrayList();
	Color currentScoreColor, currentMultiplierColor, currentOverallNotesColor;
	Color nextScoreColor, nextMultiplierColor, nextOverallNotesColor;

	boolean changed;
	Icon studentPhoto;
	LabelBeanModel photoLabelBeanModel;
	String output = "";

	// FinalGradeRecorder gradeRecorder() {
	// return projectDatabase.getGradeRecorder();
	// }
	// FinalGradeRecorder totalScoreRecorder() {
	// return projectDatabase.getTotalScoreRecorder();
	// }
	public AGradedProjectOverview() {
		photoLabelBeanModel = new ALabelBeanModel("");
//		addPropertyChangeListener(this); // listen to yourself to see if you have changed
	}
	public void setProjectDatabase(SakaiProjectDatabase aProjectDatabase) {
		projectDatabase = aProjectDatabase;
		// gradeRecorder = aProjectDatabase.getGradeRecorder();
		featureGradeRecorder = aProjectDatabase.getFeatureGradeRecorder();
		totalScoreRecorder = aProjectDatabase.getTotalScoreRecorder();
		registerWithGradingFeatures();
		logFile = aProjectDatabase.getAssigmentDataFolder().getLogFileName();
		gradedFile = aProjectDatabase.getAssigmentDataFolder()
				.getGradedIdFileName();
		skippedFile = aProjectDatabase.getAssigmentDataFolder()
				.getSkippedIdFileName();
		GraderSettingsModel graderSettings = aProjectDatabase.getGraderSettings();
//		graderSettings.getNavigationSetter().getNavigationFilterSetter().addPropertyChangeListener(this);
		getNavigationSetter().getNavigationFilterSetter().addPropertyChangeListener(this);
		
		// recordWindows(); // the first project does not wait so we need to
		// record here

	}

	boolean runExecuted;

	boolean runAttempted() {
		return runExecuted || isAutoRun() || isAutoAutoGrade();
	}

	@ComponentWidth(150)
	@Row(0)
	public String getOnyen() {
		return onyen;
	}

	String getCommentsFileName(SakaiProject aProject) {
		return AGradingFeature.getFeedbackFolderName(aProject)
				+ COMMENTS_FILE_PREFIX + AGradingFeature.FEEDBACK_FILE_SUFFIX;

	}

	String readComments(SakaiProject aProject) {
		try {
			return FileUtils.readFileToString(new File(
					getCommentsFileName(aProject)));
		} catch (IOException e) {
			return "";
		}
	}

	void writeComments(SakaiProject aProject, String newVal) {
		if (newVal.equals("")) // why create file?
			return;
		try {
			FileUtils.writeStringToFile(
					new File(getCommentsFileName(aProject)), newVal);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void setOnyen(String anOnyen) throws MissingOnyenException {
		internalSetOnyen(anOnyen);
		manualOnyen = true;
	}
	
	 void internalSetOnyen(String anOnyen) throws MissingOnyenException {
		// project = projectDatabase.getProject(anOnyen);
		String oldOnyen = onyen;
		int onyenIndex = onyens.indexOf(anOnyen);
		if (onyenIndex < 0) {
			Tracer.error("Student:" + anOnyen + " does not exist in specified onyen range");
			throw new MissingOnyenException(anOnyen);
//			return;
		}
		currentOnyenIndex = onyenIndex;
		maybeSaveState();
		redirectProject();
		boolean retVal = setProject(anOnyen);
		if (!retVal) {
			onyen = oldOnyen;
			propertyChangeSupport.firePropertyChange("onyen", null, onyen);
			return;
		}
		// again this will void a getter call when properties are redisplayed
		propertyChangeSupport.firePropertyChange(oldOnyen, null, onyen);

		// set project does most of this except the output files part
//		projectDatabase.resetRunningProject(project);
//
//		if (autoRun)
//			projectDatabase.runProject(anOnyen, project);
//		if (autoAutoGrade)
//			autoGrade();
//		manualOnyen = true;

		// projectDatabase.runProjectInteractively(anOnyen, this);
		// onyen = anOnyen;
		// setProject( projectDatabase.getProject(anOnyen));
		// projectDatabase.

	}

	public boolean setProject(String anOnyen) {

		onyen = anOnyen;
		return setProject(projectDatabase.getProject(anOnyen));

	}

	boolean autoRun = false;

	public boolean isAutoRun() {
		return autoRun;

	}

	public void setAutoRun(boolean newVal) {
		autoRun = newVal;

	}

	public void autoRun() {
		autoRun = !autoRun;
	}

	boolean autoAutoGrade = false; // should we automatically do all the auto
									// grade

	public boolean isAutoAutoGrade() {
		return autoAutoGrade;

	}

	public void setAutoAutoGrade(boolean newVal) {
		boolean oldVal = autoAutoGrade;
		autoAutoGrade = newVal;
		propertyChangeSupport.firePropertyChange("autoAutoGrade", autoAutoGrade, onyen);


	}

	public void autoAutoGrade() {
		autoAutoGrade = !autoAutoGrade;
	}

	@Row(1)
	@ComponentWidth(150)
	@Override
	public String getName() {
		return name;
	}
    @Override
	public void setName(String newVal) {
		name = newVal;
		// System.out.println("name changed to" + newVal);
		// as we are postponing notfications, sending the name change is useful
		 propertyChangeSupport.firePropertyChange("Name", null, newVal);
		notifyPreconditionChanged();
		// System.out.println("precondition notified");
	}

	void notifyPreconditionChanged() {
		propertyChangeSupport.firePropertyChange("this", null, this);

	}

	@Row(2)
	@ComponentWidth(150)
	@Override
	public double getScore() {
		return score;
	}

	void registerWithGradingFeatures() {
		if (projectDatabase == null)
			return;
		List<GradingFeature> gradingFeatures = projectDatabase
				.getGradingFeatures();
		for (GradingFeature aGradingFeature : gradingFeatures) {
			aGradingFeature.addPropertyChangeListener(this);
		}
	}

	void resetGradingFeatures() {
		if (projectDatabase == null)
			return;

		GradingFeatureList gradingFeatures = projectDatabase
				.getGradingFeatures();
		for (GradingFeature aGradingFeature : gradingFeatures) {
			// double lastScore =
			// featureGradeRecorder.getGrade(project.getStudentAssignment().getStudentName(),
			// project.getStudentAssignment().getOnyen(),
			// aGradingFeature.getFeature());
			// double lastScore = getGrade(aGradingFeature.getFeature());

			// aGradingFeature.initScore(lastScore);
			aGradingFeature.setProject(project);
			// aGradingFeature.initScore(lastScore); // this resets

		}
		for (GradingFeature aGradingFeature : gradingFeatures) {
			// double lastScore =
			// featureGradeRecorder.getGrade(project.getStudentAssignment().getStudentName(),
			// project.getStudentAssignment().getOnyen(),
			// aGradingFeature.getFeature());
			double lastScore = getGrade(aGradingFeature.getFeature());
			String result = getSavedResult(aGradingFeature);
			if (result != "")
				aGradingFeature.setResult(result);

			// aGradingFeature.initScore(lastScore);
			// aGradingFeature.setProject(project);
			// initScore was not firing updates
			// aGradingFeature.initScore(lastScore);
			if (lastScore != ASakaiCSVFinalGradeManager.DEFAULT_VALUE)

				aGradingFeature.pureSetScore(lastScore);

		}
	}

	void setComputedScore() {
		List<GradingFeature> gradingFeatures = projectDatabase
				.getGradingFeatures();
		double aScore = 0;
		for (GradingFeature aGradingFeature : gradingFeatures) {
			aScore += aGradingFeature.getScore();
		}
		setScore(aScore);
	}

	@Visible(false)
	public SakaiProject getProject() {
		return project;
	}

	public static void writeScores(ProjectStepper aProjectStepper) {
		ScoreFeedback scoreFeedback = aProjectStepper.getProjectDatabase().getScoreFeedback();
		if (scoreFeedback != null)
		    scoreFeedback
				.writeScores(aProjectStepper);
		// if (aProjectStepper.getProject() == null) return;
		// FileProxy feedbackFolder =
		// aProjectStepper.getProject().getStudentAssignment().getFeedbackFolder();
		// String totalScoresFile = feedbackFolder.getAbsoluteName() + "/" +
		// ASakaiProjectDatabase.DEFAULT_SCORE_FILE_NAME;
		// try {
		// Common.writeFile(totalScoresFile,
		// scoresText(aProjectStepper).toString());
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}

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

	public static StringBuffer scoresText(ProjectStepper aProjectStepper) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("Total Score:" + aProjectStepper.getScore());
		List<GradingFeature> gradingFeatures = aProjectStepper
				.getProjectDatabase().getGradingFeatures();

		for (GradingFeature aGradingFeature : gradingFeatures) {
			stringBuffer.append("\n");
			stringBuffer.append(aGradingFeature.toString());
		}
		return stringBuffer;

	}
	
	void setStoredOutput () {
		StringBuffer currentOutput = Common.toText(project.getOutputFileName());		
		 project.setCurrentOutput(currentOutput);
		 List<GradingFeature> gradingFeatures = getProjectDatabase().getGradingFeatures();
		 String allOutput = currentOutput.toString();

			for (GradingFeature aGradingFeature : gradingFeatures) {
				String output = RunningProject.extractFeatureTranscript(aGradingFeature.getFeature(), allOutput);
				aGradingFeature.setOutput(output);			
			} 
			if (selectedGradingFeature != null) {
//		 internalSetOutput( project.getCurrentOutput().toString());
				internalSetOutput(selectedGradingFeature.getOutput());
			} else {
				internalSetOutput(allOutput);
			}
	}

	// Josh: We want to know when a project is set, so I'm adding the project
	// property change event here.
	@Visible(false)
	public boolean setProject(SakaiProject newVal) {
		settingUpProject = true;
		propertyChangeSupport.firePropertyChange(OEFrame.SUPPRESS_NOTIFICATION_PROCESSING, false, true);
		changed = false;
		if (newVal == null) {
			// Josh: Added event
			propertyChangeSupport.firePropertyChange("Project", null, null);
			settingUpProject = false;
			// not sending anything to feature recorder
			return false;
		}
		writeScores(this);
		runExecuted = false;
		project = newVal;

		// not needed because of Josh's addition
		// if (project == null) {
		// System.out.println("No project submitted ");
		// return false;
		// }
		try {
			wrappedProject = new ProjectWrapper(project, GradingEnvironment
					.get().getAssignmentName());
			studentFolder = ProjectWrapper.getStudentFolder(onyen);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// setName(project.getStudentAssignment().getStudentDescription());
		setName(project.getStudentAssignment().getStudentName());

		documents = project.getStudentAssignment().getDocuments();
		resetGradingFeatures();
		// documents.remove(project.getOutputFileName());
		// documents.remove(project.getSourceFileName());
		nextDocumentIndex = 0;
//		if (totalScoreRecorder != null)
//			setInternalScore(getGrade());
		double savedScore = featureGradeRecorder.getGrade(name, onyen);
		
		setInternalScore(savedScore);
		if (!shouldVisit()) {
			return false;
		}

		// setInternalScore(gradeRecorder.getGrade(project.getStudentAssignment().getStudentName(),
		// project.getStudentAssignment().getOnyen()));

		// Josh: Added event
		propertyChangeSupport.firePropertyChange("Project", null, project);

		featureGradeRecorder.newSession(onyen);
		// Josh's code from ProjectStepperDisplayerWrapper
		// Figure out the late penalty
		Option<DateTime> timestamp = studentFolder.getTimestamp();
		// double gradePercentage = timestamp.isDefined() ?
		// projectDatabase.getProjectRequirements().checkDueDate(timestamp.get())
		// : 0;

		if (isAutoRun() && !getGradingFeatures().isAllAutoGraded()) {
			projectDatabase.runProject(onyen, project);
		}
		
		
		if (isAutoAutoGrade() && !getGradingFeatures().isAllAutoGraded()) {
			autoGrade();
			// setComputedSummary();
			// gradePercentage = timestamp.isDefined() ?
			// projectDatabase.getProjectRequirements().checkDueDate(timestamp.get())
			// : 0;
		} else {
			multiplier = featureGradeRecorder.getEarlyLatePoints(name,
					onyen);
			if (multiplier == ASakaiCSVFeatureGradeManager.DEFAULT_VALUE)
				multiplier = 1;
			featureGradeRecorder.setEarlyLatePoints(name, onyen,
					multiplier);

			setStoredFeedback();

			setStoredOutput();
		}
		// featureGradeRecorder.setEarlyLatePoints(name, onyen,
		// gradePercentage);

		if (selectedGradingFeature != null) {
			internalSetNotes(getNotes(selectedGradingFeature));
//			internalSetResult(getSavedResult(selectedGradingFeature));  // could  use cached result in selected feature
			internalSetResult(selectedGradingFeature.getResult());  

		} else {
			internalSetNotes("");
			autoNotes = "";
		}

		internalSetComments(readComments(project));
		
		
		studentPhoto = projectDatabase.getStudentPhoto(onyen, project);
				
//				projectDatabase.getPhotoReader().getIcon(onyen);
//		photoLabelBeanModel.setIcon(studentPhoto);

		if (studentPhoto != null){
			photoLabelBeanModel.set("", studentPhoto);
		} else {
			photoLabelBeanModel.set(APhotoReader.NO_PHOTO_TITLE, studentPhoto);
		}
		settingUpProject = false;
//		setScore();
		setColors();
//		if (!shouldVisit()) {
//			return false;
//		}
		
		propertyChangeSupport.firePropertyChange(OEFrame.SUPPRESS_NOTIFICATION_PROCESSING, true, false);

//		boolean changed = setCurrentColors();
//		if (changed)
//			displayColors();

		return true;
	}
	
	boolean shouldVisit() {
		if (manualOnyen)
			return true;
		GraderSettingsModel graderSettingsModel = projectDatabase.getGraderSettings();
		if (graderSettingsModel == null) return true;
		if (graderSettingsModel.getNavigationSetter().getNavigationKind() != NavigationKind.MANUAL)
			return true;
		BasicNavigationFilter navigationFilter = projectDatabase.getNavigationFilter();
		return navigationFilter.includeProject(this, projectDatabase);
	}
	
//	void refreshColors() {
//		// no incremental updates as score and other properties change during auto grade
//		if (settingUpProject) 
//		    return;
//		boolean changed = setCurrentColors();
//		if (changed)
//			displayColors();
//		
//	}
	
	void setGradingFeatureColor(int index) {
		if (settingUpProject) return;

		nextColors.set(index, projectDatabase.getGradingFeatureColorer().color(projectDatabase.getGradingFeatures().get(index)) );

		if (currentColors.get(index) == nextColors.get(index)) return;
		setColor ( "GradingFeatures." + index, nextColors.get(index));
		currentColors.set(index, nextColors.get(index));		
	}
	void setColor(String aPath, Color aColor) {
		propertyChangeSupport.firePropertyChange(aPath, null, 
				new Attribute(AttributeNames.CONTAINER_BACKGROUND, aColor));
	}
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
	void setMultiplierColor() {
		if (settingUpProject) return;
		nextMultiplierColor = projectDatabase.getMultiplierColorer().color(multiplier);
		if (currentMultiplierColor == nextMultiplierColor ) return;
		setColor("Multiplier",  nextMultiplierColor);
		currentMultiplierColor = nextMultiplierColor;

	}
	void setScoreColor() {
		if (settingUpProject) return;
		nextScoreColor = projectDatabase.getScoreColorer().color(score);
		if (currentScoreColor == nextScoreColor ) return;
		setColor("Score",  nextScoreColor);
		currentScoreColor = nextScoreColor;
	}
	
	void setOverallNotesColor() {
		if (settingUpProject) return;
		nextOverallNotesColor = projectDatabase.getOverallNotesColorer().color(overallNotes);
		if (currentOverallNotesColor == nextOverallNotesColor ) return;
		setColor("OverallNotes",  nextOverallNotesColor);
		currentOverallNotesColor = nextOverallNotesColor;
	}
	
	
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
	
	void setGradingFeatureColors() {
		if (settingUpProject) 
		    return;
//		for (int i = 0; i < gradingObjectAdapters.size(); i++) {
		for (int i = 0; i < projectDatabase.getGradingFeatures().size(); i++) {

//			if (currentColors.get(i) != nextColors.get(i)) {
				setGradingFeatureColor(i);
//				setGradingFeatureColor(i);
//				currentColors.set(i, nextColors.get(i));				
//			}			
		}
		
	}
	
	void setColors() {
		// no incremental updates as score and other properties change during auto grade
		if (settingUpProject) 
		    return;
//		computeNextColors();
		setGradingFeatureColors();
		setMultiplierColor();
		setOverallNotesColor();
		setScoreColor();
		
	}
	
	void displayColors() {
		for (int i = 0; i < gradingObjectAdapters.size(); i++) {
			ObjectAdapter gradingAdapter =  gradingObjectAdapters.get(i);
			gradingAdapter.setTempAttributeValue(AttributeNames.CONTAINER_BACKGROUND, currentColors.get(i));
		}
		
		
		stepperViewAdapter.get("score")
		.setTempAttributeValue(AttributeNames.COMPONENT_BACKGROUND, currentScoreColor);

		scoreAdapter.setTempAttributeValue(AttributeNames.COMPONENT_BACKGROUND, currentScoreColor);
		multiplierAdapter.setTempAttributeValue(AttributeNames.COMPONENT_BACKGROUND, currentMultiplierColor);
		overallNotesAdapter.setTempAttributeValue(AttributeNames.COMPONENT_BACKGROUND, currentOverallNotesColor);


		oeFrame.refresh();
//		stepperViewAdapter.refreshAttributes();
	}

	

	public boolean preOutput() {
		// return project.canBeRun();
		return runAttempted() && project.canBeRun();

	}

	public boolean preRun() {
		return project.canBeRun() && !autoRun
		// && !runExecuted
		;
	}

	@Row(3)
	@ComponentWidth(100)
	public void run() {
		runExecuted = true;
		projectDatabase.runProject(onyen, project);
		project.setHasBeenRun(true);
		for (GradingFeature gradingFeature : projectDatabase
				.getGradingFeatures()) {
			if (gradingFeature.isAutoGradable()) {
				gradingFeature.firePropertyChange("this", null, gradingFeature);
			}
		}

	}

	void unSelectOtherGradingFeatures(GradingFeature currentGradingFeature) {
		for (GradingFeature gradingFeature : projectDatabase
				.getGradingFeatures()) {
			if (currentGradingFeature == gradingFeature)
				continue;
			gradingFeature.setSelected(false);
		}
	}

	@Row(4)
	@ComponentWidth(100)
	public void output() {
		project.setHasBeenRun(true);

		projectDatabase.displayOutput();

	}

	public boolean preSources() {
		// return project.canBeRun();
		return project.runChecked() || project.hasBeenRun();
		// return true;

	}

	@Row(5)
	@ComponentWidth(100)
	public void sources() {
		project.setHasBeenRun(true);

		project.displaySource(projectDatabase);

	}

	@Row(6)
	@ComponentWidth(100)
	public void nextDocument() {
		if (nextDocumentIndex >= documents.size()) {
			System.out.println("No documents to display");
			return;
		}
		String nextDocument = documents.get(nextDocumentIndex);
		nextDocumentIndex++;
		if (nextDocumentIndex == documents.size())
			nextDocumentIndex = 0;
		DocumentDisplayerRegistry.display(nextDocument);

	}

	@Row(7)
	@ComponentWidth(100)
	public void comments() {
		DocumentDisplayerRegistry.display(project.getStudentAssignment()
				.getCommentsFileName());
	}

	void setInternalScore(double newVal) {
		score = newVal;
		if (!settingUpProject) {
			setScoreColor();
			propertyChangeSupport.firePropertyChange("Score", null, newVal);
		}
	}

	void setGrade(double newVal) {
		// This will be a spurious message to conglomerate as t serves as total
		// and feature recorder
		if (! (totalScoreRecorder instanceof ConglomerateRecorder))
		totalScoreRecorder.setGrade(project.getStudentAssignment()
				.getStudentName(), project.getStudentAssignment().getOnyen(),
				newVal);

	}

	double getGrade() {
		return totalScoreRecorder.getGrade(project.getStudentAssignment()
				.getStudentName(), project.getStudentAssignment().getOnyen());

	}

	void setGrade(String aFeature, double newVal) {
		if (!settingUpProject)
		featureGradeRecorder.setGrade(project.getStudentAssignment()
				.getStudentName(), project.getStudentAssignment().getOnyen(),
				aFeature, newVal);

	}

	double getGrade(String aFeature) {
		return featureGradeRecorder.getGrade(project.getStudentAssignment()
				.getStudentName(), project.getStudentAssignment().getOnyen(),
				aFeature);

	}

	@Override
	public void setScore(double newVal) {
		if (score == newVal) return;
		double oldVal = score;
		setInternalScore(newVal);
		if (totalScoreRecorder != null)

			// if (gradeRecorder != null)
			setGrade(newVal);
		featureGradeRecorder.setGrade(name, onyen, newVal);
		NotesGenerator notesGenerator = projectDatabase.getNotesGenerator();
		setOverallNotes(notesGenerator.appendNotes(
				getOverallNotes(), 
				notesGenerator.totalScoreOverrideNotes(this, oldVal, newVal)));
		
//		
//		String aNotes = projectDatabase.getNotesGenerator().totalScoreOverrideNotes(this, oldVal, newVal);
//		String oldOverallNotes = getOverallNotes();
//		String newNotes = oldOverallNotes + " " + aNotes;
//		setOverallNotes(newNotes);
		
		
		// gradeRecorder.setGrade(project.getStudentAssignment().getStudentName(),
		// project.getStudentAssignment().getOnyen(), newVal);
		// score = newVal;
		// propertyChangeSupport.firePropertyChange("Score", null, newVal);
	}

	
	boolean settingUpProject;
	//
	


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

	@Row(15)
	@Override
	public double getMultiplier() {
		return multiplier;
	}
	@Override
	public void internalSetMultiplier(double newValue) {
		double oldValue = multiplier;
		multiplier = newValue;
		featureGradeRecorder.setEarlyLatePoints(name, onyen,
				multiplier);
		setMultiplierColor();
		propertyChangeSupport.firePropertyChange("multiplier", oldValue, newValue);
	}
	@Override
	public void setMultiplier(double newValue) {
		double oldVal = multiplier;
		if (oldVal == newValue) return;
		internalSetMultiplier(newValue);
		NotesGenerator notesGenerator = projectDatabase.getNotesGenerator();
		setOverallNotes(notesGenerator.appendNotes(
				getOverallNotes(), 
				notesGenerator.multiplierOverrideNotes(this, oldVal, newValue)));
//		String aNotes = projectDatabase.getNotesGenerator().multiplierOverrideNotes(this, oldVal, newValue);
//		String oldOverallNotes = getOverallNotes();
//		String newNotes = oldOverallNotes + " " + aNotes;
//		setOverallNotes(newNotes);
		
	}

	@Row(16)
	@ComponentWidth(400)
//	@Label("Auto Notes")

	public String getAutoNotes() {
		return autoNotes;
	}




	@Override
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyChangeSupport.addPropertyChangeListener(aListener);
	}

	
	@Override
	@Visible(false)
	public SakaiProjectDatabase getProjectDatabase() {
		// TODO Auto-generated method stub
		return projectDatabase;
	}

	List<String> onyens;
	int currentOnyenIndex = 0;
	int filteredOnyenIndex = 0;
	String nextOnyen;

	
	
	@Override
	@Row(11)
	@ComponentWidth(100)
	public synchronized void done() {

		if (manualOnyen)
			writeScores(this);
		try {
			// Common.appendText(logFile, onyen + " Skipped " +
			// Common.currentTimeAsDate() + "\n\r");
			Common.appendText(gradedFile, onyen + "\n");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		move(true);
	

	}

	@Visible(false)
	@Override
	public void setFrame(Object aFrame) {
		frame = aFrame;
		if (aFrame instanceof uiFrame) {
			oeFrame = (uiFrame) aFrame;
//		oeFrame = aFrame;
//			setObjectAdapters();
		}
	}

	@Visible(false)
	@Override
	public Object getFrame() {
		return frame;
	}
	
	
	public void computeNextColors() {
////		List<Color> colors = new ArrayList();
//		int i = 0;
//		for (GradingFeature aGradingFeature:projectDatabase.getGradingFeatures()) {
//			nextColors.set(i, projectDatabase.getGradingFeatureColorer().color(aGradingFeature) );
//			i++;
//		}
		nextMultiplierColor = projectDatabase.getMultiplierColorer().color(multiplier);
		nextScoreColor = projectDatabase.getScoreColorer().color(score);
//		nextOverallNotesColor = projectDatabase.getOverallNotesColorer().color(overallNotes);
		
	}

	public static void main(String[] args) {
		ObjectEditor.edit(new AGradedProjectOverview());
	}

}
