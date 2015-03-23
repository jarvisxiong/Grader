package old.echoerAndIM;

public interface ControlDispatcher extends Echoer {
	public void addVetoer(Vetoer theVetoer);
	public void removeVetoer(Vetoer theVetoer);
}
