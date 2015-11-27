package grail.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import grail.scanner.CommandInterpreterInterface;
import util.annotations.Tags;

@Tags({ "CommandInterpreterController" })
public class ACommandInterpreterController implements ActionListener,PropertyChangeListener, CommandInterpreterController {

	CommandInterpreterInterface c;
	JTextField textField;
	JMenuItem menu;
	JButton button;
	JButton sayButton;
	JButton approachButton;
	JButton failButton;
	JButton passButton;
	

	public ACommandInterpreterController(CommandInterpreterInterface ci, JTextField x, JButton b, JMenuItem m, JButton approachButton, JButton sayButton, JButton passButton, JButton failButton) 
	{
		c = ci;
		textField = x;
		button = b;
		menu = m;
		textField.addActionListener(this);
		button.addActionListener(this);
		menu.addActionListener(this);
		
		approachButton.addActionListener(this);
		sayButton.addActionListener(this);
		failButton.addActionListener(this);
		passButton.addActionListener(this);
//		sayButton=new JButton("say");
//		approachButton=new JButton("approach");
//		failButton=new JButton("fail");
//		passButton=new JButton("pass");
		this.sayButton=sayButton;
		this.approachButton=approachButton;
		this.passButton=passButton;
		this.failButton=failButton;
		c.getBriScene().addPropertyChangeListener(this);//listen to BridgeScene property change
	}
	
	public ACommandInterpreterController(CommandInterpreterInterface ci, JTextField x, JButton b, JMenuItem m) 
	{
		c = ci;
		textField = x;
		button = b;
		menu = m;
		textField.addActionListener(this);
		button.addActionListener(this);
		menu.addActionListener(this);
		
//		approachButton.addActionListener(this);
//		sayButton.addActionListener(this);
//		failButton.addActionListener(this);
//		passButton.addActionListener(this);
////		sayButton=new JButton("say");
////		approachButton=new JButton("approach");
////		failButton=new JButton("fail");
////		passButton=new JButton("pass");
//		this.sayButton=sayButton;
//		this.approachButton=approachButton;
//		this.passButton=passButton;
//		this.failButton=failButton;
		c.getBriScene().addPropertyChangeListener(this);//listen to BridgeScene property change
	}
	
	

	public JMenuItem getMenuItem() {
		return menu;
	}

	public JButton getButton() {
		return button;
	}

	public JButton getSayButton() {
		return sayButton;
	}
	
	public JButton getApproachButton() {
		return approachButton;
	}
	
	
	public JTextField getTextField() {
		return textField;
	}
	final int twenty=20;
	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		String current = "";
		// Check for scene object
		/**
		 * public PropertyChangeEvent(Object source,
                           String propertyName,
                           Object oldValue,
                           Object newValue)
		 */
		//when you do register, new PropertyChangeEvent(this,"this", "approach", false), so to distinguis them, we should use oldValue
		if (event.getOldValue().equals("approach"))
		{
			if ((boolean)event.getNewValue())
			{
				approachButton.setEnabled(true);
			}else
			{
				approachButton.setEnabled(false);
			}
		}
		if (event.getOldValue().equals("say"))
		{
			if ((boolean)event.getNewValue())
			{
				sayButton.setEnabled(true);
			}else
			{
				sayButton.setEnabled(false);
			}
		}
		if (event.getOldValue().equals("fail"))
		{
			if ((boolean)event.getNewValue())
			{
				failButton.setEnabled(true);
			}else
			{
				failButton.setEnabled(false);
			}
		}
		if (event.getOldValue().equals("pass"))
		{
			if ((boolean)event.getNewValue())
			{
				passButton.setEnabled(true);
			}else
			{
				passButton.setEnabled(false);
			}
		}
	}
	
	//respond to action events
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == textField) {
			c.setCommand(e.getActionCommand());
		}
		if (e.getSource() == button) {
			c.getBriScene().getArthur().move(0, twenty);
			c.getBriScene().getRobin().move(0, twenty);
			c.getBriScene().getGalahad().move(0, twenty);
			c.getBriScene().getGuard().move(0,twenty);
			c.getBriScene().getLancelot().move(0,twenty);
		}
		if(e.getSource()==menu){
			c.getBriScene().getArthur().move(twenty,0);
			c.getBriScene().getRobin().move(twenty,0);
			c.getBriScene().getGalahad().move(twenty, 0);
			c.getBriScene().getGuard().move(twenty, 0);
			c.getBriScene().getLancelot().move(twenty,0);
		}
		if (e.getSource()==sayButton)
		{
			c.getBriScene().sayScene("say Button works");
		}
		
		if (e.getSource()==passButton)
		{
			c.getBriScene().passScene();
		}
		
		if (e.getSource()==failButton)
		{
			c.getBriScene().failScene();
		}
		
		if (e.getSource()==approachButton)
		{
			c.getBriScene().approachScene(c.getBriScene().getRobin());
		}

	}

}
