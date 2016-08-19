package mp.ui.mvc;

import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public interface CommandInterpreterView extends PropertyChangeListener{

	JTextField getTextField();

	JButton getMoveButton();

	JMenuItem getMenuItem();

	void runProgress();

	JButton getSayButton();

	JButton getPassButton();

	JButton getFailButton();

	JButton getApproachButton();

}
