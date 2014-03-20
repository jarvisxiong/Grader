package grader.navigation.filter;

import grader.assignment.GradingFeatureList;
import grader.sakai.project.ProjectStepper;
import grader.sakai.project.SakaiProjectDatabase;

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
	
//	@Override
//	public String getName() {
//		return NAME;
//	}
//	
	public static NotesStatus calculateNotesStatus (SakaiProjectDatabase aDatabase) {
		GradingFeatureList gradingFeatures = aDatabase.getGradingFeatures();
		if (AGradingStatusFilter.calculateGradingStatus(aDatabase) == GradingStatus.NOT_FULLY_GRADED)
			return NotesStatus.NEEDS_NOTES;
		if (gradingFeatures.hasSomeNotes())
			return NotesStatus.HAS_NOTES;
		return NotesStatus.NO_NOTES;
	}
//
//	@Override
//	public NotesStatus getParameter() {
//		return parameter;
//	}
//
//	@Override
//	public void setParameter(NotesStatus newVal) {
//		parameter = newVal;		
//	}

	@Override
	public Object fromString(String aString) {
		return NotesStatus.fromString(aString);
	}

}
