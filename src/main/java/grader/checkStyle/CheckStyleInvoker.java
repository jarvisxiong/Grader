package grader.checkStyle;

import grader.basics.execution.RunningProject;

public interface CheckStyleInvoker {
	public RunningProject checkStyle(String aSourceFileFlder);

}
