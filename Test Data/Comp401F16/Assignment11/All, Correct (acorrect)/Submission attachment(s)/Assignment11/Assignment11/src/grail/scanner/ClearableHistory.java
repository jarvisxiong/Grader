package grail.scanner;

import mp.tokens.RawInput;

public interface ClearableHistory {
	public void add(RawInput element);
	public RawInput get (int index); 
	public int size();
	public void clear();
}
