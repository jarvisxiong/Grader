package echo.modular;


public interface History<ElementType> {
	 void add(int index, ElementType input) ;
	 void add(ElementType input);
//	 void add(ElementType input) ;
	 void observableAdd(int index, ElementType input) ;
	 void observableAdd(ElementType input) ;
	 int size();
	 ElementType get(int index);
	 void addObserver(HistoryObserver<ElementType> anObserver) ;
		
}
