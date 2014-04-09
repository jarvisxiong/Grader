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
import grader.trace.multiplier.MultiplierColored;
import grader.trace.multiplier.MultiplierLoaded;
import grader.trace.multiplier.MultiplierSaved;
import grader.trace.multiplier.MultiplierUserChange;
import grader.trace.overall_notes.MultiplierOverrideNotes;
import grader.trace.overall_notes.OverallScoreOverrideNotes;
import grader.trace.overall_score.OverallScoreColored;
import grader.trace.overall_score.OverallScoreManualChange;
import grader.trace.overall_score.OverallScoreSaved;
import grader.trace.settings.MissingOnyenException;
import grader.trace.stepper.UserOnyenSet;

import java.awt.Color;
import java.awt.Window;
import java.awt.image.MultiPixelPackedSampleModel;
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
public class AGradedProjectTextOverview  implements
		GradedProjectTextOverview {
	PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	String name = "";
	SakaiProjectDatabase projectDatabase;
	double score;

//	// ideally this stuff should really be done through property change as
//	// Josh's wrapper does
	FeatureGradeRecorder featureGradeRecorder;
	double multiplier = 1;
	String onyen = "";
//	String commentsFileName = "";

	SakaiProject project;
//	framework.project.Project wrappedProject;
//	// FinalGradeRecorder gradeRecorder;
//	boolean hasMoreSteps = true;
	FinalGradeRecorder totalScoreRecorder;
	boolean manualOnyen;

	Color currentScoreColor, currentMultiplierColor;
//	currentOverallNotesColor;
	Color nextScoreColor, nextMultiplierColor;

	Icon studentPhoto;
	LabelBeanModel photoLabelBeanModel;
	OverviewProjectStepper projectStepper;

	public AGradedProjectTextOverview() {
		photoLabelBeanModel = new ALabelBeanModel("");
//		addPropertyChangeListener(this); // listen to yourself to see if you have changed
	}
	
	public void setProjectDatabase(SakaiProjectDatabase aProjectDatabase) {
		projectDatabase = aProjectDatabase;
		// gradeRecorder = aProjectDatabase.getGradeRecorder();
		featureGradeRecorder = aProjectDatabase.getFeatureGradeRecorder();
		totalScoreRecorder = aProjectDatabase.getTotalScoreRecorder();
		projectStepper = (OverviewProjectStepper) projectDatabase.getProjectStepper();
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
	@Override
	public void setFrame(Object aFrame) {
		if (projectStepper.getProject() != null) {
			currentMultiplierColor = null;
			currentScoreColor = null;
			setMultiplierColor();
			setScoreColor();
		}
	}

//	boolean runExecuted;
//
//	boolean runAttempted() {
//		return runExecuted || isAutoRun() || isAutoAutoGrade();
//	}

	@ComponentWidth(150)
	@Row(0)
	@Override
	@Explanation("Editing onyen will navigate to corresponding project")
	public String getOnyen() {
		return onyen;
	}


	@Override
	public void setOnyen(String anOnyen) throws MissingOnyenException {
		internalSetOnyen(anOnyen);
		manualOnyen = true;
		UserOnyenSet.newCase(projectDatabase, projectStepper, anOnyen, this);
		projectStepper.setProject(anOnyen);
	}
	@Override
	 public void internalSetOnyen(String anOnyen) throws MissingOnyenException {
		String oldOnyen = onyen;
		
		onyen = anOnyen;
		// again this will void a getter call when properties are redisplayed
		propertyChangeSupport.firePropertyChange("onyen", oldOnyen, onyen);



	}



	@Row(1)
	@ComponentWidth(150)
	@Override
	public String getName() {
		return name;
	}
	@Override
	@Visible(false)
	public void setName(String newVal) {
		name = newVal;
		// System.out.println("name changed to" + newVal);
		// as we are postponing notfications, sending the name change is useful
		 propertyChangeSupport.firePropertyChange("Name", null, newVal);
//		notifyPreconditionChanged();
		// System.out.println("precondition notified");
	}

//	void notifyPreconditionChanged() {
//		propertyChangeSupport.firePropertyChange("this", null, this);
//
//	}

	@Row(2)
	@ComponentWidth(150)
	public double getScore() {
		return score;
	}



	@Visible(false)
	public SakaiProject getProject() {
		return project;
	}



	// Josh: We want to know when a project is set, so I'm adding the project
	// property change event here.
	@Visible(false)
	@Override
	public boolean setProject(SakaiProject newVal) {

		project = newVal;

//		
//		studentPhoto = projectDatabase.getStudentPhoto(onyen, project);
//
//
//		if (studentPhoto != null){
//			photoLabelBeanModel.set("", studentPhoto);
//		} else {
//			photoLabelBeanModel.set(APhotoReader.NO_PHOTO_TITLE, studentPhoto);
//		}

		setColors();


		return true;
	}

	void setColor(String aPath, Color aColor) {
		propertyChangeSupport.firePropertyChange(aPath, null, 
				new Attribute(AttributeNames.CONTAINER_BACKGROUND, aColor));
	}

	@Override
	public void setMultiplierColor() {
		if (projectStepper.isSettingUpProject()) return;
		nextMultiplierColor = projectDatabase.getMultiplierColorer().color(multiplier);
		if (currentMultiplierColor == nextMultiplierColor ) return;
		setColor("Multiplier",  nextMultiplierColor);
		currentMultiplierColor = nextMultiplierColor;
		MultiplierColored.newCase(projectDatabase, projectStepper, project, nextMultiplierColor, multiplier, this);

	}
	@Override
	public void setScoreColor() {
		if (projectStepper.isSettingUpProject()) return;
		nextScoreColor = projectDatabase.getScoreColorer().color(score);
		if (currentScoreColor == nextScoreColor ) return;
		setColor("Score",  nextScoreColor);
		currentScoreColor = nextScoreColor;
		OverallScoreColored.newCase(projectDatabase, projectStepper, project, nextScoreColor, score, this);
	}
	


	
	void setColors() {
		// no incremental updates as score and other properties change during auto grade
		if (projectStepper.isSettingUpProject()) 
		    return;
//		computeNextColors();
//		setGradingFeatureColors();
		setMultiplierColor();
//		setOverallNotesColor();
		setScoreColor();
		
	}
	


@Override
	public void internalSetScore(double newVal) {
//		if (projectStepper.isChanged()) return;
		if (score == newVal) return;
		score = newVal;
		if (!projectStepper.isSettingUpProject()) {
			setScoreColor();
			propertyChangeSupport.firePropertyChange("Score", null, newVal);
//			featureGradeRecorder.setGrade(name, getOnyen(), newVal);

		}
		// do not save it in Josh's spreadsheet
//		featureGradeRecorder.setGrade(name, getOnyen(), newVal);


		
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



	@Override
	public void setScore(double newVal) {
		if (score == newVal) return;
		double oldVal = score;
		internalSetScore(newVal);
		OverallScoreManualChange.newCase (projectDatabase, projectStepper, project, score, this);
		if (totalScoreRecorder != null)

			// if (gradeRecorder != null)
			setGrade(newVal);
		featureGradeRecorder.setGrade(getName(), getOnyen(), newVal);
		OverallScoreSaved.newCase(projectDatabase, projectStepper, project, featureGradeRecorder.getFileName(), score, this);

		
//		featureGradeRecorder.setGrade(name, getOnyen(), newVal);
		NotesGenerator notesGenerator = projectDatabase.getNotesGenerator();
		String newNotes = notesGenerator.totalScoreOverrideNotes(projectStepper, oldVal, newVal);
		projectStepper.setOverallNotes(notesGenerator.appendNotes(
				projectStepper.getOverallNotes(), 
				newNotes));
//				notesGenerator.totalScoreOverrideNotes(projectStepper, oldVal, newVal)));
		OverallScoreOverrideNotes.newCase(projectDatabase, projectStepper, project, newNotes, this);
		projectStepper.setChanged(true);
		projectStepper.setOverallNotesColor();
		

	}

	
	@Row(3)
	@Visible(true)
	@Explanation("Weight based on early or late submission")
	public double getMultiplier() {
		return multiplier;
	}
	
	public void internalSetMultiplier(double newValue) {
		if (newValue == multiplier) return;
		double oldValue = multiplier;
		multiplier = newValue;
		featureGradeRecorder.setEarlyLatePoints(getName(), getOnyen(),
				multiplier);
		MultiplierSaved.newCase(projectDatabase, projectStepper, project, featureGradeRecorder.getFileName(), multiplier, this);
		setMultiplierColor();
		propertyChangeSupport.firePropertyChange("multiplier", oldValue, newValue);
	}
	
	public void setMultiplier(double newValue) {
		double oldVal = multiplier;
		if (oldVal == newValue) return;
		internalSetMultiplier(newValue);
		NotesGenerator notesGenerator = projectDatabase.getNotesGenerator();
		String newNotes = notesGenerator.multiplierOverrideNotes(projectStepper, oldVal, newValue);
		projectStepper.setOverallNotes(notesGenerator.appendNotes(
				projectStepper.getOverallNotes(),
				newNotes));
//				notesGenerator.multiplierOverrideNotes(projectStepper, oldVal, newValue)));
		featureGradeRecorder.saveMultiplier(newValue);
		projectStepper.setChanged(true);
		MultiplierUserChange.newCase(projectDatabase, projectStepper, project, newValue, this);
		MultiplierOverrideNotes.newCase(projectDatabase, projectStepper, project, newNotes, this);
//		String aNotes = projectDatabase.getNotesGenerator().multiplierOverrideNotes(this, oldVal, newValue);
//		String oldOverallNotes = getOverallNotes();
//		String newNotes = oldOverallNotes + " " + aNotes;
//		setOverallNotes(newNotes);
		
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


	
	@Override
	public void computeNextColors() {
//		List<Color> colors = new ArrayList();
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
		ObjectEditor.edit(new AGradedProjectTextOverview());
	}
	

}
