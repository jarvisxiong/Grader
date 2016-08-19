package grail.scanner;

import mp.tokens.RawInput;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
@Tags({"ClearableHistory"})
@StructurePattern(StructurePatternNames.LIST_PATTERN)

public class AClearableHistory implements ClearableHistory {
	public static final int MAX_SIZE = 50;
	protected RawInput[] contents = new RawInput[MAX_SIZE];
	protected int size = 0;
	int aUselessHistoryVariable = 1;
	// comment this out
	public AClearableHistory() {
		aUselessHistoryVariable = 2;
//		aUselessHistoryVariable = aUselessDatabaseVariable + 2;

    }
//	// uncomment this
//	public APraxisStringHistory(String anInitialElement) {
//	    System.out.println("String history constructor with an initialElement called:"+ anInitialElement);
//	    add(anInitialElement);
//    }


	@Tags({"clear"})
	public void clear()
	{
		for (int i=0;i<MAX_SIZE;i++)
		{
			contents[i]=null;
		}
		size=0;
	}
	public int size() {
		return size;
	}
	
	public RawInput get (int index) {
		return contents[index];
	}

	protected boolean isFull() {
		return size == MAX_SIZE;

	}
	
	public void add(RawInput element) {
		if (isFull())
			System.out.println("Adding item to a full history");
		else {
			contents[size] = element;
			size++;
		}
	} 
	
	public String toString() {
		String retVal = "";
		for (int i = 0; i < size; i++) {
			String separator = (i == 0)?"":":";
			retVal += separator + contents[i];
		}		
		return retVal;
	}
	

}
