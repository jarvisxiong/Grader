package widgets;

import util.awt.ADelegateFrame;

public class TextFieldLauncher {
	public static void main(String[] args) {
		TextField text1 = createTextField("text 1");
		text1.insert(0, 'a');
		createTextField("text 2");
	}
	public static TextField  createTextField(String theTitle) {
		ADelegateFrame frame = new ADelegateFrame(theTitle);
		ATextField textField = new ATextField(frame);
		frame.setSize(300, 100);
		frame.setVisible(true);		
		return textField;
	}
}
