package grader.navigation.filter;

import grader.assignment.GradingFeatureList;
import grader.sakai.project.ProjectStepper;
import grader.sakai.project.SakaiProjectDatabase;
import grader.settings.GraderSettingsModel;
import grader.settings.GraderSettingsModelSelector;
import grader.trace.settings.NotesStatusUserChange;

public class ANotesStatusFilter extends AnAbstractNavigationFilter<NotesStatus>implements NavigationFilter<NotesStatus>{
	public static final String NAME = "Notes Status";
//	NotesStatus parameter = NotesStatus.NEEDS_NOTES;
	
	public ANotesStatusFilter() {
		name = NAME;
		parameter = NotesStatus.NEEDS_NOTES;
	}
	
	@Override
	public boolean includeProject(ProjectStepper aProjectState,
			SakaiProjectDatabase aDatabase) {
		NotesStatus actualStatus = calculateNotesStatus(aDatabase);
		return compatible(parameter, actualStatus);
	}
	
	public static boolean compatible (NotesStatus parameter, NotesStatus actualStatus ) {
		if (parameter == NotesStatus.ALL) return true;
		return parameter == actualStatus;		
	}
	

	public static NotesStatus calculateNotesStatus (SakaiProjectDatabase aDatabase) {
		GradingFeatureList gradingFeatures = aDatabase.getGradingFeatures();
		if (AGradingStatusFilter.calculateGradingStatus(aDatabase) == GradingStatus.NOT_FULLY_GRADED)
			return NotesStatus.NEEDS_NOTES;
		if (gradingFeatures.hasSomeNotes())
			return NotesStatus.HAS_NOTES;
		return NotesStatus.NO_NOTES;
	}


	@Override
	public Object fromString(String aString) {
		return NotesStatus.fromString(aString);
	}
	
	@Override
	public void setParameter(NotesStatus newValue) {
		super.setParameter(newValue);
		GraderSettingsModel settingsModel = GraderSettingsModelSelector.getGraderSettingsModel();
		if (settingsModel != null && GraderSettingsModelSelector.getGraderSettingsModel().isSettingsLoaded())

		NotesStatusUserChange.newCase(newValue, null, this);		
	}

}
