package im;

import java.io.Serializable;

import trace.echo.modular.ListEditInfo;

public interface ListEdit<ElementType> extends Serializable {	
	int getIndex();
	void setIndex(int anIndex);
	ElementType getElement();
	void setElement(ElementType anElement);
	OperationName getOperationName();
	void setOperationName(OperationName name);
	ListEdit copy();
	public ListEditInfo toListEditInfo();

}