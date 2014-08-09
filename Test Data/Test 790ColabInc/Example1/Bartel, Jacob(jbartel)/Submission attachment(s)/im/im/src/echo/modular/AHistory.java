package echo.modular;

import im.OperationName;

import java.util.ArrayList;
import java.util.List;

import trace.echo.modular.ListEditMade;
import trace.echo.modular.ListEditNotified;
import util.annotations.Tags;
import util.tags.ApplicationTags;
import util.tags.InteractiveTags;

@Tags({ApplicationTags.HISTORY, InteractiveTags.MODEL})

public class AHistory<ElementType> implements History<ElementType> {

	List<ElementType> history = new ArrayList();
	List<HistoryObserver<ElementType>> observers = new ArrayList();
	int normalizedIndex(int index) {
		return index > size()?size(): index;
	}
	public synchronized void add(int anIndex, ElementType anElement) {
//		if (anIndex > size()) {
//			anIndex = size();
//			Tracer.error("Received index greater than size, appending object");
//		}
		history.add(normalizedIndex(anIndex), anElement);
		ListEditMade.newCase(OperationName.ADD, anIndex, anElement, this);
	}
	
	public synchronized void add(ElementType anElement) {
//		if (anIndex > size()) {
//			anIndex = size();
//			Tracer.error("Received index greater than size, appending object");
//		}
		history.add(history.size(), anElement);
	}
	
	public synchronized void observableAdd(ElementType anElement) {
//		if (anIndex > size()) {
//			anIndex = size();
//			Tracer.error("Received index greater than size, appending object");
//		}
		observableAdd (history.size(), anElement);		
	}

	public synchronized void observableAdd(int anIndex, ElementType anElement) {
		add(anIndex, anElement);
		notifyObservers(normalizedIndex(anIndex), anElement);
	}

	@Override
	public int size() {
		return history.size() ;
	}
	@Override
	public ElementType get(int index) {
		return history.get(index);
	}
//	public synchronized void add(int index, ElementType input) {
//		super.add(index, input);
//		notifyObservers(input);
//	}

	@Override
	public void addObserver(HistoryObserver<ElementType> anObserver) {
		observers.add(anObserver);
	}
	
	public void notifyObservers(int index, ElementType newValue) {
		ListEditNotified.newCase(OperationName.ADD, index, newValue, this);
		for (HistoryObserver<ElementType> observer:observers)
			observer.elementAdded(index, newValue);
	}
}
