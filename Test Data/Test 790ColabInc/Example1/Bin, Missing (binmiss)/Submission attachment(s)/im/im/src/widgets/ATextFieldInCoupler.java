package widgets;
import util.session.PeerMessageListener;
public class ATextFieldInCoupler implements PeerMessageListener {
	TextField textField;
	String myName;
	public ATextFieldInCoupler(TextField theTextField) {
		textField = theTextField;
		myName = textField.getName();
	}
	public void objectReceived(Object message, String userName) {
		CharInsertion charInsertion = (CharInsertion) message;
		if (charInsertion.getName().equals(myName))
			textField.insert(charInsertion.getIndex(), charInsertion.getChar());
		
	}
	

}
