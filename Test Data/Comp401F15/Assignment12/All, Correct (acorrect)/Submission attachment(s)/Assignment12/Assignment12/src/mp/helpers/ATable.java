package mp.helpers;

import java.util.ArrayList;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"Table", "generic"})
@StructurePattern(StructurePatternNames.MAP_PATTERN)
public class ATable<KeyType, ObjectType> implements Table<KeyType, ObjectType>{
	private ArrayList<KeyType> keys;
	private ArrayList<ObjectType> values;
	
	public ATable(){
		keys = new ArrayList<KeyType>();
		values = new ArrayList<ObjectType>();
	}
	
	@Override
	public void put(KeyType key, ObjectType val) {
		if(key != null && val != null){
			if(keys.contains(key)){
				values.set(keys.indexOf(key), val);
			}else{
				keys.add(key);
				values.add(val);
			}
		}
	}

	@Override
	public ObjectType get(KeyType key) {
		if(keys.contains(key) && key != null){
			return values.get(keys.indexOf(key));
		}
		return null;
	}

}
