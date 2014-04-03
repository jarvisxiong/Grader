package grader.navigation.filter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import grader.letter_grade.AScoreToLetterGradeMapper;
import grader.letter_grade.LetterGrade;
import grader.letter_grade.ScoreToLetterGradeMapper;
import grader.sakai.project.ProjectStepper;
import grader.sakai.project.SakaiProjectDatabase;

public class ALetterGradeBasedFilter extends AnAbstractNavigationFilter<LetterGrade>implements NavigationFilter<LetterGrade>{
	public static final String NAME = "Letter Grade";
//	 CoarseLetterGrade parameter = CoarseLetterGrade.C;
	 ScoreToLetterGradeMapper gradeComputer = new AScoreToLetterGradeMapper(); // factory needed!!
//	 PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	 public ALetterGradeBasedFilter() {
		 name = NAME;
		 parameter = LetterGrade.C;
	 }
	

	@Override
	public boolean includeProject(ProjectStepper aProjectState,
			SakaiProjectDatabase aDatabase) {
		LetterGrade grade = gradeComputer.toCoarseLetterGrade(aProjectState.getScore());
		return grade == parameter;
	}


	@Override
	public Object fromString(String aString) {
		// TODO Auto-generated method stub
		return LetterGrade.fromString(aString);
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
