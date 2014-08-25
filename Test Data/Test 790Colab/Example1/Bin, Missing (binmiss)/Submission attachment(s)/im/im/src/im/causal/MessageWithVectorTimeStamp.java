package im.causal;

import java.io.Serializable;

public interface MessageWithVectorTimeStamp extends Serializable {

	public abstract Object getMessage();

	public abstract VectorTimeStamp getVectorTimeStamp();

}