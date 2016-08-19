package main;

import mp.Avatar;
import util.annotations.Tags;

@Tags({"Table"})
public interface Table {
	public void put (String key, Object val);
	public Object get(String key);
}
