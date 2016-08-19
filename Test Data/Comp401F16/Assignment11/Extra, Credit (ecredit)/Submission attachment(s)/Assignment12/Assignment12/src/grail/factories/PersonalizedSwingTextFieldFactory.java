package grail.factories;

import java.awt.Color;

import javax.swing.JTextField;

import bus.uigen.widgets.TextFieldFactory;
import bus.uigen.widgets.swing.SwingTextFieldFactory;

public class PersonalizedSwingTextFieldFactory extends SwingTextFieldFactory implements TextFieldFactory {
	
	@Override
	protected JTextField createJTextField(String aText) {
		JTextField toReturn = new JTextField(aText);
		toReturn.setBackground(Color.CYAN);
		toReturn.setForeground(Color.RED);
		return toReturn;
	} 
	
	
}
