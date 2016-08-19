package mp.ui.mvc;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.JTextField;

import util.annotations.Tags;

@Tags("CommandInterpreterController")

public interface CommandInterpreterController extends ActionListener, PropertyChangeListener{
	
	public JTextField getTextField();

}
