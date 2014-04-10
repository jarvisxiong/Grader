package grader.navigation.filter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import util.annotations.Explanation;
import grader.assignment.GradingFeatureList;
import grader.sakai.project.ProjectStepper;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.settings.GraderSettingsModelSelector;
import grader.trace.settings.GradingStatusUserChange;
import grader.trace.settings.NavigationParameterChange;

@Explanation("Student record selection based on whether they have been been graded fully or automatically.")
public class AGradingStatusFilter extends AnAbstractNavigationFilter<GradingStatus> implements NavigationFilter<GradingStatus>{
	public static final String NAME = "Grading Status";
//	GradingStatus parameter = GradingStatus.NOT_FULLY_GRADED;
//	PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	
	public AGradingStatusFilter() {
		name = NAME;
		parameter =  GradingStatus.ALL;

	}

	@Override
	public boolean includeProject(ProjectStepper aProjectState,
			SakaiProjectDatabase aDatabase) {
		GradingStatus actualStatus = calculateGradingStatus(aDatabase);
		return compatible(parameter, actualStatus);
	}
	
	public static boolean compatible (GradingStatus parameter, GradingStatus actualStatus ) {
		if (parameter == GradingStatus.ALL) return true;
		return parameter == actualStatus;		
	}
	
	public static GradingStatus calculateGradingStatus (SakaiProjectDatabase aDatabase) {
		GradingFeatureList gradingFeatures = aDatabase.getGradingFeatures();
		if (gradingFeatures.isAllGraded())
			return GradingStatus.FULLY_GRADED;
		if (!gradingFeatures.isAllAutoGraded())
			return GradingStatus.NOT_FULLY_AUTO_GRADED;
		return GradingStatus.NOT_FULLY_GRADED;
	}
	@Override
	public void setParameter(GradingStatus newValue) {
		super.setParameter(newValue);
		GraderSettingsModel settingsModel = GraderSettingsModelSelector.getGraderSettingsModel();
		if (settingsModel != null && GraderSettingsModelSelector.getGraderSettingsModel().isSettingsLoaded())
		GradingStatusUserChange.newCase(newValue, null, this);
		
	}

	@Override
	public Object fromString(String aString) {
		// TODO Auto-generated method stub
		return GradingStatus.fromString(aString);
	}
	
//	@Override
//	public String getName() {
//		return NAME;
//	}
//
//	@Override
//	public GradingStatus getParameter() {
//		return parameter;
//	}
//
//	@Override
//	public void setParameter(GradingStatus newValue) {
//		Object oldValue = parameter;
//		parameter = newValue;
//		propertyChangeSupport.firePropertyChange("parameter", oldValue, newValue);
//		
//	}
//
//	@Override
//	public void addPropertyChangeListener(PropertyChangeListener aListener) {
//		propertyChangeSupport.addPropertyChangeListener(aListener);
//		
//	}

}
