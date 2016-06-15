package grader.steppers;

import grader.photos.APhotoReader;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.trace.settings.MissingOnyenException;
import grader.trace.steppers.PhotoLabelSet;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.Icon;

import util.annotations.Column;
import util.annotations.ComponentHeight;
import util.annotations.ComponentWidth;
import util.annotations.Explanation;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Visible;
import util.models.ALabelBeanModel;
import util.models.LabelBeanModel;
import bus.uigen.ObjectEditor;

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
	@ComponentHeight(100)
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
	@Override
	public double getSourcePoints() {
		// TODO Auto-generated method stub
		return textOverview.getSourcePoints();
	}
	@Override
	public void setSourcePoints(double newValue) {
		textOverview.setSourcePoints(newValue);		
	}
	@Override
	public void internalSetSourcePoints(double newValue) {
		textOverview.internalSetSourcePoints(newValue);
		
	}
	@Override
	public String getDisplayedOnyen() {
		// TODO Auto-generated method stub
		return textOverview.getDisplayedOnyen();
	}
	@Override
	public String getDisplayedName() {
		// TODO Auto-generated method stub
		return textOverview.getDisplayedName();
	}
	

}
