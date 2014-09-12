package commandEngine.interpreter;

import java.util.ArrayList;
import java.util.List;

import util.annotations.Tags;

@Tags({ "Table" })
public class Table implements ITable {
	private final ArrayList<String> keys;
	private final ArrayList<Object> values;

	public Table() {
		keys = new ArrayList<>();
		values = new ArrayList<>();
	}

	@Override
	public Object get(String key) {
		if (containsKey(key)) {
			return values.get(keys.indexOf(key));
		} else {
			return null;
		}
	}

	@Override
	public void put(String key, Object value) {
		if (key == null || value == null) {
			return;
		}
		if (containsKey(key)) {
			values.set(keys.indexOf(key), value);
		}
		keys.add(key);
		values.add(value);
	}

	@Override
	public int size() {
		return keys.size();
	}

	@Override
	public boolean isEmpty() {
		return keys.isEmpty();
	}

	@Override
	public boolean containsKey(String key) {
		return keys.contains(key);
	}

	@Override
	public boolean containsValue(Object val) {
		return values.contains(val);
	}

	@Override
	public List<String> getKeys() {
		return keys;
	}

}
