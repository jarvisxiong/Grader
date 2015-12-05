package grail;

import java.util.ArrayList;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags("table")
@StructurePattern(StructurePatternNames.MAP_PATTERN)
public class ATable implements Table {

	ArrayList<String> keyList = new ArrayList<String>();
	ArrayList<Object> valueList = new ArrayList<>();

	
	public void put(String aKey, Object aValue){
		int keyListLength = keyList.size();
		Boolean changeList = false;
		int valueIndex = 0;
		if (aKey != null && aValue != null){
		for (int i = 0; i < keyListLength; i++){
			if (aKey.equalsIgnoreCase(keyList.get(i))){
				changeList = true;
				valueIndex = i;
			}
		}
			if(changeList){
				valueList.set(valueIndex, aValue);
			}
			else {
				keyList.add(aKey);
				valueList.add(aValue);
			}
		}
//		System.out.println(valueList.get(keyList.indexOf(aKey)));
//		System.out.println(keyList.get(valueList.indexOf(aValue)));
	}
	
	public Object get(String aKey){
		int keyListLength = keyList.size();
		Object result = null;
		for(int i = 0; i<keyListLength; i++){
			if(aKey.equalsIgnoreCase(keyList.get(i))){
			result = valueList.get(i);
			}
		}
//		System.out.println(result);
		return result;
	}
	
//	public void put(String aKey, Object aValue){
//		int keyListLength = keyList.size();
//		Boolean[] changeList = new Boolean[keyList.size()];
//		for (int i = 0; i < keyListLength; i++){
//			if (aKey.equalsIgnoreCase(keyList.get(i))){
//				changeList[i] = true;
//			}
//			else {
//				changeList[i] = false;
//			}
//		}
//		for (int y = 0; y < keyList.size(); y++){
//			if(changeList[y]){
//				valueList.set(y, aValue);
//			}
//			else {
//				valueList.add(aValue);
//			}
//		}
//	}
	
	
}
