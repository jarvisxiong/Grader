package im;

import trace.echo.modular.ListEditInfo;

public class AListEdit<ElementType> implements ListEdit<ElementType>{
	OperationName name;	
	int index;
	ElementType element;
	public AListEdit (OperationName theName, int theIndex, ElementType theElement) {
		index = theIndex;
		element = theElement;
		name = theName;
	}	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public ElementType getElement() {
		return element;
	}
	
	public void setElement(ElementType anElement) {
		this.element = anElement;
	}
	public OperationName getOperationName() {
		return name;
	}
	public void setOperationName(OperationName anOperationName) {
		this.name = anOperationName;
	}
	public String toString() {
		return "[" + index + "; " + element + "]"; 
	}
	public ListEdit copy() {
		return new AListEdit(name, index, element);
	}
	
	public ListEditInfo toListEditInfo() {
		return new ListEditInfo(name, index, element );
	}
	
}
