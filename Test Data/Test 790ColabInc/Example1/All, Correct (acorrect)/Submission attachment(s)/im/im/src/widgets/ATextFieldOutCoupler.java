package widgets;

import util.session.Communicator;

public class ATextFieldOutCoupler implements TextFieldListener {
	TextField textField;
	Communicator communicator;
	public ATextFieldOutCoupler(TextField theTextField, Communicator theCommunicator) {
		textField = theTextField;
		communicator = theCommunicator;
		textField.addTextFieldListener(this);
	}
	@Override
	public void elementInserted(int index, char ch) {
		communicator.toOthers(new ACharInsertion(textField.getName(), index, ch));		
	}
}
