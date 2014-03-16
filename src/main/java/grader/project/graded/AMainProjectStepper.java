package grader.project.graded;

import grader.assignment.GradingFeature;
import grader.assignment.GradingFeatureList;
import grader.sakai.project.MissingOnyenException;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.navigation.NavigationSetter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import util.annotations.Label;
import util.annotations.Row;
import util.annotations.Visible;
import util.models.LabelBeanModel;

public class AMainProjectStepper implements MainProjectStepper {
	OverviewProjectStepper overviewProjectStepper;

	public void setProjectDatabase(SakaiProjectDatabase aProjectDatabase) {
		overviewProjectStepper = new AnOverviewProjectStepper();
		overviewProjectStepper.setProjectDatabase(aProjectDatabase);
	}
	@Row(0)
//	@Visible(true)
	@Override
	public OverviewProjectStepper getOverviewProjectStepper() {
		return overviewProjectStepper;
	}
	@Override
	public void setOverviewProjectStepper(
			OverviewProjectStepper overviewProjectStepper) {
		this.overviewProjectStepper = overviewProjectStepper;
	}

	public void proceed() {
		overviewProjectStepper.proceed();
	}

	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		overviewProjectStepper.addPropertyChangeListener(aListener);
	}

	public boolean setProject(SakaiProject newVal) {
		return overviewProjectStepper.setProject(newVal);
	}

	public void output() {
		overviewProjectStepper.output();
	}

	public void sources() {
		overviewProjectStepper.sources();
	}

	@Visible(false) 
	public double getScore() {
		return overviewProjectStepper.getScore();
	}

	public void setScore(double newVal) {
		overviewProjectStepper.setScore(newVal);
	}

	public void waitForClearance() {
		overviewProjectStepper.waitForClearance();
	}
	@Override
	@Visible(false) 
 public SakaiProjectDatabase getProjectDatabase() {
		return overviewProjectStepper.getProjectDatabase();
	}

	

	public void setOnyen(String anOnyen) throws MissingOnyenException {
		overviewProjectStepper.setOnyen(anOnyen);
	}
	
	public boolean setProject(String anOnyen) {
		return overviewProjectStepper.setProject(anOnyen);
	}
	
	@Visible(false) 
	public boolean isAutoRun() {
		return overviewProjectStepper.isAutoRun();
	}

	public void setAutoRun(boolean newVal) {
		overviewProjectStepper.setAutoRun(newVal);
	}

	public void autoRun() {
		overviewProjectStepper.autoRun();
	}

	public boolean hasMoreSteps() {
		return overviewProjectStepper.hasMoreSteps();
	}

	public void setHasMoreSteps(boolean newVal) {
		overviewProjectStepper.setHasMoreSteps(newVal);
	}

