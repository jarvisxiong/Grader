package im.centralized;

import im.AListEdit;
import im.ListEdit;
import im.OperationName;
import trace.im.ListEditSent;
import util.session.Communicator;
import util.trace.Tracer;
import util.trace.session.AddressedSentMessageInfo;
import echo.modular.AHistory;

public class ACentralizedHistory<ElementType> extends AHistory<ElementType> implements CentralizedHistory<ElementType> {
	Communicator communicator;
	public ACentralizedHistory(Communicator theCommunicator) {
		communicator = theCommunicator;
	}
	
	public synchronized void centralizedAdd(ElementType anInput, String aClientName) {
		int anIndex = size();
		super.add(anIndex, anInput);
		UserEdit<ElementType> usertEdit = new AUserEdit<ElementType>(OperationName.ADD, anIndex, anInput, aClientName);
		ListEditSent.newCase(
				communicator.getClientName(),
				usertEdit.getOperationName(), 
				usertEdit.getIndex(), 
				usertEdit.getElement(), 
				AddressedSentMessageInfo.OTHERS, this);
		communicator.toOthers(usertEdit);
	}
	
}
