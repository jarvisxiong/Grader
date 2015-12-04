package main;

import util.annotations.Tags;
import java.util.List;
import java.util.ArrayList;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.MAP_PATTERN)
@Tags({"Table"})

public class ATable<K,V> implements Table<K,V>{
	List<V> aList = new ArrayList<V>();
	List<K> referenceList = new ArrayList<K>();
	int listCounter = 0;
	
	public void put (K key, V val) {
		if (referenceList.contains(key)) {aList.set(referenceList.indexOf(key), val);}
		else {
			aList.add(val);
			referenceList.add(key);
			listCounter++;
		}
	}
	public Object get (K key) {
		if (listCounter != 0) {return aList.get(referenceList.indexOf(key));}
		else {return null;}
	}

}
