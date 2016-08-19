package mp.command;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.MAP_PATTERN) 
public interface Table {
	public void put(String key, Object val);
	public Object get(String key);
}
