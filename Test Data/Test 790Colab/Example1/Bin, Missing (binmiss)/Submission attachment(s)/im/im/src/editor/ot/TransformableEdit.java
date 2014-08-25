package editor.ot;

import java.io.Serializable;

import widgets.Edit;
// More like an edit holder, except that it indicates if it is in server
public interface TransformableEdit extends Serializable {

	public abstract Edit getEdit();

	public abstract void setEdit(Edit newVal);

	public abstract boolean isServer();

	public abstract void setServer(boolean isServer);
	
	public TransformableEdit copy();

}