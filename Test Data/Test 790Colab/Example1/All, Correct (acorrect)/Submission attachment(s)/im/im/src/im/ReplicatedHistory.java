package im;

import echo.modular.History;

public interface ReplicatedHistory<ElementType> extends History<ElementType> {
	void replicatedAdd(ElementType newVal);
	

}
