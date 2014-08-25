package echo.modular;

public interface HistoryObserver<ElementType> {
	void elementAdded(int anIndex, ElementType aNewValue);

}
