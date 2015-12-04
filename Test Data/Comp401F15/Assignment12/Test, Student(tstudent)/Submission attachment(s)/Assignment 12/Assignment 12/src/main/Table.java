package main;

import util.annotations.Tags;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.MAP_PATTERN)
@Tags({"Table"})

public interface Table<K,V> {
	public void put (K key, V val);
	public Object get(K key);
}
