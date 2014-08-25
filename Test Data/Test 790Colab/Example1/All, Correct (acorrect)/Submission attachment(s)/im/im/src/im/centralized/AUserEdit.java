package im.centralized;

import im.AListEdit;
import im.OperationName;

public class AUserEdit<ElementType> extends AListEdit<ElementType> implements UserEdit<ElementType>{
	String userName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public AUserEdit(OperationName aName, int anIndex, ElementType anElement, String aUserName) {
		super(aName, anIndex, anElement);
		userName = aUserName;
	}
	

}
