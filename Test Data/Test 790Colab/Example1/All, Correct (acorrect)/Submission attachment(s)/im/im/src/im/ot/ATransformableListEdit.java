package im.ot;

import im.ListEdit;

public class ATransformableListEdit implements TransformableListEdit {	
	ListEdit listEdit;
	boolean isServer;
	public ATransformableListEdit(ListEdit  theEdit, boolean theIsServer) {
		listEdit = theEdit;
		isServer = theIsServer;		
	}
	
	public ListEdit getListEdit() {
		return listEdit;
	}
	
	public void setListEdit(ListEdit newVal) {
		this.listEdit = newVal;
	}
	
	public boolean isServer() {
		return isServer;
	}
	
	public void setServer(boolean isServer) {
		this.isServer = isServer;
	}
	public String toString() {
		return listEdit.toString() + 
				(isServer?"(Server)":"Client");
	}
	public TransformableListEdit copy() {
		return new ATransformableListEdit(listEdit.copy(), isServer);
	}
}
