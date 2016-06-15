package gradingTools.assignment1;

import framework.execution.ARunningProject;
import framework.execution.NotRunnableException;
import framework.execution.RunningProject;
import framework.project.Project;

/**
 * This tries to run the program with a space at the end of each line and adds the terminating character '.'
 */
public class FlexibleProgramRunner {

    private static final int Timeout = 1;

    private Project project;
    private String input;
    private String spacedInput = "";
    RunningProject runningProject;

  

	public FlexibleProgramRunner(Project project, String input) {
        this(project, input, true);
    }

    public FlexibleProgramRunner(Project project, String input, boolean includePeriod) {
        this.project = project;
        this.input = input;

        String[] lines = input.split("\n");
        for (String line : lines)
            spacedInput += line + " \n";

        if (includePeriod) {
            input += ".\n";
            spacedInput += ".\n";
        }
    }

    public String runWithSpaces() throws NotRunnableException {
        runningProject = project.launch(spacedInput, Timeout);

//         runningProject = project.launch(spacedInput, new String[]{"foo", "bar"}, Timeout);
        
        return runningProject.await();
    }

    public String runNoSpaces() throws NotRunnableException {
         runningProject = project.launch(input, Timeout);
        return runningProject.await();
    }
    public RunningProject getRunningProject() {
  		return runningProject;
  	}

  	public void setRunningProject(ARunningProject runningProject) {
  		this.runningProject = runningProject;
  	}

}
