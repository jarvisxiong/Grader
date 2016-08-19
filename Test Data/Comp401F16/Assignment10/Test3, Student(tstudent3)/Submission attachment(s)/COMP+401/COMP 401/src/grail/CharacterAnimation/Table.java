package grail.CharacterAnimation;

import java.util.ArrayList;

import util.annotations.Tags;

@Tags({"Table"})

public class Table implements TableInt {

	ArrayList<String> keyTable;
	ArrayList<Object> values;	

	public Table(){
		keyTable = new ArrayList<String>();
		values = new ArrayList<Object>();
	}


	public void put(String key, Object val) {
		if (key != null && val != null){
			if (keyTable.indexOf(key)==-1){
				keyTable.add(key);
				values.add(keyTable.indexOf(key), val);
				System.out.println(key);
				System.out.println(val);
			}
		}
	}


	public Object get(String key) {
		//simplified my code by removing this variable and assignment
		//int index; index = keyTable.indexOf(key);

		if (keyTable.indexOf(key) == -1){
			return null;
		}
		else {
			return values.get(keyTable.indexOf(key));
		}


	}

}
