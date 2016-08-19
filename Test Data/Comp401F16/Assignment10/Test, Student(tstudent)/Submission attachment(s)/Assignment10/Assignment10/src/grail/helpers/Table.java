package grail.helpers;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"Table"})
@StructurePattern(StructurePatternNames.MAP_PATTERN)
public interface Table {
	public void put (String key, Object val);
	public Object get (String key);
	public void print();
	public boolean contains(String key);
}
