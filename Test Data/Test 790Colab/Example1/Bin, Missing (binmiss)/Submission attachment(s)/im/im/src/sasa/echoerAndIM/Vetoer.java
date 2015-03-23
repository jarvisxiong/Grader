package sasa.echoerAndIM;

public interface Vetoer<ValueType> {
	boolean veto(ValueType theInput);
}
