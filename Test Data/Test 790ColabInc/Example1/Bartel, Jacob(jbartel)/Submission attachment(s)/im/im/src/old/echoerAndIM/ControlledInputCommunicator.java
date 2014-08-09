package old.echoerAndIM;

public interface ControlledInputCommunicator extends InputCommunicator {
	public void addVetoer(Vetoer theVetoer);
	public void removeVetoer(Vetoer theVetoer);

}
