package mp.painter;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;

public interface CommandUi extends ActionListener, PropertyChangeListener{
	public JButton getSay();
	public JButton getApproach();
	public JButton getPassed();
	public JButton getFailed();
}
