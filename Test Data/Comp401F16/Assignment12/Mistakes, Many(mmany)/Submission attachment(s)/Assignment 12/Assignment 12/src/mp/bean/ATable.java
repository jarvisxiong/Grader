package mp.bean;

import java.util.ArrayList;

import util.annotations.EditablePropertyNames;
import util.annotations.PropertyNames;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@StructurePattern(StructurePatternNames.MAP_PATTERN)

@Tags({"Table", "generic"})

public class ATable<KeyT, ValueT> implements Table<KeyT, ValueT> {
	
	ArrayList<Object[]> elements;
	protected Object[] element;
	int size;
	public ATable() {
		elements = new ArrayList<Object[]>();
	}
	public void put(KeyT key, ValueT val) {
		boolean keyExists = false;
		if (key != null && val != null) {
			if (elements.size() == 0) {
				element = new Object[2];
				element[0] = key;
				element[1] = val;
				elements.add(element);
				size = elements.size();
			}
			else {
				for (int i = 0; i<elements.size(); i++){
					if (elements.get(i)[0].equals(key)) {
						keyExists = true;
						elements.get(i)[1] = val;
						break;
					}
				}
				if (!keyExists) {
					element = new Object[2];
					element[0] = key;
					element[1] = val;
					elements.add(element);
					size = elements.size();
				}
			}
		}
	}
	public ValueT get(KeyT key) {
		boolean elementExists = false;
		for (int i = 0; i<elements.size(); i++) {
			if (elements.get(i)[0].equals(key)){
				elementExists = true;
				return (ValueT) elements.get(i)[1];
			}
		}
		return null;
	}
	public int getSize() {
		return size;
	}
	public ArrayList<Object[]> getElements() {
		return elements;
	}
}
