package main;

public interface CommandInterpreter {
	public void setCommand(String command);
	
	public void animateArthur();
	public void animateLancelot();
	public void animateGalahad();
	public void animateRobin();
	public void animateGuard();
	
	public void waitingArthur();
	public void waitingGalahad();
	public void waitingRobin();
	public void waitingLancelot();
	
	public void startAnimation();
}
