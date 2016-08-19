package grail.helpers;

import java.util.ArrayList;
import java.util.List;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@Tags({"Table", "generic"})
@StructurePattern(StructurePatternNames.MAP_PATTERN)
public class BasicTable<KeyType, ValueType> implements Table<KeyType, ValueType>{
	protected List<ValueType> valueColumn;
	protected List<KeyType> keyColumn;
	
	public BasicTable() {
		this.valueColumn = new ArrayList<ValueType>();
		this.keyColumn = new ArrayList<KeyType>();
		
	}
	
	public void put(KeyType key, ValueType val) {
		int spotToCheck = 0;
		if (this.keyColumn.contains(key) && !(key == null || val == null)) {
			while (spotToCheck < this.keyColumn.size()) {
				Object currentKey = this.keyColumn.get(spotToCheck);
				if (currentKey.equals(key)) {
					this.valueColumn.set(spotToCheck, val);
				}
				spotToCheck++;
	
			}
		} else if (!(key == null || val == null)){
			this.keyColumn.add(key);
			this.valueColumn.add(val);
		} else {
			//do nothing
		}
		
	}

	
	public ValueType get(KeyType key) {
		int spotToCheck = 0;
		while (spotToCheck < this.keyColumn.size()) {
			Object currentKey = this.keyColumn.get(spotToCheck);
			if (currentKey.equals(key)) {
				return this.valueColumn.get(spotToCheck);
			}
			spotToCheck++;
		}
		return null;
	}

	public void print() {
		int startingPoint = 0;
		while (startingPoint < this.valueColumn.size()) {
			String toPrint = "Key: " + this.keyColumn.get(startingPoint).toString() + " Value: " + this.valueColumn.get(startingPoint).toString();
			System.out.println(toPrint);
			startingPoint++;
		}
		
	}

	public boolean contains(KeyType key) {
		int spotToCheck = 0;
		while (spotToCheck < this.keyColumn.size()) {
			Object currentKey = this.keyColumn.get(spotToCheck);
			if (currentKey.equals(key)) {
				return true;
			}
			spotToCheck++;
		}
		return false;
	}

	public String keyList() {
		String toReturn = "";
		for (int i = 0; i < this.keyColumn.size(); i++) {
			toReturn = toReturn + this.keyColumn.get(i).toString() + "\n";
		}
		return toReturn;
	}


	

}
