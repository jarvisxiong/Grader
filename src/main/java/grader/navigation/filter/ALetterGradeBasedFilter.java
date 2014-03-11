package grader.navigation.filter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import grader.letter_grade.AScoreToCoarseLetterGradeMapper;
import grader.letter_grade.CoarseLetterGrade;
import grader.letter_grade.ScoreToCoarseLetterGradeMapper;
import grader.sakai.project.ProjectStepper;
import grader.sakai.project.SakaiProjectDatabase;

public class ALetterGradeBasedFilter extends AnAbstractNavigationFilter<CoarseLetterGrade>implements NavigationFilter<CoarseLetterGrade>{
	public static final String NAME = "Letter Grade";
//	 CoarseLetterGrade parameter = CoarseLetterGrade.C;
	 ScoreToCoarseLetterGradeMapper gradeComputer = new AScoreToCoarseLetterGradeMapper(); // factory needed!!
//	 PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	 public ALetterGradeBasedFilter() {
		 name = NAME;
		 parameter = CoarseLetterGrade.C;
	 }
	

	@Override
	public boolean includeProject(ProjectStepper aProjectState,
			SakaiProjectDatabase aDatabase) {
		CoarseLetterGrade grade = gradeComputer.toCoarseLetterGrade(aProjectState.getScore());
		return grade == parameter;
	}
	
//	@Override
//	public String getName() {
//		return NAME;
//	}
//
//	@Override
//	public CoarseLetterGrade getParameter() {
//		return parameter;
//	}
//
//	@Override
//	public void setParameter(CoarseLetterGrade newValue) {
//		parameter = newValue;
//		
//	}
//	@Override
//	public void setParameter(GradingStatus newValue) {
//		Object oldValue = parameter;
//		parameter = newValue;
//		propertyChangeSupport.firePropertyChange("parameter", oldValue, newValue);
//		
//	}

//	@Override
//	public void addPropertyChangeListener(PropertyChangeListener aListener) {
//		propertyChangeSupport.addPropertyChangeListener(aListener);
//		
//	}

}
