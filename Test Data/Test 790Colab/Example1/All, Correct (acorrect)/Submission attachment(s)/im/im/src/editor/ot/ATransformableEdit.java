package editor.ot;

import widgets.Edit;

public class ATransformableEdit implements TransformableEdit {	
	Edit edit;
	boolean isServer;
	public ATransformableEdit(Edit  theEdit, boolean theIsServer) {
		edit = theEdit;
		isServer = theIsServer;		
	}
	
	public Edit getEdit() {
		return edit;
	}
	
	public void setEdit(Edit newVal) {
		this.edit = newVal;
	}
	
	public boolean isServer() {
		return isServer;
	}
	
	public void setServer(boolean isServer) {
		this.isServer = isServer;
	}
	public String toString() {
		return edit.toString() + " isServer:" + isServer;
	}
	public TransformableEdit copy() {
		return new ATransformableEdit(edit.copy(), isServer);
	}
}
