package mp.ui.mvc;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import mp.bean.CommandInterpreter;
import mp.ui.BridgeScene;
import util.annotations.Tags;

@Tags("CommandInterpreterController")

public class ACommandInterpreterController implements CommandInterpreterController {

	CommandInterpreter interpreter;
	BridgeSceneController bsController;
	BridgeScene scene;
	JTextField textField;
	JButton moveButton, sayButton, passButton, failButton, approachButton;
	JMenuItem menuItem;
	JProgressBar progressBar;
	String[] avatars = {"arthur", "galahad", "lancelot", "robin"};
	int progress;
	
	public ACommandInterpreterController(CommandInterpreter ci, BridgeSceneController BridgeSceneController, CommandInterpreterView view) {
		interpreter = ci;
		bsController = BridgeSceneController;
		scene = bsController.getBridgeScene();
		textField = view.getTextField();
		moveButton = view.getMoveButton();
		sayButton = view.getSayButton();
		sayButton.setEnabled(false);
		passButton = view.getPassButton();
		passButton.setEnabled(false);
		failButton = view.getFailButton();
		failButton.setEnabled(false);
		approachButton = view.getApproachButton();
		menuItem = view.getMenuItem();
		scene.addPropertyChangeListener(this);
		textField.addActionListener(this);
		moveButton.addActionListener(this);
		sayButton.addActionListener(this);
		passButton.addActionListener(this);
		failButton.addActionListener(this);
		approachButton.addActionListener(this);
		menuItem.addActionListener(this);
	}
	public JTextField getTextField() {
		return textField;
	}
	public JButton getSay(){
		return sayButton;
	}
	public JButton getPass(){
		return passButton;
	}
	public JButton getFail(){
		return failButton;
	}
	public JButton getApproach(){
		return approachButton;
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source instanceof JTextField){
			String text = ((JTextField) source).getText();
			interpreter.setCommand(text);
			((JTextField) source).setText("");
		}
		else if (source instanceof JMenuItem){
			for(int i = 0; i<avatars.length; i++) {
			interpreter.setCommand("move " + avatars[i] + " 0 50");
			}
		}
		else if (source instanceof JButton){
			if (source == moveButton) {
				for(int i = 0; i<avatars.length; i++) {
					interpreter.setCommand("move " + avatars[i] + " 50 0");
					} 
			}
			else if (source == sayButton) {
				scene.say("Hello");
			}
			else if (source == passButton) {
				scene.passed();
			}
			else if (source == failButton) {
				scene.failed();
			}
			else if (source == approachButton) {
				scene.approach(scene.getGalahad());
			}
		}
	}
	public void propertyChange(PropertyChangeEvent evt) {
		String button = (String) evt.getOldValue();
		boolean preCon = (boolean) evt.getNewValue();
		switch (button) {
		case "say": 
			sayButton.setEnabled(preCon);
			break;
		case "passed":
			passButton.setEnabled(preCon);
			break;
		case "failed":
			failButton.setEnabled(preCon);
			break;
		case "approach":
			approachButton.setEnabled(preCon);
			break;
		}
	}
}
