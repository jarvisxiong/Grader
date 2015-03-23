package im;

import trace.im.ListEditSent;
import util.session.Communicator;
import util.trace.Tracer;
import util.trace.session.AddressedSentMessageInfo;
import echo.modular.AHistory;

public class AReplicatedHistory<ElementType> extends AHistory<ElementType> implements ReplicatedHistory<ElementType> {
	Communicator communicator;
	public AReplicatedHistory(Communicator theCommunicator) {
		communicator = theCommunicator;
	}
	
	public synchronized void replicatedAdd(ElementType anInput) {
		int anIndex = size();
		super.observableAdd(anIndex, anInput);
//		communicator.toOthers(new ARemoteInput(input));
//		communicator.toOthers(input);
		ListEdit listEdit = new AListEdit<ElementType>(OperationName.ADD, anIndex, anInput);
		ListEditSent.newCase(
				communicator.getClientName(),
				listEdit.getOperationName(), 
				listEdit.getIndex(), 
				listEdit.getElement(), 
			AddressedSentMessageInfo.OTHERS, this);
		communicator.toOthers(listEdit);

	}

	
}
