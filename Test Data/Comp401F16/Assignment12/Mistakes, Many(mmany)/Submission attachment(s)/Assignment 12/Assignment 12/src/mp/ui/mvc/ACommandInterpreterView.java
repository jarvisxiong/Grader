package mp.ui.mvc;

import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import mp.bean.CommandInterpreter;
import util.annotations.Tags;
import util.misc.ThreadSupport;

@Tags({"ProgressBarCreator", "ObservableCommandInterpreter"})

public class ACommandInterpreterView implements CommandInterpreterView {
	
	JTextField textField;
	JLabel errorMsg;
	JMenuItem menuItem;
	JButton moveButton, sayButton, passButton, failButton, approachButton;
	static JProgressBar progressBar;
	int progress;

	final static int progressWait = 25;
	
	public ACommandInterpreterView(CommandInterpreter interpreter) {
		interpreter.addPropertyChangeListener(this);
		JFrame frame2 = new JFrame("Command Interpreter"); 
		textField = new JTextField(); 
		JLabel errorLabel = new JLabel("ERROR:");
		errorLabel.setHorizontalAlignment((int) JLabel.LEFT_ALIGNMENT);
		errorLabel.setVerticalAlignment((int) JLabel.TOP_ALIGNMENT);
		errorMsg = new JLabel("");
		errorMsg.setHorizontalAlignment((int) JLabel.CENTER_ALIGNMENT);	
		moveButton = new JButton("Move All by (50, 0)");
		sayButton = new JButton("Say \"Hello\"");
		passButton = new JButton("Pass");
		failButton = new JButton("Fail");
		approachButton = new JButton("Approach");
		JMenu menu = new JMenu("Misc.");
		JMenuBar menuBar = new JMenuBar();
		menuItem = new JMenuItem("Move All by (0, 50)");
		menu.add(menuItem);
		menuBar.add(menu);
		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		frame2.setJMenuBar(menuBar);
		frame2.setLayout(new GridLayout(9,1));
		frame2.add(textField);
		frame2.add(moveButton);
		frame2.add(sayButton);
		frame2.add(passButton);
		frame2.add(failButton);
		frame2.add(approachButton);
		frame2.add(errorLabel);
		frame2.add(errorMsg);
		frame2.add(progressBar);
		frame2.setSize(400, 300);
		frame2.setVisible(true);
	}

	public JTextField getTextField(){
		return textField;
	}
	public JMenuItem getMenuItem(){
		return menuItem;
	}
	public JButton getMoveButton(){
		return moveButton;
	}
	public JButton getSayButton(){
		return sayButton;
	}
	public JButton getPassButton(){
		return passButton;
	}
	public JButton getFailButton(){
		return failButton;
	}
	public JButton getApproachButton(){
		return approachButton;
	}
	
	static JProgressBar getProgressBar(){
		return progressBar;
	}
	public void runProgress() {
		for(int i = progressBar.getMinimum(); i <= progressBar.getMaximum(); i++){
            final int percent = i ;
            SwingUtilities.invokeLater(new Runnable(){
                @Override
                public void run() {
                    progressBar.setValue(percent);
                }
            });
            try {
                Thread.sleep(progressWait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	}
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName() == "Error"){
			errorMsg.setText((String)evt.getNewValue());
		}
	}
}
