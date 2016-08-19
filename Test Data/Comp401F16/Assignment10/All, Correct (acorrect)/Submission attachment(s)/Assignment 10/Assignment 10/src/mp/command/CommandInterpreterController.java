package mp.command;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JTextField;

public interface CommandInterpreterController extends ActionListener,PropertyChangeListener{
	public JTextField getTextField();
	public void propertyChange(PropertyChangeEvent evt);
}
