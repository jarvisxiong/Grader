package im.access;

public interface Vetoer<ValueType> {
	boolean veto(ValueType theInput);
}
