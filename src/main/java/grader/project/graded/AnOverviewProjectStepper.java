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
import grader.trace.overall_transcript.OverallTranscriptLoaded;
import grader.trace.settings.InvalidOnyenRangeException;
import grader.trace.settings.MissingOnyenException;
import grader.trace.stepper.ProjectStepStarted;
import grader.trace.stepper.ProjectStepperStarted;
import grader.trace.stepper.auto_visit.ProjectRun;
import grader.trace.stepper.feature.FeatureColored;
import grader.trace.stepper.feature.FeatureRetargetedToNewProject;
import grader.trace.stepper.feature.auto_result_format.FeatureAutoResultFormatLoaded;
import grader.trace.stepper.feature.manual_notes.FeatureManualNotesUserChange;
import grader.trace.stepper.feature.manual_notes.FeatureManualNotesColored;
import grader.trace.stepper.feature.score.FeatureScoreLoaded;
import grader.trace.stepper.feature.transcript.FeatureTranscriptLoaded;
import grader.trace.stepper.feedback.FeedbackComputed;
import grader.trace.stepper.feedback.FeedbackLoaded;
import grader.trace.stepper.overall_notes.OverallNotesChanged;
import grader.trace.stepper.overall_notes.OverallNotesColored;
import grader.trace.stepper.overall_notes.OverallNotesIncludedInFeedback;
import grader.trace.stepper.overall_notes.OverallNotesLoaded;
import grader.trace.stepper.overall_notes.OverallNotesSaved;
import grader.trace.stepper.overall_score.OverallScoreAutoChange;
import grader.trace.stepper.overall_score.OverallScoreLoaded;
import grader.trace.stepper.overview.ProjectGradingChanged;
import grader.trace.stepper.overview.ProjectWindowsRecorded;

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
import javax.swing.JOptionPane;
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
import util.annotations.ComponentsVisible;
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
@ComponentsVisible(false)
public class AnOverviewProjectStepper extends AClearanceManager implements
		OverviewProjectStepper {
	PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);
//	public final static long UI_LOAD_TIME = 10 * 1000;
//	boolean firstTime;
	String COMMENTS_FILE_PREFIX = "Comments";
	// List<OEFrame> oldList;
	// Window[] oldWindows;
	GradedProjectNavigator gradedProjectNavigator;
	GradedProjectOverview gradedProjectOverview;
	AutoVisitBehavior autoVisitBehavior;
//	String name = "";
	SakaiProjectDatabase projectDatabase;
//	double score;
	List<String> documents;
	int nextDocumentIndex = 0;
	// ideally this stuff should really be done through property change as
	// Josh's wrapper does
	FeatureGradeRecorder featureGradeRecorder;
//	double multiplier = 1;
//	String onyen = "";
	String commentsFileName = "";

	SakaiProject project;
	framework.project.Project wrappedProject;
	// FinalGradeRecorder gradeRecorder;
//	boolean hasMoreSteps = true;
	FinalGradeRecorder totalScoreRecorder;
//	boolean manualOnyen;
	String logFile, gradedFile, skippedFile;
	String manualNotes = "", autoNotes = "", feedback = "", overallNotes = "";
//	List<CheckResult> featureResults;
//	List<CheckResult> restrictionResults;
	GradingFeature selectedGradingFeature;
//	StudentFolder studentFolder;
	Object frame;
	uiFrame oeFrame;
//	List<ObjectAdapter> gradingObjectAdapters = new ArrayList();
//	ClassAdapter stepperViewAdapter;
//	ObjectAdapter multiplierAdapter, scoreAdapter, gradingFeaturesAdapter, overallNotesAdapter; 
	List<Color> currentColors = new ArrayList(), nextColors = new ArrayList();
	Color /*currentScoreColor, currentMultiplierColor,*/ currentOverallNotesColor, currentManualNotesColor;
	Color /*nextScoreColor, nextMultiplierColor,*/ nextOverallNotesColor, nextManualNotesColor;

	boolean changed;
//	Icon studentPhoto;
//	LabelBeanModel photoLabelBeanModel;
	String output = "";
	String source = "";
