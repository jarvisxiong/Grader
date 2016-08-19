package mp.command;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import mp.Scene.BridgeScene;
import util.annotations.Tags;
import util.misc.ThreadSupport;

@Tags({"CommandInterpreterController"})
public class ACommandInterpreterController implements CommandInterpreterController{
	CommandInterpreter command;
	BridgeScene aScene;
	JTextField input;
	JButton say,pass, fail,approach;
	final int wushi=50;
	final int erbai=200;
	final int shi=10;
	public ACommandInterpreterController(BridgeScene newScene,CommandInterpreter aCommand, JTextField aInput,
			JButton sayB,JButton passB, JButton failB,JButton approachB){
		command=aCommand;
		aScene=newScene;
		input=aInput;
		say=sayB;
		pass=passB;
		fail=failB;
		approach=approachB;
		input.addActionListener(this);
		say.addActionListener(this);
		pass.addActionListener(this);
		fail.addActionListener(this);
		approach.addActionListener(this);
		aScene.addPropertyChangeListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==input){
		JTextField sourse=(JTextField)e.getSource();
		String text=sourse.getText();
		command.setCommand(text);
		}
		else if(e.getSource()==approach){
			aScene.approach(aScene.getGalahad());
		}
		else if(e.getSource()==pass){
			aScene.passedScene();
		}
		else if(e.getSource()==fail){
			aScene.failedScene();
		}
		else if(e.getSource()==say){
			aScene.sayScene("Say Button");
		}
		
	}
	
	public JTextField getTextField(){
		return input;
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if("appraoch".equalsIgnoreCase((String)evt.getOldValue())){
			if(!(boolean)evt.getNewValue()){
				approach.setEnabled(false);
		}
			else if((boolean)evt.getNewValue()){
				approach.setEnabled(true);
			}
	}
		if("pass".equalsIgnoreCase((String)evt.getOldValue())){
			if(!(boolean)evt.getNewValue()){
				pass.setEnabled(false);
		}
			else if((boolean)evt.getNewValue()){
				pass.setEnabled(true);
			}
	}
		if("fail".equalsIgnoreCase((String)evt.getOldValue())){
			if(!(boolean)evt.getNewValue()){
				fail.setEnabled(false);
		}
			else if((boolean)evt.getNewValue()){
				fail.setEnabled(true);
			}
	}
		if("say".equalsIgnoreCase((String)evt.getOldValue())){
			if(!(boolean)evt.getNewValue()){
				say.setEnabled(false);
		}
			else if((boolean)evt.getNewValue()){
				say.setEnabled(true);
			}
	}
	
}
}
