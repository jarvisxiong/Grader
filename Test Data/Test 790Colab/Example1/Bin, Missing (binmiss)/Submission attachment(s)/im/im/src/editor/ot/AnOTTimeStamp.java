package editor.ot;

import util.trace.Tracer;

public class AnOTTimeStamp implements OTTimeStamp {
	int localCount;
	int remoteCount;
	public AnOTTimeStamp(int theLocalCount, int theRemoteCount) {
		localCount = theLocalCount;
		remoteCount = theRemoteCount;
	}
	public AnOTTimeStamp() {
		
	}
	public  int getLocalCount() {
		return localCount;
	}
	
	public void setLocalCount(int newVal) {
		localCount = newVal;
	}	
	public  int getRemoteCount() {
		return remoteCount;
	}	
	public void setRemoteCount(int newVal) {
		remoteCount = newVal;
	}
	public boolean isConcurrent(OTTimeStamp other) {
		Tracer.info(this, " comparing: " + this + " with:" + other);
		return !isGreaterThanOrEqual(other) && !other.isGreaterThanOrEqual(this);

	}
	public boolean isGreaterThanOrEqual(OTTimeStamp other) {
		Tracer.info(this, " comparing: " + this + " with:" + other);
		return getLocalCount() >= other.getRemoteCount() &&
				getRemoteCount() >= other.getLocalCount();
	}
	public void incrementLocalCount() {
		localCount++;
	}
	
	public void incrementRemoteCount() {
		remoteCount++;
	}
	public String toString() {
//		return "local:" + localCount + " remote:" + remoteCount;
		return "[" + localCount + ", " + remoteCount + "]";

	}
	public OTTimeStamp copy() {
		return new AnOTTimeStamp(localCount, remoteCount);
	}

}
