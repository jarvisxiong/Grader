package gradingTools;

public interface GraderDemoerAndTester extends Runnable{

	public abstract boolean isAutoProceed();

	public abstract void setAutoProceed(boolean autoProceed);

	public abstract boolean isGeneratingCorrectDir();

	public abstract void setGeneratingCorrectDir(boolean generatingCorrectDir);

	public abstract void demoAndTest();

	public abstract void startFirstSession();

	public abstract void startSecondSession();

	public abstract void secondSession();

}