package replicatedWindow;


import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import util.awt.AGlassPaneRedispatcher;

public class WidgetTesterLauncher {
	public static void main(String[] args) {
		AnInputQueue.useAsEventQueue();

		createFrameWithWidgets("frame 1");
		createFrameWithWidgets("frame 2");
	}
	public static void createFrameWithWidgets(String theTitle) {
//		Frame frame = new Frame(theTitle);
//		Frame frame = new ATelePointerFrame(theTitle);
		JFrame frame = new JFrame(theTitle);

//		Frame frame = new ADelegateFrame(theTitle);
		
//		frame.setContentPane(glassPane);
		frame.setLayout(new GridLayout(5, 2));

		JTextField jTextField = new JTextField("JTextField: Edit me");
		JSlider slider = new JSlider();
		TextField textField = new TextField("Text Field: Edit me");
		Checkbox checkBox = new Checkbox(); // auto scrolled by OS with no events sent
		JCheckBox jCheckBox = new JCheckBox();
		Button button = new Button("Button: Press me");
		JButton jButton = new JButton("JButton: Press me");
		JTextArea jTextArea = new JTextArea ("JTextArea");
		TextArea textArea = new TextArea ("TextArea");
		String[] choices = {"Red", "Green", "Blue"};
		JComboBox jComboBox = new JComboBox(choices);
		JScrollPane jTextAreaScrollPane = new JScrollPane();
		jTextAreaScrollPane.setViewportView(jTextArea);


//		TextField textField = new ProcessingEventTextField("Edit me");

		frame.add(jTextField);
		frame.add(textField);
//		frame.add(jTextArea);
		frame.add(jTextAreaScrollPane);
		frame.add(textArea);
		frame.add(jButton);
		frame.add(button);
		frame.add(checkBox);
		frame.add(jCheckBox);
		frame.add(slider);
		frame.add(jComboBox);
//		frame.add(textField4);
//		new ACharacterDrawer(frame);
		frame.setSize(300, 300);
		frame.setVisible(true);
		// to be commented in later
//		
//		JComponent glassPane = new ATelePointerGlassPane(frame);
//
//		AGlassPaneRedispatcher redispatcher = new AGlassPaneRedispatcher(glassPane, frame);
//		frame.setGlassPane(glassPane);
//		glassPane.setVisible(true);
		putGlassPane(frame);

//		return frame;
	}
	protected static void putGlassPane(JFrame aFrame) {
		DelegatingTelepointerGlassPane aGlassPane = new ADelegatingTelePointerGlassPane(aFrame);
		aGlassPane.addPainter(new AnOvalGraphicsPainter(aGlassPane));
//		AGlassPaneRedispatcher redispatcher = new AGlassPaneRedispatcher(glassPane, aFrame);
//		aFrame.setGlassPane(glassPane);
//		glassPane.setVisible(true);
		
	}
}
