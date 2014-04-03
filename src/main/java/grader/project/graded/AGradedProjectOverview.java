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
import grader.trace.settings.MissingOnyenException;
import grader.trace.stepper.overview.PhotoLabelSet;

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
	OverviewProjectStepper projectStepper;

	SakaiProjectDatabase projectDatabase;

	SakaiProject project;

	Icon studentPhoto;
	LabelBeanModel photoLabelBeanModel;

	public AGradedProjectOverview() {
		photoLabelBeanModel = new ALabelBeanModel("");
		textOverview = new AGradedProjectTextOverview();
//		addPropertyChangeListener(this); // listen to yourself to see if you have changed
	}
	public void setProjectDatabase(SakaiProjectDatabase aProjectDatabase) {
		projectDatabase = aProjectDatabase;
		textOverview.setProjectDatabase(aProjectDatabase);
		// gradeRecorder = aProjectDatabase.getGradeRecorder();

	}




	// Josh: We want to know when a project is set, so I'm adding the project
	// property change event here.
	@Visible(false)
	@Override
	public boolean setProject(SakaiProject newVal) {

		project = newVal;

		setName(project.getStudentAssignment().getStudentName());

		
		studentPhoto = projectDatabase.getStudentPhoto(textOverview.getOnyen(), project);


		if (studentPhoto != null){
			photoLabelBeanModel.set("", studentPhoto);
		} else {
			photoLabelBeanModel.set(APhotoReader.NO_PHOTO_TITLE, studentPhoto);
		}

		PhotoLabelSet.newCase(projectDatabase, projectStepper, project, photoLabelBeanModel, this);
		return true;
	}



	boolean settingUpProject;

	
	@Column(0)
	@ComponentHeight(80)
	@ComponentWidth(80)
	@Override
	@Explanation("If no photo, you need to populate photo directory in AssignmentsData folder")
	public LabelBeanModel getPhoto() {
		return photoLabelBeanModel;
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
