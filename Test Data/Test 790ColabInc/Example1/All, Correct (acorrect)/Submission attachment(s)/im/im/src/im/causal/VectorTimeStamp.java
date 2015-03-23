package im.causal;

import java.io.Serializable;

public interface VectorTimeStamp extends Serializable, Comparable<VectorTimeStamp> {
	public void addUser(String user);
	public void addMessage(String user);
	public int size();
	public int get(String user);
	public boolean isSuccessor(VectorTimeStamp other);
	public boolean isConcurrent(VectorTimeStamp other);
	public VectorTimeStamp clone();
}
