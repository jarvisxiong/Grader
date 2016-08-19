package grail.helpers;

import grail.interfaces.StoreToken;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"ClearableHistory"})
@StructurePattern(StructurePatternNames.LIST_PATTERN)
public interface TokenHistory {
	public void add(StoreToken element);
	public StoreToken get (int index); 
	public int size();
	public void clear();
}
