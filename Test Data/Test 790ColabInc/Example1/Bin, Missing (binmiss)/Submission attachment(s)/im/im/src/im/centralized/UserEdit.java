package im.centralized;

import im.ListEdit;

public interface UserEdit<ElementType> extends ListEdit<ElementType>{
	public String getUserName();
	public void setUserName(String userName) ;
}
