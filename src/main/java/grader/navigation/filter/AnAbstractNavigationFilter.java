package grader.navigation.filter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import grader.assignment.GradingFeatureList;
import grader.sakai.project.ProjectStepper;
import grader.sakai.project.SakaiProjectDatabase;

public abstract class AnAbstractNavigationFilter<ParameterType> implements NavigationFilter<ParameterType>{
//	public static final String NAME = "Grading Status";
	String name;
	ParameterType parameter;
//	GradingStatus parameter = GradingStatus.NOT_FULLY_GRADED;
	PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

//	@Override
//	public abstract boolean includeProject(ProjectStepper aProjectState,
//			SakaiProjectDatabase aDatabase) {
//		GradingStatus actualStatus = calculateGradingStatus(aDatabase);
//		return compatible(parameter, actualStatus);
//	}
//	
//	public static boolean compatible (GradingStatus parameter, GradingStatus actualStatus ) {
//		if (parameter == GradingStatus.ALL) return true;
//		return parameter == actualStatus;		
//	}
	
//	public static GradingStatus calculateGradingStatus (SakaiProjectDatabase aDatabase) {
//		GradingFeatureList gradingFeatures = aDatabase.getGradingFeatures();
//		if (gradingFeatures.isAllGraded())
//			return GradingStatus.FULLY_GRADED;
//		if (!gradingFeatures.isAllAutoGraded())
//			return GradingStatus.NOT_FULLY_AUTO_GRADED;
//		return GradingStatus.NOT_FULLY_GRADED;
//	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public ParameterType getParameter() {
		return parameter;
	}

	@Override
	public void setParameter(ParameterType newValue) {
		Object oldValue = parameter;
		parameter = newValue;
		propertyChangeSupport.firePropertyChange("parameter", oldValue, newValue);
		
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyChangeSupport.addPropertyChangeListener(aListener);
		
	}

}
