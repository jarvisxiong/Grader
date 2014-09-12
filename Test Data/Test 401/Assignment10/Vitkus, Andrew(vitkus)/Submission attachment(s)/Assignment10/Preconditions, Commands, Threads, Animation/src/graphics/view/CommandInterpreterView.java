package graphics.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import commandEngine.interpreter.IInterpreter;

@SuppressWarnings("serial")
public class CommandInterpreterView extends JFrame implements ICommandInterpreterView {
	private IInterpreter interpreter;
	private JButton approachButton, passButton, failButton;
	private JTextField commandField;
	private JTextArea errorArea;
	private boolean[] approachAllowed = {true, true, true, true};
	private boolean canFail = true;
	
	public CommandInterpreterView(IInterpreter interpreter, IBridgeScene scene) {
		super("Command Controller");
		
		this.interpreter = interpreter;
		
		buildWindow();
		buildMenu();
		attachListeners();
		scene.addPropertyChangeListener(this);
	}
	
	private void buildWindow() {
		commandField = new JTextField("");
		
		errorArea = new JTextArea("");
		errorArea.setLineWrap(true);
		errorArea.setEditable(false);
		
		setLayout(new GridLayout(3, 1));
		
		JPanel commandPanel = new JPanel(new GridLayout());
		commandPanel.setBorder(BorderFactory.createTitledBorder("Command"));
		commandPanel.add(commandField, BorderLayout.CENTER);
		add(commandPanel);
		
		JPanel errorPanel = new JPanel(new GridLayout());
		errorPanel.setBorder(BorderFactory.createTitledBorder("Errors"));
		errorPanel.add(errorArea, BorderLayout.CENTER);
		
		approachButton = new JButton("Approach");
		passButton = new JButton("Pass");
		passButton.setEnabled(false);
		failButton = new JButton("Fail");
		failButton.setEnabled(false);
		
		JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
		buttonPanel.add(approachButton);
		buttonPanel.add(passButton);
		buttonPanel.add(failButton);
		
		add(errorPanel);
		add(buttonPanel);
	}
	
	private void buildMenu() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu actionsMenu = new JMenu("Actions");
		
		JMenuItem passMenuItem = new JMenuItem("Pass");
		passMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				interpreter.setCommand("pass");
			}
			
		});
		passMenuItem.setEnabled(false);
		
		JMenuItem failMenuItem = new JMenuItem("Fail");
		failMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				interpreter.setCommand("fail");
			}
			
		});
		failMenuItem.setEnabled(false);
		
		JMenu approachAnimationMenu = new JMenu("Approach");
		
		JMenuItem arthurApproachMenuItem = new JMenuItem("Arthur");
		arthurApproachMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				interpreter.setCommand("approach Arthur");
			}
			
		});
		JMenuItem lancelotApproachMenuItem = new JMenuItem("Lancelot");
		lancelotApproachMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				interpreter.setCommand("approach Lancelot");
			}
			
		});
		JMenuItem galahadApproachMenuItem = new JMenuItem("Galahad");
		galahadApproachMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				interpreter.setCommand("approach Galahad");
			}
			
		});
		JMenuItem robinApproachMenuItem = new JMenuItem("Robin");
		robinApproachMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				interpreter.setCommand("approach Robin");
			}
			
		});
		
		approachAnimationMenu.add(arthurApproachMenuItem);
		approachAnimationMenu.add(galahadApproachMenuItem);
		approachAnimationMenu.add(lancelotApproachMenuItem);
		approachAnimationMenu.add(robinApproachMenuItem);
		
		actionsMenu.add(passMenuItem);
		actionsMenu.add(failMenuItem);
		actionsMenu.add(approachAnimationMenu);
		
		menuBar.add(actionsMenu);
		
		setJMenuBar(menuBar);
	}
	
	private void attachListeners() {
		interpreter.addPropertyChangeListener(this);
		
		commandField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				interpreter.setCommand(commandField.getText());
			}
			
		});
		
		approachButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (approachAllowed[0]) {
					interpreter.setCommand("approach arthur");
				} else if (approachAllowed[1]) {
					interpreter.setCommand("approach galahad");
				} else if (approachAllowed[2]) {
					interpreter.setCommand("approach lancelot");
				} else if (approachAllowed[3]) {
					interpreter.setCommand("approach robin");
				}
			}
			
		});
		
		passButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				interpreter.setCommand("pass");
			}
			
		});
		
		failButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				interpreter.setCommand("fail");
			}
			
		});
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		//System.out.println(evt.getSource().getClass().getSimpleName() + ", " + evt.getPropertyName());
		if (evt.getSource() instanceof IInterpreter) {
			switch(evt.getPropertyName()) {
			case "command":
				commandField.setText((String) evt.getNewValue());
				break;
			case "error":
				errorArea.setText((String)evt.getNewValue());
				break;
			}
		} else if (evt.getSource() instanceof IBridgeScene) {
			if (evt.getPropertyName().equals("this")) {
				switch((String)evt.getOldValue()) {
				case "pass":
					getJMenuBar().getMenu(0).getItem(0).setEnabled((Boolean)evt.getNewValue());
					passButton.setEnabled((Boolean)evt.getNewValue());
					break;
				case "fail":
					getJMenuBar().getMenu(0).getItem(1).setEnabled((Boolean)evt.getNewValue() && canFail);
					failButton.setEnabled((Boolean)evt.getNewValue() && canFail);
					break;
				case "approach":
					((JMenu)getJMenuBar().getMenu(0).getItem(2)).getItem(0).setEnabled((Boolean)evt.getNewValue() && approachAllowed[0]);
					((JMenu)getJMenuBar().getMenu(0).getItem(2)).getItem(1).setEnabled((Boolean)evt.getNewValue() && approachAllowed[1]);
					((JMenu)getJMenuBar().getMenu(0).getItem(2)).getItem(2).setEnabled((Boolean)evt.getNewValue() && approachAllowed[2]);
					((JMenu)getJMenuBar().getMenu(0).getItem(2)).getItem(3).setEnabled((Boolean)evt.getNewValue() && approachAllowed[3]);
					boolean approachable = false;
					for(boolean b : approachAllowed) {
						if (b) {
							approachable = true;
							break;
						}
					}
					if (approachable) {
						approachButton.setEnabled((Boolean)evt.getNewValue());
					}
					break;
				case "arthur":
					((JMenu)getJMenuBar().getMenu(0).getItem(2)).getItem(0).setEnabled((Boolean)evt.getNewValue());
					approachAllowed[0] = false;
					break;
				case "galahad":
					((JMenu)getJMenuBar().getMenu(0).getItem(2)).getItem(1).setEnabled((Boolean)evt.getNewValue());
					approachAllowed[1] = false;
					break;
				case "lancelot":
					((JMenu)getJMenuBar().getMenu(0).getItem(2)).getItem(2).setEnabled((Boolean)evt.getNewValue());
					approachAllowed[2] = false;
					break;
				case "robin":
					((JMenu)getJMenuBar().getMenu(0).getItem(2)).getItem(3).setEnabled((Boolean)evt.getNewValue());
					approachAllowed[3] = false;
					break;
				case "guard":
					canFail = false;
				}
			}
		} else if (evt.getSource() instanceof BridgeSceneView) {
			if (evt.getPropertyName().equals("Interpreter Visible")) {
				if (!isVisible()) {
					setVisible(true);
				}
			}
		}
	}

	@Override
	public void run() {		
		setSize(350, 175);
		//setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
        setLocationByPlatform(true);
		setVisible(true);
	}
}
