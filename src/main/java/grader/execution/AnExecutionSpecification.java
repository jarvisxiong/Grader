package grader.execution;

import grader.config.StaticConfigurationUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.List;

public class AnExecutionSpecification implements ExecutionSpecification {
	List<String> processTeams = new ArrayList();
	Map<String, List<String>> processTeamToProcesses = new HashMap();
	Map<String, List<String>> processTeamToTerminatingProcesses = new HashMap();
	Map<String, Integer> processToSleepTime = new HashMap();
	Map<String, String> processToEntryTag = new HashMap();
	Map<String, String> processToEntryPoint = new HashMap();
	Map<String, List<String>> processToArgs = new HashMap();
	Map<String, List<String>> processToStartTags = new HashMap();
	
	public AnExecutionSpecification() {
		
	}
	
	/* (non-Javadoc)
	 * @see grader.execution.ExecutionSpecification#loadFromConfiguration()
	 */
	@Override
	public void loadFromConfiguration() {
		processTeams = StaticConfigurationUtils.getProcessTeams();

		for (String aProcessTeam:processTeams) {
			List<String> aProcesses =  StaticConfigurationUtils.getProcesses(aProcessTeam);
			processTeamToProcesses.put(aProcessTeam, aProcesses);
			List<String> aTerminatingProcesses =  StaticConfigurationUtils.getTerminatingProcesses(aProcessTeam);
			processTeamToTerminatingProcesses.put(aProcessTeam, aTerminatingProcesses);

			for (String aProcess:aProcesses) {
				Integer sleepTime = StaticConfigurationUtils.getSleepTime(aProcess);
				if (sleepTime != null)
					processToSleepTime.put(aProcess, sleepTime);
				String entryTag = StaticConfigurationUtils.getEntryTag(aProcess);
				if (entryTag != null)
					processToEntryTag.put(aProcess, StaticConfigurationUtils.getEntryTag(aProcess));
				List<String> args = StaticConfigurationUtils.getProcessArgs(aProcess);
				if (args != null)
					processToArgs.put(aProcess, args);
				List<String> startTags = StaticConfigurationUtils.getProcessStartTags(aProcess);
				if (startTags != null)
					processToStartTags.put(aProcess, args);
				
			}
		}
		
	}
	
	/* (non-Javadoc)
	 * @see grader.execution.ExecutionSpecification#getProcessTeams()
	 */
	@Override
	public List<String> getProcessTeams() {
		return processTeams;
	}
	
	/* (non-Javadoc)
	 * @see grader.execution.ExecutionSpecification#setProcessTeams(java.util.List)
	 */
	@Override
	public void setProcessTeams(List<String> aProcessTeamNames) {
		processTeams = aProcessTeamNames;
	}
	
	/* (non-Javadoc)
	 * @see grader.execution.ExecutionSpecification#getProcesses(java.lang.String)
	 */
	@Override
	public List<String> getProcesses(String aProcessTeam) {
		return processTeamToProcesses.get(aProcessTeam);
	}
	
	/* (non-Javadoc)
	 * @see grader.execution.ExecutionSpecification#setProcesses(java.lang.String, java.util.List)
	 */
	@Override
	public void setProcesses(String aProcessTeam, List<String> aProcesses) {
		 processTeamToProcesses.put(aProcessTeam, aProcesses);
	}
	
	@Override
	public List<String> getTerminatingProcesses(String aProcessTeam) {
		return processTeamToTerminatingProcesses.get(aProcessTeam);
	}
	
	/* (non-Javadoc)
	 * @see grader.execution.ExecutionSpecification#setProcesses(java.lang.String, java.util.List)
	 */
	@Override
	public void setTerminatingProcesses(String aProcessTeam, List<String> aProcesses) {
		processTeamToTerminatingProcesses.put(aProcessTeam, aProcesses);
	}
	/* (non-Javadoc)
	 * @see grader.execution.ExecutionSpecification#getSleepTime(java.lang.String)
	 */
	@Override
	public Integer getSleepTime(String aProcess) {
		return processToSleepTime.get(aProcess);
	}
	
	/* (non-Javadoc)
	 * @see grader.execution.ExecutionSpecification#setSleepTime(java.lang.String, int)
	 */
	@Override
	public void setSleepTime(String aProcess, int aSleepTime) {
		 processToSleepTime.put(aProcess, aSleepTime);
	}
	
	/* (non-Javadoc)
	 * @see grader.execution.ExecutionSpecification#getEntrytag(java.lang.String)
	 */
	@Override
	public String getEntrytag(String aProcess) {
		return processToEntryTag.get(aProcess);
	}
	/* (non-Javadoc)
	 * @see grader.execution.ExecutionSpecification#setEntryTag(java.lang.String, java.lang.String)
	 */
	@Override
	public void setEntryTag(String aProcess, String anEntryTag) {
		 processToEntryTag.put(aProcess, anEntryTag);
	}
	/* (non-Javadoc)
	 * @see grader.execution.ExecutionSpecification#getArgs(java.lang.String)
	 */
	@Override
	public List<String> getArgs(String aProcess) {
		return processToArgs.get(aProcess);
	}
	/* (non-Javadoc)
	 * @see grader.execution.ExecutionSpecification#setArgs(java.lang.String, java.util.List)
	 */
	@Override
	public void setArgs(String aProcess, List<String> anEntryArgs) {
		 processToArgs.put(aProcess, anEntryArgs);
	}
	
	@Override
	public List<String> getStartTags(String aProcess) {
		return processToStartTags.get(aProcess);
	}
	/* (non-Javadoc)
	 * @see grader.execution.ExecutionSpecification#setArgs(java.lang.String, java.util.List)
	 */
	@Override
	public void setStartTags(String aProcess, List<String> aStartTags) {
		processToStartTags.put(aProcess, aStartTags);
	}

	@Override
	public String getEntryPoint(String aProcess) {
		return processToEntryPoint.get(aProcess);
	}

	@Override
	public void setEntryPoint(String aProcess, String anEntryPoint) {
		processToEntryPoint.put(aProcess, anEntryPoint);
	}

}
