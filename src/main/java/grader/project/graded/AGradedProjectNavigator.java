package grader.project.graded;

import grader.feedback.ScoreFeedback;
import grader.file.FileProxyUtils;
import grader.navigation.NavigationKind;
import grader.navigation.filter.BasicNavigationFilter;
import grader.sakai.project.ProjectStepper;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.spreadsheet.FeatureGradeRecorder;
import grader.trace.MissingOnyenException;
import grader.trace.stepper.HasMoreStepsChanged;
import grader.trace.stepper.NavigationListConfigured;
import grader.trace.stepper.ProceedWhenDoneChanged;
import grader.trace.stepper.ProjectStepperEnded;
import grader.trace.stepper.ProjectStepperStarted;
import grader.trace.stepper.UserNextStep;
import grader.trace.stepper.UserPreviousStep;
import grader.trace.stepper.UserQuit;
import grader.trace.stepper.UserWindowClose;

import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import util.annotations.Column;
import util.annotations.ComponentHeight;
import util.annotations.ComponentWidth;
import util.annotations.Explanation;
import util.annotations.Label;
import util.annotations.Row;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Visible;
import util.misc.Common;
import util.trace.Tracer;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import bus.uigen.uiFrame;
import bus.uigen.attributes.AttributeNames;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public class AGradedProjectNavigator /*extends AClearanceManager*/ implements
		GradedProjectNavigator, WindowListener {
	boolean playMode;
	

	OverviewProjectStepper projectStepper;
	PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	SakaiProjectDatabase projectDatabase;
	String sequenceNumber = "0/0";

//	// ideally this stuff should really be done through property change as
//	// Josh's wrapper does
	FeatureGradeRecorder featureGradeRecorder;
	String onyen = "";

	SakaiProject project;
//	framework.project.Project wrappedProject;
//	// FinalGradeRecorder gradeRecorder;
	boolean hasMoreSteps = true;
//	FinalGradeRecorder totalScoreRecorder;
	boolean manualOnyen;
	String logFile, gradedFile, skippedFile;

	public AGradedProjectNavigator() {

	}
	public void setProjectDatabase(SakaiProjectDatabase aProjectDatabase) {


		projectDatabase = aProjectDatabase;
		// ouch, cast should change once we get rid of old stepper
		projectStepper = (OverviewProjectStepper) projectDatabase.getProjectStepper();
		// gradeRecorder = aProjectDatabase.getGradeRecorder();
//		
		GraderSettingsModel graderSettings = aProjectDatabase.getGraderSettings();
		featureGradeRecorder = aProjectDatabase.getFeatureGradeRecorder();
//		totalScoreRecorder = aProjectDatabase.getTotalScoreRecorder();
//		registerWithGradingFeatures();
		logFile = aProjectDatabase.getAssigmentDataFolder().getLogFileName();
		gradedFile = aProjectDatabase.getAssigmentDataFolder()
				.getGradedIdFileName();
		skippedFile = aProjectDatabase.getAssigmentDataFolder()
				.getSkippedIdFileName();
//		ObjectEditor.setMethodAttribute(AGradedProjectNavigator.class, "togglePlayPause", AttributeNames.LABEL, computeTogglePlayPauseLabel());

		// configuteNavigationList does this
//		setCurrentOnyenIndex(0);

//		ings.getNavigationSetter().getNavigationFilterSetter().addPropertyChangeListener(this);
//		getNavigationSetter().getNavigationFilterSetter().addPropertyChangeListener(this);
		
		// recordWindows(); // the first project does not wait so we need to
		// record here

	}
	// we intercept window closes
	public static void addWindowListener(Object aFrame, WindowListener aListener) {
		if (aFrame instanceof Frame) {
			((Frame) aFrame).addWindowListener(aListener);
		} else if (aFrame instanceof JFrame) {
			((JFrame) aFrame).addWindowListener(aListener);
		} else if (aFrame instanceof uiFrame) {
			
			((OEFrame) aFrame).addWindowListener(aListener);
			((OEFrame) aFrame).setAutoExitEnabled(false);
		}
		
	}
	@Override
	public void setFrame(Object aFrame) {
		
		addWindowListener(aFrame, this);
	}



	@Override
	public void internalSetOnyen(String anOnyen) throws MissingOnyenException {
		// project = projectDatabase.getProject(anOnyen);
		String oldOnyen = onyen;
		int onyenIndex = onyens.indexOf(anOnyen);
		if (onyenIndex < 0) {
			Tracer.error("Student:" + anOnyen + " does not exist in specified onyen range");
			throw new MissingOnyenException(anOnyen, this);
//			return;
		}
//		currentOnyenIndex = onyenIndex;
		setCurrentOnyenIndex( onyenIndex);
		maybeSaveState();
		redirectProject();
		boolean retVal = projectStepper.setProject(anOnyen);
		if (!retVal) {
			onyen = oldOnyen;
			propertyChangeSupport.firePropertyChange("onyen", null, onyen);
			return;
		}
		// again this will void a getter call when properties are redisplayed
		propertyChangeSupport.firePropertyChange(oldOnyen, null, onyen);



	}



	void notifyPreconditionChanged() {
		propertyChangeSupport.firePropertyChange("this", null, this);

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
		

	}

	


	// Josh: We want to know when a project is set, so I'm adding the project
	// property change event here.
	@Visible(false)
	public boolean setProject(SakaiProject newVal) {

		return true;
	}
	@Override
	public boolean shouldVisit() {
		if (manualOnyen )
			return true;
		GraderSettingsModel graderSettingsModel = projectDatabase.getGraderSettings();
		if (graderSettingsModel == null) return true;
		if (graderSettingsModel.getNavigationSetter().getNavigationKind() != NavigationKind.MANUAL)
			return true;
		BasicNavigationFilter navigationFilter = projectDatabase.getNavigationFilter();
		return navigationFilter.includeProject(projectStepper, projectDatabase);
	}
	


	boolean settingUpProject;
	

	public boolean preSkip() {
		return !preProceed();
	}


	
	boolean noNextFilteredRecords;

	@Override
	public boolean preNext() {
		return /*!noNextFilteredRecords /*&& preProceed()* && */
				currentOnyenIndex < onyens.size() - 1;
		// this does not make sense, next is a stronger condition than next

		// return !preDone() && nextOnyenIndex < onyens.size() - 1 ;
	}
	@Row(0)
	@Column(0)
	@ComponentWidth(100)
	@Override
	@Explanation("Go to next student after saving current student changes.")
	public synchronized void next() {
//		if (!preProceed()) {
//			JOptionPane.showMessageDialog(null, "Cannot proceed as assignment not completely graded. Turn off the proceed when finished box if you do not want this check.");
//			return ;
//		}

//		try {
//			// Common.appendText(logFile, getOnyen() + " Skipped " +
//			// Common.currentTimeAsDate() + "\n");
//			Common.appendText(skippedFile, projectStepper.getOnyen() + "\n");
//			File file = new File(logFile);
//			if (file.exists())
//				file.createNewFile();
//			List<String> list = FileProxyUtils.toList(file);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		UserNextStep.newCase(projectDatabase, projectStepper, project, this);
		 userMove(true);
		// should put person in skipped list

	}
	boolean noPreviousFilteredRecords;

	@Override
	public boolean prePrevious() {
		return !noPreviousFilteredRecords && /*preProceed() &&*/ currentOnyenIndex > 0;
	}
	@Row(0)
	@Column(1)
	@ComponentWidth(100)
	@Override
	@Explanation("Go to previous student after saving current student changes.")
	public synchronized void previous() {
		
		
		// should have a user move

		// try {
		// Common.appendText(logFile, onyen + " Skipped " +
		// Common.currentTimeAsDate() + "\n");
		// Common.appendText(skippedFile, onyen + "\n");
		// List<String> list = FileProxyUtils.toList(new File(logFile));
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		UserPreviousStep.newCase(projectDatabase, projectStepper, project, this);

		 userMove(false);
		// should put person in skipped list

	}
	String computeTogglePlayPauseLabel() {
		return playMode?"Pause":"Play";	
	}
	@Override
	@Visible(false)
	public boolean isPlayMode() {
		return playMode;
	}
	@Override
	public void setPlayMode(boolean newValue) {
		if (newValue == playMode) return;
		if (!preTogglePlayPause()) return;
		this.playMode = newValue;
		ObjectEditor.setMethodAttribute(AGradedProjectNavigator.class, "togglePlayPause", AttributeNames.LABEL, computeTogglePlayPauseLabel());

		
	}
	@Override
	public boolean preTogglePlayPause() {
		return projectDatabase.getGraderSettings().getNavigationSetter().getNavigationKind() != NavigationKind.MANUAL;
	}
	@Row(0)
	@Column(2)
	@ComponentWidth(100)
	@Label("Pause") // initially play mode will be true for manual
	@Visible(false)
	@Override
	public void togglePlayPause() {
		setPlayMode(!playMode);
//		playMode = !playMode;	
//		ObjectEditor.setMethodAttribute(AGradedProjectNavigator.class, "togglePlayPause", AttributeNames.LABEL, computeTogglePlayPauseLabel());
		
	}
	

	
	boolean proceedWhenDone = true;
//	@Visible(false)
	@Row(1)
	@Column(0)
	@Override
	@Label("Stop If Not Done")
	@Explanation("Determines if next or previous command is allowed when current student is not completely graded")
	public boolean isProceedWhenDone() {
		return proceedWhenDone;
		
	}
	@Override
	public void toggleProceedWhenDone() {
		proceedWhenDone = !proceedWhenDone;
	}
	@Override
	public void setProceedWhenDone(boolean newVal) {
		boolean oldVal = proceedWhenDone;
		this.proceedWhenDone = newVal;
		propertyChangeSupport.firePropertyChange("ProceedWhenDone", oldVal, newVal);
		ProceedWhenDoneChanged.newCase(projectDatabase, projectStepper, project, newVal, this);
	}

	public boolean preProceed() {
		// if manuelOnyen then we will go to next step
		// cannot proceed if it is not all graded
		// why check for manualOnyen?
		// removing first clause
		return //(hasMoreSteps || manualOnyen) && 
				projectStepper.isAllGraded() || !proceedWhenDone || projectDatabase.getGraderSettings().getNavigationSetter().getNavigationKind() != NavigationKind.MANUAL;
	}
	@Override
	@Visible(false)
	public boolean hasMoreSteps() {
		return hasMoreSteps;
	}

	@Override
	@Visible(false)
	public void setHasMoreSteps(boolean newVal) {
		if (hasMoreSteps == newVal)
			return;
		hasMoreSteps = newVal;
		if (hasMoreSteps == false)
			writeScores(projectStepper);

		notifyPreconditionChanged();
		HasMoreStepsChanged.newCase(projectDatabase, projectStepper, project, newVal, this);

	}

	

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
	@Override
	public void resetNoFilteredRecords() {
		noNextFilteredRecords = false;
		noPreviousFilteredRecords = false;
	}




	@Override
	@Visible(false)
	public SakaiProjectDatabase getProjectDatabase() {
		// TODO Auto-generated method stub
		return projectDatabase;
	}

	List<String> onyens;
	int currentOnyenIndex = -1; // negative so when it is set to zero a proeprty change is fired for navigation status
	int filteredOnyenIndex = 0;
//	String nextOnyen;
//
	@Override
	@Visible(false)
	public void configureNavigationList() {
		onyens = projectDatabase.getOnyenNavigationList();
		NavigationListConfigured.newCase(projectDatabase, projectStepper, project, onyens, this);
		setCurrentOnyenIndex( 0);
//		hasMoreSteps = true;
		setHasMoreSteps(true);
		

		
	}

	

	@Override
	public boolean preDone() {
		// return preProceed();
		// we are done but do not have another step
		return preProceed() && !preNext();
	}
	void maybeSaveState() {
		// josh's code
				// no serialization otherwise
				if (projectStepper.isChanged() || 
						!featureGradeRecorder.logSaved()) {
				featureGradeRecorder.finish();
				projectStepper.setChanged(false);
				}

				// my original code
				projectDatabase.resetIO();
				projectDatabase.clearWindows();
	}
	void redirectProject() {
		projectDatabase.initIO();
		projectDatabase.recordWindows();
	}
	
	void setFailedMoveFlags(boolean forward) {
		if (forward)
			noNextFilteredRecords = true;
		else
			noPreviousFilteredRecords = true;
	}
	
	void setSuccessfulMoveFlags(boolean forward) {
		if (forward)
			noNextFilteredRecords = false;
		else
			noPreviousFilteredRecords = false;
	}
	
	boolean checkLeave() {
		if (!preProceed() && !projectStepper.isSettingUpProject()) {
			JOptionPane.showMessageDialog(null, "Cannot proceed as assignment not completely graded. Turn off the Stop-If-Not-Done checkbox if you do not want this check.");
			return false;
		}
		return true;
	}
	
	void userMove(boolean forward) {
//		if (!preProceed() && !projectStepper.isSettingUpProject()) {
//			JOptionPane.showMessageDialog(null, "Cannot proceed as assignment not completely graded. Turn off the Stop-If-Not-Done checkbox if you do not want this check.");
//			return ;
//		}
		if (!checkLeave()) return;
		move(forward);
	}

	@Override
	@Visible(false)
	public synchronized boolean move(boolean forward) {
		
		maybeSaveState();
//		// josh's code
//		// no serialization otherwise
//		if (changed || 
//				!featureGradeRecorder.logSaved()) 
//		featureGradeRecorder.finish();
//
//		// my original code
//		projectDatabase.resetIO();
//		projectDatabase.clearWindows();
		if (forward) {
//			currentOnyenIndex++;
			setCurrentOnyenIndex(currentOnyenIndex+1);

			if (currentOnyenIndex >= onyens.size()) {
				hasMoreSteps = false;
				return false;
			}
		} else {
//			currentOnyenIndex--;
			setCurrentOnyenIndex(currentOnyenIndex-1);
			if (currentOnyenIndex < 0) {
				hasMoreSteps = false;
				return false;
			}

		}
		redirectProject();
		String anOnyen = onyens.get(currentOnyenIndex);
		SakaiProject aProject = projectDatabase.getProject(anOnyen);
//		redirectProject();
//		projectDatabase.initIO();
//		projectDatabase.recordWindows();
		boolean projectSet = projectStepper.setProject(anOnyen);
		if (!projectSet) {
			boolean retVal = move(forward);
			if (!retVal && filteredOnyenIndex != currentOnyenIndex) {
//				currentOnyenIndex = filteredOnyenIndex;
				setCurrentOnyenIndex(filteredOnyenIndex);
				try {
					projectStepper.internalSetOnyen(onyens.get(filteredOnyenIndex));
				} catch (MissingOnyenException e) {
					e.printStackTrace(); // this should never be executed
				}
				String message = "Cannot move as no more records that satisfy selection condition. You can change the filter settings.";
				Tracer.error(message);
				JOptionPane.showMessageDialog(null, message);
				setFailedMoveFlags(forward);
			} else {
				setFilteredOnyenIndex(filteredOnyenIndex);
//				filteredOnyenIndex = currentOnyenIndex;
				setSuccessfulMoveFlags(forward);
			}
			return retVal;
		}
//		filteredOnyenIndex = currentOnyenIndex;
		setFilteredOnyenIndex(filteredOnyenIndex);
		return true;
			
		// these two steps should go into setProject unless there is something
		// subttle here, specially as the stepProject step below is commented
		// put
		// if (isAutoRun())
		// projectDatabase.runProject(anOnyen, aProject);
		// if (isAutoAutoGrade())
		// autoGrade();

		// setProject(anOnyen);
	}

	@Override
//	@Column(2)
	@Visible(false)
	@ComponentWidth(100)
	public synchronized void done() {

		if (manualOnyen)
			writeScores(projectStepper);
		try {
			// Common.appendText(logFile, onyen + " Skipped " +
			// Common.currentTimeAsDate() + "\n\r");
			Common.appendText(gradedFile, projectStepper.getOnyen() + "\n");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		move(true);
	

	}
	@Override
	public int getCurrentOnyenIndex() {
		return currentOnyenIndex;
	}
	@Override
	public void setCurrentOnyenIndex(int newValue) {
		if (newValue == currentOnyenIndex) return;
		this.currentOnyenIndex = newValue;
		setSequenceNumber();
		
	}
	
	@Override
	public int getFilteredOnyenIndex() {
		return filteredOnyenIndex;
	}
	@Override
	public void setFilteredOnyenIndex(int filteredOnyenIndex) {
		this.filteredOnyenIndex = filteredOnyenIndex;
	}


	
	String computeSequenceNumber() {
		return "" + (currentOnyenIndex + 1) + "/" + onyens.size();
	}
	void setSequenceNumber() {
		String oldValue = sequenceNumber;
		String newValue = computeSequenceNumber() ;
		sequenceNumber = newValue;
		propertyChangeSupport.firePropertyChange("sequenceNumber", oldValue, newValue);
		
		
	}
	
	@Override
	@Row(2)
	@Column(0)
	@ComponentWidth(30)
	@ComponentHeight(27)
	@Label("Navigation Distance:")
	@Explanation("How far into onyen range have you travelled - the sequence number of the current record.")
	public String getSequenceNumber() {
		return sequenceNumber;
	}
	@Visible(false)
	@ComponentWidth(100)
	@Row(0)
	@Column(3)
	@Override
	public void save() {
		maybeSaveState();
//		System.exit(0);
	}
	
//	@Visible(false)
	@ComponentWidth(100)
	@Row(0)
	@Column(2)
	@Override
	@Explanation("Quit session after saving current student changes.")
	public void quit() {
		if (!checkLeave()) return;
		maybeSaveState();
		UserQuit.newCase(projectDatabase, projectStepper, project, this);
		ProjectStepperEnded.newCase(projectDatabase, projectStepper, this);

		System.exit(0);
	}
	
	
	
	
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		if (!checkLeave()) return;
		maybeSaveState();
		UserWindowClose.newCase(projectDatabase, projectStepper, project, this);
		ProjectStepperEnded.newCase(projectDatabase, projectStepper, this);
		System.exit(0);
		
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		ObjectEditor.edit(new AGradedProjectNavigator());
	}
}
