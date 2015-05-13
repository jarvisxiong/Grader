package grader.executor;

public interface Executor {

	public abstract void compile();

	public abstract String[] maybeToExecutorCommand(String[] aCommand);

	public abstract void setExecutorDirectory(String newVal);

}