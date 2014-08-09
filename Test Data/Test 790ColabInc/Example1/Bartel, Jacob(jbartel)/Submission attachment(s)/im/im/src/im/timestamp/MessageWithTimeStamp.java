package im.timestamp;

import java.io.Serializable;

public interface MessageWithTimeStamp extends Serializable {

	public abstract Object getMessage();

	public abstract long geTimeStamp();

}