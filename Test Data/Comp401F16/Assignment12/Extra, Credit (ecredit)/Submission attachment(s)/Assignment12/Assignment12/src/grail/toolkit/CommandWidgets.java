package grail.toolkit;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public interface CommandWidgets {
	public JTextField getCommandField();
	
	public JButton getExecuteButton();
	
	public JTextArea getErrorField();
	
	public JButton getArthurRight();
	
	public JButton getArthurLeft();
	
	public JButton getArthurUp();
	
	public JButton getArthurDown();
	
	public JMenuItem getArthurHello();
	
	public JMenuItem getArthurGoodbye();
	
	public JButton getSay();
	
	public JButton getApproach();
	
	public JButton getPassed();
	
	public JButton getFailed();
	
}
