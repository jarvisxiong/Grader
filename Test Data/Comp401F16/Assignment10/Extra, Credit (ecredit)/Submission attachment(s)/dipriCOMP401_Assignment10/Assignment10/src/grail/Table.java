package grail;

import util.annotations.Tags;

@Tags("table")
public interface Table {
public void put (String key, Object val);
public Object get (String key);
}
