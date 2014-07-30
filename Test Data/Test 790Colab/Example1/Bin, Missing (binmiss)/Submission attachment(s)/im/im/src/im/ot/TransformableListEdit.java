package im.ot;

import im.ListEdit;

import java.io.Serializable;

public interface TransformableListEdit extends Serializable{
	public abstract ListEdit getListEdit();

	public abstract void setListEdit(ListEdit newVal);

	public abstract boolean isServer();

	public abstract void setServer(boolean isServer);
	
	public TransformableListEdit copy();

}
