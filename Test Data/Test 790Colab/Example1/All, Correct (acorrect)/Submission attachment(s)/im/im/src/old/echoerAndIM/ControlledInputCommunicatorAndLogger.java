package old.echoerAndIM;

public interface ControlledInputCommunicatorAndLogger extends InputCommunicatorAndLogger {
	public void addVetoer(Vetoer theVetoer);
	public void removeVetoer(Vetoer theVetoer);

}
