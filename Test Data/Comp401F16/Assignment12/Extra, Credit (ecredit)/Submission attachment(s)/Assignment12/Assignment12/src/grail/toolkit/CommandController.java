package grail.toolkit;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JMenuItem;


public interface CommandController extends ActionListener, PropertyChangeListener, KeyListener{
	public JMenuItem getMenuItem();
	public JMenuItem getMenuItemTwo();
	public JButton getButton();
	public JButton getButtonTwo();
	public JButton getButtonThree();
	public JButton getButtonFour();
	public JButton getFail();
	public JButton getPass();
	public JButton getApproach();
	public JButton getSay();
}