@Visible(false) 
	public SakaiProject getProject() {
		return overviewProjectStepper.getProject();
	}

	public boolean runProjectsInteractively() {
		return overviewProjectStepper.runProjectsInteractively();
	}

	public void configureNavigationList() {
		overviewProjectStepper.configureNavigationList();
	}

	public boolean preDone() {
		return overviewProjectStepper.preDone();
	}

	public void done() {
		overviewProjectStepper.done();
	}

	@Visible(false) 
	public boolean pregetGradingFeatures() {
		return overviewProjectStepper.preGetGradingFeatures();
	}

	public boolean preAutoGrade() {
		return overviewProjectStepper.preAutoGrade();
	}

	public void autoGrade() {
		overviewProjectStepper.autoGrade();
	}
	@Row(1)
	public GradingFeatureList getGradingFeatures() {
		return overviewProjectStepper.getGradingFeatures();
	}

	@Visible(false)
	public boolean isAllGraded() {
		return overviewProjectStepper.isAllGraded();
	}

	public boolean preNext() {
		return overviewProjectStepper.preNext();
	}

	public void next() {
		overviewProjectStepper.next();
	}

	public boolean prePrevious() {
		return overviewProjectStepper.prePrevious();
	}

	public void previous() {
		overviewProjectStepper.previous();
	}

	public boolean preRunProjectsInteractively() {
		return overviewProjectStepper.preRunProjectsInteractively();
	}

	public boolean move(boolean forward) {
		return overviewProjectStepper.move(forward);
	}

	@Visible(false)
	public boolean isAutoAutoGrade() {
		return overviewProjectStepper.isAutoAutoGrade();
	}

	public void propertyChange(PropertyChangeEvent evt) {
		overviewProjectStepper.propertyChange(evt);
	}

	public void setAutoAutoGrade(boolean newVal) {
		overviewProjectStepper.setAutoAutoGrade(newVal);
	}

	public void autoAutoGrade() {
		overviewProjectStepper.autoAutoGrade();
	}

	public void setFrame(Object aFrame) {
		overviewProjectStepper.setFrame(aFrame);
	}

	@Visible(false) 
	public Object getFrame() {
		return overviewProjectStepper.getFrame();
	}

	@Visible(false) 
	public LabelBeanModel getPhoto() {
		return overviewProjectStepper.getPhoto();
	}

	@Visible(false) 
	public String getFeedback() {
		return overviewProjectStepper.getFeedback();
	}
	@Row(4)
	public String getTranscript() {
		return overviewProjectStepper.getTranscript();
	}

	@Visible(false) 
	public NavigationSetter getNavigationSetter() {
		return overviewProjectStepper.getNavigationSetter();
	}

	public void validate() {
		overviewProjectStepper.validate();
	}

	public boolean runProjectsInteractively(String aGoToOnyen)
			throws MissingOnyenException {
		return overviewProjectStepper.runProjectsInteractively(aGoToOnyen);
	}

	public void setName(String newVal) {
		overviewProjectStepper.setName(newVal);
	}

	@Visible(false) 
	public String getName() {
		return overviewProjectStepper.getName();
	}

	public void resetNoFilteredRecords() {
		overviewProjectStepper.resetNoFilteredRecords();
	}

	@Visible(false) 
	public String getOnyen() {
		return overviewProjectStepper.getOnyen();
	}

	public void setOverallNotes(String newVal) {
		overviewProjectStepper.setOverallNotes(newVal);
	}

	@Visible(false) 
	public double getMultiplier() {
		return overviewProjectStepper.getMultiplier();
	}

	@Visible(false) 
	public String getOverallNotes() {
		return overviewProjectStepper.getOverallNotes();
	}

	public void internalSetOnyen(String anOnyen) throws MissingOnyenException {
		overviewProjectStepper.internalSetOnyen(anOnyen);
	}

	public void setMultiplier(double newValue) {
		overviewProjectStepper.setMultiplier(newValue);
	}

	@Visible(false)
	public boolean isProceedWhenDone() {
		return overviewProjectStepper.isProceedWhenDone();
	}

	public void toggleProceedWhenDone() {
		overviewProjectStepper.toggleProceedWhenDone();
	}

	public void internalSetMultiplier(double newValue) {
		overviewProjectStepper.internalSetMultiplier(newValue);
	}

	@Visible(false) 
	public String getSequenceNumber() {
		return overviewProjectStepper.getSequenceNumber();
	}

	public void computeNextColors() {
		overviewProjectStepper.computeNextColors();
	}

	public void setComputedFeedback() {
		overviewProjectStepper.setComputedFeedback();
	}

	public void setStoredFeedback() {
		overviewProjectStepper.setStoredFeedback();
	}

	public void setStoredOutput() {
		overviewProjectStepper.setStoredOutput();
	}

	@Visible(false) 
	public GradingFeature getSelectedGradingFeature() {
		return overviewProjectStepper.getSelectedGradingFeature();
	}

	public void internalSetManualNotes(String newVal) {
		overviewProjectStepper.internalSetManualNotes(newVal);
	}

	public void internalSetResult(String newVal) {
		overviewProjectStepper.internalSetResult(newVal);
	}

	public void internalSetAutoNotes(String newVal) {
		overviewProjectStepper.internalSetAutoNotes(newVal);
	}

	public void internalSetComments(String newVal) {
		overviewProjectStepper.internalSetComments(newVal);
	}

	public void setColors() {
		overviewProjectStepper.setColors();
	}

	@Visible(false)
	public boolean isChanged() {
		return overviewProjectStepper.isChanged();
	}

	public void setChanged(boolean changed) {
		overviewProjectStepper.setChanged(changed);
	}

	public void setComputedScore() {
		overviewProjectStepper.setComputedScore();
	}

	@Visible(false)
	public boolean isSettingUpProject() {
		return overviewProjectStepper.isSettingUpProject();
	}

	public void setSettingUpProject(boolean settingUpProject) {
		overviewProjectStepper.setSettingUpProject(settingUpProject);
	}

	public boolean shouldVisit() {
		return overviewProjectStepper.shouldVisit();
	}

	public void setInternalScore(double newVal) {
		overviewProjectStepper.setInternalScore(newVal);
	}

	public void setMultiplierColor() {
		overviewProjectStepper.setMultiplierColor();
	}

	public void setScoreColor() {
		overviewProjectStepper.setScoreColor();
	}

	public void setOverallNotesColor() {
		overviewProjectStepper.setOverallNotesColor();
	}

	public boolean runAttempted() {
		return overviewProjectStepper.runAttempted();
	}

	@Visible(false) 
	public int getCurrentOnyenIndex() {
		return overviewProjectStepper.getCurrentOnyenIndex();
	}

	public void setCurrentOnyenIndex(int currentOnyenIndex) {
		overviewProjectStepper.setCurrentOnyenIndex(currentOnyenIndex);
	}

	@Visible(false) 
	public int getFilteredOnyenIndex() {
		return overviewProjectStepper.getFilteredOnyenIndex();
	}

	public void setFilteredOnyenIndex(int filteredOnyenIndex) {
		overviewProjectStepper.setFilteredOnyenIndex(filteredOnyenIndex);
	}
	@Row(2)
	@Override
	public String getAutoNotes() {
		return overviewProjectStepper.getAutoNotes();
	}
	@Row(3)
	@Override
	public String getManualNotes() {
		// TODO Auto-generated method stub
		return overviewProjectStepper.getManualNotes();
	}

	@Override
	public void setManualNotes(String newVal) {
		overviewProjectStepper.setManualNotes(newVal);
		
	}

	@Override
	public boolean preGetGradingFeatures() {
		return overviewProjectStepper.preGetGradingFeatures();
	}
	@Override
	@Visible(false)
	public String getSource() {
		return overviewProjectStepper.getSource();
	}
	@Override
	public void internalSetSource(String newValue) {
		
		overviewProjectStepper.internalSetSource(newValue);
	}
	@Override
	@Visible(true)
	public void run() {
		overviewProjectStepper.run();
	}

}
