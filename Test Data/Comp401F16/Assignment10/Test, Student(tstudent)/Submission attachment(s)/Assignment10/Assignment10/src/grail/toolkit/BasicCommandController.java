package grail.toolkit;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import grail.commander.CommandInterpreter;
import grail.scene.BridgeScene;
import util.annotations.PropertyNames;
import util.annotations.Tags;

@Tags({"CommandInterpreterController"})
@PropertyNames({"MenuItem", "MenuItemTwo" , "Button", "ButtonTwo", "ButtonThree", "ButtonFour"}) 
public class BasicCommandController implements CommandController {
	CommandInterpreter currentInterpreter;
	CommandWidgets aWidget;
	JButton execute, arthurUp, arthurDown, arthurLeft, arthurRight, pass,say,fail,approach;
	JMenuItem sayHello, sayGoodbye;
	BridgeScene scene;
	
	
	public BasicCommandController(CommandInterpreter interpreter, CommandWidgets widget) {
		this.currentInterpreter = interpreter;
		this.aWidget = widget;
		this.execute = this.aWidget.getExecuteButton();
		this.arthurUp = this.aWidget.getArthurUp();
		this.arthurDown = this.aWidget.getArthurDown();
		this.arthurLeft = this.aWidget.getArthurLeft();
		this.arthurRight = this.aWidget.getArthurRight();
		this.sayHello = this.aWidget.getArthurHello();
		this.sayGoodbye = this.aWidget.getArthurGoodbye();
		this.pass = this.aWidget.getPassed();
		this.say = this.aWidget.getSay();
		this.fail = this.aWidget.getFailed();
		this.approach = this.aWidget.getApproach();
		this.execute.addActionListener(this);
		this.arthurUp.addActionListener(this);
		this.arthurDown.addActionListener(this);
		this.arthurLeft.addActionListener(this);
		this.arthurRight.addActionListener(this);
		this.sayHello.addActionListener(this);
		this.sayGoodbye.addActionListener(this);
		this.pass.addActionListener(this);
		this.say.addActionListener(this);
		this.fail.addActionListener(this);
		this.approach.addActionListener(this);
		this.say.setEnabled(false);
		this.pass.setEnabled(false);
		this.fail.setEnabled(false);
		this.approach.setEnabled(false);
	}
	
	public BasicCommandController(CommandInterpreter interpreter) {
		this(interpreter, new ToolkitCommandViewer(interpreter));
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.execute) {
			this.currentInterpreter.setCommand(this.aWidget.getCommandField().getText());
		} else if (e.getSource() == this.arthurUp) {
			this.currentInterpreter.setCommand("move arthur + 0 + 100");
		} else if (e.getSource() == this.arthurDown) {
			this.currentInterpreter.setCommand("move arthur + 0 - 100");
		} else if (e.getSource() == this.arthurLeft) {
			this.currentInterpreter.setCommand("move arthur - 100 + 0 ");
		} else if (e.getSource() == this.arthurRight) {
			this.currentInterpreter.setCommand("move arthur + 100 + 0");
		} else if (e.getSource() == this.sayGoodbye) {
			this.currentInterpreter.setCommand("say \"Goodbye\"");
		} else if (e.getSource() == this.sayHello) {
			this.currentInterpreter.setCommand("say \"Hello\"");
		} else if (e.getSource() == this.say){
			this.scene.say("Hello!");
		} else if (e.getSource() == this.pass) {
			this.scene.passed();
		} else if (e.getSource() == this.fail) {
			this.scene.failed();
		} else if (e.getSource() == this.approach) {
			this.scene.approach(this.scene.getArthur());
		} else {
			//do nothing
		}
		
	}

	public JTextField getTextField(){return this.aWidget.getCommandField();}
	
	public JMenuItem getMenuItem() {return this.sayHello;}

	public JMenuItem getMenuItemTwo() {return this.sayGoodbye;}

	public JButton getButton() {return this.arthurRight;}

	public JButton getButtonTwo() {return this.arthurUp;}

	public JButton getButtonThree() {return this.arthurLeft;}

	public JButton getButtonFour() {return this.arthurDown;}
	
	public JButton getFail(){return this.fail;}
	
	public JButton getPass(){return this.pass;}
	
	public JButton getApproach(){return this.approach;}
	
	public JButton getSay(){return this.say;}
	
	public void setScene(BridgeScene scene) {
		this.scene = scene;
		this.scene.addPropertyChangeListener(this);
		this.say.setEnabled(this.scene.preSay());
		this.pass.setEnabled(this.scene.prePassed());
		this.fail.setEnabled(this.scene.preFailed());
		this.approach.setEnabled(this.scene.preApproach());
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if ("approach".equals(evt.getOldValue())) {
			this.approach.setEnabled((boolean)evt.getNewValue());
		} else if ("say".equals(evt.getOldValue())) {
			this.say.setEnabled((boolean)evt.getNewValue());
		} else if ("failed".equals(evt.getOldValue())) {
			this.fail.setEnabled((boolean)evt.getNewValue());
		} else if ("passed".equals(evt.getOldValue())) {
			this.pass.setEnabled((boolean)evt.getNewValue());
		} else {
			//do nothing
		}
		
	}
	
	

}
