package grail.ds;

import java.util.Vector;

import util.annotations.Tags;

@Tags({"Table"})
//the keypoint is to use key to find index of value, which means, elements in keys and vals should 
//match each other with the same index, in fact, this style is too slow
public class Table implements TableInterface{
	Vector<String> keys=new Vector<String>();
	Vector<Object> vals=new Vector<Object>();
	int currentSize;
	public void put (String key, Object val)
	{
		if (key==null||val==null)
		{
			System.out.println("key or value is null, do nothing");
			return;
		}
		if (keys.contains(key))//I donot know if the key exist, we should duplicate it or not, in my version, I don't support duplicate
		{
			vals.set(keys.indexOf(key), val);
		}else
		{
			currentSize=keys.size();//vector index begins from 0
			keys.add(currentSize, key);
			vals.add(currentSize, val);
			currentSize++;
		}
	}
	public Object get(String key)
	{
		if (keys.contains(key))
		{
			return vals.get(keys.indexOf(key));
		}else
		{
			return null;
		}
	}
}
