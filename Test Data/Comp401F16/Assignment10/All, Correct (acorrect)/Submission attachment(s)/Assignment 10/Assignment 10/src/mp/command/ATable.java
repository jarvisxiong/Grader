package mp.command;


import java.util.ArrayList;
import java.util.List;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
@Tags({"table"})
@StructurePattern(StructurePatternNames.MAP_PATTERN) 
public class ATable implements Table{
	List<String> theKey= new ArrayList<String>();
	List<Object> value= new ArrayList<Object>();
	int i=0;
	public void put(String key, Object val){
		if(key!=null&&val!=null){
				if(theKey.indexOf(key.toLowerCase())!=-1&&theKey.indexOf(key.toLowerCase())==value.indexOf(val)){
				}
				else{
					theKey.add(key.toLowerCase());
					value.add(val);
				}
		}
		
	}
	
	public Object get(String key){
		if(theKey.contains(key.toLowerCase())){
			return value.get(theKey.indexOf(key.toLowerCase()));
		}
		else{
			return null;
		}
	}
}
