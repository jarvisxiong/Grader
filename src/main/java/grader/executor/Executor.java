package grader.executor;

public interface Executor {

	public abstract void compile();

	public abstract void execute(String[] args);

	public abstract void setExecutorDirectory(String newVal);

}