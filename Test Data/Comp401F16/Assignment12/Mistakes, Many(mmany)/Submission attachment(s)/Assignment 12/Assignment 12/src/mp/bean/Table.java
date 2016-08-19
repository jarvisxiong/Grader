package mp.bean;

import java.util.ArrayList;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.MAP_PATTERN)

public interface Table<KeyT, ValueT> {
	
	int size = 0;
	ArrayList<Object[]> elements = null;
	
	public void put(KeyT key, ValueT val); 
	public ValueT get(KeyT key);
	public int getSize();
	public ArrayList<Object[]> getElements();
}
