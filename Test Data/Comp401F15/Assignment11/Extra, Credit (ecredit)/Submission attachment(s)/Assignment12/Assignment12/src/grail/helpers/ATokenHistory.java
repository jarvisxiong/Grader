package grail.helpers;

import grail.interfaces.StoreToken;
import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"ClearableHistory"})
@StructurePattern(StructurePatternNames.LIST_PATTERN) 
@PropertyNames({}) 
@EditablePropertyNames({})
public class ATokenHistory implements TokenHistory {
	public static final int MAX_SIZE = 100;
	protected StoreToken[] contents = new StoreToken[MAX_SIZE];
	protected int size = 0;
	
	public ATokenHistory() {
	
	}

	public int size() {
		return size;
	}
	
	public StoreToken get(int index) {
		return contents[index];
	}

	protected boolean isFull() {
		return size == MAX_SIZE;

	}
	
	public void add(StoreToken element) {
		if (isFull()) {
			clear();
		}
		else {
			contents[size] = element;
			size++;
		}
	} 
	
	public String toString() {
		String retVal = "";
		for (int i = 0; i < size; i++) {
			String separator = (i == 0)?"":",";
			retVal += separator + contents[i];
		}		
		return super.toString() + ":The tokens stored are:" + retVal;
	}

	@Tags({"clear"})
	public void clear() {
		for(int i = 0; i < size; i++) {
			contents[i] = null;
		}
		size = 0;
	}
	

}
