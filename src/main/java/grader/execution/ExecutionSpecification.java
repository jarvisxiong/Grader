package grader.execution;

import java.util.List;

public interface ExecutionSpecification {

	public abstract void loadFromConfiguration();

	public abstract List<String> getProcessTeams();

	public abstract void setProcessTeams(List<String> aProcessTeamNames);

	public abstract List<String> getProcesses(String aProcessTeam);

	public abstract void setProcesses(String aProcessTeam,
			List<String> aProcesses);

	public abstract Integer getSleepTime(String aProcess);

	public abstract void setSleepTime(String aProcess, int aSleepTime);

	public abstract String getEntrytag(String aProcess);

	public abstract void setEntryTag(String aProcess, String anEntryTag);

	public abstract String getArgs(String aProcess);

	public abstract void setArgs(String aProcess, List<String> anEntryArgs);

}