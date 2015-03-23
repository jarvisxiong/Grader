package im.causal;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AVectorTimeStamp implements VectorTimeStamp {
	Map<String, Integer> userToCounter = new HashMap();

	@Override
	public void addMessage(String user) {
		userToCounter.put(user, userToCounter.get(user) + 1);
	}

	@Override
	public void addUser(String user) {
		userToCounter.put(user, 0);
	}

	@Override
	// assuming that the two time stamps are not concurrent
	public int compareTo(VectorTimeStamp other) {
		if (other.size() != size()) {
			throw new RuntimeException("Incomparable vector time stamps" + ". Others.size:" + other.size() );
		}
		Set<String> keys = userToCounter.keySet();
		
		for (String key: keys) {
			int myValue = get(key);
			int otherValue = other.get(key);
			int diff = otherValue - myValue;
			if (diff < 0)
				return -1;
			else if (diff > 0)
				return 1;			
		}
		return 0;		
	}
	public boolean isConcurrent(VectorTimeStamp other) {
		if (other.size() != size()) {
//			throw new RuntimeException("Incomparable vector time stamps: this:" + this + " other:" + other );
			throw new IncomparableVectorTimeStampException("Incomparable vector time stamps: this:" + this + " other:" + other );

		}
		Set<String> keys = userToCounter.keySet();
		boolean otherGreater = false;
		boolean thisGreater = false;
		for (String key: keys) {
			int myValue = get(key);
			int otherValue = other.get(key);
			int diff = otherValue - myValue;
			if (diff == 0)
				continue;
			else if (diff < 0 && !thisGreater) {
				thisGreater = true;
				if (otherGreater)
					return true;
			}
			else if (diff > 0 && !otherGreater) {
				otherGreater = true;
				if (thisGreater)
					return true;
					
			}
		}
		return false;		
		
	}
	public boolean isSuccessor(VectorTimeStamp other) {
		if (other.size() != size()) {
			throw new RuntimeException("Incomparable vector time stamps" );
		}
		Set<String> keys = userToCounter.keySet();
		boolean foundOneSuccessor= false;
		for (String key: keys) {
			int myValue = get(key);
			int otherValue = other.get(key);
			int diff = otherValue - myValue;
			if (diff == 0)
				continue;
			else if (diff < 0)
				return false;
			else if (diff == 1) {
				if (foundOneSuccessor)
					return false;
				else
					foundOneSuccessor = true;	
			} else 
				return false;			
		}
		return foundOneSuccessor;		
	}
	

	@Override
	public int get(String user) {
		return userToCounter.get(user);
	}

	@Override
	public int size() {
		return userToCounter.size();
	}
	public VectorTimeStamp clone() {
		AVectorTimeStamp retVal = new AVectorTimeStamp();
		Set<String> keys = userToCounter.keySet();
		for (String key: keys)
			retVal.userToCounter.put(key, get(key));
		return retVal;
	}
	public String toString() {
		return userToCounter.toString();
	}

}
