package grader.settings.navigation;

import grader.sakai.project.ProjectStepper;
import grader.sakai.project.SakaiProjectDatabase;

public class ANotesStatusFilter implements NavigationFilter{
	public static final String NAME = "Notes Status";
	public static final NotesStatus parameters = NotesStatus.NO_NOTES;

	@Override
	public boolean includeProject(ProjectStepper aProjectState,
			SakaiProjectDatabase aDatabase) {
		return true;
	}
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public NotesStatus getParameters() {
		return parameters;
	}

}
