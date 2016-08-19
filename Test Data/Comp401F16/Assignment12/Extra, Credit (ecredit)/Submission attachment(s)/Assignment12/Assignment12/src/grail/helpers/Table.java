package grail.helpers;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"Table", "generic"})
@StructurePattern(StructurePatternNames.MAP_PATTERN)
public interface Table<KeyType, ValueType> {
	public void put (KeyType key, ValueType val);
	public ValueType get (KeyType key);
	public void print();
	public boolean contains(KeyType key);
	public String keyList();
}
