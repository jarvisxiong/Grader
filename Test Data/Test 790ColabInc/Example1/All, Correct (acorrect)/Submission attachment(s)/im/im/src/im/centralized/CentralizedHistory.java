package im.centralized;

import echo.modular.History;

public interface CentralizedHistory<ElementType> extends History<ElementType> {
	void centralizedAdd(ElementType newVal, String aUserName);
	

}
