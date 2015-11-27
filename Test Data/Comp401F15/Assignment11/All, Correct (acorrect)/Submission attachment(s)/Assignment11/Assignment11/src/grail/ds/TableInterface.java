package grail.ds;

import util.annotations.Tags;

@Tags({"Table"})
public interface TableInterface {
	public void put (String key, Object val);
	public Object get (String key);
}
