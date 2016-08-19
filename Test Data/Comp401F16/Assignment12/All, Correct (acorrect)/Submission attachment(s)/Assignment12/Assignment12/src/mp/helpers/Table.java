package mp.helpers;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"Table", "generic"})
@StructurePattern(StructurePatternNames.MAP_PATTERN)
public interface Table<KeyType, ObjectType> {
	public void put(KeyType key, ObjectType val);
	public ObjectType get(KeyType key);
}
