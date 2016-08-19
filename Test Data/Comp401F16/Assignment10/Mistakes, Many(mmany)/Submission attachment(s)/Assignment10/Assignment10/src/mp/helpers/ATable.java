package mp.helpers;

import java.util.ArrayList;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"Table"})
@StructurePattern(StructurePatternNames.MAP_PATTERN)
public class ATable implements Table{
	private ArrayList<Object> keys;
	private ArrayList<Object> values;
	
	public ATable(){
		keys = new ArrayList<Object>();
		values = new ArrayList<Object>();
	}
	
	@Override
	public void put(String key, Object val) {
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
	public Object get(String key) {
		if(keys.contains(key) && key != null){
			return values.get(keys.indexOf(key));
		}
		return null;
	}

}
