package framework.execution;

public interface ProcessInputListener {
	void newInput(String aProcessName, String anInput);
	void inputTerminated(String aProcessName);
	

}
