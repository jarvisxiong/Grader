package sasa.echoerAndIM;

import echo.modular.History;

public interface ObservableHistory<ElementType> extends History<ElementType>{
//	 void add(ElementType input) ;
	 void addObserver(HistoryObserver<ElementType> anObserver);
}
