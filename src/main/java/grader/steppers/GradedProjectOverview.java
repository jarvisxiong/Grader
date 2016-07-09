package grader.steppers;

import util.models.LabelBeanModel;
import util.models.PropertyListenerRegisterer;


public interface GradedProjectOverview  extends GradedProjectTextOverview, /*ClearanceManager,*/ PropertyListenerRegisterer/*, PropertyChangeListener*/{
//	public boolean setProject(SakaiProject newVal) ;
////	
////	public void output();
////	
////	public void sources() ;
////	public double getScore() ;
////	public void setScore(double newVal) ;
////	public  void waitForClearance() ;
//	public SakaiProjectDatabase getProjectDatabase() ;
//
//	public void setProjectDatabase(SakaiProjectDatabase aProjectDatabase) ;
//	public void setOnyen(String anOnyen) throws MissingOnyenException ;
////	public boolean setProject(String anOnyen) ;
////	public boolean isAutoRun() ;
////    public void setAutoRun(boolean newVal);
////    public void autoRun() ;
////    public boolean hasMoreSteps();
//	
////	public void setHasMoreSteps(boolean newVal);
//    public SakaiProject getProject();
//    boolean runProjectsInteractively();
//    public void configureNavigationList();

//	boolean preDone();

//	void done();

//	String getNavigationFilter();
//
//	void setNavigationFilter(String newVal);

//	boolean preGetGradingFeatures();

//	boolean preAutoGrade();

//	void autoGrade();

//	GradingFeatureList getGradingFeatures();
//
//	boolean isAllGraded();
//
//	boolean preNext();
//
//	void next();
//
//	boolean prePrevious();
//
//	void previous();
//
//	boolean preRunProjectsInteractively();
//
//	boolean move(boolean forward);
//	public boolean isAutoAutoGrade() ;
//    public void setAutoAutoGrade(boolean newVal) ;
//    public void autoAutoGrade() ;
//
//	void setFrame(Object aFrame);
//
//	Object getFrame();

	LabelBeanModel getPhoto();

	GradedProjectTextOverview getTextOverview();


//	String getName();
//
//	void setName(String newVal);
//
//	void setScore(double newVal);
//
//	double getScore();
//
//	double getMultiplier();
//
//	void internalSetMultiplier(double newValue);
//
//	void setMultiplier(double newValue);
//
//	String getOnyen();
//
//	void internalSetOnyen(String anOnyen) throws MissingOnyenException;

//	void computeNextColors();

//	String getFeedback();
//
//	String getTranscript();
//
//	NavigationSetter getNavigationSetter();
//
//	void validate();
//
//	boolean runProjectsInteractively(String aGoToOnyen) throws MissingOnyenException;
//	

}
