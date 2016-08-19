package mp.painter;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.BridgeScene;
import main.CommandInterpreter;

public class ACommandUi extends JPanel implements CommandUi{
	private CommandInterpreter cmd; 
	private BridgeScene scene;
	
	private final String approach = "approach";
	private final String say = "say";
	private final String passed = "passed";
	
	private JButton passButton;
	private JButton approachButton;
	private JButton sayButton;
	private JButton failButton;
	
	public ACommandUi(CommandInterpreter cmd, BridgeScene scene){
		this.cmd = cmd;
		this.scene = scene;
		scene.addPropertyChangeListener(this);
		
		setVisible(true);
		
		passButton = new JButton("Pass"); passButton.setActionCommand(passed);
		approachButton = new JButton("approachButton");approachButton.setActionCommand(approach);
		sayButton = new JButton(say);sayButton.setActionCommand(say);
		failButton = new JButton("Fail");failButton.setActionCommand("failed");
		
		passButton.addActionListener(this);
		approachButton.addActionListener(this);
		sayButton.addActionListener(this);
		failButton.addActionListener(this);
		
		add(passButton); add(approachButton); add(sayButton); add(failButton);
	}

	
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(say.equals(e.getActionCommand())){
			scene.say("Hello World");
		}else if(approach.equals(e.getActionCommand())){
			scene.approach(scene.getArthur());
		}else if(passed.equals(e.getActionCommand())){
			scene.passed();
		}else{
			scene.failed();
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		if(scene.preSay()){
			sayButton.setEnabled(true);
		}else {sayButton.setEnabled(false);}
		
		if(scene.preApproach()){
			approachButton.setEnabled(true);
		}else {approachButton.setEnabled(false);}
		
		if(scene.preFailed()){
			failButton.setEnabled(true);
		}else {failButton.setEnabled(false);}
		
		if(scene.prePassed()){
			passButton.setEnabled(true);
		}else {passButton.setEnabled(false);}
		
	}



	@Override
	public JButton getSay() {
		return sayButton;
	}



	@Override
	public JButton getApproach() {
		return approachButton;
	}



	@Override
	public JButton getPassed() {
		return passButton;
	}



	@Override
	public JButton getFailed() {
		return failButton;
	}
}
