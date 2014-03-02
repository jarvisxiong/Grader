package grader.sakai.project;

import java.util.Collection;
import java.util.List;
import java.util.Set;















import javax.swing.Icon;

import framework.grading.ProjectRequirements;
import framework.grading.testing.Checkable;
import grader.assignment.AssignmentDataFolder;
import grader.assignment.GradingFeature;
import grader.assignment.GradingFeatureList;
import grader.colorers.Colorer;
import grader.feedback.AutoFeedback;
import grader.feedback.ManualFeedback;
import grader.feedback.ScoreFeedback;
import grader.feedback.SourceDisplayer;
import grader.photos.PhotoReader;
import grader.project.Project;
import grader.sakai.BulkAssignmentFolder;
import grader.sakai.GenericStudentAssignmentDatabase;
import grader.sakai.StudentCodingAssignment;
import grader.spreadsheet.FeatureGradeRecorder;
import grader.spreadsheet.FinalGradeRecorder;

public interface SakaiProjectDatabase {
	public BulkAssignmentFolder getBulkAssignmentFolder();
	
	public AssignmentDataFolder getAssigmentDataFolder() ;
	
	public SakaiProject getProject(String aName) ;
	public Set<String> getOnyens() ;
	
	public Collection<SakaiProject> getProjects();
	public SakaiProject runProject(String anOnyen);
	public void runProjectInteractively(String anOnyen);
	
	public void runProjectInteractively(String anOnyen, ProjectStepper projectStepper) ;
	public void displayOutput();
	public void runProjectsInteractively();

	FinalGradeRecorder getGradeRecorder();
	public GradingFeatureList getGradingFeatures();

	FeatureGradeRecorder getFeatureGradeRecorder();
	public void setGradeRecorder(FinalGradeRecorder gradeRecorder) ;

	public void setFeatureGradeRecorder(FeatureGradeRecorder featureGradeRecorder) ;
	SakaiProject runProject(String anOnyen, SakaiProject aProject);

	void resetRunningProject(SakaiProject aProject);
	public FinalGradeRecorder getTotalScoreRecorder() ;
	public void  setTotalScoreRecorder(FinalGradeRecorder newVal) ;

	public AutoFeedback getAutoFeedback() ;
	
	public ManualFeedback getManualFeedback();
	
	public ScoreFeedback getScoreFeedback() ;

	public SourceDisplayer getSourceDisplayer() ;
	void initIO();
	
		
	void recordWindows();
	
	void resetIO();
	void clearWindows();
	public List<String> getOnyenNavigationList(SakaiProjectDatabase aSakaiProjectDatabase);
	public List<String> getOnyenNavigationList();
	void nonBlockingRunProjectsInteractively();
	public String getNavigationFilter() ;

	public void setNavigationFilter(String navigationFilter) ;

	void startProjectStepper();

	public Object displayProjectStepper(ProjectStepper aProjectStepper) ;

	GenericStudentAssignmentDatabase<StudentCodingAssignment> getStudentAssignmentDatabase();

	 public ProjectStepper getProjectStepper() ;

	public void setProjectStepper(ProjectStepper projectStepper) ;
	ProjectStepper getOrCreateProjectStepper();

	void addProjectRequirements(ProjectRequirements requirements);

	ProjectRequirements getProjectRequirements();

	Checkable getRequirement(GradingFeature aGradingFeature);

	PhotoReader getPhotoReader();

	void setPhotoReader(PhotoReader pictureReader);

	String getAssignmentsDataFolderName();

	void setAssignmentsDataFolderName(String assignmentsDataFolderName);

	Colorer<GradingFeature> getGradingFeatureColorer();

	void setGradingFeatureColorer(
			Colorer<GradingFeature> gradingFeatureColorComputer);

	Icon getStudentPhoto(String anOnyen, SakaiProject aProject);
	public Colorer<Double> getScoreColorer() ;
	public void setScoreColorer(Colorer<Double> scoreColorer) ;

	public Colorer<Double> getMultiplierColorer() ;

	public void setMultiplierColorer(Colorer<Double> multiplierColorer) ;

	public Colorer<String> getOverallNotesColorer();

	public void setOverallNotesColorer(Colorer<String> overallNotesColorer) ;





}