//	GraderSettingsModel graderSettings;

	// FinalGradeRecorder gradeRecorder() {
	// return projectDatabase.getGradeRecorder();
	// }
	// FinalGradeRecorder totalScoreRecorder() {
	// return projectDatabase.getTotalScoreRecorder();
	// }
	public AnOverviewProjectStepper() {
		gradedProjectNavigator = new AGradedProjectNavigator();
		gradedProjectOverview = new AGradedProjectOverview();
		autoVisitBehavior = new AnAutoVisitBehavior();
		
//		photoLabelBeanModel = new ALabelBeanModel("");
//		addPropertyChangeListener(this); // listen to yourself to see if you have changed
	}
	public void setProjectDatabase(SakaiProjectDatabase aProjectDatabase) {
		projectDatabase = aProjectDatabase;
		ProjectStepperStarted.newCase(aProjectDatabase, this, this);

		gradedProjectOverview.setProjectDatabase(aProjectDatabase);
		autoVisitBehavior.setProjectDatabase(aProjectDatabase);
		gradedProjectNavigator.setProjectDatabase(aProjectDatabase);
		
		// gradeRecorder = aProjectDatabase.getGradeRecorder();
		featureGradeRecorder = aProjectDatabase.getFeatureGradeRecorder();
//		totalScoreRecorder = aProjectDatabase.getTotalScoreRecorder();
		registerWithGradingFeatures();
		logFile = aProjectDatabase.getAssigmentDataFolder().getLogFileName();
//		source = aProjectDatabase.getSourceDisplayer().getAll
//		gradedFile = aProjectDatabase.getAssigmentDataFolder()
//				.getGradedIdFileName();
//		skippedFile = aProjectDatabase.getAssigmentDataFolder()
//				.getSkippedIdFileName();
		GraderSettingsModel graderSettings = aProjectDatabase.getGraderSettings();
//		graderSettings.getNavigationSetter().getNavigationFilterSetter().addPropertyChangeListener(this);
		getNavigationSetter().getNavigationFilterSetter().addPropertyChangeListener(this);
		
		// recordWindows(); // the first project does not wait so we need to
		// record here
		
		// code moved from run projects interactively
		projectDatabase.initIO();

		projectDatabase.recordWindows();
		featureGradeRecorder.setGradingFeatures(projectDatabase
				.getGradingFeatures());
		for (int i = 0; i < projectDatabase.getGradingFeatures().size(); i++) {
			currentColors.add(null);
			nextColors.add(null);
		}

	}

	boolean runExecuted;


	String getCommentsFileName(SakaiProject aProject) {
		return AGradingFeature.getFeedbackFolderName(aProject)
				+ COMMENTS_FILE_PREFIX + AGradingFeature.FEEDBACK_FILE_SUFFIX;

	}

	String readOverallNotes(SakaiProject aProject) {
		try {
			String fileName = getCommentsFileName(aProject);
//			return FileUtils.readFileToString(new File(
//					getCommentsFileName(aProject)));
			String retVal = FileUtils.readFileToString(new File(
					fileName));
			OverallNotesLoaded.newCase(projectDatabase, this, project, fileName, retVal, this);
			return retVal;
			
		} catch (IOException e) {
			return "";
		}
	}

	void writeComments(SakaiProject aProject, String newVal) {
		if (newVal.equals("")) // no need to create file
			return;
		try {
			String fileName = getCommentsFileName(aProject);
//			FileUtils.writeStringToFile(
//					new File(getCommentsFileName(aProject)), newVal);
			FileUtils.writeStringToFile(
					new File(fileName), newVal);
			OverallNotesSaved.newCase(projectDatabase, this, project, fileName, newVal, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	


	public boolean setProject(String anOnyen) {

		try {
			gradedProjectOverview.internalSetOnyen(anOnyen);
		} catch (MissingOnyenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); // not sure we will ever come here
		}
		Boolean retVal = setProject(projectDatabase.getProject(anOnyen));
		SakaiProject project = getProject();
		internalSetSource(
						getProject().
							getClassesTextManager().getEditedAllSourcesText(project.getSourceFileName()));								;
		
		return retVal;

	}


	void notifyPreconditionChanged() {
		propertyChangeSupport.firePropertyChange("this", null, this);

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
			double lastScore = getGrade(aGradingFeature.getFeatureName());
			FeatureScoreLoaded.newCase(projectDatabase, this, project, aGradingFeature, featureGradeRecorder.getFileName(), lastScore, this);

			String resultFormat = getSavedResultFormat(aGradingFeature);

			if (resultFormat != "") {
				FeatureAutoResultFormatLoaded.newCase(projectDatabase, this, project, aGradingFeature, featureGradeRecorder.getFileName(), resultFormat, this);

				aGradingFeature.setResultFormat(resultFormat);
			}
			aGradingFeature.pureSetValidate(false);

			// aGradingFeature.initScore(lastScore);
			// aGradingFeature.setProject(project);
			// initScore was not firing updates
			// aGradingFeature.initScore(lastScore);
			if (lastScore != ASakaiCSVFinalGradeManager.DEFAULT_VALUE)

				aGradingFeature.pureSetScore(lastScore);
			FeatureRetargetedToNewProject.newCase(projectDatabase, this, project, aGradingFeature, this);

		}
	}
	@Override
	public void setComputedScore() {
		List<GradingFeature> gradingFeatures = projectDatabase
				.getGradingFeatures();
		double aScore = 0;
		for (GradingFeature aGradingFeature : gradingFeatures) {
			aScore += aGradingFeature.getScore();
		}
		if (aScore < 0) {
			Tracer.error("Negative computed Score!");
		}
//		setScore(aScore);
		internalSetScore(aScore);
		featureGradeRecorder.setGrade(getName(), getOnyen(), aScore);
		OverallScoreAutoChange.newCase(projectDatabase, this, project, getScore(), this);



	}

	@Visible(false)
	public SakaiProject getProject() {
		return project;
	}


	String allOutput;
	@Override
	public void setStoredOutput () {
		String fileName = project.getOutputFileName();
//		StringBuffer currentOutput = Common.toText(project.getOutputFileName());	
		StringBuffer currentOutput = Common.toText(fileName);
		 project.setCurrentOutput(currentOutput);
			OverallTranscriptLoaded.newCase(projectDatabase, this, project,  fileName, output, this);

		 List<GradingFeature> gradingFeatures = getProjectDatabase().getGradingFeatures();
		  allOutput = currentOutput.toString();

			for (GradingFeature aGradingFeature : gradingFeatures) {
				String output = RunningProject.extractFeatureTranscript(aGradingFeature.getFeatureName(), allOutput);
				aGradingFeature.setOutput(output);	
				FeatureTranscriptLoaded.newCase(projectDatabase, this, project, aGradingFeature, fileName, output, this);
			} 
			refreshTranscript();
			

	}
	
	void refreshTranscript() {
		if (selectedGradingFeature != null) {
//			 internalSetOutput( project.getCurrentOutput().toString());
					internalSetTranscript(selectedGradingFeature.getOutput());
				} else {
					internalSetTranscript(allOutput);
				}
	}

	// Josh: We want to know when a project is set, so I'm adding the project
	// property change event here.
	@Visible(false)
	@Override
	public boolean setProject(SakaiProject newVal) {
		ProjectStepStarted.newCase(projectDatabase, this, project, this);
		settingUpProject = true;
		propertyChangeSupport.firePropertyChange(OEFrame.SUPPRESS_NOTIFICATION_PROCESSING, false, true);
		setChanged(false);
		if (newVal == null) {
			// Josh: Added event
			propertyChangeSupport.firePropertyChange("Project", null, null);
			settingUpProject = false;
			// not sending anything to feature recorder
			return false;
		}


		
//		writeScores(this);
//		runExecuted = false;
		project = newVal;
		// will do this before setProject in overview which has side effects and has to be done later
		// onyen was set earlier in overview
		gradedProjectOverview.setName(project.getStudentAssignment().getStudentName());



		documents = project.getStudentAssignment().getDocuments();
		resetGradingFeatures();

//		autoVisitBehavior.setProject(newVal);
		// documents.remove(project.getOutputFileName());
		// documents.remove(project.getSourceFileName());
		nextDocumentIndex = 0;
//		if (totalScoreRecorder != null)
//			setInternalScore(getGrade());
		double savedScore = featureGradeRecorder.getGrade(gradedProjectOverview.getName(), gradedProjectOverview.getOnyen());
		OverallScoreLoaded.newCase(projectDatabase, this, project, featureGradeRecorder.getFileName(), savedScore, this);
		if (savedScore != ASakaiCSVFinalGradeManager.DEFAULT_VALUE) {
			gradedProjectOverview.internalSetScore(savedScore);
			// propagate to other recorders
			// cannot do this, recording session not created
//			featureGradeRecorder.setGrade(gradedProjectOverview.getName(), gradedProjectOverview.getOnyen(), savedScore);
		}
		if (!gradedProjectNavigator.shouldVisit()) {
			return false;
		}

		// setInternalScore(gradeRecorder.getGrade(project.getStudentAssignment().getStudentName(),
		// project.getStudentAssignment().getOnyen()));

		// Josh: Added event
		propertyChangeSupport.firePropertyChange("Project", AttributeNames.IGNORE_NOTIFICATION, project);

		featureGradeRecorder.newSession(getOnyen());
		featureGradeRecorder.setGrade(getName(), getOnyen(), getScore()); // pass the first score to recording session
		gradedProjectOverview.setProject(newVal);
		autoVisitBehavior.setProject(newVal);
		gradedProjectNavigator.setProject(newVal);
	
		// featureGradeRecorder.setEarlyLatePoints(name, onyen,
		// gradePercentage);

		if (selectedGradingFeature != null) {
			internalSetManualNotes(getManualNotes(selectedGradingFeature));
//			internalSetResult(getSavedResult(selectedGradingFeature));  // could  use cached result in selected feature
			internalSetResult(selectedGradingFeature.getAutoNotes());  

		} else {
			internalSetManualNotes("");
			autoNotes = "";
		}

		internalSetOverallNotes(readOverallNotes(project));
		
		

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
	

	
	void refreshManualNotesColor() {
		if (currentManualNotesColor != nextManualNotesColor) {
			setColor ( "ManualNotes", nextManualNotesColor);
			currentManualNotesColor = nextManualNotesColor;
			FeatureManualNotesColored.newCase(projectDatabase, this, project,  selectedGradingFeature, nextManualNotesColor, manualNotes, this);
		}
	}
	
	void setGradingFeatureColor(int index) {
		if (settingUpProject) return;
		GradingFeature gradingFeature = projectDatabase.getGradingFeatures().get(index);
//		Color nextFeatureColor = projectDatabase.getGradingFeatureColorer().color(projectDatabase.getGradingFeatures().get(index));
		Color nextFeatureColor = projectDatabase.getGradingFeatureColorer().color(gradingFeature);

		nextColors.set(index,nextFeatureColor );

		if (currentColors.get(index) != nextFeatureColor) {
			setColor ( "GradingFeatures." + index, nextFeatureColor);

//		setColor ( "GradingFeatures." + index, nextColors.get(index));
		currentColors.set(index, nextFeatureColor);
		FeatureColored.newCase(projectDatabase, this, project, gradingFeature, nextFeatureColor, this);
		}
		if (selectedGradingFeature == projectDatabase.getGradingFeatures().get(index)) {
			nextManualNotesColor = nextFeatureColor;
			refreshManualNotesColor();
		}
		
	}
	
	void setSelectedGradingFeatureColor() {
		if (selectedGradingFeature == null) {
//			nextManualNotesColor = null; // will we ever hit this code?
			return;
		}
		int index = projectDatabase.getGradingFeatures().indexOf(selectedGradingFeature);
		setGradingFeatureColor(index);
	}
	void setColor(String aPath, Color aColor) {
		propertyChangeSupport.firePropertyChange(aPath, null, 
				new Attribute(AttributeNames.CONTAINER_BACKGROUND, aColor));
	}
	void setLabel(String aPath, String aLabel) {
		propertyChangeSupport.firePropertyChange(aPath, null, 
				new Attribute(AttributeNames.LABEL, aLabel));
	}

	
	public void setOverallNotesColor() {
		if (settingUpProject) return;
		nextOverallNotesColor = projectDatabase.getOverallNotesColorer().color(overallNotes);
		if (currentOverallNotesColor == nextOverallNotesColor ) return;
		setColor("OverallNotes",  nextOverallNotesColor);
		OverallNotesColored.newCase(projectDatabase, this, project, nextOverallNotesColor, overallNotes, this);
		currentOverallNotesColor = nextOverallNotesColor;
	}
	
	

	
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


	@Override
	public void setColors() {
		if (frame == null)
			return;
		// no incremental updates as score and other properties change during auto grade
		if (settingUpProject) 
		    return;
//		computeNextColors();
		setGradingFeatureColors();
		gradedProjectOverview.setMultiplierColor();
		setOverallNotesColor();
		gradedProjectOverview.setScoreColor();
		
	}
	


	

	public boolean preOutput() {
		// return project.canBeRun();
		return autoVisitBehavior.runAttempted() && project.canBeRun();

	}

//	public boolean preRun() {
//		return project.canBeRun() && !autoRun
//		// && !runExecuted
//		;
//	}

//	@Row(3)
//	@ComponentWidth(100)
	@Visible(true)
	public void run() {
		runExecuted = true;
		projectDatabase.runProject(getOnyen(), project);
		// should this go in the code doing the running?
		ProjectRun.newCase(projectDatabase, this, project, this);
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
	@Visible(false)
	@Row(4)
	@ComponentWidth(100)
	public void output() {
		project.setHasBeenRun(true);

		projectDatabase.displayOutput();

	}
	@Override
	public void internalSetSource(String newValue) {
		String oldValue = source;
		source = newValue;
		propertyChangeSupport.firePropertyChange("source", oldValue, newValue);
	}
	@Override
	public String getSource() {
		return source;
	}
	@Override
	public void setSource(String newVal) {
		source = newVal;
		getProject().getClassesTextManager().setEditedAllSourcesText(getProject().getSourceFileName(), newVal);
		
	}

	public boolean preSources() {
		// return project.canBeRun();
		return project.runChecked() || project.hasBeenRun();
		// return true;

	}

//	@Row(5)
//	@ComponentWidth(100)
	@Visible(true)
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
//		double retVal = featureGradeRecorder.getGrade(project.getStudentAssignment()
//				.getStudentName(), project.getStudentAssignment().getOnyen(),
//				aFeature);
//		return retVal;
		return featureGradeRecorder.getGrade(project.getStudentAssignment()
				.getStudentName(), project.getStudentAssignment().getOnyen(),
				aFeature);

	}


	@Override
	public boolean preGetGradingFeatures() {
		return projectDatabase != null
				&& projectDatabase.getGradingFeatures().size() > 0;
	}


	boolean settingUpProject;


	@Override
//	@Row(1)
	@Visible(false)
	public GradingFeatureList getGradingFeatures() {
		if (projectDatabase != null)
			return projectDatabase.getGradingFeatures();
		else
			return null;

	}


	@Row(10)
	@Override
	public boolean isAllGraded() {
		return getGradingFeatures().isAllGraded();
	}


	
	void validate (GradingFeature aGradingFeature) {
		NotesGenerator notesGenerator = projectDatabase.getNotesGenerator();
		String newNotes = notesGenerator.appendNotes(
				aGradingFeature.getManualNotes(), 
				notesGenerator.validationNotes(this, aGradingFeature));
		aGradingFeature.setManualNotes(newNotes);
		setComputedFeedback(); // it is not yet selected
		if (selectedGradingFeature == aGradingFeature) {
			setManualNotes(newNotes);
		}

	}
	@Row(14)
	@Override
	@ComponentWidth(100)
	public void validate() {
		if (selectedGradingFeature != null) {
			validate(selectedGradingFeature);
		}
		
	}



	@Row(2)
	@ComponentWidth(400)
//	@Label("Auto Notes")

	@Visible(false)
	public String getAutoNotes() {
		return autoNotes;
	}
	@Override
	public void internalSetAutoNotes(String newVal) {
		String oldVal = autoNotes;
		autoNotes = newVal;
		propertyChangeSupport.firePropertyChange("autoNotes", oldVal, newVal);

	}

	@Row(3)
	@ComponentWidth(400)
	@PreferredWidgetClass(JTextArea.class)
//	@Label("Manual Notes:")
	@Visible(false)
	public String getManualNotes() {
		return manualNotes;
	}
@Override
	public boolean preSetManualNotes() {
		return selectedGradingFeature != null;
	}
    @Override
	public void internalSetManualNotes(String newVal) {
		String oldVal = manualNotes;

		manualNotes = newVal;
		setSelectedGradingFeatureColor();

//		if (currentManualNotesColor != nextManualNotesColor) {
//			refreshManualNotesColor(); // this will trigger a full refresh
//		}
//		else
		propertyChangeSupport.firePropertyChange("manualNotes", oldVal, newVal);
	}
	
	void internalSetTranscript(String newVal) {
		String oldVal = output;

		output = newVal;
		

		propertyChangeSupport.firePropertyChange("transcript", oldVal, newVal);
	}
    @Override
	public void internalSetResult(String newVal) {
		String oldVal = autoNotes;

		autoNotes = newVal;

		propertyChangeSupport.firePropertyChange("AutoNotes", oldVal, newVal);
	}

	public void setManualNotes(String newVal) {
		if (preSetManualNotes()) {
			setManualNotes(selectedGradingFeature, newVal);
			FeatureManualNotesUserChange.newCase(projectDatabase, this, project, selectedGradingFeature, newVal, this);

			featureGradeRecorder.setFeatureComments(newVal);
			
		}

		setComputedFeedback();
		internalSetManualNotes(newVal);
//		refreshManualNotesColor();
//		setGradingFeatureColors();
//		changed = true;
		// notes = newVal;

		// propertyChangeSupport.firePropertyChange("notes", oldVal, newVal);

	}
//	@Visible(true)
	@Row(1)
//	@Label("Overall Notes")
	@PreferredWidgetClass(JTextArea.class)
	@ComponentWidth(700)
	@ComponentHeight(50)
	@Override
	public String getOverallNotes() {
		return overallNotes;

	}
@Override
	public void internalSetOverallNotes(String newVal) {
		String oldVal = overallNotes;

		overallNotes = newVal;		
		featureGradeRecorder.saveOverallNotes(overallNotes);
		OverallNotesIncludedInFeedback.newCase(projectDatabase, this, project, overallNotes, this);
		propertyChangeSupport.firePropertyChange("OverallNotes", oldVal, newVal);
		
	}
    @Override
	public void setOverallNotes(String newVal) {
    	setChanged(true);
    	OverallNotesChanged.newCase(projectDatabase, this, project, newVal, this);
		internalSetOverallNotes(newVal);
		
		// String oldVal = newVal;
		// comments = newVal;
		// propertyChangeSupport.firePropertyChange("comments", oldVal, newVal);
//		featureGradeRecorder.save(overallNotes);
		writeComments(project, newVal);
		setComputedFeedback();
		

	}
    @Override
	public void setComputedFeedback() {
		String oldVal = feedback;
		feedback = featureGradeRecorder.computeSummary();
		propertyChangeSupport.firePropertyChange("feedback", oldVal, feedback);
		if (!oldVal.equals(feedback))
			FeedbackComputed.newCase(projectDatabase, this, project, null, feedback, this);
	}
    @Override
	public void setStoredFeedback() {
		String oldVal = feedback;
		feedback = featureGradeRecorder.getStoredSummary();		
		propertyChangeSupport.firePropertyChange("feedback", oldVal, feedback);
		FeedbackLoaded.newCase(projectDatabase, this, project, null, feedback, this);
	}
	
//	@Row(19)
////	@ComponentHeight(100)
////	@ComponentWidth(100)
//	@Override
//	public LabelBeanModel getPhoto() {
//		return photoLabelBeanModel;
//	}
	
	
	@Row(20)
	@Override
	@ComponentWidth(600)
	@ComponentHeight(100)
	@PreferredWidgetClass(JTextArea.class)
	@Visible(false)
	public String getFeedback() {
		return feedback;

	}
	
//	@Visible(true)
	@Row(6)
//	@Override
	@ComponentWidth(600)
	@ComponentHeight(100)
	@PreferredWidgetClass(JTextArea.class)
	@Visible(false)
	public String getTranscript() {
		return output;

	}
	public boolean preGetNavigationSetter() {
		return projectDatabase.getGraderSettings() != null;
	}
	@Row(22)
	@Override
	public NavigationSetter getNavigationSetter() {
		return projectDatabase.getGraderSettings().getNavigationSetter();
	}
	



	

	@Override
	public synchronized void waitForClearance() {

		super.waitForClearance();
		

	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyChangeSupport.addPropertyChangeListener(aListener);
	}


	
	String getSavedResultFormat(GradingFeature aGradingFeature) {
		return featureGradeRecorder.getResult(getName(), getOnyen(), aGradingFeature.getFeatureName());
	}
//
	String getInMemoryResult(GradingFeature aGradingFeature) {
		return aGradingFeature.getAutoNotes();
//		CheckResult checkResult = gradingFeatureToCheckResult(aGradingFeature);
//		if (checkResult != null) {
//			return checkResult.getMessage();
//		}
//
//		return "";
	}

	void setManualNotes(GradingFeature aGradingFeature, String aNotes) {
		featureGradeRecorder.setFeatureComments(aNotes);
		featureGradeRecorder.comment(aGradingFeature);
		aGradingFeature.setManualNotes(aNotes);
		// CheckResult checkResult =
		// gradingFeatureToCheckResult(aGradingFeature);
		// if (checkResult != null) {
		// checkResult.setNotes(aNotes);
		// }

	}

	String getManualNotes(GradingFeature aGradingFeature) {
		String retVal = aGradingFeature.getManualNotes();
		// CheckResult checkResult =
		// gradingFeatureToCheckResult(aGradingFeature);
		// if (checkResult != null) {
		// return checkResult.getNotes(); }
		if (retVal == null)
			retVal = "";

		return retVal;
	}
	
	void refreshSelectedFeature() {
		if (selectedGradingFeature != null)
		manualNotes = getManualNotes(selectedGradingFeature);
		else 
			manualNotes = "";
//		propertyChangeSupport.firePropertyChange("this", null, this); // an
//		// event
//		// from
//		// grading
//		// features,
//		// perhaps
//		// our
//		// precondition
//		// chnaged
//		// such
//		// as
//		// auoGraded or manualnotes
	}
	@Override
	@Visible(false)
	public GradingFeature getSelectedGradingFeature() {
		return selectedGradingFeature;
	}
	public void setSelectedFeature (GradingFeature gradingFeature) {
			selectedGradingFeature = gradingFeature;

			internalSetManualNotes(getManualNotes(gradingFeature));
			internalSetAutoNotes(getInMemoryResult(gradingFeature));
//			manualNotes = getNotes(gradingFeature);
//			autoNotes = getInMemoryResult(gradingFeature);
			// log = gradingFeature.getFeature();
//			selectedGradingFeature = gradingFeature;
			internalSetTranscript(selectedGradingFeature.getOutput());
			unSelectOtherGradingFeatures(gradingFeature);
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getSource() instanceof GradingFeature
				&& evt.getPropertyName().equalsIgnoreCase("score")) {
			GradingFeature aGradingFeature = (GradingFeature) evt.getSource();
			// setInternalScore(aGradingFeature.getScore());
			if (!settingUpProject)
			setComputedScore();
			setGrade(aGradingFeature.getFeatureName(), aGradingFeature.getScore());
//			setSelectedFeature(aGradingFeature);// auto select
			if (!settingUpProject) {
			setChanged(true);
			setComputedFeedback();
			setGradingFeatureColors();
			aGradingFeature.setSelected(true); 
			
			}

			
		} else if (evt.getSource() instanceof GradingFeature
				&& evt.getPropertyName().equalsIgnoreCase("selected") && !settingUpProject) {
			GradingFeature gradingFeature = (GradingFeature) evt.getSource();
			GradingFeature oldSelectedFeature = selectedGradingFeature;
			if ((Boolean) evt.getNewValue()) {
				
				setSelectedFeature(gradingFeature);
				refreshManualNotesColor();
//				manualNotes = getNotes(gradingFeature);
//				autoNotes = getInMemoryResult(gradingFeature);
//				// log = gradingFeature.getFeature();
//				selectedGradingFeature = gradingFeature;
//				output = selectedGradingFeature.getOutput();
//				unSelectOtherGradingFeatures(gradingFeature);
			} else {
				if (!projectDatabase.getGradingFeatures().hasASelection()) {
					selectedGradingFeature = null;
					refreshTranscript();
					internalSetManualNotes("");
					nextManualNotesColor = null;
					refreshManualNotesColor();
				}
					
				// this may be a bounced feature
				// selectedGradingFeature = null;
				// unSelectOtherGradingFeatures(null);
				// setNotes("");
			}
			if (!settingUpProject) { 
				refreshSelectedFeature(); // we know a user action occurred
				// check if editability of manual notes changes
				if ((oldSelectedFeature == null && selectedGradingFeature != null) 
						|| (oldSelectedFeature != null && selectedGradingFeature == null))
					notifyPreconditionChanged();
				// let us be more efficient
				// manual notes pre condition needs to be reevaluated
			
//				propertyChangeSupport.firePropertyChange("this", null, this); // an
//																				// event
//																				// from
//																				// grading
//																				// features,
//																				// perhaps
//																				// our
//																				// precondition
//																				// chnaged
//																				// such
//																				// as
//																				// auoGraded
				}

		} else if (evt.getSource() instanceof GradingFeature
				&& evt.getPropertyName().equalsIgnoreCase("validate") && !settingUpProject) {
			GradingFeature gradingFeature = (GradingFeature) evt.getSource();
			validate(gradingFeature);
			setGradingFeatureColors();
			gradingFeature.setSelected(true); 
		
//		} else if (evt.getSource() instanceof GradingFeature
//				&& evt.getPropertyName().equalsIgnoreCase("autograde") && !settingUpProject) {
//			GradingFeature gradingFeature = (GradingFeature) evt.getSource();			
//			gradingFeature.setSelected(true); 
//		
		} 	else if (evt.getSource() == this) {
			// will get even name and onyen changes - let us focus on the changes that really need to be saved in the setters
			if (!settingUpProject)

			setChanged(true);
			return; // do not want to execute the statement below as it  will cause infinite recursion
			
		} else if (evt.getSource() == getNavigationSetter().getNavigationFilterSetter()) {
			gradedProjectNavigator.resetNoFilteredRecords();
		}
		// we will refresh only on selection changes
//		if (!settingUpProject) { 
//		refreshSelectedFeature(); // we know a user action occurred
//		// let us be more efficient
//		// manual notes pre condition needs to be reevaluated
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

	}

	
	@Override
	@Visible(false)
	public SakaiProjectDatabase getProjectDatabase() {
		// TODO Auto-generated method stub
		return projectDatabase;
	}



	@Override
	public boolean preRunProjectsInteractively() {
		return projectDatabase.getOnyenNavigationList() != null && gradedProjectNavigator.getCurrentOnyenIndex() < projectDatabase.getOnyenNavigationList().size();
	}
	

	
	public boolean runProjectsInteractively() throws InvalidOnyenRangeException {
	
			try {
				return runProjectsInteractively("");
			} catch (MissingOnyenException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); // this cannot happen
				return false;
			}
	}
	

	@Override
	public boolean runProjectsInteractively(String aGoToOnyen) throws MissingOnyenException, InvalidOnyenRangeException {
		List<String> onyens = projectDatabase.getOnyenNavigationList();
		if (onyens.size() == 0) {
			String message = "No onyens found in specified range.";
//			JOptionPane.showMessageDialog(null, message);
			InvalidOnyenRangeException invalidOnyenRangeException = InvalidOnyenRangeException.newCase(message, this);
//			throw new InvalidOnyenRangeException(message);
			throw invalidOnyenRangeException;

		}

		if (!preRunProjectsInteractively()) {
			Tracer.error("Projects not configured");
//			hasMoreSteps = false;
			return false;
		}
//		List<String> onyens = projectDatabase.getOnyenNavigationList();
		
		String anOnyen = aGoToOnyen;
		if (aGoToOnyen.isEmpty()) {
		
			anOnyen= onyens.get(gradedProjectNavigator.getCurrentOnyenIndex());
		} else {
			int currentOnyenIndex = onyens.indexOf(anOnyen);
			if (currentOnyenIndex == -1) {
				MissingOnyenException missingOnyenException = MissingOnyenException.newCase(anOnyen, this);
//				throw new MissingOnyenException(anOnyen, this);
				throw missingOnyenException;
			}
			gradedProjectNavigator.setCurrentOnyenIndex(currentOnyenIndex);
		}
		SakaiProject aProject = projectDatabase.getProject(anOnyen);
		

		boolean retVal = setProject(anOnyen);
		if (!retVal) {
			 next();
			 if (!gradedProjectNavigator.hasMoreSteps()) {
//				 currentOnyenIndex = filteredOnyenIndex;
				 gradedProjectNavigator.setCurrentOnyenIndex(gradedProjectNavigator.getFilteredOnyenIndex());
				 return false;
			 }
		}
		gradedProjectNavigator.setFilteredOnyenIndex(gradedProjectNavigator.getCurrentOnyenIndex());
//		filteredOnyenIndex = currentOnyenIndex;
		return true;
		

	}
	


	void redirectProject() {
		projectDatabase.initIO();
		projectDatabase.recordWindows();
//		ProjectWindowsRecorded.newCase(projectDatabase, this, project, this);
	}
	



	@Visible(false)
	@Override
	public void setFrame(Object aFrame) {
		frame = aFrame;
		if (aFrame instanceof uiFrame) {
			oeFrame = (uiFrame) aFrame;
//		oeFrame = aFrame;
//			setObjectAdapters();
			if (project != null) {
				projectDatabase.recordWindows();
//				ProjectWindowsRecorded.newCase(projectDatabase, this, project, this);
				currentColors.clear();
				nextColors.clear();
				currentOverallNotesColor = null;
				for (int i = 0; i < projectDatabase.getGradingFeatures().size(); i++) {
					currentColors.add(null);
					nextColors.add(null);
				}
				setColors();
				gradedProjectOverview.setFrame(aFrame);
				gradedProjectNavigator.setFrame(aFrame);
			}
		}
		autoVisitBehavior.setFrame(aFrame);
	}

	@Visible(false)
	@Override
	public Object getFrame() {
		return frame;
	}
	
	
	public void computeNextColors() {
//		List<Color> colors = new ArrayList();
		int i = 0;
		for (GradingFeature aGradingFeature:projectDatabase.getGradingFeatures()) {
			nextColors.set(i, projectDatabase.getGradingFeatureColorer().color(aGradingFeature) );
			i++;
		}
//		nextMultiplierColor = projectDatabase.getMultiplierColorer().color(multiplier);
//		nextScoreColor = projectDatabase.getScoreColorer().color(score);
		nextOverallNotesColor = projectDatabase.getOverallNotesColorer().color(overallNotes);
		gradedProjectOverview.computeNextColors();	
		
	}
	@Override
	public boolean isChanged() {
		return changed;
	}
	@Override
	public void setChanged(boolean changed) {
		this.changed = changed;
		if (changed)
			ProjectGradingChanged.newCase(projectDatabase, this, project, this);
	}
	@Override
	public boolean isSettingUpProject() {
		return settingUpProject;
	}
	@Override
	public void setSettingUpProject(boolean settingUpProject) {
		this.settingUpProject = settingUpProject;
	}
	public void configureNavigationList() {
		gradedProjectNavigator.configureNavigationList();
	}
	public boolean preDone() {
		return gradedProjectNavigator.preDone();
	}
	public void done() {
		gradedProjectNavigator.done();
	}
	public boolean preNext() {
		return gradedProjectNavigator.preNext();
	}
	public void next() {
		gradedProjectNavigator.next();
	}
	public boolean prePrevious() {
		return gradedProjectNavigator.prePrevious();
	}
	public void previous() {
		gradedProjectNavigator.previous();
	}
	public boolean move(boolean forward) {
		return gradedProjectNavigator.move(forward);
	}
	public boolean isProceedWhenDone() {
		return gradedProjectNavigator.isProceedWhenDone();
	}
	public boolean preTogglePlayPause() {
		return gradedProjectNavigator.preTogglePlayPause();
	}
	@Override
	public void toggleProceedWhenDone() {
		gradedProjectNavigator.toggleProceedWhenDone();
	}
	public void internalSetOnyen(String anOnyen) throws MissingOnyenException {
		gradedProjectNavigator.internalSetOnyen(anOnyen);
	}
	public boolean shouldVisit() {
		return gradedProjectNavigator.shouldVisit();
	}
	public void resetNoFilteredRecords() {
		gradedProjectNavigator.resetNoFilteredRecords();
	}
	@Visible(false)
	public int getCurrentOnyenIndex() {
		return gradedProjectNavigator.getCurrentOnyenIndex();
	}
	public void setCurrentOnyenIndex(int currentOnyenIndex) {
		gradedProjectNavigator.setCurrentOnyenIndex(currentOnyenIndex);
	}
	public void setHasMoreSteps(boolean newVal) {
		gradedProjectNavigator.setHasMoreSteps(newVal);
	}
	public boolean hasMoreSteps() {
		return gradedProjectNavigator.hasMoreSteps();
	}
	@Visible(false)	
	public int getFilteredOnyenIndex() {
		return gradedProjectNavigator.getFilteredOnyenIndex();
	}
	public void setFilteredOnyenIndex(int filteredOnyenIndex) {
		gradedProjectNavigator.setFilteredOnyenIndex(filteredOnyenIndex);
	}
	@Override
	public String getSequenceNumber() {
		return gradedProjectNavigator.getSequenceNumber();
	}

	@Visible(false)
	public LabelBeanModel getPhoto() {
		return gradedProjectOverview.getPhoto();
	}
	public void setOnyen(String anOnyen) throws MissingOnyenException {
		gradedProjectOverview.setOnyen(anOnyen);
	}
	@Visible(false)
	public String getName() {
		return gradedProjectOverview.getName();
	}
	public void setName(String newVal) {
		gradedProjectOverview.setName(newVal);
	}
	public void setScore(double newVal) {
		gradedProjectOverview.setScore(newVal);
	}
	@Visible(false)
	public double getScore() {
		return gradedProjectOverview.getScore();
	}
	@Visible(false)
	public double getMultiplier() {
		return gradedProjectOverview.getMultiplier();
	}
	public void internalSetMultiplier(double newValue) {
		gradedProjectOverview.internalSetMultiplier(newValue);
	}
	public void setMultiplier(double newValue) {
		gradedProjectOverview.setMultiplier(newValue);
	}
	@Visible(false)
	public String getOnyen() {
		return gradedProjectOverview.getOnyen();
	}
	public void internalSetScore(double newVal) {
		gradedProjectOverview.internalSetScore(newVal);
	}
	public void setMultiplierColor() {
		gradedProjectOverview.setMultiplierColor();
	}
	public void setScoreColor() {
		gradedProjectOverview.setScoreColor();
	}
	public boolean isAutoRun() {
		return autoVisitBehavior.isAutoRun();
	}
	public void setAutoRun(boolean newVal) {
		autoVisitBehavior.setAutoRun(newVal);
	}
	public void autoRun() {
		autoVisitBehavior.autoRun();
	}
	public boolean preAutoGrade() {
		return autoVisitBehavior.preAutoGrade();
	}
	public void autoGrade() {
		autoVisitBehavior.autoGrade();
	}
	public boolean isAutoAutoGrade() {
		return autoVisitBehavior.isAutoAutoGrade();
	}
	public void setAutoAutoGrade(boolean newVal) {
		autoVisitBehavior.setAutoAutoGrade(newVal);
	}
	public void autoAutoGrade() {
		autoVisitBehavior.autoAutoGrade();
	}
	public boolean runAttempted() {
		return autoVisitBehavior.runAttempted();
	}
	@Visible(true)
	@Row(0)
	@Column(1)
	public GradedProjectNavigator getGradedProjectNavigator() {
		return gradedProjectNavigator;
	}
	public void setGradedProjectNavigator(
			GradedProjectNavigator gradedProjectNavigator) {
		this.gradedProjectNavigator = gradedProjectNavigator;
	}
	@Row(0)
	@Column(0)
	@Visible(true)
	public GradedProjectOverview getGradedProjectOverview() {
		return gradedProjectOverview;
	}
	
	public void setGradedProjectOverview(GradedProjectOverview gradedProjectOverview) {
		this.gradedProjectOverview = gradedProjectOverview;
	}
	@Visible(false)
	public AutoVisitBehavior getAutoVisitBehavior() {
		return autoVisitBehavior;
	}
	public void setAutoVisitBehavior(AutoVisitBehavior autoVisitBehavior) {
		this.autoVisitBehavior = autoVisitBehavior;
	}
	public static void main(String[] args) {
		ObjectEditor.edit(new AnOverviewProjectStepper());
	}
	@Override
	public boolean isPlayMode() {
		// TODO Auto-generated method stub
		return gradedProjectNavigator.isPlayMode();
	}
	@Override
	public void setPlayMode(boolean playMode) {
		gradedProjectNavigator.setPlayMode(playMode);
		
	}
	@Override
	public void togglePlayPause() {
		gradedProjectNavigator.togglePlayPause();
	}
	@Override
	public void save() {
		gradedProjectNavigator.save();
		
	}
	@Visible(false)
	@Override
	public void resetFeatureSpreadsheet() {
		boolean retVal = projectDatabase.getAssigmentDataFolder().removeFeatureGradeFile();
		if (retVal)
		System.exit(0);
		
		
	}
	@Visible(false)
	@Override
	public boolean preRestoreFeatureSpreadsheet() {
		return projectDatabase.getAssigmentDataFolder().backupExists();
	}
	@Visible(false)
	@Override
	public void restoreFeatureSpreadsheet() {
		boolean retVal = projectDatabase.getAssigmentDataFolder().restoreFeatureGradeFile();
		if (retVal)
			System.exit(0);
		
	}
	@Override
	public void quit() {
		gradedProjectNavigator.quit();
	}
	@Override
	public void setProceedWhenDone(boolean proceedWhenDone) {
		gradedProjectNavigator.setProceedWhenDone(proceedWhenDone);
		
	}
	
}
