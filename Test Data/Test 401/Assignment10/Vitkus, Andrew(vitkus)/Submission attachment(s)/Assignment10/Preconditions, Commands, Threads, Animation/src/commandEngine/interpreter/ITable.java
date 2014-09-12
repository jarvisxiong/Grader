package commandEngine.interpreter;

import java.util.List;

import util.annotations.Tags;

@Tags({ "Table" })
public interface ITable {
	public void put(String key, Object val);

	public Object get(String key);

	public int size();

	public boolean isEmpty();

	public boolean containsKey(String key);

	public boolean containsValue(Object val);

	public List<String> getKeys();
}
