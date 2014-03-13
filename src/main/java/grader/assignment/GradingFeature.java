package grader.assignment;

import grader.checkers.FeatureChecker;
import grader.sakai.project.SakaiProject;
import grader.sakai.project.SakaiProjectDatabase;
import util.annotations.Visible;
import util.models.PropertyListenerRegisterer;

import java.awt.Color;
import java.beans.PropertyChangeListener;

public interface GradingFeature extends PropertyListenerRegisterer {
    public String getFeature();

    public double getMax();

    public double getScore();

    public void setScore(double score);

    public void pureSetScore(double score);

    public void initScore(double score);

    public boolean isAutoGradable();

    public void autoGrade();

    public void setProject(SakaiProject aProject);

    void removePropertyChangeListener(PropertyChangeListener aListener);

    public void reset();

    void comment();

    public boolean isExtraCredit();

    public String[] getOutputFiles();

    public void setOutputFiles(String[] outputFiles);

    public String[] getInputFiles();

    public void setInputFiles(String[] inputFiles);

    public String getFeedbackFileName();

    public boolean isGraded();

    public void setGraded(boolean newValue);

    public void firePropertyChange(String aName, Object anOldValue, Object aNewValue);

    public FeatureChecker getFeatureChecker();

    public void setProjectDatabase(SakaiProjectDatabase aProjectDatabase);

    public GradingFeature getLinkedFeature();

    public void setLinkedFeature(GradingFeature aGradingFeature);

    public void pureSetGraded(boolean newValue);
    String getNotes();
    public void setNotes(String notes);
    public boolean isSelected() ;
    public void setSelected(boolean newVal) ;
    public String getResult() ;

	public void setResult(String result) ;

	boolean isRestriction();

	boolean isManual();

	boolean isAutoWithNotFullCredit();

	boolean isAutoNotGraded();

	boolean isManualWithNotFullCredit();

//	Color computeColor();

	boolean isManualOverride();
	String getOutput();
	void setOutput(String newVal);

	boolean isFullCredit();

	boolean isValidate();

	void setFullCredit(boolean newVal);

}
